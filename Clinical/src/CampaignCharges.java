import org.testng.annotations.Test;

import findpatient.FindPatient;
import pharmacy.testcases.chargeTestCases;

public class CampaignCharges {
	@Test(priority = 1, dependsOnGroups = { "CampaignLogin" })
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.RxPatientSearch();
		objFind = null;
	}

	@Test(priority = 2, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public static void createChargesSinglePatient() throws Exception {
		chargeTestCases obj = new chargeTestCases();
		obj.addChargeSinglePatient();
	}

	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void createChargesMultiPatient() throws Exception {
		chargeTestCases obj = new chargeTestCases();
		obj.addChargesMultiPatient();
	}
}
