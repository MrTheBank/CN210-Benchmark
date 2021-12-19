module com.cn210.benchmark {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.management;


    opens com.cn210.benchmark to javafx.fxml;
    exports com.cn210.benchmark;
    exports com.cn210.benchmark.page;
    opens com.cn210.benchmark.page to javafx.fxml;
}