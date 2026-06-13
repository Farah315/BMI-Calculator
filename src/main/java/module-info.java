module com.fara7.bmi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens com.fara7.bmi to javafx.fxml;
    exports com.fara7.bmi;
}