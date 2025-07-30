package edu.buet.shayan.algomania;

import edu.buet.shayan.algomania.datastructures.DataStructuresVisualizer;
import edu.buet.shayan.algomania.graph.GraphVisualizerApp;
import edu.buet.shayan.algomania.graph.interaction.ModeManager;
import edu.buet.shayan.algomania.graph.model.GraphModel;
import edu.buet.shayan.algomania.graph.ui.ControlPanel;
import edu.buet.shayan.algomania.graph.ui.GraphCanvas;
import edu.buet.shayan.algomania.graph.ui.InputPanel;
import edu.buet.shayan.algomania.sorting.SortingSimulator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class AlgoMania extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Create main container
        VBox mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(40));

        // Set gradient background
        LinearGradient bg = new LinearGradient(0, 0, 1, 1, true,
                javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#667eea")),
                new Stop(1, Color.web("#764ba2"))
        );
        mainContainer.setBackground(new Background(new BackgroundFill(bg, null, null)));

        // Title
        Text title = new Text("AlgoMania");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        title.setFill(Color.WHITE);
        DropShadow titleShadow = new DropShadow();
        titleShadow.setColor(Color.BLACK);
        titleShadow.setRadius(10);
        title.setEffect(titleShadow);

        Text subtitle = new Text("Choose Your Algorithm Adventure");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        subtitle.setFill(Color.web("#f0f0f0"));

        // Create boxes container
        HBox boxesContainer = new HBox(40);
        boxesContainer.setAlignment(Pos.CENTER);

        // Create the three menu boxes
        VBox graphBox = createMenuBox("Visualize Graph", "Explore graph algorithms and structures", createGraphIcon());
        VBox sortBox = createMenuBox("Sort your Life!", "Watch sorting algorithms in action", createSortIcon());
        VBox dataBox = createMenuBox("Data Enthusiast", "Dive into data structure visualizations", createDataIcon());

        // Add click handlers
        graphBox.setOnMouseClicked(e -> launchGraphVisualizer());
        sortBox.setOnMouseClicked(e -> launchSortingSimulator());
        dataBox.setOnMouseClicked(e -> launchDataStructureVisualizer());

        boxesContainer.getChildren().addAll(graphBox, sortBox, dataBox);
        mainContainer.getChildren().addAll(title, subtitle, boxesContainer);

        Scene scene = new Scene(mainContainer, 1200, 700);
        primaryStage.setTitle("AlgoMania - Algorithm Visualizer Suite");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private VBox createMenuBox(String title, String description, Pane icon) {
        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.setPrefSize(300, 350);

        // Create gradient background for box
        LinearGradient boxBg = new LinearGradient(0, 0, 1, 1, true,
                javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffffff")),
                new Stop(1, Color.web("#f8f9fa"))
        );
        box.setBackground(new Background(new BackgroundFill(boxBg, new CornerRadii(15), null)));

        // Add border and shadow
        box.setBorder(new Border(new BorderStroke(Color.web("#e0e0e0"), BorderStrokeStyle.SOLID,
                new CornerRadii(15), new BorderWidths(2))));

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web("#00000030"));
        shadow.setRadius(15);
        shadow.setOffsetY(5);
        box.setEffect(shadow);

        // Title
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#333333"));

        // Description
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        descLabel.setTextFill(Color.web("#666666"));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(250);
        descLabel.setAlignment(Pos.CENTER);

        box.getChildren().addAll(icon, titleLabel, descLabel);

        // Add hover animations
        addHoverAnimations(box);

        return box;
    }

    private void addHoverAnimations(VBox box) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), box);
        scaleUp.setToX(1.05);
        scaleUp.setToY(1.05);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), box);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        Glow glow = new Glow(0.3);

        box.setOnMouseEntered(e -> {
            scaleUp.play();
            box.setEffect(glow);
        });

        box.setOnMouseExited(e -> {
            scaleDown.play();
            box.setEffect(null);
        });
    }

    private Pane createGraphIcon() {
        Pane pane = new Pane();
        pane.setPrefSize(80, 80);

        // Create nodes
        Circle node1 = new Circle(20, 20, 8, Color.web("#ff6b6b"));
        Circle node2 = new Circle(60, 20, 8, Color.web("#4ecdc4"));
        Circle node3 = new Circle(40, 50, 8, Color.web("#45b7d1"));
        Circle node4 = new Circle(20, 60, 8, Color.web("#96ceb4"));

        // Create edges
        Line edge1 = new Line(20, 20, 60, 20);
        Line edge2 = new Line(20, 20, 40, 50);
        Line edge3 = new Line(60, 20, 40, 50);
        Line edge4 = new Line(40, 50, 20, 60);

        edge1.setStroke(Color.web("#95a5a6"));
        edge2.setStroke(Color.web("#95a5a6"));
        edge3.setStroke(Color.web("#95a5a6"));
        edge4.setStroke(Color.web("#95a5a6"));

        edge1.setStrokeWidth(2);
        edge2.setStrokeWidth(2);
        edge3.setStrokeWidth(2);
        edge4.setStrokeWidth(2);

        pane.getChildren().addAll(edge1, edge2, edge3, edge4, node1, node2, node3, node4);
        return pane;
    }

    private Pane createSortIcon() {
        Pane pane = new Pane();
        pane.setPrefSize(80, 80);

        // Create bars representing sorting
        Rectangle bar1 = new Rectangle(10, 60, 8, 20);
        Rectangle bar2 = new Rectangle(25, 40, 8, 40);
        Rectangle bar3 = new Rectangle(40, 20, 8, 60);
        Rectangle bar4 = new Rectangle(55, 30, 8, 50);

        bar1.setFill(Color.web("#ff9ff3"));
        bar2.setFill(Color.web("#54a0ff"));
        bar3.setFill(Color.web("#5f27cd"));
        bar4.setFill(Color.web("#00d2d3"));

        pane.getChildren().addAll(bar1, bar2, bar3, bar4);
        return pane;
    }

    private Pane createDataIcon() {
        Pane pane = new Pane();
        pane.setPrefSize(80, 80);

        // Create stack representation
        Rectangle base = new Rectangle(25, 55, 30, 8);
        Rectangle middle = new Rectangle(30, 45, 20, 8);
        Rectangle top = new Rectangle(35, 35, 10, 8);

        base.setFill(Color.web("#fd79a8"));
        middle.setFill(Color.web("#fdcb6e"));
        top.setFill(Color.web("#6c5ce7"));

        // Create tree nodes
        Circle root = new Circle(40, 20, 5, Color.web("#a29bfe"));
        Circle left = new Circle(25, 30, 4, Color.web("#74b9ff"));
        Circle right = new Circle(55, 30, 4, Color.web("#00b894"));

        Line leftLine = new Line(40, 20, 25, 30);
        Line rightLine = new Line(40, 20, 55, 30);
        leftLine.setStroke(Color.web("#ddd"));
        rightLine.setStroke(Color.web("#ddd"));

        pane.getChildren().addAll(base, middle, top, leftLine, rightLine, root, left, right);
        return pane;
    }

    private void launchGraphVisualizer() {
        try {
            // Replace with your actual Graph Visualizer main class
            // GraphVisualizerMain.main(new String[]{});
            Stage graphStage = new Stage();
            // For now, create a new stage as placeholder
            GraphVisualizerApp g = new GraphVisualizerApp();
            g.start(graphStage);
//            Label placeholder = new Label("Graph Visualizer\n\nReplace this with your actual GraphVisualizerMain.main() call");
//            placeholder.setFont(Font.font("Arial", 16));
//            placeholder.setAlignment(Pos.CENTER);
//
//            Scene scene = new Scene(placeholder, 600, 400);
//            graphStage.setTitle("Graph Visualizer");
//            graphStage.setScene(scene);
//            graphStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launchSortingSimulator() {
        try {
            // Replace with your actual Sorting Simulator main class
            // SortingSimulatorMain.main(new String[]{});

            Stage sortStage = new Stage();
            SortingSimulator s = new SortingSimulator();
            s.start(sortStage);
//            Label placeholder = new Label("Sorting Simulator\n\nReplace this with your actual SortingSimulatorMain.main() call");
//            placeholder.setFont(Font.font("Arial", 16));
//            placeholder.setAlignment(Pos.CENTER);
//
//            Scene scene = new Scene(placeholder, 600, 400);
//            sortStage.setTitle("Sorting Simulator");
//            sortStage.setScene(scene);
//            sortStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launchDataStructureVisualizer() {
        try {
            // Replace with your actual Data Structure Visualizer main class
            // DataStructureVisualizerMain.main(new String[]{});

            Stage dataStage = new Stage();
            DataStructuresVisualizer d = new DataStructuresVisualizer();
            d.start(dataStage);
//            Label placeholder = new Label("Data Structure Visualizer\n\nReplace this with your actual DataStructureVisualizerMain.main() call");
//            placeholder.setFont(Font.font("Arial", 16));
//            placeholder.setAlignment(Pos.CENTER);
//
//            Scene scene = new Scene(placeholder, 600, 400);
//            dataStage.setTitle("Data Structure Visualizer");
//            dataStage.setScene(scene);
//            dataStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}