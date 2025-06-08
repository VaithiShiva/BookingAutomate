package utils;

import com.booking.page.Homepage;
import driver.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class TestBeforeNAfterHooks  implements ApplicationConstants  {


    private Container container;
    public WebDriver driver;


    public TestBeforeNAfterHooks(Container container) {
        this.container = container;
        container.extentUtilsThreadLocalContext = new ThreadLocalContext<ExtentUtils>();
       container.extentUtilsThreadLocalContext.setThreadSafe(new ExtentUtils(ApplicationConstants.reportPath));
    }


    @BeforeAll()
    public static void initBeforeHooks() throws Exception {
        PropertyConfigurator.configure(".//src//test//resources//log4j.properties");

    }

    @AfterAll
    public static void tearDownHooks() {
        DriverProvider.getInstance().quitDriver();
    }

    @Before
    public void testInit() throws Exception {
        String valueOfBrowser = TestContextBrowser.getBrowser();
        System.out.println(valueOfBrowser);
        driver = DriverProvider.getInstance().getDriver(valueOfBrowser);
        container.extentUtilsThreadLocalContext.getThreadSafe().get().createTestWithName("Booking Automation");
        container.extentUtilsThreadLocalContext.getThreadSafe().get().successLog("Created " +valueOfBrowser+ " instance");
        driver.get(homePage);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20000));
        container.homepage = new Homepage(driver,valueOfBrowser);
    }

    @After
    public void tearDown() {

    }



}
