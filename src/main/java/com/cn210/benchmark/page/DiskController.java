package com.cn210.benchmark.page;

import com.cn210.benchmark.Menu;
import com.cn210.benchmark.benchmark.DiskBenchmark;
import controller.SceneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DiskController extends Application implements Menu {
    @FXML
    private Button StartDiskBenchmark;
    @FXML
    private VBox DiskProcessing;
    @FXML
    private Label DiskTimeBenchmark;
    @FXML
    private VBox DiskFinished;
    @FXML
    private Label DiskScore;
    @FXML
    private Label DiskTime;

    private int disk_time = 0;

    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void initialize() {
        DiskProcessing.setManaged(false);
        DiskFinished.setManaged(false);
    }

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
    public void onStartDiskButtonClick() {
        StartDiskBenchmark.setVisible(false);
        StartDiskBenchmark.setManaged(false);

        DiskProcessing.setManaged(true);
        DiskProcessing.setVisible(true);

        startDiskBenchmark();
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
    }

    private void startDiskBenchmark() {
        disk_time = 0;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    disk_time++;
                    DiskTimeBenchmark.setText(String.valueOf(disk_time));
                });
            }
        }, 1000, 1000);

        Thread thread = new Thread(() -> {
            DiskBenchmark diskBenchmark = new DiskBenchmark();

            long disk_time = diskBenchmark.start();

            onFinishDiskBenchmark(disk_time);

            timer.cancel();
        });
        thread.start();
    }

    private void onFinishDiskBenchmark(long disk_time_long) {
        DiskProcessing.setVisible(false);
        DiskProcessing.setManaged(false);
        DiskFinished.setVisible(true);
        DiskFinished.setManaged(true);

        long disk_score = disk_time_long / 100;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Disk Benchmark");
            alert.setHeaderText(null);
            alert.setContentText("Disk Benchmark is finished.");
            alert.show();

            DiskScore.setText(String.valueOf(disk_score));
            DiskTime.setText(secondToHumanTime(disk_time));
        });
    }

    private String secondToHumanTime(int second) {
        int min = (int) Math.floor((double) second / 60);
        int sec = second - (min * 60);

        return min + " minutes " + sec + " seconds";
    }
}
