package edu.buet.shayan.algomania.graph.ui;

//import edu.buet.shayan.graphvisualizerapp.interaction.ModeManager;
//import edu.buet.shayan.graphvisualizerapp.algorithm.*;
//import edu.buet.shayan.graphvisualizerapp.model.GraphModel;
//import edu.buet.shayan.graphvisualizerapp.model.GraphNode;
//import edu.buet.shayan.graphvisualizerapp.model.GraphEdge;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class ControlPanel {
    private HBox controlBar;
    private VBox algorithmControls;
    private ModeManager modeManager;
    private GraphCanvas canvas;
    private GraphModel model;
    private GraphAlgorithm currentAlgorithm;

    private ToggleButton addNodeBtn, addEdgeBtn, colorBtn, assignWeightBtn, removeNodeBtn, removeEdgeBtn;
    private MenuButton algorithmMenu;
    private Button runBtn, pauseBtn, stopBtn;
    private Slider speedSlider;
    private Label speedLabel, statusLabel;

    public ControlPanel(ModeManager modeManager, GraphCanvas canvas, GraphModel model) {
        this.modeManager = modeManager;
        this.canvas = canvas;
        this.model = model;
        createControls();
    }

    private void createControls() {
        createToggleButtons();
        createAlgorithmControls();

        controlBar = new HBox(10);
        controlBar.setPadding(new Insets(10));
        controlBar.getChildren().addAll(
                addNodeBtn, addEdgeBtn, colorBtn, assignWeightBtn,
                removeNodeBtn, removeEdgeBtn, new Separator(),
                createClearButton()
        );
    }

    private void createToggleButtons() {
        addNodeBtn = new ToggleButton("Add Node");
        addEdgeBtn = new ToggleButton("Add Edge");
        colorBtn = new ToggleButton("Color Node");
        assignWeightBtn = new ToggleButton("Assign Weight");
        removeNodeBtn = new ToggleButton("Remove Node");
        removeEdgeBtn = new ToggleButton("Remove Edge");

        List<ToggleButton> toggleButtons = List.of(
                addNodeBtn, addEdgeBtn, colorBtn, assignWeightBtn, removeNodeBtn, removeEdgeBtn
        );

        for (ToggleButton btn : toggleButtons) {
            btn.setOnAction(e -> {
                modeManager.activateMode(btn);
                if (modeManager.getActiveToggleButton() != null &&
                        canvas.getNodeHandler().getSelectedNode() != null) {
                    canvas.getNodeHandler().getSelectedNode().getArrow().setVisible(false);
                    canvas.getNodeHandler().setSelectedNode(null);
                }
            });
        }
    }

    private void createAlgorithmControls() {
        algorithmMenu = new MenuButton("Choose Algorithm");
        setupAlgorithmMenu();

        runBtn = new Button("Run");
        runBtn.setOnAction(e -> runAlgorithm());
        runBtn.setDisable(true);

        pauseBtn = new Button("Pause");
        pauseBtn.setOnAction(e -> pauseAlgorithm());
        pauseBtn.setDisable(true);

        stopBtn = new Button("Stop");
        stopBtn.setOnAction(e -> stopAlgorithm());
        stopBtn.setDisable(true);

        // Enhanced speed slider with better range
        speedSlider = new Slider(1, 10, 5);
        speedSlider.setShowTickMarks(true);
        speedSlider.setShowTickLabels(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(0);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPrefWidth(150);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateSpeed(newVal.intValue()));

        speedLabel = new Label("Speed: 5");
        statusLabel = new Label("Ready - Select a starting node and choose an algorithm");

        HBox algorithmButtons = new HBox(5, algorithmMenu, runBtn, pauseBtn, stopBtn);
        HBox speedControls = new HBox(10, new Label("Animation Speed:"), speedSlider, speedLabel);

        algorithmControls = new VBox(5, algorithmButtons, speedControls, statusLabel);
        algorithmControls.setPadding(new Insets(10));
    }

    private void setupAlgorithmMenu() {
        algorithmMenu.getItems().clear();
        for (String algorithmName : AlgorithmFactory.getAvailableAlgorithms()) {
            MenuItem item = new MenuItem(algorithmName);
            item.setOnAction(e -> {
                algorithmMenu.setText(algorithmName);
                runBtn.setDisable(false);
                statusLabel.setText("Algorithm selected: " + algorithmName + " - Ready to run");
            });
            algorithmMenu.getItems().add(item);
        }
    }

    private Button createClearButton() {
        Button clearBtn = new Button("Clear Graph");
        clearBtn.setOnAction(e -> {
            if (currentAlgorithm != null && currentAlgorithm.isRunning()) {
                currentAlgorithm.stop();
            }
            canvas.clearGraph();
            statusLabel.setText("Graph cleared - Ready to create new graph");
        });
        return clearBtn;
    }

    private void runAlgorithm() {
        String selectedAlgorithm = algorithmMenu.getText();

        if (selectedAlgorithm.equals("Choose Algorithm")) {
            showError("Please select an algorithm first.");
            return;
        }

        GraphNode startNode = canvas.getNodeHandler().getSelectedNode();
        if (startNode == null) {
            showError("Please select a starting node first.\nClick on a node to select it.");
            return;
        }

        if (model.getNodes().isEmpty()) {
            showError("Graph is empty. Please create a graph first.");
            return;
        }

        currentAlgorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm);
        if (currentAlgorithm == null) {
            showError("Algorithm not available: " + selectedAlgorithm);
            return;
        }

        // Set animation delay based on speed slider
        if (currentAlgorithm instanceof AbstractGraphAlgorithm) {
            int delay = calculateDelay((int) speedSlider.getValue());
            ((AbstractGraphAlgorithm) currentAlgorithm).setAnimationDelay(delay);
        }

        runBtn.setDisable(true);
        pauseBtn.setDisable(false);
        stopBtn.setDisable(false);
        algorithmMenu.setDisable(true);

        currentAlgorithm.execute(model, startNode, new CustomAlgorithmListener());
    }

    private void pauseAlgorithm() {
        if (currentAlgorithm != null && currentAlgorithm.isRunning()) {
            if (currentAlgorithm.isPaused()) {
                currentAlgorithm.resume();
                pauseBtn.setText("Pause");
            } else {
                currentAlgorithm.pause();
                pauseBtn.setText("Resume");
            }
        }
    }

    private void stopAlgorithm() {
        if (currentAlgorithm != null) {
            currentAlgorithm.stop();
        }
        resetButtonStates();
    }

    private void updateSpeed(int speed) {
        speedLabel.setText("Speed: " + speed + " (" + getSpeedDescription(speed) + ")");
        if (currentAlgorithm instanceof AbstractGraphAlgorithm) {
            int delay = calculateDelay(speed);
            ((AbstractGraphAlgorithm) currentAlgorithm).setAnimationDelay(delay);
        }
    }

    private int calculateDelay(int speed) {
        // Speed 1 = 2000ms (very slow), Speed 10 = 200ms (very fast)
        return 2200 - (speed * 200);
    }

    private String getSpeedDescription(int speed) {
        switch (speed) {
            case 1: case 2: return "Very Slow";
            case 3: case 4: return "Slow";
            case 5: case 6: return "Normal";
            case 7: case 8: return "Fast";
            case 9: case 10: return "Very Fast";
            default: return "Normal";
        }
    }

    private void resetButtonStates() {
        runBtn.setDisable(algorithmMenu.getText().equals("Choose Algorithm"));
        pauseBtn.setDisable(true);
        pauseBtn.setText("Pause");
        stopBtn.setDisable(true);
        algorithmMenu.setDisable(false);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private class CustomAlgorithmListener implements AlgorithmListener {
        @Override
        public void onNodeVisited(GraphNode node, int step) {
            statusLabel.setText("Step " + step + ": Visited node " + node.getId());
        }

        @Override
        public void onEdgeTraversed(GraphEdge edge, int step) {
            statusLabel.setText("Step " + step + ": Traversed edge " +
                    edge.getFrom().getId() + " → " + edge.getTo().getId());
        }

        @Override
        public void onAlgorithmStart() {
            statusLabel.setText("Algorithm started - Running...");
        }

        @Override
        public void onAlgorithmComplete(AlgorithmResult result) {
            statusLabel.setText("Algorithm completed successfully!");
            resetButtonStates();
            showResult(result);
        }

        @Override
        public void onAlgorithmStopped() {
            statusLabel.setText("Algorithm stopped by user");
            resetButtonStates();
        }

        @Override
        public void onAlgorithmPaused() {
            statusLabel.setText("Algorithm paused - Click Resume to continue");
        }

        @Override
        public void onAlgorithmResumed() {
            statusLabel.setText("Algorithm resumed - Running...");
        }

        @Override
        public void onMessage(String message) {
            statusLabel.setText(message);
        }

        private void showResult(AlgorithmResult result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Algorithm Result");
            alert.setHeaderText(result.getAlgorithmName() + " Completed");

            TextArea textArea = new TextArea(result.getSummary());
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setPrefRowCount(20);
            textArea.setPrefColumnCount(60);

            alert.getDialogPane().setContent(textArea);
            alert.getDialogPane().setPrefSize(600, 500);
            alert.showAndWait();
        }
    }

    public HBox getControlBar() { return controlBar; }
    public VBox getAlgorithmControls() { return algorithmControls; }
}