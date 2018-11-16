package fintechqa.web1.elements;

import fintechqa.web1.actions.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    private final By TEXT_LOCATOR;
    private final By CLICK_LOCATOR;

    public Button(WebElement element, ActionHelper actionHelper) {
        this(element, actionHelper, By.xpath("self::node()"), By.xpath("self::node()"));
    }

    public Button(WebElement element, ActionHelper actionHelper, By textLocator, By clickLocator) {
        super(element, actionHelper);
        TEXT_LOCATOR = textLocator;
        CLICK_LOCATOR = clickLocator;
    }

    public String getText() {
        WebElement textElement = element.findElement(TEXT_LOCATOR);
        return actionHelper.isPresent(textElement).getAttribute("innerText");
    }

    public void click() {
        actionHelper.isClickable(element.findElement(CLICK_LOCATOR)).click();
    }
}
