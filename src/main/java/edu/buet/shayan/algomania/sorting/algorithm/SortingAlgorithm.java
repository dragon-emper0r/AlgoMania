package edu.buet.shayan.algomania.sorting.algorithm;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.List;

public interface SortingAlgorithm {
    List<SortStep> sort(List<Double> array);
}