package fintechqa.web1.elements;

import fintechqa.web1.actions.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextBlock extends BaseElement {

    private final By TEXT_LOCATOR;

    public TextBlock(WebElement element, ActionHelper actionHelper) {
        this(element, actionHelper, By.xpath("self::node()"));
    }

    public TextBlock(WebElement element, ActionHelper actionHelper, By textLocator) {
        super(element, actionHelper);
        TEXT_LOCATOR = textLocator;
    }


}
