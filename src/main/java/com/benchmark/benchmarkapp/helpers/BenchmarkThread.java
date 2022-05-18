package com.benchmark.benchmarkapp.helpers;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BenchmarkThread extends Thread {
    private double startTime;
    private double endTime;
    private boolean startedBench = false;

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

    public int getScore(double time, long value) {
        return (int) (value / time * 100000000000L);
    }

    ArrayList<BufferedImage> images = new ArrayList<>();

    public void run() {
        DataHolder instance = DataHolder.getInstance();
        Resolution res = instance.getResolution();
        int imageCount = instance.getImageCount();

        startBench();
        for (int i = 0; i < imageCount; i++) {
            images.add(instance.useFilters(RandomImage.getRandomImage(res.width(), res.height())));
        }
        endBench();

        DataHolder.getInstance().setScore(getScore(endTime, imageCount));

        Main m = new Main();
        m.changeScene("Page3.fxml");
    }
}
