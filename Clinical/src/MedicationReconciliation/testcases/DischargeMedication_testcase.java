package MedicationReconciliation.testcases;

import org.openqa.selenium.By;

import MedicationReconciliation.pages.MedicationReconciliationPage;
import common.CommonLib;
import common.HashTableRepository;
import common.MenuNavigation;
import order_entry.OrderEntryPlus_VerifyIcons;

public class DischargeMedication_testcase {

	MedicationReconciliationPage obj = new MedicationReconciliationPage();
	OrderEntryPlus_VerifyIcons obj2 = new OrderEntryPlus_VerifyIcons();

	public void dischargeViewFromLOC() {

		try {
			MenuNavigation.menuNav("MedicationReconciliation");
			obj.diaTest();
			obj.errorDialog();
			obj.ClickDischargeMedications();
			int OrderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='discharge']//div[@id='dischargeMedsGridPane']//div[@id='dischargeMedsGrid']//div[2]//div//div//div//div//div[contains(@class, 'dojoxGridRow')]")).size();
			String gridSize = String.valueOf(OrderListBeforeAdd);
			HashTableRepository.setHash("DischargeOrderListBeforeAdd", gridSize);
			obj2.verifyLevelOfCaresDischarge(true);
			MenuNavigation.menuNav("MedicationReconciliation");
			obj.diaTest();
			obj.errorDialog();
			obj.ClickDischargeMedications();
			obj.verifyDischargeAdd();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dischargePrint() {
		obj.MedRecon_menunav();
		obj.ClickDischargeMedications();
		obj.ClickPrint();
		obj.SetPrintFrame();
		obj.PrintSubmit();
		obj.verifyPrint();
		obj.SetMainFrame();
		obj.SetPrintFrame();
		obj.PrintClose();
		obj.SetMainFrame();
	}
}
