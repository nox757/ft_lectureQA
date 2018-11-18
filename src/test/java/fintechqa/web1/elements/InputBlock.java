package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InputBlock extends BaseElement {

    public InputBlock(WebDriver driver, By baseLocator) {
        super(driver, baseLocator);
    }

    public void enterText(String value) {
        wait.until(d -> {
            driver.findElement(BASE_LOCATOR).sendKeys(value);
            if(getText().equals(value)) {
                return true;
            }
            return false;
        });
    }

    @Override
    public String getText() {
        return driver.findElement(BASE_LOCATOR).getAttribute("value");
    }

    public void clear() {
        WebElement element = driver.findElement(BASE_LOCATOR);
        element.clear();
        if(!getText().isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
    }

}
