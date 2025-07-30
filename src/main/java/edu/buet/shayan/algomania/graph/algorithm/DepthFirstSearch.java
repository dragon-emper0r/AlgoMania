package edu.buet.shayan.algomania.graph.algorithm;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.List;

public class DepthFirstSearch extends AbstractGraphAlgorithm {
    @Override
    public String getName() {
        return "Depth-First Search (DFS)";
    }

    @Override
    protected void runAlgorithm() throws InterruptedException {
        if (startNode == null) {
            sendMessage("No starting node selected");
            return;
        }

        sendMessage("Starting DFS from node " + startNode.getId());
        dfsRecursive(startNode);

        if (!stopped) {
            sendMessage("DFS completed - visited " + visitedNodes.size() + " nodes");
        }
    }

    private void dfsRecursive(GraphNode node) throws InterruptedException {
        if (stopped || visitedSet.contains(node)) {
            return;
        }

        visitNode(node);
        List<GraphNode> neighbors = model.neighbors(node);

        for (GraphNode neighbor : neighbors) {
            if (!visitedSet.contains(neighbor)) {
                GraphEdge connectingEdge = model.findEdge(node, neighbor);
                if (connectingEdge != null) {
                    traverseEdge(connectingEdge);
                }
                dfsRecursive(neighbor);
            }
        }
    }

    @Override
    protected String generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("DFS Traversal Order:\n");
        for (int i = 0; i < visitedNodes.size(); i++) {
            summary.append((i + 1)).append(". Node ").append(visitedNodes.get(i).getId()).append("\n");
        }
        summary.append("\nTotal nodes visited: ").append(visitedNodes.size());
        summary.append("\nTotal edges traversed: ").append(traversedEdges.size());
        return summary.toString();
    }
}
