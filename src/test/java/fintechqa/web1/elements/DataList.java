package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataList extends BaseElement {

    private final By OPTION_LOCATOR;

    public DataList(WebDriver driver, By baseLocator) {
        this(driver, baseLocator, By.xpath(".//li"));
    }

    public DataList(WebDriver driver, By baseLocator, By optionLocator) {
        super(driver, baseLocator);
        this.OPTION_LOCATOR = optionLocator;
    }

    public void clickByAttribute(String attribute, String value) {
        WebElement element = driver.findElement(BASE_LOCATOR);
        wait.until(d -> {
            List<WebElement> options = element.findElements(OPTION_LOCATOR);
            for (WebElement option : options) {
                if (option.getAttribute(attribute).equals(value)) {
                    option.click();
                    return true;
                }
            }
            return false;
        });
    }

    public void clickByValue(String value) {
        WebElement element = driver.findElement(BASE_LOCATOR);
        wait.until(d -> {
            List<WebElement> options = element.findElements(OPTION_LOCATOR);
            for (WebElement option : options) {
                if (option.getText().equals(value)) {
                    option.click();
                    return true;
                }
            }
            return false;
        });
    }

    public void clickByPartValue(String value) {
        WebElement element = driver.findElement(BASE_LOCATOR);
        wait.until(d -> {
            List<WebElement> options = element.findElements(OPTION_LOCATOR);
            for (WebElement option : options) {
                if (option.getText().contains(value)) {
                    option.click();
                    return true;
                }
            }
            return false;
        });
    }
}
