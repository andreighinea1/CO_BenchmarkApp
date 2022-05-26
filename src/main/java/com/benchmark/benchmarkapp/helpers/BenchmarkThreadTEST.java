package com.benchmark.benchmarkapp.helpers;

import java.awt.image.BufferedImage;

public class BenchmarkThreadTEST extends BenchmarkThread {
    @Override
    public void run() {
        int imageCount = instance.getImageCount();

        // Initialize the bench with the starting values
        initBench();
        for (int i = 0; i < imageCount; i++) {
            // First generate a random image outside the timer
            BufferedImage randomImage = RandomImage.getRandomImage(res.width(), res.height());

            // Use the filters inside the bench
            instance.useFilters(randomImage, this);
        }
        endBench();
    }
}
