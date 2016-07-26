package genericLibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class BaseTest {
	protected static ExtentReports extent;
	//protected String testName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
	static String currentTimestamp = new SimpleDateFormat("dd_MM_yy_HH_mm_ss").format(new Date());
	String path = "test-output/extent_report_" + currentTimestamp + "/Local_Extent_Report.html";
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		extent = new ExtentReports(path, false, DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
	}

	/*
	 * After suite will be responsible to close the report properly at the end You an have another afterSuite as well in the derived class and this one will be called in the end making it the last
	 * method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() throws IOException {
		extent.flush();
		File eReport = new File(path);
		File eReport1 = new File("test-output/jenkins-extent/Extent_Report.html");
		FileUtils.copyFile(eReport, eReport1);
	}
}
