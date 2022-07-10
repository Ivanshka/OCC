package by.ivanshka.command;

import by.ivanshka.exception.CommandException;

import java.util.Optional;

public interface Command {
    Optional<CommandResult> execute(CommandContext context, String... args) throws CommandException;
    String getName();
    String getDescription();
    String getHelp();
}
