package com.cn210.benchmark.page;

import com.cn210.benchmark.App;
import com.cn210.benchmark.Menu;
import controller.SceneController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SystemController extends Application implements Menu {
    @FXML
    private Label OSName;
    @FXML
    private ImageView OSNameImage;
    @FXML
    private Label OSArch;
    @FXML
    private Label AVAProcesssors;
    @FXML
    private Label TotalRAM;
    @FXML
    private Label JavaVersion;
    @FXML
    private Label JVMPath;
    @FXML
    private Label JVMMem;

    @Override
    public void start(Stage stage) throws Exception {}

    @FXML
    public void initialize() {
        OSName.setText(System.getProperty("os.name"));
        OSNameImage.setImage(this.OSImage());
        OSArch.setText(System.getProperty("os.arch"));
        AVAProcesssors.setText(Runtime.getRuntime().availableProcessors() + " Cores");
        TotalRAM.setText(TotalRAM() + " GB");
        JavaVersion.setText(System.getProperty("java.version"));
        JVMPath.setText(System.getProperty("java.home"));
        JVMMem.setText(JVMMemory() + " GB");
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

    private Image OSImage() {
        String os_name = System.getProperty("os.name");
        InputStream file;

        if (os_name.contains("Win")) {
            file = App.class.getResourceAsStream("/windows.png");
        } else if (os_name.contains("nix") || os_name.contains("nux") || os_name.contains("aix")) {
            file = App.class.getResourceAsStream("/linux.png");
        } else {
            file = App.class.getResourceAsStream("/unknown.png");
        }

        return new Image(file);
    }

    private String TotalRAM() {
        com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long memorySize = bean.getTotalMemorySize();
        double ram = (double) memorySize / 1073741824;
        return String.format("%.2f", ram);
    }

    private String JVMMemory() {
        double mem = (double) Runtime.getRuntime().maxMemory() / 1073741824;
        return String.format("%.2f", mem);
    }
}
