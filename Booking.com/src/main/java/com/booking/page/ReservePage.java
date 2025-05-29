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

    @FindBy(xpath = "//*[@id='hprt-form']/div[8]/div[2]/div[4]/div[5]/button")
    private WebElement reserveButton;


    public void isAt()
    {
        wait.until(ExpectedConditions.visibilityOf(reserveTable));

    }

    public void selectNumberOfAppartment(int num, String appartmentType)
    {
        WebElement selectAnApartmentDropDown =  driver.findElement(By.xpath("//span[@class = 'hprt-roomtype-icon-link ' and contains(text() ,'"+appartmentType+"')]//ancestor::tr//select"));
        selectByValue(1,selectAnApartmentDropDown);


            reserveButton.click();

    }

    public boolean finishBookingPage()
    {

        wait.until(ExpectedConditions.visibilityOf(finishBookingPage));
        if(finishBookingPage.getText().equals("Finish booking"))
        {
            return true;
        }
        return false;
    }





}
