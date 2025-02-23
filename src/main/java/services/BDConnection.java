package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BDConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/LostAndFound";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "0000";
    private static final Logger logger =Logger.getLogger(BDConnection.class.getName()); ;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.info("PostgreSQL Driver not found");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
