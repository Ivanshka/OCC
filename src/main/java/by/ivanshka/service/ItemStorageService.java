package by.ivanshka.service;

import by.ivanshka.model.CatalogItem;

import java.util.List;

public interface ItemStorageService {

    List<CatalogItem> getItems();

    void addItem(CatalogItem item);

    void removeItem(int index);

    double calculateTotalCost();

    void clearStorage();
}
