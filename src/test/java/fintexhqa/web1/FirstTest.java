package fintexhqa.web1;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.Assert.assertEquals;

public class FirstTest extends BaseRunner {

    @Test
    public void test1() {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.xpath("//*[@role='listbox' and .//text()='Выберите вакансию']")).click();
        driver.findElement(By.name("fio")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@role='listbox' and .//text()='Выберите вакансию']/../following-sibling::*[1]")).getText());
    }

    @Test
    public void test2()  {
        driver.get(baseUrl);
        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("A");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("1");
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("+7 (2");
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("3");
        driver.findElement(By.name("fio")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("phone")).sendKeys("+7 (989) ");
        driver.findElement(By.name("fio")).click();
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
    }

}
