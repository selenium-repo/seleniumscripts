package patient_assessment_plus.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.HashTableRepository;

public class PatientAssessmentPlusPage {

	public void setScreenDefinitionData() throws IOException {

		CommonLib.setData(By.id("searchValue"), Config.props.getProperty("assmt_description"));
		clickAssessmentSearch();
		checkAssessmentData();

		// div[contains(@id,
		// 'dijit_layout_ContentPane')]/table/tbody/tr[2]/td[2]/span/span/span/span[3]

		// div[conatains(@class,'dojoxGridRow')]/table/tbody/tr/td

	}

	public void clickAssessmentSearch() {
		CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr[2]/td[2]/span/span/span/span[3]"));
	}

	public int checkAssessmentData() throws IOException {
		CommonLib.changeimplicitwait(6);
		int row_count = CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
		System.out.println(row_count);
		if (row_count == 1) {
			String status = CommonLib.getElement(By.xpath("//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[5]")).getText();
			String publish = CommonLib.getElement(By.xpath("//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[6]")).getText();
			if (status.contains("Active") && publish.contains("Yes")) {
				System.out.println("Its published already");

			} else {
				CommonLib.clickButton(By.xpath("//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
				CommonLib.clickButton(By.id("editItem_label"));
				CommonLib.clickButton(By.id("templateCodeStatus_A"));
				CommonLib.clickButton(By.id("templateCodePublish_Y"));
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td[4]/span/span/span/span[3]"));
				if (CommonLib.isElementPresent(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr/td"), 3)) {
					String publish_msg = CommonLib.getElement(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr/td")).getText();
					if (publish_msg.contains("Once a Template is Published, you cannot make any changes to it")) {
						CommonLib.clickButton(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
					}
				}

			}

		} else if (CommonLib.isElementPresent(By.xpath("(//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')])[" + row_count + "]"), 3)) {
			CommonLib.clickButton(By.xpath("(//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')])[" + row_count + "]"));
			CommonLib.clickButton(By.id("editItem_label"));
			CommonLib.clickButton(By.id("templateCodeStatus_A"));
			CommonLib.clickButton(By.id("templateCodePublish_Y"));
			CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td[4]/span/span/span/span[3]"));
			if (CommonLib.isElementPresent(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr/td"), 3)) {
				String publish_msg = CommonLib.getElement(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr/td")).getText();
				if (publish_msg.contains("Once a Template is Published, you cannot make any changes to it")) {
					CommonLib.clickButton(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				}
			}

		} else {
			CommonLib.clickButton(By.id("importItem_label"));
			CommonLib.clickButton(By.xpath("//div[@class='dijitDialogPaneContent']/form/table/tbody/tr/td[2]/input[@id='calcFile']"));
			CommonLib.staticWait(2);
			Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical\\Resources\\ImportFile\\import_file.exe");
			CommonLib.staticWait(5);
			CommonLib.clickButton(By.xpath("//div[@class='dijitDialogPaneContent']/form[@id='docImport']/table/tbody/tr[2]/td[2]/input[2]"));
			CommonLib.clickButton(By.id("buttonSubmitImport"));
			CommonLib.staticWait(2);
			setScreenDefinitionData();

		}
		return row_count;

	}

	public void checkGroupTable() {
		CommonLib.staticWait(15);
		CommonLib.setData(By.id("searchValue"), Config.props.getProperty("user_code"));
		clickRollTypeSearch();
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
			CommonLib.clickButton(By.id("editUser_label"));
			CommonLib.staticWait(7);
			String list_value = getListDisplayedValue("assessmentAuthorizationListList");
			System.out.println(list_value);
			HashTableRepository.setHash("authorization_list", list_value);
			CommonLib.staticWait(3);
		}

	}

	public void clickRollTypeSearch() {
		CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr[3]/td[2]/span/span/span/span[3]"));
	}

	public String getListDisplayedValue(String id) {
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
		return dojoValue;
	}

	public void checkAssessmentGroups() {
		CommonLib.staticWait(5);
		CommonLib.setData(By.id("searchValue"), HashTableRepository.getHash("authorization_list"));
		clickAssessmentSearch();
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"), 2)) {
			CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
			CommonLib.clickButton(By.id("detailItem_label"));
			CommonLib.staticWait(15);
			if (CommonLib.isElementPresent(By.xpath("//div[@id='assessmentTab']/div/div/form[@id='detailForm']/table[@id='tableDetail']/tbody//tr/td/label[text()='Automation Assessment']"), 5)) {
				String assessment_id = CommonLib.getElement(By.xpath("//div[@id='assessmentTab']/div/div/form[@id='detailForm']/table[@id='tableDetail']/tbody//tr/td/label[text()='Automation Assessment']")).getAttribute("id");
				String[] id = assessment_id.split("_");
				String sel_radio1 = "a_Y_" + id[1];
				String sel_radio2 = "u_Y_" + id[1];
				String sel_radio3 = "d_Y_" + id[1];
				CommonLib.clickButton(By.id(sel_radio1));
				CommonLib.clickButton(By.id(sel_radio2));
				CommonLib.clickButton(By.id(sel_radio3));
				CommonLib.staticWait(2);
				CommonLib.clickButton(By.id("wizardSubmit_label"));
			}

		}

	}

	public void selectAssessmentTab() {
		CommonLib.clickButton(By.id("AssessmentsTabs_tablist_assessmentTab"));

	}

	public String setBloodPressureValue() {
		int first = CommonLib.generateRandom(80, 110);
		int second = first - 20;
		String data = first + "/" + second;
		return data;
	}

	public static void setCharByCharData() {
		WebElement wb;
		String xpathstring = "//input[@id='assessmentsTemplateList' and contains(@class,'dijitReset dijitInputInner')]";
		wb = CommonLib.GetDriver().findElement(By.xpath(xpathstring));
		wb.clear();
		wb.click();
		String alpha = "Automation Assessment";
		int n = alpha.length();
		JavascriptExecutor jse = (JavascriptExecutor) CommonLib.GetDriver();
		for (int i = 0; i < n; i++) {
			char ch = alpha.charAt(i);
			System.out.println(ch);
			jse.executeScript("dijit.byId('assessmentsTemplateList').value='" + ch + "'");
			CommonLib.staticWait(3);
		}
		wb.sendKeys(Keys.ARROW_DOWN);
		wb.sendKeys(Keys.ARROW_DOWN);
		wb.sendKeys(Keys.ENTER);

	}

	public void setWalkingValue() {
		if (CommonLib.isElementPresent(By.xpath("(//img[@src='/OptimumClinicals/Images/list.gif'])[1]"), 2)) {
			CommonLib.clickButton(By.xpath("(//img[@src='/OptimumClinicals/Images/list.gif'])[1]"));
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
				WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
				CommonLib.setFrameFromCurrent(iFrameDialog);
				CommonLib.setRandomDropDown(By.id("valuesTable"));
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();
				CommonLib.setFrame(By.id("mainWorkFrame0"));

			}
		} else if (CommonLib.isElementPresent(By.xpath("(//img[@src='/OptimumClinicals/Images/Search16.gif'])[1]"), 2)) {
			CommonLib.clickButton(By.xpath("(//img[@src='/OptimumClinicals/Images/Search16.gif'])[1]"));
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
				WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
				CommonLib.setFrameFromCurrent(iFrameDialog);
				CommonLib.setRandomDropDown(By.xpath("//form[@id='dijit_form_Form_0']/table/tbody/tr[2]/td[2]/select[@id='valuesTable']"));
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();
				CommonLib.setFrame(By.id("mainWorkFrame0"));
			}

		}
	}

	public void setEyeOpeningValue() {
		if (CommonLib.isElementPresent(By.xpath("(//img[@src='/OptimumClinicals/Images/list.gif'])[2]"), 2)) {
			CommonLib.clickButton(By.xpath("(//img[@src='/OptimumClinicals/Images/list.gif'])[2]"));
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
				WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
				CommonLib.setFrameFromCurrent(iFrameDialog);
				CommonLib.setRandomDropDown(By.id("valuesTable"));
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();
				CommonLib.setFrame(By.id("mainWorkFrame0"));

			}
		} else if (CommonLib.isElementPresent(By.xpath("(//img[@src='/OptimumClinicals/Images/Search16.gif'])[2]"), 2)) {
			CommonLib.clickButton(By.xpath("(//img[@src='/OptimumClinicals/Images/Search16.gif'])[2]"));
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
				WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
				CommonLib.setFrameFromCurrent(iFrameDialog);
				CommonLib.setRandomDropDown(By.xpath("//form[@id='dijit_form_Form_0']/table/tbody/tr[2]/td[2]/select[@id='valuesTable']"));
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();
				CommonLib.setFrame(By.id("mainWorkFrame0"));
			}

		}

	}

	public void SetHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id("mainWorkFrame0"));
	}

	public int getAssessmentCount() {
		int assmnt_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='assessmentGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 3)) {
			assmnt_count = CommonLib.getElements(By.xpath("//div[@id='assessmentGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
		}
		return assmnt_count;
	}

	public void clickAddAssessment() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void selectAssessmentTemplate() throws InterruptedException {
		CommonLib.changeimplicitwait(2);
		for (int count = 2; count < 18; count++) {
			String selected_value = CommonLib.selectDojoDropDownByKeyDownNumber("assessmentsTemplateList", count);
			if (selected_value.equals(Config.props.getProperty("assmt_description"))) {
				break;
			}
		}

	}

	public void clickAssessmentTemplateSubmit() {
		CommonLib.clickButton(By.id("buttonSubmitDialog_label"));
	}

	public void fillAssessmentData() throws InterruptedException {
		if (CommonLib.isElementPresent(By.xpath("//form[@id='assessmentDetailForm']/div/table/tbody/tr/td"), 3)) {
			String assmnt_title = CommonLib.getElement(By.xpath("//form[@id='assessmentDetailForm']/div/table/tbody/tr/td")).getText();
			if (assmnt_title.contains(Config.props.getProperty("assmt_description"))) {
				String data = setBloodPressureValue();
				CommonLib.setData(By.id("dp~tx~*ALINEBP~A"), data);
				// String cardiac_output =
				// Integer.toString(CommonLib.generateRandom(20, 80));
				// CommonLib.setData(By.id("dp~tx~*CO~N"), cardiac_output);
				setWalkingValue();
				setEyeOpeningValue();
				CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				// if (CommonLib.isElementPresent(By.xpath("//div[contains(@id,
				// 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"),
				// 2)) {
				// CommonLib.clickButton(By.xpath("//div[contains(@id,
				// 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
				// }
				if (CommonLib.isElementPresent(By.xpath("//span[(@id='carePlanTriggeredItemsUpdateDialog_title') and contains(.,'Care Plan Triggered Items')]"), 8)) {
					// CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]"));
					CommonLib.clickIcon("carePlanTriggeredItemsCancelButton_label");
					// CommonLib.clickButton(By.id("carePlanTriggeredItemsCancelButton_label"));
				} else if (CommonLib.isElementPresent(By.id("carePlanTriggeredItemsCancelButton_label"), 8)) {
					CommonLib.clickIcon("carePlanTriggeredItemsCancelButton_label");

				}

			}
		}

	}

	public void selectAssessmentFirstRow() {
		try {
			CommonLib.clickButton(By.xpath("//div[@id='assessmentGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"));
			HashTableRepository.setHash("NoRow", null);
		} catch (Exception e) {
			HashTableRepository.setHash("NoRow", "NoRow");
		}

	}

	public int getWindowHandleCount() {
		return CommonLib.GetDriver().getWindowHandles().size();
	}

	public void clickEditAssessment() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public String selectAssessmentEditReason() {
		String text = CommonLib.setRandomDropDown(By.xpath("//select[@name='err~tx~*ALINEBP~A']"));
		return text;
	}

	public void setBloodPressureData() {
		String data = setBloodPressureValue();
		CommonLib.setData(By.id("dp~tx~*ALINEBP~A"), "");
		CommonLib.setData(By.id("dp~tx~*ALINEBP~A"), data);
	}

	public void clickCompleteAssessment() {
		CommonLib.clickButton(By.id("buttonComplete_label"));
	}

	public void clickAssessmentPrint() {
		CommonLib.clickButton(By.id("printItem_label"));

	}

	public void clickSubmitPtAssessmentPrintOptions() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
			CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_layout_ContentPane')]/table/tbody/tr/td/span/span/span/span[3]"));
		}

	}

	public void clickPtAssessmentPrintOptionsClose() {
		CommonLib.clickButton(By.id("closeButtonAssessmentPrint_label"));
	}

	public void checkCarePlanTrigger() throws InterruptedException {
		if (CommonLib.isElementPresent(By.xpath("//span[(@id='carePlanTriggeredItemsUpdateDialog_title') and contains(.,'Care Plan Triggered Items')]"), 8)) {
			// CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]"));
			CommonLib.clickIcon("carePlanTriggeredItemsCancelButton_label");
			// CommonLib.clickButton(By.id("carePlanTriggeredItemsCancelButton_label"));
		} else if (CommonLib.isElementPresent(By.id("carePlanTriggeredItemsCancelButton_label"), 8)) {
			CommonLib.clickIcon("carePlanTriggeredItemsCancelButton_label");

		}
	}

	public void clickAssessmentSubmit() {
		CommonLib.clickButton(By.id("buttonSubmitEdit_label"));
	}

	public void clickSelectionProperties() {
		CommonLib.clickButton(By.xpath("//span[(@class='dijitTitlePaneTextNode') and contains(.,'Selection Properties')]"));
	}

	public void selectEntireHistory() {
		CommonLib.clickButton(By.id("displayTypeAll"));
	}

	public String selectAssessmentType() {
		String text = CommonLib.setRandomDropDown(By.xpath("//select[@id='assessmentType']"));
		return text;
	}

	public void clickCompleteRSign() {
		CommonLib.clickButton(By.id("signItem_label"));

	}

	public String getAssessmentStatus() {
		return CommonLib.getElement(By.xpath("//div[@id='assessmentGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[5]")).getText();
	}

	public void clickAssessmentDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void selectAssessmentDeleteReason() throws InterruptedException {
		CommonLib.selectDojoListByXpath("//div[@id='widget_removalReason']/div[3]/input[@id='removalReason']", "removalReason");
	}

	public void clickAssessmentDeleteSubmit() {
		CommonLib.clickButton(By.id("deleteButtonSubmit_label"));
	}

}
