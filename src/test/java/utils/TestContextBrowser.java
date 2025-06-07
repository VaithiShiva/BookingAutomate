package utils;

public class TestContextBrowser{

    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static ThreadLocal<ExtentUtils> extentReport = new ThreadLocal<>();

    public static void setBrowser(String value) {
        browser.set(value);
    }

    public static String getBrowser() {
        return browser.get();
    }

    public static ThreadLocal<ExtentUtils> getExtentReport() {
        return extentReport;
    }

    public static void setExtentReport(ThreadLocal<ExtentUtils> extentReport) {
        TestContextBrowser.extentReport = extentReport;
    }
}


