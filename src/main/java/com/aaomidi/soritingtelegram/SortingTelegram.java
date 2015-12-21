package com.aaomidi.soritingtelegram;

import com.aaomidi.soritingtelegram.handlers.CommandHandler;
import com.aaomidi.soritingtelegram.handlers.SortingRegister;
import com.aaomidi.soritingtelegram.hooks.TelegramHook;
import com.aaomidi.soritingtelegram.model.Sortable;
import com.aaomidi.soritingtelegram.util.IntegerConverter;
import com.aaomidi.soritingtelegram.util.StringManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.setupSorts();

        boolean runCli = runCLI();
        if (runCli) {
            this.cli();
        } else {
            if (args.length == 0) {
                StringManager.logn("There was no CLI arguments. Please make sure you've entered a telegram API key as an argument.");
                return;
            }

            this.setupTelegram(args[0]);
            this.setupCommands();

            this.keepAlive();
        }
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

    private boolean runCLI() {
        Scanner scanner = new Scanner(System.in);
        Integer input;
        do {
            StringManager.logn("Please enter how to run the program:\n\t1. CLI\n\t2. Telegram Bot");
            input = IntegerConverter.fromString(scanner.nextLine());
            if (input != null) {
                break;
            }
            StringManager.logn("That was an unrecognized input. Lets try this again.");
        } while (true);

        return input == 1;
    }

    private void cli() {
        do {

            StringManager.logn("Please enter your data points (enter \"stop\" to stop): ");
            Scanner scanner = new Scanner(System.in);
            List<String> strings = new ArrayList<>();
            do {
                String in = scanner.nextLine();
                if (in.equalsIgnoreCase("stop")) {
                    break;
                }
                strings.add(in);
            } while (true);


            Integer pick;
            do {
                StringManager.logn("Please pick the sorting method you prefer: ");
                int i = 1;
                for (Sortable sortable : sortingRegister.getSortables()) {
                    StringManager.logn("\t%d. %s", i++, sortable.getAlgorithmName());
                }

                pick = IntegerConverter.fromString(scanner.nextLine());
                if (pick != null) {
                    break;
                }

                StringManager.logn("That was an incorrect choice. Lets try this again.");
            } while (true);

            Sortable sortable = sortingRegister.getSortables().get(pick - 1);

            String[] sorted = new String[strings.size()];
            sorted = strings.toArray(sorted);

            StringManager.logn(sortable.sort(sorted, strings.size()));

            StringManager.log("The sorted strings are: ");
            for (String s : sorted) {
                StringManager.log(s + " ");
            }

            StringManager.logn("");

            {
                StringManager.log("Do you want to try again? Y/N");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("Y")) {
                    break;
                }
            }
        } while (true);

    }
}
