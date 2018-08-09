import org.testng.annotations.Test;

import consultationNote.TestCases.CnTestCases;

public class CampaignConsultationNotes {

	@Test(priority = 37, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public void impressionCN() {

		CnTestCases obj = new CnTestCases();
		obj.impressionTestCases();

	}

	@Test(priority = 38, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void recommendationTestCases() {

		CnTestCases obj = new CnTestCases();
		obj.recommendationsTestCases();

	}

	@Test(priority = 39, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void hnpTestCases() {

		CnTestCases obj = new CnTestCases();
		obj.hnpConsultationTestCases();

	}

	@Test(priority = 40, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void ccCNTestCases() {

		CnTestCases obj = new CnTestCases();
		obj.ccTestCases();

	}

	@Test(priority = 41, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void cc() {
		CnTestCases obj = new CnTestCases();
		obj.ccAddTestCase();
		obj.ccEditTestCase();
		obj.ccDeleteTestCase();
	}

	@Test(priority = 42, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void concludingStatement() {
		CnTestCases obj = new CnTestCases();
		obj.concludingStatementTestcase();
	}

	@Test(priority = 43, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public void getHnPData() {
		CnTestCases obj = new CnTestCases();
		obj.getHnPData();
	}

}
