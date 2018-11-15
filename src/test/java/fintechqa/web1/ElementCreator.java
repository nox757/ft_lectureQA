package fintechqa.web1;

import fintechqa.web1.elements.BaseElement;
import fintechqa.web1.elements.EnumElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ElementCreator {

    private WebDriver driver;

    public ElementCreator(WebDriver driver) {
        this.driver = driver;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseElement> T create(EnumElements typeElement, By xpath) {
        WebElement element = getElement(xpath);
        return initElement(element, typeElement);
    }

    private WebElement getElement(By xpath) {
        return driver.findElement(xpath);
    }

    public <T extends BaseElement> List<T> createList(EnumElements typeElement, By xpath) {
        List<WebElement> elements = getElements(xpath);
        List<T> result = new ArrayList<>();
        for (WebElement element : elements) {
            T obj = initElement(element, typeElement);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }

    private List<WebElement> getElements(By xpath) {
        return driver.findElements(xpath);
    }

    @SuppressWarnings("unchecked")
    private <T extends BaseElement> T initElement(WebElement element, EnumElements typeElement) {
        try {
            Class<?> clazz = Class.forName(typeElement.getClassName());
            Constructor<?> constructor = clazz.getConstructor(WebElement.class);
            return (T) constructor.newInstance(element);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


}
