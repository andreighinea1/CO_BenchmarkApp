package com.benchmark.benchmarkapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Page3  {
    @FXML
    private Button back;
    @FXML
    private Button exit;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;

    public Page3() {
    }

    public void exitApplication(){
        javafx.application.Platform.exit();
    }

    public void backToStartpage() throws IOException {
        Main m= new Main();
        m.changeScene("StartPage.fxml");
    }
}
