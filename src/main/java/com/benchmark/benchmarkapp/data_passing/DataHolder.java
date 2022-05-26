package com.benchmark.benchmarkapp.data_passing;


import com.jhlabs.image.*;
import javafx.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public final class DataHolder {
    private Resolution resolution;
    private ArrayList<PointFilter> filters = new ArrayList<>();
    private BufferedImage uploadedImage;
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

    public static PointFilter getProcessedFilter(PointFilter filter) {
        // Added there bc they need to be initialized
        if (filter instanceof ContrastFilter contrastFilter) {
            contrastFilter.setContrast(4);
            contrastFilter.setBrightness(4);
            return contrastFilter;
        }
        if (filter instanceof RescaleFilter rescaleFilter) {
            rescaleFilter.setScale(4);
            return rescaleFilter;
        }
        if (filter instanceof GainFilter gainFilter) {
            gainFilter.setGain(2);
            return gainFilter;
        }

        return filter;
    }

    public void addFilter(PointFilter filter) {
        filters.add(getProcessedFilter(filter));
    }

    public void addAllFilters() {
        addFilter(new GrayscaleFilter());
        addFilter(new GainFilter());
        addFilter(new ThresholdFilter());
        addFilter(new ContrastFilter());
        addFilter(new ExposureFilter());
        addFilter(new RescaleFilter());
        addFilter(new InvertFilter());
        addFilter(new SolarizeFilter());
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

    private String getFilterName(PointFilter filter) {
        if (filter instanceof GrayscaleFilter)
            return "GrayscaleFilter";
        if (filter instanceof GainFilter)
            return "GainFilter";
        if (filter instanceof ThresholdFilter)
            return "ThresholdFilter";
        if (filter instanceof ContrastFilter)
            return "ContrastFilter";
        if (filter instanceof ExposureFilter)
            return "ExposureFilter";
        if (filter instanceof RescaleFilter)
            return "RescaleFilter";
        if (filter instanceof InvertFilter)
            return "InvertFilter";
        if (filter instanceof SolarizeFilter)
            return "SolarizeFilter";
        return "NOT_FOUND";
    }

    public String getFiltersString() {
        StringBuilder res = new StringBuilder();
        for (PointFilter filter : filters)
            res.append(getFilterName(filter)).append(", ");

        return res.substring(0, res.length() - 2);
    }
}
