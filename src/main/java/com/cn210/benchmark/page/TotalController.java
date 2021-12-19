package com.cn210.benchmark.page;

import com.cn210.benchmark.Menu;
import com.cn210.benchmark.benchmark.CPUBenchmark;
import com.cn210.benchmark.benchmark.DiskBenchmark;
import com.cn210.benchmark.benchmark.MemoryBenchmark;
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

public class TotalController extends Application implements Menu {
    @FXML
    private Button StartTotalBenchmark;
    @FXML
    private VBox TotalProcessing;
    @FXML
    private Label TotalTimeBenchmark;
    @FXML
    private VBox TotalFinished;
    @FXML
    private Label TotalScore;
    @FXML
    private Label TotalTime;

    private int total_time = 0;

    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void initialize() {
        TotalProcessing.setManaged(false);
        TotalFinished.setManaged(false);
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
    public void onStartTotalButtonClick() {
        StartTotalBenchmark.setVisible(false);
        StartTotalBenchmark.setManaged(false);

        TotalProcessing.setManaged(true);
        TotalProcessing.setVisible(true);

        startTotalBenchmark();
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
    }

    private void startTotalBenchmark() {
        total_time = 0;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    total_time++;
                    TotalTimeBenchmark.setText(String.valueOf(total_time));
                });
            }
        }, 1000, 1000);

        Thread thread = new Thread(() -> {
            CPUBenchmark cpuBenchmark = new CPUBenchmark();
            MemoryBenchmark memoryBenchmark = new MemoryBenchmark();
            DiskBenchmark diskBenchmark = new DiskBenchmark();

            long total_time = cpuBenchmark.start() + memoryBenchmark.start() + diskBenchmark.start();

            onFinishTotalBenchmark(total_time);

            timer.cancel();
        });
        thread.start();
    }

    private void onFinishTotalBenchmark(long total_time_long) {
        TotalProcessing.setVisible(false);
        TotalProcessing.setManaged(false);
        TotalFinished.setVisible(true);
        TotalFinished.setManaged(true);

        long total_score = total_time_long / 100;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Total Benchmark");
            alert.setHeaderText(null);
            alert.setContentText("Total Benchmark is finished.");
            alert.show();

            TotalScore.setText(String.valueOf(total_score));
            TotalTime.setText(secondToHumanTime(total_time));
        });
    }

    private String secondToHumanTime(int second) {
        int min = (int) Math.floor((double) second / 60);
        int sec = second - (min * 60);

        return min + " minutes " + sec + " seconds";
    }
}
