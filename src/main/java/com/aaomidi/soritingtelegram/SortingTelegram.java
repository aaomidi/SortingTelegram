package com.aaomidi.soritingtelegram;

import com.aaomidi.soritingtelegram.handlers.CommandHandler;
import com.aaomidi.soritingtelegram.handlers.SortingRegister;
import com.aaomidi.soritingtelegram.hooks.TelegramHook;
import com.aaomidi.soritingtelegram.util.StringManager;
import lombok.Getter;

/**
 * Created by amir on 2015-12-20.
 */
public class SortingTelegram {
    @Getter
    private SortingRegister sortingRegister;
    @Getter
    private CommandHandler commandHandler;
    @Getter
    private TelegramHook telegramHook;

    public SortingTelegram(String... args) {
        this.setupTelegram(args[0]);
        this.setupSorts();
        this.setupCommands();

        this.keepAlive();
    }

    public static void main(String... args) {
        new SortingTelegram(args);
    }

    private void setupTelegram(String key) {
        StringManager.logn("Connecting to telegram...");
        telegramHook = new TelegramHook(this, key);
        StringManager.logn("\tConnected");
    }

    private void setupSorts() {
        StringManager.logn("Setting up sorts...");
        sortingRegister = new SortingRegister(this);

        sortingRegister.registerSortings();
        StringManager.logn("\tDone");
    }

    private void setupCommands() {
        StringManager.logn("Setting up commands...");
        commandHandler = new CommandHandler(this);

        commandHandler.registerCommands();
        StringManager.logn("\tDone");
    }

    private void keepAlive() {
        while (true) {
            // Nothing.
        }
    }
}
