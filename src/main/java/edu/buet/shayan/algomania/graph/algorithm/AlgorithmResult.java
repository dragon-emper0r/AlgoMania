package edu.buet.shayan.algomania.graph.algorithm;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import java.util.List;
import java.util.ArrayList;

public class AlgorithmResult {
    private final String algorithmName;
    private final List<GraphNode> visitedNodes;
    private final List<GraphEdge> traversedEdges;
    private final long executionTime;
    private final String summary;
    private final boolean completed;

    public AlgorithmResult(String algorithmName, List<GraphNode> visitedNodes,
                           List<GraphEdge> traversedEdges, long executionTime,
                           String summary, boolean completed) {
        this.algorithmName = algorithmName;
        this.visitedNodes = new ArrayList<>(visitedNodes);
        this.traversedEdges = new ArrayList<>(traversedEdges);
        this.executionTime = executionTime;
        this.summary = summary;
        this.completed = completed;
    }

    public String getAlgorithmName() { return algorithmName; }
    public List<GraphNode> getVisitedNodes() { return new ArrayList<>(visitedNodes); }
    public List<GraphEdge> getTraversedEdges() { return new ArrayList<>(traversedEdges); }
    public long getExecutionTime() { return executionTime; }
    public String getSummary() { return summary; }
    public boolean isCompleted() { return completed; }

    @Override
    public String toString() {
        return String.format("%s: %d nodes visited, %d edges traversed, %dms",
                algorithmName, visitedNodes.size(), traversedEdges.size(), executionTime);
    }
}