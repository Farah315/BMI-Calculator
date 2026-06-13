# BMI Calculator — JavaFX Desktop Application

## Overview

A desktop application built with JavaFX that calculates Body Mass Index (BMI) and Basal Metabolic Rate (BMR) based on user input. The application provides a clean, modern UI with real-time results and visual feedback.

## Features

- BMI calculation with weight status classification
- BMR and daily calorie needs (TDEE) calculation based on activity level
- Gender-specific BMR formulas (Mifflin-St Jeor equation)
- Visual body image display based on BMI result
- Gradient dark blue and gold UI theme
- Confirmation dialog on ESCAPE key press
- Input validation with descriptive error messages
- New/Reset functionality to clear all fields

## Technologies

- Java 17
- JavaFX 17
- FXML for UI layout
- Maven for dependency management
- IntelliJ IDEA

## Project Structure

```
src/
  main/
    java/
      com/fara7/bmi/
        BMIApplication.java
        BMIController.java
        Launcher.java
    resources/
      com/fara7/bmi/
        bmi-view.fxml
        underweight.png
        normal.png
        overweight.png
        obese.png
```

## BMI Formula

```
BMI = weight (kg) / height (m)^2
```

| BMI Range   | Weight Status     |
|-------------|-------------------|
| Below 18.5  | Underweight       |
| 18.5 - 24.9 | Normal Weight     |
| 25.0 - 29.9 | Over Weight       |
| 30.0 - 34.9 | Obesity Class I   |
| 35.0 - 39.9 | Obesity Class II  |
| Above 40    | Obesity Class III |

## BMR Formula

**Male:**
```
BMR = 88.362 + (13.397 x weight) + (4.799 x height) - (5.677 x age)
```

**Female:**
```
BMR = 447.593 + (9.247 x weight) + (3.098 x height) - (4.330 x age)
```

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Ensure JavaFX SDK is configured in the project settings
4. Run `Launcher.java` or `BMIApplication.java`

## Academic Context

Faculty of Computing and Information Technology
Al-Aqsa University, Gaza
Visual Programming Course
<img width="503" height="646" alt="image" src="https://github.com/user-attachments/assets/d73d7c8f-f566-4246-be2d-b8104f542ec0" />
<img width="483" height="657" alt="image" src="https://github.com/user-attachments/assets/7a3b3c5a-6eca-42cd-a526-f658dcf621c0" />
