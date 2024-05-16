package Ass1;
import java.sql.*;

public class Signup {
    // JDBC URL, username, and password for database connection
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/assig";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Kumar@1234";

    public static boolean registerUser(String name, String email, String password, String userType, String profileHeadline, String address) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isRegistered = false;

        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO User (name, email, password_hash, user_type, profile_headline, address) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, userType);
            stmt.setString(5, profileHeadline);
            stmt.setString(6, address);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRegistered;
    }

    public static void main(String[] args) {
        String name = "Ram";
        String email = "Ram@example.com";
        String password = "secord";
        String userType = "Applicant";
        String profileHeadline = "Java Software Developer";
        String address = "123 Main St, delhi";

        boolean isRegistered = registerUser(name, email, password, userType, profileHeadline, address);

        if (isRegistered) {
            System.out.println("User registration successful!");
        } else {
            System.out.println("User registration failed.");
        }
    }
}
