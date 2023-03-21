package com.example.javafx.fxcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    public Label welcomeText1;
    @FXML
    public Label welcomeText2;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText2.setText("Welcome to JavaFX Application!");
    }
}