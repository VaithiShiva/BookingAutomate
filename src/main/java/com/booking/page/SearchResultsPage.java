package com.booking.page;

import com.booking.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SearchResultsPage extends TestBase {

    ReservePage rp;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ReservePage searchForTheHotelAndSeeAvailability(String hotelName) throws InterruptedException {

        WebElement hotel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3//*[contains(text(),'" + hotelName + "')]")));
        scrollToElement(hotel);
        WebElement seeAvailabilityButton = driver.findElement(By.xpath("//div[@data-testid = 'title' and text()='" + hotelName + "']//ancestor::div[@class='ad8fb705c9']//span[text()='See availability']"));
        seeAvailabilityButton.click();
        switchToWindowByTitle(hotelName);
        rp = new ReservePage(driver);
        return rp;
    }

}
