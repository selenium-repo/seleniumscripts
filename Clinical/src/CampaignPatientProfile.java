import org.testng.annotations.Test;

import findpatient.FindPatient;
import pharmacy.testcases.patientProfileTestcase;

public class CampaignPatientProfile {
	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.RxPatientSearch();
		objFind = null;
	}

	@Test(priority = 4, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public static void confirmHoldReleaseOrder() throws Exception {
		patientProfileTestcase pf = new patientProfileTestcase();
		pf.confirmOrder();
		pf.holdOrder();
		pf.releaseOrder();
	}

	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public static void discontinueOrder() throws Exception {
		patientProfileTestcase pf = new patientProfileTestcase();
		pf.discontinueOrder();
	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void interventionOrder() throws Exception {
		patientProfileTestcase pf = new patientProfileTestcase();
		pf.interventionOrder();
	}
}
