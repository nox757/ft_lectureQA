package fintechqa.web1.elements;

import org.openqa.selenium.WebElement;

abstract class BaseElement {
    WebElement element;

    BaseElement(WebElement element) {
        this.element = element;
    }

}
