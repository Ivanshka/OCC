package by.ivanshka.storage.impl;

import by.ivanshka.model.CatalogItem;
import by.ivanshka.storage.ItemStorage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class ItemStorageImpl implements ItemStorage {
    private final List<CatalogItem> items;

    public ItemStorageImpl() {
        final int startCapacity = 10;
        items = new ArrayList<>(startCapacity);
    }

    public void addItem(CatalogItem item) {
        items.add(item);
    }

    @Override
    public void addItems(Collection<CatalogItem> items) {
        this.items.addAll(items);
    }

    public void removeItem(int index) {
        items.remove(index);
    }
}
