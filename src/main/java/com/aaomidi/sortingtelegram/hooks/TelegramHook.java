package com.aaomidi.sortingtelegram.hooks;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.util.StringManager;
import lombok.Getter;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

/**
 * Created by amir on 2015-11-26.
 */
public class TelegramHook implements Listener {
    private final SortingTelegram instance;
    @Getter
    private TelegramBot bot;

    public TelegramHook(SortingTelegram instance, String auth) {
        this.instance = instance;

        bot = TelegramBot.login(auth);

        bot.getEventsManager().register(this);

        bot.startUpdates(false);

    }

    @Override
    public void onCommandMessageReceived(CommandMessageReceivedEvent event) {
        StringManager.logn("Command received from %s: %s", event.getMessage().getSender().getFullName(), event.getContent().getContent());

        instance.getCommandHandler().handleCommand(event);
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {


    }

}
