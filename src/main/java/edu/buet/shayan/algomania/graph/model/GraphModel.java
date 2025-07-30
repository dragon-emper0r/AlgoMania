package edu.buet.shayan.algomania.graph.model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.*;

public class GraphModel {
    private final List<GraphNode> nodes = new ArrayList<>();
    private final List<GraphEdge> edges = new ArrayList<>();
    private final Map<Circle, GraphNode> circleToNode = new HashMap<>();
    private final Map<Line, GraphEdge> lineToEdge = new HashMap<>();

    public List<GraphNode> getNodes() { return nodes; }
    public List<GraphEdge> getEdges() { return edges; }
    public Map<Circle, GraphNode> getCircleToNode() { return circleToNode; }
    public Map<Line, GraphEdge> getLineToEdge() { return lineToEdge; }

    public GraphNode getNode(Circle circle) { return circleToNode.get(circle); }
    public GraphEdge getEdge(Line line) { return lineToEdge.get(line); }

    public void addNode(GraphNode node) {
        nodes.add(node);
        circleToNode.put(node.getCircle(), node);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
        lineToEdge.put(edge.getLine(), edge);
    }

    public void removeNode(GraphNode node) {
        nodes.remove(node);
        circleToNode.remove(node.getCircle());
    }

    public void removeEdge(GraphEdge edge) {
        edges.remove(edge);
        lineToEdge.remove(edge.getLine());
    }

    public void clear() {
        nodes.clear();
        edges.clear();
        lineToEdge.clear();
        circleToNode.clear();
    }

    public List<GraphNode> neighbors(GraphNode node) {
        List<GraphNode> neighbors = new ArrayList<>();
        for (GraphEdge edge : edges) {
            if (edge.getFrom() == node) neighbors.add(edge.getTo());
            if (!edge.isDirected() && edge.getTo() == node) neighbors.add(edge.getFrom());
        }
        return neighbors;
    }

    public List<GraphEdge> getNodeEdges(GraphNode node) {
        List<GraphEdge> nodeEdges = new ArrayList<>();
        for (GraphEdge edge : edges) {
            if (edge.getFrom() == node) nodeEdges.add(edge);
            if (!edge.isDirected() && edge.getTo() == node) nodeEdges.add(edge);
        }
        return nodeEdges;
    }

    public GraphEdge findEdge(GraphNode from, GraphNode to) {
        for (GraphEdge edge : edges) {
            if ((edge.getFrom() == from && edge.getTo() == to) ||
                    (!edge.isDirected() && edge.getFrom() == to && edge.getTo() == from)) {
                return edge;
            }
        }
        return null;
    }
}
