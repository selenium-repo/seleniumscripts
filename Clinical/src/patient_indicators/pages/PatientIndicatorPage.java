package patient_indicators.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;
import order_entry.OrderEntryPlus_OrderPanel;

public class PatientIndicatorPage {
	static int row = 0;

	OrderEntryPlus_OrderPanel obj = new OrderEntryPlus_OrderPanel();

	public void navigateToPatientIndicator() {
		MenuNavigation.menuNav("PatientIndicator");
	}

	public void clickPatientIndicatorAdd() {
		CommonLib.clickButton(By.xpath("//span[@id='addItem_label']"));
	}

	public void clickPatientIndicatorEdit() {
		CommonLib.clickButton(By.xpath("//span[@id='editItem_label']"));
	}

	public void clickPatientIndicatorCopy() {
		CommonLib.clickButton(By.xpath("//span[@id='copyItem_label']"));
	}

	public void clickPatientIndicatorDelete() {
		CommonLib.clickButton(By.xpath("//span[@id='deleteItem_label']"));
	}

	public void clickPatientIndicatorFilter() {
		CommonLib.clickButton(By.xpath("//span[@id='filter_label']"));
	}

	public String selectPatientIndicatorCode(int d) throws InterruptedException {
		// return CommonLib.selectDojoListValue("piCodeList", "");

		CommonLib.changeimplicitwait(2);
		String selected_value = "";
		for (int count = d; count < 13; count++) {
			selected_value = CommonLib.selectDojoDropDownByKeyDownNumber("piCodeList", count);
			if (selected_value.equals(Config.props.getProperty("pat_ind_code1")) || selected_value.equals(Config.props.getProperty("pat_ind_code2")) || selected_value.equals(Config.props.getProperty("pat_ind_code3"))) {
				selectPatientIndicatorCode(d);
			} else {
				break;
			}
		}
		return selected_value;

	}

	public void clickPatientIndicatorSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']"));
	}

	public String setPatientIndicatorComment() {
		return CommonLib.setData(By.id("comment"), CommonLib.RandomText(1, 10));
	}

	public int getPatientIndicatorRowCount() {
		CommonLib.changeimplicitwait(3);
		int row_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 3)) {
			row_count = CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
		}
		return row_count;
	}

	public void selectPatientIndicatorFirstRow() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"));
		}

	}

	public int getRowNumberOfPatientCode() {
		CommonLib.changeimplicitwait(3);
		String txt = "";
		int row_count = getPatientIndicatorRowCount();
		System.out.println(row_count);
		for (row = 1; row <= row_count; row++) {
			if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + row + "]/table/tbody/tr/td[2]"), 2)) {
				txt = CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + row + "]/table/tbody/tr/td[2]")).getText();
				System.out.println(HashTableRepository.getHash("code_txt"));
				if (HashTableRepository.getHash("code_txt").equals(txt)) {
					CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + row + "]"));
					String comment_val = CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + row + "]/table/tbody/tr/td[3]")).getText();
					HashTableRepository.setHash("comment_val", comment_val);
					break;
				}
			}
		}
		return row;

	}

	public void duplicateDialogHandling(int d) throws InterruptedException {
		CommonLib.changeimplicitwait(2);
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@id,'dijit_Dialog')]/div[2]/table/tbody/tr/td"), 3)) {
			clickDialog();
			selectPatientIndicatorCode(d);
			clickPatientIndicatorSubmit();
			duplicateDialogHandling(d);

		}
	}

	public String getCommentValue() {
		String comm_val = CommonLib.getElement(By.id("comment")).getAttribute("value");
		return comm_val;
	}

	public boolean checkDeletePopUp() {
		boolean flag = false;
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@id,'dijit_Dialog')]/div[2]/table/tbody/tr/td"), 6)) {
			String del_msg = CommonLib.getElement(By.xpath("//div[contains(@id,'dijit_Dialog')]/div[2]/table/tbody/tr/td")).getText();
			if (del_msg.contains("wish to delete this record")) {
				clickDialog();
				flag = true;
			}
		}
		return flag;
	}

	public void clickDialog() {
		// CommonLib.clickButton(By.id("dijit_form_Button_0_label"));
		CommonLib.changeimplicitwait(4);
		WebElement wb = CommonLib.getElement(By.xpath("//div[contains(@id,'dijit_Dialog')]/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		// Actions act = new Actions(CommonLib.GetDriver()).doubleClick(wb);
		// act.build().perform();
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();

		String mouseOverScript = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);}else if(document.createEventObject) {arguments[0].fireEvent('onmouseover');}";
		js.executeScript(mouseOverScript, wb);
		String onClickScript = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('click',true, false); arguments[0].dispatchEvent(evObj);}else if(document.createEventObject) {arguments[0].fireEvent('onclick');}";
		js.executeScript(onClickScript, wb);

	}

	public void navigateToPtChartTempLocation() {
		MenuNavigation.menuNav("PtChartTempLocation");
	}

	public void uncheckReturnedToNsCheckBox() {
		if (CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).isEnabled()) {
			System.out.println("Enabled");
			String check = CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).getAttribute("aria-checked");
			if (check.equals("true"))
				CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).click();
		}

	}

	public void selectTempLocation() throws InterruptedException {
		String temp_loc = CommonLib.selectDojoListByXpath("//form[@id='patientLocationForm']/table/tbody/tr[2]/td[2]/div[2]/div[3]/input[@id='tempLocCode']", "tempLocCode");
		HashTableRepository.setHash("temp_loc", temp_loc);
	}

	public void clickTempLocationSubmit() {
		CommonLib.clickButton(By.id("wizardSubmit_label"));
	}

	public boolean validateTempLocation() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td"), 3)) {
			String temp_txt = CommonLib.getElement(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td")).getText();
			if (temp_txt.contains(HashTableRepository.getHash("temp_loc"))) {
				CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[4]/td/span/span/span/span[3]"));
				check = true;

			}
		}
		return check;

	}

	public void checkReturnedToNsCheckBox() {
		if (CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).isEnabled()) {
			String check = CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).getAttribute("aria-checked");
			if (check.equals("false"))
				CommonLib.getElement(By.xpath("//input[@id='returnToNSUnit' and @type='checkbox']")).click();
		}

	}

	public void clickTempLocationAuditList() {
		CommonLib.clickButton(By.id("report_label"));
	}

	public boolean validateNsLocation() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td"), 3)) {
			String temp_txt = CommonLib.getElement(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td")).getText();
			if (temp_txt.contains("moved to N/S Unit")) {
				CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[4]/td/span/span/span/span[3]"));
				check = true;
			}
		}
		return check;
	}

	public boolean checkAuditData() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
			WebElement iframe = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iframe);
			String audit_txt = CommonLib.getElement(By.xpath("//div[@id='auditGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td")).getText();
			if (HashTableRepository.getHash("temp_loc").contains(audit_txt)) {
				CommonLib.clickButton(By.id("close_label"));
				check = true;
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();
			}
		}
		return check;
	}

	public boolean checkLabIcon() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//img[contains(@src, '/OptimumClinicals/Images/png/labResults.png')]"), 3)) {
			CommonLib.clickButton(By.xpath("//img[contains(@src, '/OptimumClinicals/Images/png/labResults.png')]"));
			CommonLib.staticWait(4);
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 8)) {
				WebElement ifram = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
				CommonLib.setFrameFromCurrent(ifram);
				if (CommonLib.isElementPresent(By.xpath("//div[@class='iframeDialogTitleBar']/span[contains(.,'Lab')]"), 5)) {
					check = true;
				}
			}
		}
		return check;
	}

	public void clickLabIconPrint() {
		CommonLib.clickButton(By.id("print_label"));

	}

	public int getWindowHandleCount() {
		return CommonLib.GetDriver().getWindowHandles().size();
	}

	public void SetHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id("mainWorkFrame0"));
	}

	public void closeLabTestSummaryScreen() {
		CommonLib.clickButton(By.id("close_label"));
	}

	public void navigateToPatientClinicalSummary() {
		MenuNavigation.menuNav("PatientClinicalSummary");
	}

	public boolean verifyPatientClinicalSummaryIcon(String iconname) {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//img[contains(@src, '/OptimumClinicals/Images/png/magnifier_zoom_in.png')]/parent::a[contains(@href,'" + iconname + "')]"), 3)) {
			CommonLib.clickButton(By.xpath("//img[contains(@src, '/OptimumClinicals/Images/png/magnifier_zoom_in.png')]/parent::a[contains(@href,'" + iconname + "')]"));
			// if
			// (CommonLib.isElementPresent(By.xpath("//div[@class='dojoxFloatingPaneTitle']/descendant::span[contains(.,'"
			// + iconname + "')]"), 5)) {
			check = true;
			// }
		} else {
			CustomReporter.MessageLogger("Patient Clinical Summaryt's '" + iconname + "' Icon is not available to check, please continue with other Icons", CustomReporter.status.Warning);

		}

		return check;

	}

	public boolean closeDialog() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@id,'floatingFrame')][last()]"), 9)) {
			WebElement ifram = CommonLib.getElement(By.xpath("//iframe[contains(@id,'floatingFrame')][last()]"));
			CommonLib.setFrameFromCurrent(ifram);
			CommonLib.staticWait(10);
			try {
				CommonLib.clickButton(By.xpath("//span[((@id='save_label') or (@id='closeItem_label') or (@id='button.close_label'))]"));
			} catch (Exception e) {
				CommonLib.clickButton(By.xpath("//span[@class='dojoxFloatingCloseIcon']"));
			}
			CommonLib.GetDriver().switchTo().parentFrame();
			SetHomeFrame();
			check = true;
		}
		return check;
	}

	public void clickClinicalSummaryOrderEntry() {
		CommonLib.clickButton(By.id("addNewOrderEntry_label"));
	}

	public boolean verifyDialogClose() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@id,'floatingFrame')][last()]"), 8)) {
			closeDialog();
			check = true;
		}
		return check;
	}

	public void clickClinicalSummaryGraphicDataSheet() {
		CommonLib.clickButton(By.id("addGDS_label"));
	}

	public boolean verifyCloseGraphSheet() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@id,'floatingFrame')][last()]"), 9)) {
			WebElement ifram = CommonLib.getElement(By.xpath("//iframe[contains(@id,'floatingFrame')][last()]"));
			CommonLib.setFrameFromCurrent(ifram);
			obj.checkAllergiesWarning();
			CommonLib.clickButton(By.xpath("//div[@id='orderEntryToolbar']/span/span/span/span[3]"));
			CommonLib.GetDriver().switchTo().parentFrame();
			SetHomeFrame();
			check = true;
		}
		return check;

	}

}
