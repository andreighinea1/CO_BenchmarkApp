package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.jhlabs.image.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page2 {
    private DataHolder instance;

    List<String> firstFile;

    @FXML
    private Button uploadImage;

    @FXML
    private Label labSingleFile;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> resolution;

    private String[] resolutions = {"512x512", "1024x1024", "2048x2048", "4096x4096", "8192x8192"};

    @FXML
    private CheckBox grayscaleFilter;

    @FXML
    private CheckBox gainFilter;

    @FXML
    private CheckBox thresholdFilter;

    @FXML
    private CheckBox contrastFilter;

    @FXML
    private CheckBox exposureFilter;

    @FXML
    private CheckBox rescaleFilter;

    @FXML
    private CheckBox invertFilter;

    @FXML
    private CheckBox solarizeFilter;

    @FXML
    private Button start;

    public Page2() {
    }

    public void initialize() {
        instance = DataHolder.getInstance();

        firstFile = new ArrayList<>();
        firstFile.add("*.jpg");
        firstFile.add("*.png");
        firstFile.add("*.jpeg");
        resolution.getItems().addAll(resolutions);
    }

    @FXML
    void uploadChoosenImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", firstFile));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            labSingleFile.setText("Selected File:" + f.getAbsolutePath());

            try {
                instance.setUploadedImage(ImageIO.read(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void backToStartPage() {
        Main m = new Main();
        m.changeScene("StartPage.fxml");
    }

    public void moveToPage3() {
        if (resolution.getValue() == null)
            return;

        instance.setResolution(resolution.getValue());
        if (grayscaleFilter.isSelected())
            instance.addFilter(new GrayscaleFilter());
        if (gainFilter.isSelected())
            instance.addFilter(new GainFilter());
        if (thresholdFilter.isSelected())
            instance.addFilter(new ThresholdFilter());
        if (contrastFilter.isSelected())
            instance.addFilter(new ContrastFilter());
        if (exposureFilter.isSelected())
            instance.addFilter(new ExposureFilter());
        if (rescaleFilter.isSelected())
            instance.addFilter(new RescaleFilter());
        if (invertFilter.isSelected())
            instance.addFilter(new InvertFilter());
        if (solarizeFilter.isSelected())
            instance.addFilter(new SolarizeFilter());

        instance.setImageCount(100); // Here place something inputted from the GUI instead of the number

        Main m = new Main();
        m.changeScene("LoadingPage.fxml");
    }
}
