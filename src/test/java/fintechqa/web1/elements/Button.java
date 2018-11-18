package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Button extends BaseElement {

    private final By TEXT_LOCATOR;
    private final By CLICK_LOCATOR;

    public Button(WebDriver driver, By baseLocator) {
        this(driver, baseLocator, By.xpath("./self::node()"), By.xpath("./self::node()"));
    }

    public Button(WebDriver driver, By baseLocator, By textLocator, By clickLocator) {
        super(driver, baseLocator);
        TEXT_LOCATOR = textLocator;
        CLICK_LOCATOR = clickLocator;
    }

    public String getText() {
        WebElement textElement = wait.until(ExpectedConditions.presenceOfElementLocated(new ByChained(BASE_LOCATOR, TEXT_LOCATOR)));
        return textElement.getAttribute("innerText");
    }

    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(new ByChained(BASE_LOCATOR, CLICK_LOCATOR))).click();
    }
}
