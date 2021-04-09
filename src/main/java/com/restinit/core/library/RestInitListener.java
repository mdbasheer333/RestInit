package com.restinit.core.library;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.restinit.core.reporting.ReportingService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RestInitListener implements ITestListener {

    private static ExtentReports extent = ReportingService.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ThreadLocal<ExtentTest> getLocalThreadExtentTest() {
        return test;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable());
        result.getTestContext().setAttribute("extentObject",test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

}
