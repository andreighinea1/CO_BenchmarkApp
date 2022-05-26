package com.benchmark.benchmarkapp.data_passing;


import com.benchmark.benchmarkapp.helpers.BenchmarkThread;
import com.jhlabs.image.*;

import java.awt.image.BufferedImage;
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

    private double getDivisionFactor(PointFilter filter) {
        // These are based on the results from the tests ran
        if (filter instanceof GrayscaleFilter)
            return 1.293;
        if (filter instanceof GainFilter)
            return 1.533;
        if (filter instanceof ThresholdFilter)
            return 5.234;
        if (filter instanceof ContrastFilter)
            return 1.54;
        if (filter instanceof ExposureFilter)
            return 1.528;
        if (filter instanceof RescaleFilter)
            return 1.505;
        if (filter instanceof InvertFilter)
            return 0.98;
        if (filter instanceof SolarizeFilter)
            return 1.525;
        return 1;
    }

    public void useFilters(BufferedImage img, BenchmarkThread instance) {
        for (var filter : filters) {
            BenchmarkThread.setDivisionFactor(getDivisionFactor(filter));

            // Then continue with the bench
            instance.continueBench();

            // And apply the filter
            filter.filter(img, img);

            // And pause the bench, so that the generation of
            // the random image doesn't affect the benchmark
            instance.pauseBench();
        }
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
