package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBlock extends BaseElement {

    private final By TEXT_LOCATOR;

    public TextBlock(WebDriver driver, By baseLocator) {
        this(driver, baseLocator, By.xpath("/self::node()"));
    }

    public TextBlock(WebDriver driver, By baseLocator, By textLocator) {
        super(driver, baseLocator);
        TEXT_LOCATOR = textLocator;
    }


}
