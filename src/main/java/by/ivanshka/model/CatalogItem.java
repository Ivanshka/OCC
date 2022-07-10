package by.ivanshka.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CatalogItem {
    private final String name;
    private final float cost;
    private final String url;
}
