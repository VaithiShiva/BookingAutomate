package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentUtils {


    private ExtentSparkReporter extentSparkReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    public ExtentUtils(String reportPath) {
        reportPath = reportPath + Math.random() + ".html";
        extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public ExtentTest getTestReportObject() {
        return extentTest;
    }


}
