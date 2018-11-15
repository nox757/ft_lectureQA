package fintechqa.web1.elements;

import org.openqa.selenium.WebElement;

public class BaseElement {
    WebElement element;

    BaseElement(WebElement element) {
        this.element = element;
    }

    public String getText() {
        return element.getText();
    }

}
