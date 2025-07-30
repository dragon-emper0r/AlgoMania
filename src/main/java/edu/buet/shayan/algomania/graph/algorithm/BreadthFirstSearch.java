package edu.buet.shayan.algomania.graph.algorithm;

//import edu.buet.shayan.graphvisualizerapp.model.GraphNode;
//import edu.buet.shayan.graphvisualizerapp.model.GraphEdge;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch extends AbstractGraphAlgorithm {
    @Override
    public String getName() {
        return "Breadth-First Search (BFS)";
    }

    @Override
    protected void runAlgorithm() throws InterruptedException {
        if (startNode == null) {
            sendMessage("No starting node selected");
            return;
        }

        sendMessage("Starting BFS from node " + startNode.getId());

        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(startNode);
        visitNode(startNode);

        while (!queue.isEmpty() && !stopped) {
            GraphNode current = queue.poll();
            List<GraphNode> neighbors = model.neighbors(current);

            for (GraphNode neighbor : neighbors) {
                if (!visitedSet.contains(neighbor)) {
                    GraphEdge connectingEdge = model.findEdge(current, neighbor);
                    if (connectingEdge != null) {
                        traverseEdge(connectingEdge);
                    }
                    visitNode(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        if (!stopped) {
            sendMessage("BFS completed - visited " + visitedNodes.size() + " nodes");
        }
    }

    @Override
    protected String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("BFS Traversal Order:\n");
        for (int i = 0; i < visitedNodes.size(); i++) {
            summary.append((i + 1)).append(". Node ").append(visitedNodes.get(i).getId()).append("\n");
        }
        summary.append("\nTotal nodes visited: ").append(visitedNodes.size());
        summary.append("\nTotal edges traversed: ").append(traversedEdges.size());
        return summary.toString();
    }
}