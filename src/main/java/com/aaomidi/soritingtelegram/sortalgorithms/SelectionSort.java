package com.aaomidi.soritingtelegram.sortalgorithms;

import com.aaomidi.soritingtelegram.SortingTelegram;
import com.aaomidi.soritingtelegram.model.Sortable;
import lombok.RequiredArgsConstructor;

/**
 * Created by amir on 2015-12-21.
 */
public class SelectionSort extends Sortable {


    public SelectionSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }

    @Override
    public void innerSort(String[] data, int len) {
        int i, j, index;
        String temp;

        for (i = 0; i < len - 1 & addComparisonCount(1); i++) {

            index = i;
            for (j = i + 1; j < data.length && addComparisonCount(1); j++) {
                if (data[j].compareTo(data[index]) > 0 && addComparisonCount(1)) {
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
