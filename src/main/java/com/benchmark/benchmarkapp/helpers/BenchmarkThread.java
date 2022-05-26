package com.benchmark.benchmarkapp.helpers;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;
import com.benchmark.benchmarkapp.pages.LoadingPageController;
import javafx.application.Platform;

import java.awt.image.BufferedImage;

public class BenchmarkThread extends Thread {
    double startTime;
    double totalTime;
    boolean startedBench = false;

    final DataHolder instance;
    final Resolution res;

    public BenchmarkThread() {
        instance = DataHolder.getInstance();
        res = instance.getResolution();
    }

    public void initBench() {
        System.out.printf("Bench INIT with: %s, (%s), %s%n", res,
                instance.getFiltersString(), instance.getImageCount());
        startedBench = false;
        totalTime = 0;
    }

    public void continueBench() {
        if (!startedBench) {
            startedBench = true;
            startTime = System.nanoTime(); // Continued at the end of the function
        } else {
            System.out.println("Bench already started!");
        }
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

    public void endBench() {
        startedBench = false;
        int score = getScore(totalTime);
        System.out.printf("Bench DONE in %.3f seconds with score: %s%n",
                totalTime / 1000000000, score);

        DataHolder.getInstance().setTime(totalTime);
        DataHolder.getInstance().setScore(score);
    }

    public int getScore(double time) {
        return (int) (instance.getImageCount()
                      * instance.getFilterCount()
                      / time
                      * res.pixelCount()
                      * 1000);
    }

    public void run() {
        // Just some init of variables
        int imageCount = instance.getImageCount();
        LoadingPageController lpcInstance = LoadingPageController.getInstance();

        // Initialize the bench with the starting values
        Platform.runLater(() -> lpcInstance.setBenchmarkingLabelCount(0, imageCount)); // Update label
        initBench();
        for (int i = 0; i < imageCount; i++) {
            // First generate a random image outside the timer
            BufferedImage randomImage = RandomImage.getRandomImage(res.width(), res.height());

            // Then continue with the bench
            continueBench();

            // Use the filters inside the bench
            instance.useFilters(randomImage);

            // And pause the bench, so that the generation of the random image doesn't affect the benchmark
            pauseBench();

            // Update label
            int finalI = i;
            Platform.runLater(() -> lpcInstance.setBenchmarkingLabelCount(finalI + 1, imageCount));
        }
        endBench();

        // Go to the results page
        Main m = new Main();
        m.changeScene("Page3.fxml");
    }
}
