package ClinicalFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class allergies {

	static String txtAllergen = "";

	public void verifyAllergiesFunctions() throws InterruptedException {

		MenuNavigation.menuNav("Allergies");
		CommonLib.staticWait(2);
		boolean result = addAllergy(false);
		if (result) {
			editAllergy();
			checkWarningAllergy();
			markReviewedAllergy();
			auditAllergy();
			inactivateAllergy();
			filterAllergy();
		} else {
			CustomReporter.MessageLogger("Failed to add an allergy to the patient allergies screen, hence unable to check the other functionalities, please try again!", status.Fail);
		}

	}

	public static boolean addAllergy(boolean manual) throws InterruptedException {
		boolean result = false;
		CommonLib.changeimplicitwait(8);
		WebElement wb;
		CommonLib.clickButton(By.xpath("//span[@widgetId='add' and contains(@class,'dijitButton')]"));
		WebElement iFraAddAggergy = CommonLib.getElement(By.xpath(".//iframe[contains(@src,'/OptimumClinicals/AllergiesEntry.do')][last()]"));
		CommonLib.setFrameFromCurrent(iFraAddAggergy);
		if (manual) {
			CommonLib.setfocusbyTab("//input[@id='compositeCodeList' and contains(@class,'dijitReset dijitInputInner')]", 1);
			CommonLib.setValueDojo("//input[@name='descriptionOne']", CommonLib.RandomText(1, 12));
		} else {
			txtAllergen = CommonLib.selDojoListValue("compositeCodeList", 4);
			System.out.println(txtAllergen);

		}
		CommonLib.selectDojoListValue("allergySource", "");
		addReaction(true);
		// int Count = 0;
		// boolean failed;
		try {
			CommonLib.changeimplicitwait(0);
			CommonLib.staticWait(3);
			String errorMsg = CommonLib.getText(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"));
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
			if (errorMsg.contains("An allergy of the type selecte") || errorMsg.contains("Either a coded or free text al")) {
				while (true) {
					txtAllergen = CommonLib.selDojoListValue("compositeCodeList", 5);
					CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
					CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				}
			}
			if (errorMsg.contains("A reaction must be selected fo")) {
				while (true) {
					// to do
					CommonLib.selectrow("//div[@id='reactionGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[4]");
					addReaction(false);
					CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				}
			}
			if (errorMsg.contains("An allergy source must be sele")) {
				while (true) {
					CommonLib.selectDojoListValue("allergySource", "");
					CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
					CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				}
			}
		} catch (Exception e) {

		}
		CommonLib.changeimplicitwait(0);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.getText(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td/ol/li"));
		CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		CommonLib.staticWait(4);

		int row_count = CommonLib.getRowCount("//div[contains(@class,'dojoxGridRow') and (@role='row')]/table/tbody/tr");
		// System.out.println(row_count);
		int final_count = row_count / 2;
		for (int i = 1; i <= final_count; i++) {
			wb = CommonLib.getElement(By.xpath("//div[@role='presentation']/div[contains(@class, 'dojoxGridRow') and (@role='row')][" + i + "]/table/tbody/tr/td"));
			String row_allergy = wb.getText();
			if (row_allergy.contains(txtAllergen)) {
				wb.click();
				CustomReporter.MessageLogger("An allergy " + row_allergy + " has been added to the allergies screen successfully", status.Pass);
				result = true;
				break;
			}

		}

		return result;
	}

	public static void addReaction(boolean addrecord) throws InterruptedException {
		if (addrecord) {
			CommonLib.clickButton(By.xpath("//span[@widgetId='add' and contains(@class,'dijitButton')]"));
		} else {
			CommonLib.clickButton(By.xpath("//span[@widgetId='edit' and contains(@class,'dijitButton')]"));
		}
		CommonLib.selectDojoDropDownByKeyDownNumber("reactionList", 3);
		if (Integer.parseInt(CommonLib.RandomText(2, 1)) % 2 == 0) {
			CommonLib.selectradio(By.xpath("//input[@name='reactionType'  and @value='Intolerance']"));
		} else {
			CommonLib.selectradio(By.xpath("//input[@name='reactionType'  and @value='Allergy']"));
		}
		CommonLib.setCheckbox("//*[@type='checkbox' and contains(@name,'reactionSeverity') ]");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitReaction_label']"));

		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
		// CommonLib.staticWait(3);
		// CommonLib.setFrameToDefault();
	}

	public static void editAllergy() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='edit_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(4);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// String first_word = txtAllergen.split(" ", 2)[0];
		// System.out.println(first_word);
		// WebElement iFrameEditAllergy =
		// CommonLib.getElement(By.xpath(".//iframe[contains(@src,'" +
		// first_word + "')]"));
		WebElement iFrameEditAllergy = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'][last()]"));
		CommonLib.setFrameFromCurrent(iFrameEditAllergy);
		// CommonLib.GetDriver().switchTo().frame("" +
		// Config.props.getProperty("MainFrame") +
		// "").switchTo().frame("iFrameDialog");
		// CommonLib.GetDriver().switchTo().frame("iFrameDialog");
		// WebElement web
		// =CommonLib.GetDriver().findElement(By.xpath("//iframe[@id='" +
		// Config.props.getProperty("MainFrame") + "']"));
		// int ifa = web.findElements(By.tagName("iframe")).size();
		// System.out.println(ifa);
		// for (int i = 0; i < ifa; i++) {
		// CommonLib.GetDriver().switchTo().frame(i);
		// int req_cnt =
		// CommonLib.GetDriver().findElements(By.xpath("//form[@id='allergyEntry']/table/tbody/tr[2]/td[2]/div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']")).size();
		// System.out.println(req_cnt);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// }
		// CommonLib.setFrame("iFrameDialog");
		CommonLib.staticWait(4);
		// CommonLib.selectDojoListByXpath("//form[@id='allergyEntry']/table/tbody/tr[2]/td[2]/div[@id='widget_editReasonList']/div[3]/input[@id='editReasonList']",
		// "editReasonList");
		CommonLib.selectDojoListValue("editReasonList", "");

		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
		CommonLib.GetDriver().switchTo().parentFrame();
		if (CommonLib.isElementPresent(By.xpath("//div[@id='carePlanTriggeredItemsDialog']"), 1)) {
			CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]/div"));
			// System.out.println("Care Plan Trigger is Present");
		}
		if (CommonLib.isElementPresent(By.xpath("//td[text()[contains(.,'" + txtAllergen + "')]]"), 5)) {
			CustomReporter.MessageLogger("The Patient Allergy " + txtAllergen + "  has been edited to the list successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit the The Patient's Allergy in the list, please try again!", status.Fail);
		}

	}

	public static void inactivateAllergy() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='delete_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("removalReasonList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitReaction_label']"));
		CommonLib.staticWait(3);
		if (CommonLib.isElementPresent(By.xpath("//td[text()[contains(.,'" + txtAllergen + "')]]"), 5)) {
			CustomReporter.MessageLogger("Failed to inactivate/delete the Patient Allergy  " + txtAllergen + " by using the Inactivate button, which is not as expected", status.Fail);
		} else {
			CustomReporter.MessageLogger("The Patient Allergy  " + txtAllergen + " got removed/inactivated by using the Inactivate button, which is as expected", status.Pass);

		}

	}

	public static void checkWarningAllergy() {

		CommonLib.clickButton(By.xpath("//span[@id='clinicalWarnings_label' and contains(@class,'dijitButtonText')]"));
		String title_txt = CommonLib.getElement(By.xpath("//div[@id='reminderDialog']/div[@class='dijitDialogTitleBar']/span[@id='reminderDialog_title']")).getText();
		if (title_txt.contains("Reminder")) {
			CustomReporter.MessageLogger("For Patient Allergy " + txtAllergen + "  its displaying the title " + title_txt + " which is as expected successfully", status.Pass);
			CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		} else {
			CustomReporter.MessageLogger("For Patient Allergy " + txtAllergen + "  its displaying the title " + title_txt + " which is not as expected", status.Fail);
		}

	}

	public static void auditAllergy() {

		CommonLib.clickButton(By.xpath("//span[@id='viewAudit_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(3);
		WebElement iFramePatientAudit = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog' and contains(@src, 'AllergiesAudit')]"));
		CommonLib.setFrameFromCurrent(iFramePatientAudit);
		CommonLib.staticWait(1);
		String lastaudit = CommonLib.getText(By.xpath("//div[@id='auditGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[3]"));
		CommonLib.clickButton(By.xpath("//div[@id='vitalSignsGraphToolbar']/span/span/span/span[3]"));
		// CommonLib.setFrameToDefault();
		CommonLib.GetDriver().switchTo().parentFrame();
		if (lastaudit != null) {
			CustomReporter.MessageLogger("The Patient Allergy " + txtAllergen + " has the last audit comment as: " + lastaudit + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the audit for " + txtAllergen + " Allergy, which is not as expected", status.Fail);
		}

	}

	public static void markReviewedAllergy() {
		CommonLib.clickButton(By.xpath("//span[@id='markReviewed_label' and contains(@class,'dijitButtonText')]"));
		String review_txt = CommonLib.getText(By.xpath("//td[@id='lastViewBlock']"));
		if (review_txt.contains("reviewed")) {
			CustomReporter.MessageLogger("The Patient Allergy " + txtAllergen + " has been reviewed and the same is displayed as : " + review_txt + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to review the Patient Allergy " + txtAllergen + " and the displayed text as : " + review_txt + ", which is as not expected", status.Fail);
		}

	}

	public static void filterAllergy() {
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showCorrectedEntriesN']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int row_count_wo_inactive = CommonLib.getRowCount("//div[contains(@class,'dojoxGridRow') and (@role='row')]/table/tbody/tr");
		int final_count_wo_inactive = row_count_wo_inactive / 2;
		CustomReporter.MessageLogger("Number of Patient Allergies displayed without Inactive records is :" + final_count_wo_inactive + ", which is as expected", status.Information);

		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showCorrectedEntriesY']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int row_count_with_inactive = CommonLib.getRowCount("//div[contains(@class,'dojoxGridRow') and (@role='row')]/table/tbody/tr");
		int final_count_with_inactive = row_count_with_inactive / 2;
		CustomReporter.MessageLogger("Number of Patient Allergies displayed without Inactive records is :" + final_count_with_inactive + ", which is as expected", status.Information);
		if (final_count_wo_inactive != final_count_with_inactive) {
			CustomReporter.MessageLogger(
					"Number of Patient Allergies displayed without Inactive records is :" + final_count_wo_inactive + " and with Inactive records displayed is:" + final_count_with_inactive + ", hence the filter button is working properly",
					status.Pass);
		} else {
			CustomReporter.MessageLogger("Number of Patient Allergies displayed for without Inactive Record and With Inactive Record is the same :" + final_count_wo_inactive + "", status.Information);

		}

	}

}