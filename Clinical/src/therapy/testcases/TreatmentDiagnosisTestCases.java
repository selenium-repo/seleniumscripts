package therapy.testcases;

import java.awt.AWTException;

import common.CommonLib;
import common.CustomReporter;
import therapy.pages.TreatmentDiagnosisPage;

public class TreatmentDiagnosisTestCases extends TreatmentDiagnosisPage {

	public void verifyTreatmentDiagnosisFunctionalities() throws AWTException, InterruptedException {
		boolean result = verifyTreatmentDiagnosisAdd();
		if (result) {
			verifyTreatmentDiagnosisDelete();
		}
		verifyTreatmentDiagnosisAudit();
		verifyTreatmentDiagnosisCarePlans();
		verifyTreatmentDiagnosisOrderManager();

	}

	public boolean verifyTreatmentDiagnosisAdd() {
		boolean check = false;
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(2);
		selectTreatmentDiagnosisTab();
		int before_count = getTreatmentDiagnosisCount();
		clickTreatmentDiagnosisAdd();
		switchToDialogFrame();
		if (checkICDcheckBox()) {
			clickTreatmentDiagnosisSubmit();
			setParentFrame();
			setHomeFrame();
			int after_count = getTreatmentDiagnosisCount();
			if (after_count > before_count) {
				check = true;
				CustomReporter.MessageLogger("A Treatment Diagnosis record has been added to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed to add a Treatment Diagnosis record in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);
			}
		} else {
			clickTreatmentDiagnosisSubmit();
			setParentFrame();
			setHomeFrame();
			CustomReporter.MessageLogger("No ICD Records are available in Add Treatment Diagnosis Selection window, please check the system", CustomReporter.status.Warning);

		}
		return check;

	}

	public void verifyTreatmentDiagnosisAudit() {
		CommonLib.staticWait(4);
		clickTherapyDocumentationAudit();
		switchToDialogFrame();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Treatment Diagnosis records has been Auditing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Treatment Diagnosis records, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTreatmentDiagnosisCarePlans() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationCarePlans();
		CommonLib.staticWait(8);
		switchToLastIframe();
		boolean result = clickCarePlanClose();
		if (result) {
			CustomReporter.MessageLogger("Treatment Diagnosis Care Plans Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Treatment Diagnosis Care Plans Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyTreatmentDiagnosisOrderManager() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationOrderManager();
		CommonLib.staticWait(6);
		switchToFloatingFrame();
		boolean result = clickOrderManagerClose();
		if (result) {
			CustomReporter.MessageLogger("Treatment Diagnosis Order Manager Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Treatment Diagnosis Order Manager Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyTreatmentDiagnosisDelete() {
		CommonLib.staticWait(4);
		int before_count = getTreatmentDiagnosisCount();
		selectFirstTreatmentRow();
		clickTherapyDocumentationDelete();
		// setDeleteReason();
		CommonLib.staticWait(4);
		clickTreatmentDiagnosisReasonDelete();
		CommonLib.staticWait(3);
		int after_count = getTreatmentDiagnosisCount();
		if (after_count < before_count) {
			CustomReporter.MessageLogger("Treatment Diagnosis Delete Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Treatment Diagnosis Delete Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

}
