package com.mql5.autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public abstract class AbstractWebTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractWebTest.class);

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    protected MainPage mainPage;

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

        mainPage = new MainPage();
    }

    @After
    public void tearDown() throws Exception {
        WebDriverRunner.getWebDriver().close();
    }

    // private methods

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
}