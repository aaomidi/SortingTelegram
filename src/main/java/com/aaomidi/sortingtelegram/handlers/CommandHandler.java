package com.aaomidi.sortingtelegram.handlers;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.commands.SortRandomCommand;
import com.aaomidi.sortingtelegram.model.TelegramCommand;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.util.HashMap;

/**
 * Created by amir on 2015-12-21.
 */
public class CommandHandler {
    private final SortingTelegram instance;
    private HashMap<String, TelegramCommand> commands;

    public CommandHandler(SortingTelegram instance) {
        this.instance = instance;
        commands = new HashMap<>();
    }

    public void registerCommands() {
        new SortRandomCommand(instance, "SortRandom", "Sorts a random set of data.");
    }

    public void registerCommand(TelegramCommand telegramCommand) {
        commands.put(telegramCommand.getName().toLowerCase(), telegramCommand);

        for (String alias : telegramCommand.getAliases()) {
            commands.put(alias.toLowerCase(), telegramCommand);
        }
    }

    public void handleCommand(CommandMessageReceivedEvent event) {
        String cmdString = event.getCommand();
        cmdString = cmdString.toLowerCase();

        TelegramCommand command = commands.get(cmdString);

        if (command == null) return;

        command.execute(event);
    }

    public void handleText(final TextMessageReceivedEvent event) {
        commands.values().stream().forEach(c -> c.listenToReply(event));
    }
}
