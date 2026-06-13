package com.fara7.bmi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BMIController implements Initializable {

    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private TextField ageField;
    @FXML private TextField lengthField;
    @FXML private TextField weightField;
    @FXML private ComboBox<String> activityCombo;
    @FXML private Label bmiLabel;
    @FXML private Label caloriesLabel;
    @FXML private CheckBox displayImageCheck;
    @FXML private ImageView bodyImage;

    private ToggleGroup genderGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        activityCombo.getItems().addAll(
                "Sedentary",
                "Lightly_Active",
                "Moderately_Active",
                "Mustard_Active",
                "Radio_Active"
        );
        activityCombo.setValue("Lightly_Active");
    }

    public void setScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setHeaderText("Are you sure you want to exit?");
                alert.setContentText("Press Yes to close the application.");

                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().setAll(yes, no);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    System.exit(0);
                }
            }
        });
    }

    @FXML
    private void handleCalc() {
        String ageText    = ageField.getText().trim();
        String lengthText = lengthField.getText().trim();
        String weightText = weightField.getText().trim();
        String activity   = activityCombo.getValue();

        if (ageText.isEmpty() || lengthText.isEmpty() || weightText.isEmpty() || activity == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill in all fields before calculating.");
            alert.showAndWait();
            return;
        }

        try {
            double age    = Double.parseDouble(ageText);
            double height = Double.parseDouble(lengthText);
            double weight = Double.parseDouble(weightText);

            double heightM = height / 100.0;
            double bmi = weight / (heightM * heightM);

            String status;
            if      (bmi < 18.5) status = "Underweight";
            else if (bmi < 25.0) status = "Normal Weight";
            else if (bmi < 30.0) status = "Over Weight";
            else if (bmi < 35.0) status = "Obesity Class I";
            else if (bmi < 40.0) status = "Obesity Class II";
            else                 status = "Obesity Class III";

            double bmr;
            if (maleRadio.isSelected()) {
                bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
            } else {
                bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            }

            double tdee;
            switch (activity) {
                case "Sedentary":          tdee = bmr * 1.2;   break;
                case "Lightly_Active":     tdee = bmr * 1.375; break;
                case "Moderately_Active":  tdee = bmr * 1.55;  break;
                case "Mustard_Active":     tdee = bmr * 1.725; break;
                case "Radio_Active":       tdee = bmr * 1.9;   break;
                default:                   tdee = bmr * 1.375;
            }

            bmiLabel.setText(String.format("%.2f  %s", bmi, status));
            caloriesLabel.setText(String.format("%.2f  Calories", tdee));

            if (displayImageCheck.isSelected()) {
                showBmiImage(status);
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Number");
            alert.setContentText("Please enter valid numeric values.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNew() {
        ageField.clear();
        lengthField.clear();
        weightField.clear();
        activityCombo.setValue("Lightly_Active");
        maleRadio.setSelected(true);
        bmiLabel.setText("");
        caloriesLabel.setText("");
        displayImageCheck.setSelected(false);
        bodyImage.setVisible(false);
    }

    @FXML
    private void handleCheckBox() {
        if (displayImageCheck.isSelected() && !bmiLabel.getText().isEmpty()) {
            showBmiImage(bmiLabel.getText());
        } else {
            bodyImage.setVisible(false);
        }
    }

    private void showBmiImage(String status) {
        String imageName;
        if      (status.contains("Underweight")) imageName = "underweight.png";
        else if (status.contains("Normal"))      imageName = "normal.png";
        else if (status.contains("Over"))        imageName = "overweight.png";
        else                                     imageName = "obese.png";

        try {
            URL imgUrl = getClass().getResource("/com/fara7/bmi/" + imageName);
            if (imgUrl != null) {
                bodyImage.setImage(new Image(imgUrl.toExternalForm()));
                bodyImage.setVisible(true);
            }
        } catch (Exception e) {
            bodyImage.setVisible(false);
        }
    }

    @FXML
    private void handleHyperlink() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}