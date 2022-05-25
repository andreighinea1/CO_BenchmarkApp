package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.jhlabs.image.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filters {

    List<String> firstFile;

    @FXML
    private Button back;
    @FXML
    private Button tobenchmark;
    @FXML
    private Button grayscaleButton;
    @FXML
    private Button thresholdButton;
    @FXML
    private Button exposureButton;
    @FXML
    private Button invertButton;
    @FXML
    private Button gainButton;
    @FXML
    private Button contrasButtont;
    @FXML
    private Button rescaleButton;
    @FXML
    private Button solarizeButton;
    @FXML
    private ImageView afterImageView;

    @FXML
    private ImageView beforeImageView;

    public Filters() throws IOException {
    }


    public void grayscale(ActionEvent event) throws IOException {
        PointFilter filter=new GrayscaleFilter();
        setFilterImage(filter);

    }


    private void setFilterImage(PointFilter filter) throws IOException {
        BufferedImage bi = ImageIO.read(new File("ImaginePtFiltre1.jpeg"));

        filter.filter(bi,bi);

        afterImageView.setImage(SwingFXUtils.toFXImage(bi, null));
        afterImageView.setVisible(true);
    }

    public void contrast(ActionEvent event) throws IOException {
        PointFilter filter=new ContrastFilter();
        setFilterImage(filter);
    }

    public void rescale(ActionEvent event) throws IOException {
        PointFilter filter=new RescaleFilter();
        setFilterImage(filter);
    }

    public void solarize(ActionEvent event) throws IOException {
        PointFilter filter=new SolarizeFilter();
        setFilterImage(filter);
    }

    public void gain(ActionEvent event) throws IOException {
        PointFilter filter=new GainFilter();
        setFilterImage(filter);
    }

    public void invert(ActionEvent event) throws IOException {
        PointFilter filter=new InvertFilter();
        setFilterImage(filter);
    }

    public void exposure(ActionEvent event) throws IOException {
        PointFilter filter=new ExposureFilter();
        setFilterImage(filter);
    }

    public void threshold(ActionEvent event) throws IOException {
        PointFilter filter=new ThresholdFilter();
        setFilterImage(filter);
    }

    @FXML
    public void backToStartPage() {
        Main m = new Main();
        m.changeScene("StartPage.fxml");
    }

    @FXML
    public void toBenchmark() {
        Main m = new Main();
        m.changeScene("Page2.fxml");
    }
}
