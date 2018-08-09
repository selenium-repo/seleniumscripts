import org.testng.annotations.Test;

import mpihistorynotesmaintenance.testcases.MPIHistoryNotesMaintenanceTestCases;

public class CampaignMPIHistoryNotesMaintenace {

	@Test(priority = 2, dependsOnGroups = { "CampaignLogin" })
	public static void allergies() throws Exception {

		MPIHistoryNotesMaintenanceTestCases mpiObj = new MPIHistoryNotesMaintenanceTestCases();
		mpiObj.verifyMPIHistoryNotesMaintenanceFunctionalities();

	}

}
