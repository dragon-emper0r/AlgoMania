package edu.buet.shayan.algomania.graph.interaction;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import java.util.Optional;

public class EdgeInteractionHandler {
    private GraphModel model;
    private GraphCanvas canvas;
    private ModeManager modeManager;

    public EdgeInteractionHandler(GraphModel model, GraphCanvas canvas, ModeManager modeManager) {
        this.model = model;
        this.canvas = canvas;
        this.modeManager = modeManager;
    }

    public void handleEdgeClick(GraphEdge edge) {
        if (modeManager.getActiveToggleButton() == null) return;

        String buttonText = modeManager.getActiveToggleButton().getText();

        switch (buttonText) {
            case "Assign Weight":
                showWeightDialog(edge);
                break;
            case "Remove Edge":
                handleRemoveEdge(edge);
                break;
        }
    }

    private void showWeightDialog(GraphEdge edge) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(edge.getWeight()));
        dialog.setTitle("Assign Weight");
        dialog.setHeaderText("Enter weight for edge " + edge.getFrom().getId() + " → " + edge.getTo().getId());
        dialog.setContentText("Weight:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                int weight = Integer.parseInt(input.trim());
                if (weight < 0) {
                    showWarning("Negative weights not allowed for this implementation");
                    return;
                }
                edge.setWeight(weight);
                canvas.updateWeightLabelPosition(edge);
            } catch (NumberFormatException e) {
                showWarning("Invalid input. Please enter a valid number.");
            }
        });
    }

    private void handleRemoveEdge(GraphEdge edge) {
        canvas.getPane().getChildren().removeAll(edge.getLine(), edge.getWeightLabel());
        model.removeEdge(edge);
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}