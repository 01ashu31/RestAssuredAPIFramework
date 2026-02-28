package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 1;
    private int currentRetryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (currentRetryCount < MAX_RETRY_COUNT) {
            currentRetryCount++;
            return true;
        }
        return false;
    }
}
