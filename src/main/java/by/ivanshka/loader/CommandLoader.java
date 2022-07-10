package by.ivanshka.loader;

import by.ivanshka.command.Command;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandLoader {
    private static final String COMMANDS_PACKAGE_NAME = "by.ivanshka.command.impl";

    public static List<Command> loadCommands() {
        List<Command> commands = new ArrayList<>();

        Set<Class<?>> commandClasses = findAllClassesUsingReflectionsLibrary(COMMANDS_PACKAGE_NAME, Command.class);

        for (Class clazz : commandClasses) {
            try {
                if (Command.class.isAssignableFrom(clazz)){
                    Command commandObject = (Command) clazz.getConstructor().newInstance();
                    commands.add(commandObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return commands;
    }

    private static Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName, Class parentType) {
        return new Reflections(packageName).getSubTypesOf(parentType);
    }
}
