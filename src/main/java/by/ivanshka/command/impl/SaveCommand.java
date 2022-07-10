package by.ivanshka.command.impl;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.exception.CommandException;
import by.ivanshka.readerWriter.FileReaderWriterJson;
import by.ivanshka.wrapper.CatalogItemListWrapper;

import java.io.IOException;
import java.util.Optional;

public class SaveCommand implements Command {
    private static final String NAME = "save";

    private static final String DESCRIPTION = "Save product list to file";

    private static final String HELP_TEXT = """
            save [FILE_PATH]
                Saves your list to file located at the FILE_PATH
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        if (args.length == 0)
            throw new CommandException("File path is not specified!");

        String filePath = String.join(" ", args);

        try {
            new FileReaderWriterJson<CatalogItemListWrapper>().write(
                    filePath, new CatalogItemListWrapper(context.getStorageService().getItems()));
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        }

        CommandResult result = new CommandResult(String.format("List saved to file \"%s\"", filePath));
        return Optional.of(result);
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
