package com.booking.page;

import com.booking.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReservePage extends TestBase {



    public ReservePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr.hprt-table-header")
    private WebElement reserveTable;

    @FindBy(xpath = "//strong[text()='Finish booking']")
    private WebElement finishBookingPage;




    public void isAt()
    {
        wait.until(ExpectedConditions.visibilityOf(reserveTable));
    }

    public void selectNumberOfAppartment(int num, String appartmentType) throws InterruptedException {
        WebElement selectAnApartmentDropDown =  driver.findElement(By.xpath("//span[@class = 'hprt-roomtype-icon-link ' and contains(text() ,'"+appartmentType+"')]//ancestor::tr//select"));
        selectByValue(num,selectAnApartmentDropDown);
            //Thread.sleep(10000);

       WebElement reserveButton = driver.findElement(By.cssSelector("div[data-testid = reservation-summary] button span:nth-child(2)"));
       reserveButton.submit();
    }

    public boolean finishBookingPage()
    {

        wait.until(ExpectedConditions.visibilityOf(finishBookingPage));
        if(finishBookingPage.getText().contains("Finish booking"))
        {
           driver.close();
           driver.switchTo().window(parentWindowID.get());
        }
        return false;
    }





}
