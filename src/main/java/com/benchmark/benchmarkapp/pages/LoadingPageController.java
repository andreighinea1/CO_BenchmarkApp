package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.helpers.BenchmarkThread;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LoadingPageController {
    @FXML
    private Label benchmarkingLabel;
    private static LoadingPageController instance;

    public void initialize() {
        instance = this; // For access of this class in a static way

        // Here run benchmark
        BenchmarkThread benchmarkThread = new BenchmarkThread();
        benchmarkThread.start();
    }

    public void setBenchmarkingLabelCount(int count, int max) {
        benchmarkingLabel.setText(String.format("Benchmarking... %s/%s", count, max));
    }

    public static LoadingPageController getInstance() {
        return instance;
    }
}
