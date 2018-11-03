package fintechqa.web1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.Assert.assertEquals;

public class FirstTest extends BaseRunner {

    @Test
    public void test1() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//input[@name='fio']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='phone']")).click();
        driver.findElement(By.xpath("//input[@name='city']")).click();
        driver.findElement(By.xpath("//*[@role='listbox' and .//text()='Выберите вакансию']")).click();
        driver.findElement(By.xpath("//input[@name='fio']")).click();
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("//*[contains(@class, 'errorMessage') and ./ancestor::*[contains(@class, 'FormField')]//*[@data-qa='fio']]")).getText()
        );
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("//*[contains(@class, 'errorMessage') and ./ancestor::*[contains(@class, 'FormField')]//*[@data-qa='email']]")).getText()
        );
        assertEquals("Необходимо указать номер телефона",
                driver.findElement(By.xpath("//*[contains(@class, 'errorMessage') and ./ancestor::*[contains(@class, 'FormField')]//input[@name='phone']]")).getText()
        );
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("//*[contains(@class, 'errorMessage') and ./ancestor::*[contains(@class, 'FormField')]//input[@name='city']]")).getText()
        );
        assertEquals("Поле обязательное",
                driver.findElement(By.xpath("//*[@role='listbox' and .//text()='Выберите вакансию']/ancestor::*[contains(@class, 'FormField')]//*[contains(@class, 'errorMessage')]")).getText()
        );
    }

    @Test
    public void test2() {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("input[name='fio']")).click();
        driver.findElement(By.cssSelector("input[name='fio']")).clear();
        driver.findElement(By.cssSelector("input[name='fio']")).sendKeys("A");
        driver.findElement(By.cssSelector("input[name='email']")).clear();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("1");
        driver.findElement(By.cssSelector("input[name='phone']")).click();
        driver.findElement(By.cssSelector("input[name='phone']")).clear();
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("+7 (2");
        driver.findElement(By.cssSelector("input[name='city']")).click();
        driver.findElement(By.cssSelector("input[name='city']")).clear();
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("3");
        driver.findElement(By.cssSelector("input[name='fio']")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                driver.findElement(By.cssSelector("[data-qa-file='UIProductBlockForm'] [class*='Row__row']:nth-child(1) [class*='errorMessage']")).getText()
        );
        assertEquals("Введите корректный адрес эл. почты",
                driver.findElement(By.cssSelector("[data-qa-file='UIProductBlockForm'] [class*='Row__row']:nth-child(2) [class*='FormField']:nth-child(1) [class*='errorMessage']")).getText()
        );
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9",
                driver.findElement(By.cssSelector("[data-qa-file='UIProductBlockForm'] [class*='Row__row']:nth-child(2) [class*='FormField']:nth-child(2) [class*='errorMessage']")).getText()
        );
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис",
                driver.findElement(By.cssSelector("[data-qa-file='UIProductBlockForm'] [class*='Row__row']:nth-child(3) [class*='errorMessage']")).getText()
        );
        driver.findElement(By.cssSelector("input[name='phone']")).click();
        driver.findElement(By.cssSelector("input[name='phone']")).clear();
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("+7 (989) ");
        driver.findElement(By.cssSelector("input[name='fio']")).click();
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора",
                driver.findElement(By.cssSelector("[data-qa-file='UIProductBlockForm'] [class*='Row__row']:nth-child(2) [class*='FormField']:nth-child(2) [class*='errorMessage']")).getText()
        );
    }

}
