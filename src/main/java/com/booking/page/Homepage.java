package com.booking.page;

import com.booking.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Homepage extends TestBase {

    private  final Logger log = LogManager.getLogger(this.getClass());

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
    public List<WebElement> searchList;
    Map<String, String> locations = new HashMap<String, String>();
    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//button[@class = 'de576f5064']")
    private WebElement clearLocationButton;

    @FindBy(xpath = "//button[@data-testid = 'searchbox-dates-container']")
    private WebElement datePickerTabs;

    @FindBy(xpath = "//span[@data-testid = 'date-display-field-start']")
    private WebElement dateField;

    @FindBy(css = "div#close svg")
    private WebElement closeSignInFrame;

    @FindBy(xpath = "//div[@id = 'credential_picker_container']/iframe")
    private WebElement signInPopUpFrame;


    private String browserName;



    public Homepage(WebDriver driver, String browser) {
        super(driver);
        this.browserName = browser;
        PageFactory.initElements(driver, this);
    }

    public Homepage selectSearchType(String menu) {
        closeSignInPopUp();
        navigationBar.findElement(By.xpath("//a[@id='" + menu + "']")).click();
        return this;
    }

    public void closeSignInPopUp() {
        if (browserName.equals("firefox")) {
           try {
               log.info("Firefox Driver - Inside close sign in pop up");
               driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
               WebElement ele = wait.until(ExpectedConditions.visibilityOf(signInPopUpFrame));
               if (ele != null) {
                   driver.switchTo().frame(ele);
                   closeSignInFrame.click();
                   driver.switchTo().defaultContent();
                   log.info("Closed pop up and switched to default content");
               }
               driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
           }catch (Exception e)
           {
               e.printStackTrace();
           }
        }
    }

    public String searchDestination(String location) throws InterruptedException {
        String[] locationWithState = location.split("\\|");
        waitForPageLoad();
        if (!Objects.requireNonNull(searchDestination.getDomAttribute("value")).isEmpty()) {
            clearLocationButton.click();
        }
        searchDestination.sendKeys(locationWithState[0]);
        Thread.sleep(2000);
        searchList = driver.findElements(By.xpath("//ul[@role = 'group']/li"));
        boolean foundFlag = false;
        for (int i = 1; i <= searchList.size(); i++) {
            int j = 1;
            WebElement locationSearch = driver.findElement(By.xpath("//ul[@role = 'group']/li[" + i + "]//span//following-sibling::div/div[" + j + "]"));

            if (locationSearch.getText().equalsIgnoreCase(locationWithState[0])) {
                j++;
                if (driver.findElement(By.xpath("//ul[@role = 'group']/li[" + i + "]//span//following-sibling::div/div[" + j + "]")).getText().contains(locationWithState[1])) {
                    locationSearch.click();
                    log.info("Selected from location >> "+ locationSearch.getText());
                    foundFlag = true;
                    break;
                }
            }
        }
        if (!foundFlag) {
            log.info("Location not found");
            return "Location Not Found";
        }
        return "Found Location";
    }


    public void selectStartAndToDate(LocalDate from, LocalDate to) {
        Duration implicitWaitTimeOut = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        List<WebElement> elements = driver.findElements(By.xpath("//div[@data-testid = 'searchbox-datepicker']"));
        if (elements.isEmpty()) {
            datePickerTabs.click();
        }
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeOut);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        YearMonth defaultTo = YearMonth.parse(toDateMonth.getText(), dateTimeFormatter);
        YearMonth defaultFrom = YearMonth.parse(fromDateMonth.getText(), dateTimeFormatter);

        System.out.println(defaultFrom.getMonth());
        System.out.println(defaultTo.getMonth());
        if (defaultFrom.getYear() == from.getYear()) {
            if (from.isAfter(LocalDate.now()) && to.isAfter(from)) {
                String startMonth = (from.getMonth().name().substring(0, 1).toUpperCase()) + from.getMonth().name().substring(1).toLowerCase();
                String toMonth = (to.getMonth().name().substring(0, 1).toUpperCase()) + to.getMonth().name().substring(1).toLowerCase();
                System.out.println(startMonth);
                System.out.println(toMonth);
                WebElement startDate = driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']//h3[contains(text(),'" + startMonth + "')]//following-sibling::table//child::span[text() = '" + from.getDayOfMonth() + "']/.."));
                startDate.click();
                log.info("Selected Start Date");
                System.out.println("//*[@id='calendar-searchboxdatepicker']//h3[contains(text(),'" + toMonth + "')]//following-sibling::table//child::span[text() = '" + to.getDayOfMonth() + "']/..");
                WebElement endDate = driver.findElement(By.xpath("//*[@id='calendar-searchboxdatepicker']//h3[contains(text(),'" + toMonth + "')]//following-sibling::table//child::span[text() = '" + to.getDayOfMonth() + "']/.."));
                endDate.click();
                log.info("Selected end Date");
            }
        }
    }

    public SearchResultsPage searchSubmit() {
        submit.click();
        log.info("Submitted search");
        return new SearchResultsPage(driver);




    }


}


