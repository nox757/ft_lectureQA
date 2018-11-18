package fintechqa.web1.pages;

import fintechqa.web1.elements.DataList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleResultsPage extends BasePage {

    private final By resultsLocator = By.xpath("//*[@id='search']//*[@class='srg']//*[@class='r']");
    private final By optionResultLocator = By.xpath(".//a[not(@class)]");
    private final DataList linkResults;

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
        this.linkResults = new DataList(driver, resultsLocator, optionResultLocator);
    }

    public void clickByHref(String href) {
       linkResults.clickByAttribute( "href", href);
    }
}
