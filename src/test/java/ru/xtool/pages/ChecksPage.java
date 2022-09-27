package ru.xtool.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

public class ChecksPage {
    public static String url = "https://xtool.ru/account/check/";
    private SelenideDriver driver;
    public ChecksPage(SelenideDriver driver) {
        this.driver = driver;
        System.out.println("test" + driver);
    }
}
