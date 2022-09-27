package ru.xtool.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

public class TokensPage {
    public static String url = "https://xtool.ru/pay";
    private SelenideDriver driver;
    SelenideElement paymentBlock;
    SelenideElement paymentAmountInput;
    SelenideElement paymentEmailInput1;
    SelenideElement paymentEmailInput2;
    SelenideElement paymentSubmit;
    public TokensPage(SelenideDriver driver) {
        this.driver = driver;
        findPaymentFields();
    }
    private void findPaymentFields(){
        paymentBlock = driver.$x("//descendant::div[contains(@class, 'buy-menu center')]");
        paymentAmountInput = paymentBlock.$x("descendant::input[@name='sum']");
        paymentEmailInput1 = paymentBlock.$x("descendant::input[@name='account']");
        paymentEmailInput2 = paymentBlock.$x("descendant::input[@name='customerEmail']");
        paymentSubmit = paymentBlock.$x("descendant::input[contains(@class, 'inputSubmit btn-md btn-primary')]");
    }

    public void typeInputAmount(int amount) {
        paymentAmountInput.scrollTo().click();
        paymentAmountInput.sendKeys(String.valueOf(amount));
    }
    public void typeEmail(String email) {
        paymentEmailInput1.scrollTo().click();
        paymentEmailInput1.sendKeys(email);
        paymentEmailInput2.scrollTo().click();
        paymentEmailInput2.sendKeys(email);
    }
    public void submitPayment() {
        paymentSubmit.scrollTo().click();
    }
}
