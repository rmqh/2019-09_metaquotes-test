package com.mql5.autotests;

import java.util.Date;

public class EventHistory {
    private Date date;
    private String actual;
    private String forecast;
    private String previous;

    public EventHistory(Date date, String actual, String forecast, String previous) {
        this.date = date;
        this.actual = actual;
        this.forecast = forecast;
        this.previous = previous;
    }

    public Date getDate() {
        return date;
    }

    public String getActual() {
        return actual;
    }

    public String getForecast() {
        return forecast;
    }

    public String getPrevious() {
        return previous;
    }
}
