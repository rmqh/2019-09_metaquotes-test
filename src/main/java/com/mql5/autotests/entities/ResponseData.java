package com.mql5.autotests.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData {
    private String url;

    @JsonProperty("short")
    private String shortUrl;

    private String qr;
    private String type;
    private String created;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "url='" + url + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", qr='" + qr + '\'' +
                ", type='" + type + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
