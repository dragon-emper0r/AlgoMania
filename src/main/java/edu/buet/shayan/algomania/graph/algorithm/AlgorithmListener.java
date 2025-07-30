package edu.buet.shayan.algomania.graph.algorithm;

//
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;

public interface AlgorithmListener {
    void onNodeVisited(GraphNode node, int step);
    void onEdgeTraversed(GraphEdge edge, int step);
    void onAlgorithmStart();
    void onAlgorithmComplete(AlgorithmResult result);
    void onAlgorithmStopped();
    void onAlgorithmPaused();
    void onAlgorithmResumed();
    void onMessage(String message);
}