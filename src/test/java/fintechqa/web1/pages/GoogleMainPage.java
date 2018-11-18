package fintechqa.web1.pages;

import fintechqa.web1.elements.DataList;
import fintechqa.web1.elements.InputBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleMainPage extends BasePage {

    private final By inputSearchLocator = By.xpath("//input[@name='q']");
    private final InputBlock inputSearch;

    private final By promptsLocator = By.xpath("//ul[@role='listbox']");
    private final By optionPromptsLocator = By.xpath(".//*[@role='option']");
    private final DataList prompts;

    public GoogleMainPage(WebDriver driver) {
        super(driver);
        inputSearch = new InputBlock(driver, inputSearchLocator);
        prompts = new DataList(driver, promptsLocator, optionPromptsLocator);
    }

    public void open() {
        driver.navigate().to("https://www.google.ru/");
        isTitleContainsText("Google");
    }

    public void openSearchByPartValue(String partValue, String fullValue) {
        inputSearch.clear();
        inputSearch.enterText(partValue);
        prompts.clickByValue(fullValue);
        wait.until(d -> d.getTitle().equals(fullValue.toLowerCase() + " - Поиск в Google"));
    }


}
