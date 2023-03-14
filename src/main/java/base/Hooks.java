package base;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class Hooks extends BasePage {

	public Hooks() throws IOException {
		super();
	}
	
	@BeforeSuite
	public void setup() throws InterruptedException, IOException {
		getDriver().get(getUrl());
	}
	
	@AfterTest
	public void end() {
		WebDriverInstance.cleanupDriver();
	}

}
