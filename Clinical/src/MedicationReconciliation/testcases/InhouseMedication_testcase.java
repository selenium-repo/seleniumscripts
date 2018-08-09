package MedicationReconciliation.testcases;

import org.openqa.selenium.By;

import MedicationReconciliation.pages.MedicationReconciliationPage;
import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import order_entry.OrderEntryPlus_Medication;

public class InhouseMedication_testcase {

	MedicationReconciliationPage obj = new MedicationReconciliationPage();
	OrderEntryPlus_Medication obj1 = new OrderEntryPlus_Medication();

	public void inhouseAdd() {
		obj1.cleanUpOrders();
		obj.MedRecon_menunav();
		obj.diaTest();
		obj.errorDialog();
		obj.ClickInhouseMedications();
		int OrderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='inhouse']//div[@id='rxOrderManagerGridPane']//div[@id='rxOrderManagerGrid']//div[2]//div//div//div//div//div[contains(@class, 'dojoxGridRow')]")).size();
		String gridSize = String.valueOf(OrderListBeforeAdd);
		HashTableRepository.setHash("inhouseOrderListBeforeAdd", gridSize);
		obj.placeMedicationOrder();
		obj.verifyInhouseAdd();
	}

	public void inhouseDisplay() {
		obj.ClickInhouseMedications();
		boolean result = obj.verifyInhouseAdd();
		if (result)
			CustomReporter.MessageLogger("Display Functionality Of Inhouse Medication is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Display Functionality Of Inhouse Medication is not working properly", status.Fail);
	}

	public void inhouseOrderEntry() {
		obj.ClickOrderEntry();
		try {
			obj.setOrderEntryFrame();
			CustomReporter.MessageLogger("Order Entry Functionality Of Inhouse Medication is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Order Entry Functionality Of Inhouse Medication is not working properly", status.Fail);
		}
		obj.ClickClose();
		obj.SetMainFrame();
	}

	public void inhousePrint() {
		obj.MedRecon_menunav();
		obj.ClickInhouseMedications();
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
