package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartPageController {
    @FXML
    private Button nextButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button startDemoButton;

    public StartPageController() {
    }

    public void initialize() {
        DataHolder.getInstance().resetData();
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void pressNext1() {
        Main m = new Main();
        m.changeScene("Page2.fxml");
    }

    @FXML
    public void startDemo() {
        Main m = new Main();
        m.changeScene("Filters.fxml");
    }

}
