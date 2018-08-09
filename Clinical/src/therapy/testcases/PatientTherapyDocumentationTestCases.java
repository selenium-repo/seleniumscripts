package therapy.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import therapy.pages.PatientTherapyDocumentationPage;

public class PatientTherapyDocumentationTestCases extends PatientTherapyDocumentationPage {

	public void setUpPatientTherapy() {
		// navigateToUserTable();
		// setUserName();
		// clickSearchButton();
		// String user_id = getUserIDValue();
		// if (user_id.equals("ROOT")) {
		// getRollTypeValue();
		navigateToRollTypes();
		setCode();
		clickSearchButton();
		String user_code = getUserIDValue();
		System.out.println(user_code);
		if (user_code.equals("ADMIN")) {
			clickCode();
			clickRollTypesEdit();
			CommonLib.staticWait(9);
			storeTherapyAuthorizationList();
			navigateToTherapyGroups();
			setGroupValue();
			clickSearchButton();
			authorize_therapy();
		}
		// }
	}

	public void verifyPatientTherapyDocumentationFunctionalities() throws InterruptedException, IOException {
		verifyPatientTherapyDocumentationAdd();
		verifyPatientTherapyDocumentationEdit();
		verifyPatientTherapyDocumentationFilter();
		verifyPatientTherapyDocumentationAudit();
		verifyPatientTherapyDocumentationFlowsheets();
		verifyPatientTherapyDocumentationCarePlans();
		verifyPatientTherapyDocumentationOrderManager();
		verifyPatientTherapyDocumentationDelete();
		verifyPatientTherapyDocumentationSign();
		verifyPatientTherapyDocumentationAmend();

	}

	public void verifyPatientTherapyDocumentationAdd() throws InterruptedException {
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(2);
		int bef_count = getTherapyNotesCount();
		therapyDocumentationAdd();
		selectTherapyTemplate();
		CommonLib.staticWait(2);
		clickTherapyTemplateSubmit();
		CommonLib.staticWait(4);
		expandOpenAll();
		selectTherapyStartTime();
		selectTherapyStopTime();
		setIndividualMin();
		setEvaluationMin();
		setGroupMin();
		setConcurrentMin();
		setCoTreatmentMin();
		setBloodPressure();
		clickSaveButton();
		CommonLib.staticWait(5);
		int aft_count = getTherapyNotesCount();
		if (aft_count > bef_count) {
			CustomReporter.MessageLogger("Therapy Note has been added to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Therapy Note in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyPatientTherapyDocumentationEdit() {
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		String bef_edit = getBloodPressureValue();
		clickTherapyDocumentationEdit();
		CommonLib.staticWait(2);

		clickAddTreatmentCharges();
		checkTreatmentChargesList();
		clickTreatmentChargesSubmit();
		setTreatmentChargesMinute();
		setTreatmentChargesUnit();
		clickModifireIcon();
		selectThreatmentChargesModifier();
		moveSelectedModifier();
		clickModifierSubmit();

		selectEditBP();
		setBloodPressure();
		clickSaveButton();
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		String aft_edit = getBloodPressureValue();
		if (bef_edit != aft_edit) {
			CustomReporter.MessageLogger("Therapy Note has been edited to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Therapy Note in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientTherapyDocumentationFilter() {
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationFilterCheckBox();
		clickTherapyDocumentationFilterSubmit();
		CommonLib.staticWait(6);
		int bef_count = getTherapyNotesCount();
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationFilterCheckBox();
		clickTherapyDocumentationFilterSubmit();
		CommonLib.staticWait(6);
		int aft_count = getTherapyNotesCount();
		if (aft_count != bef_count) {
			CustomReporter.MessageLogger("Therapy Note has been filtered to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to filter a Therapy Notes in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Information);

		}
	}

	public void verifyPatientTherapyDocumentationPrint() throws IOException {
		CommonLib.staticWait(4);
		selectAddedNote();
		clickTherapyDocumentationPrint();
		CommonLib.staticWait(15);
		clickTherapyDocumentationPrintSubmit();
		CommonLib.staticWait(12);
		int count = getWindowHandleCount();
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		clickTherapyDocumentationPrintContents();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		if (count > 1) {
			CustomReporter.MessageLogger("Therapy Note record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the Therapy Note  in the system, please try again!", CustomReporter.status.Fail);
		}
	}

	public void verifyPatientTherapyDocumentationAudit() {
		CommonLib.staticWait(4);
		selectAddedNote();
		clickTherapyDocumentationAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Therapy Documentation records has been Auditing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Therapy Documentation Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyPatientTherapyDocumentationFlowsheets() {

		clickTherapyDocumentationFlowsheets();
		CommonLib.staticWait(4);
		boolean result = clickFlowsheetClose();
		if (result) {
			CustomReporter.MessageLogger("Therapy Documentation Flowsheets Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Flowsheets Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyPatientTherapyDocumentationCarePlans() throws InterruptedException {
		CommonLib.staticWait(3);
		clickTherapyDocumentationCarePlans();
		CommonLib.staticWait(4);
		switchToLastIframe();

		clickTherapyInfoAdd();
		setCurrentDateTherapy();
		selectTherapyDiscipline();
		setTherapyIntensity();
		setTherapyFrequency();
		setTherapyDuration();
		setTherapyComments();
		clickTherapyInfoSubmit();
		CommonLib.GetDriver().switchTo().parentFrame();
		switchToLastIframe();

		boolean result = clickCarePlanClose();
		if (result) {
			CustomReporter.MessageLogger("Therapy Documentation Care Plans Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Care Plans Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyPatientTherapyDocumentationOrderManager() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationOrderManager();
		CommonLib.staticWait(4);
		switchToFloatingFrame();
		boolean result = clickOrderManagerClose();
		if (result) {
			CustomReporter.MessageLogger("Therapy Documentation Order Manager Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Order Manager Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyPatientTherapyDocumentationDelete() {
		CommonLib.staticWait(4);
		selectAddedNote();
		int bef_count = getTherapyNotesCount();
		clickTherapyDocumentationDelete();
		setDeleteReason();
		clickDeleteSubmit();
		CommonLib.staticWait(3);
		int aft_count = getTherapyNotesCount();
		if (aft_count < bef_count) {
			CustomReporter.MessageLogger("Therapy Documentation Delete Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Delete Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyPatientTherapyDocumentationSign() throws InterruptedException {
		verifyPatientTherapyDocumentationAdd();
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		setIndividualMin();
		setEvaluationMin();
		setGroupMin();
		setConcurrentMin();
		setCoTreatmentMin();
		clickSaveButton();
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		clickTherapyDocumentationSign();
		CommonLib.staticWait(6);
		selectAddedNote();
		String user = getSigedUser();
		if (user != null) {
			CustomReporter.MessageLogger("Therapy Documentation Sign Button in the system is working properly and the note is signed by " + user + ", which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Sign Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientTherapyDocumentationAmend() {
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		clickTherapyDocumentationAmend();
		clickOKAmend();
		selectAddedNote();
		expandOpenAll();
		setAmendReason();
		clickSaveButton();
		selectAddedNote();
		expandOpenAll();
		String amend = getAmendedUser();
		if (amend != null) {
			CustomReporter.MessageLogger("Therapy Documentation Amend Button in the system is working properly and the note is amended by " + amend + ", which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation Amend Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTherapySelectionProperties() throws InterruptedException {
		clickTherapySelectionProperties();
		Iterator<WebElement> itor = getRadioButtons("displayType1");
		int j = 1;
		while (itor.hasNext()) {
			itor.next().click();
			selectDiscipline();
			for (int i = 1; i < 3; i++) {
				selectTemplate(i);
				int count = getTherapyNotesCount();
				if (count > 0) {
					CustomReporter.MessageLogger("For Therapy Documentation Selection Properties, when " + j++ + " display type radio button  is selected; the number of rows displaying as :" + count + "", CustomReporter.status.Pass);
				} else {
					CustomReporter.MessageLogger("For Therapy Documentation Selection Properties, when " + j++ + " display type radio button  is selected, there are no orders displaying", CustomReporter.status.Information);
				}
			}
		}
	}

	public void verifyRightClickTreeViewFunctions() throws InterruptedException {
		verifyRightClickTreeViewEdit();
		verifyRightClickTreeViewSign();
		verifyRightClickTreeViewAmend();
		verifyRightClickTreeViewDelete();

	}

	public void verifyRightClickTreeViewEdit() throws InterruptedException {
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(4);
		verifyPatientTherapyDocumentationAdd();
		selectAddedNote();
		CommonLib.staticWait(4);
		expandOpenAll();
		String bef_edit = getBloodPressureValue();
		rightClickTreeViewEdit();
		CommonLib.staticWait(2);
		expandOpenAll();
		selectEditBP();
		setBloodPressure();
		clickSaveButton();
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		String aft_edit = getBloodPressureValue();
		if (bef_edit != aft_edit) {
			CustomReporter.MessageLogger("Therapy Note has been edited by Right Click Edit button of Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Therapy Note by Right click Edit button of Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyRightClickTreeViewDelete() throws InterruptedException {
		verifyPatientTherapyDocumentationAdd();
		CommonLib.staticWait(4);
		selectAddedNote();
		int bef_count = getTherapyNotesCount();
		rightClickTreeViewDelete();
		CommonLib.staticWait(2);
		setDeleteReason();
		clickDeleteSubmit();
		CommonLib.staticWait(5);
		int aft_count = getTherapyNotesCount();
		if (aft_count < bef_count) {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Delete Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Delete Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyRightClickTreeViewSign() throws InterruptedException {

		CommonLib.staticWait(4);
		selectAddedNote();
		CommonLib.staticWait(2);
		rightClickTreeViewEdit();
		CommonLib.staticWait(2);
		expandOpenAll();
		setIndividualMin();
		setEvaluationMin();
		setGroupMin();
		setConcurrentMin();
		setCoTreatmentMin();
		clickSaveButton();
		CommonLib.staticWait(15);
		selectAddedNote();
		CommonLib.staticWait(5);
		// expandOpenAll();
		rightClickTreeViewSign();
		CommonLib.staticWait(4);
		selectAddedNote();
		CommonLib.staticWait(4);
		String user = getSigedUser();
		if (user != null) {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Sign Button in the system is working properly and the note is signed by " + user + ", which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Sign Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyRightClickTreeViewAmend() {
		CommonLib.staticWait(6);
		selectAddedNote();
		expandOpenAll();
		CommonLib.staticWait(4);
		rightClickTreeViewAmend();
		CommonLib.staticWait(4);
		clickOKAmend();
		selectAddedNote();
		expandOpenAll();
		setAmendReason();
		clickSaveButton();
		CommonLib.staticWait(4);
		selectAddedNote();
		expandOpenAll();
		String amend = getAmendedUser();
		if (amend != null) {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Amend Button in the system is working properly and the note is amended by " + amend + ", which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Therapy Documentation's Right Click Amend Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

}
