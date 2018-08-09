import org.testng.annotations.Test;

import DischargeSummaryPhyscDoc.testcases.DischargeSummaryPhyscDocTestCase;

public class CampaignDischargeSummaryPhysDoc {
	@Test(priority = 20, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void setDischargeCondition() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.dischargeConditionTestcases();
	}

	@Test(priority = 21, dependsOnGroups = { "CampaignLogin" }, enabled = false)

	public void hpi() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.historyOfPresentIllness();
	}

	@Test(priority = 21, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void ccTestCase() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.ccTestCase();
	}

	@Test(priority = 22, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void diagnosisTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.diagnosisTestCases();
	}

	@Test(priority = 22, dependsOnGroups = { "CampaignLogin" }, enabled = false)

	public void procedureTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.procedureTestCases();
	}

	@Test(priority = 23, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void ConsultationNoteTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.consulationTestCases();
	}

	@Test(priority = 24, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void commentNoteTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.commentTestCase();
	}

	@Test(priority = 25, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void hospitalCareTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.hospitalCareTestCase();
	}

	@Test(priority = 26, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void followupTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.followUpTestcases();
	}

	@Test(priority = 27, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void dischargeMedicationTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.dischargeMedication();
	}

	@Test(priority = 29, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void submitTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.submit();
	}

	@Test(priority = 28, dependsOnGroups = { "CampaignLogin" }, enabled = true)

	public void dischargePhysicalExamTestCases() {

		DischargeSummaryPhyscDocTestCase obj = new DischargeSummaryPhyscDocTestCase();
		obj.dischargePhysicalExam();
	}

}
