package therapy.testcases;

import java.awt.AWTException;

import common.CommonLib;
import common.CustomReporter;
import therapy.pages.CertificatesRecertificatesPage;

public class CertificatesRecertificatesTestCases extends CertificatesRecertificatesPage {

	public void verifyCertificatesFunctionalities() throws AWTException, InterruptedException {
		verifyCertificatesAdd();
		// verifyCertificateEdit();
		verifyCertificateFilter();
		verifyCertificateAudit();
		verifyCertificateCarePlans();
		verifyCertificateOrderManager();
		verifyCertificateDelete();
		verifyReCertificatesAdd();
	}

	public void verifyCertificatesAdd() throws AWTException, InterruptedException {
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(2);
		selectCertificateTab();
		CommonLib.staticWait(2);
		int before_add = getCertificatesCount();
		System.out.println(before_add);
		clickCertificatesAddArrow();
		clickCertificateAdd();
		CommonLib.staticWait(4);
		switchToLastFloatingFrame();
		setFrequencyValue();
		setInitialAssessment();
		CommonLib.staticWait(2);
		switchToShortTermGoalFrame();
		setShortTermGoal();
		CommonLib.staticWait(2);
		switchToLongTermGoalFrame();
		setLongTermGoal();
		switchToTreatmentPlanFrame();
		setTreatmentPlan();
		setParentFrame();
		selectPhysician();
		setParentFrame();
		switchToLastFloatingFrame();
		clickCertificateSubmit();
		setParentFrame();
		setHomeFrame();
		CommonLib.staticWait(3);
		int after_add = getCertificatesCount();
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("A Certificate record has been added to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Certificate record in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyCertificateEdit() throws InterruptedException, AWTException {
		if (selectCreatedCertificateRow()) {
			int row = getCertificatesCount();
			clickLastRow(row);
		}
		clickCertificatesEdit();
		switchToEditFloatingFrame();
		boolean check = selectPrimaryDiagnosis();
		setParentFrame();
		switchToEditFloatingFrame();
		clickCertificateSubmit();
		setParentFrame();
		setHomeFrame();
		if (check) {
			setParentFrame();
			setHomeFrame();
			CustomReporter.MessageLogger("A Certificate record has been edited to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Certificate record in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyCertificateFilter() {
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationIncompletCheckBox();
		clickTherapyDocumentationFilterSubmit();
		int bef_count = getCertificatesCount();
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationIncompletCheckBox();
		clickTherapyDocumentationFilterSubmit();
		int aft_count = getCertificatesCount();
		if (aft_count != bef_count) {
			CustomReporter.MessageLogger("Certificates records has been filtered to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to filter the Certificates records in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Information);

		}
	}

	public void verifyCertificateAudit() {
		CommonLib.staticWait(4);
		// selectAddedNote();
		clickTherapyDocumentationAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Certificate records has been Auditing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Certificate records, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyCertificateFlowsheets() {

		clickTherapyDocumentationFlowsheets();
		CommonLib.staticWait(6);
		boolean result = closeCertificateFlowsheets();
		if (result) {
			CustomReporter.MessageLogger("Certificate Flowsheets Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Certificate Flowsheets Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyCertificateCarePlans() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationCarePlans();
		CommonLib.staticWait(8);
		switchToLastIframe();
		boolean result = clickCarePlanClose();
		if (result) {
			CustomReporter.MessageLogger("Certificate Care Plans Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Certificate Care Plans Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyCertificateOrderManager() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationOrderManager();
		CommonLib.staticWait(6);
		switchToFloatingFrame();
		boolean result = clickOrderManagerClose();
		if (result) {
			CustomReporter.MessageLogger("Certificate Order Manager Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Certificate Order Manager Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyCertificateDelete() {
		CommonLib.staticWait(4);
		selectAddedNote();
		int bef_count = getCertificatesCount();
		clickTherapyDocumentationDelete();
		// setDeleteReason();
		CommonLib.staticWait(4);
		clickCertificateReasonDelete();
		CommonLib.staticWait(6);
		int aft_count = getCertificatesCount();
		if (aft_count < bef_count) {
			CustomReporter.MessageLogger("Certificate Delete Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Certificate Delete Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyReCertificatesAdd() throws AWTException, InterruptedException {

		CommonLib.staticWait(2);
		int before_add = getCertificatesCount();
		System.out.println(before_add);
		clickCertificatesAddArrow();
		clickReCertificateAdd();
		CommonLib.staticWait(4);
		switchToReCertiFrame();
		setFrequencyValue();
		setReasonContinueTreatment();
		CommonLib.staticWait(2);
		switchToShortTermGoalFrame();
		setShortTermGoal();
		CommonLib.staticWait(2);
		switchToLongTermGoalFrame();
		setLongTermGoal();
		switchToTreatmentPlanFrame();
		setTreatmentPlan();
		setParentFrame();
		selectPhysician();
		setParentFrame();
		switchToReCertiFrame();
		clickCertificateSubmit();
		setParentFrame();
		setHomeFrame();
		CommonLib.staticWait(3);
		int after_add = getCertificatesCount();
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("A ReCertificate record has been added to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a ReCertificate record in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}
}
