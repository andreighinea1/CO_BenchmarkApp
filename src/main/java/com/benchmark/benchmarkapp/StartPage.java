package com.benchmark.benchmarkapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPage {
    @FXML
    private Button Next;
    @FXML
    private Button Exit;

    public StartPage(){
    }

    public void exitApplication(){
        javafx.application.Platform.exit();
    }
    public void pressNext1() throws IOException {
        Main m= new Main();
        m.changeScene("Page2.fxml");
    }
}
