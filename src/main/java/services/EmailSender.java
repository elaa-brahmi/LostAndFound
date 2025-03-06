package services;


import jakarta.mail.Message;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class EmailSender {
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static void sendNotifEmail(String recipient, String subject, String messageText) {
        String senderEmail = "alabrahmi12340@gmail.com";
        String senderPassword = "xwec eqjm nqqq wqla";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
            System.out.println("📧 Email sent to " + recipient);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail(String receiverEmail, String otp) {
        String from = "elaa.brahmii@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("alabrahmi12340@gmail.com", "xwec eqjm nqqq wqla");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmail));
            message.setSubject("Votre code OTP");
            message.setText("Votre OTP est : " + otp);
            Transport.send(message);
            System.out.println("OTP envoyé avec succès !");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
