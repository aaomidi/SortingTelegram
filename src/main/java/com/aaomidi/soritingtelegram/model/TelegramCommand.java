package com.aaomidi.soritingtelegram.model;

import com.aaomidi.soritingtelegram.SortingTelegram;
import lombok.Getter;
import pro.zackpollard.telegrambot.api.TelegramBot;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

/**
 * Created by amir on 2015-11-26.
 */
public abstract class TelegramCommand {
    @Getter
    private final SortingTelegram instance;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final String[] aliases;


    public TelegramCommand(SortingTelegram instance, String name, String description, String... aliases) {
        this.instance = instance;
        this.name = name;
        this.description = description;
        this.aliases = aliases;

        //Auto command register
        this.instance.getCommandHandler().registerCommand(this);
    }

    public abstract void execute(CommandMessageReceivedEvent event);

    public void listenToReply(TextMessageReceivedEvent event) {
        // Override pls
    }

    protected TelegramBot getTelegramBot() {
       return instance.getTelegramHook().getBot();

    }
}
