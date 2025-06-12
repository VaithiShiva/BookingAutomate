package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import utils.TestContextBrowser;


@CucumberOptions(features = "classpath:features",
        glue = {"utils", "test"}, monochrome = true, dryRun = false
)
public class InitRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }


    @BeforeClass
    @Parameters("browser")
    public void setBrowser(String browser) {
        TestContextBrowser.setBrowser(browser); // Store in ThreadLocal
    }



}
