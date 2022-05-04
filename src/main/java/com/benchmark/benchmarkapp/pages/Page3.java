package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;

import com.jhlabs.image.GainFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.ThresholdFilter;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.benchmark.benchmarkapp.image_functions.RandomImage.getRandomImage;


public class Page3 {
    @FXML
    private Button back;
    @FXML
    private Button exit;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;

    public Page3() {
    }

    public void initialize() {
        Resolution res = DataHolder.getInstance().getResolution();
        BufferedImage img = getRandomImage(res.width(), res.height());
        setImage1(SwingFXUtils.toFXImage(img, null));


        BufferedImage newImg = new BufferedImage(res.width(), res.height(), BufferedImage.TYPE_INT_RGB);

//        GrayscaleFilter filter = new GrayscaleFilter();
//        GainFilter filter = new GainFilter();
        ThresholdFilter filter = new ThresholdFilter();

        filter.filter(img, newImg);
        setImage2(SwingFXUtils.toFXImage(newImg, null));
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void backToStartpage() throws IOException {
        Main m = new Main();
        m.changeScene("StartPage.fxml");
    }

    public void setImage1(Image img) {
        imageView1.setImage(img);
    }

    public void setImage2(Image img) {
        imageView2.setImage(img);
    }
}
