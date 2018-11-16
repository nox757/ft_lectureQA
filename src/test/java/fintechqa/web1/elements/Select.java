package fintechqa.web1.elements;

import fintechqa.web1.actions.ActionHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Select extends BaseElement {

    private final By OPTION_LOCATOR;

    private final By SELECTED_LOCATOR;

    public Select(WebElement element, ActionHelper actionHelper) {
        this(element, actionHelper,
                By.xpath(".//*[@data-qa-file='UIDropdownSelectItemComponent' and contains(@class, '-text')]"),
                By.xpath(".//*[@data-qa-file='UISelectTitle' and contains(@class, '-text')]"));
    }

    public Select(WebElement element, ActionHelper actionHelper, By optionLocator, By selectedLocator) {
        super(element, actionHelper);
        OPTION_LOCATOR = optionLocator;
        SELECTED_LOCATOR = selectedLocator;
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
