package order_entry;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.Login;
import common.MenuNavigation;
import findpatient.FindPatient;

public class OrderEntryPlus_VerifyIcons extends OrderEntryPlus_OrderPanel {
	String active_order = "";

	public void verifyOrderEntryPlusIcons() throws InterruptedException, AWTException {
		MenuNavigation.menuNav("Order Entry Plus");
		verifyHomePanelIcon();
		verifyOrderManagerIcon();
		verifyAllergiesIcon();
		verifyDiagnosisIcon();
		verifyMedReconliation();
		verifyLevelOfCaresTrasfer();
		verifyLevelOfCaresDischarge(false);
		verifyDischargeSelectedItemsTree();
		CheckHeightWeight();
		unlockOrders();

	}

	public void unlockOrders() throws AWTException, InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		// CommonLib.changeimplicitwait(5);
		CommonLib.clickButton(By.xpath("//span[@id='orderRecLink']"));
		CommonLib.staticWait(18);
		CommonLib.clickButton(By.xpath("//span[@id='orderReconciliationTabs_tablist_transferTab']"));
		// CommonLib.staticWait(10);
		// List<WebElement> list =
		// CommonLib.getElements(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//div[@class='dijitCheckBox']/input[contains(@id,
		// 'dijit_form_CheckBox_') and @role='checkbox']"));
		// System.out.println(list.size());
		// System.out.println("the count is ");
		// Iterator<WebElement> itr = list.iterator();
		// while (itr.hasNext()) {
		// itr.next().click();
		// }
		// if
		// (CommonLib.isElementPresent(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')
		// and ((@value='Pharmacy')and(@value='Other Orders'))]"), 3)) {
		// CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')
		// and ((@value='Pharmacy')and(@value='Other Orders'))]"));
		// } else {
		// CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')
		// and ((@value='Pharmacy')or
		// (@value='Radiology')or(@value='Laboratory') or(@value='Other
		// Orders'))]"));
		// }

		CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput') and ((@value='Pharmacy')or (@value='Radiology'))]"));
		CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')and ((@value='Laboratory') or(@value='Other Orders'))]"));
		// CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')
		// and ((@value='Dietary') or (@value='Nursing Procedures'))]"));
		// CommonLib.clickButton(By.xpath("//div[@id='transferPane']//input[contains(@id,'dijit_form_CheckBox_')]"));
		CommonLib.clickButton(By.xpath("//span[@id='renewTransferItem_label']"));
		// String mainWinHander = CommonLib.GetDriver().getWindowHandle();
		Login objLogin = new Login();
		CommonLib.setFrameToDefault();
		logout();
		try {
			Alert alert = CommonLib.GetDriver().switchTo().alert();
			alert.accept();
			String alertText = alert.getText();
			CustomReporter.MessageLogger("Expected alert message displayed." + alertText, CustomReporter.status.Information);

		} catch (Exception e) {
		}

		objLogin.initlogin(Config.props.getProperty("username"), Config.props.getProperty("password"), false);

		FindPatient objFind = new FindPatient();
		objFind.newPatientSearch();
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		// OrderEntry obj = new OrderEntry();
		// try {
		// obj.orderscreen();
		// } catch (NumberFormatException | InterruptedException e) {
		// }
		CommonLib.clickButton(By.xpath("//span[@id='unlockOrders_label']"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='reminderDialog']//span[contains(@id,'dijit_form_Button_') and text()='Yes']"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']//span[contains(@id,'dijit_form_Button_') and text()='Yes']"));
			CustomReporter.MessageLogger("The Unlock Orders Icon of Order Entry Plus is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("The Unlock Orders Icon of Order Entry Plus is not working properly", CustomReporter.status.Fail);
		}

	}

	public void logout() throws AWTException {
		if (Config.props.getProperty("SystemUnderTest").equals("NetSolution")) {
			boolean flag = false;
			CommonLib.setFrame("left");
			int rowcount = CommonLib.getElements(By.xpath("//div[@id='TimePanel']/table/tbody//tr")).size();
			for (int i = 0; i < rowcount; i++) {
				String x = CommonLib.getElement(By.xpath("//div[@id='TimePanel']/table/tbody//tr[" + (i + 1) + "]/following::a[1]")).getText();
				if (x.equals("Sign Out")) {

					CommonLib.clickButton(By.id("grdMenu_lnkMenuItem_" + (i + 1)));
					flag = true;

					CustomReporter.MessageLogger("Application signed out successfully", CustomReporter.status.Pass);
					break;
				}

			}
			if (flag == false) {
				CustomReporter.MessageLogger("Application did not signed out successfully", CustomReporter.status.Fail);
			}
		} else {
			CommonLib.changeimplicitwait(1);
			// CommonLib.GetDriver().navigate().refresh();
			try {
				Alert alert = CommonLib.GetDriver().switchTo().alert();
				alert.accept();
				String alertText = alert.getText();
				CustomReporter.MessageLogger("Expected alert message displayed." + alertText, CustomReporter.status.Information);

			} catch (Exception e) {
			}
			String parentWindowHandler = CommonLib.GetDriver().getWindowHandle();
			CommonLib.clickButton(By.xpath("//div[@id='OptimumMenu']/table[@id='OptimumMenuTable']/tbody/tr/td/table/tbody/tr/td[contains(text(),'Logoff')]"));
			String subWindowHandler = null;
			Set<String> handles = CommonLib.GetDriver().getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				// CommonLib.staticWait(1);
				subWindowHandler = iterator.next();

			}
			CommonLib.GetDriver().switchTo().window(subWindowHandler);
			JavascriptExecutor myexecutor = ((JavascriptExecutor) CommonLib.GetDriver());
			// WebElement wb =
			// CommonLib.getElement(By.xpath("//table[@id='Table2']/tbody/tr/td[1]/input"));
			if (CommonLib.isElementPresent(By.xpath("//div[@id='dlgButtons']/table[@id='Table2']/tbody/tr/td/input[@id='button.Submit']"), 5)) {
				WebElement wb = CommonLib.getElement(By.xpath("//div[@id='dlgButtons']/table[@id='Table2']/tbody/tr/td/input[@id='button.Submit']"));
				myexecutor.executeScript("arguments[0].click()", wb);
			}
			CommonLib.GetDriver().switchTo().window(parentWindowHandler);

		}
	}

	public String getDropDownValue(String id) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
		CustomReporter.MessageLogger("For " + id + " dropdown, selected value is : " + dojoValue, CustomReporter.status.Information);
		return dojoValue;

	}

	public void verifyHomePanelIcon() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='homePanel_label']"));
		CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderPanelTab"));
		String default_list_value = getDropDownValue("orderPanelsList");
		if (default_list_value != null) {
			CustomReporter.MessageLogger("The default selection of Order Panel in the home screen is :" + default_list_value + "", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("No values has been set to the home screen's Order Panel, please try again!", status.Fail);
		}
	}

	public void verifyOrderManagerIcon() {
		CommonLib.clickButton(By.id("orderManagerLink_label"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		if (CommonLib.isElementPresent(By.xpath("//span[@class='iframeDialogTitle' and contains(text(), 'Patient Order Worklist')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_AllOrders_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			CustomReporter.MessageLogger("The Order Manager Icon of Order Entry Plus is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, the Order Manager Icon of Order Entry Plus is not working properly, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyAllergiesIcon() {

		CommonLib.clickButton(By.id("allergiesLink_label"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		if (CommonLib.isElementPresent(By.xpath("//span[@class='iframeDialogTitle' and contains(text(), 'Allergies')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='save_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			CustomReporter.MessageLogger("The Allergies Icon of Order Entry Plus is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, the Allergies Icon of Order Entry Plus is not working properly, please try again!", CustomReporter.status.Fail);
		}
	}

	public void verifyDiagnosisIcon() {

		CommonLib.clickButton(By.id("diagnosisLink_label"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		if (CommonLib.isElementPresent(By.xpath("//span[@class='iframeDialogTitle' and contains(text(), 'Patient Diagnoses')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			CustomReporter.MessageLogger("The Diagnosis Icon of Order Entry Plus is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, the Diagnosis Icon of Order Entry Plus is not working properly, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyMedReconliation() {
		CommonLib.clickButton(By.id("medRecLink_label"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		if (CommonLib.isElementPresent(By.xpath("//span[@class='iframeDialogTitle' and contains(text(), 'Medication Reconciliation')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			CustomReporter.MessageLogger("The Med Reconliation Icon of Order Entry Plus is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, the Med Reconliation Icon of Order Entry Plus is not working properly, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyLevelOfCaresTrasfer() throws InterruptedException {

		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.clickButton(By.id("orderRecLink_label"));
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.id("orderReconciliationTabs_tablist_transferTab"));
		int b_reorder = countOrderBasketTree();
		CommonLib.staticWait(18);
		selectOneTreeItem("transfer");
		CommonLib.clickButton(By.id("renewTransferItem_label"));
		processActiveOrder();
		int a_reorder = countOrderBasketTree();
		if (a_reorder > b_reorder) {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Reorder Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Reorder Icon is not working properly", CustomReporter.status.Fail);
		}
		selectOneTreeItem("transfer");
		CommonLib.clickButton(By.id("changeTransferItem_label"));
		processActiveOrder();
		int a_trasfer = countOrderBasketTree();
		if (a_trasfer > a_reorder) {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Change Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Change Icon is not working properly", CustomReporter.status.Fail);
		}

		CommonLib.clickButton(By.xpath("//div[@id='transferActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput') and ((@value='Pharmacy')or (@value='Radiology')or(@value='Laboratory') or(@value='Other Orders'))]"));
		CommonLib.clickButton(By.id("discontinueTrasnferItem_label"));
		int a_discountinue = countOrderBasketTree();
		if (a_discountinue > a_trasfer) {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Discontinue Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Trasfer Tab's Discontinue Icon is not working properly", CustomReporter.status.Fail);
		}

	}

	public void verifyLevelOfCaresDischarge(boolean medRecLOC) throws InterruptedException {
		// boolean element = false;
		MenuNavigation.menuNav("Order Entry Plus");
		checkAllergiesWarning();
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.clickButton(By.id("orderRecLink_label"));
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.id("orderReconciliationTabs_tablist_dischargeTab"));
		CommonLib.staticWait(3);
		int b_new = countDischargeBasketTree();
		CommonLib.clickButton(By.id("changeDischargeItem_label"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		CommonLib.selectDojoDropDownByKeyDownNumber("medicationIDList", 3);
		CommonLib.selectDojoListByXpath("//div[contains(@class,'dijitReset dijitInputField')]/input[@id='medRecDoseVolumeUnit' and @type='text']", "medRecDoseVolumeUnit");
		CommonLib.selectDojoDropDownByKeyDownNumber("sigList", 3);
		CommonLib.clickButton(By.id("button.Submit_label"));
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		int a_new = countDischargeBasketTree();
		if (a_new > b_new) {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's New Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's New Icon  is not working properly", CustomReporter.status.Fail);
		}
		boolean continueItem = selectOneTreeItem("discharge");
		CommonLib.clickButton(By.id("discontinueDischargeItem_label"));
		CommonLib.staticWait(3);
		checkClinicalWarning();
		checkLOCReminderDialog();
		int a_continue = countDischargeBasketTree();
		if (continueItem != false) {
			if (a_continue > a_new) {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's Continue Icon is working properly", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's Continue Icon is not working properly", CustomReporter.status.Fail);
			}
		} else {
			CustomReporter.MessageLogger("There are no orders in the Active Order Tree", CustomReporter.status.Pass);
		}
		if (medRecLOC != true) {
			selectOneTreeItem("discharge");
			CommonLib.clickButton(By.id("renewDischargeItem_label"));
			CommonLib.staticWait(3);
			int a_discontinue = countDischargeBasketTree();
			if (a_discontinue > a_continue) {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's	Discontinue Icon is working properly", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's Discontinue Icon is not working properly", CustomReporter.status.Fail);
			}
			CommonLib.clickButton(By.id("print_label"));
			verifyPrint();
		}
	}

	public void verifyDischargeSelectedItemsTree() throws InterruptedException {

		int b_edit = countDischargeBasketTree();
		selectOneSelectedItemsTree();
		CommonLib.clickButton(By.id("editDischargeBasketItem"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		CommonLib.setData(By.xpath("//textarea[@id='comments' and contains(@class,'TextArea')]"), Config.props.getProperty("order_type"));
		CommonLib.clickButton(By.id("button.Submit_label"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 2)) {
			String err_msg = CommonLib.getElement(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td")).getText();
			if (err_msg.contains("valid drug")) {
				CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				CommonLib.selectDojoDropDownByKeyDownNumber("medicationIDList", 3);
				CommonLib.selectDojoListByXpath("//div[contains(@class,'dijitReset dijitInputField')]/input[@id='medRecDoseVolumeUnit' and @type='text']", "medRecDoseVolumeUnit");
				CommonLib.clickButton(By.id("button.Submit_label"));
				CommonLib.staticWait(2);
			}
		}
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		int a_edit = countDischargeBasketTree();
		if (b_edit == a_edit) {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's	Edit Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's	Edit Icon is not working properly", CustomReporter.status.Fail);
		}
		int b_delete = countDischargeBasketTree();
		selectOneSelectedItemsTree();
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.id("deleteDischargeBasketItem"));
		int a_delete = countDischargeBasketTree();
		if (a_delete < b_delete) {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's	Delete Icon is working properly", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's	Delete Icon is not working properly", CustomReporter.status.Fail);
		}
		CommonLib.clickButton(By.id("printDischarge_label"));
		verifyPrint();
		CommonLib.staticWait(2);
		while (chkComplete()) {
			CommonLib.clickButton(By.xpath("//div[@id='dischargeActiveOrdersTree']//input[contains(@class,'dijitCheckBoxInput') and ((@value='Active Medications')or (@value='Home Medications'))]"));
			CommonLib.clickButton(By.id("renewDischargeItem_label"));
			CommonLib.staticWait(3);
		}
		checkComplete();

	}

	public boolean chkComplete() {
		String value = CommonLib.getElement(By.id("completeOrdersDischarge")).getAttribute("aria-disabled");
		// System.out.println(value);
		boolean check = Boolean.parseBoolean(value);
		return check;
	}

	public void checkComplete() {
		CommonLib.clickButton(By.id("completeOrdersDischarge"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr/td"), 3)) {
			String msg = CommonLib.getElement(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr/td")).getText();
			if (msg.contentEquals("Order Reconciliation is complete.")) {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's	Complete Icon is working properly", CustomReporter.status.Pass);
				CommonLib.clickButton(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
			} else {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's	Complete Icon is not working properly", CustomReporter.status.Fail);
			}
		}
	}

	public void verifyPrint() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[starts-with(@src,'medication-reconciliation')][last()]"), 5)) {
			CommonLib.staticWait(3);
			WebElement iFrameOrderEntry1 = CommonLib.getElement(By.xpath("//iframe[starts-with(@src,'medication-reconciliation')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry1);
			CommonLib.clickButton(By.xpath("//input[@name='button.Submit' and @value='Submit']"));
			CommonLib.staticWait(22);
			setWindowHandleToLast();
			CommonLib.GetDriver().close();
			CommonLib.staticWait(4);
			setWindowHandleToLast();
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			WebElement iFrameOrderEntry2 = CommonLib.getElement(By.xpath("//iframe[starts-with(@src,'medication-reconciliation')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry2);
			CommonLib.staticWait(5);
			if (CommonLib.isElementPresent(By.xpath("//input[@name='button.Close' and @value='Close']"), 2)) {
				CommonLib.clickButton(By.xpath("//input[@name='button.Close' and @value='Close']"));
				CustomReporter.MessageLogger("Level of Care Discharge Tab's Print Icon is working properly", CustomReporter.status.Pass);
				CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			} else {
				CustomReporter.MessageLogger("Level of Care Discharge Tab's Print Icon is not working properly", CustomReporter.status.Fail);
			}

		} else {
			CustomReporter.MessageLogger("Level of Care Discharge Tab's Print Icon is not working properly", CustomReporter.status.Fail);
		}
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));

	}

	public void setWindowHandleToLast() {
		Set<String> handles = CommonLib.GetDriver().getWindowHandles();
		for (String handle : handles) {
			CommonLib.GetDriver().switchTo().window(handle);
		}
	}

	public boolean selectOneTreeItem(String tab) {
		boolean result = false;
		WebElement wb2 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='" + tab + "ActiveOrdersTree']"));
		List<WebElement> tree_list2 = wb2.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		int basket_count_after = tree_list2.size();
		// System.out.println(basket_count_after);
		if (basket_count_after > 0) {
			int i = 0;
			for (WebElement web_ele : tree_list2) {
				i++;
				if (i == 3) {
					// System.out.println(web_ele.getText());
					active_order = web_ele.getText();
					HashTableRepository.setHash("active_order", web_ele.getText());
					break;
				}

			}
			if (i >= 3) {
				String[] act = active_order.split(" ");
				String new_active_order = act[0] + " " + act[1];
				CommonLib.clickButton(By.xpath("//div[@id='" + tab + "ActiveOrdersTreeContainer']//input[contains(@class,'dijitCheckBoxInput')and contains(@value,'" + new_active_order + "')][1]"));
				result = true;
			}

		}
		return result;
	}

	public int countOrderBasketTree() {
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTrasnferPane']"));
		int count = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']")).size();
		return count;

	}

	public void processActiveOrder() throws InterruptedException {
		CommonLib.changeimplicitwait(12);
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@src,'order-entry')][last()]"), 1)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			errorHandling();
			checkClinicalWarning();
		} else {

			checkClinicalWarning();
		}
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public int countDischargeBasketTree() {
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='dischargeBasketTree']"));
		int count = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']")).size();
		return count;

	}

	public void selectOneSelectedItemsTree() {
		CommonLib.staticWait(3);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		WebElement wb2 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='dischargeBasketTree']"));
		CommonLib.staticWait(2);
		List<WebElement> tree_list2 = wb2.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		int basket_count_after = tree_list2.size();
		// System.out.println(basket_count_after);
		if (basket_count_after > 0) {
			int i = 0;
			for (WebElement web_ele : tree_list2) {
				if (i > 2) {
					/// System.out.println(web_ele.getText());
					active_order = web_ele.getText();
					HashTableRepository.setHash("active_order", web_ele.getText());
					break;
				}
				i++;
			}
			String[] act = active_order.split(" ");
			String new_active_order = act[0] + " " + act[1];
			CommonLib.clickButton(By.xpath("//div[@id='dischargeBasketTree']//input[contains(@class,'dijitCheckBoxInput')and contains(@value,'" + new_active_order + "')][1]"));
		}
	}

	public void CheckHeightWeight() throws NumberFormatException, InterruptedException {

		String a = CommonLib.getText(By.xpath(
				"//div[@class='dojoxGridMasterView']/div[@class='dojoxGridView']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[@role='presentation']/div[@class='dojoxGridRow']/table[@class='dojoxGridRowTable']/tbody/tr/td[1]"));
		// System.out.println("ROWText: " + a);
		CommonLib.clickButton(By.xpath("//div[@id='orderEntryTabsContainer']/div[contains(@id,'dijit_layout_ContentPane')]/table/tbody/tr/th[@class='sectionHeadingTitle']/a[@href='javascript:onHeightWeight();']"));
		WebElement iFrameHeightWeight = CommonLib.getElement(By.xpath("//iframe[contains(@id,'floatingFrame')and contains(@src,'patient-assessment.action')]"));
		CommonLib.setFrameFromCurrent(iFrameHeightWeight);
		// String b = CommonLib.getText(By.xpath(
		// "//div[@id='FSGridContainer']/div[@id='flowTable']/div[@id='fsGrid_wrapper']/div[@class='DTFC_ScrollWrapper']/div[@class='dataTables_scroll']/div[@class='dataTables_scrollBody']/table[@id='fsGrid']/tbody/tr[1]/td[2]"));
		// String b =
		// CommonLib.getText(By.xpath("//div[@id='flowsheetTab']/div/div/div/div[2]/div/div/div/div[2]/table/tbody/tr/td[2]/div"));
		// System.out.println("ROWText: " + b);
		CommonLib.clickButton(By.id("closeItem_label"));
		if (!(a.contains("error"))) {
			CustomReporter.MessageLogger("Proper Height displayed", status.Pass);
		} else {
			CustomReporter.MessageLogger("System error displayed", status.Fail);
		}
		CommonLib.staticWait(3);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));

	}

	public void checkLOCReminderDialog() {
		CommonLib.staticWait(2);
		if (CommonLib.getElements(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[4]/td/span//span[@id='dijit_form_Button_8_label']")).size() > 0) {
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[4]/td/span//span[@id='dijit_form_Button_8_label']"));
		}
	}
}
