
package MedicationReconciliation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import order_entry.OrderEntryPlus_Medication;
import order_entry.OrderEntryPlus_OrderSet;
import order_entry.OrderEntryPlus_VerifyIcons;

public class MedicationReconciliationPage {

	OrderEntryPlus_Medication obj = new OrderEntryPlus_Medication();
	OrderEntryPlus_VerifyIcons obj1 = new OrderEntryPlus_VerifyIcons();
	OrderEntryPlus_OrderSet obj2 = new OrderEntryPlus_OrderSet();

	public void MedRecon_menunav() {
		MenuNavigation.menuNav("MedicationReconciliation");
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void setFrameOrderEntryPlus() {
		WebElement OrderEntryPlusFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-plus.action?action=init')][last()]"));
		CommonLib.setFrameFromCurrent(OrderEntryPlusFrame);
	}

	public void diaTest() {
		try {
			CommonLib.changeimplicitwait(3);
			String t = CommonLib.getText(By.id("drFirstSetupDialog_title"));
			CommonLib.clickButton(By.xpath("//span[text()='" + t + "']//following-sibling::span[@title='Cancel']"));
		} catch (Exception e) {
		}
		CommonLib.resetImplicitWait();
	}

	// public void medRecDialog() {
	// // CommonLib.staticWait(10);
	// boolean flag = false;
	// CommonLib.setImplicitWait(50);
	// while (flag != true) {
	// if
	// (CommonLib.getElements(By.xpath("//div[@id='drFirstSetupDialog']//span[(@title='Cancel')
	// and (@class='dijitDialogCloseIcon')]")).size() > 0) {
	// CommonLib.clickButton(By.xpath("//div[@id='drFirstSetupDialog']//span[(@title='Cancel')
	// and (@class='dijitDialogCloseIcon')]"));
	// flag = true;
	// break;
	// } else
	// break;
	// }
	// CommonLib.resetImplicitWait();
	// }

	public void errorDialog() {
		boolean flag = false;
		CommonLib.setImplicitWait(50);
		while (flag != true) {
			if (CommonLib.getElements(By.xpath("//div[@id='genericDialog']//div[2]//table/tbody/tr[4]/td/span/span/span//span[@id='dijit_form_Button_0_label']")).size() > 0) {
				CommonLib.clickButton(By.xpath("//div[@id='genericDialog']//div[2]//table/tbody/tr[4]/td/span/span/span//span[@id='dijit_form_Button_0_label']"));
				flag = true;
				break;
			} else
				break;
		}
		CommonLib.resetImplicitWait();
	}

	public void orderEntry() throws InterruptedException {
		obj1.checkAllergiesWarning();
		List<WebElement> orderList = CommonLib.getElements(By.xpath(
				"//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[contains(@class, 'dijitInline dijitIcon dijitTreeIcon errorIcon') or contains(@class, 'dijitInline dijitIcon dijitTreeIcon exclamationIcon')]"));
		WebElement element = orderList.get(orderList.size() - 1);
		element.click();
		CommonLib.clickButton(By.xpath("//span[@id='editBasketItem']"));
		WebElement OrderEntryPlusEditFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail.action?action=changeRxOrderPlus')][last()]"));
		CommonLib.setFrameFromCurrent(OrderEntryPlusEditFrame);
		try {
			CommonLib.selectDojoDropDownByKeyDownNumber("routeList", 2);
		} catch (Exception e) {

		}
		try {
			CommonLib.selectDojoDropDownByKeyDownNumber("priorityList", 2);
		} catch (Exception e) {

		}
		try {
			CommonLib.selectDojoListValue("sigCodeList", "");
		} catch (Exception e) {

		}
		try {
			CommonLib.selectDojoListValue("prnIndList", "");
		} catch (Exception e) {

		}
		try {
			String durationCheck = CommonLib.getElement(By.xpath("//input[@id='duration']")).getText();
			if (durationCheck.equals("")) {
				CommonLib.setData(By.xpath("//input[@id='duration']"), "1");
				CommonLib.clickButton(By.xpath("//input[@id='durationType_D']"));
			}
			CommonLib.clickButton(By.id("button.submit_label"));
		} catch (Exception e) {

		}

		obj.clinicalWarnings();
		SetMainFrame();
		setFrameOrderEntryPlus();
		CommonLib.clickButton(By.xpath("//span[@id='completeOrders']"));
		obj.checkReadBackOrders();
	}

	public void clickClose() {
		CommonLib.clickButton(By.id("close_label"));
	}

	public void SelectYes() {
		CommonLib.selectradio(By.id("cdaYN_Y_0"));
	}

	public void SelectNo() {
		CommonLib.selectradio(By.id("cdaYN_N_0"));
	}

	public void ClickHomeMedications() {
		CommonLib.clickButton(By.id("medReconTabs_tablist_home"));
	}

	public void ClickInhouseMedications() {
		CommonLib.clickButton(By.id("medReconTabs_tablist_inhouse"));
	}

	public void ClickDischargeMedications() {
		CommonLib.clickButton(By.id("medReconTabs_tablist_discharge"));
	}

	public void ClickFilter() {
		CommonLib.clickButton(By.id("filter_label"));
	}

	public void ClickRefresh() {
		CommonLib.clickButton(By.id("refreshPage_label"));
	}

	public void ClickAdd() {
		CommonLib.clickButton(By.id("add_label"));
	}

	public void ClickEdit() {
		CommonLib.clickButton(By.id("edit_label"));
	}

	public void ClickDelete() {
		CommonLib.clickButton(By.id("delete_label"));
	}

	public void ClickVerify() {
		CommonLib.clickButton(By.id("verify_label"));
	}

	public void ClickNoMed() {
		CommonLib.clickButton(By.id("noHomeMeds_label"));
	}

	public void ClickCopyMedPreviousVisit() {
		CommonLib.clickButton(By.id("copyHomeMeds_label"));
	}

	public void ClickPharmacy() {
		CommonLib.clickButton(By.id("pharmacy_label"));
	}

	public void ClickOrderEntry() {
		CommonLib.clickButton(By.id("newOrderEntry_label"));
	}

	public void ClickPrint() {
		CommonLib.clickButton(By.id("print_label"));
	}

	public void ClickAudit() {
		CommonLib.clickButton(By.id("audit_label"));
	}

	public void ClickCCDAImport() {
		CommonLib.clickButton(By.id("CCDImport"));
	}

	public void ClickSubmit() {
		CommonLib.clickButton(By.id("homeSubmitButton_label"));
	}

	public void ClickCancel() {
		CommonLib.clickButton(By.id("homeCancelButton_label"));
	}

	public void ClickClose() {
		CommonLib.clickButton(By.id("close_label"));
	}

	public void SetAddFrame() {
		WebElement MedAddFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation-entry.action?action=add')][last()]"));
		CommonLib.setFrameFromCurrent(MedAddFrame);
	}

	public void setOrderEntryFrame() {
		WebElement orderEntryFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-plus.action')][last()]"));
		CommonLib.setFrameFromCurrent(orderEntryFrame);
	}

	public void setOrderingPhysicianFrame() {
		try {
			WebElement orderingPhysicianFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'OrderingPhysician.do')][last()]"));
			CommonLib.setFrameFromCurrent(orderingPhysicianFrame);
		} catch (Exception e) {
		}
	}

	public void setCophysicianFrame() {
		try {
			WebElement CophysicianFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'CoPhysician.do?')][last()]"));
			CommonLib.setFrameFromCurrent(CophysicianFrame);
		} catch (Exception e) {
		}
	}

	public void SetDrug() {
		try {
			// CommonLib.selectDojoListValue("medicationIDList", "");
			String a = CommonLib.selectRequiredDojoListValue("medicationIDList", Config.props.getProperty("medrec_drug"));
			// String a = CommonLib.selectDojoListValue("medicationIDList", "");
			HashTableRepository.setHash("selectedMedication", a);
		} catch (Exception e) {
		}
	}

	public void repeatPopup() {
		CommonLib.clickButton(By.xpath("//span[text()='Yes']"));

	}

	public void SelectCoPhysician() {
		try {
			CommonLib.selectDojoDropDownByKeyDownNumber("coSigningPhysicianList", 1);
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));
	}

	public void SelectOrderingPhysician() {
		try {
			CommonLib.selectDojoListValue("orderingPhysicianList", "");
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));
	}

	public void SetVolume() {
		try {
			CommonLib.selectDojoListValue("medRecDoseVolumeUnit", "");
		} catch (Exception e) {
		}
	}

	public void SetRoute() {
		try {
			CommonLib.selectDojoListValue("routeList", "");
		} catch (Exception e) {
		}
	}

	public void SetFrequency() {
		try {
			CommonLib.selectDojoListValue("sigList", "");
		} catch (Exception e) {
		}
	}

	public void SetPRN() {
		try {
			CommonLib.selectDojoListValue("prnIndList", "");
		} catch (Exception e) {
		}
	}

	public void SetSourceOfInformation() {
		try {
			CommonLib.selectDojoListValue("informationSourceList", "");
		} catch (Exception e) {
		}
	}

	public void ClickSubmitPopUp() {
		CommonLib.clickButton(By.id("buttonSubmit_label"));
	}

	public void ClickSubmitNewWindow() {
		CommonLib.clickButton(By.id("button.Submit_label"));
	}

	public void DuplicateMedPopup() {
		try {
			CommonLib.clickButton(By.id("dijit_form_Button_1_label"));
		} catch (Exception e) {
		}
	}

	public void SetMainFrame() {
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void VerifyAdd() throws InterruptedException {
		String NewMed = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div//table//tbody//tr//td[5]//a")).getText();
		if ((HashTableRepository.getHash("selectedMedication")).contains(NewMed))
			CustomReporter.MessageLogger("Add Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Add Functionality Of Medication Reconciliation is not working properly", status.Fail);

	}

	public void SetEditFrame() {
		WebElement MedEditFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation-entry.action?action=edit')][last()]"));
		CommonLib.setFrameFromCurrent(MedEditFrame);
	}

	public void SetComments() {
		CommonLib.setData(By.id("comments"), "data");
	}

	public void VerifyEdit() {
		String Comment = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div[2]//div//div//div//div//table//tbody//tr//td[8]")).getText();
		if (Comment.equals(""))
			Comment = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div[2]//div//div//div//div//table//tbody//tr//td[7]")).getText();
		if (Comment.equals("data"))
			CustomReporter.MessageLogger("Edit Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Edit Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void VerifyVerify() {
		String VerifyStatus = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div//table//tbody//tr//td[3]")).getText();
		if (VerifyStatus.equals("Y"))
			CustomReporter.MessageLogger("Verify Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Verify Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void verifyFilter() {
		List<WebElement> filterList = CommonLib.getElements(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[@id='dojox_grid__View_3']//div[contains(@class,'dojoxGridRow') and (contains(@style, 'line-through'))]"));
		int listSize = filterList.size();
		if (listSize > 0)
			CustomReporter.MessageLogger("Filter Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Filter Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public boolean verifyNoMeds() {
		boolean result = false;
		ClickAudit();
		WebElement MedEditFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation.action?action=viewAudit')][last()]"));
		CommonLib.setFrameFromCurrent(MedEditFrame);
		String auditText = CommonLib.getElement(By.xpath("//div[@id='auditGrid']//div[2]//div[@id='dojox_grid__View_3']//div//div//div//div//table/tbody/tr/td[2]")).getText();
		if (auditText.equals("** No Current Medications ** added and verified on Home Medications list.")) {
			CustomReporter.MessageLogger("NoMeds Functionality Of Medication Reconciliation is working properly", status.Pass);
			result = true;
		} else
			CustomReporter.MessageLogger("NoMeds Functionality Of Medication Reconciliation is not working properly", status.Fail);
		ClickClose();
		SetMainFrame();
		return result;
	}

	public void SetFilterFrame() {
		WebElement MedFilterFrame = CommonLib
				.getElement(By.xpath("//iframe[contains(@src,'javascript:'<html><head><script>if(\"loadFirebugConsole\" in window){window.loadFirebugConsole();}</script></head><body></body></html>'')][last()]"));
		CommonLib.setFrameFromCurrent(MedFilterFrame);
	}

	public void DeleteRadioButton() {
		CommonLib.clickButton(By.id("dijit_form_RadioButton_3"));
	}

	// public void RowCountBefore() {
	// int rowcountold =
	// CommonLib.getElements(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div[contains(@class,'dojoxGridRow')]")).size();
	// }
	//
	// public void RowCountAfter() {
	// int rowcountnew =
	// CommonLib.getElements(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div[contains(@class,'dojoxGridRow')]")).size();
	// }

	public void VerifyDelete() {
		// int rowcountold =
		// CommonLib.getElements(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div[contains(@class,'dojoxGridRow')]")).size();
		// ClickDelete();
		int rowcountnew = CommonLib.getElements(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[2]//div//div//div//div//div[contains(@class,'dojoxGridRow')]")).size();
		if (Integer.parseInt(HashTableRepository.getHash("oldCOunting")) > rowcountnew)
			CustomReporter.MessageLogger("Delete Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Delete Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void SetPrintFrame() {
		WebElement MedPrintFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'medication-reconciliation.action?action=print')][last()]"));
		CommonLib.setFrameFromCurrent(MedPrintFrame);
	}

	public void SetPharmacyFrame() {
		WebElement MedPrintFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'RxOrderGroup.do?action=viewrx')][last()]"));
		CommonLib.setFrameFromCurrent(MedPrintFrame);
	}

	public void PrintSubmit() {
		CommonLib.clickButton(By.xpath("//table[@id='buttonTable']//input[@id='functions']"));
	}

	public void VerifyRefresh() {
		int a = CommonLib.getElements(By.xpath("//div[@class='dojoxGridMasterView']//div//div//div//div//div//table//tbody//tr//td//div[@aria-checked='true']")).size();
		if (a == 0)
			CustomReporter.MessageLogger("Refresh Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Refresh Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void VerifyNoAdmission() {
		boolean a = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[@class='dojoxGridMasterView']//div//div//div//div//div[1]//table//tbody//tr[2]//td[2]//div//input[@id='cdaYN_N_0']"))
				.isSelected();
		if (a == true)
			CustomReporter.MessageLogger("Continue on Admission = No Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Continue on Admission = No Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void VerifyYesAdmission() {
		boolean a = CommonLib.getElement(By.xpath("//div[@id='homeMedsGridPane']//div[@id='homeMedsGrid']//div[@class='dojoxGridMasterView']//div//div//div//div//div[1]//table//tbody//tr[2]//td[1]//div//input[@id='cdaYN_Y_0']"))
				.isSelected();
		if (a == true)
			CustomReporter.MessageLogger("Refresh Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Refresh Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void PrintClose() {
		CommonLib.clickButton(By.xpath("//table[@id='buttonTable']//input[@name='button.Close']"));
	}

	public void verifyPrint() {

		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Medication Reconciliation is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Medication Reconciliation is not working properly", status.Fail);
	}

	public void Audit() {
		WebElement MedAuditFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/medication-reconciliation.action?action=viewAudit')][last()]"));
		CommonLib.setFrameFromCurrent(MedAuditFrame);
		// String audit =
		// CommonLib.getElement(By.xpath("//div[@id='pageContainer']//div//div//div[2]//div[2]//div//div//div//div//table//tbody//tr//td[2]")).getText();

		CommonLib.clickButton(By.id("close_label"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void checkVolumeValue(String id) throws InterruptedException {

		if (CommonLib.getElement(By.id(id)).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
			String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
			if (dojoValue.isEmpty()) {
				CommonLib.selectDojoDropDownByKeyDownNumber(id, 2);
			}
		}
	}

	public void placeMedicationOrder() {
		MenuNavigation.menuNav("Order Entry Plus");
		try {
			obj.checkAllergiesWarning();
			obj.checkOrderPhysicianValue("orderingPhysicianList");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			obj.checkCoSignListBox();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//table[@id='orderTypeList']/tbody/tr/td[2]/input"));
		CommonLib.clickButton(By.xpath("//table[@id='orderTypeList_menu']/tbody/tr/td[text()='Medication']"));
		CommonLib.selectRequiredDojoListValue("orderPriorityList", "Medication");
		CommonLib.clickButton(By.xpath("//span[@id='orderEntryTabs_tablist_orderMedicationTab']"));
		try {
			obj.addMedicationOrder();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			obj.completeOrder();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MenuNavigation.menuNav("MedicationReconciliation");
	}

	public boolean verifyInhouseAdd() {
		boolean result = false;
		ClickInhouseMedications();
		int orderListAfterAdd = CommonLib.getElements(By.xpath("//div[@id='inhouse']//div[@id='rxOrderManagerGridPane']//div[@id='rxOrderManagerGrid']//div[2]//div//div//div//div//div[contains(@class, 'dojoxGridRow')]")).size();
		String gridsize = HashTableRepository.getHash("inhouseOrderListBeforeAdd");
		int orderListBeforeAdd = Integer.parseInt(gridsize);
		if (orderListAfterAdd > orderListBeforeAdd) {
			CustomReporter.MessageLogger("Add Functionality Of Inhouse Medication is working properly", status.Pass);
			result = true;
		} else
			CustomReporter.MessageLogger("Add Functionality Of Inhouse Medication is not working properly", status.Fail);
		return result;
	}

	public boolean verifyDischargeAdd() {
		boolean result = false;
		ClickDischargeMedications();
		int orderListAfterAdd = CommonLib.getElements(By.xpath("//div[@id='discharge']//div[@id='dischargeMedsGridPane']//div[@id='dischargeMedsGrid']//div[2]//div//div//div//div//div[contains(@class, 'dojoxGridRow')]")).size();
		String gridsize = HashTableRepository.getHash("DischargeOrderListBeforeAdd");
		int orderListBeforeAdd = Integer.parseInt(gridsize);
		if (orderListAfterAdd > orderListBeforeAdd) {
			CustomReporter.MessageLogger("View from Level Of Care Functionality Of Discharge Medication is working properly", status.Pass);
			result = true;
		} else
			CustomReporter.MessageLogger("View from Level Of Care Functionality Of Discharge Medication is not working properly", status.Fail);
		return result;
	}

}
