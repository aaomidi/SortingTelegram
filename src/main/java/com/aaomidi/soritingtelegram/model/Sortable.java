package com.aaomidi.soritingtelegram.model;

import com.aaomidi.soritingtelegram.SortingTelegram;

/**
 * Created by amir on 2015-12-21.
 */
public abstract class Sortable implements Sort {
    private final String algorithmName;
    private final SortingTelegram instance;
    private long swapCount = 0;
    private long comparisonCount = 0;
    private long startTime;
    private long endTime;

    public Sortable(String algorithmName, SortingTelegram instance) {
        this.algorithmName = algorithmName;
        this.instance = instance;

        instance.getSortingRegister().registerSorting(this);
    }

    public String sort(String[] array, int len) {
        swapCount = 0;
        comparisonCount = 0;
        StringBuilder sb = new StringBuilder(this.sayStartingString());
        this.innerSort(array, len);
        sb.append(this.sayEndingString());

        return sb.toString();
    }

    private String sayStartingString() {
        startTime = System.currentTimeMillis();
        return String.format("Starting %s\n\tTime is: %d miliseconds", algorithmName, startTime);
    }

    private String sayEndingString() {
        StringBuilder sb = new StringBuilder();
        endTime = System.currentTimeMillis();
        sb
                .append(String.format("\n\tEnding %s\n\tTime is: %d", algorithmName, endTime))
                .append(String.format("\n\tStats for %s:\n\tTime Taken: %d miliseconds\n\tSwap Count: %d\n\tComparison Count: %d", algorithmName, endTime - startTime, swapCount, comparisonCount));
        return sb.toString();
    }

    @Override
    public abstract void innerSort(String[] data, int len);

    protected void addSwapCount(int swaps) {
        swapCount += swaps;
    }

    protected boolean addComparisonCount(int comparisons) {
        comparisonCount += comparisons;
        return true;
    }

    public void reset() {
        swapCount = 0;
        comparisonCount = 0;
    }
}

