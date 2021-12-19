package com.cn210.benchmark;

import controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneController.setStage(stage);

        SceneController.setScene("home.fxml", 600, 400);
        SceneController.getStage().setTitle("Home");
        SceneController.getStage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}
