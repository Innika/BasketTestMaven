package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Pages.BasePage.takeScreenshotOnFail;

public class AllureListener implements ITestListener {

    public void onTestStart(ITestResult var1){}

    public void onTestSuccess(ITestResult var1){}

    public void onTestSkipped(ITestResult var1){}

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1){}

    public void onStart(ITestContext var1){}

    public void onFinish(ITestContext var1){}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        takeScreenshotOnFail();
    }
}
