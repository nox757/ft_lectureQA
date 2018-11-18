package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Select extends BaseElement {

    private final By OPTION_LOCATOR;
    private final By SELECTED_LOCATOR;

    public Select(WebDriver driver, String innerText) {
        this(driver, By.xpath(".//*[@data-qa-file='UIDropdownSelect']" + String.format("[.//*[contains(text(),'%s')]]", innerText)));
    }

    public Select(WebDriver driver, By baseLocator) {
        this(driver, baseLocator,
                By.xpath(".//*[@data-qa-file='UIDropdownSelectItemComponent' and contains(@class, '-text')]"),
                By.xpath(".//*[@data-qa-file='UISelectTitle' and contains(@class, '-text')]"));
    }

    public Select(WebDriver driver, By baseLocator, By optionLocator, By selectedLocator) {
        super(driver, baseLocator);
        OPTION_LOCATOR = optionLocator;
        SELECTED_LOCATOR = selectedLocator;
    }

    public boolean selectValue(String value) {
        WebElement element = driver.findElement(BASE_LOCATOR);
        element.click();
        wait.until(d -> {
            List<WebElement> options = element.findElements(OPTION_LOCATOR);
            for (WebElement option : options) {
                if (option.getText().contains(value)) {
                    option.click();
                    if (getSelectedValue().contains(value)) {
                        return true;
                    }
                }
            }
            return false;
        });
        return false;
    }

    public String getSelectedValue() {
        return driver.findElement(new ByChained(BASE_LOCATOR, SELECTED_LOCATOR)).getAttribute("innerText");
    }

}
