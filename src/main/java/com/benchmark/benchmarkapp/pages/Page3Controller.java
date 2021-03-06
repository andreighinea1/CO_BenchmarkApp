package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.image.BufferedImage;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Page3Controller {
    private DataHolder instance;

    @FXML
    private Label scoreText;
    @FXML
    private Button back;
    @FXML
    private Button exit;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;

    @FXML
    private Label timeAndSize;

    public Page3Controller() {
    }

    public void initialize() {
        instance = DataHolder.getInstance();

        scoreText.setText("The score is: " + instance.getScore());
        timeAndSize.setText(instance.getImageCount() + " images were processed in " + instance.getTime() / 1000000000 + " seconds");

        BufferedImage img = instance.getUploadedImage();


        if (img != null) {
            setImage1(SwingFXUtils.toFXImage(img, null));
            setImage2(SwingFXUtils.toFXImage(instance.useFilters(img), null));
        }
    }

    public void exitApplication() {
        javafx.application.Platform.exit();
    }

    public void backToStartPage() {
        Main m = new Main();
        m.changeScene("StartPage.fxml");
    }

    private void setImage1(Image img) {
        imageView1.setImage(img);
    }

    private void setImage2(Image img) {
        imageView2.setImage(img);
    }
}
