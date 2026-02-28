package com.automation.listeners;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String message = "Test failed: " + result.getName() + " | " + result.getThrowable();
        Allure.addAttachment("Failure details", message);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.addAttachment("Skipped details", "Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        Allure.label("suite", context.getSuite().getName());
    }
}
