package edu.buet.shayan.algomania.sorting.implementation;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public List<SortStep> sort(List<Double> array) {
        List<SortStep> steps = new ArrayList<>();
        List<Double> arr = new ArrayList<>(array);

        for (int i = 1; i < arr.size(); i++) {
            double key = arr.get(i);
            int j = i - 1;

            steps.add(new SortStep(arr).setComparing(i));

            while (j >= 0 && arr.get(j) > key) {
                steps.add(new SortStep(arr).setComparing(j, j + 1));
                arr.set(j + 1, arr.get(j));
                steps.add(new SortStep(arr).setSwapping(j, j + 1));
                j--;
            }

            arr.set(j + 1, key);
            steps.add(new SortStep(arr));
        }

        for (int i = 0; i < arr.size(); i++) {
            steps.add(new SortStep(arr).setSorted(i));
        }

        return steps;
    }
}
