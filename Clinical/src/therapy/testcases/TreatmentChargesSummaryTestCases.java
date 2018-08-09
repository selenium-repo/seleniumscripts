package therapy.testcases;

import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.HashTableRepository;
import therapy.pages.TreatmentChargesSummaryPage;

public class TreatmentChargesSummaryTestCases extends TreatmentChargesSummaryPage {

	public void verifyTreatmentChargesSummaryFunctionalities() throws InterruptedException, IOException {

		verifyTreatmentChargesSummaryAdd();
		verifyTreatmentChargesSummaryAudit();
		verifyTreatmentChargesSummaryFlowsheets();
		verifyTreatmentChargesSummaryCarePlans();
		verifyTreatmentChargesSummaryOrderManager();
		verifyTreatmentChargesSummaryFilter();

	}

	public void verifyTreatmentChargesSummarySelectionProperties() throws InterruptedException {
		selectTreatmentChargesSummaryTab();
		CommonLib.staticWait(3);
		clickTreatmentChargesSummarySelectionProperties();
		Iterator<WebElement> itor = getRadioButtons("treatChargeDisplayType");
		int j = 1;
		while (itor.hasNext()) {
			itor.next().click();
			selectTreatmentChargesSummaryDiscipline();
			for (int i = 1; i < 3; i++) {
				selectTreatmentChargesSummaryTemplate(i);
				int count = getTreatmentChargesCount();
				if (count > 0) {
					CustomReporter.MessageLogger("For Treatment Charges Summary Selection Properties, when " + j++ + " display type radio button  is selected; the number of rows displaying as :" + count + "", CustomReporter.status.Pass);
				} else {
					CustomReporter.MessageLogger("For Treatment Charges Summary Selection Properties, when " + j++ + " display type radio button  is selected, there are no orders displaying", CustomReporter.status.Information);
				}
			}
		}
	}

	public void verifyTreatmentChargesSummaryAdd() throws InterruptedException {
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(2);
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

		clickAddTreatmentCharges();
		checkTreatmentChargesList();
		clickTreatmentChargesSubmit();
		setTreatmentChargesMinute();
		setTreatmentChargesUnit();
		clickModifireIcon();
		selectThreatmentChargesModifier();
		moveSelectedModifier();
		clickModifierSubmit();

		setBloodPressure();
		clickTherapyNotesSignAndSubmit();
		CommonLib.staticWait(5);
		selectTreatmentChargesSummaryTab();
		CommonLib.staticWait(4);
		int count = getTreatmentChargesCount();
		if (checkTreatmentChargesTemplate() && count > 0) {
			String template = HashTableRepository.getHash("template_code");
			for (int i = 1; i < 10; i++) {
				String data = getTreatmentChargesColumnsData(i);
			}

			CustomReporter.MessageLogger("Therapy Template  " + template + " Added in Therapy Notes is displaying in Treatment Charges Summary tab successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Therapy Template in Treatment Charges Summary tab, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTreatmentChargesSummaryFilter() {
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationFilterCheckBox();
		clickTherapyDocumentationFilterSubmit();
		int bef_count = getTherapyNotesCount();
		clickTherapyDocumentationFilter();
		checkTherapyDocumentationFilterCheckBox();
		clickTherapyDocumentationFilterSubmit();
		int aft_count = getTherapyNotesCount();
		if (aft_count != bef_count) {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's records has been filtered to Patient Therapy Documentation screen in system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to filter Treatment Charges Summary Tab's records in Patient Therapy Documentation, please try again! which is not as expected", CustomReporter.status.Information);

		}
	}

	public void verifyTreatmentChargesSummaryAudit() {

		CommonLib.staticWait(4);
		clickTherapyDocumentationAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's records has been Auditing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Treatment Charges Summary Tab's records, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTreatmentChargesSummaryFlowsheets() {

		clickTherapyDocumentationFlowsheets();
		CommonLib.staticWait(4);
		boolean result = clickFlowsheetClose();
		if (result) {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Flowsheets Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Flowsheets Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyTreatmentChargesSummaryCarePlans() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationCarePlans();
		CommonLib.staticWait(4);
		switchToLastIframe();
		boolean result = clickCarePlanClose();
		if (result) {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Care Plans Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Care Plans Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyTreatmentChargesSummaryOrderManager() {
		CommonLib.staticWait(3);
		clickTherapyDocumentationOrderManager();
		CommonLib.staticWait(4);
		switchToFloatingFrame();
		boolean result = clickOrderManagerClose();
		if (result) {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Order Manager Button in the system is working properly, which is as expected", CustomReporter.status.Pass);
			setHomeFrame();
		} else {
			CustomReporter.MessageLogger("Treatment Charges Summary Tab's Order Manager Button in the system is not working properly, which is not as expected", CustomReporter.status.Fail);
		}
	}

}
