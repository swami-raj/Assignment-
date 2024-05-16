package Ass1;
import java.sql.*;

public class ApplyJob {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/Assig";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Kumar@1234";

    public static boolean applyToJob(int jobId, int applicantId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isApplied = false;

        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO JobApplication (job_id, applicant_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, jobId);
            stmt.setInt(2, applicantId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isApplied = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isApplied;
    }

    public static void main(String[] args) {
        int jobIdToApply = 1;
        int applicantId = 1;

        boolean isApplied = applyToJob(jobIdToApply, applicantId);

        if (isApplied) {
            System.out.println("Application submitted successfully!");
        } else {
            System.out.println("Application submission failed.");
        }
    }
}

