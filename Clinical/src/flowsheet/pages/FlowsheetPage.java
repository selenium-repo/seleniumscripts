package flowsheet.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;

public class FlowsheetPage {

	public void clickFlowsheetTab() {
		CommonLib.clickButton(By.id("AssessmentsTabs_tablist_flowsheetTab"));
	}

	public void navigateToImmunizations() {
		MenuNavigation.menuNav("Immunizations");
	}

	public void clickImmunizationAdd() {
		CommonLib.clickButton(By.xpath("//span[@id='add_label']"));
	}

	public void clickImmunizationEdit() {
		CommonLib.clickButton(By.id("edit_label"));
	}

	public void clickImmunizationRemove() {
		CommonLib.clickButton(By.id("remove_label"));
	}

	public void clickImmunizationPrint() {
		CommonLib.clickButton(By.id("print_label"));
	}

	public void clickImmunizationSend() {
		CommonLib.clickButton(By.id("send_label"));
	}

	public void clickImmunizationQuery() {
		CommonLib.clickButton(By.id("query_label"));
	}

	public void clickImmunizationRefresh() {
		CommonLib.clickButton(By.id("refreshPage_label"));
	}

	public int getImmunizationRowCount() {
		int row_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='imGrid']/div[2]/div[2]/div/div/div/div[@class='dojoxGridRow']"), 4)) {
			row_count = CommonLib.getElements(By.xpath("//div[@id='imGrid']/div[2]/div[2]/div/div/div/div[@class='dojoxGridRow']")).size();
		}
		return row_count;
	}

	public String selectImmunizationValue() throws InterruptedException {
		String immunization_value = CommonLib.selectDojoDropDownByKeyDownNumber("IHCODEList", 3);
		return immunization_value;
	}

	public void setDialogFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
		}
	}

	public void clickImmunizationSubmit() {
		CommonLib.clickButton(By.id("button.submit_label"));
	}

	public void SetHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id(Config.props.getProperty("MainFrame")));
	}

	public void setClinicalRulesDialog() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@src, 'clinical-rules')][last()]"), 3)) {
			WebElement iframe = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'clinical-rules')][last()]"));
			CommonLib.setFrameFromCurrent(iframe);
			CommonLib.clickButton(By.xpath("//table/tbody/tr/td/span/span/span/span[3]"));
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.setFrame(By.id(Config.props.getProperty("MainFrame")));

		}
	}

	public String selectEditReason() throws InterruptedException {
		// return
		// CommonLib.selectDojoListByXpath("//div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']",
		// "editReasonList");
		return CommonLib.selectDojoDropDownByKeyDownNumber("editReasonList", 2);
		// ("//div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']",
		// "editReasonList");

	}

	public void selectFirstRow() {
		CommonLib.getElement(By.xpath("//div[@id='imGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td")).click();
	}

	public int getWindowHandleCount() {
		return CommonLib.GetDriver().getWindowHandles().size();
	}

	public boolean getSendAlertMsg() {
		boolean flag = false;
		try {
			Alert alert = CommonLib.GetDriver().switchTo().alert();
			String send_msg = alert.getText();
			HashTableRepository.setHash("send_msg", send_msg);
			if (send_msg.contains("immunizations have been sent"))
				alert.accept();
			flag = true;

		} catch (Exception e) {
		}
		return flag;
	}

	public String selectRemoveReason() throws InterruptedException {
		// return
		// CommonLib.selectDojoListByXpath("//div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']",
		// "editReasonList");
		return CommonLib.selectDojoDropDownByKeyDownNumber("removalReasonList", 2);
		// ("//div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']",
		// "editReasonList");

	}

	public boolean getQueryAlertMsg() {
		boolean flag = false;
		try {
			Alert alert = CommonLib.GetDriver().switchTo().alert();
			String query_msg = alert.getText();
			HashTableRepository.setHash("query_msg", query_msg);
			if (query_msg.contains("immunizations query has been sent"))
				alert.accept();
			flag = true;

		} catch (Exception e) {
		}
		return flag;
	}

	public boolean getCellFocusValue() {
		boolean result = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='imGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[contains(@class, 'dojoxGridCellFocus')]"), 3)) {
			result = true;
		}
		return result;
	}

	// public void clickFlowsheetTabName(String tab_name) {
	// CommonLib.clickButton(By.xpath("//span[@id='flowtabs_tablist_" + tab_name
	// + "']"));
	//
	// }
	public void clickFlowsheetTabName(String tab_name) {
		// tab_name = tab_name.toUpperCase();
		// System.out.println(tab_name + "tab name");
		// WebElement wb =
		// CommonLib.getElement(By.xpath("//span[@id='flowtabs_tablist_" +
		// tab_name + "']"));
		// WebElement web = CommonLib.getElement(By.xpath("//span[contains(@id,
		// 'flowtabs_tablist_') and contains(text(), '" + tab_name + "')]"));
		// web.click();
		// if
		// (CommonLib.isElementPresent(By.xpath("//span[contains(@id,'flowtabs_tablist_')
		// and contains(., '" + tab_name + "')]"), 8)) {
		WebElement wb = CommonLib.getElement(By.xpath("//span[contains(@id, 'flowtabs_tablist_') and  contains(., '" + tab_name + "')]"));
		String mouseover = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onmouseover');}}";
		String onclick = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('click', true, false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onlcick');}}";
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		js.executeScript(mouseover, wb);
		js.executeScript(onclick, wb);
		CustomReporter.MessageLogger("Selected the flowsheet tab :" + tab_name + " succesfully for processing", CustomReporter.status.Pass);
		// } else {
		// CustomReporter.MessageLogger("Failed to select the flowsheet tab :" +
		// tab_name + " for processing, please try again!",
		// CustomReporter.status.Fail);
		// }
	}

	public void clickFlowsheetsAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void clickFlowsheetsEdit() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public void clickFlowsheetsDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void clickFlowsheetsPrint() {
		CommonLib.clickButton(By.id("printItem_label"));
	}

	public void clickFlowsheetsRotate() {
		CommonLib.clickButton(By.id("rotateItem_label"));
	}

	public void clickFlowsheetsLineGraph() {
		CommonLib.clickButton(By.id("lineGraph_label"));
	}

	public void clickFlowsheetsBarGraph() {
		CommonLib.clickButton(By.id("barGraph_label"));
	}

	public boolean clickGrowthChart() {
		boolean check = false;
		String value = CommonLib.getElement(By.id("growthChart")).getAttribute("aria-disabled");
		if (value.equals("false")) {
			CommonLib.clickButton(By.id("growthChart_label"));
			check = true;
		}
		return check;
	}

	public void setGlucoseValue(int a, int b) {
		// int data = CommonLib.generateRandom(a, b);
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		CommonLib.getElement(By.id("_*BLDSUGAR")).clear();
		CommonLib.setData(By.id("_*BLDSUGAR"), data);
	}

	public void setGlucoseComment() {
		CommonLib.getElement(By.id("_*BGCOMMENT")).clear();
		CommonLib.setData(By.id("_*BGCOMMENT"), CommonLib.RandomText(1, 10));

	}

	public void clickFlowsheetSubmit() {
		if (CommonLib.isElementPresent(By.id("submit-flowsheet"), 2)) {
			CommonLib.clickButton(By.id("submit-flowsheet"));
		} else if (CommonLib.isElementPresent(By.id("button.Submit_label"), 1)) {
			CommonLib.clickButton(By.id("button.Submit_label"));
		}
	}

	public void clickAddedDescDate() {

		if (CommonLib.isElementPresent(By.xpath("//div[@id='flowTable']/div/div/div[2]/div/div/table/thead/tr/th[2]//input[@name='flowsheetSelect' and @type='checkbox']"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='flowTable']/div/div/div[2]/div/div/table/thead/tr/th[2]//input[@name='flowsheetSelect' and @type='checkbox']"));
		} else if (CommonLib.isElementPresent(By.xpath("//div[@id='flowTable']/div/div/div/div/table/thead/tr/th[2]/input[(@name='flowsheetSelect' and @type='checkbox')]"), 1)) {
			CommonLib.clickButton(By.xpath("//div[@id='flowTable']/div/div/div/div/table/thead/tr/th[2]/input[(@name='flowsheetSelect' and @type='checkbox')]"));
		}
		// WebElement wb =
		// CommonLib.getElement(By.xpath("//div[@id='flowTable']/div/div/div[2]/div/div/table/thead/tr/th[2]//input[@name='flowsheetSelect'
		// and @type='checkbox']"));
		// wb.click();

		// String mouseover = "if(document.createEvent){var
		// evObj=document.createEvent('MouseEvents');evObj.initEvent('mouseover',
		// true,
		// false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onmouseover');}}";
		// String onclick = "if(document.createEvent){var
		// evObj=document.createEvent('MouseEvents');evObj.initEvent('click',
		// true,
		// false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onlcick');}}";
		// JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		// js.executeScript(mouseover, wb);
		// js.executeScript(onclick, wb);

	}

	public void clickAddedHWDescDate() {
		// CommonLib.clickButton(By.xpath("//div[@id='flowTable']/div/div/div/div/table/thead/tr/th[2]/input[(@name='flowsheetSelect'
		// and @type='checkbox')]"));

		WebElement wb = CommonLib.getElement(By.xpath("//div[@id='flowTable']/div/div/div/div/table/thead/tr/th[2]/input[(@name='flowsheetSelect' and @type='checkbox')]"));
		String mouseover = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onmouseover');}}";
		String onclick = "if(document.createEvent){var evObj=document.createEvent('MouseEvents');evObj.initEvent('click', true, false);arguments[0].dispatchEvent(evObj);}else{if(document.createEventObject){arguments[0].fireEvent('onlcick');}}";
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		js.executeScript(mouseover, wb);
		js.executeScript(onclick, wb);
	}

	public int getRowCount() {
		int row_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='flowTable']/div/div/div[3]/div[2]/div/table/tbody/tr[@role='row']"), 2)) {
			row_count = CommonLib.getElements(By.xpath("//div[@id='flowTable']/div/div/div[3]/div[2]/div/table/tbody/tr[@role='row']")).size();
		} else if (CommonLib.isElementPresent(By.xpath("//div[@id='flowTable']/div/div/div[2]/table/tbody/tr[@role='row']"), 1)) {
			row_count = CommonLib.getElements(By.xpath("//div[@id='flowTable']/div/div/div[2]/table/tbody/tr[@role='row']")).size();
		}
		return row_count;
	}

	public String getEditReason() throws InterruptedException {
		String edit_text = CommonLib.setRandomDropDown(By.id("editReason"));
		return edit_text;
		// return CommonLib.selectDojoDropDownByKeyDownNumber("editReason", 3);
	}

	public void selectEyeOpening() {
		CommonLib.setRandomDropDown(By.id("_*EYEOPEN"));
	}

	public void selectVerbalResponse() {
		CommonLib.setRandomDropDown(By.id("_*VERBAL"));
	}

	public void selectMotorSkills() {
		CommonLib.setRandomDropDown(By.id("_*MOTOR"));
	}

	public void selectRightArmMovement() {
		CommonLib.setRandomDropDown(By.id("_*ARMRIGHT"));
	}

	public void selectRightLegMovement() {
		CommonLib.setRandomDropDown(By.id("_*LEGRIGHT"));
	}

	public void selectLeftLegMovement() {
		CommonLib.setRandomDropDown(By.id("_*LEGLEFT"));
	}

	public void checkGlucosecheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*BLDSUGAR' and @type='checkbox']"));

	}

	public void checkHeightInFtcheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*HEIGHT_FI' and @type='checkbox']"));

	}

	public String getBMIValue() {
		String bmi = CommonLib.getElement(By.xpath("//div[@id='flowTable']/div/div/div[2]/table/tbody/tr[10]/td[2]/div")).getText();
		return bmi;
	}

	public void checkGlucoseReqNum() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*BGREQNUM' and @type='checkbox']"));

	}

	public void checkWeightInKgCheckbox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*WEIGHT_KG' and @type='checkbox']"));

	}

	public void checkGlasgowComaScaleCheckbox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*GCSTOTAL' and @type='checkbox']"));

	}

	public void checkFlowsheetTemperatureCheckbox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*TEMP_PO' and @type='checkbox']"));

	}

	public void checkFlowsheetBloodPressureCheckbox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*CUFFBP' and @type='checkbox']"));

	}

	public void checkRightPupilSizecheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*PUPILSR' and @type='checkbox']"));

	}

	public void checkLeftPupilSizecheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_*PUPILSL' and @type='checkbox']"));

	}

	public boolean closeGraphs() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@class='jsglyph jsglyph-close']"), 5)) {
			CommonLib.clickButton(By.xpath("//span[@class='jsglyph jsglyph-close']"));
			check = true;
		}
		return check;
	}

	public String selectRemovalReason() throws InterruptedException {
		return CommonLib.selectDojoListValue("removalReasonList", "");
	}

	public void selectRightPupilResponse() throws InterruptedException {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.xpath("//select[@id='_*PUPILRR']"), 1)) {
			CommonLib.setRandomDropDown(By.xpath("//select[@id='_*PUPILRR']"));
		} else if (CommonLib.isElementPresent(By.id("_*PUPILRR"), 1)) {
			CommonLib.selectDojoDropDownByKeyDownNumber("_*PUPILRR", 2);
		}

	}

	public void selectRightPupilSize() throws InterruptedException {

		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.xpath("//select[@id='_*PUPILSR']"), 1)) {
			CommonLib.setRandomDropDown(By.xpath("//select[@id='_*PUPILSR']"));
		} else if (CommonLib.isElementPresent(By.id("_*PUPILSR"), 1)) {
			CommonLib.selectDojoDropDownByKeyDownNumber("_*PUPILSR", 2);
		}

	}

	public void selectLeftPupilResponse() throws InterruptedException {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.xpath("//select[@id='_*PUPILRL']"), 1)) {
			CommonLib.setRandomDropDown(By.xpath("//select[@id='_*PUPILRL']"));
		} else if (CommonLib.isElementPresent(By.id("_*PUPILRL"), 1)) {
			CommonLib.selectDojoDropDownByKeyDownNumber("_*PUPILRL", 1);
		}

	}

	public void selectLeftPupilSize() throws InterruptedException {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.xpath("//select[@id='_*PUPILSL']"), 1)) {
			CommonLib.setRandomDropDown(By.xpath("//select[@id='_*PUPILSL']"));
		} else if (CommonLib.isElementPresent(By.id("_*PUPILSL"), 1)) {
			CommonLib.selectDojoDropDownByKeyDownNumber("_*PUPILSL", 1);
		}

	}

	public void clearHeightWeightFields() {
		CommonLib.getElement(By.id("_*HEIGHT_IN")).clear();
		CommonLib.getElement(By.id("_*HEIGHT_CM")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_LB")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_KG")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_GM")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_OZ")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_LO_LB")).clear();
		CommonLib.getElement(By.id("_*WEIGHT_LO_LB_OZ")).clear();
		if (CommonLib.isElementPresent(By.id("_*HEAD_IN"), 1)) {
			CommonLib.getElement(By.id("_*HEAD_IN")).clear();
		}
		if (CommonLib.isElementPresent(By.id("_*HEAD_CM"), 1)) {
			CommonLib.getElement(By.id("_*HEAD_CM")).clear();
		}
		if (CommonLib.isElementPresent(By.id("_*WEIGHT"), 1)) {
			CommonLib.getElement(By.id("_*WEIGHT")).clear();
		}
		if (CommonLib.isElementPresent(By.id("_*HEIGHT"), 1)) {
			CommonLib.getElement(By.id("_*HEIGHT")).clear();
		}

	}

	public void setHeightValue(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		System.out.println(data);
		CommonLib.setData(By.id("_*HEIGHT_FI_FT"), data);
		// CommonLib.setData(By.id("_*HEIGHT_FI_IN"), data);
	}

	public void setWeightValue(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		System.out.println(data);
		CommonLib.setData(By.id("_*WEIGHT_KG"), data);

	}

	public void setHeightInCM(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		System.out.println(data);
		CommonLib.getElement(By.id("_*HEIGHT_IN")).clear();
		CommonLib.getElement(By.id("_*HEIGHT_CM")).clear();
		if (CommonLib.isElementPresent(By.id("_*WEIGHT"), 1)) {
			CommonLib.getElement(By.id("_*WEIGHT")).clear();
		}
		if (CommonLib.isElementPresent(By.id("_*HEIGHT"), 1)) {
			CommonLib.getElement(By.id("_*HEIGHT")).clear();
		}
		CommonLib.setData(By.id("_*HEIGHT_CM"), data);

	}

	public boolean checkForError() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@class='ui-dialog-title' and contains(.,'Entry Errors')]"), 2)) {
			CommonLib.clickButton(By.xpath("//div[contains(@aria-describedby, 'flowsheetErrorDialog')]/div[3]/div[contains(@class, 'ui-dialog-buttonset')]/button[@type='button' and contains(.,'ok')]"));
			check = true;
		}
		return check;
	}

	public void closeAlertMsg() {
		try {
			Alert alert = CommonLib.GetDriver().switchTo().alert();
			// String send_msg = alert.getText();
			alert.accept();

		} catch (Exception e) {
		}

	}

	public void closeAuditDialog() {
		CommonLib.clickButton(By.xpath("//div[@id='vitalSignsGraphToolbar']/span/span/span/span[3]"));
		CommonLib.staticWait(2);
		CommonLib.GetDriver().switchTo().parentFrame();
	}

	public boolean verifyRotateButton1() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//thead/tr/th[@class='sorting_disabled' and contains(.,'Description')]"), 2)) {
			clickFlowsheetsRotate();
			if (!CommonLib.isElementPresent(By.xpath("//thead/tr/th[@class='sorting_disabled' and contains(.,'Description')]"), 2)) {
				check = true;
				CustomReporter.MessageLogger("Rotate Button is working properly in the system, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, Rotate Button is not working properly in the system, which is not as expected", CustomReporter.status.Fail);
			}

		}
		return check;

	}

	public boolean verifyRotateButton2() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//thead/tr/th[@class='sorting_disabled' and contains(.,'Reading Date')]"), 3)) {
			clickFlowsheetsRotate();
			if (!CommonLib.isElementPresent(By.xpath("//thead/tr/th[@class='sorting_disabled' and contains(.,'Reading Date')]"), 2)) {
				check = true;
				CustomReporter.MessageLogger("Rotate Button is working properly in the system, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, Rotate Button is not working properly in the system, which is not as expected", CustomReporter.status.Fail);
			}
		}
		return check;
	}

	public void clickIntakeAndOutput() {
		if (CommonLib.isElementPresent(By.id("intakeOutput_label"), 2)) {
			CommonLib.clickButton(By.id("intakeOutput_label"));

		}
	}

	public void clickIntakeOutputAdd() {
		CommonLib.clickButton(By.id("add_label"));
	}

	public void selectIntakeRadioButton() {
		CommonLib.clickButton(By.xpath("//input[@id='itemType_Intake' and @type='radio']"));
	}

	public void selectLeftPupillarySizeRadioButton() {
		if (CommonLib.isElementPresent(By.xpath("//input[@id='_*PUPILSL_2' and @type='radio']"), 1)) {
			CommonLib.clickButton(By.xpath("//input[@id='_*PUPILSL_2' and @type='radio']"));
		}
	}

	public void selectForEditLeftPupillarySizeRadio() {
		if (CommonLib.isElementPresent(By.xpath("//input[@id='_*PUPILSL_6' and @type='radio']"), 1)) {
			CommonLib.clickButton(By.xpath("//input[@id='_*PUPILSL_6' and @type='radio']"));
		}

	}

	public void selectOutputRadioButton() {
		CommonLib.clickButton(By.xpath("//input[@id='itemType_Output' and @type='radio']"));
	}

	public void setFloatingFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@src, 'IntakeAndOutput')][last()]"), 3)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'IntakeAndOutput')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);

		}
	}

	public String setOralFluidIntakeData(int a, int b) {
		CommonLib.changeimplicitwait(1);
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		if (CommonLib.isElementPresent(By.id("Oral fluids_I"), 1)) {
			CommonLib.setData(By.id("Oral fluids_I"), data);
			setOralFluidComment();
		} else if (CommonLib.isElementPresent(By.id("oral fluids_I"), 1)) {
			CommonLib.setData(By.id("oral fluids_I"), data);
			setOralFluidComment();
		} else if (CommonLib.isElementPresent(By.id("Food_I"), 1)) {
			CommonLib.setData(By.id("Food_I"), data);
			setOralFluidComment();
		}

		return data;
	}

	public void setOralFluidComment() {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.id("Oral fluids_I_Comment"), 1)) {
			CommonLib.setData(By.id("Oral fluids_I_Comment"), CommonLib.RandomText(1, 10));
		} else if (CommonLib.isElementPresent(By.id("oral fluids_I_Comment"), 1)) {
			CommonLib.setData(By.id("oral fluids_I_Comment"), CommonLib.RandomText(1, 10));
		} else if (CommonLib.isElementPresent(By.id("Food_I_Comment"), 1)) {
			CommonLib.setData(By.id("Food_I_Comment"), CommonLib.RandomText(1, 10));
		}
	}

	public void setIVFluidIntakeData(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		CommonLib.setData(By.id("IV Fluids_I"), data);
		setIVFluidComment();
	}

	public void setIVFluidComment() {
		CommonLib.setData(By.id("IV Fluids_I_Comment"), CommonLib.RandomText(1, 10));
	}

	public void setBloodProductsIntakeData(int a, int b) {
		CommonLib.changeimplicitwait(1);
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		if (CommonLib.isElementPresent(By.id("Blood products_I"), 1)) {
			CommonLib.setData(By.id("Blood products_I"), data);
			setBloodProductComment();
		} else if (CommonLib.isElementPresent(By.id("lood products_I"), 1)) {
			CommonLib.setData(By.id("lood products_I"), data);
			setBloodProductComment();
		}
	}

	public void setBloodProductComment() {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.id("Blood products_I_Comment"), 1)) {
			CommonLib.setData(By.id("Blood products_I_Comment"), CommonLib.RandomText(1, 10));
		} else if (CommonLib.isElementPresent(By.id("lood products_I_Comment"), 1)) {
			CommonLib.setData(By.id("lood products_I_Comment"), CommonLib.RandomText(1, 10));
		}
	}

	public void setUrineOutputData(int a, int b) {
		CommonLib.changeimplicitwait(1);
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		if (CommonLib.isElementPresent(By.id("Urine_O"), 1)) {
			CommonLib.setData(By.id("Urine_O"), data);
			setUrineComment();
		} else if (CommonLib.isElementPresent(By.id("urine_O"), 1)) {
			CommonLib.setData(By.id("urine_O"), data);
			setUrineComment();
		}

	}

	public void setUrineComment() {

		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.id("Urine_O_Comment"), 1)) {
			CommonLib.setData(By.id("Urine_O_Comment"), CommonLib.RandomText(1, 10));
		} else if (CommonLib.isElementPresent(By.id("urine_O_Comment"), 1)) {
			CommonLib.setData(By.id("urine_O_Comment"), CommonLib.RandomText(1, 10));
		}
	}

	public void setEmesisOutputData(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		CommonLib.setData(By.id("Emesis_O"), data);
		setEmesisComment();
	}

	public void setEmesisComment() {
		CommonLib.setData(By.id("Emesis_O_Comment"), CommonLib.RandomText(1, 10));
	}

	public void setJPDrainOutputData(int a, int b) {
		CommonLib.changeimplicitwait(1);
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		if (CommonLib.isElementPresent(By.id("JP Drain 1_O"), 1)) {
			CommonLib.setData(By.id("JP Drain 1_O"), data);
			setJPDrainComment();
		} else if (CommonLib.isElementPresent(By.id("P Drain_O"), 1)) {
			CommonLib.setData(By.id("P Drain_O"), data);
			setJPDrainComment();

		}
	}

	public void setJPDrainComment() {
		CommonLib.changeimplicitwait(1);
		if (CommonLib.isElementPresent(By.id("JP Drain 1_O_Comment"), 1)) {
			CommonLib.setData(By.id("JP Drain 1_O_Comment"), CommonLib.RandomText(1, 10));
		} else if (CommonLib.isElementPresent(By.id("P Drain_O_Comment"), 1)) {
			CommonLib.setData(By.id("P Drain_O_Comment"), CommonLib.RandomText(1, 10));

		}

	}

	public String getIntakeTotalValue() {
		CommonLib.changeimplicitwait(2);
		String total = "";
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[7]"), 3)) {
			total = CommonLib.getText(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[7]"));
		} else if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[3]"), 1)) {
			total = CommonLib.getText(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[3]"));

		}
		return total;
	}

	public void clickIntakeAndOutputDetails() {
		if (CommonLib.isElementPresent(By.id("edit_label"), 2)) {
			CommonLib.clickButton(By.id("edit_label"));
		}
	}

	public void clickIntakeOutputEdit() {
		CommonLib.clickButton(By.xpath("//span[@id='edit_label']"));
	}

	public void clickIntakeOutputRemove() {
		CommonLib.clickButton(By.xpath("//span[@id='remove_label']"));
	}

	public void clickIntakeOutputClose() {
		CommonLib.clickButton(By.xpath("//span[@id='save_label']"));
	}

	public String getIntakeTotalDetailsValue() {
		String total = "";
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[3]"), 3)) {
			total = CommonLib.getText(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td[3]"));
		}
		return total;
	}

	public void clickDetailsRow() {
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dojoxGridRow') and @role='row']/table/tbody/tr/td"));
	}

	public String selectDetailsEditReason() throws InterruptedException {
		String data = CommonLib.selectDojoListValue("editReasonsList", "");
		return data;
	}

	public String selectDetailsRemoveReason() throws InterruptedException {
		String data = CommonLib.selectDojoListValue("removalReasonList", "");
		return data;

	}

	public void clickIntakeOutputPrint() {
		CommonLib.clickButton(By.xpath("//span[@id='print_label']"));
	}

	public void checkIntakeTotalcheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_intakeTotal' and @type='checkbox']"));

	}

	public void checkOutputTotalcheckBox() {
		CommonLib.clickButton(By.xpath("//input[@id='graph_outputTotal' and @type='checkbox']"));

	}

	public boolean clickIntakeOutputBarGraphClose() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@id='close_label']"), 2)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_label']"));
			check = true;
		}
		return check;
	}

	public void setBloodPressureValue(int a, int b) {
		int first = CommonLib.generateRandom(a, b);
		int second = first - 20;
		String data = first + "/" + second;
		CommonLib.setData(By.id("_*CUFFBP"), data);
	}

	public void setVitalsTemperature(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		CommonLib.getElement(By.id("_*TEMP_PO")).clear();
		CommonLib.getElement(By.id("_*VSO2RATE2")).clear();
		CommonLib.setData(By.id("_*TEMP_PO"), data);
	}

	public void setGlucoseReqSeqNumber(int a, int b) {
		String data = Integer.toString(CommonLib.generateRandom(a, b));
		CommonLib.getElement(By.id("_*BGREQNUM")).clear();
		CommonLib.getElement(By.id("_*BGSEQNUM")).clear();
		CommonLib.setData(By.id("_*BGREQNUM"), data);
		CommonLib.setData(By.id("_*BGSEQNUM"), data);

	}

}
