package ru.xtool.tests;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.xtool.pages.MainPage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginTest {
  static SelenideDriver chrome;
  static SelenideDriver firefox;

  public static List<SelenideDriver> getDrivers() {
    if (chrome == null)
      chrome = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    if (firefox == null)
      firefox = new SelenideDriver(new SelenideConfig().browser("firefox").browserSize("1280x1024").timeout(20000));
    return Stream.of(chrome, firefox).filter(i -> i != null).collect(Collectors.toList());
  }

  @BeforeEach
  public void setUp() {

  }
  @ParameterizedTest
  @MethodSource("getDrivers")
  public void loginWithCorrectCredentials(SelenideDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.open(MainPage.url);
    mainPage.sendLoginData();
  }

  @ParameterizedTest
  @MethodSource("getDrivers")
  public void loginWithInCorrectCredentials(SelenideDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.open(MainPage.url);
    mainPage.sendIncorrectLoginData();
  }
}

