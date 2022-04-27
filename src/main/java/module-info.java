module com.benchmark.benchmarkapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.benchmark.benchmarkapp to javafx.fxml;
    exports com.benchmark.benchmarkapp;
}