package utils;

import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import driver.DriverProvider;
import com.booking.page.Homepage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import java.time.Duration;

public class TestBeforeNAfterHooks implements ApplicationConstants {

    public static WebDriver driver;
    public static ExtentReporter extentReporter;
    private final Container container;

    public TestBeforeNAfterHooks(Container container) {
        this.container = container;
    }


    @BeforeAll()
    public static void initBeforeHooks() {
        PropertyConfigurator.configure(".//src//test//resources//log4j.properties");
        driver = DriverProvider.getInstance().getDriver("Chrome");
        extentReporter = new ExtentSparkReporter(reportPath);
    }

    @Before
    public void testInit() {
        driver.get(homePage);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20000));
        container.homepage = new Homepage(driver);
    }


    @AfterAll
    public static void tearDownHooks() {

        DriverProvider.getInstance().quitDriver();
    }


}
