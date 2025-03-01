package userServlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import services.BDConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet(name="UserInfos",urlPatterns="/UserInfos")
public class GetUser extends HttpServlet {
    Connection con ;
    User user=null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            con = BDConnection.getConnection();
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println("userId:" + userId);
            PreparedStatement ps0 = con.prepareStatement("select * from users where id=?");
            ps0.setInt(1, userId);
            ResultSet rs0 = ps0.executeQuery();
            while (rs0.next()) {
                Integer Id = rs0.getInt("id");
                String name = rs0.getString("name");
                String email = rs0.getString("email");
                String phone = rs0.getString("phone");
                 user = new User(Id, name, email, phone);
                System.out.println("user: " + user);
            }
            Gson gson = new Gson();
            String userDetails= gson.toJson(user);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            // Write the JSON to the response
            PrintWriter out = response.getWriter();
            out.print(userDetails);
            out.flush();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
