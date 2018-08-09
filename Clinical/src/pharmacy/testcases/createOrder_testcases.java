package pharmacy.testcases;

import pharmacy.pages.createOrderPage;

public class createOrder_testcases {

	createOrderPage obj = new createOrderPage();

	public void rxPtSearch() {
		obj.menuNavRxsearch();

	}

	public void createOrderSpecialInstructions() {
		try {
			obj.clickSendOrders();
		} catch (Exception e) {

		}
		obj.calculateRows("IV Orders");
		obj.clickCreateOrder();
		obj.selectOrder("IV");
		obj.clickSolutionIv();
		obj.RandomSelection("Solution");
		obj.setFrequencySolution();
		obj.clickAdditiveIv();
		obj.RandomSelection("Additive");
		obj.setFrequencyAdditive();
		obj.clickUpdate();
		obj.setinfusionRate();
		obj.antiMicrobialIndication();
		obj.cosigningPhysician();
	}

	public void createOrderIv() {
		try {
			obj.clickSendOrders();
		} catch (Exception e) {

		}
		obj.calculateRows("IV Orders");
		obj.clickCreateOrder();
		obj.selectOrder("IV");
		obj.clickSolutionIv();
		// obj.drugSelection("6");
		obj.RandomSelection("Solution");
		obj.setFrequencySolution();
		obj.clickAdditiveIv();
		obj.RandomSelection("Additive");
		obj.setFrequencyAdditive();
		obj.clickUpdate();
		obj.setinfusionRate();
		obj.antiMicrobialIndication();
		obj.cosigningPhysician();
		obj.submitOrderIv();
		obj.clickSendOrders();
		obj.validateOrder("IV Orders");
	}

	public void createOrderTPN() {
		try {
			obj.clickSendOrders();
		} catch (Exception e) {

		}
		obj.calculateRows("TPN Orders");
		obj.clickCreateOrder();
		obj.selectOrder("TPN");
		obj.setBaseVolume();
		obj.clickSolutionIv();
		obj.RandomSelection("Solution");
		obj.setFrequencySolution();
		obj.clickAdditiveIv();
		obj.RandomSelection("Additive");
		obj.setFrequencyAdditive();
		obj.clickUpdate();
		obj.setinfusionRate();
		obj.antiMicrobialIndication();
		obj.cosigningPhysician();
		obj.submitOrderIv();
		obj.clickSendOrders();
		obj.validateOrder("TPN Orders");
	}

	public void createOrderMed() {
		try {
			obj.clickSendOrders();
		} catch (Exception e) {

		}
		obj.calculateRows("Medication Orders");

		obj.placeMedOrders("withprn");

		obj.placeMedOrders("withoutprn");

		obj.placeMedOrders("onetime");

		obj.validateOrder("Medication Orders");
	}

	public void createOrderPB() {
		try {
			obj.clickSendOrders();
		} catch (Exception e) {

		}
		obj.calculateRows("PiggyBack Orders");

		obj.placePBOrder("withprn");

		obj.placePBOrder("withoutprn");

		obj.placePBOrder("onetime");

		obj.validateOrder("PiggyBack Orders");
	}
}
