package base;

import org.testng.annotations.BeforeClass;

import config.ConfigManager;
import io.restassured.RestAssured;

public class BaseTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI=ConfigManager.get("base.url");
	}

}
