package edu.buet.shayan.algomania.sorting.ui;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompareWindow {
    private Stage stage;
    private ComboBox<String> algorithm1Selector, algorithm2Selector;
    private Button startCompareButton, resetButton;
    private Label status1Label, status2Label, time1Label, time2Label, resultLabel;
    private Slider speedSlider;

    // Visualization components for algorithm 1
    private VBox barGraph1Container;
    private List<Rectangle> bars1;
    private List<Label> valueLabels1;
    private List<Double> arrayData1;

    // Visualization components for algorithm 2
    private VBox barGraph2Container;
    private List<Rectangle> bars2;
    private List<Label> valueLabels2;
    private List<Double> arrayData2;

    // Original data for reset
    private List<Double> originalData;

    // Animation and timing
    private AnimationTimer animationTimer;
    private Iterator<SortStep> sortSteps1, sortSteps2;
    private boolean isRunning = false;
    private long startTime1, startTime2;
    private long endTime1 = -1, endTime2 = -1;
    private boolean algo1Finished = false, algo2Finished = false;

    public CompareWindow(List<Double> originalData) {
        this.originalData = new ArrayList<>(originalData);
        this.arrayData1 = new ArrayList<>(originalData);
        this.arrayData2 = new ArrayList<>(originalData);
        this.bars1 = new ArrayList<>();
        this.bars2 = new ArrayList<>();
        this.valueLabels1 = new ArrayList<>();
        this.valueLabels2 = new ArrayList<>();

        createWindow();
    }

    private void createWindow() {
        stage = new Stage();
        stage.setTitle("Compare Sorting Algorithms");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        // Control section
        HBox controlSection = createControlSection();

        // Comparison section
        HBox comparisonSection = createComparisonSection();

        // Results section
        HBox resultsSection = createResultsSection();

        mainLayout.getChildren().addAll(controlSection, new Separator(), comparisonSection, resultsSection);

        Scene scene = new Scene(mainLayout, 1200, 600);
        stage.setScene(scene);

        setupEventHandlers();
        updateVisualizations();
    }

    private HBox createControlSection() {
        HBox controlSection = new HBox(20);
        controlSection.setAlignment(Pos.CENTER);
        controlSection.setPadding(new Insets(10));

        // Algorithm selectors
        VBox algo1Box = new VBox(5);
        algo1Box.setAlignment(Pos.CENTER);
        Label algo1Label = new Label("Algorithm 1:");
        algo1Label.setFont(Font.font(14));
        algorithm1Selector = new ComboBox<>();
        algorithm1Selector.getItems().addAll("Selection Sort", "Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort");
        algorithm1Selector.setValue("Selection Sort");
        algo1Box.getChildren().addAll(algo1Label, algorithm1Selector);

        VBox algo2Box = new VBox(5);
        algo2Box.setAlignment(Pos.CENTER);
        Label algo2Label = new Label("Algorithm 2:");
        algo2Label.setFont(Font.font(14));
        algorithm2Selector = new ComboBox<>();
        algorithm2Selector.getItems().addAll("Selection Sort", "Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort");
        algorithm2Selector.setValue("Bubble Sort");
        algo2Box.getChildren().addAll(algo2Label, algorithm2Selector);

        // Control buttons
        VBox controlBox = new VBox(5);
        controlBox.setAlignment(Pos.CENTER);
        startCompareButton = new Button("Start Comparison");
        startCompareButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        startCompareButton.setPrefWidth(150);

        resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        resetButton.setPrefWidth(150);

        controlBox.getChildren().addAll(startCompareButton, resetButton);

        // Speed control
        VBox speedBox = new VBox(5);
        speedBox.setAlignment(Pos.CENTER);
        Label speedLabel = new Label("Speed:");
        speedLabel.setFont(Font.font(14));
        speedSlider = new Slider(1, 10, 5);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setPrefWidth(150);
        speedBox.getChildren().addAll(speedLabel, speedSlider);

        controlSection.getChildren().addAll(algo1Box, algo2Box, controlBox, speedBox);
        return controlSection;
    }

    private HBox createComparisonSection() {
        HBox comparisonSection = new HBox(20);
        comparisonSection.setAlignment(Pos.TOP_CENTER);
        comparisonSection.setPrefHeight(400);

        // Algorithm 1 visualization
        VBox algo1Viz = new VBox(10);
        algo1Viz.setAlignment(Pos.TOP_CENTER);
        algo1Viz.setPrefWidth(650);

        Label algo1Title = new Label("Algorithm 1");
        algo1Title.setFont(Font.font(16));
        algo1Title.setStyle("-fx-font-weight: bold;");

        barGraph1Container = new VBox();
        barGraph1Container.setPrefHeight(350);
        barGraph1Container.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");

        status1Label = new Label("Ready");
        status1Label.setFont(Font.font(12));

        algo1Viz.getChildren().addAll(algo1Title, barGraph1Container, status1Label);

        // Algorithm 2 visualization
        VBox algo2Viz = new VBox(10);
        algo2Viz.setAlignment(Pos.TOP_CENTER);
        algo2Viz.setPrefWidth(650);

        Label algo2Title = new Label("Algorithm 2");
        algo2Title.setFont(Font.font(16));
        algo2Title.setStyle("-fx-font-weight: bold;");

        barGraph2Container = new VBox();
        barGraph2Container.setPrefHeight(350);
        barGraph2Container.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");

        status2Label = new Label("Ready");
        status2Label.setFont(Font.font(12));

        algo2Viz.getChildren().addAll(algo2Title, barGraph2Container, status2Label);

        comparisonSection.getChildren().addAll(algo1Viz, new Separator(), algo2Viz);
        return comparisonSection;
    }

    private HBox createResultsSection() {
        HBox resultsSection = new HBox(50);
        resultsSection.setAlignment(Pos.CENTER);
        resultsSection.setPadding(new Insets(10));

        time1Label = new Label("Algorithm 1 Time: 0ms");
        time1Label.setFont(Font.font(14));

        time2Label = new Label("Algorithm 2 Time: 0ms");
        time2Label.setFont(Font.font(14));

        resultLabel = new Label("Results will appear here");
        resultLabel.setFont(Font.font(14));
        resultLabel.setStyle("-fx-font-weight: bold;");

        resultsSection.getChildren().addAll(time1Label, time2Label, resultLabel);
        return resultsSection;
    }

    private void setupEventHandlers() {
        startCompareButton.setOnAction(e -> startComparison());
        resetButton.setOnAction(e -> resetComparison());
    }

    private void startComparison() {
        if (isRunning) return;

        // Reset data to original state
        arrayData1 = new ArrayList<>(originalData);
        arrayData2 = new ArrayList<>(originalData);

        // Get algorithms
        String algo1Name = algorithm1Selector.getValue();
        String algo2Name = algorithm2Selector.getValue();

        SortingAlgorithm algorithm1 = SortingAlgorithmFactory.createAlgorithm(algo1Name);
        SortingAlgorithm algorithm2 = SortingAlgorithmFactory.createAlgorithm(algo2Name);

        // Generate sort steps
        sortSteps1 = algorithm1.sort(new ArrayList<>(arrayData1)).iterator();
        sortSteps2 = algorithm2.sort(new ArrayList<>(arrayData2)).iterator();

        // Reset states
        isRunning = true;
        algo1Finished = false;
        algo2Finished = false;
        endTime1 = -1;
        endTime2 = -1;
        startTime1 = System.currentTimeMillis();
        startTime2 = System.currentTimeMillis();

        // Update UI
        startCompareButton.setDisable(true);
        status1Label.setText("Running " + algo1Name + "...");
        status2Label.setText("Running " + algo2Name + "...");
        resultLabel.setText("Comparison in progress...");
        time1Label.setText("Algorithm 1 Time: 0ms");
        time2Label.setText("Algorithm 2 Time: 0ms");

        // Reset visualizations to original data
        updateVisualizations();
        resetVisualizationColors();
        startAnimation();
    }

    private void startAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }

        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                double speed = speedSlider.getValue();
                long interval = (long) (1_000_000_000 / speed);

                if (now - lastUpdate >= interval) {
                    boolean step1Available = sortSteps1.hasNext() && !algo1Finished;
                    boolean step2Available = sortSteps2.hasNext() && !algo2Finished;

                    if (step1Available) {
                        SortStep step = sortSteps1.next();
                        visualizeStep(step, 1);
                        if (!sortSteps1.hasNext()) {
                            finishAlgorithm(1);
                        }
                    }

                    if (step2Available) {
                        SortStep step = sortSteps2.next();
                        visualizeStep(step, 2);
                        if (!sortSteps2.hasNext()) {
                            finishAlgorithm(2);
                        }
                    }

                    // Update time labels for running algorithms
                    if (!algo1Finished) {
                        long elapsed1 = System.currentTimeMillis() - startTime1;
                        time1Label.setText("Algorithm 1 Time: " + elapsed1 + "ms");
                    }
                    if (!algo2Finished) {
                        long elapsed2 = System.currentTimeMillis() - startTime2;
                        time2Label.setText("Algorithm 2 Time: " + elapsed2 + "ms");
                    }

                    if (!step1Available && !step2Available) {
                        finishComparison();
                        stop();
                    }

                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();
    }

    private void visualizeStep(SortStep step, int algorithmNumber) {
        List<Rectangle> bars = (algorithmNumber == 1) ? bars1 : bars2;
        List<Label> valueLabels = (algorithmNumber == 1) ? valueLabels1 : valueLabels2;
        List<Double> arrayData = (algorithmNumber == 1) ? arrayData1 : arrayData2;

        // Reset colors
        for (Rectangle bar : bars) {
            bar.setFill(Color.LIGHTBLUE);
        }

        // Highlight comparing elements
        if (step.getComparing() != null) {
            for (int index : step.getComparing()) {
                if (index >= 0 && index < bars.size()) {
                    bars.get(index).setFill(Color.YELLOW);
                }
            }
        }

        // Highlight swapping elements
        if (step.getSwapping() != null && step.getSwapping().size() == 2) {
            int idx1 = step.getSwapping().get(0);
            int idx2 = step.getSwapping().get(1);
            if (idx1 >= 0 && idx1 < bars.size()) {
                bars.get(idx1).setFill(Color.RED);
            }
            if (idx2 >= 0 && idx2 < bars.size()) {
                bars.get(idx2).setFill(Color.RED);
            }
        }

        // Update array data and visualization
        if (step.getArray() != null) {
            arrayData.clear();
            arrayData.addAll(step.getArray());
            updateVisualization(algorithmNumber);
        }

        // Highlight sorted elements
        if (step.getSorted() != null) {
            for (int index : step.getSorted()) {
                if (index >= 0 && index < bars.size()) {
                    bars.get(index).setFill(Color.GREEN);
                }
            }
        }
    }

    private void updateVisualization(int algorithmNumber) {
        List<Rectangle> bars = (algorithmNumber == 1) ? bars1 : bars2;
        List<Label> valueLabels = (algorithmNumber == 1) ? valueLabels1 : valueLabels2;
        List<Double> arrayData = (algorithmNumber == 1) ? arrayData1 : arrayData2;

        if (arrayData.size() != bars.size() || arrayData.size() != valueLabels.size()) return;

        double maxValue = arrayData.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
        double maxHeight = 300;

        for (int i = 0; i < bars.size(); i++) {
            double value = arrayData.get(i);
            double height = (value / maxValue) * maxHeight;
            bars.get(i).setHeight(height);
            valueLabels.get(i).setText(Double.toString(value));
        }
    }

    private void finishAlgorithm(int algorithmNumber) {
        if (algorithmNumber == 1 && !algo1Finished) {
            algo1Finished = true;
            endTime1 = System.currentTimeMillis();
            status1Label.setText("Completed!");
            // Color all bars green
            for (Rectangle bar : bars1) {
                bar.setFill(Color.GREEN);
            }
            time1Label.setText("Algorithm 1 Time: " + (endTime1 - startTime1) + "ms");
        } else if (algorithmNumber == 2 && !algo2Finished) {
            algo2Finished = true;
            endTime2 = System.currentTimeMillis();
            status2Label.setText("Completed!");
            // Color all bars green
            for (Rectangle bar : bars2) {
                bar.setFill(Color.GREEN);
            }
            time2Label.setText("Algorithm 2 Time: " + (endTime2 - startTime2) + "ms");
        }
    }

    private void finishComparison() {
        isRunning = false;
        startCompareButton.setDisable(false);

        // Determine winner
        if (endTime1 != -1 && endTime2 != -1) {
            long time1 = endTime1 - startTime1;
            long time2 = endTime2 - startTime2;

            String algo1Name = algorithm1Selector.getValue();
            String algo2Name = algorithm2Selector.getValue();

            if (time1 < time2) {
                resultLabel.setText("🏆 Winner: " + algo1Name + " (Faster by " + (time2 - time1) + "ms)");
                resultLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
            } else if (time2 < time1) {
                resultLabel.setText("🏆 Winner: " + algo2Name + " (Faster by " + (time1 - time2) + "ms)");
                resultLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
            } else {
                resultLabel.setText("🤝 Tie! Both algorithms took the same time.");
                resultLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");
            }
        }
    }

    private void resetComparison() {
        if (animationTimer != null) {
            animationTimer.stop();
        }

        isRunning = false;
        algo1Finished = false;
        algo2Finished = false;
        endTime1 = -1;
        endTime2 = -1;

        // Reset data to original
        arrayData1 = new ArrayList<>(originalData);
        arrayData2 = new ArrayList<>(originalData);

        startCompareButton.setDisable(false);
        status1Label.setText("Ready");
        status2Label.setText("Ready");
        time1Label.setText("Algorithm 1 Time: 0ms");
        time2Label.setText("Algorithm 2 Time: 0ms");
        resultLabel.setText("Results will appear here");
        resultLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");

        updateVisualizations();
        resetVisualizationColors();
    }

    private void resetVisualizationColors() {
        for (Rectangle bar : bars1) {
            bar.setFill(Color.LIGHTBLUE);
        }
        for (Rectangle bar : bars2) {
            bar.setFill(Color.LIGHTBLUE);
        }
    }

    private void updateVisualizations() {
        updateVisualizationGraph(barGraph1Container, arrayData1, bars1, valueLabels1);
        updateVisualizationGraph(barGraph2Container, arrayData2, bars2, valueLabels2);
    }

    private void updateVisualizationGraph(VBox container, List<Double> data, List<Rectangle> bars, List<Label> valueLabels) {
        container.getChildren().clear();
        bars.clear();
        valueLabels.clear();

        if (data.isEmpty()) return;

        double maxValue = data.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
        double maxHeight = 300;
        double barWidth = Math.min(40, 500.0 / data.size());

        HBox barsContainer = new HBox(2);
        barsContainer.setAlignment(Pos.BOTTOM_CENTER);
        barsContainer.setPadding(new Insets(20));

        for (int i = 0; i < data.size(); i++) {
            VBox barContainer = new VBox();
            barContainer.setAlignment(Pos.BOTTOM_CENTER);

            double value = data.get(i);
            double height = (value / maxValue) * maxHeight;

            Rectangle bar = new Rectangle(barWidth, height);
            bar.setFill(Color.LIGHTBLUE);
            bar.setStroke(Color.DARKBLUE);
            bar.setStrokeWidth(1);

            Label valueLabel = new Label(Double.toString(value));
            valueLabel.setFont(Font.font(10));

            barContainer.getChildren().addAll(valueLabel, bar);
            barsContainer.getChildren().add(barContainer);
            bars.add(bar);
            valueLabels.add(valueLabel);
        }

        container.getChildren().add(barsContainer);
    }

    public void show() {
        stage.show();
    }
}