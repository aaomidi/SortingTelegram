package com.aaomidi.sortingtelegram.sortalgorithms;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.model.Sortable;

/**
 * Created by amir on 2015-12-21.
 */

public class BubbleSort extends Sortable {

    public BubbleSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }

    @Override
    public void innerSort(String[] data, int len) {
        int i, j = 0;
        String temp;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            j++;

            for (i = 0; i < len - j; i++) {
                if (data[i].compareTo(data[i + 1]) > 0 && addComparisonCount(1)) {
                    temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    swapped = true;
                    addSwapCount(2);
                }
            }
        }
    }
}
