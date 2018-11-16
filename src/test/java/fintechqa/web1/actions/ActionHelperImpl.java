package fintechqa.web1.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionHelperImpl implements ActionHelper {

    private WebDriverWait wait;

    public ActionHelperImpl(WebDriverWait wait) {
        this.wait = wait;
    }

    @Override
    public boolean textToBePresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.textToBePresentInElement(element, text)
        ));
    }

    @Override
    public WebElement isPresent(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement isClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
