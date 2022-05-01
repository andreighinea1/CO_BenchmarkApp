package com.benchmark.benchmarkapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page2 implements Initializable {

    public Page2(){
    }

    List<String> firstFile;

    @FXML
    private Button uploadImage;

    @FXML
    private Label labSingleFile;

    @FXML
    private Button back;

    @FXML
    private ChoiceBox<String> resolution;

    private String[] resolutions={"resolution1","resolution2","resolution3","resolution4"};

    @FXML
    private CheckBox filter1;

    @FXML
    private CheckBox filter2;

    @FXML
    private CheckBox filter3;

    @FXML
    private CheckBox filter4;

    @FXML
    private Button start;

    @FXML
    void uploadChoosenImage(ActionEvent event){
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images",firstFile));
        File f= fc.showOpenDialog(null);


        if(f!=null){
            labSingleFile.setText("Selected File:" + f.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstFile= new ArrayList<>();
        firstFile.add("*.jpg");
        firstFile.add("*.png");
        firstFile.add("*.jpeg");
        resolution.getItems().addAll(resolutions);
    }

    public void backToStartPage() throws IOException {
        Main m= new Main();
        m.changeScene("StartPage.fxml");
    }

    public void moveToPage3() throws IOException {
        Main m= new Main();
        m.changeScene("Page3.fxml");
    }
}
