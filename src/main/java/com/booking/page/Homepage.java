package com.booking.page;

import com.booking.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Homepage extends TestBase {

    Map<String, String> locations = new HashMap<String, String>();

    public Homepage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//nav[@data-testid = 'header-xpb']")
    public WebElement navigationBar;

    @FindBy(xpath = "//input[@name = 'ss']")
    public WebElement searchDestination;


    @FindBy(xpath = "//*[@id='autocomplete-results']")
    public WebElement locationSearchResult;

    @FindBy(xpath = "//*[@id='indexsearch']/div[2]/div/form/div/div[2]/div/button")
    public WebElement date;

    @FindBy(xpath = "//*[@id='calendar-searchboxdatepicker']/div/div[1]/div/div[2]/h3")
    public WebElement toDateMonth;

    @FindBy(xpath = "//*[@id='calendar-searchboxdatepicker']/div/div[1]/div/div[1]/h3")
    public WebElement fromDateMonth;

    @FindBy(xpath = "//*[@id='group-0-heading' and text()='Trending destinations']")
    public WebElement trendingDestinationText;

    @FindBy(css = "button[type='submit']")
    private  WebElement submit;

    @FindBy(xpath = "//button[@class = 'de576f5064']")
    private WebElement clearLocationButton;

    @FindBy(xpath = "//button[@data-testid = 'searchbox-dates-container']")
    private WebElement datePickerTabs;

    @FindBy(xpath = "//span[@data-testid = 'date-display-field-start']")
    private WebElement dateField;

    public List<WebElement> searchList;




    public Homepage selectSearchType(String menu) {
        navigationBar.findElement(By.xpath("//a[@id='" + menu + "']")).click();
        return this;
    }


    public String searchDestination(String location) throws InterruptedException {


        String[] locationWithState = location.split("\\|");
        waitForPageLoad();
        if(!Objects.requireNonNull(searchDestination.getDomAttribute("value")).isEmpty())
        {
            clearLocationButton.click();
        }
        searchDestination.sendKeys(locationWithState[0]);

        Thread.sleep(2000);


        searchList = driver.findElements(By.xpath("//ul[@role = 'group']/li"));
        boolean foundFlag = false;
        for (int i = 1; i <= searchList.size(); i++) {
            int j = 1;
            WebElement locationSearch = driver.findElement(By.xpath("//ul[@role = 'group']/li[" + i + "]//span//following-sibling::div/div[" + j + "]"));
            System.out.println(locationSearch.getText());
            if (locationSearch.getText().equalsIgnoreCase(locationWithState[0])) {
                j++;
                if (driver.findElement(By.xpath("//ul[@role = 'group']/li[" + i + "]//span//following-sibling::div/div[" + j + "]")).getText().contains(locationWithState[1])) {
                    locationSearch.click();
                    foundFlag = true;
                    break;
                }
            }
        }
        if (!foundFlag) {
            return "Location Not Found";
        }
        return "Found Location";
    }


    public void selectStartAndToDate(LocalDate from, LocalDate to) {


        Duration implicitWaitTimeOut = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        List<WebElement> elements = driver.findElements(By.xpath("//div[@data-testid = 'searchbox-datepicker']"));
        if (elements.isEmpty())
        {
            datePickerTabs.click();
        }
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeOut);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        YearMonth defaultTo = YearMonth.parse(toDateMonth.getText(), dateTimeFormatter);
        YearMonth defaultFrom = YearMonth.parse(fromDateMonth.getText(), dateTimeFormatter);

        System.out.println(defaultFrom.getYear());
        System.out.println(from.getYear());
        if (defaultFrom.getYear() == from.getYear()) {
            if ( from.isAfter(LocalDate.now()) && to.isAfter(from)) {
                ////*[@id="calendar-searchboxdatepicker"]//h3[contains(text(),'May')]//following-sibling::table//child::span[text() = '7']

                String startMonth = (defaultFrom.getMonth().name().substring(0, 1).toUpperCase()) + defaultFrom.getMonth().name().substring(1).toLowerCase();
                String toMonth = (defaultTo.getMonth().name().substring(0, 1).toUpperCase()) + defaultTo.getMonth().name().substring(1).toLowerCase();
                System.out.println(startMonth);
                System.out.println(toMonth);
                WebElement startDate = driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']//h3[contains(text(),'" + startMonth + "')]//following-sibling::table//child::span[text() = '" + from.getDayOfMonth() + "']/.."));
                startDate.click();
                WebElement endDate = driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']//h3[contains(text(),'" + toMonth + "')]//following-sibling::table//child::span[text() = '" + to.getDayOfMonth() + "']/.."));
                endDate.click();
            }
        }
    }

    public SearchResultsPage searchSubmit()
    {
        submit.click();
        return new SearchResultsPage(driver);
    }




}


