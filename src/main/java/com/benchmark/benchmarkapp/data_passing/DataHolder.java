package com.benchmark.benchmarkapp.data_passing;


public final class DataHolder {
    private Resolution resolution;
    private final static DataHolder INSTANCE = new DataHolder();

    private DataHolder() {}

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public void setResolution(String resolutionStr) {
        if(resolutionStr == null)
            return;
        String[] parts = resolutionStr.split("x");
        resolution = new Resolution(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public Resolution getResolution() {
        return resolution;
    }
}
