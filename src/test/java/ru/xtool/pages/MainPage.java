package ru.xtool.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

public class MainPage {
    public static String url = "https://xtool.ru";
    private SelenideDriver driver;
    SelenideElement form;
    public SelenideElement usernameField;
    public SelenideElement passwordField;
    public SelenideElement loginButton;
    public MainPage(SelenideDriver driver) {
        this.driver = driver;
        System.out.println("test" + driver);
    }
    private void findLoginFields(){
        form = driver.$x("//descendant::form[contains(@class, 'form-login')]");
        usernameField = form.$x("descendant::input[@name='login']");
        passwordField = form.$x("descendant::input[@name='pass']");
        loginButton = form.$x("descendant::button[@type='submit']");
    }

    public void sendLoginData() {
        findLoginFields();
        usernameField.scrollTo().click();
        usernameField.sendKeys("natashata7sen@gmail.com");
        passwordField.scrollTo().click();
        passwordField.sendKeys("5LGrHF8g");
        loginButton.click();
    }

    public void sendIncorrectLoginData() {
        findLoginFields();
        usernameField.scrollTo().click();
        usernameField.sendKeys("natashata7sen@gmail.com");
        passwordField.scrollTo().click();
        passwordField.sendKeys("12345678");
        loginButton.click();
    }
}
