package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Select extends BaseElement {

    private final static By OPTION_LOCATOR = By.xpath(".//*[@data-qa-file='UIDropdownSelectItemComponent' and contains(@class, '-text')]");

    private final static By SELECTED_LOCATOR = By.xpath(".//*[@data-qa-file='UISelectTitle' and contains(@class, '-text')]");

    public Select(WebElement element) {
        super(element);
    }

    public boolean selectValue(String value) {
        element.click();
        List<WebElement> options = element.findElements(OPTION_LOCATOR);
        for (WebElement option : options) {
            if (option.getAttribute("innerText").contains(value)) {
                option.click();
                if(element.findElement(SELECTED_LOCATOR).getAttribute("innerText").contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getSelectedValue() {
        return element.findElement(SELECTED_LOCATOR).getAttribute("innerText");
    }
}
