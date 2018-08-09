import org.testng.annotations.Test;

import progressNotes.testcases.ProgressNotesTestCases;

public class CampaignProgressNotes {

	ProgressNotesTestCases obj;

	@Test(priority = 33, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void pgAssesments() {
		obj = new ProgressNotesTestCases();
		obj.assesmentTestCases();
	}

	@Test(priority = 34, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void pgPlan() {
		obj = new ProgressNotesTestCases();
		obj.planTestCases();

		// obj.validateSubmit();
	}

	@Test(priority = 35, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void subjectiveTestCases() {
		obj = new ProgressNotesTestCases();
		obj.subJectiveTestCases();

	}

	@Test(priority = 36, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void reviewSystemTestCases() {
		obj = new ProgressNotesTestCases();
		obj.reviewOfSystems();
	}

	@Test(priority = 41, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void ccTestCases() {
		obj = new ProgressNotesTestCases();
		// obj.objectiveTestCases();
		obj.ccTestCases();

	}

	@Test(priority = 42, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void signSubmitAmendTestCases() {
		obj = new ProgressNotesTestCases();
		// obj.objectiveTestCases();
		obj.validateSubmit();

	}

	@Test(priority = 38, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void microBiologyTestCases() {
		obj = new ProgressNotesTestCases();
		obj.objectiveTestCases();
		obj.generalLabTestCases();
		obj.microBiologyTestCases();

	}

	@Test(priority = 20, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void ehrTestCases() {
		obj = new ProgressNotesTestCases();
		obj.assesmentLinkTest();

	}

	@Test(priority = 29, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void assesmentTestCase() {
		obj = new ProgressNotesTestCases();
		obj.ehrTestCases();

	}

	@Test(priority = 31, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void flowSheetTestCases() {
		obj = new ProgressNotesTestCases();
		obj.flowSheetTestCases();

	}

}
