
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import findpatient.FindPatient;
import findpatient.PendingOrders;
import findpatient.PhysicianCensus;
import findpatient.Physiciangroup;
import findpatient.StationCensus;;

@Listeners(common.Listener.class)
@Test(groups = "CampaignPatientSearch")
public class CampaignPatientSearch {

	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void physicianGrp() throws InterruptedException {
		Physiciangroup objFind = new Physiciangroup();
		objFind.physician_Group();
		objFind = null;

	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void pendingord() throws InterruptedException {
		PendingOrders objFind = new PendingOrders();
		objFind.pending_orders();
		objFind = null;
	}

	@Test(priority = 7, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public static void patsearch() {
		FindPatient objFind = new FindPatient();
		objFind.PatientSearch();
		objFind = null;
	}

	@Test(priority = 8, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void physicianCens() throws InterruptedException {
		PhysicianCensus objFind = new PhysicianCensus();
		objFind.Physician_Census();
		objFind = null;
	}

	@Test(priority = 9, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void stationcen() throws InterruptedException {
		StationCensus objFind = new StationCensus();
		objFind.Station_Census();
		objFind = null;
	}

}
