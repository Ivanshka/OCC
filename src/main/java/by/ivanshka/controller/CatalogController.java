package by.ivanshka.controller;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.exception.CommandException;
import by.ivanshka.loader.CommandLoader;
import by.ivanshka.model.CatalogItem;
import by.ivanshka.service.ItemStorageService;
import by.ivanshka.service.NetworkService;
import by.ivanshka.util.CatalogUtil;
import by.ivanshka.util.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CatalogController {
    private static final String PROMPT = "Cl> ";

    private static final String SITE = "https://catalog.onliner.by";
    private static final String EXIT_COMMAND = "exit";
    private static final String HELP_COMMAND = "help";
    private static final String NO_URL = "no URL";

    private final ItemStorageService storageService;
    private final CatalogUtil storageUtil;
    private final NetworkService networkService;

    private final Scanner in;

    private boolean printInfo = false;
    private String infoText;

    public CatalogController(ItemStorageService storageService, CatalogUtil storageUtil,
                             NetworkService networkService, Scanner in) {
        this.storageService = storageService;
        this.storageUtil = storageUtil;
        this.networkService = networkService;
        this.in = in;
    }

    public void run() {
        System.out.println("Loading of commands...");

        Map<String, Command> commands = makeCommandMap(CommandLoader.loadCommands());

        System.out.println("""
                    
                    Welcome to Onliner Catalog Calculator (created by Ivanshka)!
                    Enter product URL, product name, command, 'help' or 'exit'.
                    """);

        while(true) {
            final int itemsAmount = storageService.getItems().size();

            System.out.println();

            if (itemsAmount == 0)
                System.out.println("Your list is empty now!");
            else
                storageUtil.printCatalogItems(storageService.getItems());

            if (printInfo) {
                System.out.println(infoText);
                printInfo = false;
            }

            System.out.print(PROMPT);

            String rawUserInput = in.nextLine();

            if (rawUserInput.isEmpty())
                continue;

            if (rawUserInput.equals(EXIT_COMMAND))
                break;

            if (rawUserInput.equals(HELP_COMMAND)) {
                List<Command> commandsList = new ArrayList<>(commands.values());

                commandsList.stream()
                        .sorted(Comparator.comparing(Command::getName))
                        .forEach((command) ->
                                System.out.format("%1$-20s     %2$s\n", command.getName(), command.getDescription()));
                System.out.println("\nType\n[COMMAND] help\nto see the HELP of specified command");

                continue;
            }

            if (rawUserInput.contains(SITE)) {
                try {
                    System.out.println("Processing...");
                    final CatalogItem itemFromURL = networkService.getItemFromURL(rawUserInput);
                    storageService.addItem(itemFromURL);
                }
                catch(Exception e) {
                    System.out.println("Sorry, but an error has occurred when we tried to get an item!(\n" +
                            "Try again or enter product data manually.\nDebug information:\n" + e.getMessage());
                    continue;
                }
            } else
                parseAndExecuteCommand(rawUserInput, commands);

            clearScreen();
        }
    }

    private static Map<String, Command> makeCommandMap(List<Command> commands) {
        return commands.stream().collect(Collectors.toMap(Command::getName, command -> command));
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void parseAndExecuteCommand(String rawUserInput, Map<String, Command> commands) {
        List<String> words = Arrays.stream(rawUserInput.split("\\s+")).toList();

        final Command command = commands.get(words.get(0).toLowerCase());
        if (command == null) {
            addCustomItem(words.get(0));
        }
        else {
            final String[] args = words.stream()
                    .skip(1)
                    .toList()
                    .toArray(new String[0]);

            CommandContext context = new CommandContext(storageService, networkService);

            if (args[0].equalsIgnoreCase("help")) {
                System.out.println(command.getHelp());
                return;
            }

            try {
                final Optional<CommandResult> commandResult = command.execute(context, args);
                if (commandResult.isPresent()) {
                    printInfo = true;
                    infoText = commandResult.get().getResultMessage();
                }
            }
            catch (CommandException e) {
                printInfo = true;
                infoText = e.getMessage();
            }
        }
    }

    private void addCustomItem(String name) {
        System.out.print("Enter product cost: ");
        final float cost = UserInput.readFloat(in);

        CatalogItem item = new CatalogItem(name, cost, NO_URL);
        storageService.addItem(item);
    }
}
