package com.example.registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.BDConnection;
import services.PasswordUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name="newPassword", urlPatterns={"/newPassword"})
public class newPassword extends HttpServlet {
    private Connection con;
    private HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confPassword");
        session = request.getSession();
        String email = session.getAttribute("email").toString();
        if (password!=null && confirmPassword!=null && password.equals(confirmPassword)) {
            try {
                con = BDConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("update users set password=? where email=?");
                String hashedPassword= PasswordUtil.hashPassword(password);
                ps.setString(1, hashedPassword);
                ps.setString(2, email);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Password updated");
                    request.setAttribute("passwordUpdate", "Password updated login again");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{

        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
