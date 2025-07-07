package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;


public class DriverProvider {

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
    public static DriverProvider driverProvider;
    private  WebDriver driver;

    private DriverProvider() {
    }

    public static DriverProvider getInstance() {
        if (driverProvider == null) {
            synchronized (DriverProvider.class) {
                if (driverProvider == null) {
                    driverProvider = new DriverProvider();
                }
            }
        }
        return driverProvider;
    }


    public WebDriver getDriver(String browser) throws Exception {
        if (threadLocal.get() == null) {
            threadLocal.set(setDriver(browser,Boolean.getBoolean("selenium.grid.enabled")));
        }
        return threadLocal.get();
    }

    public WebDriver setDriver(String browserName, Boolean flag) throws Exception{

        URI url = new URI("http://192.168.1.21:4444/wd/hub");
        switch (browserName) {
            case "chrome":{
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            if (!flag) {
                driver = new ChromeDriver();
            }
            else{
                driver = new RemoteWebDriver(url.toURL(), options);
            }
            
            break;      
            }   
            case "firefox":{

                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            if (!flag) {
                driver = new FirefoxDriver();
            }
            else{
                driver = new RemoteWebDriver(url.toURL(), options);
            }                break;
            }
            case "safari":
            SafariOptions options = new SafariOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            if (!flag) {
                driver = new SafariDriver();
            }
            else{
                driver = new RemoteWebDriver(url.toURL(), options);
            }                break;
        
            default:
                throw new IllegalArgumentException("Unsupported browser version passed");
        }
        return driver;
    }

   

    public void quitDriver() {
        if (threadLocal.get() != null) {
            threadLocal.get().quit();
        }
    }

}
