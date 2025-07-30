//package edu.buet.shayan.sortingsimulator.ui;
//
//import javafx.geometry.Insets;
//import javafx.scene.control.*;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//
//public class ControlPanel extends VBox {
//
//    private ComboBox<String> algorithmSelector;
//    private Spinner<Integer> arraySizeSpinner;
//    private Button runButton, pauseButton, stopButton, compareButton;
//    private Slider speedSlider;
//    private Label statusLabel, timeLabel;
//
//    public ControlPanel() {
//        super(15);
//        setPadding(new Insets(10));
//        setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");
//
//        createComponents();
//        layoutComponents();
//    }
//
//    private void createComponents() {
//        // Algorithm selection
//        algorithmSelector = new ComboBox<>();
//        algorithmSelector.getItems().addAll(
//                "Selection Sort", "Bubble Sort", "Insertion Sort",
//                "Merge Sort", "Quick Sort"
//        );
//        algorithmSelector.setValue("Selection Sort");
//        algorithmSelector.setPrefWidth(200);
//
//        // Array size control
//        arraySizeSpinner = new Spinner<>(2, 50, 5);
//        arraySizeSpinner.setPrefWidth(100);
//
//        // Control buttons
//        runButton = new Button("Run");
//        runButton.setPrefWidth(80);
//        runButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
//
//        pauseButton = new Button("Pause");
//        pauseButton.setPrefWidth(80);
//        pauseButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
//        pauseButton.setDisable(true);
//
//        stopButton = new Button("Stop");
//        stopButton.setPrefWidth(80);
//        stopButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
//        stopButton.setDisable(true);
//
//        // Speed control
//        speedSlider = new Slider(1, 10, 5);
//        speedSlider.setShowTickLabels(true);
//        speedSlider.setShowTickMarks(true);
//        speedSlider.setMajorTickUnit(1);
//
//        // Compare button
//        compareButton = new Button("Compare Algorithms");
//        compareButton.setPrefWidth(200);
//        compareButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
//
//        // Status labels
//        statusLabel = new Label("Ready");
//        statusLabel.setFont(Font.font(12));
//        timeLabel = new Label("Time: 0ms");
//        timeLabel.setFont(Font.font(12));
//    }
//
//    private void layoutComponents() {
//        Label algoLabel = new Label("Select Algorithm:");
//        algoLabel.setFont(Font.font(14));
//
//        Label sizeLabel = new Label("Array Size:");
//        sizeLabel.setFont(Font.font(14));
//
//        HBox buttonRow = new HBox(5, runButton, pauseButton, stopButton);
//
//        Label speedLabel = new Label("Speed:");
//        speedLabel.setFont(Font.font(14));
//
//        getChildren().addAll(
//                algoLabel, algorithmSelector,
//                new Separator(),
//                sizeLabel, arraySizeSpinner,
//                new Separator(),
//                buttonRow,
//                new Separator(),
//                speedLabel, speedSlider,
//                new Separator(),
//                compareButton,
//                new Separator(),
//                statusLabel, timeLabel
//        );
//    }
//
//    // Getters for components
//    public ComboBox<String> getAlgorithmSelector() { return algorithmSelector; }
//    public Spinner<Integer> getArraySizeSpinner() { return arraySizeSpinner; }
//    public Button getRunButton() { return runButton; }
//    public Button getPauseButton() { return pauseButton; }
//    public Button getStopButton() { return stopButton; }
//    public Button getCompareButton() { return compareButton; }
//    public Slider getSpeedSlider() { return speedSlider; }
//    public Label getStatusLabel() { return statusLabel; }
//    public Label getTimeLabel() { return timeLabel; }
//}



package edu.buet.shayan.algomania.sorting.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlPanel extends VBox {

    private ComboBox<String> algorithmSelector;
    private Spinner<Integer> arraySizeSpinner;
    private Button runButton, pauseButton, stopButton, compareButton, clearButton; // Added clearButton
    private Slider speedSlider;
    private Label statusLabel, timeLabel;

    public ControlPanel() {
        super(15);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 5;");

        createComponents();
        layoutComponents();
    }

    private void createComponents() {
        // Algorithm selection
        algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll(
                "Selection Sort", "Bubble Sort", "Insertion Sort",
                "Merge Sort", "Quick Sort"
        );
        algorithmSelector.setValue("Selection Sort");
        algorithmSelector.setPrefWidth(200);

        // Array size control
        arraySizeSpinner = new Spinner<>(2, 50, 5);
        arraySizeSpinner.setPrefWidth(100);

        // Control buttons
        runButton = new Button("Run");
        runButton.setPrefWidth(80);
        runButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        pauseButton = new Button("Pause");
        pauseButton.setPrefWidth(80);
        pauseButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        pauseButton.setDisable(true);

        stopButton = new Button("Stop");
        stopButton.setPrefWidth(80);
        stopButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
        stopButton.setDisable(true);

        // NEW: Clear button
        clearButton = new Button("Clear");
        clearButton.setPrefWidth(80);
        clearButton.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white;");

        // Speed control
        speedSlider = new Slider(1, 10, 5);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);

        // Compare button
        compareButton = new Button("Compare Algorithms");
        compareButton.setPrefWidth(200);
        compareButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        // Status labels
        statusLabel = new Label("Ready");
        statusLabel.setFont(Font.font(12));
        timeLabel = new Label("Time: 0ms");
        timeLabel.setFont(Font.font(12));
    }

    private void layoutComponents() {
        Label algoLabel = new Label("Select Algorithm:");
        algoLabel.setFont(Font.font(14));

        Label sizeLabel = new Label("Array Size:");
        sizeLabel.setFont(Font.font(14));

        // Updated button rows to include clear button
        HBox buttonRow1 = new HBox(5, runButton, pauseButton, stopButton);
        HBox buttonRow2 = new HBox(5, clearButton);

        Label speedLabel = new Label("Speed:");
        speedLabel.setFont(Font.font(14));

        getChildren().addAll(
                algoLabel, algorithmSelector,
                new Separator(),
                sizeLabel, arraySizeSpinner,
                new Separator(),
                buttonRow1,
                buttonRow2, // Added second button row
                new Separator(),
                speedLabel, speedSlider,
                new Separator(),
                compareButton,
                new Separator(),
                statusLabel, timeLabel
        );
    }

    // Getters for components
    public ComboBox<String> getAlgorithmSelector() { return algorithmSelector; }
    public Spinner<Integer> getArraySizeSpinner() { return arraySizeSpinner; }
    public Button getRunButton() { return runButton; }
    public Button getPauseButton() { return pauseButton; }
    public Button getStopButton() { return stopButton; }
    public Button getClearButton() { return clearButton; } // Added getter for clear button
    public Button getCompareButton() { return compareButton; }
    public Slider getSpeedSlider() { return speedSlider; }
    public Label getStatusLabel() { return statusLabel; }
    public Label getTimeLabel() { return timeLabel; }
}