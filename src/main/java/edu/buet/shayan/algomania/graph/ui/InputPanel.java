package edu.buet.shayan.algomania.graph.ui;

import edu.buet.shayan.algomania.graph.parser.EdgeListParser;
import edu.buet.shayan.algomania.graph.parser.GraphInputParser;
//import edu.buet.shayan.graphvisualizerapp.model.GraphModel;
//import edu.buet.shayan.graphvisualizerapp.parser.*;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InputPanel {
    private VBox leftPanel;
    private MenuButton createGraphMenu;
    private GraphModel model;
    private GraphCanvas canvas;
    private Stage stage;

    public InputPanel(GraphModel model, GraphCanvas canvas, Stage stage) {
        this.model = model;
        this.canvas = canvas;
        this.stage = stage;
        createPanel();
    }

    private void createPanel() {
        leftPanel = new VBox(15);
        leftPanel.setPrefWidth(320);
        leftPanel.setPadding(new Insets(15));
        leftPanel.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1;");

        Label title = new Label("Graph Input Methods");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label instruction = new Label("Choose a method to create your graph:");
        instruction.setFont(Font.font("Arial", 12));
        instruction.setWrapText(true);

        createGraphMenu = new MenuButton("Create Graph From...");
        createGraphMenu.setPrefWidth(280);
        setupMenuItems();

        leftPanel.getChildren().addAll(title, instruction, createGraphMenu);
    }

    private void setupMenuItems() {
        MenuItem adjacencyMatrixItem = new MenuItem("Adjacency Matrix");
        MenuItem adjacencyListItem = new MenuItem("Adjacency List");
        MenuItem edgeListItem = new MenuItem("Edge List");

        createGraphMenu.getItems().addAll(adjacencyMatrixItem, adjacencyListItem, edgeListItem);

        adjacencyMatrixItem.setOnAction(e -> {
            createGraphMenu.setText("Adjacency Matrix");
            showTextInputPane("Adjacency Matrix Input",
                    "Enter matrix (space-separated values):\n" +
                            "Use 0 for no edge, 1 for edge, or weight for weighted edge:\n\n" +
                            "0 1 0\n1 0 1\n0 1 0\n\n" +
                            "Weighted example:\n0 5 0\n5 0 3\n0 3 0",
                    new AdjacencyMatrixParser(model, canvas));
        });

        adjacencyListItem.setOnAction(e -> {
            createGraphMenu.setText("Adjacency List");
            showTextInputPane("Adjacency List Input",
                    "Format: node: space-separated neighbors\n\n" +
                            "0: 1 2\n1: 0 2\n2: 0 1\n\n" +
                            "Empty neighbors (isolated node):\n3:",
                    new AdjacencyListParser(model, canvas));
        });

        edgeListItem.setOnAction(e -> {
            createGraphMenu.setText("Edge List");
            showTextInputPane("Edge List Input",
                    "Format: from_node to_node [weight]\n" +
                            "Weight is optional (default = 1):\n\n" +
                            "0 1\n1 2\n2 0\n\n" +
                            "With weights:\n0 1 5\n1 2 3\n2 0 2",
                    new EdgeListParser(model, canvas));
        });
    }

    private void showTextInputPane(String title, String example, GraphInputParser parser) {
        leftPanel.getChildren().clear();

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label exampleLabel = new Label("Format & Examples:");
        exampleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        TextArea exampleArea = new TextArea(example);
        exampleArea.setEditable(false);
        exampleArea.setPrefRowCount(6);
        exampleArea.setStyle("-fx-control-inner-background: #f0f8ff; -fx-font-family: monospace;");

        Label inputLabel = new Label("Enter Your Graph Data:");
        inputLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(8);
        textArea.setWrapText(false);
        textArea.setStyle("-fx-font-family: monospace;");
        textArea.setPromptText("Enter graph data here...");

        Button loadButton = new Button("Load Graph");
        loadButton.setPrefWidth(130);
        loadButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        loadButton.setOnAction(e -> {
            String inputText = textArea.getText();
            if (inputText == null || inputText.isBlank()) {
                showWarning("Input is empty. Please enter graph data.");
                return;
            }
            canvas.clearGraph();
            boolean success = parser.parse(inputText);
            if (success) {
                showInfo("Graph loaded successfully!\nNodes: " + model.getNodes().size() +
                        ", Edges: " + model.getEdges().size());
            } else {
                showWarning("Failed to parse input. Please check the format and try again.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setPrefWidth(130);
        backButton.setOnAction(e -> {
            leftPanel.getChildren().clear();
            leftPanel.getChildren().addAll(
                    new Label("Graph Input Methods") {{
                        setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    }},
                    new Label("Choose a method to create your graph:") {{
                        setFont(Font.font("Arial", 12));
                        setWrapText(true);
                    }},
                    createGraphMenu
            );
            createGraphMenu.setText("Create Graph From...");
        });

        HBox buttonBox = new HBox(10, loadButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        leftPanel.getChildren().addAll(
                titleLabel, exampleLabel, exampleArea,
                inputLabel, textArea, buttonBox
        );
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public VBox getPanel() { return leftPanel; }
}