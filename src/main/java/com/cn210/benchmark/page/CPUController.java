package com.cn210.benchmark.page;

import com.cn210.benchmark.Menu;
import com.cn210.benchmark.benchmark.CPUBenchmark;
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

public class CPUController extends Application implements Menu {
    @FXML
    private Button StartCPUBenchmark;
    @FXML
    private VBox CPUProcessing;
    @FXML
    private Label CPUTimeBenchmark;
    @FXML
    private VBox CPUFinished;
    @FXML
    private Label CPUScore;
    @FXML
    private Label CPUTime;

    private int cpu_time = 0;

    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void initialize() {
        CPUProcessing.setManaged(false);
        CPUFinished.setManaged(false);
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
    public void onStartCPUButtonClick() {
        StartCPUBenchmark.setVisible(false);
        StartCPUBenchmark.setManaged(false);

        CPUProcessing.setManaged(true);
        CPUProcessing.setVisible(true);

        startCPUBenchmark();
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
    }

    private void startCPUBenchmark() {
        cpu_time = 0;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    cpu_time++;
                    CPUTimeBenchmark.setText(String.valueOf(cpu_time));
                });
            }
        }, 1000, 1000);

        Thread thread = new Thread(() -> {
            CPUBenchmark cpuBenchmark = new CPUBenchmark();

            long cpu_time = cpuBenchmark.start();

            onFinishCPUBenchmark(cpu_time);

            timer.cancel();
        });
        thread.start();
    }

    private void onFinishCPUBenchmark(long cpu_time_long) {
        CPUProcessing.setVisible(false);
        CPUProcessing.setManaged(false);
        CPUFinished.setVisible(true);
        CPUFinished.setManaged(true);

        long cpu_score = cpu_time_long / 100;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CPU Benchmark");
            alert.setHeaderText(null);
            alert.setContentText("CPU Benchmark is finished.");
            alert.show();

            CPUScore.setText(String.valueOf(cpu_score));
            CPUTime.setText(secondToHumanTime(cpu_time));
        });
    }

    private String secondToHumanTime(int second) {
        int min = (int) Math.floor((double) second / 60);
        int sec = second - (min * 60);

        return min + " minutes " + sec + " seconds";
    }
}
