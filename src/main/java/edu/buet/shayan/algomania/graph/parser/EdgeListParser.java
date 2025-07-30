package edu.buet.shayan.algomania.graph.parser;

//mport edu.buet.shayan.graphvisualizerapp.model.*;
//import edu.buet.shayan.graphvisualizerapp.ui.GraphCanvas;i
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.*;

public class EdgeListParser implements GraphInputParser {
    private GraphModel model;
    private GraphCanvas canvas;

    public EdgeListParser(GraphModel model, GraphCanvas canvas) {
        this.model = model;
        this.canvas = canvas;
    }

    @Override
    public boolean parse(String input) {
        try {
            String[] lines = input.strip().split("\n");
            Set<Integer> allNodes = new HashSet<>();
            List<int[]> edges = new ArrayList<>();

            // Parse all edges and collect unique nodes
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length < 2) return false;

                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                int weight = 1; // Default weight

                //Check if weight is provided;
                if (parts.length >= 3) {
                    weight = Integer.parseInt(parts[2]);
                }

                allNodes.add(from);
                allNodes.add(to);
                edges.add(new int[]{from, to, weight});
            }

            List<Integer> nodeList = new ArrayList<>(allNodes);
            Collections.sort(nodeList);

            double centerX = 400;
            double centerY = 300;
            double radius = Math.min(centerX, centerY) * 0.7;

            Map<Integer, GraphNode> nodeMap = new HashMap<>();

            for (int i = 0; i < nodeList.size(); i++) {
                int nodeId = nodeList.get(i);
                double angle = 2 * Math.PI * i / nodeList.size();
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);

                GraphNode node = canvas.createNode(nodeId, x, y);
                model.addNode(node);
                nodeMap.put(nodeId, node);
            }

            for (int[] edge : edges) {
                int fromId = edge[0];
                int toId = edge[1];
                int weight = edge[2];

                GraphNode fromNode = nodeMap.get(fromId);
                GraphNode toNode = nodeMap.get(toId);

                if (fromNode != null && toNode != null) {
                    canvas.addEdge(fromNode, toNode);
                    // Set weight if it's not the default
                    if (weight != 1) {
                        GraphEdge graphEdge = model.findEdge(fromNode, toNode);
                        if (graphEdge != null) {
                            graphEdge.setWeight(weight);
                            canvas.updateWeightLabelPosition(graphEdge);
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