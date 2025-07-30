package edu.buet.shayan.algomania.graph.parser;

//import edu.buet.shayan.graphvisualizerapp.model.*;
//import edu.buet.shayan.graphvisualizerapp.ui.GraphCanvas;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.*;

public class AdjacencyListParser implements GraphInputParser {
    private GraphModel model;
    private GraphCanvas canvas;

    public AdjacencyListParser(GraphModel model, GraphCanvas canvas) {
        this.model = model;
        this.canvas = canvas;
    }

    @Override
    public boolean parse(String input) {
        try {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            String[] lines = input.strip().split("\n");

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(":", 2);
                if (parts.length != 2) return false;

                int node = Integer.parseInt(parts[0].trim());
                List<Integer> neighbors = new ArrayList<>();
                if (!parts[1].trim().isEmpty()) {
                    String[] nbrStrs = parts[1].trim().split("\\s+");
                    for (String nbr : nbrStrs) {
                        neighbors.add(Integer.parseInt(nbr));
                    }
                }
                adj.put(node, neighbors);
            }

            Set<Integer> allNodes = new HashSet<>();
            for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
                allNodes.add(entry.getKey());
                allNodes.addAll(entry.getValue());
            }

            List<Integer> nodeList = new ArrayList<>(allNodes);
            Collections.sort(nodeList);

            double centerX = 400; // Default canvas center
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

            for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
                int fromId = entry.getKey();
                GraphNode fromNode = nodeMap.get(fromId);

                for (int toId : entry.getValue()) {
                    GraphNode toNode = nodeMap.get(toId);
                    if (fromNode != null && toNode != null) {
                        canvas.addEdge(fromNode, toNode);
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