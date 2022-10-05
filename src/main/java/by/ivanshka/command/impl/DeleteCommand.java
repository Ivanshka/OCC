package by.ivanshka.command.impl;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.exception.CommandException;

import java.util.Optional;

public class DeleteCommand implements Command {
    private static final String NAME = "delete";

    private static final String DESCRIPTION = "Delete item by number";

    private static final String HELP_TEXT = """
            delete [POSITION]
                Deletes the item with specified POSITION
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        int index;

        try{
            index= Integer.parseInt(args[0]) - 1;
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new CommandException("Can't delete item cause number is incorrect!");
        }

        if (index < context.getStorageService().getItems().size())
            context.getStorageService().removeItem(index);

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
