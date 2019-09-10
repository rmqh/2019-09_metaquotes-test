package com.mql5.autotests.urlshortener;

public interface UrlShortenerService {

    String shortenUrl(String currentUrl);

    boolean isExist(String shortUrl);

    void deleteUrl(String shortUrl);
}
