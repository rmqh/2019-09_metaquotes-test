package com.mql5.autotests;

import com.codeborne.selenide.SelenideElement;

import java.util.*;

import static com.codeborne.selenide.Selenide.$;

public class EventPage {

    private SelenideElement bodyContent = $("#bodyContent");
    private SelenideElement calendarTabs = $("#calendar-tabs");
    private SelenideElement eventHistory = $("#eventHistory");

    // general info

    public String getImportance() {
        return bodyContent.$("td[class^='event-table__importance']").text();
    }

    public String getCountry() {
        return bodyContent.$("div[class='ec-event__header__content'] > a").text();
    }

    // tabs

    public EventPage openOverviewTab() {
        selectEventTab("overview");
        return this;
    }

    public EventPage openChartTab() {
        selectEventTab("chart");
        return this;
    }

    public EventPage openHistoryTab() {
        selectEventTab("history");
        return this;
    }

    public EventPage openWidgetTab() {
        selectEventTab("widget");
        return this;
    }

    // common

    public List<EventHistory> getLogs(int monthCount) {
        TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
        TimeZone.setDefault(gmtTimeZone);

        Calendar calendar = Calendar.getInstance(gmtTimeZone);
        calendar.add(Calendar.MONTH, monthCount * -1);

        Date startDate = calendar.getTime();

        List<SelenideElement> items = eventHistory.$$("div[class='event-table-history__item'");

        List<EventHistory> events = new ArrayList<>();

        for (SelenideElement item : items) {
            String date = item.$("div[class='event-table-history__date'").attr("data-date");

            Date eventDate = new Date(Long.parseLong(date));

            if (eventDate.before(startDate)) {
                break;
            }

            String actual = item.$("div[class^='event-table-history__actual'").$("span").text();
            String forecast = item.$("div[class='event-table-history__forecast'").text();
            String previous = item.$("div[class='event-table-history__previous'").$("span").text();

            EventHistory eventHistory = new EventHistory(eventDate, actual, forecast, previous);
            events.add(eventHistory);
        }
        return events;
    }

    private void selectEventTab(String name) {
        calendarTabs.$x(String.format("li[@data-content='%s']", name)).click();
    }
}
