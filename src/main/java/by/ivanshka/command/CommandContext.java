package by.ivanshka.command;

import by.ivanshka.service.ItemStorageService;
import by.ivanshka.service.NetworkService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandContext {
    private final ItemStorageService storageService;
    private final NetworkService networkService;
}
