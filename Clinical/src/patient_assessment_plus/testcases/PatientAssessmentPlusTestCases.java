package patient_assessment_plus.testcases;

import java.io.IOException;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;
import patient_assessment_plus.pages.PatientAssessmentPlusPage;

public class PatientAssessmentPlusTestCases extends PatientAssessmentPlusPage {

	public void verifyPtAssessmentPlusFucntionalities() throws IOException, InterruptedException {
		MenuNavigation.menuNav("ScreenDefinitionMaintenance");
		setScreenDefinitionData();
		MenuNavigation.menuNav("RollTypes");
		checkGroupTable();
		MenuNavigation.menuNav("AssessmentGroupsTable");
		checkAssessmentGroups();
		MenuNavigation.menuNav("PatientAssessmentPlus");
		selectAssessmentTab();
		boolean check = verifyPtAssessmentPlusAdd();
		if (check) {
			verifyPtAssessmentPlusEdit();
			verifyPtAssessmentPlusPrint();
			verifyPtAssessmentPlusAssessmentHistory();
			verifyPtAssessmentPlusComplete();
			verifyPtAssessmentPlusDelete();
		}
	}

	public boolean verifyPtAssessmentPlusAdd() throws InterruptedException {
		boolean result = false;
		int before_add = getAssessmentCount();
		clickAddAssessment();
		CommonLib.staticWait(2);
		// CommonLib.clickButton(By.xpath("//form[@id='filterForm']//input[contains(@class,'dijitArrowButtonInner')
		// and @value='▼ ']"));
		// String dropdown_value =
		// Config.props.getProperty("assmt_description");
		// CommonLib.clickButton(By.xpath("//form[@id='filterForm']//div[@class='dijitReset
		// dijitMenuItem' and contains(., '" + dropdown_value + "')]"));
		// setCharByCharData();
		selectAssessmentTemplate();
		// CommonLib.selectRequiredDojoListValue("assessmentsTemplateList",
		// Config.props.getProperty("assmt_description"));
		CommonLib.staticWait(2);
		clickAssessmentTemplateSubmit();
		fillAssessmentData();
		int after_add = getAssessmentCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Patient Assessment Plus record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Patient Assessment Plus record to the system, please try again! which is not as expected", CustomReporter.status.Fail);

		}
		return result;
	}

	public void verifyPtAssessmentPlusEdit() throws InterruptedException {
		selectAssessmentFirstRow();
		clickEditAssessment();
		String edit_reason = selectAssessmentEditReason();
		setBloodPressureData();
		clickAssessmentSubmit();
		// clickCompleteAssessment();
		checkCarePlanTrigger();
		if (edit_reason != "") {
			CustomReporter.MessageLogger("Patient Assessment Plus record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit the Patient Assessment Plus record, please try again!, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPtAssessmentPlusPrint() throws IOException {
		CommonLib.staticWait(3);
		clickAssessmentPrint();
		clickSubmitPtAssessmentPrintOptions();
		CommonLib.staticWait(8);
		int count = getWindowHandleCount();
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		clickPtAssessmentPrintOptionsClose();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		if (count > 1) {
			CustomReporter.MessageLogger("Patient Assessment Plus record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the Patient Assessment Plus  in the system, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyPtAssessmentPlusAssessmentHistory() {
		clickSelectionProperties();
		selectEntireHistory();
		String assmnt_type1 = selectAssessmentType();
		CommonLib.staticWait(8);
		int row_count1 = getAssessmentCount();
		String assmnt_type2 = selectAssessmentType();
		CommonLib.staticWait(8);
		int row_count2 = getAssessmentCount();
		String assmnt_type3 = selectAssessmentType();
		CommonLib.staticWait(8);
		int row_count3 = getAssessmentCount();
		CommonLib.staticWait(2);
		clickSelectionProperties();
		CommonLib.staticWait(3);
		if (assmnt_type1 != "") {
			CustomReporter.MessageLogger("Patient Assessment Plus record has been displaying the Entire History for " + assmnt_type1 + ", " + assmnt_type2 + " and " + assmnt_type3 + " having the number of records " + row_count1 + ", "
					+ row_count2 + " and " + row_count3 + " respectively in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Entire History of the Patient Assessment Plus, please try again!", CustomReporter.status.Fail);
		}
	}

	public void verifyPtAssessmentPlusComplete() {
		selectAssessmentFirstRow();
		String firstRow = HashTableRepository.getHash("NoRow");
		if (firstRow != null) {
			if (firstRow.equals("NoRow")) {
				CustomReporter.MessageLogger("Assessment gid is empty.", CustomReporter.status.Pass);
			}
		} else {
			String status_before = getAssessmentStatus();
			clickCompleteRSign();
			String status_after = getAssessmentStatus();
			if (status_after.equals("Complete")) {
				CustomReporter.MessageLogger("Patient Assessment Plus record's Status has been changed from " + status_before + " to " + status_after + "in the system successfully, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed to change the status of Patient Assessment Plus  record in the system, please try again!", CustomReporter.status.Fail);

			}
		}

	}

	public void verifyPtAssessmentPlusDelete() throws InterruptedException {
		selectAssessmentFirstRow();
		int count_before = getAssessmentCount();
		clickAssessmentDelete();
		selectAssessmentDeleteReason();
		clickAssessmentDeleteSubmit();
		int count_after = getAssessmentCount();
		if (count_after < count_before) {
			CustomReporter.MessageLogger("Patient Assessment Plus record has been deleted from the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete the Patient Assessment Plus record from the system, please try again!", CustomReporter.status.Fail);

		}

	}

}
