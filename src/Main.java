package hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private Connection myConn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        connectToDatabase(); // Connect to the database
        primaryStage.setTitle("Employee Management System");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Employee Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Home Screen Buttons
        Button reportsBtn = new Button("Reports");
        reportsBtn.setOnAction(e -> showReportsScreen());

        Button searchEmployeesBtn = new Button("Search Employees");
        searchEmployeesBtn.setOnAction(e -> showSearchEmployeesScreen());

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> showUpdateScreen());

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(titleLabel, reportsBtn, searchEmployeesBtn, updateBtn, exitBtn);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //function to connect to database
    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/employeeData";
        String user = "root";
        String password = "Gr@duate22";

        try {
            myConn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    //function to display 3 report options
    private void showReportsScreen() {
        Stage reportsStage = new Stage();
        reportsStage.setTitle("Reports");

        VBox reportsRoot = new VBox(10);
        reportsRoot.setPadding(new Insets(20));

        //displays payment report
        Button paymentReportBtn = new Button("Payment Report");
        paymentReportBtn.setOnAction(e -> displayPaymentReport()); 

        //displays pay by job division
        Button payByJobBtn = new Button("Pay By Job");
        payByJobBtn.setOnAction(e -> {
            String result = PayByJob.getPayByJob(myConn);
            displayResult("Pay By Job", result);
        });

        //displays pay by division
        Button payByDivisionBtn = new Button("Pay By Division");
        payByDivisionBtn.setOnAction(e -> {
            String result = PayByDivision.getPayByDivision(myConn);
            displayResult("Pay By Division", result);
        });

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> reportsStage.close());

        reportsRoot.getChildren().addAll(paymentReportBtn, payByJobBtn, payByDivisionBtn, backBtn);

        Scene reportsScene = new Scene(reportsRoot, 400, 300);
        reportsStage.setScene(reportsScene);
        reportsStage.show();
    }

    //created to adjust window when payment report is displyed
    private void displayPaymentReport() {
        String result = PaymentReport.getPayByMonth(myConn);

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Report");
        alert.setHeaderText(null);
        alert.setContentText(result);

        
        alert.getDialogPane().setMinWidth(800); 
        alert.getDialogPane().setMinHeight(400); 

        
        alert.showAndWait();
    }

    //function created to search employee by name, ssn or empid
    private void showSearchEmployeesScreen() {
        Stage searchStage = new Stage();
        searchStage.setTitle("Search Employees");

        VBox searchRoot = new VBox(10);
        searchRoot.setPadding(new Insets(20));

        HBox nameBox = new HBox(10);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("First Name");
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Last Name");
        Button nameSearchBtn = new Button("Search by Name");
        nameSearchBtn.setOnAction(e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String result = SearchEmployees.searchEmployees(myConn, firstName, lastName, null, 0);
            displayResult("Search Result", result);
        });
        nameBox.getChildren().addAll(firstNameTextField, lastNameTextField, nameSearchBtn);

        HBox ssnBox = new HBox(10);
        TextField ssnTextField = new TextField();
        ssnTextField.setPromptText("SSN");
        Button ssnSearchBtn = new Button("Search by SSN");
        ssnSearchBtn.setOnAction(e -> {
            String ssn = ssnTextField.getText();
            String result = SearchEmployees.searchEmployees(myConn, null, null, ssn, 0);
            displayResult("Search Result", result);
        });
        ssnBox.getChildren().addAll(ssnTextField, ssnSearchBtn);

        HBox empidBox = new HBox(10);
        TextField empidTextField = new TextField();
        empidTextField.setPromptText("Employee ID");
        Button empidSearchBtn = new Button("Search by empid");
        empidSearchBtn.setOnAction(e -> {
            int empid = Integer.parseInt(empidTextField.getText());
            String result = SearchEmployees.searchEmployees(myConn, null, null, null, empid);
            displayResult("Search Result", result);
        });
        empidBox.getChildren().addAll(empidTextField, empidSearchBtn);

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> searchStage.close());

        searchRoot.getChildren().addAll(nameBox, ssnBox, empidBox, backBtn);

        Scene searchScene = new Scene(searchRoot, 400, 300);
        searchStage.setScene(searchScene);
        searchStage.show();
    }

    //created to show option between update data and update salary by percentage
    private void showUpdateScreen() {
        Stage updateStage = new Stage();
        updateStage.setTitle("Update");

        VBox updateRoot = new VBox(10);
        updateRoot.setPadding(new Insets(20));

        Button updateDataBtn = new Button("Update Data");
        updateDataBtn.setOnAction(e -> showUpdateDataScreen());

        Button updateSalaryBtn = new Button("Update Salary");
        updateSalaryBtn.setOnAction(e -> showUpdateSalaryScreen());

        Button backBtn = new Button("Back to Home");
        backBtn.setOnAction(e -> updateStage.close());

        updateRoot.getChildren().addAll(updateDataBtn, updateSalaryBtn, backBtn);

        Scene updateScene = new Scene(updateRoot, 400, 300);
        updateStage.setScene(updateScene);
        updateStage.show();
    }

    //function created to implement update data
    private void showUpdateDataScreen() {
        Stage updateDataStage = new Stage();
        updateDataStage.setTitle("Update Data");

        VBox updateDataRoot = new VBox(10);
        updateDataRoot.setPadding(new Insets(20));

        // UI elements for Update Data
        TextField fieldTextField = new TextField();
        fieldTextField.setPromptText("Field Name");
        TextField newValueTextField = new TextField();
        newValueTextField.setPromptText("New Value");
        TextField empidTextField = new TextField();
        empidTextField.setPromptText("Employee ID");

        Button updateDataBtn = new Button("Update Data");
        updateDataBtn.setOnAction(e -> {
            String field = fieldTextField.getText();
            String newValue = newValueTextField.getText();
            int empid = Integer.parseInt(empidTextField.getText());
            UpdateData.updateData(myConn, field, newValue, empid);
            displayResult("Update Data", "Data updated successfully.");
        });

        Button backBtn = new Button("Back to Update Menu");
        backBtn.setOnAction(e -> updateDataStage.close());

        updateDataRoot.getChildren().addAll(fieldTextField, newValueTextField, empidTextField, updateDataBtn, backBtn);

        Scene updateDataScene = new Scene(updateDataRoot, 400, 300);
        updateDataStage.setScene(updateDataScene);
        updateDataStage.show();
    }

    //function created to implement update salary
    private void showUpdateSalaryScreen() {
        Stage updateSalaryStage = new Stage();
        updateSalaryStage.setTitle("Update Salary");

        VBox updateSalaryRoot = new VBox(10);
        updateSalaryRoot.setPadding(new Insets(20));

        // UI elements for Update Salary
        TextField percentageTextField = new TextField();
        percentageTextField.setPromptText("Percentage");
        TextField minSalaryTextField = new TextField();
        minSalaryTextField.setPromptText("Minimum Salary");
        TextField maxSalaryTextField = new TextField();
        maxSalaryTextField.setPromptText("Maximum Salary");

        Button updateSalaryBtn = new Button("Update Salary");
        updateSalaryBtn.setOnAction(e -> {
            double percentage = Double.parseDouble(percentageTextField.getText());
            int minSalary = Integer.parseInt(minSalaryTextField.getText());
            int maxSalary = Integer.parseInt(maxSalaryTextField.getText());
            UpdateSalary.updateSalaryByPercentage(myConn, percentage, minSalary, maxSalary);
            displayResult("Update Salary", "Salary updated successfully.");
        });

        Button backBtn = new Button("Back to Update Menu");
        backBtn.setOnAction(e -> updateSalaryStage.close());

        updateSalaryRoot.getChildren().addAll(percentageTextField, minSalaryTextField, maxSalaryTextField, updateSalaryBtn, backBtn);

        Scene updateSalaryScene = new Scene(updateSalaryRoot, 400, 300);
        updateSalaryStage.setScene(updateSalaryScene);
        updateSalaryStage.show();
    }

    //used to create a new alert box with intended message displayed
    private void displayResult(String title, String result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        if (myConn != null && !myConn.isClosed()) {
            myConn.close();
        }
    }

    //run the main function in javafx and connect the launch.json
    public static void main(String[] args) {
        launch(args);
    }
}
