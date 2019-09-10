package com.mql5.autotests.entities;

public class RequestData {
    private String create;
    private String url;
    private String utmTemplate;

    public RequestData() {
    }

    public RequestData(String create, String url, String utmTemplate) {
        this.create = create;
        this.url = url;
        this.utmTemplate = utmTemplate;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUtmTemplate() {
        return utmTemplate;
    }

    public void setUtmTemplate(String utmTemplate) {
        this.utmTemplate = utmTemplate;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "create='" + create + '\'' +
                ", url='" + url + '\'' +
                ", utmTemplate='" + utmTemplate + '\'' +
                '}';
    }
}
