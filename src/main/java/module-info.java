module com.benchmark.benchmarkapp {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    // Other
    requires javafx.swing;

    // Filters
    requires com.jhlabs;

    exports com.benchmark.benchmarkapp.image_functions;
    opens com.benchmark.benchmarkapp.image_functions to javafx.fxml;
    exports com.benchmark.benchmarkapp.pages;
    opens com.benchmark.benchmarkapp.pages to javafx.fxml;
    exports com.benchmark.benchmarkapp;
    opens com.benchmark.benchmarkapp to javafx.fxml;
    exports com.benchmark.benchmarkapp.data_passing;
    opens com.benchmark.benchmarkapp.data_passing to javafx.fxml;
}