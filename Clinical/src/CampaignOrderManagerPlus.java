import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import OrderManagerPlus.testcases.AllFunctionalityTestcases;
import OrderManagerPlus.testcases.AllTabsTestcases;
import OrderManagerPlus.testcases.SelectionPropertiesTestcase;
import OrderManagerPlus.testcases.ServiceCodeTestcase;
import OrderManagerPlus.testcases.placeOrderTestcase;
import common.Config;
import order_entry.OrderEntryPlus_Medication;

@Listeners(common.Listener.class)
@Test(groups = "CampaignOrderManagerPlus")
public class CampaignOrderManagerPlus {

	@Test(priority = 30, dependsOnGroups = { "CampaignLogin" })
	public static void serviceCode() throws Exception {

		ServiceCodeTestcase sct = new ServiceCodeTestcase();
		sct.servicecodetable();
	}

	@Test(priority = 31, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Medication() throws Exception {
		OrderEntryPlus_Medication obj = new OrderEntryPlus_Medication();
		obj.cleanUpOrders();
		obj = null;
	}

	@Test(priority = 32, dependsOnGroups = { "CampaignLogin" })
	public static void placeOrder() throws Exception {

		placeOrderTestcase pot = new placeOrderTestcase();
		pot.placeOrderFromOrderEntryPlus(Config.props.getProperty("drug_servicecode"));
	}

	@Test(priority = 33, dependsOnGroups = { "CampaignLogin" })
	public static void find() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyFind();
	}

	@Test(priority = 34, dependsOnGroups = { "CampaignLogin" })
	public static void refresh() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyRefresh();
	}

	@Test(priority = 35, dependsOnGroups = { "CampaignLogin" })
	public static void change() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyChange();
	}

	@Test(priority = 36, dependsOnGroups = { "CampaignLogin" })
	public static void receive() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyReceive();
	}

	@Test(priority = 37, dependsOnGroups = { "CampaignLogin" })
	public static void note() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyNote();
	}

	@Test(priority = 38, dependsOnGroups = { "CampaignLogin" })
	public static void verify() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyVerify();
	}

	@Test(priority = 39, dependsOnGroups = { "CampaignLogin" })
	public static void hold() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyHold();
	}

	@Test(priority = 40, dependsOnGroups = { "CampaignLogin" })
	public static void release() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyRelease();
	}

	@Test(priority = 41, dependsOnGroups = { "CampaignLogin" })
	public static void collect() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyCollect();
	}

	@Test(priority = 42, dependsOnGroups = { "CampaignLogin" })
	public static void acknowledgement() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyAcknowledgement();
	}

	@Test(priority = 43, dependsOnGroups = { "CampaignLogin" })
	public static void importResult() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyImportResult();
	}

	@Test(priority = 44, dependsOnGroups = { "CampaignLogin" })
	public static void results() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyResults();
	}

	@Test(priority = 45, dependsOnGroups = { "CampaignLogin" })
	public static void cancel() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyCancel();
	}

	@Test(priority = 46, dependsOnGroups = { "CampaignLogin" })
	public static void uncancel() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyUncancel();
	}

	@Test(priority = 47, dependsOnGroups = { "CampaignLogin" })
	public static void reprint() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyReprint();
	}

	@Test(priority = 48, dependsOnGroups = { "CampaignLogin" })
	public static void discontinue() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyDiscontinue();
	}

	@Test(priority = 49, dependsOnGroups = { "CampaignLogin" })
	public static void printAll() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyPrintAll();
	}

	@Test(priority = 50, dependsOnGroups = { "CampaignLogin" })
	public static void print() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyPrint();
	}

	@Test(priority = 51, dependsOnGroups = { "CampaignLogin" })
	public static void carePlan() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyCarePlan();
	}

	@Test(priority = 52, dependsOnGroups = { "CampaignLogin" })
	public static void ackUpd() throws Exception {

		AllFunctionalityTestcases obj = new AllFunctionalityTestcases();
		obj.verifyAckUpd();
	}

	@Test(priority = 53, dependsOnGroups = { "CampaignLogin" })
	public static void ivTab() throws Exception {

		AllTabsTestcases obj = new AllTabsTestcases();
		obj.verifyOrderManagerPlusIVTab();
	}

	@Test(priority = 54, dependsOnGroups = { "CampaignLogin" })
	public static void medTab() throws Exception {

		AllTabsTestcases obj = new AllTabsTestcases();
		obj.verifyOrderManagerPlusMedsTab();
	}

	@Test(priority = 55, dependsOnGroups = { "CampaignLogin" })
	public static void nonPharmacyTab() throws Exception {

		AllTabsTestcases obj = new AllTabsTestcases();
		obj.verifyOrderManagerPlusNonPharmacyTab();
	}

	@Test(priority = 56, dependsOnGroups = { "CampaignLogin" })
	public static void selectionPropertiesTab() throws Exception {

		SelectionPropertiesTestcase obj = new SelectionPropertiesTestcase();
		obj.verifyOrderManagerSelectionProperties();
	}

	@Test(priority = 57, dependsOnGroups = { "CampaignLogin" })
	public static void newOrdersTab() throws Exception {

		AllTabsTestcases obj = new AllTabsTestcases();
		obj.verifyOrderManagerPlusNewOrdersTab();
	}

	@Test(priority = 58, dependsOnGroups = { "CampaignLogin" })
	public static void allTab() throws Exception {

		AllTabsTestcases obj = new AllTabsTestcases();
		obj.verifyOrderManagerAllTab();
	}

}
