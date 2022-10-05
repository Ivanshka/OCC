package by.ivanshka.command.impl;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.wrapper.StringListWrapper;
import by.ivanshka.exception.CommandException;
import by.ivanshka.model.CatalogItem;
import by.ivanshka.readerWriter.FileReaderWriterJson;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SaveUrlsCommand implements Command {
    private static final String NAME = "saveurls";

    private static final String DESCRIPTION = "Saves only URLs of your list items to file";

    private static final String HELP_TEXT = """
            save [FILE_PATH]
                Saves only URLs of your list items to file located at the FILE_PATH
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        if (args.length == 0)
            throw new CommandException("File path is not specified!");

        String filePath = String.join(" ", args);

        List<String> urls = context
                .getStorageService()
                .getItems()
                .stream()
                .map(CatalogItem::getUrl)
                .toList();

        try {
            new FileReaderWriterJson<StringListWrapper>().write(filePath, new StringListWrapper(urls));
        } catch (IOException e) {
            throw new CommandException(e.getMessage(), e);
        }

        CommandResult result = new CommandResult(String.format("URLs list saved to file \"%s\"", filePath));
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
