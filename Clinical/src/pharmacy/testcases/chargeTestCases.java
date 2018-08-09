package pharmacy.testcases;

import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;
import pharmacy.pages.chargesPage;
import pharmacy.pages.createOrderPage;

public class chargeTestCases {
	chargesPage cp = new chargesPage();
	createOrderPage cop = new createOrderPage();

	public chargeTestCases() {
		// TODO Auto-generated constructor stub
		// MenuNavigation.menuNav("Rx Chg Prgms");
	}

	public void addChargeSinglePatient() {
		MenuNavigation.menuNav("ChargesWithoutOrders");

		cop.setMainFrame();
		int sizeBefore = cp.countRows();
		MenuNavigation.menuNav("Rx Chg Prgms");
		cop.setMainFrame();
		cp.setPattientNumber(1);
		cp.clickSearchDrug();
		cop.setDrugFrame();
		cop.RandomSelection("Charge");
		cop.setMainFrame();
		cp.setQuantity("QuantitySingle");
		cp.clickAdd();
		cp.validateOnScreen("Charge", "Quantity");
		MenuNavigation.menuNav("ChargesWithoutOrders");
		int sizeAfter = cp.countRows();
		if (sizeAfter - sizeBefore == 1 && cp.validateChargeWithoutOrders()) {
			CustomReporter.MessageLogger("charges placed", status.Pass);
		} else
			CustomReporter.MessageLogger("charges not placed", status.Fail);

	}

	public void addChargesMultiPatient() {

		MenuNavigation.menuNav("ChargesWithoutOrders");
		cop.setMainFrame();
		int sizeBeforeOriginalPatient = cp.countRows();
		cop.setMainFrame();
		cp.clickNewPatient();
		cp.setPatientNumberMultiUser();
		cp.clickMultiPatientSearch();
		int sizeBeforeMultiPatient = cp.countRows();
		MenuNavigation.menuNav("Rx Chg PrgmsMulti");
		cop.setMainFrame();
		cp.setPattientNumber(1);
		cop.setMainFrame();
		cp.clickSearchDrug();
		cop.RandomSelection("ChargeOriginal");
		cop.setMainFrame();
		cp.setQuantity("QuantityMultiOrginal");
		cp.clickAdd();
		cp.clickSubmit();
		// cp.validateOnScreen("ChargeOriginal", "QuantityMultiOrginal");
		cp.setPattientNumber(2);
		cop.setMainFrame();
		// cp.clickDrugName();
		cp.clickSearchDrug();
		// cop.setDrugFrame();
		cop.RandomSelection("ChargeNew");
		cop.setMainFrame();
		cp.setQuantity("QuantityMultiNew");
		cp.clickAdd();
		cp.clickSubmit();
		// cp.validateOnScreen("ChargeNew", "QuantityMultiNew");
		MenuNavigation.menuNav("ChargesWithoutOrders");
		int sizeAfterMultiPatient = cp.countRows();
		cp.clickNewPatient();
		cp.setPatientNumberMultiUserOriginal();
		cp.clickMultiPatientSearch();
		int sizeAfterOriginalPatient = cp.countRows();
		if (sizeAfterOriginalPatient - sizeBeforeOriginalPatient == 1
				&& sizeAfterMultiPatient - sizeBeforeMultiPatient == 1) {
			CustomReporter.MessageLogger("charges placed", status.Pass);
		} else
			CustomReporter.MessageLogger("charges not placed", status.Fail);

	}

}
