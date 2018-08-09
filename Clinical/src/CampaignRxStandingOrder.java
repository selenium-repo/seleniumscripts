import java.awt.AWTException;

import org.testng.annotations.Test;

import pharmacy.testcases.StandingOrdersTestcases;

public class CampaignRxStandingOrder {

	@Test(priority = 504, dependsOnGroups = "CampaignLogin")
	public static void verifyMedStandingOrder() throws AWTException {
		StandingOrdersTestcases order = new StandingOrdersTestcases();
		order.selectPatient();
		order.verifyMedStandingOrder();
	}

	@Test(priority = 505, dependsOnGroups = "CampaignLogin")
	public static void verifyPBStandingOrder() throws AWTException {
		StandingOrdersTestcases order = new StandingOrdersTestcases();
		order.verifyPBStandingOrder();
	}

	@Test(priority = 506, dependsOnGroups = "CampaignLogin")
	public static void verifyIVStandingOrder() throws AWTException {
		StandingOrdersTestcases order = new StandingOrdersTestcases();
		order.verifyIVStandingOrder();
	}

	@Test(priority = 507, dependsOnGroups = "CampaignLogin")
	public static void verifyTPNStandingOrder() throws AWTException {
		StandingOrdersTestcases order = new StandingOrdersTestcases();
		order.verifyTPNStandingOrder();
	}
}
