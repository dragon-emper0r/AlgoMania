package edu.buet.shayan.algomania.sorting.algorithm;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;


public class SortingAlgorithmFactory {
    public static SortingAlgorithm createAlgorithm(String algorithmName) {
        switch (algorithmName) {
            case "Selection Sort": return new SelectionSort();
            case "Bubble Sort": return new BubbleSort();
            case "Insertion Sort": return new InsertionSort();
            case "Merge Sort": return new MergeSort();
            case "Quick Sort": return new QuickSort();
            default: return new SelectionSort();
        }
    }
}