package com.benchmark.benchmarkapp.helpers;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;
import javafx.scene.chart.PieChart;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BenchmarkThread extends Thread {
    private double startTime;
    private double endTime;
    private boolean startedBench = false;

    private final DataHolder instance;
    private final Resolution res;

    public BenchmarkThread() {
        instance = DataHolder.getInstance();
        res = instance.getResolution();
    }

    public void startBench() {
        System.out.println("Bench STARTED");
        startedBench = true;
        startTime = System.nanoTime();
    }

    public void endBench() {
        double t = System.nanoTime();
        if (startedBench) {
            endTime = t - startTime;
            startedBench = false;
            System.out.println("Bench DONE");
        } else {
            System.out.println("Bench not started");
        }
    }

    public int getScore(double time) {
        return (int) ((instance.getImageCount() + 5 * instance.getFilterCount()) / time * res.pixelCount() * 1000000);
    }

    ArrayList<BufferedImage> images = new ArrayList<>();

    public void run() {
        int imageCount = instance.getImageCount();

        startBench();
        for (int i = 0; i < imageCount; i++) {
            images.add(instance.useFilters(RandomImage.getRandomImage(res.width(), res.height())));
        }
        endBench();

        DataHolder.getInstance().setTime(endTime);
        DataHolder.getInstance().setScore(getScore(endTime));

        Main m = new Main();
        m.changeScene("Page3.fxml");
    }
}
