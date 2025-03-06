package services;

import model.Item;
import model.ItemStatus;
import model.ItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemDao {
    public static Connection con;

    private static final Logger logger =Logger.getLogger(UserDao.class.getName());
    public ItemDao() {}
    public static Item getItem(int id) {
        try {
            con = BDConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from item where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Item i = new Item();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
                i.setDescription(rs.getString("description"));
                i.setCategory(rs.getString("category"));
                i.setLocation(rs.getString("location"));
                i.setStatus(ItemStatus.valueOf(rs.getString("status")));
                i.setType(ItemType.valueOf(rs.getString("type")));
                i.setDatefound(rs.getDate("datefound").toLocalDate());
                System.out.println(i);
                return i;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void updateItem(Item item) {
        try{
            con = BDConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE item  SET name = ?, description = ?, category = ?, location = ?, image = ?, datefound = ?, type = ?, userid = ?, status = ? WHERE id = ?");
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getLocation());
            ps.setString(5, item.getImage());
            ps.setString(6, item.getDatefound().toString());
            ps.setString(7, item.getType().toString());
            ps.setInt(8, item.getUserId());
            ps.setString(9, item.getStatus().toString());
            ps.setInt(10, item.getId());
            int rows=ps.executeUpdate();
            if(rows>0) {
                System.out.println("Item updated successfully");

            }


        }
        catch(SQLException e ){
            e.printStackTrace();
        }
    }
    public static void deleteItem(int id) {
        try{
            con = BDConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from item WHERE id = ?");
            ps.setInt(1, id);
            int rows=ps.executeUpdate();
            if(rows>0) {
                System.out.println("Item deleted successfully");
            }

    }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Item> getLostItems() {
        try{
            con = BDConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from item WHERE type=='LOST'");
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<Item>();
            while(rs.next()) {
                Item i = new Item();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
                i.setDescription(rs.getString("description"));
                i.setCategory(rs.getString("category"));
                i.setLocation(rs.getString("location"));
                i.setStatus(ItemStatus.valueOf(rs.getString("status")));
                i.setType(ItemType.valueOf(rs.getString("type")));
                i.setDatefound(rs.getDate("datefound").toLocalDate());
                i.setUserId(rs.getInt("userid"));
                System.out.println(i);
                items.add(i);

            }
            return items;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Item> getFoundItems() {
        try{
            con = BDConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from item WHERE type=='FOUND'");
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<Item>();
            while(rs.next()) {
                Item i = new Item();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
                i.setDescription(rs.getString("description"));
                i.setCategory(rs.getString("category"));
                i.setLocation(rs.getString("location"));
                i.setStatus(ItemStatus.valueOf(rs.getString("status")));
                i.setType(ItemType.valueOf(rs.getString("type")));
                i.setDatefound(rs.getDate("datefound").toLocalDate());
                i.setUserId(rs.getInt("userid"));
                System.out.println(i);
                items.add(i);

            }
            return items;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getUserEmail(Item lostItem) {

    }
}
