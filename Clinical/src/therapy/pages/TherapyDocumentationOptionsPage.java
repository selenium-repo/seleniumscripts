package therapy.pages;

import org.openqa.selenium.By;

import common.CommonLib;
import common.MenuNavigation;

public class TherapyDocumentationOptionsPage {

	public void navigateToPatientTherapyDocumentation() {
		MenuNavigation.menuNav("PatientTherapyDocumentation");
	}

	public void navigateToTherapyDocumentationOptions() {
		MenuNavigation.menuNav("TherapyDocumentationOptions");
	}

	public void therapyDocumentationOptionsTabSelect() {
		CommonLib.clickButton(By.xpath("//span[@id='findPatientTabs_tablist_optionsTab']"));
	}

	public void therapyDocumentationOptionsCheckbox(String id) {
		// if (CommonLib.isElementPresent(By.xpath("//div[@widgetId='" + id + "'
		// and contains(@class, 'dijitCheckBox')]/input[@id='" + id + "']"), 3))
		// {
		String check = CommonLib.getElement(By.xpath("//div[@widgetId='" + id + "' and contains(@class, 'dijitCheckBox')]/input[@id='" + id + "']")).getAttribute("aria-checked");
		if (check.equals("true")) {
		} else {

			CommonLib.clickButton(By.xpath("//div[@widgetId='" + id + "' and contains(@class, 'dijitCheckBox')]/input[@id='" + id + "']"));
		}
		// }
	}

	public void therapyDocumentationOptionsTotalTreatmentTime() {
		// CommonLib.clickButton(By.xpath("//input[@name='requireTotalTreatmentTime']"));
		CommonLib.clickButton(By.id("dijit_form_RadioButton_1"));
	}

	public void therapyDocumentationOptionsDisableTotalTreatmentTime() {
		CommonLib.clickButton(By.id("dijit_form_RadioButton_2"));
	}

	public void therapyDocumentationOptionsRequireTotalTreatmentTime() {
		CommonLib.clickButton(By.id("dijit_form_RadioButton_4"));
	}

	public boolean clickTherapyDocumentationOptionsSubmit() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//span[@id='wizardSubmit_label']/div[contains(text(), 'Submit')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']/div[contains(text(), 'Submit')]"));
			check = true;
		}
		return check;
	}

	public void therapyDocumentationCertificationRecertificationTabSelect() {
		CommonLib.clickButton(By.xpath("//span[@id='findPatientTabs_tablist_certificationsTab']"));
	}

	public void therapyDocumentationCertificationTimePeriodSet() {
		CommonLib.getElement(By.xpath("//input[@id='certTimePeriod']")).clear();
		CommonLib.setData(By.xpath("//input[@id='certTimePeriod']"), "4");
	}

	public void therapyDocumentationRecertificationTimePeriodSet() {
		CommonLib.getElement(By.xpath("//input[@id='reCertTimePeriod']")).clear();
		CommonLib.setData(By.xpath("//input[@id='reCertTimePeriod']"), "4");
	}

	public String getTherapyDocumentationCertificateProviderName() {
		String name = CommonLib.getElement(By.xpath("//input[@id='providerNameNumber']")).getAttribute("value");
		return name;
	}

	public void selectTherapyDocumentationDropDown(String str) throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber(str, 2);

	}

}
