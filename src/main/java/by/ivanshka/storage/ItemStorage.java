package by.ivanshka.storage;

import by.ivanshka.model.CatalogItem;

import java.util.List;

public interface ItemStorage {

    List<CatalogItem> getItems();

    void addItem(CatalogItem item);

    void removeItem(int index);
}
