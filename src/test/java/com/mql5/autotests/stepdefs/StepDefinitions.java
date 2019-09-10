package com.mql5.autotests.stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.mql5.autotests.AbstractWebTest;
import com.mql5.autotests.EventHistory;
import com.mql5.autotests.EventPage;
import com.mql5.autotests.MainPage;
import com.mql5.autotests.enums.Currency;
import com.mql5.autotests.enums.Importance;
import com.mql5.autotests.enums.Language;
import com.mql5.autotests.header.CalendarPage;
import com.mql5.autotests.urlshortener.LstToService;
import com.mql5.autotests.urlshortener.UrlShortenerService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private static final Logger LOG = LoggerFactory.getLogger(StepDefinitions.class);

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private static final SimpleDateFormat FORMAT_EN = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

    private static final String TOKEN_LST_TO = "d8458d1a9f3d9e13f83651e8";
    private static final String USER_AGENT = "Googlebot/2.1 (+http://www.google.com/bot.html)";

    private UrlShortenerService service = new LstToService(TOKEN_LST_TO, USER_AGENT);

    private MainPage mainPage = new MainPage();
    private CalendarPage calendarPage;
    private EventPage eventPage;


    // steps

    @Given("Open main page")
    public void openMainPage() {
        mainPage.open();
    }

    @Given("Open economic calendar page")
    public void openEconomicCalendarPage() {
        calendarPage = mainPage.openCalendarPage();
    }

    @And("Select language {string}")
    public void selectLanguageEn(String language) {
        mainPage.selectLanguage(Language.valueOf(language.toUpperCase()));
    }

    @And("Select current month")
    public void selectCurrentMonth() {
        calendarPage.selectCurrentMonth();
    }

    @And("^Select importance \"([^\"]*)\"$")
    public void selectImportance(Importance importance) {
        switch (importance) {
            case HOLIDAYS:
                calendarPage.selectImportanceHolidays();
                break;
            case LOW:
                calendarPage.selectImportanceLow();
                break;
            case MEDIUM:
                calendarPage.selectImportanceMedium();
                break;
            case HIGH:
                calendarPage.selectImportanceHigh();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @And("^Deselect importance \"([^\"]*)\"$")
    public void deselectImportance(Importance importance) {
        switch (importance) {
            case HOLIDAYS:
                calendarPage.deselectImportanceHolidays();
                break;
            case LOW:
                calendarPage.deselectImportanceLow();
                break;
            case MEDIUM:
                calendarPage.deselectImportanceMedium();
                break;
            case HIGH:
                calendarPage.deselectImportanceHigh();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @And("^Select currency \"([^\"]*)\" and deselect others$")
    public void selectCurrencyAndDeselectOthers(Currency currency) {
        switch (currency) {
            case USD:
                calendarPage.selectUSD(true);
                break;
            case EUR:
                calendarPage.selectEUR(true);
                break;
            case JPY:
                calendarPage.selectJPY(true);
                break;
            case GBP:
                calendarPage.selectGBP(true);
                break;
            case CAD:
                calendarPage.selectCAD(true);
                break;
            case AUD:
                calendarPage.selectAUD(true);
                break;
            case CHF:
                calendarPage.selectCHF(true);
                break;
            case CNY:
                calendarPage.selectCNY(true);
                break;
            case NZD:
                calendarPage.selectNZD(true);
                break;
            case BRL:
                calendarPage.selectBRL(true);
                break;
            case KRW:
                calendarPage.selectKRW(true);
                break;
            case HKD:
                calendarPage.selectHKD(true);
                break;
            case SGD:
                calendarPage.selectSGD(true);
                break;
            case MXN:
                calendarPage.selectMXN(true);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @And("Open event # {int}")
    public void openEvent(int rowNumber) {
        eventPage = calendarPage.openEvent(rowNumber);
    }

    @Then("Event importance is {string}")
    public void checkEventImportance(String importance) {
        assertEquals(importance, eventPage.getImportance());
    }

    @And("Event country is {string}")
    public void checkEventCountry(String country) {
        assertEquals(country, eventPage.getCountry().toUpperCase());
    }

    @And("Print event history for last {int} months")
    public void printEventHistoryForLastMonths(int months) {
        eventPage.openHistoryTab();
        printHistories(eventPage.getLogs(12));
    }

    @And("Print event page short url")
    public void printEventPageShortUrl() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        LOG.info("Current URL: {}", currentUrl);

        String shortUrl = service.shortenUrl(currentUrl);
        LOG.info("Short URL: {}", shortUrl);

        service.deleteUrl(shortUrl);
    }

    @Before
    public void setUp() throws Exception {
        Properties properties = getProperties();
        LOG.info("Properties: {}", properties);

        String browserName = properties.getProperty("browserName");
        String userAgent = properties.getProperty("userAgent");
        String baseUrl = properties.getProperty("baseUrl");

        Configuration.baseUrl = baseUrl;

        WebDriver driver;

        switch (browserName) {
            case CHROME:
                driver = getUpChromeDriver(userAgent);
                break;
            case FIREFOX:
                driver = getUpFirefoxDriver(userAgent);
                break;
            default:
                throw new InvalidArgumentException("Unexpected browser name");
        }

        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void tearDown() throws Exception {
        WebDriverRunner.getWebDriver().close();
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(AbstractWebTest.class.getResourceAsStream("/pom.properties"));
        return properties;
    }

    private static ChromeDriver getUpChromeDriver(String userAgent) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(String.format("--user-agent='%s'", userAgent));
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    private static FirefoxDriver getUpFirefoxDriver(String userAgent) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("general.useragent.override", userAgent);
        return new FirefoxDriver(options);
    }

    private static void printHistories(List<EventHistory> histories) {
        LOG.info("| Date | Actual | Forecast | Previous |");
        for (EventHistory log : histories) {
            LOG.info(String.format("| %s | %s | %s | %s |",
                    FORMAT_EN.format(log.getDate()), log.getActual(), log.getForecast(), log.getPrevious()));
        }
    }
}
