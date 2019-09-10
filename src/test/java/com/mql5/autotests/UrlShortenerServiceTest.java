package com.mql5.autotests;

import com.mql5.autotests.urlshortener.LstToService;
import com.mql5.autotests.urlshortener.UrlShortenerService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UrlShortenerServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(UrlShortenerServiceTest.class);

    private static final String TOKEN_LST_TO = "d8458d1a9f3d9e13f83651e8";
    private static final String USER_AGENT = "Googlebot/2.1 (+http://www.google.com/bot.html)";

    private UrlShortenerService service = new LstToService(TOKEN_LST_TO, USER_AGENT);

    @Test
    public void testUrlShortener() {
        String url = String.format("https://%s.com", UUID.randomUUID().toString());
        LOG.info("Long url: {}", url);

        String shortenUrl = service.shortenUrl(url);
        LOG.info("Short url: {}", shortenUrl);

        assertTrue(service.isExist(shortenUrl));

        service.deleteUrl(shortenUrl);

        assertFalse(service.isExist(shortenUrl));
    }
}
