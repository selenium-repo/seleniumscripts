package therapy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.HashTableRepository;
import common.MenuNavigation;
import pharmacy.testcases.PatientSearchTestCases;

public class TherapyTemplateMasterPage extends PatientSearchTestCases {

	public void navigateToTherapyTemplateMaster() {
		MenuNavigation.menuNav("TherapyTemplateMaster");
	}

	public void therapyTemplateMasterAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void therapyTemplateMasterEdit() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public void therapyTemplateMasterCopy() {
		CommonLib.clickButton(By.id("copyItem_label"));
	}

	public void therapyTemplateMasterDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void therapyTemplateMasterDetails() {
		CommonLib.clickButton(By.id("detailItem_label"));
	}

	public void therapyTemplateMasterActivate() {
		CommonLib.clickButton(By.id("activateItem_label"));
	}

	public void therapyTemplateMasterInactivate() {
		CommonLib.clickButton(By.id("inActivateItem_label"));
	}

	public void therapyTemplateMasterAudit() {
		CommonLib.clickButton(By.id("auditItem_label"));
	}

	public void therapyTemplateMasterFilter() {
		CommonLib.clickButton(By.id("filter_label"));
	}

	public void setTemplateCode() {
		String text = CommonLib.RandomText(1, 6);
		String new_txt = "AA" + text;
		System.out.println(new_txt);
		CommonLib.getElement(By.xpath("//form[@id='detailForm1']//input[@id='code']")).sendKeys(new_txt);
		HashTableRepository.setHash("template_code", new_txt.toUpperCase());
	}

	public void setTemplateDescription() {
		CommonLib.setData(By.xpath("//form[@id='detailForm1']//input[@id='description']"), HashTableRepository.getHash("template_code"));
	}

	public String setTemplateDescriptionEdit() {
		String text = CommonLib.RandomText(1, 6);
		String new_edit_txt = "TC" + text;
		CommonLib.setData(By.xpath("//form[@id='detailForm1']//input[@id='description']"), new_edit_txt);
		HashTableRepository.setHash("edit_template_code", new_edit_txt);
		return new_edit_txt;
	}

	public void selectTherapyDiscipline() throws InterruptedException {
		CommonLib.selectDojoListByXpath("//form[@id='detailForm1']//input[@id='therapydisciplineList']", "therapydisciplineList");
	}

	public void clickTherapyMasterSubmit() {
		CommonLib.clickButton(By.id("wizardSubmit_label"));
		CommonLib.staticWait(2);
	}

	public void setSearchValue() {
		CommonLib.getElement(By.xpath("//input[@id='searchValue']")).sendKeys(HashTableRepository.getHash("template_code"));
	}

	public void clickCodeSearch() {
		CommonLib.clickButton(By.xpath("//div[contains(text(), 'Search')]"));
	}

	public void selectFirstRow() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td"), 4)) {
			CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td")).click();
		}
	}

	public String getTherapyCode() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td")).getText();
	}

	public String getTherapyDescription() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td[2]")).getText();
	}

	public void selectPublish() {
		CommonLib.clickButton(By.id("templateCodePublish_Y"));
	}

	public void clickDeleteOK() {
		CommonLib.clickButton(By.xpath("//div[@id='dijit_Dialog_0']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
	}

	public int getTherapyMasterCodeCount() {
		int therapy_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]"), 5)) {
			therapy_count = CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]")).size();
		}
		return therapy_count;
	}

	public void clearSearchValue() {
		CommonLib.getElement(By.xpath("//input[@id='searchValue']")).clear();
	}

	public void confirmMessageClick() {
		CommonLib.clickButton(By.xpath("//div[@id='activateInactivateDailogId']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
	}

	public String getTherapyStatus() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td[6]")).getText();
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

	public boolean clickFilterSubmit() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='filterDialog']/div[2]//table/tbody/tr/td/span/span/span/span[3]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='filterDialog']/div[2]//table/tbody/tr/td/span/span/span/span[3]"));
			check = true;
		}
		return check;
	}

	public void sectionType() {
		CommonLib.clickButton(By.xpath("//input[@id='sectionType_N']"));
	}

	public void selectPredefinedTitle() throws InterruptedException {
		// CommonLib.selectDojoListByXpath("//form[@id='detailForm2']/table/tbody/tr[5]/td[2]/div/div[3]/input[@id='preDefinedTitle']",
		// "preDefinedTitle");
		CommonLib.selectRequiredDojoListValue("preDefinedTitle", Config.props.getProperty("treatment_charge"));
	}

	public void selectPredefinedMissedSessionTitle() throws InterruptedException {
		// CommonLib.selectDojoListByXpath("//form[@id='detailForm2']/table/tbody/tr[5]/td[2]/div/div[3]/input[@id='preDefinedTitle']",
		// "preDefinedTitle");
		CommonLib.selectRequiredDojoListValue("preDefinedTitle", Config.props.getProperty("missed_session"));
	}

	public void clickDetailsSubmit() {
		CommonLib.clickButton(By.id("wizardSubmit_label"));
	}

	public String setUserDefinedTitle() {
		String text = CommonLib.RandomText(1, 6);
		String new_txt = "TC" + text;
		CommonLib.setData(By.xpath("//form[@id='detailForm2']//input[@id='userDefinedTitle']"), new_txt);
		HashTableRepository.setHash("user_defined", new_txt);
		return new_txt;
	}

	public boolean clickPreviousButton() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.id("wizardPrevious_label"), 3)) {
			CommonLib.clickButton(By.id("wizardPrevious_label"));
			check = true;
		}
		return check;

	}

	public void clickUDSectionType() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='listGrid']/div[2]/div/div/div/div/div[3]/table/tbody/tr/td[2]"), 5)) {
			String ud_text = CommonLib.getElement(By.xpath("//div[@id='listGrid']/div[2]/div/div/div/div/div[3]/table/tbody/tr/td[2]")).getText();
			if (ud_text.equals("UD")) {
				CommonLib.clickButton(By.xpath("//div[@id='listGrid']/div[2]/div/div/div/div/div[3]/table/tbody/tr/td[2]"));
			}
		}
	}

	public void clickSectionDataPoint() {
		CommonLib.clickButton(By.id("selectDatapointItem_label"));
	}

	public void selectLineType() throws InterruptedException {
		// CommonLib.selectDojoListByXpath("//form[contains(@id,'detailForm')]/table/tbody/tr[3]/td[2]/div/div[3]/input[@id='lineType']",
		// "lineType");
		CommonLib.selectDojoDropDownByKeyDownNumber("lineType", 2);

	}

	public void clickDataPointSearchIcon() {
		CommonLib.clickButton(By.xpath("//a[@class='find' and @href='javascript:findCode();']"));
	}

	public void selectDataPointRow() {
		CommonLib.clickButton(By.xpath("//div[@id='searchGridLookup']/div[2]/div/div/div/div/div[2]/table/tbody/tr/td[1]"));
	}

	public boolean clickPublishConfirm() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='publishDialogId']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
			check = true;
		}
		return check;

	}

}
