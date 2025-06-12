package com.booking.page;

import com.booking.base.TestBase;
import com.booking.base.TestContextBrowserPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReservePage extends TestBase {

    private  final Logger log = LogManager.getLogger(this.getClass());

    @FindBy(css = "tr.hprt-table-header")
    private WebElement reserveTable;
    @FindBy(xpath = "//strong[text()='Finish booking']")
    private WebElement finishBookingPage;

    public ReservePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void isAt() {
        wait.until(ExpectedConditions.visibilityOf(reserveTable));
    }

    public void selectNumberOfAppartment(int num, String appartmentType) throws InterruptedException {
        WebElement selectAnApartmentDropDown = driver.findElement(By.xpath("//span[@class = 'hprt-roomtype-icon-link ' and contains(text() ,'" + appartmentType + "')]//ancestor::tr//select"));
        selectByValue(num, selectAnApartmentDropDown);
        WebElement reserveButton = driver.findElement(By.cssSelector("div[data-testid = reservation-summary] button span:nth-child(2)"));
        reserveButton.submit();
        log.info("Selected number of appartment and submitted");
    }

    public void finishBookingPage() throws InterruptedException {
        waitForPageLoad();
        Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(finishBookingPage));
        if (finishBookingPage.isDisplayed()) {
            log.info("Landed on Finish Booking Page");
            driver.close();
            driver.switchTo().window(TestContextBrowserPage.getBrowser());
            log.info("Closed Window and navigated to parent window");
        }
    }


}
