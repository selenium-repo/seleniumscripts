package DischargeSummaryPhyscDoc.testcases;

import DischargeSummaryPhyscDoc.pages.DischargeSummaryPhyscDocPage;
import historyAndPhysical.pages.HistoryAndPhysicial;
import historyAndPhysical.testcases.HnPTestCases;

public class DischargeSummaryPhyscDocTestCase {

	HistoryAndPhysicial hnpObj = new HistoryAndPhysicial();

	DischargeSummaryPhyscDocPage dSObj = new DischargeSummaryPhyscDocPage();
	static boolean isNavigated = false;

	public DischargeSummaryPhyscDocTestCase() {
		if (isNavigated == false) {
			dSObj.menuNav();
			dSObj.setMainFrame();
			isNavigated = true;
		}
	}

	public void dischargeConditionTestcases() {
		// dSObj.menuNav();
		// dSObj.setMainFrame();
		hnpObj.clickGrid("DCON");
		if (!hnpObj.isGridOpen("DCON")) {
			hnpObj.clickGrid("DCON");
		}
		dSObj.setDischargeCondition();
		if (!hnpObj.isGridOpen("INS")) {
			hnpObj.clickGrid("INS");
		}
		dSObj.setActivities();
		dSObj.getActivityList();
		dSObj.setDiets();
		// dSObj.setPatientEducationDetail();
		dSObj.setAdditionalInstruction();
	}

	public void historyOfPresentIllness() {
		HnPTestCases obj = new HnPTestCases("");
		obj.historyOfPresentIllness("DS");
	}

	public void ccTestCase() {
		HnPTestCases obj = new HnPTestCases("");
		obj.ccAddTestCase("DS");
		obj.ccEditTestCase("DS");
		obj.ccDeleteTestCase();
	}

	public void diagnosisTestCases() {
		HnPTestCases obj = new HnPTestCases("");
		obj.impressionTestCases("DS");
	}

	public void procedureTestCases() {
		HnPTestCases obj = new HnPTestCases("");
		obj.pastSurgicalHistoryTestCases("PROC");
	}

	public void consulationTestCases() {
		hnpObj.actionOnGrid("CONS", "Add");
		dSObj.checkforErrorConsulationNote();

	}

	public void commentTestCase() {
		
		hnpObj.clickGrid("CMT");
		if (!hnpObj.isGridOpen("CMT")) {
			hnpObj.clickGrid("CMT");
		}
		dSObj.setComment("cmt");
	}

	public void hospitalCareTestCase() {
	
		hnpObj.clickGrid("HC");
		if (!hnpObj.isGridOpen("HC")) {
			hnpObj.clickGrid("HC");
		}
		dSObj.setComment("hc");
	}

	public void followUpTestcases() {
		hnpObj.clickGrid("FLW");
		if (!hnpObj.isGridOpen("FLW")) {
			hnpObj.clickGrid("FLW");
			dSObj.setFollowUp();
		}
	}

	public void dischargeMedication() {
		HnPTestCases obj = new HnPTestCases("");
		// hnpObj.clickGrid("DMED");
		// if (!hnpObj.isGridOpen("DMED")) {
		// hnpObj.clickGrid("DMED");
		// }
		obj.homeMedicationTestCases("DS");
	}

	public void dischargePhysicalExam() {
		HnPTestCases obj = new HnPTestCases("");
		obj.microbiologyResult("DS");
		// obj.radioLogyTestCases();
		obj.generalLabTestCases("DS");
		obj.labRadNotes("DS");
	}

	public void submit() {
		dSObj.submitAndVerify();
	}

}
