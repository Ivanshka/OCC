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

public class LoadUrlsCommand implements Command {
    private static final String NAME = "loadurls";

    private static final String DESCRIPTION = "Loads product list using URLs saved in file";

    private static final String HELP_TEXT = """
            loadurl [FILE_PATH]
                Loads products from URLs stored in a file located at the FILE_PATH path
            """;

    @Override
    public Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException {
        if (args.length == 0)
            throw new CommandException("File path is not specified!");

        String filePath = String.join(" ", args);

        try {
            System.out.println("Reading file...");

            List<String> urls = new FileReaderWriterJson<StringListWrapper>().read(filePath, StringListWrapper.class)
                    .getStrings();

            System.out.println("Processing urls...");

            for (String url : urls) {
                CatalogItem itemFromURL = context.getNetworkService().getItemFromURL(url);
                context.getStorageService().addItem(itemFromURL);
            }
        } catch (IOException e) {
            throw new CommandException(e.getMessage());
        }

        return Optional.of(new CommandResult("URLs list was loaded and specified!"));
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
