package edu.buet.shayan.algomania.graph.algorithm;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import java.util.*;

public class DijkstraAlgorithm extends AbstractGraphAlgorithm {
    private Map<GraphNode, Integer> distances;
    private Map<GraphNode, GraphNode> previous;
    private PriorityQueue<GraphNode> pq;

    @Override
    public String getName() {
        return "Dijkstra's Shortest Path";
    }

    @Override
    protected void runAlgorithm() throws InterruptedException {
        if (startNode == null) {
            sendMessage("No starting node selected");
            return;
        }

        sendMessage("Starting Dijkstra's algorithm from node " + startNode.getId());

        distances = new HashMap<>();
        previous = new HashMap<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (GraphNode node : model.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }

        distances.put(startNode, 0);
        startNode.setDistance(0);
        pq.offer(startNode);

        visitNode(startNode);
        updateDistanceLabels();

        while (!pq.isEmpty() && !stopped) {
            GraphNode current = pq.poll();

            if (distances.get(current) == Integer.MAX_VALUE) break;

            List<GraphEdge> edges = model.getNodeEdges(current);
            for (GraphEdge edge : edges) {
                GraphNode neighbor = (edge.getFrom() == current) ? edge.getTo() :
                        (!edge.isDirected() && edge.getTo() == current) ? edge.getFrom() : null;

                if (neighbor == null) continue;

                traverseEdge(edge);

                int newDistance = distances.get(current) + edge.getWeight();

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    neighbor.setDistance(newDistance);
                    previous.put(neighbor, current);

                    if (!visitedSet.contains(neighbor)) {
                        visitNode(neighbor);
                        pq.offer(neighbor);
                    }

                    updateDistanceLabels();

                    // Highlight the relaxed edge
                    Platform.runLater(() -> edge.setColor(Color.BLUE));
                    Thread.sleep(animationDelay / 2);
                    Platform.runLater(() -> edge.resetColor());
                }
            }
        }

        if (!stopped) {
            sendMessage("Dijkstra's algorithm completed");
            highlightShortestPaths();
        }
    }

    private void updateDistanceLabels() {
        Platform.runLater(() -> {
            for (GraphNode node : model.getNodes()) {
                int dist = distances.get(node);
                String distText = (dist == Integer.MAX_VALUE) ? "∞" : String.valueOf(dist);
                node.getLabel().setText(node.getId() + "(" + distText + ")");
            }
        });
    }

    private void highlightShortestPaths() throws InterruptedException {
        sendMessage("Highlighting shortest paths...");

        for (GraphNode node : model.getNodes()) {
            if (node != startNode && distances.get(node) != Integer.MAX_VALUE) {
                GraphNode current = node;
                List<GraphEdge> pathEdges = new ArrayList<>();

                while (previous.get(current) != null) {
                    GraphNode prev = previous.get(current);
                    GraphEdge edge = model.findEdge(prev, current);
                    if (edge != null) {
                        pathEdges.add(edge);
                    }
                    current = prev;
                }

                // Highlight path edges
                for (GraphEdge edge : pathEdges) {
                    Platform.runLater(() -> edge.setColor(Color.GREEN));
                    Thread.sleep(animationDelay / 4);
                }
            }
        }
    }

    @Override
    protected String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Dijkstra's Algorithm Results:\n");
        summary.append("Shortest distances from node ").append(startNode.getId()).append(":\n\n");

        List<GraphNode> sortedNodes = new ArrayList<>(model.getNodes());
        sortedNodes.sort(Comparator.comparingInt(GraphNode::getId));

        for (GraphNode node : sortedNodes) {
            int dist = distances.get(node);
            String distText = (dist == Integer.MAX_VALUE) ? "∞" : String.valueOf(dist);
            summary.append("Node ").append(node.getId()).append(": ").append(distText);

            if (dist != Integer.MAX_VALUE && node != startNode) {
                summary.append(" (Path: ");
                List<Integer> path = new ArrayList<>();
                GraphNode current = node;
                while (current != null) {
                    path.add(current.getId());
                    current = previous.get(current);
                }
                Collections.reverse(path);
                summary.append(String.join(" → ", path.stream().map(String::valueOf).toArray(String[]::new)));
                summary.append(")");
            }
            summary.append("\n");
        }

        summary.append("\nTotal nodes visited: ").append(visitedNodes.size());
        summary.append("\nTotal edges examined: ").append(traversedEdges.size());
        return summary.toString();
    }
}