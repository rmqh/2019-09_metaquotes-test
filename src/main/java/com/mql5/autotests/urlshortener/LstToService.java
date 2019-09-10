package com.mql5.autotests.urlshortener;

import com.mql5.autotests.entities.LstToRequest;
import com.mql5.autotests.entities.LstToResponse;
import com.mql5.autotests.entities.RequestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LstToService implements UrlShortenerService {
    private static final Logger LOG = LoggerFactory.getLogger(LstToService.class);

    private static final String URL = "https://lst.to/api/v1";
    private static final String LINK = "/link";

    private final RequestSpecification rs;

    public LstToService(String token, String userAgent) {
        RestAssured.baseURI = URL;
        rs = RestAssured.given()
                .header(new Header("X-AUTH-TOKEN", token))
                .header(new Header("User-Agent", userAgent))
                .contentType(ContentType.JSON);
    }

    @Override
    public String shortenUrl(String currentUrl) {
        LOG.debug("Shorten url: {}", currentUrl);
        RequestData requestData = new RequestData("true", currentUrl, "utm_source=[domain]&utm_referer=[referer]");

        LstToRequest request = new LstToRequest();
        request.setRequestData(requestData);

        LstToResponse resp = rs
                .body(request, ObjectMapperType.GSON)
                .post(LINK)
                .as(LstToResponse.class);

        LOG.info("Lst.To service response: {}", resp);
        return resp.getData().getShortUrl();
    }

    @Override
    public boolean isExist(String shortUrl) {
        return rs.get(String.format("%s/%s", LINK, shortUrl.split("/")[3]))
                .getStatusCode() == HttpStatus.SC_OK;
    }

    @Override
    public void deleteUrl(String shortUrl) {
        LOG.debug("Delete url: {}", shortUrl);
        String path = String.format("%s/%s", LINK, shortUrl.split("/")[3]);
        rs.delete(path)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
