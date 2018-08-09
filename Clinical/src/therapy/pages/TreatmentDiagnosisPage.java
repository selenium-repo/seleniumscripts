package therapy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.MenuNavigation;

public class TreatmentDiagnosisPage extends PatientTherapyDocumentationPage {
	public void navigateToPatientTherapyDocumentation() {
		MenuNavigation.menuNav("PatientTherapyDocumentation");
	}

	public void selectTreatmentDiagnosisTab() {
		CommonLib.clickButton(By.id("patientTherapyDocumentationTabs_tablist_treatmentDiagnosisTab"));
	}

	public void clickTreatmentDiagnosisAdd() {
		CommonLib.clickButton(By.xpath("//span[@id='addItem_label']"));
	}

	public void switchToDialogFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public boolean checkICDcheckBox() {
		boolean flag = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='icdDetailsGrid_rowSelector_0' and @role='checkbox']"), 5)) {
			CommonLib.clickButton(By.xpath("//div[@id='icdDetailsGrid_rowSelector_0' and @role='checkbox']"));
			flag = true;
		}
		return flag;
	}

	public void setHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id("mainWorkFrame0"));
	}

	public void setParentFrame() {
		CommonLib.staticWait(3);
		CommonLib.GetDriver().switchTo().parentFrame();
	}

	public void clickTreatmentDiagnosisSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']"));
	}

	public int getTreatmentDiagnosisCount() {
		int treatment_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='diagnosesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 5)) {
			treatment_count = CommonLib.getElements(By.xpath("//div[@id='diagnosesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
		}
		return treatment_count;
	}

	public void clickTreatmentDiagnosisReasonDelete() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='dijit_Dialog_2']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"), 6)) {
			CommonLib.clickButton(By.xpath("//div[@id='dijit_Dialog_2']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		}

	}

	public void selectFirstTreatmentRow() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='diagnosesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='diagnosesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"));
		}
	}

}
