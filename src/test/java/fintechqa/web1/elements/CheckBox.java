package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class CheckBox extends BaseElement {

    private final By TEXT_LOCATOR;
    private final By CLICK_LOCATOR;
    private final By INPUT_LOCATOR;

    public CheckBox(WebDriver driver, String innerText) {
        this(driver, By.xpath(".//*[@data-qa-file='UICheckbox' and not(ancestor::*[@data-qa-file='UICheckbox'])]" + String.format("[.//*[contains(text(),'%s')]]", innerText)));
    }

    public CheckBox(WebDriver driver, By baseLocator) {
        this(driver, baseLocator,
                By.xpath(".//label//*[contains(@class, '__text')]"),
                By.xpath(".//label//*[contains(@class, '__check')]"),
                By.xpath(".//input[@type='checkbox']")
        );
    }

    public CheckBox(WebDriver driver, By baseLocator, By textLocator, By clickLocator, By inputLocator) {
        super(driver, baseLocator);
        TEXT_LOCATOR = textLocator;
        CLICK_LOCATOR = clickLocator;
        INPUT_LOCATOR = inputLocator;
    }

    public boolean isSelected() {
        return driver.findElement(new ByChained(BASE_LOCATOR, INPUT_LOCATOR)).isSelected();
    }

    public String getText() {
        return driver.findElement(new ByChained(BASE_LOCATOR, TEXT_LOCATOR)).getText();
    }

    public void setValue(boolean state) throws IOException {
        if (state != isSelected()) {
            this.click();
        }
    }

    public void click() throws IOException {
        boolean StateBeforeClick = isSelected();
        WebElement clickableElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(new ByChained(BASE_LOCATOR, CLICK_LOCATOR))
        );
        wait.until(ExpectedConditions.elementToBeClickable(clickableElement)).click();
        if (StateBeforeClick == isSelected()) {
            throw new IOException("No change State of CheckBox");
        }
    }

}
