package Ass1;
import java.sql.*;

public class ViewJob {
    // JDBC URL, username, and password for database connection
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/assig";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Kumar@1234";

    public static void viewJobOpenings() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM Job";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int jobId = rs.getInt("job_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String companyName = rs.getString("company_name");
                System.out.println("Job ID: " + jobId + ", Title: " + title + ", Description: " + description + ", Company: " + companyName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        viewJobOpenings();
    }
}

