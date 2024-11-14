package hellofx;

import java.sql.*;

public class UpdateSalary {
    public static void updateSalaryByPercentage(Connection myConn, double percentage, int minSalary, int maxSalary) {
        String sqlcommand = "UPDATE employees SET salary = salary * (1 + " + (percentage / 100) + ") WHERE salary BETWEEN " + minSalary + " AND " + maxSalary + ";";
        try {
            Statement myStmt = myConn.createStatement();
            myStmt.executeUpdate(sqlcommand);
            System.out.println("Salaries updated successfully.");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
        }
    }
}

