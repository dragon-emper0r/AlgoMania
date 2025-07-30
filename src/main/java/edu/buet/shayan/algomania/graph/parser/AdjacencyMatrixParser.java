package edu.buet.shayan.algomania.graph.parser;

//import edu.buet.shayan.graphvisualizerapp.model.*;
//import edu.buet.shayan.graphvisualizerapp.ui.GraphCanvas;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.*;

public class AdjacencyMatrixParser implements GraphInputParser {
    private GraphModel model;
    private GraphCanvas canvas;

    public AdjacencyMatrixParser(GraphModel model, GraphCanvas canvas) {
        this.model = model;
        this.canvas = canvas;
    }

    @Override
    public boolean parse(String input) {
        try {
            String[] lines = input.strip().split("\n");
            int n = lines.length;

            // Create nodes in a circle
            double centerX = 400; // Default canvas center
            double centerY = 300;
            double radius = Math.min(centerX, centerY) * 0.7;

            List<GraphNode> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n;
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);
                GraphNode node = canvas.createNode(i, x, y);
                model.addNode(node);
                nodes.add(node);
            }

            // Create edges based on matrix
            for (int i = 0; i < n; i++) {
                String[] values = lines[i].trim().split("\\s+");
                if (values.length != n) return false;

                for (int j = 0; j < n; j++) {
                    int val = Integer.parseInt(values[j]);
                    if (val > 0) { // Support weighted adjacency matrices
                        canvas.addEdge(nodes.get(i), nodes.get(j));
                        if (val > 1) {
                            // Set weight if greater than 1
                            GraphEdge edge = model.findEdge(nodes.get(i), nodes.get(j));
                            if (edge != null) {
                                edge.setWeight(val);
                                canvas.updateWeightLabelPosition(edge);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}