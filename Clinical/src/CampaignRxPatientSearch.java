import org.testng.annotations.Test;

import pharmacy.patientsearch.testcases.PatientSearchTestCases;

public class CampaignRxPatientSearch {

	@Test(priority = 3, dependsOnGroups = "CampaignLogin")
	public static void RxPatientSearch() {
		PatientSearchTestCases pat = new PatientSearchTestCases();
		pat.verifyPatientSearchOptions();
	}

}
