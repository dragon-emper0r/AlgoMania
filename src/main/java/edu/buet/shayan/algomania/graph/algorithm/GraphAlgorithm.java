package edu.buet.shayan.algomania.graph.algorithm;

import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;

public interface GraphAlgorithm {
    void execute(GraphModel model, GraphNode startNode, AlgorithmListener listener);
    String getName();
    void stop();
    void pause();
    void resume();
    boolean isRunning();
    boolean isPaused();
}