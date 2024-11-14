package hellofx;
import java.sql.*;

public class PaymentReport {

    public static String getPayByMonth(Connection myConn) {
        StringBuilder output = new StringBuilder("");
        String sqlcommand = "SELECT e.empid, p.pay_date, p.earnings, p.fed_tax, " +
                "p.fed_med, p.fed_SS, p.state_tax, p.retire_401k, p.health_care " +
                "FROM employees e " +
                "JOIN payroll p ON e.empid = p.empid " +
                "ORDER BY p.pay_date;";
        try (Statement myStmt = myConn.createStatement();
             ResultSet myRS1 = myStmt.executeQuery(sqlcommand)) {

            output.append("\tEMP ID"+ "  "+ "PAY DATE" + "  "+"GROSS"+"  "+"Federal" + "  "+ "FedMed"+ "  "+ "FedSS"+"  "+ "State"+"  "+ "401K" +"  " +"HealthCare\n");
            while (myRS1.next()) {
                output.append("\t").append(myRS1.getString("e.empid")).append("\t");
                output.append(myRS1.getDate("p.pay_date")).append("\t").append(myRS1.getDouble("p.earnings")).append("\t");
                output.append(myRS1.getDouble("p.fed_tax")).append("\t").append(myRS1.getDouble("p.fed_med")).append("\t");
                output.append(myRS1.getDouble("p.fed_SS")).append("\t").append(myRS1.getDouble("p.state_tax")).append("\t");
                output.append(myRS1.getDouble("p.retire_401K")).append("\t").append(myRS1.getDouble("p.health_care")).append("\n");
            }

        } catch (SQLException e) {
            output.append("ERROR ").append(e.getLocalizedMessage());
        }
        return output.toString();
    }
}


