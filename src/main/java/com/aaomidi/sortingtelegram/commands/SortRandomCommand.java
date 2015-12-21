package com.aaomidi.sortingtelegram.commands;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.model.TelegramCommand;
import com.aaomidi.sortingtelegram.util.IntegerConverter;
import com.aaomidi.sortingtelegram.util.StringManager;
import pro.zackpollard.telegrambot.api.chat.Chat;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

import java.util.Random;

/**
 * Created by amir on 2015-12-21.
 */
public class SortRandomCommand extends TelegramCommand {
    public SortRandomCommand(SortingTelegram instance, String name, String description, String... aliases) {
        super(instance, name, description, aliases);
    }

    @Override
    public void execute(CommandMessageReceivedEvent event) {
        Chat chat = event.getChat();
        Integer size = null;
        if (event.getArgs().length != 0) {
            size = IntegerConverter.fromString(event.getArgs()[0]);
        }

        if (size == null) {
            chat.sendMessage("Since no input was entered. We're going to assume 1000.", getTelegramBot());
            size = 1000;
        }

        if (size <= 0) {
            chat.sendMessage("Since the input was below 0. We're going to assume 1000.", getTelegramBot());
            size = 1000;
        }

        if (size > 100000) {
            chat.sendMessage("Since the input was above 100000(the limit). We're going to assume 1000.", getTelegramBot());
            size = 1000;
        }

        String[] strings = new String[size];
        event.getChat().sendMessage("Started sorting using " + size + " data points!", getTelegramBot());
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            String generated = StringManager.generateString(random.nextInt(10));
            strings[i] = generated;
        }

        getInstance().getSortingRegister().sort(strings, size, getTelegramBot(), event.getChat());
    }
}
