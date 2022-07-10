package by.ivanshka.service.impl;

import by.ivanshka.model.CatalogItem;
import by.ivanshka.service.ItemStorageService;
import by.ivanshka.storage.ItemStorage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ItemStorageServiceImpl implements ItemStorageService {
    private final ItemStorage storage;

    @Override
    public List<CatalogItem> getItems() {
        return storage.getItems();
    }

    public void addItem(CatalogItem item) {
        storage.addItem(item);
    }

    public void removeItem(int index) {
        storage.removeItem(index);
    }

    public double calculateTotalCost() {
        return storage.getItems()
                .stream()
                .mapToDouble(CatalogItem::getCost)
                .sum();
    }

    public void clearStorage() {
        storage.getItems().clear();
    }

    public int getItemsAmount() {
        return storage.getItems().size();
    }
}
