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
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  static SelenideDriver chrome;
  static SelenideDriver firefox;

  public static List<SelenideDriver> getDrivers() {
    if (chrome == null)
      chrome = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    if (firefox == null)
      firefox = new SelenideDriver(new SelenideConfig().browser("firefox").browserSize("1280x1024").timeout(20000));
    return Stream.of(chrome, firefox).filter(i -> i != null).collect(Collectors.toList());
  }
  @ParameterizedTest
  @MethodSource("getDrivers")
  public void testChecks(SelenideDriver driver){
    MainPage mainPage = new MainPage(driver);
    AccountPage account = new AccountPage(driver);
    ChecksPage check = new ChecksPage(driver);
    driver.open(MainPage.url);
    mainPage.sendLoginData();
    sleep(1000);
    driver.open(AccountPage.url);
    account.testChecks();

    //driver.open(check.url);

  }

  @ParameterizedTest
  @MethodSource("getDrivers")
  public void testChangePassword(SelenideDriver driver){
    MainPage mainPage = new MainPage(driver);
    AccountPage account = new AccountPage(driver);
    driver.open(MainPage.url);
    mainPage.sendLoginData();
    sleep(1000);
    driver.open(AccountPage.url);
    account.testChangePassword();

  }
}
//  @BeforeAll
//  public void setUp() {
//    driver = (WebDriver)getDrivers().get(0);
//    js = (JavascriptExecutor) driver;
//    vars = new HashMap<String, Object>();
//  }
//  @AfterAll
//  public void tearDown() {
//    driver.quit();
//  }
//  @Test
//  public void account() {
//    driver.get("https://xtool.ru/");
//    driver.manage().window().setSize(new Dimension(1370, 786));
//    driver.findElement(By.id("btn-account")).click();
//    driver.findElement(By.name("newpass1")).click();
//    driver.findElement(By.name("newpass1")).sendKeys("5LGrHF8g");
//    driver.findElement(By.name("newpass2")).click();
//    driver.findElement(By.name("newpass2")).sendKeys("12345678");
//    driver.findElement(By.cssSelector("#form-account-change-pass > input")).click();
//    assertEquals(driver.switchTo().alert().getText(), "Пароли не совпадают");
//    driver.findElement(By.name("newpass2")).click();
//    driver.findElement(By.cssSelector("#form-account-change-pass tr:nth-child(2)")).click();
//    driver.findElement(By.name("newpass2")).sendKeys("5LGrHF8g");
//    driver.findElement(By.cssSelector("#form-account-change-pass > input")).click();
//    assertEquals(driver.switchTo().alert().getText(), "На Ваш e-mail отправлено письмо для подтверждения операции");
//    driver.findElement(By.linkText("Мои проверки")).click();
//    driver.findElement(By.linkText("Аккаунт")).click();
//    driver.findElement(By.id("btn-account")).click();
//  }
//}
