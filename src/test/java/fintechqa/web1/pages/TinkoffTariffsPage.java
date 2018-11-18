package fintechqa.web1.pages;

import fintechqa.web1.elements.Button;
import fintechqa.web1.elements.DataList;
import fintechqa.web1.elements.TextBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class TinkoffTariffsPage extends BasePage {

    private final By confirmButtonLocator = By.xpath("//*[contains(@class,'MvnoRegionConfirmation__optionAgreement')]");
    private final Button confirmButton;

    private final By selectedRegionLocator = By.xpath("//*[contains(@class, 'MvnoRegionConfirmation__title')]");
    private final Button selectedRegion;

    private final By amountTextLocator = By.xpath("//*[contains(@class, '_amountTitle_') and @data-qa-file='UITitle']");
    private final TextBlock amountTextBlock;

    private final By cityListLocator = By.xpath("//*[contains(@class, 'MobileOperatorRegionsPopup__listParts_')]");
    private final By cityElementListLocator = By.xpath(".//*[contains(@class, 'MobileOperatorRegionsPopup__region_')]");
    private final DataList cityList ;


    public TinkoffTariffsPage(WebDriver driver) {
        super(driver);
        confirmButton = new Button(driver, confirmButtonLocator);
        selectedRegion = new Button(driver, selectedRegionLocator);
        amountTextBlock = new TextBlock(driver, amountTextLocator);
        cityList = new DataList(driver, cityListLocator, cityElementListLocator);
    }

    public void open() {
        driver.navigate().to("https://www.tinkoff.ru/mobile-operator/tariffs/");
        isTitleContainsText("Тарифы Тинькофф Мобайл");
    }

    public void confirmDefaultRegion() {
        confirmButton.click();
    }

    public boolean checkRegion(String region) {
        return wait.until(d -> {
            return selectedRegion.getText().contains(region);
        });
    }

    public void changeRegion(String region) {
        selectedRegion.click();
        cityList.clickByPartValue(region);
    }

    public String getAmount() {
        return amountTextBlock.getText();
    }

    public boolean compareAmount(String value) {
        return amountTextBlock.containsText(value);
    }
}
