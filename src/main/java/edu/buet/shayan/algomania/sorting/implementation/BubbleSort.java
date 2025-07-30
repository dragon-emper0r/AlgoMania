package edu.buet.shayan.algomania.sorting.implementation;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public List<SortStep> sort(List<Double> array) {
        List<SortStep> steps = new ArrayList<>();
        List<Double> arr = new ArrayList<>(array);

        for (int i = 0; i < arr.size() - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.size() - i - 1; j++) {
                steps.add(new SortStep(arr).setComparing(j, j + 1));

                if (arr.get(j) > arr.get(j + 1)) {
                    Collections.swap(arr, j, j + 1);
                    steps.add(new SortStep(arr).setSwapping(j, j + 1));
                    swapped = true;
                }
            }

            steps.add(new SortStep(arr).setSorted(arr.size() - i - 1));

            if (!swapped) break;
        }

        for (int i = 0; i < arr.size(); i++) {
            steps.add(new SortStep(arr).setSorted(i));
        }

        return steps;
    }
}