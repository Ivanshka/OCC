package by.ivanshka.wrapper;

import by.ivanshka.model.CatalogItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CatalogItemListWrapper {
    private final List<CatalogItem> items;
}
