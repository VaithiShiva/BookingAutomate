package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProvider {

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
    public static DriverProvider driverProvider;
    private WebDriver driver;

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


    public WebDriver getDriver(String browserName) {
        if (threadLocal.get() == null) {
            threadLocal.set(setDriver(browserName));
        }
        return threadLocal.get();
    }

    public WebDriver setDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver();
            return driver;
        }
        return null;
    }

    public void quitDriver() {
        if (threadLocal.get() != null) {
            threadLocal.get().quit();
        }
    }

}
