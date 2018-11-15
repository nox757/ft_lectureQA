package fintechqa.web1;

import fintechqa.web1.elements.CheckBox;
import fintechqa.web1.elements.EnumElements;
import fintechqa.web1.elements.Select;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class SecondTest extends BaseRunner {


    @Test
    public void test1() throws IOException {
        driver.get("https://www.google.ru/");
        driver.findElement(By.xpath("//input[@id='lst-ib']")).clear();
        driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("мобайл тинькофф");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By findPhraseLocator = By.xpath("//ul[@class='sbsb_b']//li");
        wait.until(d -> {
            List<WebElement> prompts = d.findElements(findPhraseLocator);
            for (WebElement prompt : prompts) {
                if (prompt.getText().equals("мобайл тинькофф тарифы")) {
                    prompt.click();
                    return true;
                }
            }
            return false;
        });

        By linkResultLocator = By.xpath("//*[@id='search']//*[@class='srg']//*[@class='r']//a[not(@class)]");
        wait.until(d -> {
            List<WebElement> linkResultsList = d.findElements(linkResultLocator);
            for (WebElement el : linkResultsList) {
                if (el.getAttribute("href").equals("https://www.tinkoff.ru/mobile-operator/tariffs/")) {
                    el.click();
                    return true;
                }
            }
            return false;
        });

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if(tabs.size() != 2) {
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
    public void test2() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        By confirmButtonLocator = By.xpath("//*[contains(@class,'MvnoRegionConfirmation__optionAgreement')]");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButtonLocator));
        driver.findElement(confirmButtonLocator).click();
        By selectedRegionLocator = By.xpath("//*[contains(@class, 'MvnoRegionConfirmation__title')]");

        driver.navigate().refresh();
        assertThat(driver.findElement(selectedRegionLocator).getText(), CoreMatchers.containsString("Москва"));

        By amountTextLocator = By.xpath("//*[contains(@class, '_amountTitle_') and @data-qa-file='UITitle']");
        String amountMoscow = driver.findElement(amountTextLocator).getText();

        driver.findElement(selectedRegionLocator).click();
        By cityListLocator =  By.xpath("//*[contains(@class, 'MobileOperatorRegionsPopup__region_')]");
        wait.until( d -> {
            List<WebElement> cityList = d.findElements(cityListLocator);
            for (WebElement el : cityList) {
                if (el.getText().equals("Краснодар")){
                    el.click();
                    return true;
                }
            }
            return false;
        });
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selectedRegionLocator, "Краснодар"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountTextLocator));
        String amountKrasnodar = driver.findElement(amountTextLocator).getAttribute("innerText");
        assertNotEquals(amountKrasnodar, amountMoscow);
        ElementCreator elementCreator = new ElementCreator(driver);

        Select internetSelect = elementCreator.create(EnumElements.SELECT,
                By.xpath("//*[@data-qa-file='UIDropdownSelect' and .//*[@name='internet']]"));
        Select callsSelect = elementCreator.create(EnumElements.SELECT,
                By.xpath("//*[@data-qa-file='UIDropdownSelect' and .//*[@name='calls']]"));

        internetSelect.selectValue("Безлимитный интернет");
        callsSelect.selectValue("Безлимитные минуты");

        System.out.println(driver.findElement(By.xpath("//*[@data-qa-file=\"UICheckbox\"]//input[@type='checkbox']")).isSelected());

       CheckBox checkBox =  elementCreator.create(EnumElements.CHECKBOX,
               By.xpath("//*[@data-qa-file='UICheckbox']//input[@type='checkbox']"));


        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }
}
