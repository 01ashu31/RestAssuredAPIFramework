package utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshoyUtility {
	public static String captureBase64(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public static String captureAndSave(WebDriver driver, String testName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String path = "reports/screenshots/" + testName + ".png";
			Files.createDirectories(Paths.get("reports/screenshots"));
			Files.copy(src.toPath(), Paths.get(path));
			return path;

		} catch (Exception e) {
			throw new RuntimeException("Failed to capture screenshot");
		}

	}

}
