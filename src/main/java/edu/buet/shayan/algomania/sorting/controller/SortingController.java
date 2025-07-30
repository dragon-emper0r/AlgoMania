//package edu.buet.shayan.sortingsimulator.controller;
//
//import edu.buet.shayan.sortingsimulator.ui.ControlPanel;
//import edu.buet.shayan.sortingsimulator.algorithm.SortingAlgorithm;
//import edu.buet.shayan.sortingsimulator.algorithm.SortingAlgorithmFactory;
//import edu.buet.shayan.sortingsimulator.model.SortStep;
//import edu.buet.shayan.sortingsimulator.ui.VisualizationPanel;
//import javafx.animation.AnimationTimer;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class SortingController {
//
//    private ControlPanel controlPanel;
//    private VisualizationPanel visualizationPanel;
//    private AnimationTimer animationTimer;
//    private SortingAlgorithm currentAlgorithm;
//    private Iterator<SortStep> sortSteps;
//    private boolean isRunning = false;
//    private boolean isPaused = false;
//    private long startTime;
//
//    public SortingController(ControlPanel controlPanel, VisualizationPanel visualizationPanel) {
//        this.controlPanel = controlPanel;
//        this.visualizationPanel = visualizationPanel;
//
//        setupEventHandlers();
//    }
//
//    private void setupEventHandlers() {
//        // Array size change handler
//        controlPanel.getArraySizeSpinner().valueProperty().addListener((obs, oldVal, newVal) -> {
//            visualizationPanel.resizeArray(newVal);
//        });
//
//        // Control button handlers
//        controlPanel.getRunButton().setOnAction(e -> startSorting());
//        controlPanel.getPauseButton().setOnAction(e -> pauseResumeSort());
//        controlPanel.getStopButton().setOnAction(e -> stopSorting());
//        controlPanel.getCompareButton().setOnAction(e -> toggleCompareMode());
//    }
//
//    private void startSorting() {
//        if (isRunning && isPaused) {
//            isPaused = false;
//            controlPanel.getPauseButton().setText("Pause");
//            controlPanel.getStatusLabel().setText("Running...");
//            return;
//        }
//
//        String selectedAlgo = controlPanel.getAlgorithmSelector().getValue();
//        currentAlgorithm = SortingAlgorithmFactory.createAlgorithm(selectedAlgo);
//
//        List<Double> dataCopy = new ArrayList<>(visualizationPanel.getArrayData());
//        sortSteps = currentAlgorithm.sort(dataCopy).iterator();
//
//        isRunning = true;
//        isPaused = false;
//        startTime = System.currentTimeMillis();
//
//        controlPanel.getRunButton().setDisable(true);
//        controlPanel.getPauseButton().setDisable(false);
//        controlPanel.getStopButton().setDisable(false);
//        controlPanel.getStatusLabel().setText("Running...");
//
//        visualizationPanel.resetBarColors();
//        startAnimation();
//    }
//
//    private void startAnimation() {
//        if (animationTimer != null) {
//            animationTimer.stop();
//        }
//
//        animationTimer = new AnimationTimer() {
//            private long lastUpdate = 0;
//
//            @Override
//            public void handle(long now) {
//                if (isPaused) return;
//
//                double speed = controlPanel.getSpeedSlider().getValue();
//                long interval = (long) (1_000_000_000 / speed);
//
//                if (now - lastUpdate >= interval) {
//                    if (sortSteps.hasNext()) {
//                        SortStep step = sortSteps.next();
//                        visualizeStep(step);
//                        lastUpdate = now;
//
//                        long elapsed = System.currentTimeMillis() - startTime;
//                        controlPanel.getTimeLabel().setText("Time: " + elapsed + "ms");
//                    } else {
//                        finishSorting();
//                        stop();
//                    }
//                }
//            }
//        };
//        animationTimer.start();
//    }
//
//    private void visualizeStep(SortStep step) {
//        List<Rectangle> bars = visualizationPanel.getBars();
//
//        // Reset all bars to default color
//        for (Rectangle bar : bars) {
//            bar.setFill(Color.LIGHTBLUE);
//        }
//
//        // Highlight comparing elements
//        if (step.getComparing() != null) {
//            for (int index : step.getComparing()) {
//                if (index < bars.size()) {
//                    bars.get(index).setFill(Color.YELLOW);
//                }
//            }
//        }
//
//        // Highlight swapping elements
//        if (step.getSwapping() != null && step.getSwapping().size() == 2) {
//            bars.get(step.getSwapping().get(0)).setFill(Color.RED);
//            bars.get(step.getSwapping().get(1)).setFill(Color.RED);
//        }
//
//        // Update array data and bar heights
//        if (step.getArray() != null) {
//            visualizationPanel.getArrayData().clear();
//            visualizationPanel.getArrayData().addAll(step.getArray());
//            visualizationPanel.updateBarHeights();
//        }
//
//        // Highlight sorted elements
//        if (step.getSorted() != null) {
//            for (int index : step.getSorted()) {
//                if (index < bars.size()) {
//                    bars.get(index).setFill(Color.GREEN);
//                }
//            }
//        }
//    }
//
//    private void pauseResumeSort() {
//        if (isRunning) {
//            isPaused = !isPaused;
//            controlPanel.getPauseButton().setText(isPaused ? "Resume" : "Pause");
//            controlPanel.getStatusLabel().setText(isPaused ? "Paused" : "Running...");
//        }
//    }
//
//    private void stopSorting() {
//        if (animationTimer != null) {
//            animationTimer.stop();
//        }
//
//        isRunning = false;
//        isPaused = false;
//
//        controlPanel.getRunButton().setDisable(false);
//        controlPanel.getPauseButton().setDisable(true);
//        controlPanel.getPauseButton().setText("Pause");
//        controlPanel.getStopButton().setDisable(true);
//        controlPanel.getStatusLabel().setText("Stopped");
//
//        visualizationPanel.resetBarColors();
//        visualizationPanel.updateBarGraph();
//    }
//
//    private void finishSorting() {
//        isRunning = false;
//        isPaused = false;
//
//        controlPanel.getRunButton().setDisable(false);
//        controlPanel.getPauseButton().setDisable(true);
//        controlPanel.getPauseButton().setText("Pause");
//        controlPanel.getStopButton().setDisable(true);
//        controlPanel.getStatusLabel().setText("Completed");
//
//        // Color all bars green to show completion
//        for (Rectangle bar : visualizationPanel.getBars()) {
//            bar.setFill(Color.GREEN);
//        }
//
//        long elapsed = System.currentTimeMillis() - startTime;
//        controlPanel.getTimeLabel().setText("Time: " + elapsed + "ms");
//    }
//
//    private void toggleCompareMode() {
//        controlPanel.getStatusLabel().setText("Compare mode - Coming Soon!");
//    }
//}


package edu.buet.shayan.algomania.sorting.controller;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortingController {

    private ControlPanel controlPanel;
    private VisualizationPanel visualizationPanel;
    private AnimationTimer animationTimer;
    private SortingAlgorithm currentAlgorithm;
    private Iterator<SortStep> sortSteps;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private long startTime;

    public SortingController(ControlPanel controlPanel, VisualizationPanel visualizationPanel) {
        this.controlPanel = controlPanel;
        this.visualizationPanel = visualizationPanel;

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        // Array size change handler
        controlPanel.getArraySizeSpinner().valueProperty().addListener((obs, oldVal, newVal) -> {
            visualizationPanel.resizeArray(newVal);
        });

        // Control button handlers
        controlPanel.getRunButton().setOnAction(e -> startSorting());
        controlPanel.getPauseButton().setOnAction(e -> pauseResumeSort());
        controlPanel.getStopButton().setOnAction(e -> stopSorting());
        controlPanel.getClearButton().setOnAction(e -> clearArray()); // NEW: Clear button handler
        controlPanel.getCompareButton().setOnAction(e -> openCompareWindow());
    }

    private void startSorting() {
        if (isRunning && isPaused) {
            isPaused = false;
            controlPanel.getPauseButton().setText("Pause");
            controlPanel.getStatusLabel().setText("Running...");
            return;
        }

        String selectedAlgo = controlPanel.getAlgorithmSelector().getValue();
        currentAlgorithm = SortingAlgorithmFactory.createAlgorithm(selectedAlgo);

        List<Double> dataCopy = new ArrayList<>(visualizationPanel.getArrayData());
        sortSteps = currentAlgorithm.sort(dataCopy).iterator();

        isRunning = true;
        isPaused = false;
        startTime = System.currentTimeMillis();

        controlPanel.getRunButton().setDisable(true);
        controlPanel.getPauseButton().setDisable(false);
        controlPanel.getStopButton().setDisable(false);
        controlPanel.getStatusLabel().setText("Running...");

        visualizationPanel.resetBarColors();
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
                if (isPaused) return;

                double speed = controlPanel.getSpeedSlider().getValue();
                long interval = (long) (1_000_000_000 / speed);

                if (now - lastUpdate >= interval) {
                    if (sortSteps.hasNext()) {
                        SortStep step = sortSteps.next();
                        visualizeStep(step);
                        lastUpdate = now;

                        long elapsed = System.currentTimeMillis() - startTime;
                        controlPanel.getTimeLabel().setText("Time: " + elapsed + "ms");
                    } else {
                        finishSorting();
                        stop();
                    }
                }
            }
        };
        animationTimer.start();
    }

    private void visualizeStep(SortStep step) {
        List<Rectangle> bars = visualizationPanel.getBars();

        // Reset all bars to default color
        for (Rectangle bar : bars) {
            bar.setFill(Color.LIGHTBLUE);
        }

        // Highlight comparing elements
        if (step.getComparing() != null) {
            for (int index : step.getComparing()) {
                if (index < bars.size()) {
                    bars.get(index).setFill(Color.YELLOW);
                }
            }
        }

        // Highlight swapping elements
        if (step.getSwapping() != null && step.getSwapping().size() == 2) {
            bars.get(step.getSwapping().get(0)).setFill(Color.RED);
            bars.get(step.getSwapping().get(1)).setFill(Color.RED);
        }

        // Update array data and bar heights
        if (step.getArray() != null) {
            visualizationPanel.getArrayData().clear();
            visualizationPanel.getArrayData().addAll(step.getArray());
            visualizationPanel.updateBarHeights(); // This now also updates value labels
        }

        // Highlight sorted elements
        if (step.getSorted() != null) {
            for (int index : step.getSorted()) {
                if (index < bars.size()) {
                    bars.get(index).setFill(Color.GREEN);
                }
            }
        }
    }

    private void pauseResumeSort() {
        if (isRunning) {
            isPaused = !isPaused;
            controlPanel.getPauseButton().setText(isPaused ? "Resume" : "Pause");
            controlPanel.getStatusLabel().setText(isPaused ? "Paused" : "Running...");
        }
    }

    private void stopSorting() {
        if (animationTimer != null) {
            animationTimer.stop();
        }

        isRunning = false;
        isPaused = false;

        controlPanel.getRunButton().setDisable(false);
        controlPanel.getPauseButton().setDisable(true);
        controlPanel.getPauseButton().setText("Pause");
        controlPanel.getStopButton().setDisable(true);
        controlPanel.getStatusLabel().setText("Stopped");

        visualizationPanel.resetBarColors();
        visualizationPanel.updateBarGraph();
    }

    private void finishSorting() {
        isRunning = false;
        isPaused = false;

        controlPanel.getRunButton().setDisable(false);
        controlPanel.getPauseButton().setDisable(true);
        controlPanel.getPauseButton().setText("Pause");
        controlPanel.getStopButton().setDisable(true);
        controlPanel.getStatusLabel().setText("Completed");

        // Color all bars green to show completion
        for (Rectangle bar : visualizationPanel.getBars()) {
            bar.setFill(Color.GREEN);
        }

        long elapsed = System.currentTimeMillis() - startTime;
        controlPanel.getTimeLabel().setText("Time: " + elapsed + "ms");
    }

    // NEW: Clear array functionality
    private void clearArray() {
        // Stop any running animation first
        if (isRunning) {
            stopSorting();
        }

        visualizationPanel.clearArray();
        controlPanel.getStatusLabel().setText("Array cleared");
        controlPanel.getTimeLabel().setText("Time: 0ms");
    }

    // NEW: Open compare window functionality
    private void openCompareWindow() {
        CompareWindow compareWindow = new CompareWindow(visualizationPanel.getArrayData());
        compareWindow.show();
        controlPanel.getStatusLabel().setText("Compare window opened");
    }
}
