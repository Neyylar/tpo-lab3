package ru.xtool.tests;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import ru.xtool.pages.AccountPage;
import ru.xtool.pages.ChecksPage;
import ru.xtool.pages.MainPage;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountTest {
    private static SelenideDriver driver = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    private static MainPage mainPage;
    private static AccountPage account;

    static void loginAndOpenAccountPage() {
        mainPage = new MainPage(driver);
        account = new AccountPage(driver);
        driver.open(MainPage.url);
        mainPage.sendLoginData();
        sleep(1000);
        driver.open(AccountPage.url);
    }

    @Test
    @Order(1)
    public void testChangePassword() {
        loginAndOpenAccountPage();
        account.testChangePassword();
        assertEquals(driver.switchTo().alert().getText(), "На Ваш e-mail отправлено письмо для подтверждения операции");
    }
    @Test
    @Order(2)
    public void testChangePasswordDiffFields() {
        loginAndOpenAccountPage();
        account.testChangePasswordDifferentValues();
        assertEquals(driver.switchTo().alert().getText(), "Пароли не совпадают");
    }
    @Test
    @Order(3)
    public void testChangePasswordWrongPass() {
        loginAndOpenAccountPage();
        account.testChangePasswordEmptyFields();
        assertEquals(driver.switchTo().alert().getText(), "Укажите пароль и его подтверждение");
    }
    @Test
    @Order(4)
    public void logout() {
        loginAndOpenAccountPage();
        account.logout();
        assertEquals(mainPage.getLoginButtonText(), "Войти");
    }
    @Test
    @Order(5)
    public void testChecks() {
        loginAndOpenAccountPage();
        account.testChecks();
        assertEquals(account.getCheckedUrl(), "github.com");
    }
}

