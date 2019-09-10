package com.mql5.autotests;

import com.codeborne.selenide.WebDriverRunner;
import com.mql5.autotests.enums.Language;
import com.mql5.autotests.header.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HeadBlock headBlock = new HeadBlock();

    public MainPage openMainPage() {
        logger.info("Open main page");
        return headBlock.clickOnLogo();
    }

    public WebTerminalPage openWebTerminalPage() {
        logger.info("Open WebTerminal page");
        return headBlock.clickOnWebTerminalLink();
    }

    public DocumentationPage openDocumentationPage() {
        logger.info("Open Documentation page");
        return headBlock.clickOnDocumentationLink();
    }

    public CalendarPage openCalendarPage() {
        logger.info("Open Calendar page");
        return headBlock.clickOnCalendarLink();
    }

    public CodeBasePage openCodeBasePage() {
        logger.info("Open CodeBase page");
        return headBlock.clickOnCodeBaseLink();
    }

    public ArticlesPage openArticlesPage() {
        logger.info("Open Articles page");
        return headBlock.clickOnArticlesLink();
    }

    public FreelancePage openFreelancePage() {
        logger.info("Open Freelance page");
        return headBlock.clickOnFreelanceLink();
    }

    public MarketPage openMarketPage() {
        logger.info("Open Market page");
        return headBlock.clickOnMarketLink();
    }

    public SignalsPage openSignalsPage() {
        logger.info("Open Signals page");
        return headBlock.clickOnSignalsLink();
    }

    public VPSPage openVPSPage() {
        logger.info("Open VPS page");
        return headBlock.clickOnVPSLink();
    }

    public ForumPage openForumPage() {
        logger.info("Open Forum page");
        return headBlock.clickOnForumLink();
    }

    public void selectLanguage(Language language) {
        logger.info("Select language: '{}'", language.name());
        switch (language) {
            case EN:
                headBlock.selectLanguageEN();
                break;
            case RU:
                headBlock.selectLanguageRU();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected void clickToElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        executor.executeScript("arguments[0].click();", element);
    }
}
