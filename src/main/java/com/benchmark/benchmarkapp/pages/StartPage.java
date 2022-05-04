package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartPage {
    @FXML
    private Button Next;
    @FXML
    private Button Exit;

    public StartPage() {
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void pressNext1() throws IOException {
        Main m = new Main();
        m.changeScene("Page2.fxml");
    }
}
