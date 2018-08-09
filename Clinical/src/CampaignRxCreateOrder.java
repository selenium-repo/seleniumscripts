import org.testng.annotations.Test;

import findpatient.FindPatient;
import pharmacy.testcases.createOrder_testcases;

public class CampaignRxCreateOrder {

	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.RxPatientSearch();
		objFind = null;
	}

	@Test(priority = 4, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void createOrderMed() throws Exception {
		createOrder_testcases obj = new createOrder_testcases();
		obj.createOrderMed();
	}

	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void createOrderIv() throws Exception {
		createOrder_testcases obj = new createOrder_testcases();
		obj.createOrderIv();
	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void RxCreateOrderPB() throws Exception {
		createOrder_testcases obj = new createOrder_testcases();
		obj.createOrderPB();
	}

	@Test(priority = 7, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void createOrderTPN() throws Exception {
		createOrder_testcases obj = new createOrder_testcases();
		obj.createOrderTPN();
	}

}
