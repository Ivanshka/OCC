package by.ivanshka;

import by.ivanshka.controller.CatalogController;
import by.ivanshka.service.ItemStorageService;
import by.ivanshka.service.impl.ItemStorageServiceImpl;
import by.ivanshka.storage.impl.ItemStorageImpl;
import by.ivanshka.util.CatalogUtil;
import by.ivanshka.service.impl.NetworkServiceImpl;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        ItemStorageService storageService = new ItemStorageServiceImpl(new ItemStorageImpl());

        CatalogController catalog = new CatalogController(storageService,
                new CatalogUtil(), new NetworkServiceImpl(), new Scanner(System.in));

        catalog.run();
    }
}
