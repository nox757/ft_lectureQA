package fintechqa.web1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public boolean isTitleContainsText(String text) {
        return wait.until(ExpectedConditions.titleContains(text));
    }

    public boolean isUrl(String url){
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    public void switchToTab(String tabName) {
        wait.until(d -> {
            for (String tab : d.getWindowHandles()) {
                d.switchTo().window(tab);
                if(d.getTitle().equals(tabName))
                    return true;
            }
           return false;
        });
    }

    public void getPage(String url) {
        driver.navigate().to(url);
    }

    public void reloadPage(){
        driver.navigate().refresh();
    }

    public void closeTab() {
        driver.close();
    }

    public void switchToMainTab(){
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}
