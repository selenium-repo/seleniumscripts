package therapy.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.CommonLib;
import common.Config;
import common.HashTableRepository;
import common.MenuNavigation;

public class PatientTherapyDocumentationPage {

	public void navigateToUserTable() {
		MenuNavigation.menuNav("UserTable");
	}

	public void navigateToRollTypes() {
		MenuNavigation.menuNav("RollTypes");
	}

	public void navigateToTherapyGroups() {
		MenuNavigation.menuNav("TherapyDocumentationGroups");
	}

	public void navigateToPatientTherapyDocumentation() {
		MenuNavigation.menuNav("PatientTherapyDocumentation");
	}

	public void setUserName() {
		CommonLib.setData(By.xpath("//div[@id='widget_searchValue']/div/input[@id='searchValue']"), Config.props.getProperty("user_name"));
	}

	public void therapyDocumentationAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void clickSearchButton() {
		CommonLib.clickButton(By.xpath("//div[contains(text(), 'Search')]"));
	}

	public String getUserIDValue() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td")).getText();
	}

	public void getRollTypeValue() {
		String role_type = CommonLib.getValue(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]"));
		HashTableRepository.setHash("role_type", role_type);
	}

	public void setCode() {
		// CommonLib.setData(By.id("searchValue"),
		// HashTableRepository.getHash("role_type"));
		CommonLib.setData(By.id("searchValue"), Config.props.getProperty("user_code"));
	}

	public void clickCode() {
		CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td"));
	}

	public void clickRollTypesEdit() {
		CommonLib.clickButton(By.id("editUser_label"));
	}

	public String getListDisplayedValue(String id) {
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
		return dojoValue;
	}

	public void storeTherapyAuthorizationList() {
		String therapy_group = getListDisplayedValue("therapydocAuthoziedList");
		HashTableRepository.setHash("therapy_group", therapy_group);
	}

	public void setGroupValue() {
		String therapy_group = HashTableRepository.getHash("therapy_group");
		String[] group_text = therapy_group.split(" ");
		String list_group = group_text[0] + " " + group_text[1];
		System.out.println(list_group);
		CommonLib.setData(By.id("searchValue"), list_group);

	}

	public void authorize_therapy() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
			CommonLib.clickButton(By.id("detailItem_label"));
			CommonLib.staticWait(15);
			String template_code = HashTableRepository.getHash("template_code");
			System.out.println(template_code);
			if (CommonLib.isElementPresent(By.xpath("//form[@id='detailForm']/table[@id='tableDetail']/tbody//tr/td/label[text()='" + template_code + "']"), 5)) {
				String assessment_id = CommonLib.getElement(By.xpath("//form[@id='detailForm']/table[@id='tableDetail']/tbody//tr/td/label[text()='" + template_code + "']")).getAttribute("id");
				String[] id = assessment_id.split("_");
				String sel_radio1 = "accessAuthorized_Y_" + id[1];
				String sel_radio2 = "updateAuthorized_Y_" + id[1];
				String sel_radio3 = "deleteAuthorized_Y_" + id[1];
				CommonLib.clickButton(By.id(sel_radio1));
				CommonLib.clickButton(By.id(sel_radio2));
				CommonLib.clickButton(By.id(sel_radio3));
				CommonLib.staticWait(2);
				CommonLib.clickButton(By.id("wizardSubmit_label"));
			}

		}
	}

	public void selectTherapyTemplate() throws InterruptedException {
		CommonLib.changeimplicitwait(2);
		// for (int count = 2; count < 18; count++) {
		// String selected_value =
		// CommonLib.selectDojoDropDownByKeyDownNumber("therapyTemplateList",
		// count);
		//
		// if
		// (selected_value.equals(HashTableRepository.getHash("template_code")))
		// {
		// break;
		// }
		// }

		// CommonLib.selectRequiredDojoListValue("therapyTemplateList",
		// "AAPLNLVM");

		CommonLib.selectRequiredDojoListValue("therapyTemplateList", HashTableRepository.getHash("template_code"));

	}

	public void clickTherapyTemplateSubmit() {
		CommonLib.clickButton(By.id("buttonSubmitDialog_label"));
	}

	public void expandOpenAll() {
		CommonLib.clickButton(By.xpath("//form[@id='therapyNoteDetail']/div[2]/div/div//span[contains(.,'Open All')]"));
	}

	public void selectTherapyStartTime() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("treatStartTime", 2);
	}

	public void selectTherapyStopTime() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("treatStopTime", 4);
	}

	public void setBloodPressure() {
		// int first = CommonLib.generateRandom(80, 99);
		// int second = first - 20;
		// String data = first + "/" + second;
		// CommonLib.setData(By.id("dp~tx~*ALINEBP~A"), data);
		CommonLib.setData(By.id("dp~tx~*ALINEBP_D~N"), getRandomIntString());

	}

	public void clickSaveButton() {
		// CommonLib.clickButton(By.xpath("//div[contains(.,'Save')]"));
		CommonLib.clickButton(By.xpath("//div[@id='mainButtons']/table/tbody/tr/td/table/tbody/tr/td/span/span/span/span[3]/div[contains(.,'Save')]"));
	}

	public int getTherapyNotesCount() {
		int therapy_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"), 5)) {
			therapy_count = CommonLib.getElements(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]")).size();
			System.out.println(therapy_count);
		}
		return therapy_count;
	}

	public void selectAddedNote() {
		// CommonLib.clickButton(By.xpath("(//span[contains(@class,
		// 'dijitTreeLabel') and contains(@role, 'treeitem'])[1]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"), 15)) {
			WebElement list = CommonLib.getElement(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"));
			// Iterator<WebElement> it = list.iterator();
			// while (it.hasNext()) {
			// it.next().click();
			// CommonLib.staticWait(5);
			// break;
			// }
			JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
			js.executeScript("arguments[0].click();", list);
		}
	}

	public void clickTherapyDocumentationEdit() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public void clickTherapyDocumentationPrint() {
		CommonLib.clickButton(By.id("print_label"));
	}

	public void selectEditBP() {
		CommonLib.setRandomDropDown(By.name("err~tx~*ALINEBP_D~N"));

	}

	public String getBloodPressureValue() {
		String value = CommonLib.getElement(By.id("dp~tx~*ALINEBP_D~N")).getAttribute("value");
		return value;
	}

	public void clickTherapyDocumentationFilter() {
		CommonLib.clickButton(By.id("filter_label"));
	}

	public void clickTherapyDocumentationFilterSubmit() {
		if (CommonLib.isElementPresent(By.id("submitNoteFilterBtn_label"), 3)) {
			CommonLib.clickButton(By.id("submitNoteFilterBtn_label"));
		}
	}

	public void clickTherapyDocumentationPrintSubmit() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 5)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
			if (CommonLib.isElementPresent(By.xpath("//div[@id='pageContainer']/div[2]/table/tbody/tr/td/span/span/span/span[3]/div[contains(text(),'Submit')]"), 3)) {
				CommonLib.clickButton(By.xpath("//div[@id='pageContainer']/div[2]/table/tbody/tr/td/span/span/span/span[3]/div[contains(text(),'Submit')]"));
			}
		}
	}

	public void clickTherapyDocumentationPrintContents() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 5)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
			CommonLib.clickButton(By.xpath("//div[@id='pageContainer']/div[2]/table/tbody/tr/td/span[2]/span/span/span[3]/div[contains(.,'Contents')]"));
		}
	}

	public void SetHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id("mainWorkFrame0"));
	}

	public void checkTherapyDocumentationFilterCheckBox() {
		if (CommonLib.isElementPresent(By.id("unsignedNotesChkBox"), 3)) {
			CommonLib.clickButton(By.id("unsignedNotesChkBox"));
		}
	}

	public int getWindowHandleCount() {
		return CommonLib.GetDriver().getWindowHandles().size();
	}

	public void clickTherapyDocumentationAudit() {
		CommonLib.clickButton(By.id("audit_label"));
	}

	public void switchToLastIframe() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public boolean closeAudit() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@id='close_label']"), 5)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_label']"));
			check = true;
		}
		return check;
	}

	public void setHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id("mainWorkFrame0"));
	}

	public void clickTherapyDocumentationFlowsheets() {
		CommonLib.clickButton(By.id("flowsheet_label"));
	}

	public void clickTherapyDocumentationCarePlans() {
		CommonLib.clickButton(By.id("careplan_label"));
	}

	public void clickTherapyDocumentationOrderManager() {
		CommonLib.clickButton(By.id("orderManager_label"));
	}

	public void clickTherapyDocumentationDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void clickTherapyDocumentationSign() {
		CommonLib.clickButton(By.xpath("//span[@id='sign_label']"));
	}

	public void clickTherapyDocumentationAmend() {
		CommonLib.clickButton(By.xpath("//span[@id='amend_label']"));

	}

	public boolean clickFlowsheetClose() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@id, 'nntFloatingPane')]/div/span[@class='dojoxFloatingCloseIcon']"), 8)) {
			CommonLib.clickButton(By.xpath("//div[contains(@id, 'nntFloatingPane')]/div/span[@class='dojoxFloatingCloseIcon']"));
			check = true;
		}
		return check;
	}

	public boolean clickCarePlanClose() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@class='iframeDialogCloseIcon']"), 4)) {
			CommonLib.clickButton(By.xpath("//span[@class='iframeDialogCloseIcon']"));
			check = true;
		}
		return check;
	}

	public void switchToFloatingFrame() {
		if (CommonLib.isElementPresent(By.xpath("(//iframe[contains(@id, 'floatingFrame')])[last()]"), 8)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("(//iframe[contains(@id, 'floatingFrame')])[last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public boolean clickOrderManagerClose() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@id='close_AllOrders_label']"), 4)) {
			CommonLib.clickButton(By.xpath("//span[@id='close_AllOrders_label']"));
			check = true;
		}
		return check;
	}

	public String setDeleteReason() {
		return CommonLib.setRandomDropDown(By.xpath("//select[@id='removalReason']"));
	}

	public void clickDeleteSubmit() {
		CommonLib.clickButton(By.id("deleteButtonSubmit_label"));
	}

	public void setIndividualMin() {
		CommonLib.setData(By.id("individual"), getRandomIntString());
	}

	public String getRandomIntString() {
		int data1 = CommonLib.generateRandom(10, 39);
		String data = Integer.toString(data1);
		return data;
	}

	public void setEvaluationMin() {
		CommonLib.setData(By.id("evaluation"), getRandomIntString());
	}

	public void setGroupMin() {
		CommonLib.setData(By.id("group"), getRandomIntString());
	}

	public void setConcurrentMin() {
		CommonLib.setData(By.id("concurrent"), getRandomIntString());
	}

	public void setCoTreatmentMin() {
		CommonLib.setData(By.id("coTreatment"), getRandomIntString());
	}

	public String getSigedUser() {
		return CommonLib.getElement(By.xpath("//input[@id='sign']")).getAttribute("value");
	}

	public String getAmendedUser() {
		return CommonLib.getElement(By.xpath("//input[@id='amendBy']")).getAttribute("value");
	}

	public void clickOKAmend() {
		CommonLib.clickButton(By.xpath("//div[contains(@id, 'dijit_Dialog')]/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
	}

	public String setAmendReason() {
		return CommonLib.setRandomDropDown(By.xpath("//select[@id='amendReasonList']"));
	}

	public void clickTherapySelectionProperties() {
		CommonLib.clickButton(By.xpath("//span[@class='dijitTitlePaneTextNode' and contains(.,'Selection Properties')]"));
	}

	public Iterator<WebElement> getRadioButtons(String str) {
		List<WebElement> list = CommonLib.getElements(By.xpath("//input[(@name='" + str + "') and (@role='radio')]"));
		int count = list.size();
		System.out.println(count);
		Iterator<WebElement> itr = list.iterator();
		return itr;
	}

	public void rightClickTreeViewEdit() {
		WebElement wb = CommonLib.getElement(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"));
		Actions action = new Actions(CommonLib.GetDriver()).contextClick(wb);
		action.build().perform();
		CommonLib.staticWait(3);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void rightClickTreeViewDelete() {
		WebElement wb = CommonLib.getElement(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"));
		Actions action = new Actions(CommonLib.GetDriver()).contextClick(wb);
		action.build().perform();
		CommonLib.staticWait(3);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	}

	public void selectDiscipline() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("discipline", 1);
	}

	public void selectTemplate(int val) throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("noteTabtemplate", val);
	}

	public void rightClickTreeViewSign() {
		WebElement wb = CommonLib.getElement(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"));
		Actions action = new Actions(CommonLib.GetDriver()).contextClick(wb);
		action.build().perform();
		CommonLib.staticWait(3);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	}

	public void clickAddTreatmentCharges() {
		CommonLib.clickButton(By.xpath("//span[@id='addTreatmentBtn_label']"));
	}

	public void checkTreatmentChargesList() {
		if (CommonLib.isElementPresent(By.xpath("//form[@id='icdForm']//div[@id='treatmentChargeGrid_rowSelector_0']"), 5)) {
			String check = CommonLib.getElement(By.xpath("//form[@id='icdForm']//div[@id='treatmentChargeGrid_rowSelector_0']")).getAttribute("aria-checked");
			if (check.equalsIgnoreCase("false")) {
				CommonLib.clickButton(By.xpath("//form[@id='icdForm']//div[@id='treatmentChargeGrid_rowSelector_0']"));
			}
		}
	}

	public void clickTreatmentChargesSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='treatmentChargeSubmit_label']"));
	}

	public void setTreatmentChargesMinute() {
		CommonLib.setData(By.xpath("//input[@id='minutes0']"), getRandomIntString());
	}

	public void setTreatmentChargesUnit() {
		CommonLib.setData(By.xpath("//input[@id='units0']"), getRandomIntString());
	}

	public void rightClickTreeViewAmend() {
		WebElement wb = CommonLib.getElement(By.xpath("//div[@id='notesTreeDiv']//span[contains(@class, 'dijitLeaf')]"));
		Actions action = new Actions(CommonLib.GetDriver()).contextClick(wb);
		action.build().perform();
		CommonLib.staticWait(3);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	}

	public void clickModifireIcon() {
		CommonLib.clickButton(By.xpath("//a[@id='search0']"));
	}

	public void selectThreatmentChargesModifier() {
		CommonLib.selectfrmDropdwn("availableModifiers", 2, "", "");
	}

	public void moveSelectedModifier() {
		CommonLib.clickButton(By.xpath("//form[@id='modifierForm']//img[contains(@alt, 'Add to current co-signers')]"));
	}

	public void clickModifierSubmit() {
		CommonLib.clickButton(By.xpath("//form[@id='modifierForm']//span[@id='submitModifier_label']"));
	}

	public void clickTherapyInfoAdd() {
		CommonLib.clickButton(By.xpath("//span[@id='addItem_label']"));
	}

	public void setCurrentDateTherapy() {
		CommonLib.setCurrentDate("effectiveDate");
	}

	public void selectTherapyDiscipline() throws InterruptedException {
		// CommonLib.selectDojoListByXpath("//input[@id='therapydisciplineList']",
		// "therapydisciplineList");

		CommonLib.selectDojoDropDownByKeyDownNumber("therapydisciplineList", 3);
	}

	public void setTherapyIntensity() {
		CommonLib.setData(By.id("intensity"), getRandomIntString());
	}

	public void setTherapyFrequency() {
		CommonLib.setData(By.id("frequency"), getRandomIntString());
	}

	public void setTherapyDuration() {
		CommonLib.setData(By.id("duration"), getRandomIntString());
	}

	public void setTherapyComments() {
		CommonLib.setData(By.id("comments"), CommonLib.RandomText(1, 7));
	}

	public void clickTherapyInfoSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='buttonSummarySubmit_label']"));
	}
}
