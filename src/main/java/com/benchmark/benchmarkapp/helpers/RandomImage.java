package com.benchmark.benchmarkapp.helpers;

import java.awt.image.BufferedImage;

public class RandomImage {
    public static BufferedImage getRandomImage(int width, int height) {
        // Create buffered image object
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // create random values pixel by pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // generating values less than 256
                int a = (int) (Math.random() * 256);
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);

                //pixel
                int p = (a << 24) | (r << 16) | (g << 8) | b;

                img.setRGB(x, y, p);
            }
        }

        // write image
//        try {
//            File f = new File("D:/test.png");
//            ImageIO.write(img, "png", f);
//        } catch (IOException e) {
//            System.out.println("Error: " + e);
//        }

        return img;
    }
}
