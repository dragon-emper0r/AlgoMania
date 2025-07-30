package edu.buet.shayan.algomania.sorting.implementation;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort implements SortingAlgorithm {
    private List<SortStep> steps;

    @Override
    public List<SortStep> sort(List<Double> array) {
        steps = new ArrayList<>();
        List<Double> arr = new ArrayList<>(array);
        quickSort(arr, 0, arr.size() - 1);

        for (int i = 0; i < arr.size(); i++) {
            steps.add(new SortStep(arr).setSorted(i));
        }

        return steps;
    }

    private void quickSort(List<Double> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(List<Double> arr, int low, int high) {
        double pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            steps.add(new SortStep(arr).setComparing(j, high));

            if (arr.get(j) < pivot) {
                i++;
                Collections.swap(arr, i, j);
                steps.add(new SortStep(arr).setSwapping(i, j));
            }
        }

        Collections.swap(arr, i + 1, high);
        steps.add(new SortStep(arr).setSwapping(i + 1, high));

        return i + 1;
    }
}