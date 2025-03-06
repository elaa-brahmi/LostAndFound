package itemsServlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.BDConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


@WebServlet(name="addItem",urlPatterns="/addItem")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB threshold
        maxFileSize = 1024 * 1024 * 10,      // 10MB max file size
        maxRequestSize = 1024 * 1024 * 50    // 50MB max request size
)
public class AddItem extends HttpServlet {
    Connection con;
    HttpSession session;
//    private String uploadPath;
//    @Override
//    public void init() throws ServletException {
//        // Read the upload directory from web.xml
//        ServletContext context = getServletContext();
//        uploadPath = context.getInitParameter("UPLOAD_DIR");
//
//        // Ensure the directory exists
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            boolean dirCreated = uploadDir.mkdirs();
//            if (!dirCreated) {
//                throw new ServletException("Failed to create upload directory: " + uploadPath);
//            }
//        }
//    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String description=request.getParameter("description");
        String category=request.getParameter("category");
        String location=request.getParameter("location");
        String date=request.getParameter("date");
        String type=request.getParameter("type");


        Part filePart = request.getPart("image"); // Input name="image" in form
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        String filePath = uploadPath + File.separator + fileName;
//        filePart.write(filePath);
//
//        // Save file path in the database
//
//        System.out.println(filePath);
//        System.out.println(name + " " + description + " " + category + " " + location + " " + filePath + " " + date + " " + type );
//
        // Get the absolute path to webapp/uploads/
        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directory if it doesn't exist
        }

        File file = new File(uploadPath, fileName);
        filePart.write(file.getAbsolutePath());

        // Store relative path in the database (NOT absolute path)
        String imagePath = "uploads/" + fileName;

        System.out.println("File saved at: " + file.getAbsolutePath());
        System.out.println("Image Path: " + imagePath);
      try{
             con= BDConnection.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into item(name,description,category,location,image,datefound,status,type,userid) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2,description);
            ps.setString(3,category);
            ps.setString(4,location);
            ps.setString(5,imagePath);
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
                getServletContext().getRequestDispatcher("/MyPosts.jsp").forward(request,response);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
