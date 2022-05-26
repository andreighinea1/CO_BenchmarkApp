package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.jhlabs.image.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page2Controller {
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

    @FXML
    private TextField numberField;

    @FXML
    private Label wrongInput;

    public Page2Controller() {
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

        boolean validResolution=false;
        boolean validFilter=false;
        boolean validSize=false;

        if (resolution.getValue() == null) {
            wrongInput.setText("Please select a resolution!");
        }
        else {
            validResolution=true;
            instance.setResolution(resolution.getValue());
        }

        if (grayscaleFilter.isSelected()) {
            instance.addFilter(new GrayscaleFilter());
            validFilter=true;
        }
        if (gainFilter.isSelected()) {
            instance.addFilter(new GainFilter());
            validFilter=true;
        }
        if (thresholdFilter.isSelected()) {
            instance.addFilter(new ThresholdFilter());
            validFilter=true;
        }
        if (contrastFilter.isSelected()) {
            instance.addFilter(new ContrastFilter());
            validFilter=true;
        }
        if (exposureFilter.isSelected()) {
            instance.addFilter(new ExposureFilter());
            validFilter=true;
        }
        if (rescaleFilter.isSelected()) {
            instance.addFilter(new RescaleFilter());
            validFilter=true;
        }
        if (invertFilter.isSelected()) {
            instance.addFilter(new InvertFilter());
            validFilter=true;
        }
        if (solarizeFilter.isSelected()) {
            instance.addFilter(new SolarizeFilter());
            validFilter=true;
        }

        if(validFilter==false){
            wrongInput.setText("Please select at least a filter!");
        }

        // handle the number of images tested

        if(numberField.getText().isEmpty()){
            wrongInput.setText("Please give a number at Step4!");
        }
        else {
            int numberOfImages;

            try {
                numberOfImages=Integer.parseInt(numberField.getText());
                if(numberOfImages<50 || numberOfImages>1000){
                    wrongInput.setText("Please give a number between 50 and 1000 at Step4!");
                }
                else{
                    validSize=true;
                    instance.setImageCount(numberOfImages);
                }
            } catch (NumberFormatException e) {
                wrongInput.setText("Please give a number at Step4, not text!");
            }
        }

        if(validFilter && validSize && validResolution) {
            Main m = new Main();
            m.changeScene("LoadingPage.fxml");
        }
    }
}
