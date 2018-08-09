package pharmacy.testcases;

import pharmacy.pages.createOrderPage;

public class patientProfileTestcase {
	createOrderPage cr = new createOrderPage();
	createOrder_testcases ct = new createOrder_testcases();

	public void confirmOrder() {
		boolean flag = cr.checkIfOrdersPresent("Medication Orders");
		if (!flag) {
			ct.createOrderMed();
		}
		cr.chooseAction("Confirm");
		cr.clickFirstOrder("Medication Orders");
		// cr.setMainFrame();
		try {
			cr.interactionsConfirm();
		} catch (Exception e) {

		}
		try {
			cr.override();
		} catch (Exception e) {

		}
		try {
			cr.confirm();
		} catch (Exception e) {

		}
		cr.clickSendOrders();
		cr.validateAction("confirm", "Medication Orders");

	}

	public void holdOrder() {
		cr.chooseAction("Hold (Now)");
		cr.clickReferenceNumberOrder("Medication Orders");
		cr.validateAction("hold", "Medication Orders");
	}

	public void releaseOrder() {
		cr.chooseAction("Release (Now)");
		cr.clickReferenceNumberOrder("Medication Orders");

		try {
			cr.interactionsReleaseNow();
		} catch (Exception e) {

		}
		try {
			cr.interactions2ReleaseNow();
		} catch (Exception e) {

		}

		cr.validateAction("release", "Medication Orders");
	}

	public void discontinueOrder() {

		boolean flag = cr.checkIfOrdersPresent("IV Orders");
		if (!flag) {
			ct.createOrderIv();
		}
		cr.chooseAction("Discontinue");
		cr.clickFirstOrder("IV Orders");
		cr.clickSendOrders();
		cr.clickOnCompleteOrder();
		cr.validateAction("discontinue", "IV Orders");
	}

	public void interventionOrder() {

		boolean flag = cr.checkIfOrdersPresent("IV Orders");
		if (!flag) {
			ct.createOrderIv();
		}
		cr.countInterventions("IV Orders");
		cr.chooseAction("Interventions");
		cr.clickFirstOrder("IV Orders");
		cr.setInterventionFrame();
		cr.clickAddIntervention();
		cr.setClinicalArea();
		cr.setInterventionType();
		cr.setInterventionAction();
		cr.setFollowUpToNo();
		cr.clickClose();
		cr.validateAction("interventions", "IV Orders");
	}

}
