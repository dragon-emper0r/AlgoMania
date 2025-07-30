package edu.buet.shayan.algomania.graph;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GraphVisualizerApp extends Application {
    private GraphModel model;
    private GraphCanvas canvas;
    private ControlPanel controlPanel;
    private InputPanel inputPanel;
    private ModeManager modeManager;

    @Override
    public void start(Stage stage) {
        Image icon = new Image("C:\\Users\\hp\\Desktop\\AlgoMania\\src\\main\\java\\edu\\buet\\shayan\\algomania\\images\\graph_icon.png");
        stage.getIcons().add(icon);
        model = new GraphModel();
        modeManager = new ModeManager();
        canvas = new GraphCanvas(model, modeManager);
        controlPanel = new ControlPanel(modeManager, canvas, model);
        inputPanel = new InputPanel(model, canvas, stage);

        BorderPane topBar = new BorderPane();
        topBar.setLeft(controlPanel.getControlBar());
        topBar.setRight(controlPanel.getAlgorithmControls());

        VBox rightPanel = new VBox(topBar, canvas.getPane());
        VBox.setVgrow(canvas.getPane(), Priority.ALWAYS);

        HBox root = new HBox(inputPanel.getPanel(), rightPanel);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("Graph Visualizer with Algorithms");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}