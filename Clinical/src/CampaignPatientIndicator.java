import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import patient_indicators.testcases.PatientIndicatorTestCases;

@Listeners(common.Listener.class)
@Test(groups = "CampaignPatientIndicator")
public class CampaignPatientIndicator {

	@Test(priority = 300, dependsOnGroups = { "CampaignLogin" })
	public static void verifyPatientIndicator() throws InterruptedException {
		PatientIndicatorTestCases piobj = new PatientIndicatorTestCases();
		piobj.verifyPatientIndicatorFunctionalities();
	}

	// @Test(priority = 301, dependsOnGroups = { "CampaignLogin" })
	// public static void verifyPtChartTempLocation() throws
	// InterruptedException, IOException {
	// PatientIndicatorTestCases piobj = new PatientIndicatorTestCases();
	// piobj.verifyPtChartTempLocation();
	// }
	//
	// @Test(priority = 302, dependsOnGroups = { "CampaignLogin" })
	// public static void verifyPatientClinicalSummary() {
	// PatientIndicatorTestCases piobj = new PatientIndicatorTestCases();
	// piobj.verifyPatientClinicalSummary();
	// }

}
