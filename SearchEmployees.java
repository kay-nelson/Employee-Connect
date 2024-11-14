package hellofx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SearchEmployees {

    public static String searchEmployees(Connection myConn, String firstName, String lastName, String ssn, int empid) {
        StringBuilder output = new StringBuilder("");

        String sqlcommand = "";
        if (empid != 0) {
            sqlcommand = "SELECT e.Fname, e.Lname, e.email, jt.job_title, e.empid, " +
                    "a.addressLine1, a.addressLine2, c.city, s.state, " +
                    "a.gender, a.pronouns, a.race, a.DOB, a.phonenumber, e.salary " +
                    "FROM employees e " +
                    "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                    "JOIN address a ON e.empid = a.empid " +
                    "JOIN city c ON a.city_ID = c.city_ID " +
                    "JOIN state s ON a.state_ID = s.state_ID " +
                    "WHERE e.empid = ?";
        } else if (firstName != null && lastName != null) {
            sqlcommand = "SELECT e.Fname, e.Lname, e.email, jt.job_title, e.empid, " +
                    "a.addressLine1, a.addressLine2, c.city, s.state, " +
                    "a.gender, a.pronouns, a.race, a.DOB, a.phonenumber, e.salary " +
                    "FROM employees e " +
                    "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                    "JOIN address a ON e.empid = a.empid " +
                    "JOIN city c ON a.city_ID = c.city_ID " +
                    "JOIN state s ON a.state_ID = s.state_ID " +
                    "WHERE e.Fname = ? AND e.Lname = ?";
        } else if (ssn != null) {
            sqlcommand = "SELECT e.Fname, e.Lname, e.email, jt.job_title, e.empid, " +
                    "a.addressLine1, a.addressLine2, c.city, s.state, " +
                    "a.gender, a.pronouns, a.race, a.DOB, a.phonenumber, e.salary " +
                    "FROM employees e " +
                    "JOIN employee_job_titles ejt ON e.empid = ejt.empid " +
                    "JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id " +
                    "JOIN address a ON e.empid = a.empid " +
                    "JOIN city c ON a.city_ID = c.city_ID " +
                    "JOIN state s ON a.state_ID = s.state_ID " +
                    "WHERE e.ssn = ?";
        }

        try (PreparedStatement myStmt = myConn.prepareStatement(sqlcommand)) {

            if (empid != 0) {
                myStmt.setInt(1, empid);
            } else if (firstName != null && lastName != null) {
                myStmt.setString(1, firstName);
                myStmt.setString(2, lastName);
            } else if (ssn != null) {
                myStmt.setString(1, ssn);
            }

            ResultSet myRS = myStmt.executeQuery();
            while (myRS.next()) {
                output.append("Name: ").append(myRS.getString("Fname")).append(" ").append(myRS.getString("Lname")).append("\n");
                output.append("Title: ").append(myRS.getString("job_title")).append("\n");
                output.append("Email: ").append(myRS.getString("email")).append("\n");
                output.append("Address: ").append(myRS.getString("addressLine1")).append(", ").append(myRS.getString("addressLine2"))
                        .append(", ").append(myRS.getString("city")).append(", ").append(myRS.getString("state")).append("\n");
                output.append("Gender: ").append(myRS.getString("gender")).append("\n");
                output.append("Pronouns: ").append(myRS.getString("pronouns")).append("\n");
                output.append("Race: ").append(myRS.getString("race")).append("\n");
                output.append("Date Of Birth: ").append(myRS.getDate("DOB")).append("\n");
                output.append("Phone Number: ").append(myRS.getString("phonenumber")).append("\n");
                output.append("Salary: ").append(myRS.getString("Salary")).append("\n\n");
            }
        } catch (SQLException e) {
            output.append("ERROR: " + e.getMessage());
        }
        return output.toString();
    }
}
