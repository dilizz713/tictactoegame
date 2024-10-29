module com.assignment.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.assignment.tictactoe.controller to javafx.fxml;
    exports com.assignment.tictactoe;
}