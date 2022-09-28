package ru.xtool.tests;// Generated by Selenium IDE

import com.codeborne.selenide.SelenideDriver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.xtool.pages.MainPage;
import ru.xtool.pages.TokensPage;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTokensTest {
    // private static SelenideDriver driver = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    private static MainPage mainPage;
    private static TokensPage tokensPage;

    static void login(SelenideDriver driver) {
        mainPage = new MainPage(driver);
        driver.open(MainPage.url);
        mainPage.sendLoginData();
        sleep(1000);
    }

    static void openTokenPage(SelenideDriver driver) {
        tokensPage = new TokensPage(driver);
        driver.open(TokensPage.url);
    }

    @DisplayName("Redirects to payment page for authorized user and correct amount")
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(1)
    public void testBuyTokensAuthorized(SelenideDriver driver) {
        login(driver);
        openTokenPage(driver);
        tokensPage.typeInputAmount(500);
        tokensPage.submitPayment();
        sleep(1000);
        assertEquals(driver.getAndCheckWebDriver().getWindowHandles().size(), 2);
    }
    @DisplayName("Redirects to payment page and shows alert with wrong amount and fixes amount")
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(2)
    public void testBuyTokensAuthorizedWrongAmount(SelenideDriver driver) {
        login(driver);
        openTokenPage(driver);
        tokensPage.typeInputAmount(10);
        tokensPage.submitPayment();
        assertEquals(driver.switchTo().alert().getText(), "Минимальная сумма пополнения 300 руб.");
        driver.switchTo().alert().dismiss();
        assertEquals(driver.getAndCheckWebDriver().getWindowHandles().size(), 2);

    }
    @DisplayName("Doesn't redirects to payment page and shows alert with wrong amount without email")
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(3)
    public void testBuyTokensUnauthorizedWrongAmount(SelenideDriver driver) {
        openTokenPage(driver);
        tokensPage.typeInputAmount(100);
        tokensPage.submitPayment();
        assertEquals(driver.switchTo().alert().getText(), "Минимальная сумма пополнения 300 руб.");
        driver.switchTo().alert().accept();
        assertEquals(driver.getAndCheckWebDriver().getWindowHandles().size(), 1);
    }

    @DisplayName("Doesn't redirects to payment page without email")
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(4)
    public void testBuyTokensUnauthorizedWithoutEmail(SelenideDriver driver) {
        openTokenPage(driver);
        tokensPage.typeInputAmount(500);
        tokensPage.submitPayment();
        assertEquals(driver.getAndCheckWebDriver().getWindowHandles().size(), 1);
    }

    @DisplayName("Redirects to payment page with email")
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(5)
    public void testBuyTokensUnauthorized(SelenideDriver driver) {
        openTokenPage(driver);
        tokensPage.typeEmail("natashata7sen@gmail.com");
        tokensPage.typeInputAmount(500);
        tokensPage.submitPayment();
        assertEquals(driver.getAndCheckWebDriver().getWindowHandles().size(), 2);
    }
}
