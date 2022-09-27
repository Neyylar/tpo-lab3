package ru.xtool.pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

public class AnalyzePage {
    public static String url = "https://xtool.ru/analyze/";
    private SelenideDriver driver;
    SelenideElement analyzeBlock;
    SelenideElement urlInput;
    SelenideElement submitButton;
    SelenideElement robotCheck;
    public AnalyzePage(SelenideDriver driver) {
        this.driver = driver;
        findAnalyzeFields();
    }
    public void findAnalyzeFields (){
        analyzeBlock = driver.$x("//descendant::div[contains(@class, 'check-trust-form')]");
        urlInput = analyzeBlock.$x("descendant::input[contains(@name, 'site')]");
        submitButton = analyzeBlock.$x("descendant::button[contains(@class, 'btn')]");
        robotCheck = analyzeBlock.$x("descendant::span[contains(@class, 'recaptcha-checkbox-border')]");
    }

    public void analyzeCite(String url){
        urlInput.scrollTo().click();
        urlInput.sendKeys(url);
        //robotCheck.scrollTo().click();
        submitButton.scrollTo().click();
    }
    public String getAnalyzedUrl(){
        return driver.$x("//descendant::p[contains(@class, 'trust-site-link')]").getText();
    }
}
