import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CarePlanPlus.testcases.CarePlanPlusTestcase;

@Listeners(common.Listener.class)

@Test(groups = "CampaignCarePlanPlus")
public class CampaignCarePlanPlus {

	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAdd() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addCarePlan();
	}

	@Test(priority = 4, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAddProblem() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addProblem();
	}

	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAddOutcome() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addOutcome();
	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAddIntervention() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addIntervention();
	}

	@Test(priority = 7, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAddOrder() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addOrder();
	}

	@Test(priority = 8, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusEditIntervention() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.editIntervention();
	}

	@Test(priority = 9, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusEditOutcome() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.editOutcome();
	}

	@Test(priority = 10, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusEditProblem() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.editProblem();
	}

	@Test(priority = 11, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusEdit() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.editCarePlan();
	}

	@Test(priority = 12, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusDeleteIntervention() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.deleteIntervention();
	}

	@Test(priority = 13, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusDeleteOutcome() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.deleteOutcome();
	}

	@Test(priority = 14, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusDeleteProblem() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.deleteProblem();
	}

	@Test(priority = 15, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusDelete() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.deleteCarePlan();
	}

	@Test(priority = 16, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusPrint() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.print();
	}

	@Test(priority = 17, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusActiveOnly() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.activeOnly();
	}

	@Test(priority = 18, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusEntirePlan() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.entirePlan();
	}

	@Test(priority = 19, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusProperties() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.properties();
	}

	@Test(priority = 20, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusControl() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.control();
	}

	@Test(priority = 21, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAddViewSuggested() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.addViewSuggested();
	}

	@Test(priority = 22, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusAudit() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.audit();
	}

	@Test(priority = 23, dependsOnGroups = { "CampaignLogin" })
	public static void CarePlanPlusPhysicianReview() throws Exception {
		CarePlanPlusTestcase obj = new CarePlanPlusTestcase();
		obj.physicianReview();
	}

}
