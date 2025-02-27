package com.example.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import services.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name="login" ,urlPatterns="/login")

public class LoginServlet extends HttpServlet {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private HttpSession session=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            if(UserDao.authenticate(email, password)==null) {
                request.setAttribute("error","Invalid email or password");
                System.out.println("Invalid email or password in loginServlet");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            else{
                System.out.println("user logged in in loginServlet");
                Optional<User> user=UserDao.authenticate(email,password);
                session=request.getSession();
                System.out.println(user.toString());
                session.setAttribute("userId",user.get().getId());
                session.setAttribute("email",email);
                session.setAttribute("name",user.get().getName());
                if(user.get().getRole().toString().equals("ADMIN")) {
                    System.out.println("admin logged in in loginServlet");
                    session.setAttribute("role", user.get().getRole().toString().toLowerCase());
                    response.sendRedirect("adminDashboard.jsp");
                }
                else{
                        System.out.println("user logged in in loginServlet");
                        session.setAttribute("role",user.get().getRole().toString().toLowerCase());
                        response.sendRedirect("index.jsp");
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet login is triggered");
    }
}
