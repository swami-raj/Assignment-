package Ass1;
import java.sql.*;

public class Login {
    // JDBC URL, username, and password for database connection
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assig";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Kumar@1234";

    public static boolean authenticateUser(String email, String password) {
        boolean isAuthenticated = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Prepare SQL statement to fetch user data
            String sql = "SELECT user_id FROM User WHERE email = ? AND password_hash = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password); // In real applications, hash the password before comparing

            // Execute the statement
            rs = stmt.executeQuery();

            // Check if a user with matching credentials exists
            if (rs.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }

    public static void main(String[] args) {
        String email = "john@example.com";
        String password = "securepassword";

        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Invalid email or password.");
        }
    }
}
