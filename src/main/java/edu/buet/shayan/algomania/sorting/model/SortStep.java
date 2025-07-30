package edu.buet.shayan.algomania.sorting.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortStep {
    private List<Double> array;
    private List<Integer> comparing;
    private List<Integer> swapping;
    private List<Integer> sorted;

    public SortStep(List<Double> array) {
        this.array = new ArrayList<>(array);
    }

    public SortStep setComparing(int... indices) {
        this.comparing = Arrays.stream(indices).boxed()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return this;
    }

    public SortStep setSwapping(int i, int j) {
        this.swapping = Arrays.asList(i, j);
        return this;
    }

    public SortStep setSorted(int... indices) {
        this.sorted = Arrays.stream(indices).boxed()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return this;
    }

    // Getters
    public List<Double> getArray() { return array; }
    public List<Integer> getComparing() { return comparing; }
    public List<Integer> getSwapping() { return swapping; }
    public List<Integer> getSorted() { return sorted; }
}