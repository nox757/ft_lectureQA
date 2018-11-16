package fintechqa.web1.actions;

import org.openqa.selenium.WebElement;

public interface ActionHelper {
    boolean textToBePresent(WebElement element, String text);
    WebElement isPresent(WebElement element);
    WebElement isClickable(WebElement element);
}
