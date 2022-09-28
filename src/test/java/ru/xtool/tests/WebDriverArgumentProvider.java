package ru.xtool.tests;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class WebDriverArgumentProvider implements ArgumentsProvider {

    private final SelenideDriver chromeDriver = new SelenideDriver(new SelenideConfig().browser("chrome").browserSize("1280x1024").timeout(20000));
    private final SelenideDriver firefoxDriver = new SelenideDriver(new SelenideConfig().browser("firefox").browserSize("1280x1024").timeout(20000));

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(chromeDriver),
                Arguments.of(firefoxDriver)
        );
    }
}
