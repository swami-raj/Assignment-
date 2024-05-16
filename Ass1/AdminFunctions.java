package Ass1;
import java.sql.*;

public class AdminFunctions {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/assig";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Kumar@1234";

    public static boolean createJobOpening(String title, String description, String companyName, int postedByUserId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isJobCreated = false;

        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO Job (title, description, company_name, posted_by) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, companyName);
            stmt.setInt(4, postedByUserId);

            // Execute the statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isJobCreated = true;
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
        return isJobCreated;
    }

    public static void main(String[] args) {
        String title = "Software Engineer";
        String description = "Looking for experienced Java developers";
        String companyName = "XYZ Corporation";
        int postedByUserId = 1;

        boolean isJobCreated = createJobOpening(title, description, companyName, postedByUserId);

        if (isJobCreated) {
            System.out.println("Job opening created successfully!");
        } else {
            System.out.println("Job creation failed.");
        }
    }
}

