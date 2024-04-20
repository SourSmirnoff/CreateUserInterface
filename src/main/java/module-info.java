module com.example.createuserinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.createuserinterface to javafx.fxml;
    exports com.example.createuserinterface;
}