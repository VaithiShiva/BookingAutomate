package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentUtils {


    private  ExtentReports extentReports;
    private ExtentTest extentTest;

    public ExtentUtils(String reportPath) {
        reportPath = reportPath + Math.random() + ".html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public ExtentTest createTestWithName(String name) {
        extentTest =  extentReports.createTest(name);
        return extentTest;
    }

    public void successLog(String message)
    {
        extentTest.pass(message);
    }

    public void successLog(String message, Media media){
        extentTest.pass(message,media);
    }

    public void endTest()
    {
        extentReports.flush();
    }



}
