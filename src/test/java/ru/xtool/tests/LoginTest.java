package ru.xtool.tests;
import com.codeborne.selenide.SelenideDriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.xtool.pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

  @BeforeEach
  public void setUp() {

  }
  @ParameterizedTest
  @ArgumentsSource(WebDriverArgumentProvider.class)
  public void loginWithCorrectCredentials(SelenideDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.open(MainPage.url);
    mainPage.sendLoginData();
    assertEquals(mainPage.getEmailLoginBLock(), "natashata7sen@gmail.com");
  }

  @ParameterizedTest
  @ArgumentsSource(WebDriverArgumentProvider.class)
  public void loginWithInCorrectCredentials(SelenideDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.open(MainPage.url);
    mainPage.sendIncorrectLoginData();
    assertEquals(driver.switchTo().alert().getText(), "Не верно задан пароль");
  }
}

