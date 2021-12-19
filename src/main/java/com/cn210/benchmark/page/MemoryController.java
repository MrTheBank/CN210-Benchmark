package com.cn210.benchmark.page;

import com.cn210.benchmark.Menu;
import com.cn210.benchmark.benchmark.CPUBenchmark;
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

public class MemoryController extends Application implements Menu {
    @FXML
    private Button StartMemoryBenchmark;
    @FXML
    private VBox MemoryProcessing;
    @FXML
    private Label MemoryTimeBenchmark;
    @FXML
    private VBox MemoryFinished;
    @FXML
    private Label MemoryScore;
    @FXML
    private Label MemoryTime;

    private int memory_time = 0;
    
    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void initialize() {
        MemoryProcessing.setManaged(false);
        MemoryFinished.setManaged(false);
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
    public void onStartMemoryButtonClick() {
        StartMemoryBenchmark.setVisible(false);
        StartMemoryBenchmark.setManaged(false);

        MemoryProcessing.setManaged(true);
        MemoryProcessing.setVisible(true);

        startMemoryBenchmark();
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
    }

    private void startMemoryBenchmark() {
        memory_time = 0;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    memory_time++;
                    MemoryTimeBenchmark.setText(String.valueOf(memory_time));
                });
            }
        }, 1000, 1000);

        Thread thread = new Thread(() -> {
            MemoryBenchmark memoryBenchmark = new MemoryBenchmark();

            long memory_time = memoryBenchmark.start();

            onFinishMemoryBenchmark(memory_time);

            timer.cancel();
        });
        thread.start();
    }

    private void onFinishMemoryBenchmark(long memory_time_long) {
        MemoryProcessing.setVisible(false);
        MemoryProcessing.setManaged(false);
        MemoryFinished.setVisible(true);
        MemoryFinished.setManaged(true);

        long memory_score = memory_time_long / 100;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Memory Benchmark");
            alert.setHeaderText(null);
            alert.setContentText("Memory Benchmark is finished.");
            alert.show();

            MemoryScore.setText(String.valueOf(memory_score));
            MemoryTime.setText(secondToHumanTime(memory_time));
        });
    }

    private String secondToHumanTime(int second) {
        int min = (int) Math.floor((double) second / 60);
        int sec = second - (min * 60);

        return min + " minutes " + sec + " seconds";
    }
}
