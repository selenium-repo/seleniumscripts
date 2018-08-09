
import java.awt.AWTException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import order_entry.OrderEntryPlus_Abbrevonym;
import order_entry.OrderEntryPlus_CPT;
import order_entry.OrderEntryPlus_Medication;
import order_entry.OrderEntryPlus_OrderPanel;
import order_entry.OrderEntryPlus_OrderSet;
import order_entry.OrderEntryPlus_RxOrderSet;
import order_entry.OrderEntryPlus_Service;
import order_entry.OrderEntryPlus_TherapeuticClass;
import order_entry.OrderEntryPlus_VerifyIcons;

@Listeners(common.Listener.class)
@Test(groups = "CampaignOrderEntryPlus")
public class CampaignOrderEntryPlus {

	@Test(priority = 20, dependsOnGroups = { "CampaignLogin" })
	public static void allergies() throws Exception {

		ClinicalFunction.allergies objAllergy = new ClinicalFunction.allergies();
		objAllergy.verifyAllergiesFunctions();
		objAllergy = null;

	}

	@Test(priority = 21, dependsOnGroups = { "CampaignLogin" })
	public static void cleanUpOrders() throws Exception {
		OrderEntryPlus_Medication obj = new OrderEntryPlus_Medication();
		obj.cleanUpOrders();
		obj = null;
	}

	@Test(priority = 22, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Order_Panel() throws Exception {
		OrderEntryPlus_OrderPanel order = new OrderEntryPlus_OrderPanel();
		order.verifyOrderEntryPlusFunctions();
		order = null;
	}

	@Test(priority = 23, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_TherapeuticClass() throws Exception {
		OrderEntryPlus_TherapeuticClass obj = new OrderEntryPlus_TherapeuticClass();
		obj.therapeuticClass();
		obj = null;
	}

	@Test(priority = 24, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Medication() throws Exception {
		OrderEntryPlus_Medication obj = new OrderEntryPlus_Medication();
		obj.medication(false);
		obj = null;
	}

	@Test(priority = 25, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_RxOrderSet() throws InterruptedException {
		OrderEntryPlus_RxOrderSet obj = new OrderEntryPlus_RxOrderSet();
		obj.verifyAddRxOrderSet();
		obj = null;
	}

	@Test(priority = 26, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Order_Set() throws Exception {
		OrderEntryPlus_OrderSet order = new OrderEntryPlus_OrderSet();
		order.verifyOrderSetFunctions();
		order = null;
	}

	@Test(priority = 27, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_Service() throws InterruptedException {
		OrderEntryPlus_Service obj = new OrderEntryPlus_Service();
		obj.verifyOrderEntryPlusServiceTab();
	}

	@Test(priority = 28, dependsOnGroups = { "CampaignLogin" }, dependsOnMethods = { "OrderEntryPlus_Service" })
	public static void OrderEntryPlus_Abbrevonym() throws InterruptedException {
		OrderEntryPlus_Abbrevonym obj = new OrderEntryPlus_Abbrevonym();
		obj.verifyOrderEntryPlusAbbrevonymTab();
	}

	@Test(priority = 29, dependsOnGroups = { "CampaignLogin" })
	public static void OrderEntryPlus_CPT() throws Exception {
		OrderEntryPlus_CPT obj = new OrderEntryPlus_CPT();
		obj.cpt();
		obj = null;
	}

	@Test(priority = 30, dependsOnGroups = { "CampaignLogin" }, enabled = false)
	public static void OrderEntryPlus_VerifyIcons() throws InterruptedException, AWTException {
		OrderEntryPlus_VerifyIcons obj = new OrderEntryPlus_VerifyIcons();
		obj.verifyOrderEntryPlusIcons();
	}

}
