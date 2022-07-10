package by.ivanshka.service.impl;

import by.ivanshka.model.CatalogItem;
import by.ivanshka.service.NetworkService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class NetworkServiceImpl implements NetworkService {
    public CatalogItem getItemFromURL(String url) throws IOException {
        final String html = getURLSource(url);
        final String productName = HtmlParser.getProductName(html).trim();
        final float productCost = HtmlParser.getProductCost(html);

        return new CatalogItem(productName, productCost, url);
    }

    private String getURLSource(String url) throws IOException
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }

    private String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8)))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

    private static class HtmlParser {
        public static String getProductName(String html) {
            final String startPattern = "<h1 class=\"catalog-masthead__title js-nav-header\">";
            final String endPattern = "</h1>";

            final int startIndex = html.indexOf(startPattern);
            final int endIndex = html.indexOf(endPattern, startIndex);

            if (startIndex == -1 || endIndex == -1)
                throw new IllegalArgumentException("Can't process specified HTML code when getting product name");

            final int substringStartIndex = startIndex + startPattern.length();

            return html.substring(substringStartIndex, endIndex);
        }

        public static float getProductCost(String html) {
            final String startPattern = "\"min_price\":\"";
            final String endPattern = "\"}];";

            final int startIndex = html.indexOf(startPattern);
            final int endIndex = html.indexOf(endPattern, startIndex);

            if (startIndex == -1 || endIndex == -1)
                throw new IllegalArgumentException("Can't process specified HTML code when getting product cost");

            final int substringStartIndex = startIndex + startPattern.length();

            final String cost = html.substring(substringStartIndex, endIndex);

            return Float.parseFloat(cost);
        }
    }
}
