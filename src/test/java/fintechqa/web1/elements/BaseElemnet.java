package fintechqa.web1.elements;

import org.openqa.selenium.WebElement;

abstract class BaseElemnet {

    WebElement element;

    BaseElemnet(WebElement element) {
        this.element = element;
    }
}
