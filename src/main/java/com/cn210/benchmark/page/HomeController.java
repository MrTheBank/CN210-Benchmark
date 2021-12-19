package com.cn210.benchmark.page;

import com.cn210.benchmark.Menu;
import controller.SceneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController extends Application implements Menu {
    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void onHomeButtonClick() throws IOException {
        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
    }

    @FXML
    public void onSystemButtonClick() throws IOException {
        SceneController.setScene("system.fxml", 600, 400);
        SceneController.getStage().setTitle("System");
    }

    @FXML
    public void onAboutButtonClick() throws IOException {
        SceneController.setScene("about.fxml", 600, 400);
        SceneController.getStage().setTitle("About");
    }

    @FXML
    public void onSRCButtonClick() {
        getHostServices().showDocument("https://github.com/MrTheBank/CN210-Benchmark");
    }

    @FXML
    public void onCPUButtonClick() throws IOException {
        SceneController.setScene("cpu.fxml", 600, 400);
        SceneController.getStage().setTitle("CPU Benchmark");
    }

    @FXML
    public void onMemoryButtonClick() throws IOException {
        SceneController.setScene("memory.fxml", 600, 400);
        SceneController.getStage().setTitle("Memory Benchmark");
    }

    @FXML
    public void onDiskButtonClick() throws IOException {
        SceneController.setScene("disk.fxml", 600, 400);
        SceneController.getStage().setTitle("Disk Benchmark");
    }

    @FXML
    public void onTotalButtonClick() throws IOException {
        SceneController.setScene("total.fxml", 600, 400);
        SceneController.getStage().setTitle("Total Benchmark");
    }
}
