package fintechqa.web1.elements;

import fintechqa.web1.actions.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CheckBox extends BaseElement {

    private final By TEXT_LOCATOR;
    private final By CLICK_LOCATOR;
    private final By INPUT_LOCATOR;

    public CheckBox(WebElement element, ActionHelper actionHelper) {
        this(element, actionHelper,
                By.xpath(".//label//*[contains(@class, '__text')]"),
                By.xpath(".//label//*[contains(@class, '__check')]"),
                By.xpath(".//input[@type='checkbox']")
        );
    }

    public CheckBox(WebElement element, ActionHelper actionHelper, By textLocator, By clickLocator, By inputLocator) {
        super(element, actionHelper);
        TEXT_LOCATOR = textLocator;
        CLICK_LOCATOR = clickLocator;
        INPUT_LOCATOR = inputLocator;
    }

    public boolean isSelected() {
        return element.findElement(INPUT_LOCATOR).isSelected();
    }

    public String getText() {
        return element.findElement(TEXT_LOCATOR).getText();
    }

    public void setValue(boolean state) throws IOException {
        if (state != isSelected()) {
            click();
        }
    }

    public void click() throws IOException {
        boolean StateBeforeClick = isSelected();
        WebElement clickableElement = element.findElement(CLICK_LOCATOR);
        actionHelper.isClickable(clickableElement).click();
        if (StateBeforeClick == isSelected()) {
            throw new IOException("No change State of CheckBox");
        }
    }

}
