package patient_indicators.testcases;

import java.io.IOException;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import patient_indicators.pages.PatientIndicatorPage;

public class PatientIndicatorTestCases extends PatientIndicatorPage {

	public void verifyPatientIndicatorFunctionalities() throws InterruptedException {
		navigateToPatientIndicator();
		deleteRemainingPI();
		boolean result = verifyPatientIndicatorAdd();
		if (result) {
			verifyPatientIndicatorEdit();
			verifyPatientIndicatorCopy();
			verifyPatientIndicatorDelete();

		}

	}

	public boolean verifyPatientIndicatorAdd() throws InterruptedException {
		boolean check = false;
		int before_add = getPatientIndicatorRowCount();
		clickPatientIndicatorAdd();
		String code_txt = selectPatientIndicatorCode(2);
		HashTableRepository.setHash("code_txt", code_txt);
		String comment_txt = setPatientIndicatorComment();
		HashTableRepository.setHash("comment_txt", comment_txt);
		clickPatientIndicatorSubmit();
		duplicateDialogHandling(4);
		getRowNumberOfPatientCode();
		int after_add = getPatientIndicatorRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Patient Indicator record has been added with code as  " + code_txt + " to the system successfully, which is as expected", CustomReporter.status.Pass);
			check = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Patient Indicator record to the system, please try again! which is not as expected", CustomReporter.status.Fail);

		}
		return check;

	}

	public void verifyPatientIndicatorEdit() throws InterruptedException {
		// selectPatientIndicatorFirstRow();
		clickPatientIndicatorEdit();
		String comment_new = setPatientIndicatorComment();
		HashTableRepository.setHash("comment_new", comment_new);
		clickPatientIndicatorSubmit();
		duplicateDialogHandling(4);
		if (comment_new != HashTableRepository.getHash("comment_txt")) {
			CustomReporter.MessageLogger("Patient Indicator record has been edited with " + comment_new + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {

			CustomReporter.MessageLogger("Failed to edit the Patient Indicator record, please try again!, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyPatientIndicatorCopy() throws InterruptedException {
		// selectPatientIndicatorFirstRow();
		getRowNumberOfPatientCode();
		clickPatientIndicatorCopy();
		String comment = getCommentValue();
		System.out.println(comment);
		selectPatientIndicatorCode(3);
		clickPatientIndicatorSubmit();
		duplicateDialogHandling(4);
		if (HashTableRepository.getHash("comment_val").equals(comment)) {
			CustomReporter.MessageLogger("Patient Indicator record has been copied with comment " + comment + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to copy the Patient Indicator record, please try again!, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientIndicatorDelete() {
		int before_delete = getPatientIndicatorRowCount();
		System.out.println(before_delete);
		getRowNumberOfPatientCode();
		clickPatientIndicatorDelete();
		checkDeletePopUp();
		clickDialog();
		int after_delete = getPatientIndicatorRowCount();
		if (after_delete < before_delete) {
			CustomReporter.MessageLogger("Patient Indicator record has been deleted from the system successfully, which is as expected", CustomReporter.status.Pass);
			deleteRemainingPI();
		} else {
			CustomReporter.MessageLogger("Failed to delete the Patient Indicator Plus record from the system, please try again!", CustomReporter.status.Fail);

		}

	}

	public void deleteRemainingPI() {
		int row_count = getPatientIndicatorRowCount();
		if (row_count > 0) {
			for (int i = 0; i <= row_count; i++) {
				selectPatientIndicatorFirstRow();
				clickPatientIndicatorDelete();
				clickDialog();
			}
		}
	}

	public void verifyPtChartTempLocation() throws InterruptedException, IOException {
		// navigateToPtChartTempLocation();
		boolean result = verifyMoveToTempLocation();
		if (result) {
			verifyMoveToNsLocation();
			verifyAuditList();
			verifyIcons();
			verifyLabIconPrint();
		}

	}

	public boolean verifyMoveToTempLocation() throws InterruptedException {
		boolean check = false;
		navigateToPtChartTempLocation();
		uncheckReturnedToNsCheckBox();
		selectTempLocation();
		clickTempLocationSubmit();
		boolean result = validateTempLocation();
		if (result) {
			CustomReporter.MessageLogger("Patient has been moved to temp location: '" + HashTableRepository.getHash("temp_loc") + "' in the system successfully, which is as expected", CustomReporter.status.Pass);
			check = true;
		} else {
			CustomReporter.MessageLogger("Failed to move the Patient to the temp location, please try again!", CustomReporter.status.Fail);
		}
		return check;
	}

	public void verifyMoveToNsLocation() {
		checkReturnedToNsCheckBox();
		clickTempLocationSubmit();
		boolean check = validateNsLocation();
		if (check) {
			CustomReporter.MessageLogger("Patient has been moved back to earlier location in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to move back to the earlier location, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyAuditList() {
		clickTempLocationAuditList();
		if (checkAuditData()) {
			CustomReporter.MessageLogger("Patient has been moved to temp location: '" + HashTableRepository.getHash("temp_loc") + "' in the system as shown inthe Audit List successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to show the Patient to the temp location, please try again!", CustomReporter.status.Fail);
		}
	}

	public void verifyIcons() {
		CommonLib.staticWait(3);
		boolean result = checkLabIcon();
		if (result) {
			CustomReporter.MessageLogger("Lab Icon is displaying the Patient's Lab Test Summanry in the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to display the Lab Test Summary through Lab Icon, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyLabIconPrint() throws IOException {
		clickLabIconPrint();
		CommonLib.staticWait(8);
		int count = getWindowHandleCount();
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		closeLabTestSummaryScreen();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		if (count > 1) {
			CustomReporter.MessageLogger("Lab Icon is printing the Patient's Lab Test Summanry in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Lab Test Summary through Lab Icon, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyPatientClinicalSummary() {
		navigateToPatientClinicalSummary();
		verifyPatientIndicatorIcon();
		verifyAllergiesIcon();
		verifyAssessmentsIcon();
		verifyOrderEntryIcon();
		verifyGraphicDataSheetIcon();

	}

	public void verifyAllergiesIcon() {
		if (verifyPatientClinicalSummaryIcon(Config.props.getProperty("patient_icon2"))) {
			closeDialog();
			CustomReporter.MessageLogger("Patient Clinical Summary's " + Config.props.getProperty("patient_icon2") + " Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's " + Config.props.getProperty("patient_icon2") + " Icon, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientIndicatorIcon() {
		if (verifyPatientClinicalSummaryIcon(Config.props.getProperty("patient_icon1"))) {
			closeDialog();
			CustomReporter.MessageLogger("Patient Clinical Summary's " + Config.props.getProperty("patient_icon1") + " Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's " + Config.props.getProperty("patient_icon1") + " Icon, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyAssessmentsIcon() {
		if (verifyPatientClinicalSummaryIcon(Config.props.getProperty("patient_icon3"))) {
			closeDialog();
			CustomReporter.MessageLogger("Patient Clinical Summary's " + Config.props.getProperty("patient_icon3") + " Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's " + Config.props.getProperty("patient_icon3") + " Icon, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyImcompleteOrdersIcon() {
		if (verifyPatientClinicalSummaryIcon(Config.props.getProperty("patient_icon4"))) {
			closeDialog();
			CustomReporter.MessageLogger("Patient Clinical Summary's " + Config.props.getProperty("patient_icon4") + " Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's " + Config.props.getProperty("patient_icon4") + " Icon, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyOrderEntryIcon() {
		clickClinicalSummaryOrderEntry();
		CommonLib.staticWait(9);
		boolean result = verifyCloseGraphSheet();
		if (result) {
			CustomReporter.MessageLogger("Patient Clinical Summary's Order Entry Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's Order Entry screen, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyGraphicDataSheetIcon() {
		clickClinicalSummaryGraphicDataSheet();
		CommonLib.staticWait(8);
		if (closeDialog()) {
			CustomReporter.MessageLogger("Patient Clinical Summary's Graphic Data Sheet Icon is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Patient Clinical Summary's Graphic Data Sheet screen, please try again!", CustomReporter.status.Fail);
		}

	}

}
