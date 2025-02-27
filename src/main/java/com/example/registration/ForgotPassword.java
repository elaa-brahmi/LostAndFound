package com.example.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailSender;

import java.io.IOException;
@WebServlet(name="ForgotPassword", urlPatterns={"/ForgotPassword"})
public class ForgotPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String otp= EmailSender.generateOtp();
        EmailSender.sendEmail(email, otp);
        HttpSession session=request.getSession();
        session.setAttribute("otp", otp);
        System.out.println(session.getAttribute("otp"));
        session.setAttribute("email", email);
        response.sendRedirect("EnterOtp.jsp");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
