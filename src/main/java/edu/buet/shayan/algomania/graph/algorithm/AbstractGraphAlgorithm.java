package edu.buet.shayan.algomania.graph.algorithm;

//import edu.buet.shayan.graphvisualizerapp.model.GraphModel;
//import edu.buet.shayan.graphvisualizerapp.model.GraphNode;
//import edu.buet.shayan.graphvisualizerapp.model.GraphEdge;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractGraphAlgorithm implements GraphAlgorithm {
    protected volatile boolean running = false;
    protected volatile boolean stopped = false;
    protected volatile boolean paused = false;
    protected GraphModel model;
    protected GraphNode startNode;
    protected AlgorithmListener listener;
    protected List<GraphNode> visitedNodes;
    protected List<GraphEdge> traversedEdges;
    protected Set<GraphNode> visitedSet;
    protected long startTime;
    protected int stepCounter;
    protected volatile int animationDelay = 1000; // milliseconds

    @Override
    public void execute(GraphModel model, GraphNode startNode, AlgorithmListener listener) {
        if (running) {
            throw new IllegalStateException("Algorithm is already running");
        }

        this.model = model;
        this.startNode = startNode;
        this.listener = listener;
        this.visitedNodes = new ArrayList<>();
        this.traversedEdges = new ArrayList<>();
        this.visitedSet = new HashSet<>();
        this.stepCounter = 0;
        this.stopped = false;
        this.paused = false;

        resetGraphVisualization();

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    running = true;
                    startTime = System.currentTimeMillis();
                    Platform.runLater(() -> listener.onAlgorithmStart());

                    runAlgorithm();

                    if (!stopped) {
                        long executionTime = System.currentTimeMillis() - startTime;
                        AlgorithmResult result = new AlgorithmResult(
                                getName(), visitedNodes, traversedEdges,
                                executionTime, generateSummary(), true
                        );
                        Platform.runLater(() -> listener.onAlgorithmComplete(result));
                    }
                } catch (InterruptedException e) {
                    Platform.runLater(() -> listener.onAlgorithmStopped());
                } finally {
                    running = false;
                }
                return null;
            }
        };

        Thread algorithmThread = new Thread(task);
        algorithmThread.setDaemon(true);
        algorithmThread.start();
    }

    @Override
    public void stop() {
        stopped = true;
        running = false;
        paused = false;
    }

    @Override
    public void pause() {
        paused = true;
        Platform.runLater(() -> listener.onAlgorithmPaused());
    }

    @Override
    public void resume() {
        paused = false;
        Platform.runLater(() -> listener.onAlgorithmResumed());
    }

    @Override
    public boolean isRunning() { return running; }

    @Override
    public boolean isPaused() { return paused; }

    public void setAnimationDelay(int delay) {
        this.animationDelay = Math.max(100, delay); // Minimum 100ms
    }

    protected abstract void runAlgorithm() throws InterruptedException;
    protected abstract String generateSummary();

    protected void resetGraphVisualization() {
        Platform.runLater(() -> {
            for (GraphNode node : model.getNodes()) {
                node.resetColor();
                node.setVisited(false);
                node.deselect();
                node.setDistance(Integer.MAX_VALUE);
            }
            for (GraphEdge edge : model.getEdges()) {
                edge.resetColor();
                edge.setTraversed(false);
            }
        });
    }

    protected void visitNode(GraphNode node) throws InterruptedException {
        checkPauseAndStop();
        visitedNodes.add(node);
        visitedSet.add(node);
        stepCounter++;

        Platform.runLater(() -> {
            node.setVisited(true);
            listener.onNodeVisited(node, stepCounter);
        });

        Thread.sleep(animationDelay);
    }

    protected void traverseEdge(GraphEdge edge) throws InterruptedException {
        checkPauseAndStop();
        traversedEdges.add(edge);
        stepCounter++;

        Platform.runLater(() -> {
            edge.setTraversed(true);
            listener.onEdgeTraversed(edge, stepCounter);
        });

        Thread.sleep(animationDelay);
    }

    protected void checkPauseAndStop() throws InterruptedException {
        if (stopped) throw new InterruptedException("Algorithm stopped");

        while (paused && !stopped) {
            Thread.sleep(100);
        }

        if (stopped) throw new InterruptedException("Algorithm stopped");
    }

    protected void sendMessage(String message) {
        Platform.runLater(() -> listener.onMessage(message));
    }
}
