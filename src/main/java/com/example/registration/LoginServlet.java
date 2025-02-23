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
import java.util.Optional;

@WebServlet(name="login" ,urlPatterns="/login")

public class LoginServlet extends HttpServlet {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private HttpSession session;
    private final UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
      String password = request.getParameter("password");
        try {
            if(userDao.authenticate(email,password)!=null){
                Optional<User> user=userDao.authenticate(email,password);
                session.setAttribute("userId",user.get().getId());
                session.setAttribute("email",email);
                session.setAttribute("role",user.get().getRole());
                response.sendRedirect("index.jsp");

            }
            else{
                request.setAttribute("error","Invalid email or password");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost login is triggered");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet login is triggered");
    }
}
