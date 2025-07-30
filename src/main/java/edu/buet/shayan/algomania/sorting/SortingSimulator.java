package edu.buet.shayan.algomania.sorting;

//import edu.buet.shayan.sortingsimulator.controller.SortingController;
//import edu.buet.shayan.sortingsimulator.ui.ControlPanel;
//import edu.buet.shayan.sortingsimulator.ui.VisualizationPanel;
import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class SortingSimulator extends Application {

    private ControlPanel controlPanel;
    private VisualizationPanel visualizationPanel;
    private SortingController controller;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Data Structure Simulator - Sorting Algorithms");
        Image icon = new Image("C:\\Users\\hp\\Desktop\\AlgoMania\\src\\main\\java\\edu\\buet\\shayan\\algomania\\images\\sorting_icon.png");
        primaryStage.getIcons().add(icon);

        // Initialize components
        controlPanel = new ControlPanel();
        visualizationPanel = new VisualizationPanel();
        controller = new SortingController(controlPanel, visualizationPanel);
//
        // Setup layout
        HBox mainContainer = new HBox(10);
        mainContainer.setPadding(new Insets(10));


        controlPanel.setPrefWidth(360);
        controlPanel.setMaxWidth(360);

        HBox.setHgrow(visualizationPanel, Priority.ALWAYS);

        mainContainer.getChildren().addAll(controlPanel, visualizationPanel);

        Scene scene = new Scene(mainContainer, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}