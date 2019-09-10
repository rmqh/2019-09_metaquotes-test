package com.mql5.autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class MainPage extends AbstractPage{

    public MainPage open() {
        logger.info("Open main page");
        Selenide.open(Configuration.baseUrl);
        return Selenide.page(MainPage.class);
    }
}
