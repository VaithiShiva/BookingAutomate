package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/features",
glue = {"utils","test"},monochrome = true,dryRun = false
)
public class InitRunner extends AbstractTestNGCucumberTests {


    @DataProvider
    public Object[][] scenarios()
    {

        return super.scenarios();
    }
}
