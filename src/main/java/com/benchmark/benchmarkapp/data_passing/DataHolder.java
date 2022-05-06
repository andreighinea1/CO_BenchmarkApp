package com.benchmark.benchmarkapp.data_passing;


import com.jhlabs.image.*;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class DataHolder {
    private Resolution resolution;
    private ArrayList<PointFilter> filters = new ArrayList<>();
    private BufferedImage uploadedImage;

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
    }

    public BufferedImage getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(BufferedImage uploadedImage) {
        this.uploadedImage = uploadedImage;
    }
}
