package com.benchmark.benchmarkapp.pages;

import com.benchmark.benchmarkapp.Main;
import com.benchmark.benchmarkapp.data_passing.DataHolder;
import com.benchmark.benchmarkapp.data_passing.Resolution;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        Image img = getRandomImage(res.width(), res.height());
        setImage1(img);

//        Image newImg = Smoother.smooth(img);
//        setImage2(newImg);
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
