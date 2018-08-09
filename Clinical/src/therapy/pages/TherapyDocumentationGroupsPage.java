package therapy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.HashTableRepository;
import common.MenuNavigation;

public class TherapyDocumentationGroupsPage {

	public void navigateToPatientTherapyDocumentation() {
		MenuNavigation.menuNav("PatientTherapyDocumentation");
	}

	public void navigateToTherapyDocumentationGroups() {
		MenuNavigation.menuNav("TherapyDocumentationGroups");
	}

	public void therapyDocumentationGroupsAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void therapyDocumentationGroupsEdit() {
		CommonLib.clickButton(By.id("editItem_label"));
	}

	public void therapyDocumentationGroupsCopy() {
		CommonLib.clickButton(By.id("copyItem_label"));
	}

	public void therapyDocumentationGroupsDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void therapyDocumentationGroupsDetails() {
		CommonLib.clickButton(By.id("detailItem_label"));
	}

	public void therapyDocumentationGroupsAudit() {
		CommonLib.clickButton(By.id("auditItem_label"));
	}

	public void setTherapyGroupCode() {
		String text = CommonLib.RandomText(1, 6);
		String new_txt = "TG" + text;
		System.out.println(new_txt);
		CommonLib.getElement(By.xpath("//form[@id='detailForm1']//input[@id='code']")).sendKeys(new_txt);
		HashTableRepository.setHash("therapy_doc_code", new_txt.toUpperCase());
	}

	public void setTherapyGroupDescription() {
		CommonLib.setData(By.xpath("//form[@id='detailForm1']//input[@id='description']"), HashTableRepository.getHash("therapy_doc_code"));
	}

	public void clickTherapyDocumentationGroupSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']"));
	}

	public void setCodeSearchValue() {
		CommonLib.getElement(By.xpath("//input[@id='searchValue']")).sendKeys(HashTableRepository.getHash("therapy_doc_code"));
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
		String new_edit_txt = "TC" + text;
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

}
