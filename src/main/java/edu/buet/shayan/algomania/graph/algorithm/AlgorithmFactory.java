package edu.buet.shayan.algomania.graph.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import edu.buet.shayan.algomania.graph.algorithm.*;
import edu.buet.shayan.algomania.graph.ui.*;
import edu.buet.shayan.algomania.graph.parser.*;
import edu.buet.shayan.algomania.graph.model.*;
import edu.buet.shayan.algomania.graph.interaction.*;

public class AlgorithmFactory {
    private static final Map<String, Class<? extends GraphAlgorithm>> algorithms = new HashMap<>();

    static {
        algorithms.put("BFS", BreadthFirstSearch.class);
        algorithms.put("DFS", DepthFirstSearch.class);
        algorithms.put("Dijkstra", DijkstraAlgorithm.class);
    }

    public static GraphAlgorithm createAlgorithm(String algorithmName) {
        Class<? extends GraphAlgorithm> algorithmClass = algorithms.get(algorithmName);
        if (algorithmClass == null) return null;

        try {
            return algorithmClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Set<String> getAvailableAlgorithms() {
        return algorithms.keySet();
    }

    public static boolean isAlgorithmAvailable(String algorithmName) {
        return algorithms.containsKey(algorithmName);
    }
}