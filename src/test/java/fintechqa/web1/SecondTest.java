package fintechqa.web1;

import fintechqa.web1.elements.*;
import fintechqa.web1.pages.GoogleMainPage;
import fintechqa.web1.pages.GoogleResultsPage;
import fintechqa.web1.pages.TinkoffTariffsPage;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.junit.Assert.*;

public class SecondTest extends BaseRunner {


    @Test
    public void test1() throws IOException {
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.open();
        googleMainPage.openSearchByPartValue("мобайл тинькофф", "тинькофф мобайл тарифы");

        GoogleResultsPage googleResultsPage = new GoogleResultsPage(driver);
        googleResultsPage.clickByHref("https://www.tinkoff.ru/mobile-operator/tariffs/");
        googleMainPage.switchToTab("Тарифы Тинькофф Мобайл");
        TinkoffTariffsPage tinkoffTariffsPage = new TinkoffTariffsPage(driver);
        tinkoffTariffsPage.isTitleContainsText("Тарифы Тинькофф Мобайл");

        googleResultsPage.switchToMainTab();
        googleResultsPage.closeTab();

        tinkoffTariffsPage.switchToMainTab();
        tinkoffTariffsPage.isUrl("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

    @Test
    public void test2() throws IOException {
        TinkoffTariffsPage tinkoffTariffsPage = new TinkoffTariffsPage(driver);
        tinkoffTariffsPage.open();
        tinkoffTariffsPage.confirmDefaultRegion();
        tinkoffTariffsPage.checkRegion("Москва");
        tinkoffTariffsPage.reloadPage();
        tinkoffTariffsPage.checkRegion("Москва");
        String amountMoscow = tinkoffTariffsPage.getAmount();
        tinkoffTariffsPage.changeRegion("Краснодар");
        tinkoffTariffsPage.checkRegion("Краснодар");
        assertTrue(tinkoffTariffsPage.compareAmount(amountMoscow));



        Select internetSelect = new Select(driver, "Интернет");
        Select callsSelect = new Select(driver, "Звонки");
        internetSelect.selectValue("Безлимитный интернет");
        callsSelect.selectValue("Безлимитные минуты");
        CheckBox modemCheckBox = new CheckBox(driver, "Режим модема");
        CheckBox unlimitedSmsCheckBox = new CheckBox(driver, "Безлимитные SMS");
//        if(!modemCheckBox.isSelected()) {
//            modemCheckBox.click();
//        }
//        if(!unlimitedSmsCheckBox.isSelected()) {
//            unlimitedSmsCheckBox.click();
//        }
//        String amountKrasnodar = amountTextBlock.getText();
//
//        selectedRegion.click();
//        cityList.clickByPartValue("Москва");
//        assertTrue(selectedRegion.containsText("Москва"));
//        internetSelect.selectValue("Безлимитный интернет");
//        callsSelect.selectValue("Безлимитные минуты");
//        if(!modemCheckBox.isSelected()) {
//            modemCheckBox.click();
//        }
//        if(!unlimitedSmsCheckBox.isSelected()) {
//            unlimitedSmsCheckBox.click();
//        }
//        assertTrue(amountTextBlock.containsText(amountKrasnodar));

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

    }
}
