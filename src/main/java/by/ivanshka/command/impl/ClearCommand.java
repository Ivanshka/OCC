package by.ivanshka.command.impl;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.exception.CommandException;

import java.util.Optional;

public class ClearCommand implements Command {
    private static final String NAME = "clear";

    private static final String DESCRIPTION = "Clear product list";

    private static final String HELP_TEXT = """
            clear
                Clear product list
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        context.getStorageService().clearStorage();

        return Optional.empty();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getHelp() {
        return HELP_TEXT;
    }
}
