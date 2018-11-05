package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDown extends BaseElemnet {

    private final By optionLocator = By.xpath("//*[@data-qa-file='UIDropdownSelectItemComponent']");

    private final By selectedLocator = By.xpath("");

    public DropDown(WebElement element) {
        super(element);
    }

    public boolean selectValue(String value) {
        element.click();
        List<WebElement> options = element.findElements(optionLocator);
        for (WebElement option : options) {
            if (option.getAttribute("innerText").contains(value)) {
                option.click();
            }
        }
        return true;
    }
}
