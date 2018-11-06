package fintechqa.web1.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CheckBox extends BaseElement {

    private final static By TEXT_LOCATOR = By.xpath("//label//*[contains(@class, 'description')]");
    private final static By CLICK_LOCATOR = By.xpath("");
    private final static By INPUT_LOCATOR = By.xpath("//input[@type='checkbox']");

    public CheckBox(WebElement element) {
        super(element);
    }

    public boolean isSelected() {
        return element.findElement(INPUT_LOCATOR).isSelected();
    }

    public String getLabelText() {
        return element.findElement(TEXT_LOCATOR).getText();
    }

    public void click() throws IOException {
        boolean StateBeforeClick = isSelected();
        element.findElement(CLICK_LOCATOR).click();
        if(StateBeforeClick == isSelected()) {
            throw new IOException("No change State of CheckBox");
        }
    }

}
