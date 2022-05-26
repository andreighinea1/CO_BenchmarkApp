package com.benchmark.benchmarkapp;

import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.helpers.BenchmarkThread;
import com.benchmark.benchmarkapp.helpers.BenchmarkThreadTEST;
import com.jhlabs.image.*;
import javafx.application.Platform;

public class RunPredefinedTests {
    private static DataHolder instance;

    public static void main(String[] args) {
        instance = DataHolder.getInstance();

        System.out.println("Running updated benchmark - v3");

        startTest1();
        startTest2();
        startTest3();
        startTest4();
        startTest5();
        startTest6();
        startTest7();
        startTest8();
        startTest9();
        startTest10();
        startTest11();
        startTest12();
        startTest13();
        startTest14();
        startTest15();
//        startTest16();
    }

    private static void startTest1(){
        instance.resetData();

        instance.setResolution("512x512");
        instance.addAllFilters();
        instance.setImageCount(50);

        startBench();
    }

    private static void startTest2(){
        instance.resetData();

        instance.setResolution("1024x1024");
        instance.addAllFilters();
        instance.setImageCount(50);

        startBench();
    }

    private static void startTest3(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addAllFilters();
        instance.setImageCount(50);

        startBench();
    }

    private static void startTest4(){
        instance.resetData();

        instance.setResolution("4096x4096");
        instance.addAllFilters();
        instance.setImageCount(50);

        startBench();
    }

    private static void startTest5(){
        instance.resetData();

        instance.setResolution("1024x1024");
        instance.addAllFilters();
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest6(){
        instance.resetData();

        instance.setResolution("1024x1024");
        instance.addAllFilters();
        instance.setImageCount(200);

        startBench();
    }

    private static void startTest7(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new GrayscaleFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest8(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new GainFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest9(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new ThresholdFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest10(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new ContrastFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest11(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new ExposureFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest12(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new RescaleFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest13(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new InvertFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest14(){
        instance.resetData();

        instance.setResolution("2048x2048");
        instance.addFilter(new SolarizeFilter());
        instance.setImageCount(100);

        startBench();
    }

    private static void startTest15(){
        instance.resetData();

        instance.setResolution("512x512");
        instance.addFilter(new InvertFilter());
        instance.setImageCount(100);

        startBench();
    }

//    private static void startTest16(){
//        instance.resetData();
//
//        instance.setResolution("8192x8192");
//        instance.addAllFilters();
//        instance.setImageCount(50);
//
//        startBench();
//    }

    private static void startBench(){
        BenchmarkThreadTEST benchmarkThread = new BenchmarkThreadTEST();
        benchmarkThread.start();

        try {
            benchmarkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
