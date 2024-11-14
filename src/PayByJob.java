package hellofx;
import java.sql.*;

public class PayByJob {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employeeData";
        String user = "root";
        String password = "Gr@duate22";

        
        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            String result = getPayByJob(myConn);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public static String getPayByJob(Connection myConn) {
       
        StringBuilder output = new StringBuilder("");
        String sqlcommand = "SELECT jt.job_title AS Job_Title, " +
                "p.pay_date AS Pay_Date, " +
                "SUM(p.earnings) AS Total_Earnings " +
                "FROM payroll p " +
                "JOIN employees e ON p.empid = e.empid " +
                "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                "GROUP BY jt.job_title, p.pay_date " +
                "ORDER BY jt.job_title, p.pay_date";

        try (Connection conn = myConn;
             Statement myStmt = conn.createStatement()) {

            output.append("\tJOB TITLE\t\tPAY DATE\tTOTAL PAY\n");
            ResultSet myRS = myStmt.executeQuery(sqlcommand);
            while (myRS.next()) {
                String jobTitle = myRS.getString("Job_Title");
                Date payDate = myRS.getDate("Pay_Date");
                double totalEarnings = myRS.getDouble("Total_Earnings");

                output.append("\t").append(jobTitle).append("\t")
                        .append(payDate).append("\t")
                        .append(totalEarnings).append("\n");
            }

        } catch (SQLException e) {
            output.append("ERROR ").append(e.getLocalizedMessage());
        }
        return output.toString();
    }
}

