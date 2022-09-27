package ru.xtool.tests;// Generated by Selenium IDE
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import ru.xtool.pages.AccountPage;
import ru.xtool.pages.MainPage;
import ru.xtool.pages.TokensPage;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class BuyTokensTest {
  private static SelenideDriver driver = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
  private static MainPage mainPage;
  private static TokensPage tokensPage;
  static void login() {
    mainPage = new MainPage(driver);
    driver.open(MainPage.url);
    mainPage.sendLoginData();
    sleep(1000);
  }
  static void openTokenPage() {
    tokensPage = new TokensPage(driver);
    driver.open(TokensPage.url);
  }
  //TODO check driver redirect!
  @Test
  @Order(1)
  public void testBuyTokensAuthorized() {
    login();
    openTokenPage();
    tokensPage.typeInputAmount(500);
    tokensPage.submitPayment();
    sleep(1000);
  }
  @Test
  @Order(1)
  public void testBuyTokensAuthorizedWrongAmount() {
    login();
    openTokenPage();
    tokensPage.typeInputAmount(100);
    tokensPage.submitPayment();
    assertEquals(driver.switchTo().alert().getText(), "Минимальная сумма пополнения 300 руб.");
  }

  @Test
  @Order(1)
  public void testBuyTokensUnauthorizedWrongAmount() {
    openTokenPage();
    tokensPage.typeInputAmount(100);
    tokensPage.submitPayment();
    assertEquals(driver.switchTo().alert().getText(), "Минимальная сумма пополнения 300 руб.");
  }
  @Test
  @Order(1)
  public void testBuyTokensUnauthorizedWithoutEmail() {
    openTokenPage();
    tokensPage.typeInputAmount(500);
    tokensPage.submitPayment();
  }
  @Test
  @Order(1)
  public void testBuyTokensUnauthorized() {
    openTokenPage();
    tokensPage.typeEmail("natashata7sen@gmail.com");
    tokensPage.typeInputAmount(500);
    tokensPage.submitPayment();
  }
}
