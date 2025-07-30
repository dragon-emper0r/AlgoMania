package edu.buet.shayan.algomania.sorting.implementation;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectionSort implements SortingAlgorithm {
    @Override
    public List<SortStep> sort(List<Double> array) {
        List<SortStep> steps = new ArrayList<>();
        List<Double> arr = new ArrayList<>(array);

        for (int i = 0; i < arr.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.size(); j++) {
                steps.add(new SortStep(arr).setComparing(minIndex, j));

                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Collections.swap(arr, i, minIndex);
                steps.add(new SortStep(arr).setSwapping(i, minIndex));
            }

            steps.add(new SortStep(arr).setSorted(i));
        }

        steps.add(new SortStep(arr).setSorted(arr.size() - 1));
        return steps;
    }
}