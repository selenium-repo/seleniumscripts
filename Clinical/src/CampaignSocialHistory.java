import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import SocialHistory.testcases.SocialHistoryTestcase;

@Listeners(common.Listener.class)

@Test(groups = "CampaignSocialHistory")
public class CampaignSocialHistory {
	//
	// @Test(priority = 3, dependsOnGroups = { "CampaignLogin" })
	// public static void FindPatient() throws Exception {
	// MenuNavigation.menuNav("SocialHistory");
	// }

	@Test(priority = 4, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistorygeneral() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.general();
	}

	//
	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryTobaccoAdd() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.tobaccoAdd();
	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryTobaccoEdit() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.tobaccoEdit();
	}

	@Test(priority = 7, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryTobaccoDelete() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.tobaccoDelete();
	}

	@Test(priority = 8, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryAlcoholAdd() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.alcoholAdd();
	}

	@Test(priority = 9, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryAlcoholEdit() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.alcoholEdit();
	}

	@Test(priority = 10, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryAlcoholDelete() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.alcoholDelete();
	}

	@Test(priority = 11, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryDrugAdd() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.socialDrugAdd();
	}

	@Test(priority = 12, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryDrugEdit() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.socialDrugEdit();
	}

	@Test(priority = 13, dependsOnGroups = { "CampaignLogin" })
	public static void socialHistoryDrugDelete() throws Exception {
		SocialHistoryTestcase obj = new SocialHistoryTestcase();
		obj.socialDrugDelete();
	}
}
