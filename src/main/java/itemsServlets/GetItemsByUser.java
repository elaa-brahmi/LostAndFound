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
import java.util.List;
import java.util.Map;
@WebServlet(name="UserItems" ,urlPatterns = "/UserItems")
public class GetItemsByUser extends HttpServlet {
    Connection con;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, List<Item>> AllItems = new HashMap<>();
        AllItems.put("pending", new ArrayList<>());
        AllItems.put("accepted", new ArrayList<>());

        System.out.println("GET ALL ITEMS by user");
        try {
            con = BDConnection.getConnection();
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println("User ID: " + userId);

            // Récupérer les éléments ACCEPTED
            PreparedStatement ps = con.prepareStatement("SELECT * FROM item WHERE status = 'ACCEPTED' AND userid=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AllItems.get("accepted").add(mapItem(rs));
            }
            System.out.println("Accepted items: " + AllItems.get("accepted"));

            // Récupérer les éléments PENDING
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM item WHERE status = 'PENDING' AND userid=?");
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();

            boolean hasPendingItems = false;
            while (rs1.next()) {
                hasPendingItems = true;
                AllItems.get("pending").add(mapItem(rs1));
            }
            System.out.println("Found pending items: " + hasPendingItems);
            System.out.println("Pending items list: " + AllItems.get("pending"));

            // Convertir en JSON et envoyer la réponse
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            String json = gson.toJson(AllItems);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Database error\"}");
        }
    }

    private Item mapItem(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String category = rs.getString("category");
        String location = rs.getString("location");
        String image = rs.getString("image");
        LocalDate date = rs.getDate("datefound").toLocalDate();
        String type = rs.getString("type");
        Integer userid = rs.getInt("userid");
        String status = rs.getString("status");
        return new Item(id, name, description, category, location, image, date,
                ItemStatus.valueOf(status), userid, ItemType.valueOf(type));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
