import org.testng.annotations.Test;

import historyAndPhysical.testcases.HnPTestCases;

public class CampaignHistoryAndPhysicial {

	@Test(priority = 20, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void chiefComplaint() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.checkIfSigned();
		obj.chiefComplaintTestCases();

	}

	@Test(priority = 21, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void HistoryPresentIllness() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.historyOfPresentIllness("HP");
	}

	@Test(priority = 22, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void pastMedicalHistory() {
		// HistoryAndPhysicial obj1 = new HistoryAndPhysicial();
		// obj1.gotoHistoryAndPhysicial();
		HnPTestCases obj = new HnPTestCases("HP");
		obj.pastMedicalHistoryTestCases("HP");

	}

	@Test(priority = 23, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void pastSurgicalHistory() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.pastSurgicalHistoryTestCases("HP");

	}

	@Test(priority = 24, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void familyMedicalHistory() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.FamilyMedicalHistoryTestCases("HP");

	}

	@Test(priority = 25, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void socialHistory() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.socialHistoryTestCases();
	}

	@Test(priority = 26, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void immunization() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.immunizationTestCases("");
	}

	@Test(priority = 27, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void homeMedication() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.homeMedicationTestCases("HP");
	}

	@Test(priority = 29, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void allergies() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.allergiesAdd("HP");
		obj.allergiesEdit("HP");
		obj.allergiesDelete();
		obj.allergiesAdd("HP");
	}

	@Test(priority = 30, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void reviewOfSystem() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.reviewSystemTestCases("HP");

	}

	@Test(priority = 31, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void physicialExam() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.physicialExamTestCases("");

	}

	@Test(priority = 32, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void studies() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.microbiologyResult("HP");
		// obj.radioLogyTestCases();
		obj.generalLabTestCases("HP");
		obj.labRadNotes("HP");
	}

	@Test(priority = 33, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void impression() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.impressionTestCases("HP");

	}

	@Test(priority = 34, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void plan() {

		HnPTestCases obj = new HnPTestCases("HP");
		obj.planTestCases("HP");
	}

	@Test(priority = 35, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void cc() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.ccAddTestCase("HP");
		obj.ccEditTestCase("HP");
		obj.ccDeleteTestCase();
		obj.ccAddTestCase("HP");
	}

	@Test(priority = 36, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void signSubmit() {
		HnPTestCases obj = new HnPTestCases("HP");
		obj.ammend();
	}

}
