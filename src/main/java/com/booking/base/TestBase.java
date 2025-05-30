package com.booking.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public abstract class TestBase {

    public static final String searchHotel = "Misty Meridian Serviced Apartments";
    public static WebDriver driver;
    protected static Optional<String> parentWindowID;
    protected WebDriverWait wait;
    Actions action;
    Select select;
    JavascriptExecutor js;
    FluentWait<WebDriver> fluentWait;

    public TestBase(WebDriver driver) {
        TestBase.driver = driver;
        wait = new WebDriverWait(TestBase.driver, Duration.ofSeconds(20));
        js = ((JavascriptExecutor) driver);
        action = new Actions(driver);

    }


    public void findObjectWithElementToBeClickable(WebElement element, String subElement) {
        wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.xpath("//*[text()='" + subElement + "']/parent::div")))).click();
    }

    public WebElement findElementInPageUsingValue(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected void scrollIntoViewExecutor(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElement(WebElement element) {
        action.scrollToElement(element);
        action.build().perform();
    }

    protected Boolean switchToWindowByTitle(String title) throws InterruptedException {
        Set<String> openedWindows = driver.getWindowHandles();
        parentWindowID = openedWindows.stream().findFirst();


        Iterator<String> itr = openedWindows.iterator();
        for (String id : openedWindows) {
            if (!id.equals(parentWindowID.get())) {
                driver.switchTo().window(id);
                return true;
            }

        }
        return false;
    }

    protected void selectByValue(int value, WebElement options) {
        select = new Select(options);

        select.selectByValue(String.valueOf(value));
    }

    protected void waitForPageLoad() {
        js.executeScript("return document.readyState").equals("complete");
    }

    protected boolean fluentWait(long waitTime, List<WebElement> ele) {
        Duration implicitWaitTimeOut = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(waitTime)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        if (fluentWait.until(ExpectedConditions.visibilityOfAllElements(ele)).size() > 0) {
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeOut);
            return true;
        }
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeOut);
        return false;
    }


}
