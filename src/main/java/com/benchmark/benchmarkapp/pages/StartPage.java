package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartPage {
    @FXML
    private Button Next;
    @FXML
    private Button Exit;
    @FXML
    private Button StartDemo;

    public StartPage() {
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
