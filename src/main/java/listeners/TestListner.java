package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utility.ExtentManager;

public class TestListner implements ITestListener{
	
	private static ExtentReports extent= ExtentManager.getReport();
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
	}
	
	public void OnFinish(ITestResult result) {
		extent.flush();
	}

}
