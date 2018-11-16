package fintechqa.web1.elements;

import fintechqa.web1.actions.ActionHelper;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {

    WebElement element;
    ActionHelper actionHelper;

    protected BaseElement(WebElement element, ActionHelper actionHelper) {
        this.element = element;
        this.actionHelper = actionHelper;
    }

    public String getText() {
        return actionHelper.isPresent(element).getText();
    }

}
