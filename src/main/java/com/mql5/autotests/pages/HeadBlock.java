package com.mql5.autotests.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HeadBlock {
    private static final String WEBTERMINAL = "/trading";
    private static final String DOCUMENTATION = "/docs";
    private static final String CALENDAR = "/economic-calendar";
    private static final String CODEBASE = "/code";
    private static final String ARTICLES = "/articles";
    private static final String FREELANCE = "/job";
    private static final String MARKET = "/market";
    private static final String SIGNALS = "/signals";
    private static final String VPS = "/vps";
    private static final String FORUM = "/forum";

    private static final String ID_LANGUAGE = "#langMenuContainer";

    public MainPage clickOnLogo() {
        $(".head").$("a[class='logo']").click();
        return page(MainPage.class);
    }

    public WebTerminalPage clickOnWebTerminalLink() {
        clickOnLink(WEBTERMINAL);
        return page(WebTerminalPage.class);
    }

    public DocumentationPage clickOnDocumentationLink() {
        clickOnLink(DOCUMENTATION);
        return page(DocumentationPage.class);
    }

    public CalendarPage clickOnCalendarLink() {
        clickOnLink(CALENDAR);
        return page(CalendarPage.class);
    }

    public CodeBasePage clickOnCodeBaseLink() {
        clickOnLink(CODEBASE);
        return page(CodeBasePage.class);
    }

    public ArticlesPage clickOnArticlesLink() {
        clickOnLink(ARTICLES);
        return page(ArticlesPage.class);
    }

    public FreelancePage clickOnFreelanceLink() {
        clickOnLink(FREELANCE);
        return page(FreelancePage.class);
    }

    public MarketPage clickOnMarketLink() {
        clickOnLink(MARKET);
        return page(MarketPage.class);
    }

    public SignalsPage clickOnSignalsLink() {
        clickOnLink(SIGNALS);
        return page(SignalsPage.class);
    }

    public VPSPage clickOnVPSLink() {
        clickOnLink(VPS);
        return page(VPSPage.class);
    }

    public ForumPage clickOnForumLink() {
        clickOnLink(FORUM);
        return page(ForumPage.class);
    }

    public void selectLanguageEN() {
        hoverOnLangSelection();
        selectLanguage("en");
    }

    public void selectLanguageRU() {
        hoverOnLangSelection();
        selectLanguage("ru");
    }

    // private methods

    private void clickOnLink(String link) {
        $(".head")
                .$(By.cssSelector(String.format("a[href$='%s']", link)))
                .click();
    }

    private void hoverOnLangSelection() {
        $(ID_LANGUAGE).shouldBe(Condition.appear).hover();
    }

    private void selectLanguage(String lang) {
        $("#langmenu")
                .$(String.format("li[class='%s']", lang))
                .click();
    }
}
