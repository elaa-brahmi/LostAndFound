package com.example.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name="ValidateOtp", urlPatterns={"/ValidateOtp"})
public class ValidateOtp extends HttpServlet {
    private HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otp = request.getParameter("otp");
        session = request.getSession();
        if(otp!=null && session.getAttribute("otp")!=null && session.getAttribute("otp").equals(otp)) {

            response.sendRedirect("newPassword.jsp");
        }
        else{
            request.setAttribute("message", "Invalid otp");
            request.getRequestDispatcher("EnterOtp.jsp").forward(request, response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
