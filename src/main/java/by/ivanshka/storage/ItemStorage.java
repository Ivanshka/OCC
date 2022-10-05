package by.ivanshka.storage;

import by.ivanshka.model.CatalogItem;

import java.util.Collection;
import java.util.List;

public interface ItemStorage {

    List<CatalogItem> getItems();

    void addItem(CatalogItem item);
    void addItems(Collection<CatalogItem> item);

    void removeItem(int index);
}
