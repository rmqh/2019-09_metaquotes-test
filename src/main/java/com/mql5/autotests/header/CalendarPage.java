package com.mql5.autotests.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.mql5.autotests.AbstractPage;
import com.mql5.autotests.EventPage;
import com.mql5.autotests.enums.Currency;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/*
 * Dates
 * Importance
 * Currencies
 */

public class CalendarPage extends AbstractPage {

    // dates
    private static final String ID_DATE_WEEK_CURRENT = "filterDate1";
    private static final String ID_DATE_WEEK_PREV = "filterDate2";
    private static final String ID_DATE_WEEK_NEXT = "filterDate3";
    private static final String ID_DATE_MONTH_CURRENT = "filterDate4";
    private static final String ID_DATE_MONTH_PREV = "filterDate5";
    private static final String ID_DATE_MONTH_NEXT = "filterDate6";

    // importance
    private static final String ID_IMPORTANCE_HOLIDAYS = "filterImportance1";
    private static final String ID_IMPORTANCE_LOW = "filterImportance2";
    private static final String ID_IMPORTANCE_MEDIUM = "filterImportance4";
    private static final String ID_IMPORTANCE_HIGH = "filterImportance8";

    // currencies
    private static final String ID_CURRENCY_USD = "filterCurrency1";
    private static final String ID_CURRENCY_EUR = "filterCurrency2";
    private static final String ID_CURRENCY_JPY = "filterCurrency4";
    private static final String ID_CURRENCY_GBP = "filterCurrency8";
    private static final String ID_CURRENCY_CAD = "filterCurrency16";
    private static final String ID_CURRENCY_AUD = "filterCurrency32";
    private static final String ID_CURRENCY_CHF = "filterCurrency64";
    private static final String ID_CURRENCY_CNY = "filterCurrency128";
    private static final String ID_CURRENCY_NZD = "filterCurrency256";
    private static final String ID_CURRENCY_BRL = "filterCurrency1024";
    private static final String ID_CURRENCY_KRW = "filterCurrency2048";
    private static final String ID_CURRENCY_HKD = "filterCurrency4096";
    private static final String ID_CURRENCY_SGD = "filterCurrency8192";
    private static final String ID_CURRENCY_MXN = "filterCurrency16384";

    // dates

    public CalendarPage selectCurrentWeek() {
        logger.info("Select item 'Current week'");
        selectPredefinedDateRange(ID_DATE_WEEK_CURRENT);
        return this;
    }

    public CalendarPage selectPreviousWeek() {
        logger.info("Select item 'Previous week'");
        selectPredefinedDateRange(ID_DATE_WEEK_PREV);
        return this;
    }

    public CalendarPage selectNextWeek() {
        logger.info("Select item 'Next week'");
        selectPredefinedDateRange(ID_DATE_WEEK_NEXT);
        return this;
    }

    public CalendarPage selectCurrentMonth() {
        logger.info("Select item 'Current month'");
        selectPredefinedDateRange(ID_DATE_MONTH_CURRENT);
        return this;
    }

    public CalendarPage selectPreviousMonth() {
        logger.info("Select item 'Previous month'");
        selectPredefinedDateRange(ID_DATE_MONTH_PREV);
        return this;
    }

    public CalendarPage selectNextMonth() {
        logger.info("Select item 'Next month'");
        selectPredefinedDateRange(ID_DATE_MONTH_NEXT);
        return this;
    }

    // importance

    public CalendarPage selectImportanceAll() {
        logger.info("Select all importances");
        selectImportanceHolidays();
        selectImportanceLow();
        selectImportanceMedium();
        selectImportanceHigh();
        return this;
    }

    public CalendarPage selectImportanceHolidays(boolean deselectOthers) {
        logger.info("Select importance 'HOLIDAYS'");
        selectEventImportance(ID_IMPORTANCE_HOLIDAYS);
        if (deselectOthers) {
            deselectImportanceLow();
            deselectImportanceMedium();
            deselectImportanceHigh();
        }
        return this;
    }

    public CalendarPage selectImportanceLow(boolean deselectOthers) {
        logger.info("Select importance 'LOW'");
        selectEventImportance(ID_IMPORTANCE_LOW);
        if (deselectOthers) {
            deselectImportanceHolidays();
            deselectImportanceMedium();
            deselectImportanceHigh();
        }
        return this;
    }

    public CalendarPage selectImportanceMedium(boolean deselectOthers) {
        logger.info("Select importance 'MEDIUM'");
        selectEventImportance(ID_IMPORTANCE_MEDIUM);
        if (deselectOthers) {
            deselectImportanceHolidays();
            deselectImportanceLow();
            deselectImportanceHigh();
        }
        return this;
    }

    public CalendarPage selectImportanceHigh(boolean deselectOthers) {
        logger.info("Select importance 'HIGH'");
        selectEventImportance(ID_IMPORTANCE_HIGH);
        if (deselectOthers) {
            deselectImportanceHolidays();
            deselectImportanceLow();
            deselectImportanceMedium();
        }
        return this;
    }

    public CalendarPage selectImportanceHolidays() {
        return selectImportanceHolidays(false);
    }

    public CalendarPage selectImportanceLow() {
        return selectImportanceLow(false);
    }

    public CalendarPage selectImportanceMedium() {
        return selectImportanceMedium(false);
    }

    public CalendarPage selectImportanceHigh() {
        return selectImportanceHigh(false);
    }

    public CalendarPage deselectImportanceHolidays() {
        logger.info("Deselect importance 'Holidays'");
        deselectEventImportance(ID_IMPORTANCE_HOLIDAYS);
        return this;
    }

    public CalendarPage deselectImportanceLow() {
        logger.info("Deselect importance 'Low'");
        deselectEventImportance(ID_IMPORTANCE_LOW);
        return this;
    }

    public CalendarPage deselectImportanceMedium() {
        logger.info("Deselect importance 'Medium'");
        deselectEventImportance(ID_IMPORTANCE_MEDIUM);
        return this;
    }

    public CalendarPage deselectImportanceHigh() {
        logger.info("Deselect importance 'High'");
        deselectEventImportance(ID_IMPORTANCE_HIGH);
        return this;
    }

    // currencies

    public CalendarPage selectCurrencyAll() {
        for (Currency currency : Currency.values()) {
            selectCurrency(currency);
        }
        return this;
    }

    // select currency

    public CalendarPage selectUSD() {
        return selectCurrency(Currency.USD);
    }

    public CalendarPage selectEUR() {
        return selectCurrency(Currency.EUR);
    }

    public CalendarPage selectJPY() {
        return selectCurrency(Currency.JPY);
    }

    public CalendarPage selectGBP() {
        return selectCurrency(Currency.GBP);
    }

    public CalendarPage selectCAD() {
        return selectCurrency(Currency.CAD);
    }

    public CalendarPage selectAUD() {
        return selectCurrency(Currency.AUD);
    }

    public CalendarPage selectCHF() {
        return selectCurrency(Currency.CHF);
    }

    public CalendarPage selectCNY() {
        return selectCurrency(Currency.CNY);
    }

    public CalendarPage selectNZD() {
        return selectCurrency(Currency.NZD);
    }

    public CalendarPage selectBRL() {
        return selectCurrency(Currency.BRL);
    }

    public CalendarPage selectKRW() {
        return selectCurrency(Currency.KRW);
    }

    public CalendarPage selectHKD() {
        return selectCurrency(Currency.HKD);
    }

    public CalendarPage selectSGD() {
        return selectCurrency(Currency.SGD);
    }

    public CalendarPage selectMXN() {
        return selectCurrency(Currency.MXN);
    }

    // deselect currency

    public CalendarPage deselectUSD() {
        return deselectCurrency(Currency.USD);
    }

    public CalendarPage deselectEUR() {
        return deselectCurrency(Currency.EUR);
    }

    public CalendarPage deselectJPY() {
        return deselectCurrency(Currency.JPY);
    }

    public CalendarPage deselectGBP() {
        return deselectCurrency(Currency.GBP);
    }

    public CalendarPage deselectCAD() {
        return deselectCurrency(Currency.CAD);
    }

    public CalendarPage deselectAUD() {
        return deselectCurrency(Currency.AUD);
    }

    public CalendarPage deselectCHF() {
        return deselectCurrency(Currency.CHF);
    }

    public CalendarPage deselectCNY() {
        return deselectCurrency(Currency.CNY);
    }

    public CalendarPage deselectNZD() {
        return deselectCurrency(Currency.NZD);
    }

    public CalendarPage deselectBRL() {
        return deselectCurrency(Currency.BRL);
    }

    public CalendarPage deselectKRW() {
        return deselectCurrency(Currency.KRW);
    }

    public CalendarPage deselectHKD() {
        return deselectCurrency(Currency.HKD);
    }

    public CalendarPage deselectSGD() {
        return deselectCurrency(Currency.SGD);
    }

    public CalendarPage deselectMXN() {
        return deselectCurrency(Currency.MXN);
    }

    // select one currency only

    public CalendarPage selectUSD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.USD);
    }

    public CalendarPage selectEUR(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.EUR);
    }

    public CalendarPage selectJPY(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.JPY);
    }

    public CalendarPage selectGBP(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.GBP);
    }

    public CalendarPage selectCAD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.CAD);
    }

    public CalendarPage selectAUD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.AUD);
    }

    public CalendarPage selectCHF(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.CHF);
    }

    public CalendarPage selectCNY(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.CNY);
    }

    public CalendarPage selectNZD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.NZD);
    }

    public CalendarPage selectBRL(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.BRL);
    }

    public CalendarPage selectKRW(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.KRW);
    }

    public CalendarPage selectHKD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.HKD);
    }

    public CalendarPage selectSGD(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.SGD);
    }

    public CalendarPage selectMXN(boolean deselectOthers) {
        return selectOneCurrencyOnly(Currency.MXN);
    }

    private CalendarPage selectOneCurrencyOnly(Currency currency) {
        selectCurrency(currency);
        deselectOthers(currency);
        return this;
    }

    // common

    public EventPage openEvent(int rowNumber) {
        logger.info("Open event. Table row number : {}", rowNumber);
        ElementsCollection links = $("#economicCalendarTable")
                .$$("div[class^='ec-table__item'] a");

        links.get(rowNumber - 1).click();
        return page(EventPage.class);
    }

    // private methods

    private void selectPredefinedDateRange(String id) {
        $(String.format("label[for='%s']", id)).click();
    }

    private void selectEventImportance(String id) {
        logger.trace("Select event importance by id: {}", id);
        if (!$(By.id(id)).isSelected()) {
            logger.debug("Element is not selected. Select it.");
            $(By.id(id)).$x("following-sibling::label").click();
        }
    }

    private void deselectEventImportance(String id) {
        logger.trace("Deselect event importance by id: {}", id);

        SelenideElement element = $(By.id(id));
        boolean selected = element.isSelected();
        if (selected) {
            element.$x("following-sibling::label").click();
//            Selenide.$x(String.format("//label[@for='%s']", id)).click();
        }
        element.shouldNotBe(Condition.selected);
    }

    private CalendarPage selectCurrency(Currency currency) {
        logger.info("Select currency '{}'", currency);
        setCurrency(currency, true);
        return this;
    }

    private CalendarPage deselectCurrency(Currency currency) {
        logger.info("Deselect currency '{}'", currency);
        setCurrency(currency, false);

        return this;
    }

    private void setCurrency(Currency currency, boolean selected) {
        SelenideElement list = $("#economicCalendarFilterCurrency");
        SelenideElement input = list.$$("input")
                .findBy(Condition.attribute("value", String.valueOf(currency.getValue())));
        SelenideElement label = list.$$("li > label").findBy(Condition.text(currency.name()));

        if (selected) {
            if (!input.isSelected()) {
                clickToElement(label);
            }
            input.shouldBe(Condition.selected);
        } else {
            if (input.isSelected()) {
                clickToElement(label);
            }
            input.shouldNotBe(Condition.selected);
        }
    }

    private void deselectOthers(Currency excludedCurrency) {
        List<Currency> list = Arrays.stream(Currency.values())
                .filter(curr -> !curr.equals(excludedCurrency))
                .collect(Collectors.toList());
        for (Currency curr : list) {
            deselectCurrency(curr);
        }
    }
}