package hellofx;

import java.sql.*;

public class UpdateData {
    public static void updateData(Connection myConn, String field, String newValue, int empid) {
        String sqlcommand = "";
        switch (field.toLowerCase()) {
            case "first name":
                sqlcommand = "UPDATE employees SET Fname = '" + newValue + "' WHERE empid = " + empid + ";";
                break;
            case "last name":
                sqlcommand = "UPDATE employees SET Lname = '" + newValue + "' WHERE empid = " + empid + ";";
                break;
            case "email":
                sqlcommand = "UPDATE employees SET email = '" + newValue + "' WHERE empid = " + empid + ";";
                break;
            case "salary":
                // Assuming newValue is a valid integer
                sqlcommand = "UPDATE employees SET Salary = " + Integer.parseInt(newValue) + " WHERE empid = " + empid
                        + ";";
                break;
            case "address line 1":
                sqlcommand = "UPDATE address SET addressLine1 = '" + newValue + "' WHERE empid = " + empid + ";";
                break;
            case "address line 2":
                sqlcommand = "UPDATE address SET addressLine2 = '" + newValue + "' WHERE empid = " + empid + ";";
                break;
            default:
                System.out.println("Invalid field name. Please choose from First name, Last name, Email, or Salary.");
                return;
        }

        try (Statement myStmt = myConn.createStatement()){
            myStmt.executeUpdate(sqlcommand);
            System.out.println("Update successful.");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

