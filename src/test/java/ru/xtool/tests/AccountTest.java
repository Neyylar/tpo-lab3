package ru.xtool.tests;

import com.codeborne.selenide.SelenideDriver;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.xtool.pages.AccountPage;
import ru.xtool.pages.MainPage;


public class AccountTest {
    // private static SelenideDriver driver = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    private static MainPage mainPage;
    private static AccountPage account;

    static void loginAndOpenAccountPage(SelenideDriver driver) {
        mainPage = new MainPage(driver);
        account = new AccountPage(driver);
        driver.open(MainPage.url);
        mainPage.sendLoginData();
        sleep(1000);
        driver.open(AccountPage.url);
    }

    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(1)
    public void testChangePassword(SelenideDriver driver) {
        loginAndOpenAccountPage(driver);
        account.testChangePassword();
        assertEquals(driver.switchTo().alert().getText(), "На Ваш e-mail отправлено письмо для подтверждения операции");
    }
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(2)
    public void testChangePasswordDiffFields(SelenideDriver driver) {
        loginAndOpenAccountPage(driver);
        account.testChangePasswordDifferentValues();
        assertEquals(driver.switchTo().alert().getText(), "Пароли не совпадают");
    }
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(3)
    public void testChangePasswordWrongPass(SelenideDriver driver) {
        loginAndOpenAccountPage(driver);
        account.testChangePasswordEmptyFields();
        assertEquals(driver.switchTo().alert().getText(), "Укажите пароль и его подтверждение");
    }
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(4)
    public void logout(SelenideDriver driver) {
        loginAndOpenAccountPage(driver);
        account.logout();
        assertEquals(mainPage.getLoginButtonText(), "Войти");
    }
    @ParameterizedTest
    @ArgumentsSource(WebDriverArgumentProvider.class)
    @Order(5)
    public void testChecks(SelenideDriver driver) {
        loginAndOpenAccountPage(driver);
        account.testChecks();
        assertEquals(account.getCheckedUrl(), "github.com");
    }
}

