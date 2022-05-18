package com.benchmark.benchmarkapp.data_passing;

import java.util.Objects;

public record Resolution(int width, int height) {
    public int pixelCount() {
        return width * height;
    }
}