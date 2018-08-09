package MedicationReconciliation.testcases;

import org.openqa.selenium.By;

import MedicationReconciliation.pages.MedicationReconciliationPage;
import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import order_entry.OrderEntryPlus_Medication;
import order_entry.OrderEntryPlus_OrderPanel;

public class HomeMedicationReconciliation_testcase {

	MedicationReconciliationPage obj = new MedicationReconciliationPage();
	OrderEntryPlus_Medication obj1 = new OrderEntryPlus_Medication();
	OrderEntryPlus_OrderPanel obj2 = new OrderEntryPlus_OrderPanel();

	public void homeMedAdd() {
		obj.MedRecon_menunav();
		obj.SetMainFrame();
		obj.diaTest();
		obj.errorDialog();
		obj.ClickAdd();
		obj.SetAddFrame();
		obj.SetDrug();
		obj.SetVolume();
		obj.SetRoute();
		obj.SetFrequency();
		obj.SetPRN();
		obj.SetSourceOfInformation();
		try {
			obj.checkVolumeValue("medRecDoseVolumeUnit");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		obj.ClickSubmitNewWindow();
		if (CommonLib.getElements(By.xpath("//div[@id='genericDialog']//div[2]//table//tbody//tr[2]//td//span//span//span//span[3]")).size() > 0) {
			CommonLib.clickButton(By.xpath("//div[@id='genericDialog']//div[2]//table//tbody//tr[2]//td//span//span//span//span[3]"));
		}
		try {
			obj.repeatPopup();
		} catch (Exception e) {

		}
		obj.SetMainFrame();
		try {
			obj.VerifyAdd();
		} catch (Exception e) {
		}
	}

	public void homeMedEdit() {
		obj.ClickHomeMedications();
		CommonLib.checkBoxSelect(By.id("homeMedsGrid_rowSelector_0"), 4);
		obj.ClickEdit();
		obj.SetEditFrame();
		obj.SetComments();
		obj.ClickSubmitNewWindow();
		obj.SetMainFrame();
		try {
			obj.VerifyEdit();
		} catch (Exception e) {
		}
	}

	public void homeMedVerify() {
		obj.ClickHomeMedications();
		CommonLib.checkBoxSelect(By.id("homeMedsGrid_rowSelector_0"), 4);
		obj.ClickVerify();
		try {
			obj.VerifyVerify();
		} catch (Exception e) {
		}
	}

	public void homeMedDelete() {
		obj.ClickHomeMedications();
		CommonLib.checkBoxSelect(By.id("homeMedsGrid_rowSelector_0"), 8);
		obj.ClickDelete();
		try {
			obj.VerifyDelete();
		} catch (Exception e) {
		}
	}

	public void homeMedFilter() {
		obj.ClickHomeMedications();
		obj.ClickFilter();
		obj.DeleteRadioButton();
		obj.ClickSubmitPopUp();
		obj.SetMainFrame();
		try {
			obj.verifyFilter();
		} catch (Exception e) {
		}
	}

	public void homeMedRefresh() {
		obj.ClickHomeMedications();
		CommonLib.checkBoxSelect(By.id("homeMedsGrid_rowSelector_0"), 4);
		obj.ClickRefresh();
		obj.VerifyRefresh();
	}

	public boolean homeMedNoMeds() {
		obj.ClickHomeMedications();
		obj.ClickNoMed();
		boolean resultNoMeds = obj.verifyNoMeds();
		return resultNoMeds;
	}

	public void homeMedAudit() {
		boolean result = homeMedNoMeds();
		if (result == true)
			CustomReporter.MessageLogger("Audit Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Audit Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void homeMedpharmacy() {
		obj.ClickHomeMedications();
		obj.ClickPharmacy();
		obj.SetPharmacyFrame();
		try {
			boolean result = CommonLib.checkSorryError("rxOrderManagerGridPane");
			if (result == true)
				CustomReporter.MessageLogger("Pharmacy Functionality Of Medication Reconciliation is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Pharmacy Functionality Of Medication Reconciliation is not working properly", status.Fail);
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("save_label"));
		obj.SetMainFrame();
	}

	public void homeMedPrint() {
		obj.MedRecon_menunav();
		obj.ClickPrint();
		obj.SetPrintFrame();
		obj.PrintSubmit();
		obj.verifyPrint();
		obj.SetMainFrame();
		obj.SetPrintFrame();
		obj.PrintClose();
		obj.SetMainFrame();
	}

	public void homeMedYesAdmission() {
		homeMedAdd();
		obj.SelectYes();
		obj.ClickSubmit();
		try {
			obj.setOrderingPhysicianFrame();
			obj.SelectOrderingPhysician();
			obj.SetMainFrame();
		} catch (Exception e) {
		}
		try {
			obj.setCophysicianFrame();
			obj.SelectCoPhysician();
			obj.SetMainFrame();
		} catch (Exception e) {
		}
		try {
			obj.setFrameOrderEntryPlus();

		} catch (Exception e) {
		}
		try {
			obj.orderEntry();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj.clickClose();
		obj.SetMainFrame();
		obj.VerifyYesAdmission();
	}

	public void homeMedNoAdmission() {
		homeMedAdd();
		obj.SelectNo();
		obj.ClickSubmit();
		try {
			obj.setCophysicianFrame();
			obj.SelectCoPhysician();
			obj.SetMainFrame();
		} catch (Exception e) {
		}
		obj.VerifyNoAdmission();
	}
}
