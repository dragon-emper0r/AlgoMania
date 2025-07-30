package edu.buet.shayan.algomania.sorting.implementation;

import edu.buet.shayan.algomania.sorting.algorithm.*;
import edu.buet.shayan.algomania.sorting.model.*;
import edu.buet.shayan.algomania.sorting.ui.*;
import edu.buet.shayan.algomania.sorting.controller.*;
import edu.buet.shayan.algomania.sorting.implementation.*;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortingAlgorithm {
    private List<SortStep> steps;

    @Override
    public List<SortStep> sort(List<Double> array) {
        steps = new ArrayList<>();
        List<Double> arr = new ArrayList<>(array);
        mergeSort(arr, 0, arr.size() - 1);

        for (int i = 0; i < arr.size(); i++) {
            steps.add(new SortStep(arr).setSorted(i));
        }

        return steps;
    }

    private void mergeSort(List<Double> arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(List<Double> arr, int left, int mid, int right) {
        List<Double> leftArray = new ArrayList<>(arr.subList(left, mid + 1));
        List<Double> rightArray = new ArrayList<>(arr.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftArray.size() && j < rightArray.size()) {
            steps.add(new SortStep(arr).setComparing(left + i, mid + 1 + j));

            if (leftArray.get(i) <= rightArray.get(j)) {
                arr.set(k, leftArray.get(i));
                i++;
            } else {
                arr.set(k, rightArray.get(j));
                j++;
            }
            steps.add(new SortStep(arr));
            k++;
        }

        while (i < leftArray.size()) {
            arr.set(k, leftArray.get(i));
            steps.add(new SortStep(arr));
            i++;
            k++;
        }

        while (j < rightArray.size()) {
            arr.set(k, rightArray.get(j));
            steps.add(new SortStep(arr));
            j++;
            k++;
        }
    }
}