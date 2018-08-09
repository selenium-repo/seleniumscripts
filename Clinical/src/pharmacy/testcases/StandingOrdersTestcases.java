package pharmacy.testcases;

import java.awt.AWTException;

import common.CommonLib;
import common.CustomReporter;
import common.MenuNavigation;
import pharmacy.pages.StandingOrdersPage;

public class StandingOrdersTestcases extends StandingOrdersPage {

	public void selectPatient() {
		MenuNavigation.menuNav("RxPatientSearch");
		CommonLib.staticWait(3);
		verifyPatientSearchByNumber();

	}

	public void verifyMedStandingOrder() throws AWTException {
		CommonLib.staticWait(5);
		int before_add = getStandingOrdersRowCount("Med");
		System.out.println(before_add);
		clickCreateOrder();
		selectStandingOrderRadioButton();
		selectStandingOrderList();
		selectDirectAccessNo();
		clickStandingOrderSubmit();
		checkStandingOrder("0");
		clickSubmit();
		checkInteractions();
		selectUserOrderType();
		enterSpecialInstructions();
		clickOrderSubmit();
		clickSendOrders();
		CommonLib.staticWait(5);
		int after_add = getStandingOrdersRowCount("Med");
		// System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger(
					"Medication Standing Order record has been added to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger(
					"Failed to add an Medication Standing Order record to the system, please try again! which is not as expected",
					CustomReporter.status.Fail);

		}

	}

	public void verifyPBStandingOrder() throws AWTException {
		CommonLib.staticWait(5);
		int before_add = getStandingOrdersRowCount("P/B");
		System.out.println(before_add);
		clickCreateOrder();
		selectStandingOrderRadioButton();
		selectStandingOrderList();
		selectDirectAccessNo();
		clickStandingOrderSubmit();
		checkStandingOrder("1");
		clickSubmit();
		checkInteractions();
		selectUserOrderType();
		enterSpecialInstructions();
		clickOrderSubmit();
		checkInteractions();
		clickSendOrders();
		CommonLib.staticWait(5);
		int after_add = getStandingOrdersRowCount("P/B");
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger(
					"PiggyBack Standing Order record has been added to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger(
					"Failed to add an PiggyBack Standing Order record to the system, please try again! which is not as expected",
					CustomReporter.status.Fail);

		}

	}

	public void verifyIVStandingOrder() throws AWTException {
		CommonLib.staticWait(5);
		int before_add = getStandingOrdersRowCount("IV");
		System.out.println(before_add);
		clickCreateOrder();
		selectStandingOrderRadioButton();
		selectStandingOrderList();
		selectDirectAccessNo();
		clickStandingOrderSubmit();
		checkStandingOrder("2");
		clickSubmit();
		checkInteractions();
		selectUserOrderType();
		enterSpecialInstructions();
		clickOrderSubmit();
		checkInteractions();
		CommonLib.staticWait(3);
		clickSendOrders();
		CommonLib.staticWait(5);
		int after_add = getStandingOrdersRowCount("IV");
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger(
					"IV Standing Order record has been added to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger(
					"Failed to add an IV Standing Order record to the system, please try again! which is not as expected",
					CustomReporter.status.Fail);

		}

	}

	public void verifyTPNStandingOrder() throws AWTException {
		CommonLib.staticWait(5);
		int before_add = getStandingOrdersRowCount("TPN");
		System.out.println(before_add);
		clickCreateOrder();
		selectStandingOrderRadioButton();
		selectStandingOrderList();
		selectDirectAccessNo();
		clickStandingOrderSubmit();
		checkStandingOrder("3");
		clickSubmit();
		checkInteractions();
		selectUserOrderType();
		enterSpecialInstructions();
		clickOrderSubmit();
		checkInteractions();
		clickSendOrders();
		CommonLib.staticWait(5);
		int after_add = getStandingOrdersRowCount("TPN");
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger(
					"TPN Standing Order record has been added to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger(
					"Failed to add an TPN Standing Order record to the system, please try again! which is not as expected",
					CustomReporter.status.Fail);

		}

	}

}
