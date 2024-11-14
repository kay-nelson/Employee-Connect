package hellofx;
import java.sql.*;

public class PayByDivision {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employeeData";
        String user = "root";
        String password = "Gr@duate22";

        try (Connection myConn = DriverManager.getConnection(url, user, password)) {
            String result = getPayByDivision(myConn);
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static String getPayByDivision(Connection myConn) {
        StringBuilder output = new StringBuilder("");
        String sqlcommand = "SELECT d.Name AS Division, " +
                "p.pay_date AS Pay_Date, " +
                "SUM(p.earnings) AS Total_Earnings " +
                "FROM payroll p " +
                "JOIN employees e ON p.empid = e.empid " +
                "JOIN employee_division ed ON e.empid = ed.empid " +
                "JOIN division d ON ed.div_ID = d.ID " +
                "GROUP BY d.Name, p.pay_date " +
                "ORDER BY d.Name, p.pay_date";

        try {
            Statement myStmt = myConn.createStatement();

            output.append("\nDIVISION\tPAY DATE\tTOTAL PAY\n");
            ResultSet myRS = myStmt.executeQuery(sqlcommand);
            while (myRS.next()) {
                String division = myRS.getString("Division");
                Date payDate = myRS.getDate("Pay_Date");
                double totalEarnings = myRS.getDouble("Total_Earnings");

                output.append(division).append("\t")
                        .append("\t").append(payDate).append("\t")
                        .append(totalEarnings).append("\n");
            }
        } catch (SQLException e) {
            output.append("ERROR " + e.getLocalizedMessage());
        }
        return output.toString();
    }
}

