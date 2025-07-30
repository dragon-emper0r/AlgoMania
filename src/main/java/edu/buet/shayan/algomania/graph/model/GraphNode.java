package edu.buet.shayan.algomania.graph.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class GraphNode {
    private final int id;
    private Label label;
    private Circle circle;
    private Polygon arrow;
    private Color initialColor = Color.LIGHTBLUE;
    private Color currentColor = Color.LIGHTBLUE;
    private boolean isVisited = false;
    private boolean isSelected = false;
    private int distance = Integer.MAX_VALUE;

    public GraphNode(int id, Label label, Circle circle) {
        this.id = id;
        this.label = label;
        this.circle = circle;
        this.arrow = new Polygon(0.0, 0.0, 10.0, 20.0, -10.0, 20.0);
        this.arrow.setFill(Color.DARKBLUE);
        arrow.setVisible(false);
        circle.setFill(initialColor);
    }

    public int getId() { return id; }
    public Label getLabel() { return label; }
    public Circle getCircle() { return circle; }
    public Polygon getArrow() { return arrow; }
    public boolean isVisited() { return isVisited; }
    public boolean isSelected() { return isSelected; }
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public void setColor(Color color) {
        currentColor = color;
        circle.setFill(currentColor);
    }

    public void resetColor() {
        setColor(initialColor);
    }

    public void select() {
        isSelected = true;
        arrow.setVisible(true);
    }

    public void deselect() {
        isSelected = false;
        arrow.setVisible(false);
    }

    public void setVisited(boolean visited) {
        if (visited) {
            isVisited = true;
            setColor(Color.LIGHTGREEN);
        } else {
            isVisited = false;
            resetColor();
        }
    }

    public void setArrowPosition(double x, double y) {
        arrow.setLayoutX(x);
        arrow.setLayoutY(y);
    }

    public void updateLabelPosition(double x, double y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    @Override
    public String toString() {
        return String.format("Node(id=%d)", id);
    }
}
