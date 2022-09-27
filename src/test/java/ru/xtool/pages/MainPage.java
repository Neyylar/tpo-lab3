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
        findLoginFields();
    }
    private void findLoginFields(){
        form = driver.$x("//descendant::form[contains(@class, 'form-login')]");
        usernameField = form.$x("descendant::input[@name='login']");
        passwordField = form.$x("descendant::input[@name='pass']");
        loginButton = form.$x("descendant::button[@type='submit']");
    }

    public void sendLoginData() {
        usernameField.scrollTo().click();
        usernameField.sendKeys("natashata7sen@gmail.com");
        passwordField.scrollTo().click();
        passwordField.sendKeys("5LGrHF8g");
        loginButton.click();
    }

    public void sendIncorrectLoginData() {
        usernameField.scrollTo().click();
        usernameField.sendKeys("natashata7sen@gmail.com");
        passwordField.scrollTo().click();
        passwordField.sendKeys("12345678");
        loginButton.click();
    }
    public String getEmailLoginBLock(){
        return driver.$x("//div[contains(@class, 'login-block')]/div/div/span[contains(@class, 'text-primary')]").getText();
    }
    public String getLoginButtonText(){
        return loginButton.getText();
    }
}
