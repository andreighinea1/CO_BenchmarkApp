package com.benchmark.benchmarkapp.helpers;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;
import com.benchmark.benchmarkapp.pages.LoadingPageController;
import javafx.application.Platform;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BenchmarkThread extends Thread {
    private double startTime;
    private double totalTime;
    private boolean startedBench = false;

    private final DataHolder instance;
    private final Resolution res;

    public BenchmarkThread() {
        instance = DataHolder.getInstance();
        res = instance.getResolution();
    }

    public void initBench() {
        System.out.println("Bench INIT");
        startedBench = false;
        totalTime = 0;
    }

    public void pauseBench() {
        double t = System.nanoTime(); // Paused at the start of the function
        if (startedBench) {
            totalTime += t - startTime;
            startedBench = false;
        } else {
            System.out.println("Bench not started!");
        }
    }

    public void continueBench() {
        if (!startedBench) {
            startedBench = true;
            startTime = System.nanoTime(); // Continued at the end of the function
        } else {
            System.out.println("Bench already started!");
        }
    }

    public void endBench() {
        if (startedBench) {
            startedBench = false;
            System.out.println("Bench DONE");
        } else {
            System.out.println("Bench not started");
        }
    }

    public int getScore(double time) {
        return (int) ((instance.getImageCount() + 5 * instance.getFilterCount()) / time * res.pixelCount() * 1000000);
    }

    public void run() {
        int imageCount = instance.getImageCount();
        LoadingPageController lpcInstance = LoadingPageController.getInstance();

        // Initialize the bench with the starting values
        Platform.runLater(() -> lpcInstance.setBenchmarkingLabelCount(0, imageCount));
        initBench();
        for (int i = 0; i < imageCount; i++) {
            // First generate a random image outside the timer
            BufferedImage randomImage = RandomImage.getRandomImage(res.width(), res.height());
            int finalI = i;
            Platform.runLater(() -> lpcInstance.setBenchmarkingLabelCount(finalI + 1, imageCount));

            // Then continue with the bench
            continueBench();

            // Use the filters inside the bench
            instance.useFilters(randomImage);

            // And pause the bench, so that the generation of the random image doesn't affect the image
            pauseBench();
        }
        endBench();

        DataHolder.getInstance().setTime(totalTime);
        DataHolder.getInstance().setScore(getScore(totalTime));

        Main m = new Main();
        m.changeScene("Page3.fxml");
    }
}
