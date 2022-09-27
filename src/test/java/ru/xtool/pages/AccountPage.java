package ru.xtool.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

public class AccountPage {

    public static String url = "https://xtool.ru/account/";
    private SelenideDriver driver;
    SelenideElement menu;
    SelenideElement checks;
    SelenideElement changePassTable;
    SelenideElement newPass1;
    SelenideElement newPass2;
    SelenideElement submitPass;
    public AccountPage(SelenideDriver driver) {
        this.driver = driver;
        findAccountFields();
    }

    public void findAccountFields (){
        menu = driver.$x("//descendant::div[contains(@class, 'blog-menu')]");
        checks = menu.$x("descendant::a[contains(text(), 'Мои проверки')]");
        changePassTable = driver.$x("//descendant::table[contains(@class, 'account-table')]");
        newPass1 = changePassTable.$x("descendant::tbody/tr/td/input[@name='newpass1']");
        newPass2 = changePassTable.$x("descendant::tbody/tr/td/input[@name='newpass2']");
        submitPass = changePassTable.$x("following-sibling::input");
    }

    public String getCheckedUrl() {
        return driver.$x("//descendant::td/a").text();
    }
    public void testChecks() {
        checks.scrollTo().click();
    }

    public void testChangePassword() {
        newPass1.scrollTo().click();
        newPass1.sendKeys("5LGrHF8g");
        newPass2.scrollTo().click();
        newPass2.sendKeys("5LGrHF8g");
        submitPass.scrollTo().click();
    }

    public void testChangePasswordDifferentValues() {
        newPass1.scrollTo().click();
        newPass1.sendKeys("5LGrHF8g");
        newPass2.scrollTo().click();
        newPass2.sendKeys("12345678");
        submitPass.scrollTo().click();
    }
    public void testChangePasswordEmptyFields() {
        submitPass.scrollTo().click();
    }
    public void logout() {
        SelenideElement logoutButton = driver.$x("//descendant::a[contains(@class, 'btn btn-xs default-button btn-block')]");
        logoutButton.scrollTo().click();
    }
}
