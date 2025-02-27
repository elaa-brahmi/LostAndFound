package items;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ItemType;
import services.BDConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


@WebServlet(name="addItem",urlPatterns="/addItem")
public class AddItem extends HttpServlet {
    Connection con;
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost added item");
        String name = request.getParameter("name");
        String description=request.getParameter("description");
        String category=request.getParameter("category");
        String location=request.getParameter("location");
        String image=request.getParameter("image");
        String date=request.getParameter("date");
        String type=request.getParameter("type");
        System.out.println(name + " " + description + " " + category + " " + location + " " + image + " " + date + " " + type );
        try{
             con= BDConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into item(name,description,category,location,image,datefound,status,type,userid) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2,description);
            ps.setString(3,category);
            ps.setString(4,location);
            ps.setString(5,image);
            LocalDate localDate = LocalDate.parse(date);
            ps.setDate(6,Date.valueOf(localDate));
            ps.setString(7,"PENDING");
            ps.setString(8,type.toUpperCase());
            session=request.getSession();
            Integer userId= (Integer) session.getAttribute("userId");
            System.out.println(userId);
            ps.setInt(9,userId);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("item added");
            }



        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
