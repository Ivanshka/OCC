package by.ivanshka.command.impl;

import by.ivanshka.command.Command;
import by.ivanshka.command.CommandContext;
import by.ivanshka.command.CommandResult;
import by.ivanshka.exception.CommandException;
import by.ivanshka.model.CatalogItem;
import by.ivanshka.readerWriter.FileReaderWriterJson;
import by.ivanshka.wrapper.CatalogItemListWrapper;

import java.io.IOException;
import java.util.Optional;

public class LoadCommand implements Command {
    private static final String NAME = "load";

    private static final String DESCRIPTION = "Load product list from file";

    private static final String HELP_TEXT = """
            load [FILE_PATH]
                Loads items from file located at the FILE_PATH path
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        if (args.length == 0)
            throw new CommandException("File path is not specified!");

        String filePath = String.join(" ", args);

        try {
            CatalogItemListWrapper itemsWrapper = new FileReaderWriterJson<CatalogItemListWrapper>().read(
                    filePath, CatalogItemListWrapper.class);

            for (CatalogItem item : itemsWrapper.getItems()) {
                context.getStorageService().addItem(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.of(new CommandResult("Product list was loaded!"));
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
