import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import findpatient.FindPatient;
import kardexplus.testcases.KardexPlusTestcase;

@Listeners(common.Listener.class)
@Test(groups = "CampaignKardexPlus")
public class CampaignKardexPlus {

	@Test(priority = 2, dependsOnGroups = { "CampaignLogin" })
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.newPatientSearch();
		objFind = null;
	}

	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationAdd() throws Exception {
		KardexPlusTestcase obj = new KardexPlusTestcase();
		obj.kardexPlus();
		obj = null;
	}
}
