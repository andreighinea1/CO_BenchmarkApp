package com.benchmark.benchmarkapp.data_passing;


import com.jhlabs.image.*;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class DataHolder {
    private Resolution resolution;
    private ArrayList<PointFilter> filters = new ArrayList<>();
    private BufferedImage uploadedImage;
    private int currentProgress;
    private int score;
    private int imageCount;
    private double time;

    private final static DataHolder INSTANCE = new DataHolder();

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public void setResolution(String resolutionStr) {
        if (resolutionStr == null)
            return;
        String[] parts = resolutionStr.split("x");
        resolution = new Resolution(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public Resolution getResolution() {
        return resolution;
    }


    public int getFilterCount() {
        return filters.size();
    }

    public void addFilter(PointFilter filter) {
        filters.add(filter);
    }

    public BufferedImage useFilters(BufferedImage img) {
        for (var filter : filters)
            filter.filter(img, img);
        return img;
    }


    public void resetData() {
        filters = new ArrayList<>();
        resolution = null;
        uploadedImage = null;
        time = 0;
    }

    public BufferedImage getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(BufferedImage uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }
}
