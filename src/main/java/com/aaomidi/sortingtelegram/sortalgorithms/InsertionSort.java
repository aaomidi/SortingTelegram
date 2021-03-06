package com.aaomidi.sortingtelegram.sortalgorithms;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.model.Sortable;

/**
 * Created by amir on 2015-12-21.
 */
public class InsertionSort extends Sortable {

    public InsertionSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }

    @Override
    public void innerSort(String[] data, int len) {
        int i, j, index;
        String temp;

        for (i = 0; i < len - 1 & addComparisonCount(1); i++) {

            index = i;
            for (j = i + 1; j < len && addComparisonCount(1); j++) {
                if (data[j].compareTo(data[index]) < 0 && addComparisonCount(1)) {
                    index = j;
                }
            }

            temp = data[index];
            data[index] = data[i];
            data[i] = temp;
            addSwapCount(2);
        }
    }
}
