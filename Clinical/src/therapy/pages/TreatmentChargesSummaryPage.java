package therapy.pages;

import org.openqa.selenium.By;

import common.CommonLib;
import common.HashTableRepository;

public class TreatmentChargesSummaryPage extends PatientTherapyDocumentationPage {

	public void selectTreatmentChargesSummaryTab() {
		if (CommonLib.isElementPresent(By.id("patientTherapyDocumentationTabs_tablist_treatmentChargesTab"), 3)) {
			CommonLib.clickButton(By.id("patientTherapyDocumentationTabs_tablist_treatmentChargesTab"));
		}
	}

	public void clickTreatmentChargesSummarySelectionProperties() {
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.xpath("//span[contains(.,'Selection Properties')]"));
		// (@class='dijitTitlePaneTextNode') and
	}

	public void selectTreatmentChargesSummaryDiscipline() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("treatChargeDiscipline", 1);
	}

	public void selectTreatmentChargesSummaryTemplate(int val) throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("treatChargeTemplate", val);
	}

	public int getTreatmentChargesCount() {
		int treatment_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='treatmentChargesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 5)) {
			treatment_count = CommonLib.getElements(By.xpath("//div[@id='treatmentChargesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
			System.out.println(treatment_count);
		}
		return treatment_count;
	}

	public void clickTherapyNotesSignAndSubmit() {
		CommonLib.clickButton(By.xpath("//div[@id='mainButtons']/table/tbody/tr/td/table/tbody/tr/td/span[2]/span/span/span[3]/div[contains(.,'Sign and Submit')]"));
	}

	public boolean checkTreatmentChargesTemplate() {
		boolean check = false;
		String template = HashTableRepository.getHash("template_code");
		if (CommonLib.isElementPresent(By.xpath("//a[contains(@href,'" + template + "')]"), 5)) {
			check = true;
		}
		return check;
	}

	public String getTreatmentChargesColumnsData(int i) {
		String data = null;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='treatmentChargesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td['" + i + "']"), 5)) {
			data = CommonLib.getElement(By.xpath("//div[@id='treatmentChargesGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td['" + i + "']")).getText();
		}
		return data;
	}

}
