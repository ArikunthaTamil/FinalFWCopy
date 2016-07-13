package genericLibrary;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class BaseTest {
	protected static ExtentReports extent;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		extent = new ExtentReports("target/surefire-reports/Regression.html", false, DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
	}

	/*
	 * After suite will be responsible to close the report properly at the end You an have another afterSuite as well in the derived class and this one will be called in the end making it the last
	 * method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

}
