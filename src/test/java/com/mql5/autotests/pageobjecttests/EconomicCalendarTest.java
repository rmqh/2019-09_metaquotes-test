package com.mql5.autotests.pageobjecttests;

import com.codeborne.selenide.WebDriverRunner;
import com.mql5.autotests.AbstractWebTest;
import com.mql5.autotests.EventHistory;
import com.mql5.autotests.EventPage;
import com.mql5.autotests.MainPage;
import com.mql5.autotests.enums.Country;
import com.mql5.autotests.enums.Importance;
import com.mql5.autotests.enums.Language;
import com.mql5.autotests.urlshortener.LstToService;
import com.mql5.autotests.urlshortener.UrlShortenerService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EconomicCalendarTest extends AbstractWebTest {
    private static final Logger LOG = LoggerFactory.getLogger(EconomicCalendarTest.class);
    private static final SimpleDateFormat FORMAT_EN = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

    private static final String TOKEN_LST_TO = "d8458d1a9f3d9e13f83651e8";
    private static final String USER_AGENT = "Googlebot/2.1 (+http://www.google.com/bot.html)";

    private UrlShortenerService service = new LstToService(TOKEN_LST_TO, USER_AGENT);

    @Test
    public void testMultipleFilters() {
        LOG.info("Start multiple filters test");

        MainPage mainPage = this.mainPage.open();
        mainPage.selectLanguage(Language.EN);

        EventPage eventPage = mainPage.openCalendarPage()
                .selectCurrentMonth()
                .deselectImportanceHolidays()
                .deselectImportanceLow()
                .selectImportanceMedium()
                .deselectImportanceHigh()
                .selectCHF(true)
                .openEvent(1);

        Assert.assertEquals(Importance.MEDIUM.name(), eventPage.getImportance());
        Assert.assertEquals(Country.SWITZERLAND.getCountry(), eventPage.getCountry());

        eventPage.openHistoryTab();
        List<EventHistory> histories = eventPage.getLogs(12);

        printHistories(histories);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        LOG.info("Current URL: {}", currentUrl);

        String shortUrl = service.shortenUrl(currentUrl);
        LOG.info("Short URL: {}", shortUrl);

        service.deleteUrl(shortUrl);
    }

    private static void printHistories(List<EventHistory> histories) {
        LOG.info("| Date | Actual | Forecast | Previous |");
        for (EventHistory log : histories) {
            LOG.info(String.format("| %s | %s | %s | %s |",
                    FORMAT_EN.format(log.getDate()), log.getActual(), log.getForecast(), log.getPrevious()));
        }
    }
}
