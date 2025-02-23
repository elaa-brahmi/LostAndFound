package com.example.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Role;
import model.User;
import org.hibernate.Session;
import services.UserDao;

import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
@WebServlet(name="RegistrationServlet" ,urlPatterns="/register")
public class RegistrationServlet extends HttpServlet {
    private HttpSession session;
    private final UserDao userDao=new UserDao();
    public RegistrationServlet() {
        System.out.println("Registration Servlet Initialized!");

    }
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private final UserDao userService = new UserDao();
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost register is triggered");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String confirmPassword=request.getParameter("confirmPassword");
        String phone=request.getParameter("phoneNumber");

        StringBuilder message=new StringBuilder();
        if(!confirmPassword.equals(password)){
            System.out.println("password does not match");
            message.append("Password and confirm password do not match");
            request.setAttribute("error",message.toString());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;


        }
        if(phone.length()!=8){
            System.out.println("Phone number is too short or too long");
            message.append("Phone number is too short or too long");
            request.setAttribute("error",message.toString());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;

        }
        if(!isValidEmail(email)){
            System.out.println("Invalid email");
            message.append("Invalid email");
            request.setAttribute("error",message.toString());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;

        }
        //todo: check is the email is already there
        User user=new User(name,email,password, Role.USER,phone);
        try {
            if(userDao.insertUser(user)){
                System.out.println("User registered successfully");
                request.setAttribute("status","success");
                session.setAttribute("userId",user.getId());
                session.setAttribute("username",user.getName());
                session.setAttribute("role","user");
                response.sendRedirect("index.jsp");
            }
            else{
                request.setAttribute("status","fail");
            }
        } catch (SQLException e) {
            request.setAttribute("status","fail");
            e.printStackTrace();
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet register is triggered");
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }
}
