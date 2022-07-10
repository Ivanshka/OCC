package by.ivanshka.util;

import by.ivanshka.model.CatalogItem;

import java.util.List;

public class CatalogUtil {
    public void printCatalogItems(List<CatalogItem> items) {
        // number, name, cost, url
        System.out.format(
                """
                 %1$-3s| %2$-50s | %3$-9s | %4$s
                ----------------------------------------------------------------------------
                """,
                "#", "Product name", "Cost", "URL");
        int number = 1;
        float totalCost = 0;
        for (CatalogItem item : items) {
            System.out.format(
                    " %1$-3d" + // number
                    "| %2$-50s |" + // name
                    " %3$-9.2f " + // cost
                    "| %4$s\n", // url
                    number, item.getName(), item.getCost(), item.getUrl());
            ++number;
            totalCost += item.getCost();
        }

        System.out.format("\nTotal cost: %.2f BYN\n\n", totalCost);
    }
}
