package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	// Private constructor prevents instantiation from outside
	//Make the constructor of the class private, so that no other instances can be created.
	private ExtentManager() {
		//prevent reflection attack
		if (extent != null) {
			throw new RuntimeException("Use getReport() method to create instance");
		}
	}
	
	public static synchronized ExtentReports getReport() {
		if(extent == null) {
			ExtentSparkReporter spark= new ExtentSparkReporter("reports/ExtentReport.html");
			spark.config().setDocumentTitle("Test Execution report");
			spark.config().setReportName("Extent Report");
			
			extent= new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
		
	}
	
	
	

}
