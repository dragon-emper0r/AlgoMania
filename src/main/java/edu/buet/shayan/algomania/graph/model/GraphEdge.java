package edu.buet.shayan.algomania.graph.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GraphEdge {
    private GraphNode from;
    private GraphNode to;
    private Line line;
    private Label weightLabel;
    private Color initialColor = Color.BLACK;
    private Color currentColor = Color.BLACK;
    private boolean isDirected = false;
    private boolean isTraversed = false;
    private int weight = 1;

    public GraphEdge(GraphNode from, GraphNode to, Line line, int weight) {
        this.from = from;
        this.to = to;
        this.line = line;
        this.weight = weight;
        this.weightLabel = new Label(String.valueOf(weight));

        Font weightLabelFont = Font.font("Arial", FontWeight.BOLD, 13);
        weightLabel.setFont(weightLabelFont);
        weightLabel.setTextFill(Color.BLACK);
        weightLabel.setVisible(weight > 0);

        line.setStroke(initialColor);
        line.setStrokeWidth(2);
    }

    public GraphEdge(GraphNode from, GraphNode to, Line line) {
        this(from, to, line, 1);
        weightLabel.setVisible(false);
    }

    public GraphNode getFrom() { return from; }
    public GraphNode getTo() { return to; }
    public Line getLine() { return line; }
    public Label getWeightLabel() { return weightLabel; }
    public int getWeight() { return weight; }
    public boolean isDirected() { return isDirected; }
    public boolean isTraversed() { return isTraversed; }

    public void setWeight(int weight) {
        this.weight = weight;
        weightLabel.setText(String.valueOf(weight));
        weightLabel.setVisible(weight > 0);
    }

    public void setDirected(boolean directed) { this.isDirected = directed; }

    public void setTraversed(boolean traversed) {
        this.isTraversed = traversed;
        if (traversed) {
            setColor(Color.RED);
            line.setStrokeWidth(4);
        } else {
            resetColor();
            line.setStrokeWidth(2);
        }
    }

    public void setColor(Color color) {
        currentColor = color;
        line.setStroke(currentColor);
    }

    public void resetColor() {
        setColor(initialColor);
        line.setStrokeWidth(2);
    }

    public void updateWeightPosition(double x, double y) {
        weightLabel.setLayoutX(x);
        weightLabel.setLayoutY(y);
    }

    @Override
    public String toString() {
        return String.format("Edge(%s -> %s, weight=%d)", from.getId(), to.getId(), weight);
    }
}