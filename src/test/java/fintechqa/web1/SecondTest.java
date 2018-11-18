package fintechqa.web1;

import fintechqa.web1.elements.*;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SecondTest extends BaseRunner {


    @Test
    public void test1() throws IOException {
        driver.get("https://www.google.ru/");
        By inputSearchLocator = By.xpath("//input[@name='q']");
        InputBlock inputSearch = new InputBlock(driver, inputSearchLocator);
        inputSearch.clear();
        inputSearch.enterText("мобайл тинькофф");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By promptsLocator = By.xpath("//ul[@role='listbox']");
        By optionPromptsLocator = By.xpath(".//*[@role='option']");
        DataList prompts = new DataList(driver, promptsLocator, optionPromptsLocator);
        prompts.clickByValue("тинькофф мобайл тарифы");

        By resultsLocator = By.xpath("//*[@id='search']//*[@class='srg']//*[@class='r']");
        By optionResultLocator = By.xpath(".//a[not(@class)]");
        DataList linkResults = new DataList(driver, resultsLocator, optionResultLocator);
        linkResults.clickByAttribute( "href", "https://www.tinkoff.ru/mobile-operator/tariffs/");


        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() != 2) {
            throw new IOException("No opened second tab");
        }
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.titleIs("Тарифы Тинькофф Мобайл"));
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/",
                driver.getCurrentUrl()
        );
    }

    @Test
    public void test2() throws IOException {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        By confirmButtonLocator = By.xpath("//*[contains(@class,'MvnoRegionConfirmation__optionAgreement')]");
        Button confirmButton = new Button(driver, confirmButtonLocator);
        confirmButton.click();

        By selectedRegionLocator = By.xpath("//*[contains(@class, 'MvnoRegionConfirmation__title')]");
        Button selectedRegion = new Button(driver, selectedRegionLocator);
        assertThat(selectedRegion.getText(), CoreMatchers.containsString("Москва"));
        driver.navigate().refresh();
        assertThat(selectedRegion.getText(), CoreMatchers.containsString("Москва"));
        By amountTextLocator = By.xpath("//*[contains(@class, '_amountTitle_') and @data-qa-file='UITitle']");
        TextBlock amountTextBlock = new TextBlock(driver, amountTextLocator);
        String amountMoscow = amountTextBlock.getText();

        selectedRegion.click();
        By cityListLocator = By.xpath("//*[contains(@class, 'MobileOperatorRegionsPopup__listParts_')]");
        By cityElementListLocator = By.xpath(".//*[contains(@class, 'MobileOperatorRegionsPopup__region_')]");
        DataList cityList = new DataList(driver, cityListLocator, cityElementListLocator);
        cityList.clickByPartValue("Краснодар");
        assertTrue(selectedRegion.containsText("Краснодар"));
        assertTrue(amountTextBlock.notContainsText(amountMoscow));

        Select internetSelect = new Select(driver, "Интернет");
        Select callsSelect = new Select(driver, "Звонки");
        internetSelect.selectValue("Безлимитный интернет");
        callsSelect.selectValue("Безлимитные минуты");
        CheckBox modemCheckBox = new CheckBox(driver, "Режим модема");
        CheckBox unlimitedSmsCheckBox = new CheckBox(driver, "Безлимитные SMS");
        if(!modemCheckBox.isSelected()) {
            modemCheckBox.click();
        }
        if(!unlimitedSmsCheckBox.isSelected()) {
            unlimitedSmsCheckBox.click();
        }
        String amountKrasnodar = amountTextBlock.getText();

        selectedRegion.click();
        cityList.clickByPartValue("Москва");
        assertTrue(selectedRegion.containsText("Москва"));
        internetSelect.selectValue("Безлимитный интернет");
        callsSelect.selectValue("Безлимитные минуты");
        if(!modemCheckBox.isSelected()) {
            modemCheckBox.click();
        }
        if(!unlimitedSmsCheckBox.isSelected()) {
            unlimitedSmsCheckBox.click();
        }
        assertTrue(amountTextBlock.containsText(amountKrasnodar));

    }

    @Test
    public void test3() throws IOException {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        Select internetSelect = new Select(driver, "Интернет");
        Select callsSelect = new Select(driver, "Звонки");
        internetSelect.selectValue("0 ГБ");
        callsSelect.selectValue("0 минут");
        CheckBox messageCheckBox = new CheckBox(driver, "Мессенджеры");
        CheckBox socialCheckBox = new CheckBox(driver, "Социальные сети");
        CheckBox musicCheckBox = new CheckBox(driver, "Музыка");
        CheckBox videoCheckBox = new CheckBox(driver, "Видео");
        CheckBox unlimitedSmsCheckBox = new CheckBox(driver, "Безлимитные SMS");
        messageCheckBox.setValue(false);
        socialCheckBox.setValue(false);
        musicCheckBox.setValue(false);
        videoCheckBox.setValue(false);
        unlimitedSmsCheckBox.setValue(false);

        By amountTextLocator = By.xpath("//*[contains(@class, '_amountTitle_') and @data-qa-file='UITitle']");
        TextBlock amountTextBlock = new TextBlock(driver, amountTextLocator);
        assertTrue(amountTextBlock.containsText("Общая цена: 0 ₽"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
