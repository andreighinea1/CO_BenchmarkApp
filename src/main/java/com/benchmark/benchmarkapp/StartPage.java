package com.benchmark.benchmarkapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartPage {
    @FXML
    private Button Next;
    @FXML
    private Button Exit;

    public StartPage(){
    }

    public void exitApplication(){
        System.out.println("Exit button pressed");
    }
    public void pressNext1() throws IOException {
        Main m= new Main();
        m.changeScene("Page2.fxml");
    }
}
