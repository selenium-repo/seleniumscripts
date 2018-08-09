package therapy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.HashTableRepository;
import common.MenuNavigation;

public class TherapyDisciplineMasterPage {

	public void navigateToPatientTherapyDocumentation() {
		MenuNavigation.menuNav("PatientTherapyDocumentation");
	}

	public void navigateToTherapyDisciplineMaster() {
		MenuNavigation.menuNav("TherapyDisciplineMaster");
	}

	public void therapyDisciplineMasterAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void therapyDisciplineMasterEdit() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public void therapyDisciplineMasterCopy() {
		CommonLib.clickButton(By.id("copyItem_label"));
	}

	public void therapyDisciplineMasterDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void therapyDisciplineMasterDetails() {
		CommonLib.clickButton(By.id("detailItem_label"));
	}

	public void therapyDisciplineMasterAudit() {
		CommonLib.clickButton(By.id("viewAudit_label"));
	}

	public void setTherapyDisciplineMasterCode() {
		String text = CommonLib.RandomText(1, 3);
		CommonLib.getElement(By.xpath("//form[@id='detailForm1']//input[@id='code']")).sendKeys(text);
		HashTableRepository.setHash("therapy_dis_code", text.toUpperCase());
	}

	public void setTherapyDisciplineMasterCopyCode() {
		String text = CommonLib.RandomText(1, 3);
		CommonLib.getElement(By.xpath("//form[@id='detailForm1']//input[@id='code']")).clear();
		CommonLib.getElement(By.xpath("//form[@id='detailForm1']//input[@id='code']")).sendKeys(text);
		HashTableRepository.setHash("therapy_dis_copy_code", text.toUpperCase());
	}

	public void setTherapyDisciplineMasterDescription() {
		CommonLib.setData(By.xpath("//form[@id='detailForm1']//input[@id='description']"), HashTableRepository.getHash("therapy_dis_code"));
	}

	public void setCMSTherapyDisciplineCorrelation() throws InterruptedException {
		// CommonLib.setRandomDropDown(By.id("mdsTherapy"));
		CommonLib.selectDojoListByXpath("//input[@id='mdsTherapy']", "mdsTherapy");
	}

	public void clickTherapyDisciplineMasterSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']"));
	}

	public void setCodeSearchValue() {
		CommonLib.getElement(By.xpath("//input[@id='searchValue']")).sendKeys(HashTableRepository.getHash("therapy_dis_code"));
	}

	public void clickCodeSearch() {
		CommonLib.clickButton(By.xpath("//div[contains(text(), 'Search')]"));
	}

	public String getTherapyCode() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td")).getText();
	}

	public void selectFirstRow() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td"), 4)) {
			CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td")).click();
		}
	}

	public String setTemplateDescriptionEdit() {
		String text = CommonLib.RandomText(1, 6);
		String new_edit_txt = text;
		CommonLib.setData(By.xpath("//form[@id='detailForm1']//input[@id='description']"), new_edit_txt);
		HashTableRepository.setHash("edit_therapy_code", new_edit_txt);
		return new_edit_txt;
	}

	public void clearSearchValue() {
		CommonLib.getElement(By.xpath("//input[@id='searchValue']")).clear();
	}

	public String getTherapyDescription() {
		return CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]/table/tbody/tr/td[2]")).getText();
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

	public int getTherapyMasterCodeCount() {
		int therapy_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]"), 5)) {
			therapy_count = CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div/div[contains(@role,'row')]")).size();
		}
		return therapy_count;
	}

	public void clickDeleteOK() {
		CommonLib.clickButton(By.xpath("//div[@id='dijit_Dialog_0']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
	}

	public void setTherapyStaffingLevelCode() {
		String text = CommonLib.RandomText(1, 6);
		String new_txt = "SL" + text;
		System.out.println(new_txt);
		CommonLib.getElement(By.xpath("//form[@id='detailForm2']//input[@id='levelCode']")).sendKeys(new_txt);
		HashTableRepository.setHash("therapy_staff_code", new_txt.toUpperCase());
	}

	public void setTherapyDisciplineMasterStaffingDescription() {
		CommonLib.setData(By.xpath("//form[@id='detailForm2']//input[@id='levelDescription']"), HashTableRepository.getHash("therapy_staff_code"));
	}

	public boolean clickTherapyDisciplineMasterPrevious() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@id='table.PreviousTS_label']"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='table.PreviousTS_label']"));
			check = true;
		}
		return check;
	}

}
