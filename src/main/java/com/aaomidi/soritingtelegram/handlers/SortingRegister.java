package com.aaomidi.soritingtelegram.handlers;

import com.aaomidi.soritingtelegram.SortingTelegram;
import com.aaomidi.soritingtelegram.model.Sortable;
import com.aaomidi.soritingtelegram.sortalgorithms.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.chat.Chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amir on 2015-12-21.
 */
@RequiredArgsConstructor
public class SortingRegister {
    private final SortingTelegram instance;
    @Getter
    private final List<Sortable> sortables = new ArrayList<>();

    public void registerSorting(Sortable sortable) {
        sortables.add(sortable);
    }

    public void registerSortings() {
        new BubbleSort("Bubble Sort", instance);
        new InsertionSort("Insertion Sort", instance);
        new MergeSort("Merge Sort", instance);
        new QuickSort("Quick Sort", instance);
        new SelectionSort("Selection Sort", instance);
    }

    public void sort(String[] strings, int len, TelegramBot bot, Chat chat) {
        for (Sortable s : sortables) {
            chat.sendMessage(s.sort(strings, len), bot);
        }
    }
}
