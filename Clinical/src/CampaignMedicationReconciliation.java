import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import MedicationReconciliation.testcases.DischargeMedication_testcase;
import MedicationReconciliation.testcases.HomeMedicationReconciliation_testcase;
import MedicationReconciliation.testcases.InhouseMedication_testcase;
import order_entry.OrderEntryPlus_Medication;

@Listeners(common.Listener.class)
@Test(groups = "CampaignMedicationReconciliation")
public class CampaignMedicationReconciliation {

	@Test(priority = 83, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Medication() throws Exception {
		OrderEntryPlus_Medication obj = new OrderEntryPlus_Medication();
		obj.cleanUpOrders();
		obj = null;
	}

	@Test(priority = 84, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconAdd() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedAdd();
	}

	@Test(priority = 85, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconEdit() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedEdit();
	}

	@Test(priority = 86, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconVerify() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedVerify();
	}

	@Test(priority = 87, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconDelete() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedDelete();
	}

	@Test(priority = 88, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconFilter() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedFilter();
	}

	@Test(priority = 89, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconRefresh() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedRefresh();
	}

	@Test(priority = 90, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconPharmacy() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedpharmacy();
	}

	@Test(priority = 91, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconNoMedsAndAudit() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedAudit();
	}

	@Test(priority = 92, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconYesAdmission() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedYesAdmission();
	}

	@Test(priority = 93, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconNoAdmission() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedNoAdmission();
	}

	@Test(priority = 94, dependsOnGroups = { "CampaignLogin" })
	public static void homeMedReconPrint() throws Exception {
		HomeMedicationReconciliation_testcase obj = new HomeMedicationReconciliation_testcase();
		obj.homeMedPrint();
	}

	@Test(priority = 95, dependsOnGroups = { "CampaignLogin" })
	public static void inhouseMedReconAdd() throws Exception {
		InhouseMedication_testcase obj = new InhouseMedication_testcase();
		obj.inhouseAdd();
	}

	@Test(priority = 96, dependsOnGroups = { "CampaignLogin" })
	public static void inhouseMedReconDisplay() throws Exception {
		InhouseMedication_testcase obj = new InhouseMedication_testcase();
		obj.inhouseDisplay();
	}

	@Test(priority = 97, dependsOnGroups = { "CampaignLogin" })
	public static void inhouseMedReconOrderEntry() throws Exception {
		InhouseMedication_testcase obj = new InhouseMedication_testcase();
		obj.inhouseOrderEntry();
	}

	@Test(priority = 98, dependsOnGroups = { "CampaignLogin" })
	public static void inhouseMedReconPrint() throws Exception {
		InhouseMedication_testcase obj = new InhouseMedication_testcase();
		obj.inhousePrint();
	}

	@Test(priority = 99, dependsOnGroups = { "CampaignLogin" })
	public static void dischargeMedReconLOC() throws Exception {
		DischargeMedication_testcase obj = new DischargeMedication_testcase();
		obj.dischargeViewFromLOC();
	}

	@Test(priority = 100, dependsOnGroups = { "CampaignLogin" })
	public static void dischargeMedReconPrint() throws Exception {
		DischargeMedication_testcase obj = new DischargeMedication_testcase();
		obj.dischargePrint();
	}
}
