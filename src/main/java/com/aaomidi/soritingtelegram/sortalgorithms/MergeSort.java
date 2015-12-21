package com.aaomidi.soritingtelegram.sortalgorithms;

import com.aaomidi.soritingtelegram.SortingTelegram;
import com.aaomidi.soritingtelegram.model.Sortable;

/**
 * Created by amir on 2015-12-21.
 */
public class MergeSort extends Sortable {
    private String[] strings;
    private String[] helper;

    private int len;

    public MergeSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }


    @Override
    public void innerSort(String[] data, int len) {
        this.strings = data;
        this.helper = new String[len];

        mergeSort(0, len - 1);
    }

    private void mergeSort(int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    private void merge(int low, int mid, int high) {
        for (int i = low; i <= high && addComparisonCount(1); i++) {
            helper[i] = strings[i];
            addSwapCount(1);
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high && addComparisonCount(2)) {
            if (helper[i].compareTo(helper[j]) <= 0) {
                strings[k] = helper[i];
                addSwapCount(1);
                i++;
            } else {
                strings[k] = helper[j];
                addSwapCount(1);
                j++;
            }
            k++;
        }

        while (i <= mid && addComparisonCount(1)) {
            strings[k] = helper[i];
            addSwapCount(1);
            k++;
            i++;
        }
    }
}
