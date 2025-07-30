package edu.buet.shayan.algomania.graph.ui;

import edu.buet.shayan.algomania.graph.interaction.ModeManager;
import edu.buet.shayan.algomania.graph.model.GraphModel;
//import edu.buet.shayan.graphvisualizerapp.model.*;
//import edu.buet.shayan.graphvisualizerapp.interaction.*;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GraphCanvas {
    private Pane canvas;
    private GraphModel model;
    private NodeInteractionHandler nodeHandler;
    private EdgeInteractionHandler edgeHandler;
    private ModeManager modeManager;

    public GraphCanvas(GraphModel model, ModeManager modeManager) {
        this.model = model;
        this.modeManager = modeManager;

        canvas = new Pane();
        canvas.setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #333; -fx-border-width: 2;");

        this.nodeHandler = new NodeInteractionHandler(model, this, modeManager);
        this.edgeHandler = new EdgeInteractionHandler(model, this, modeManager);

        setupCanvasClickHandler();
    }


    private void setupCanvasClickHandler() {
        canvas.setOnMouseClicked(event -> {
            if (modeManager.getActiveToggleButton() != null &&
                    modeManager.getActiveToggleButton().getText().equals("Add Node")) {
                double x = event.getX();
                double y = event.getY();
                addNodeAtPosition(x, y);
            }
        });
    }

    public GraphNode createNode(int id, double x, double y) {
        Circle circle = new Circle(x, y, 25);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        Label label = new Label(String.valueOf(id));
        label.setLayoutX(x - 8);
        label.setLayoutY(y - 12);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        GraphNode node = new GraphNode(id, label, circle);
        canvas.getChildren().addAll(circle, label, node.getArrow());

        circle.setOnMouseClicked(e -> {
            e.consume();
            nodeHandler.handleNodeClick(node);
        });

        return node;
    }

    public void addNodeAtPosition(double x, double y) {
        // Ensure node is not too close to edges
        x = Math.max(30, Math.min(x, canvas.getWidth() - 30));
        y = Math.max(30, Math.min(y, canvas.getHeight() - 30));

        int nodeID = model.getNodes().size();
        GraphNode node = createNode(nodeID, x, y);
        model.addNode(node);
        updateArrowPosition(node);
    }

    public void addEdge(GraphNode from, GraphNode to) {
        // Check for duplicate edges
        for (GraphEdge edge : model.getEdges()) {
            if (edge.getFrom() == from && edge.getTo() == to) return;
        }

        Line line = new Line(
                from.getCircle().getCenterX(), from.getCircle().getCenterY(),
                to.getCircle().getCenterX(), to.getCircle().getCenterY()
        );

        GraphEdge edge = new GraphEdge(from, to, line);
        model.addEdge(edge);
        canvas.getChildren().add(0, line); // Add line behind nodes
        canvas.getChildren().add(edge.getWeightLabel());
        updateWeightLabelPosition(edge);

        line.setOnMouseClicked(ev -> {
            ev.consume();
            edgeHandler.handleEdgeClick(edge);
        });
    }

    public void clearGraph() {
        for (GraphEdge edge : model.getEdges()) {
            canvas.getChildren().removeAll(edge.getLine(), edge.getWeightLabel());
        }
        for (GraphNode node : model.getNodes()) {
            canvas.getChildren().removeAll(node.getCircle(), node.getLabel(), node.getArrow());
        }
        model.clear();
        nodeHandler.setSelectedNode(null);
        modeManager.clearActiveButton();
    }

    public void updateWeightLabelPosition(GraphEdge edge) {
        double x1 = edge.getLine().getStartX();
        double y1 = edge.getLine().getStartY();
        double x2 = edge.getLine().getEndX();
        double y2 = edge.getLine().getEndY();

        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;

        double dx = x2 - x1;
        double dy = y2 - y1;
        double len = Math.hypot(dx, dy);
        if (len == 0) len = 1;
        double perpX = -dy / len;
        double perpY = dx / len;

        double offset = 15;
        double labelX = midX + perpX * offset - 10;
        double labelY = midY + perpY * offset - 8;

        edge.updateWeightPosition(labelX, labelY);
    }

    public void updateArrowPosition(GraphNode node) {
        double cx = node.getCircle().getCenterX();
        double cy = node.getCircle().getCenterY();
        node.setArrowPosition(cx, cy - 40);
    }

    public Pane getPane() { return canvas; }
    public NodeInteractionHandler getNodeHandler() { return nodeHandler; }
    public EdgeInteractionHandler getEdgeHandler() { return edgeHandler; }
}
