package utils;

import com.booking.page.Homepage;
import driver.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class TestBeforeNAfterHooks  implements ApplicationConstants  {

    private  final Logger log = LogManager.getLogger(this.getClass());


    private Container container;
    public WebDriver driver;


    public TestBeforeNAfterHooks(Container container) {
        this.container = container;
        container.extentUtilsThreadLocalContext = new ThreadLocalContext<ExtentUtils>();
       container.extentUtilsThreadLocalContext.setThreadSafe(new ExtentUtils(ApplicationConstants.reportPath));
    }


    @BeforeAll()
    public static void initBeforeHooks() throws Exception {

       /*InputStream ip = ResourceLoader.class.getClassLoader().getResourceAsStream(".//target//docker-resources//log4j.properties");
       if (!Objects.isNull(ip))
       {
           PropertyConfigurator.configure(ip);
       }
       else {
           PropertyConfigurator.configure(FileUtils.getFile("./src/test/resources/log4j.properties").getPath());
       }*/
    }

    @AfterAll
    public static void tearDownHooks() {
        DriverProvider.getInstance().quitDriver();
    }

    @Before
    public void testInit() throws Exception {
        String valueOfBrowser = TestContextBrowser.getBrowser();
        driver = DriverProvider.getInstance().getDriver(valueOfBrowser);
        log.info("Created driver instance of {}", valueOfBrowser);
        log.info("In Thread >>> {}", Thread.currentThread());
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
