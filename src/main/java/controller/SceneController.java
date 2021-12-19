package controller;

import com.cn210.benchmark.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage main;

    public static void setStage(Stage stage) {
        SceneController.main = stage;
    }

    public static Stage getStage() {
        return SceneController.main;
    }

    public static void setScene(String resource, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("" + resource));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        getStage().setScene(scene);
    }
}
