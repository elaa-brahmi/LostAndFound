package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Role;
import model.User;
import java.util.Optional;
import java.util.logging.Logger;
public class UserDao{
    private static Connection connection=null;
    private static final Logger logger =Logger.getLogger(UserDao.class.getName());
    public UserDao() {
    }
    public static void SaveUser(User user) throws SQLException{
       try{
           connection=BDConnection.getConnection();
           PreparedStatement ps=connection.prepareStatement("insert into users(name,email,password,role,phone) values(?,?,?,?,?)");
           ps.setString(1,user.getName());
           ps.setString(2,user.getEmail());
           String password=user.getPassword();
           String hashed=PasswordUtil.hashPassword(password);
           ps.setString(3,hashed);
           ps.setString(4, String.valueOf(Role.USER));
           ps.setString(5,user.getPhone());
           int rows=ps.executeUpdate();
           if(rows>0){
               System.out.println("User Saved");
           }
       }
       catch(SQLException e){
           e.printStackTrace();
       }
    }
    public static Optional<User> authenticate(String email, String password) throws SQLException{
        //todo:return an object of the authenticated user
        try{
            connection = BDConnection.getConnection();
            System.out.println("Connected to the database");
            PreparedStatement ps=connection.prepareStatement("select * from users where email=?");
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                String hashedPassword = rs.getString("password");
                if(PasswordUtil.checkPassword(password,hashedPassword)) {
                    System.out.println("user found");
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setPhone(rs.getString("phone"));
                    user.setRole(Role.valueOf(rs.getString("role")));
                    return Optional.of(user);
                }
            }
            else{
                System.out.println("user not found");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean registerUser(User user) throws SQLException{
        try{
            connection = BDConnection.getConnection();
            System.out.println("Connected to the database");
            PreparedStatement ps1=connection.prepareStatement("select user u from users where phone=?");
            ps1.setString(1,user.getPhone());
            ResultSet rs1=ps1.executeQuery();
            if(rs1.next()){
                System.out.println("user already exists");
                return false;
            }
            PreparedStatement ps0=connection.prepareStatement("select user u from users where email=?");

            ps0.setString(1,user.getEmail());
            ResultSet rs=ps0.executeQuery();
            if(rs.next()){
                System.out.println("user already exists");
                return false;
            }
            SaveUser(user);

        }
        catch(SQLException e){

            e.printStackTrace();
        }
        return true;
    }

    public void updateUser(User user) throws SQLException{
        try{
            connection = BDConnection.getConnection();
            PreparedStatement ps= connection.prepareStatement("update users set name=?,email=?,password=?,role=?,phone=? where id=?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            String password=user.getPassword();
            String hashedPassword=PasswordUtil.hashPassword(password);
            ps.setString(3,hashedPassword);
            ps.setString(4, String.valueOf(Role.USER));
            ps.setString(5,user.getPhone());
            ps.setInt(6,user.getId());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteUser(int id) throws SQLException{
        try{
            connection = BDConnection.getConnection();
            PreparedStatement ps=connection.prepareStatement("delete from users where id=?");
            ps.setInt(1,id);
            ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }








}
