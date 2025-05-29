package com.booking.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public abstract class TestBase {

    public static WebDriver driver;
    protected WebDriverWait wait;
    public static final String searchHotel = "Misty Meridian Serviced Apartments";
    Actions action;
    Select select;

    public TestBase(WebDriver driver) {
        TestBase.driver = driver;
        wait = new WebDriverWait(TestBase.driver, Duration.ofSeconds(20));
        action = new Actions(driver);
    }


    public void findObjectWithElementToBeClickable(WebElement element, String subElement) {
        wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.xpath("//*[text()='" + subElement + "']/parent::div")))).click();
    }

    public WebElement findElementInPageUsingValue(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected void scrollIntoViewExecutor(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElement(WebElement element) {
        action.scrollToElement(element);
        action.build().perform();
    }

    protected Boolean switchToWindowByTitle(String title) throws InterruptedException {
        Set<String> openedWindows = driver.getWindowHandles();
        Optional<String> parentWindowID = openedWindows.stream().findFirst();
        Iterator<String> itr = openedWindows.iterator();
        for (String id : openedWindows)
        {
            if (!id.equals(parentWindowID.get()))
            {
                driver.switchTo().window(id);
                return true;
            }

        }
        return false;
    }

    protected void selectByValue(int value, WebElement options)
    {
        select = new Select(options);

        select.selectByValue(String.valueOf(value));
    }


}
