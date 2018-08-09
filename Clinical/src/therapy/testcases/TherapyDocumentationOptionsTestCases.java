package therapy.testcases;

import common.CommonLib;
import common.CustomReporter;
import therapy.pages.TherapyDocumentationOptionsPage;

public class TherapyDocumentationOptionsTestCases extends TherapyDocumentationOptionsPage {

	public void therapyDocumentationOptionsFunctionalities() throws InterruptedException {
		therapyDocumentationOptionsSetUp();
		therapyDocumentationCertificationRecertificationSetUp();

	}

	public void therapyDocumentationOptionsSetUp() {
		navigateToPatientTherapyDocumentation();
		navigateToTherapyDocumentationOptions();
		CommonLib.staticWait(3);
		therapyDocumentationOptionsTabSelect();
		CommonLib.staticWait(3);
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeInpat");
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeOutpat");
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeEmergency");
		therapyDocumentationOptionsTotalTreatmentTime();
		therapyDocumentationOptionsDisableTotalTreatmentTime();
		therapyDocumentationOptionsCheckbox("dispMinutesInpat");
		therapyDocumentationOptionsCheckbox("dispMinutesOutpat");
		therapyDocumentationOptionsCheckbox("dispMinutesEmergency");
		therapyDocumentationOptionsCheckbox("reqMinutesDataEntryInpat");
		therapyDocumentationOptionsCheckbox("reqMinutesDataEntryOutpat");
		therapyDocumentationOptionsCheckbox("reqMinutesDataEntryEmergency");
		therapyDocumentationOptionsCheckbox("primaryGroupDiscipline_OT");
		therapyDocumentationOptionsCheckbox("primaryGroupDiscipline_PT");
		therapyDocumentationOptionsCheckbox("primaryGroupDiscipline_RT");
		therapyDocumentationOptionsCheckbox("primaryGroupDiscipline_ST");
		therapyDocumentationOptionsCheckbox("displayTotalTreatmentTime_I");
		therapyDocumentationOptionsCheckbox("displayTotalTreatmentTimeOutpatient_O");
		therapyDocumentationOptionsCheckbox("displayTotalTreatmentTimeEmergency_E");
		therapyDocumentationOptionsRequireTotalTreatmentTime();
		therapyDocumentationOptionsCheckbox("displayTimeUntimedInpat");
		therapyDocumentationOptionsCheckbox("displayTimeUntimedOutpat");
		therapyDocumentationOptionsCheckbox("displayTimeUntimedEmergency");
		therapyDocumentationOptionsCheckbox("displayModifierInpat");
		therapyDocumentationOptionsCheckbox("displayModifierOutpat");
		therapyDocumentationOptionsCheckbox("displayModifierEmergency");
		therapyDocumentationOptionsCheckbox("displayTotalTimedInpat");
		therapyDocumentationOptionsCheckbox("displayTotalTimedOP");
		therapyDocumentationOptionsCheckbox("displayTotalTimedER");
		therapyDocumentationOptionsCheckbox("displayTotalMinutesInpat");
		therapyDocumentationOptionsCheckbox("displayTotalMinutesOP");
		therapyDocumentationOptionsCheckbox("displayTotalMinutesER");
		boolean result = clickTherapyDocumentationOptionsSubmit();
		if (result) {
			CustomReporter.MessageLogger("Therapy Documentation Options setup has been done successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to setup the Therapy Documentation Options, please try again! which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void therapyDocumentationCertificationRecertificationSetUp() throws InterruptedException {
		therapyDocumentationCertificationRecertificationTabSelect();
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeInpat1");
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeOutpat1");
		therapyDocumentationOptionsCheckbox("dispReqTreatStartStopTimeEmergency1");
		therapyDocumentationCertificationTimePeriodSet();
		therapyDocumentationRecertificationTimePeriodSet();
		String provider_name = getTherapyDocumentationCertificateProviderName();
		selectTherapyDocumentationDropDown("noOfVisit");
		selectTherapyDocumentationDropDown("physician");
		selectTherapyDocumentationDropDown("shortTermGoals");
		selectTherapyDocumentationDropDown("longTermGoals");
		selectTherapyDocumentationDropDown("treatmentPlan");
		selectTherapyDocumentationDropDown("reqPhysicianSig");
		clickTherapyDocumentationOptionsSubmit();
		if (provider_name != null) {
			CustomReporter.MessageLogger("Therapy Documentation Options Certification/Recertifications setup has been done successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to setup the Therapy Documentation Options Certification/Recertifications, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

}
