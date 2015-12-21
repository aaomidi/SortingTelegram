package com.aaomidi.sortingtelegram.sortalgorithms;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.model.Sortable;

/**
 * Created by amir on 2015-12-21.
 */
public class QuickSort extends Sortable {
    private transient String[] arr;

    public QuickSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }


    @Override
    public void innerSort(String[] data, int len) {
        this.arr = data;
        quickSort(0, len - 1);

    }

    private void quickSort(int left, int right) {
        if (left >= right && addComparisonCount(1))
            return;

        int j = left;
        String pivot = arr[left];
        for (int i = j + 1; i <= right && addComparisonCount(1); i++) {
            if (arr[i].compareTo(pivot) < 0 && addComparisonCount(1)) {
                j++;
                String temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                addSwapCount(2);
            }
        }
        if (left != j && addComparisonCount(1)) {
            String temp = arr[j];
            arr[j] = arr[left];
            arr[left] = temp;
            addSwapCount(2);
        }
        quickSort(left, j - 1);
        quickSort(j + 1, right);
    }
}
