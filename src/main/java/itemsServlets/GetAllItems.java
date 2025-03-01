package itemsServlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemStatus;
import model.ItemType;
import services.BDConnection;
import services.LocalDateAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="allItems",urlPatterns = "/allItems")
public class GetAllItems extends HttpServlet {

    Connection con;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
        int pageSize = Integer.parseInt(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "6");

        try{
            con= BDConnection.getConnection();
            // Query to get the total number of items
            PreparedStatement countPs = con.prepareStatement("SELECT COUNT(*) AS total FROM item WHERE status = 'ACCEPTED'");
            ResultSet countRs = countPs.executeQuery();
            countRs.next();
            int totalItems = countRs.getInt("total");
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);
            PreparedStatement ps=con.prepareStatement("SELECT * FROM item WHERE status = 'ACCEPTED' LIMIT ? OFFSET ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs=ps.executeQuery();
            ArrayList<Item> items=new ArrayList<>();
            while(rs.next()){
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                String description=rs.getString("description");
                String category=rs.getString("category");
                String location=rs.getString("location");
                String image=rs.getString("image");
                LocalDate date= rs.getDate("datefound").toLocalDate();
                String type=rs.getString("type");
                Integer userid=rs.getInt("userid");
                String status=rs.getString("status");
                Item item=new Item(id,name,description,category,location,image,date, ItemStatus.valueOf(status), userid, ItemType.valueOf(type));
                items.add(item);
            }

            // Create a response object with items and pagination metadata
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("itemsServlets", items);
            responseData.put("totalItems", totalItems);
            responseData.put("currentPage", page);
            responseData.put("totalPages", totalPages);
            // Convert list to JSON
            // Create a Gson instance with the custom LocalDate adapter
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            String json = gson.toJson(responseData);

            // Set the response content type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write the JSON to the response
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Database error\"}");
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
