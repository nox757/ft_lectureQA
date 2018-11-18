package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseElement {

    final WebDriver driver;
    protected final By BASE_LOCATOR;
    protected final WebDriverWait wait;

    protected BaseElement(WebDriver driver, By baseLocator) {
        this.driver = driver;
        this.BASE_LOCATOR = baseLocator;
        this.wait = new WebDriverWait(this.driver, 15);

    }

    public String getText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(BASE_LOCATOR)).getText();
    }

    public boolean containsText(String value) {
        return wait.until(d -> {
            return getText().contains(value);
        });
    }

    public boolean notContainsText(String value) {
        return wait.until(d -> {
            return !getText().contains(value);
        });
    }
}
