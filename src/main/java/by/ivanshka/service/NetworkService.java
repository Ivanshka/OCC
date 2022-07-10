package by.ivanshka.service;

import by.ivanshka.model.CatalogItem;

import java.io.IOException;

public interface NetworkService {
    CatalogItem getItemFromURL(String url) throws IOException;
}
