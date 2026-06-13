package com.fara7.bmi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BMIApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                BMIApplication.class.getResource("bmi-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 500, 640);
        stage.setTitle("BMI Calculator");
        stage.setScene(scene);

        BMIController controller = fxmlLoader.getController();
        controller.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}