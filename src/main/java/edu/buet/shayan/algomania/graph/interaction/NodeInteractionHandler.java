package edu.buet.shayan.algomania.graph.interaction;

//import edu.buet.shayan.graphvisualizerapp.model.*;
//import edu.buet.shayan.graphvisualizerapp.ui.GraphCanvas;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import java.util.ArrayList;
import java.util.List;

public class NodeInteractionHandler {
    private GraphModel model;
    private GraphCanvas canvas;
    private ModeManager modeManager;
    private GraphNode selectedNode;

    public NodeInteractionHandler(GraphModel model, GraphCanvas canvas, ModeManager modeManager) {
        this.model = model;
        this.canvas = canvas;
        this.modeManager = modeManager;
    }

    public void handleNodeClick(GraphNode node) {
        if (modeManager.getActiveToggleButton() == null) {
            // Default selection behavior
            if (selectedNode != null) selectedNode.deselect();
            selectedNode = node;
            canvas.updateArrowPosition(node);
            node.select();
            return;
        }

        String buttonText = modeManager.getActiveToggleButton().getText();

        switch (buttonText) {
            case "Add Edge":
                handleAddEdgeMode(node);
                break;
            case "Color Node":
                handleColorMode(node);
                break;
            case "Remove Node":
                handleRemoveNode(node);
                break;
        }
    }

    private void handleAddEdgeMode(GraphNode node) {
        if (selectedNode == null) {
            selectedNode = node;
            node.getCircle().setStroke(Color.BLUE);
            node.getCircle().setStrokeWidth(4);
        } else if (selectedNode != node) {
            canvas.addEdge(selectedNode, node);
            selectedNode.getCircle().setStroke(Color.BLACK);
            selectedNode.getCircle().setStrokeWidth(2);
            selectedNode = null;
        }
    }

    private void handleColorMode(GraphNode node) {
        ColorPicker picker = new ColorPicker((Color) node.getCircle().getFill());
        Popup popup = new Popup();
        popup.getContent().add(picker);
        popup.show(node.getCircle().getScene().getWindow(),
                node.getCircle().localToScreen(0, 0).getX(),
                node.getCircle().localToScreen(0, 0).getY() + 30);
        picker.setOnAction(ev -> {
            node.setColor(picker.getValue());
            popup.hide();
        });
        picker.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) popup.hide();
        });
    }

    private void handleRemoveNode(GraphNode node) {
        canvas.getPane().getChildren().removeAll(
                node.getCircle(), node.getLabel(), node.getArrow());

        // Remove all edges connected to this node
        List<GraphEdge> toRemove = new ArrayList<>();
        for (GraphEdge edge : model.getEdges()) {
            if (edge.getFrom() == node || edge.getTo() == node) {
                canvas.getPane().getChildren().removeAll(edge.getLine(), edge.getWeightLabel());
                toRemove.add(edge);
            }
        }
        for (GraphEdge edge : toRemove) {
            model.removeEdge(edge);
        }

        model.removeNode(node);
        if (selectedNode == node) selectedNode = null;
    }

    public GraphNode getSelectedNode() { return selectedNode; }
    public void setSelectedNode(GraphNode node) { this.selectedNode = node; }
}