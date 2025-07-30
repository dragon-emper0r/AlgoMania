//package edu.buet.shayan.sortingsimulator.ui;
//
//import javafx.application.Platform;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class VisualizationPanel extends VBox {
//
//    private HBox arrayInputBoxes;
//    private VBox barGraphContainer;
//    private List<Double> arrayData;
//    private List<Rectangle> bars;
//
//    public VisualizationPanel() {
//        super(10);
//        setPadding(new Insets(10));
//
//        arrayData = new ArrayList<>(Arrays.asList(3.0, 4.0, 5.0, 2.0, 1.0));
//        bars = new ArrayList<>();
//
//        createComponents();
//        layoutComponents();
//        updateBarGraph();
//    }
//
//    private void createComponents() {
//        // Array input section
//        arrayInputBoxes = new HBox(5);
//        arrayInputBoxes.setAlignment(Pos.CENTER_LEFT);
//
//        // Bar graph section
//        barGraphContainer = new VBox();
//        barGraphContainer.setPrefHeight(400);
//        barGraphContainer.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");
//
//        createInputBoxes();
//    }
//
//    private void layoutComponents() {
//        Label inputLabel = new Label("Array Input:");
//        inputLabel.setFont(Font.font(16));
//
//        Label graphLabel = new Label("Visualization:");
//        graphLabel.setFont(Font.font(16));
//
//        getChildren().addAll(inputLabel, arrayInputBoxes, graphLabel, barGraphContainer);
//    }
//
//    public void createInputBoxes() {
//        arrayInputBoxes.getChildren().clear();
//        for (int i = 0; i < arrayData.size(); i++) {
//            TextField textField = new TextField();
//            textField.setPrefWidth(60);
//            textField.setText(String.valueOf(arrayData.get(i).intValue()));
//
//            final int index = i;
//            textField.textProperty().addListener((obs, oldText, newText) -> {
//                try {
//                    if (!newText.isEmpty()) {
//                        double value = Double.parseDouble(newText);
//                        if (index < arrayData.size()) {
//                            arrayData.set(index, value);
//                            updateBarGraph();
//                        }
//                    }
//                } catch (NumberFormatException e) {
//                    Platform.runLater(() -> textField.setText(oldText));
//                }
//            });
//
//            arrayInputBoxes.getChildren().add(textField);
//        }
//    }
//
//    public void resizeArray(int newSize) {
//        while (arrayData.size() < newSize) {
//            arrayData.add(1.0);
//        }
//        while (arrayData.size() > newSize) {
//            arrayData.remove(arrayData.size() - 1);
//        }
//        createInputBoxes();
//        updateBarGraph();
//    }
//
//    public void updateBarGraph() {
//        barGraphContainer.getChildren().clear();
//        bars.clear();
//
//        if (arrayData.isEmpty()) return;
//
//        double maxValue = arrayData.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
//        double maxHeight = 350;
//        double barWidth = Math.min(50, 400.0 / arrayData.size());
//
//        HBox barsContainer = new HBox(2);
//        barsContainer.setAlignment(Pos.BOTTOM_CENTER);
//        barsContainer.setPadding(new Insets(20));
//
//        for (int i = 0; i < arrayData.size(); i++) {
//            VBox barContainer = new VBox();
//            barContainer.setAlignment(Pos.BOTTOM_CENTER);
//
//            double value = arrayData.get(i);
//            double height = (value / maxValue) * maxHeight;
//
//            Rectangle bar = new Rectangle(barWidth, height);
//            bar.setFill(Color.LIGHTBLUE);
//            bar.setStroke(Color.DARKBLUE);
//            bar.setStrokeWidth(1);
//
//            Label valueLabel = new Label(String.valueOf((double) value));
//            valueLabel.setFont(Font.font(10));
//
//            barContainer.getChildren().addAll(valueLabel, bar);
//            barsContainer.getChildren().add(barContainer);
//            bars.add(bar);
//        }
//
//        barGraphContainer.getChildren().add(barsContainer);
//    }
//
//    public void updateBarHeights() {
//        if (arrayData.size() != bars.size()) return;
//
//        double maxValue = arrayData.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
//        double maxHeight = 350;
//
//        for (int i = 0; i < bars.size(); i++) {
//            double value = arrayData.get(i);
//            double height = (value / maxValue) * maxHeight;
//            bars.get(i).setHeight(height);
//        }
//    }
//
//    public void resetBarColors() {
//        for (Rectangle bar : bars) {
//            bar.setFill(Color.LIGHTBLUE);
//        }
//    }
//
//    // Getters
//    public List<Double> getArrayData() { return arrayData; }
//    public List<Rectangle> getBars() { return bars; }
//    public VBox getBarGraphContainer() { return barGraphContainer; }
//}


package edu.buet.shayan.algomania.sorting.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualizationPanel extends VBox {

    private HBox arrayInputBoxes;
    private VBox barGraphContainer;
    private List<Double> arrayData;
    private List<Rectangle> bars;
    private List<Label> valueLabels; // Added to track value labels

    public VisualizationPanel() {
        super(10);
        setPadding(new Insets(10));

        arrayData = new ArrayList<>(Arrays.asList(3.0, 4.0, 5.0, 2.0, 1.0));
        bars = new ArrayList<>();
        valueLabels = new ArrayList<>(); // Initialize value labels list

        createComponents();
        layoutComponents();
        updateBarGraph();
    }

    private void createComponents() {
        // Array input section
        arrayInputBoxes = new HBox(5);
        arrayInputBoxes.setAlignment(Pos.CENTER_LEFT);

        // Bar graph section
        barGraphContainer = new VBox();
        barGraphContainer.setPrefHeight(400);
        barGraphContainer.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");

        createInputBoxes();
    }

    private void layoutComponents() {
        Label inputLabel = new Label("Array Input:");
        inputLabel.setFont(Font.font(16));

        Label graphLabel = new Label("Visualization:");
        graphLabel.setFont(Font.font(16));

        getChildren().addAll(inputLabel, arrayInputBoxes, graphLabel, barGraphContainer);
    }

    public void createInputBoxes() {
        arrayInputBoxes.getChildren().clear();
        for (int i = 0; i < arrayData.size(); i++) {
            TextField textField = new TextField();
            textField.setPrefWidth(60);
            textField.setText(String.valueOf(arrayData.get(i).intValue()));

            final int index = i;
            textField.textProperty().addListener((obs, oldText, newText) -> {
                try {
                    if (!newText.isEmpty()) {
                        double value = Double.parseDouble(newText);
                        if (index < arrayData.size()) {
                            arrayData.set(index, value);
                            updateBarGraph();
                        }
                    }
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> textField.setText(oldText));
                }
            });

            arrayInputBoxes.getChildren().add(textField);
        }
    }

    public void resizeArray(int newSize) {
        while (arrayData.size() < newSize) {
            arrayData.add(1.0);
        }
        while (arrayData.size() > newSize) {
            arrayData.remove(arrayData.size() - 1);
        }
        createInputBoxes();
        updateBarGraph();
    }

    public void clearArray() {
        arrayData.clear();
        arrayData.addAll(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0)); // Reset to default
        createInputBoxes();
        updateBarGraph();
    }

    public void updateBarGraph() {
        barGraphContainer.getChildren().clear();
        bars.clear();
        valueLabels.clear(); // Clear value labels list

        if (arrayData.isEmpty()) return;

        double maxValue = arrayData.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
        double maxHeight = 350;
        double barWidth = Math.min(50, 400.0 / arrayData.size());

        HBox barsContainer = new HBox(2);
        barsContainer.setAlignment(Pos.BOTTOM_CENTER);
        barsContainer.setPadding(new Insets(20));

        for (int i = 0; i < arrayData.size(); i++) {
            VBox barContainer = new VBox();
            barContainer.setAlignment(Pos.BOTTOM_CENTER);

            double value = arrayData.get(i);
            double height = (value / maxValue) * maxHeight;

            Rectangle bar = new Rectangle(barWidth, height);
            bar.setFill(Color.LIGHTBLUE);
            bar.setStroke(Color.DARKBLUE);
            bar.setStrokeWidth(1);

//            Label valueLabel = new Label(String.valueOf(value.intValue()));
            Label valueLabel = new Label(Double.toString(value));
            valueLabel.setFont(Font.font(10));

            barContainer.getChildren().addAll(valueLabel, bar);
            barsContainer.getChildren().add(barContainer);
            bars.add(bar);
            valueLabels.add(valueLabel); // Add to value labels list
        }

        barGraphContainer.getChildren().add(barsContainer);
    }

    public void updateBarHeights() {
        if (arrayData.size() != bars.size() || arrayData.size() != valueLabels.size()) return;

        double maxValue = arrayData.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
        double maxHeight = 350;

        for (int i = 0; i < bars.size(); i++) {
            double value = arrayData.get(i);
            double height = (value / maxValue) * maxHeight;
            bars.get(i).setHeight(height);

            // FIXED: Update value labels to match the current array data
            valueLabels.get(i).setText(Double.toString(value));
        }
    }

    public void resetBarColors() {
        for (Rectangle bar : bars) {
            bar.setFill(Color.LIGHTBLUE);
        }
    }

    // Getters
    public List<Double> getArrayData() { return arrayData; }
    public List<Rectangle> getBars() { return bars; }
    public List<Label> getValueLabels() { return valueLabels; } // Added getter for value labels
    public VBox getBarGraphContainer() { return barGraphContainer; }
}