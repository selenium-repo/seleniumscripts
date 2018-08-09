package OrderManagerPlus.testcases;

import OrderManagerPlus.pages.OrderManagerPlusPage;
import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.MenuNavigation;

public class AllTabsTestcases extends OrderManagerPlusPage {

	public void verifyOrderManagerAllTab() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String non_pharm_order = Config.props.getProperty("non_pharm");
		String order_test = Config.props.getProperty("other_test");
		String order_type = Config.props.getProperty("order_type");
		boolean check = addOrderBasket("", non_pharm_order);
		if (check) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickAllTab();
			String firstrow_value = getFirstRowDetailValue();
			if (non_pharm_order.equals(firstrow_value)) {
				String date_value = getFirstRowDetailDateValue();
				CustomReporter.MessageLogger(
						"When Non Pharmacy order " + firstrow_value + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's ALL Tab with date and time details as : " + date_value + "successfully, which is as expected",
						CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Non Pharmacy order " + non_pharm_order + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's ALL Tab successfully, which is not as expected",
						CustomReporter.status.Fail);
			}
		}
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		boolean result = addOrderBasket(order_type, order_test);
		if (result) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickAllTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowDetailValue();
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValue();
				CustomReporter.MessageLogger(
						"When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's ALL Tab with date and time details as : " + date_data + "successfully, which is as expected",
						CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's ALL Tab successfully, which is not as expected",
						CustomReporter.status.Fail);
			}

		}
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String order_type_iv = Config.props.getProperty("order_type_iv");
		boolean checker = addIVOrder(order_type_iv, order_test);
		if (checker) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickIVsTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowValueIV();
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValueIV();
				CustomReporter.MessageLogger(
						"When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's IV Tab with date and time details as : " + date_data + "successfully, which is as expected",
						CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's IV Tab, which is not as expected", CustomReporter.status.Fail);
			}

		}

	}

	public void verifyOrderManagerPlusIVTab() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String order_test = Config.props.getProperty("other_test");
		String order_type_iv = Config.props.getProperty("order_type_iv");
		boolean result = addIVOrder(order_type_iv, order_test);
		if (result) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickIVsTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowValueIV();
			System.out.println("The first row data in IV tab is :" + firstrow_data);
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValueIV();
				CustomReporter.MessageLogger(
						"When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's IV Tab with date and time details as : " + date_data + "successfully, which is as expected",
						CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's IV Tab, which is not as expected", CustomReporter.status.Fail);
			}

		}

	}

	public void verifyOrderManagerPlusMedsTab() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String order_test = Config.props.getProperty("other_test");
		String order_type = Config.props.getProperty("order_type");
		boolean result = addOrderBasket(order_type, order_test);
		if (result) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickMedTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowValueMed();
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValueMed();
				CustomReporter.MessageLogger(
						"When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's Med Tab with date and time details as : " + date_data + "successfully, which is as expected",
						CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's Med Tab, which is not as expected", CustomReporter.status.Fail);
			}

		}

	}

	public void verifyOrderManagerPlusNonPharmacyTab() throws InterruptedException {

		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String non_pharm_order = Config.props.getProperty("non_pharm");
		boolean check = addOrderBasket("", non_pharm_order);
		if (check) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickNonPharmacyTab();
			String firstrow_value = getFirstRowValueNon();
			if (non_pharm_order.equals(firstrow_value)) {
				String date_value = getFirstRowDetailDateValueNon();
				CustomReporter.MessageLogger("When Non Pharmacy order " + firstrow_value + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's Non-Pharmacy Tab with date and time details as : " + date_value
						+ "successfully, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, When Non Pharmacy order " + non_pharm_order + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's  Non-PharmacyL Tab successfully, which is not as expected",
						CustomReporter.status.Fail);
			}
		}

	}

	public void verifyOrderManagerPlusNewOrdersTab() throws InterruptedException {

		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String order_test = Config.props.getProperty("other_test");
		String order_type = Config.props.getProperty("order_type");
		boolean result = addOrderBasket(order_type, order_test);
		if (result) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickMedTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowValueMed();
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValueMed();
				clickNewOrdersTab();
				CommonLib.staticWait(2);
				clickNewOrdersNewMedsTab();
				CommonLib.staticWait(3);
				String firstrow_new_value = getNewOrdersFirstRowMedValue();
				if (firstrow_new_value.contains(order_test)) {
					CustomReporter.MessageLogger("When Med order " + order_test + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's New Orders-New Meds Tab with date and time details as : " + date_data
							+ "successfully, which is as expected", CustomReporter.status.Pass);

				} else {
					CustomReporter.MessageLogger("Failed, When Med order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's  New Orders New Meds Tab successfully, which is not as expected",
							CustomReporter.status.Fail);
				}

			} else {
				CustomReporter.MessageLogger("Failed, When Pharmacy order " + order_test + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's Med Tab, which is not as expected", CustomReporter.status.Fail);
			}

		}

		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String order_test1 = Config.props.getProperty("other_test");
		String order_type_iv = Config.props.getProperty("order_type_iv");
		boolean result1 = addIVOrder(order_type_iv, order_test1);
		if (result1) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickIVsTab();
			CommonLib.staticWait(3);
			String firstrow_data = getFirstRowValueIV();
			if (order_test.contains(firstrow_data)) {
				String date_data = getFirstRowDetailDateValueIV();
				clickNewOrdersTab();
				CommonLib.staticWait(2);
				clickNewOrdersNewIVTab();
				CommonLib.staticWait(2);
				String firstrow_new_value = getNewOrdersFirstRowIVValue();
				if (firstrow_new_value.contains(firstrow_data)) {
					CustomReporter.MessageLogger("When IV order " + order_test1 + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's New Orders-New IVs Tab with date and time details as : " + date_data
							+ "successfully, which is as expected", CustomReporter.status.Pass);

				} else {
					CustomReporter.MessageLogger("Failed, When IV order " + order_test1 + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's  New Orders New IVs Tab successfully, which is not as expected",
							CustomReporter.status.Fail);
				}

			} else {
				CustomReporter.MessageLogger("Failed, When IV order " + order_test1 + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's IV Tab, which is not as expected", CustomReporter.status.Fail);
			}

		}

		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		String non_pharm_order = Config.props.getProperty("non_pharm");
		boolean check = addOrderBasket("", non_pharm_order);
		if (check) {
			completeOrderBasket();
			MenuNavigation.menuNav("Order Manager Plus");
			clickNonPharmacyTab();
			String firstrow_value = getFirstRowValueNon();
			if (non_pharm_order.equals(firstrow_value)) {
				String date_value = getFirstRowDetailDateValueNon();
				clickNewOrdersTab();
				CommonLib.staticWait(2);
				clickNewOrdersNewNonPharmacyTab();
				CommonLib.staticWait(2);
				String firstrow_new_value = getNewOrdersFirstRowNonPharmValue();
				if (firstrow_new_value.contains(non_pharm_order)) {
					CustomReporter.MessageLogger("When Non Pharmacy order " + firstrow_new_value + " placed in Order Entry Plus screen, is displaying in Order Manager Plus's New Orders-New Non-Pharmacy Tab with date and time details as : "
							+ date_value + "successfully, which is as expected", CustomReporter.status.Pass);

				} else {
					CustomReporter.MessageLogger(
							"Failed, When Non Pharmacy order " + non_pharm_order + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's  New Orders New Non-Pharmacy Tab successfully, which is not as expected",
							CustomReporter.status.Fail);
				}

			} else {
				CustomReporter.MessageLogger("Failed, When Non Pharmacy order " + non_pharm_order + " placed in Order Entry Plus screen, is not displaying in Order Manager Plus's  Non-PharmacyL Tab successfully, which is not as expected",
						CustomReporter.status.Fail);
			}
		}

	}
}
