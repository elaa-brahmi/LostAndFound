package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Role;
import model.User;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;



public class UserDao{
    private static Connection connection=null;
    private static final Logger logger =Logger.getLogger(UserDao.class.getName());
    public UserDao() {
    }
    public static Optional<User> authenticate(String email, String password) throws SQLException{
        //todo:return an object of the authenticated user
        try{
            connection = BDConnection.getConnection();
            System.out.println("Connected to the database");
            PreparedStatement ps=connection.prepareStatement("select * from users where email=? and password=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                    User user=new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                System.out.println("user found");
                return Optional.of(user);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertUser(User user) throws SQLException{
        try{
            connection = BDConnection.getConnection();
            System.out.println("Connected to the database");
            PreparedStatement ps0=connection.prepareStatement("select user u from users where email=?");
            ps0.setString(1,user.getEmail());
            ResultSet rs=ps0.executeQuery();
            if(rs.next()){
                System.out.println("user already exists");
                return false;
            }
            PreparedStatement ps=connection.prepareStatement("insert into users(name,email,password,role,phone) values(?,?,?,?,?)");
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            String password=user.getPassword();
            String hashed=PasswordUtil.hashPassword(password);
            ps.setString(3,hashed);
            ps.setString(4,user.getRole().toString());
            ps.setString(5,user.getPhone());

            int rows=ps.executeUpdate();
            if(rows>0){
                logger.info("user inserted successufully");
                return true;
            }
        }
        catch(SQLException e){

            e.printStackTrace();
        }
        return false;
    }








}
