package consultationNote.TestCases;

import org.openqa.selenium.By;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import common.TableActions;
import consultationNote.pages.CnPages;
import historyAndPhysical.pages.HistoryAndPhysicial;
import historyAndPhysical.testcases.HnPTestCases;

public class CnTestCases {

	HistoryAndPhysicial hnpObj = new HistoryAndPhysicial();
	CnPages cnObj = new CnPages();
	static boolean isNavigated = false;

	public CnTestCases() {
		if (isNavigated == false) {
			MenuNavigation.menuNav("ConsultationNotes");
			hnpObj.clickADD("Add");
			cnObj.setConsultationFrame();
			isNavigated = true;
		}

	}

	public void ccTestCases() {
		boolean flag = true;
		hnpObj.actionOnGrid("CC_CN", "Add");
		hnpObj.changeFrameToPopUpCC();
		hnpObj.clickAddPatient_sPhysician();
		hnpObj.generateRandomLetter();
		hnpObj.choosePhysician();
		while (flag) {
			hnpObj.selectPhysicianType();
			flag = hnpObj.checkIfSamePhysicianWithSameRoleExists();
		}
		boolean check = hnpObj.validatePopUp();
		hnpObj.clickCloseCC();
		boolean check2 = hnpObj.validate("CN");
		if (check && check2) {
			CustomReporter.MessageLogger("Physician has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been added", status.Fail);

	}

	public void hnpConsultationTestCases() {
		if (!hnpObj.isGridOpen("HNP"))
			hnpObj.clickGrid("HNP");

		HnPTestCases obj = new HnPTestCases("CN");
		// obj.chiefComplaintTestCases();
		obj.historyOfPresentIllness("CN");
		obj.pastMedicalHistoryTestCases("CN");
		obj.pastSurgicalHistoryTestCases("CN");
		obj.FamilyMedicalHistoryTestCases("CN");
		// obj.immunizationTestCases("CN");
		obj.homeMedicationTestCases("CN");
		obj.allergiesAdd("CN");
		obj.allergiesEdit("CN");
		obj.allergiesDelete();
		obj.reviewSystemTestCases("CN");
		obj.physicialExamTestCases("CN");
		obj.impressionTestCases("CN");
		// obj.planTestCases("CN");
		// obj.ccAddTestCase("CN");
		// obj.ccEditTestCase("CN");
		// obj.ccDeleteTestCase();
	}

	public void recommendationsTestCases() {

		hnpObj.clickOnOrderEntryLink();
		hnpObj.switchFrameToOrderEntryPopUp();
		boolean check = hnpObj.getTitleOrderEntryPopUp();
		hnpObj.setMainFrame();
		cnObj.setConsultationFrame();

		if (check) {
			CustomReporter.MessageLogger("The title is 'Order Entry'", status.Pass);
		} else
			CustomReporter.MessageLogger("The title doesnt match", status.Fail);

		cnObj.setRecommendationFrame();
		cnObj.setRecommendation(CommonLib.RandomText(1, 10));
		hnpObj.setMainFrame();
		cnObj.setConsultationFrame();
	}

	public void concludingStatementTestCases() {
		hnpObj.actionOnGrid("CS", "Add");
		hnpObj.setHistoryFrame();
		if (hnpObj.historyPresentIllness() == true) {
			hnpObj.selectHistoryIllness(true);
			hnpObj.submitReview();
			hnpObj.setMainFrame();
			hnpObj.validateHIP("CN");
			hnpObj.setMainFrame();
			hnpObj.actionOnGrid("HPI", "Edit");
			hnpObj.setHistoryFrame();
			hnpObj.selectHistoryIllness(false);
			hnpObj.submitReview();
			hnpObj.setMainFrame();
		} else {
			hnpObj.emptyHIP("CN");
			cnObj.validateConcludingStatement(HashTableRepository.getHash("mannualHIP"));
		}

	}

	public void impressionTestCases() {
		// hnpObj.actions("Add");
		// cnObj.setConsultationFrame();

		hnpObj.getAdmitDate();
		CommonLib.clickButton(By.id("IMP_titleBarNode"));
		hnpObj.actions("Add");
		// hnpObj.actionOnGrid("IMP", "Add");
		hnpObj.setDiagnosisStatus("A");
		hnpObj.setDiagnosisDate(hnpObj.getPastDate());
		hnpObj.setDiagnosisTIme();
		hnpObj.setRandomDiagnosis();

		// hnpObj.submitDiagnosis();
		// hnpObj.verifyErrorDiagnosis();
		TableActions tableObj = new TableActions();
		tableObj.validateAddition(cnObj.getImpressionList(), HashTableRepository.getHash("diagnosisDate"));
		hnpObj.clickImpression(HashTableRepository.getHash("diagnosisDate"), "");
		hnpObj.actions("Edit");
		hnpObj.setDiagnosisStatus("C");
		hnpObj.setEditDiagnosisReason();
		hnpObj.submitDiagnosis();
		hnpObj.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosis"), "medical");
		hnpObj.clickImpression(HashTableRepository.getHash("diagnosisDate"), "");
		hnpObj.actions("Resolve");
		tableObj.validateDeletition(cnObj.getImpressionList(), HashTableRepository.getHash("diagnosisDate"));
		// hnpObj.actionOnGrid("DIAG", "Add");
		// hnpObj.setDiagnosisStatus("A");
		// hnpObj.setDiagnosisDate(hnpObj.getPastDate());
		// hnpObj.setDiagnosisTIme();
		// hnpObj.setRandomDiagnosis();
		//
		// // hnpObj.submitDiagnosis();
		// hnpObj.clickImpression(HashTableRepository.getHash("diagnosisDate"));
		// hnpObj.actions("Inactivate");
		// hnpObj.setInactivateReason();
		// hnpObj.submitInactivate();
		// tableObj.validateDeletition(cnObj.getImpressionList(),
		// HashTableRepository.getHash("diagnosisDate"));

	}

	public void ccAddTestCase() {
		boolean flag = true;
		hnpObj.actionOnGrid("CC_CN", "Add");
		hnpObj.changeFrameToPopUpCC();
		// obj.deleteAllPatient_sPhysicians();
		hnpObj.clickAddPatient_sPhysician();
		hnpObj.generateRandomLetter();
		hnpObj.choosePhysician();
		while (flag) {
			hnpObj.selectPhysicianType();
			flag = hnpObj.checkIfSamePhysicianWithSameRoleExists();
		}
		boolean check = hnpObj.validatePopUp();
		hnpObj.clickCloseCC();
		boolean check2 = hnpObj.validate("CN");
		if (check && check2) {
			CustomReporter.MessageLogger("Physician has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been added", status.Fail);

	}

	//
	// // public boolean validate() {
	// // cnObj.setConsultationFrame();
	// // boolean status = false;
	// // List<WebElement> li =
	// //
	// CommonLib.getElements(By.xpath("//div[@id='CC_HPGrid1']/div[2]//table/tbody/tr/td[3]"));
	// // List<WebElement> li2 =
	// //
	// CommonLib.getElements(By.xpath("//div[@id='CC_HPGrid1']/div[2]//table/tbody/tr/td[4]"));
	// // for (int i = 0; i < li.size(); i++) {
	// // String modified =
	// // HashTableRepository.getHash("SelectedPhysicianName").replaceAll(" ",
	// "");
	// // String modified2 = li.get(i).getText().replaceAll(" ", "");
	// // if (modified2.equals(modified)) {
	// // if
	// //
	// (li2.get(i).getText().equals(HashTableRepository.getHash("SelectedPhysicianType")))
	// // {
	// // status = true;
	// //
	// // }
	// // }
	// // }
	// // return status;
	// // }
	//
	public void ccEditTestCase() {
		hnpObj.clickEditForTestcase();
		boolean check = hnpObj.validateEdit("CN");
		if (check) {
			CustomReporter.MessageLogger("Physician has been edited", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been edited", status.Fail);

	}

	//
	public void ccDeleteTestCase() {
		hnpObj.clickEditForTestcase();
		hnpObj.clickDeleteCC();
		hnpObj.clickOKForDelete();
		boolean check = hnpObj.validatePopUp();
		hnpObj.clickCloseCC();
		boolean check2 = hnpObj.validate("CN");
		if (check == false && check2 == false) {
			CustomReporter.MessageLogger("Physician has been deleted", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been deleted", status.Fail);
	}

	public void concludingStatementTestcase() {
		hnpObj.actionOnGrid("CS", "Add");
		cnObj.setConcludingFrame();
		cnObj.setLibraryOfStatement();
		cnObj.validateConcludingStatement();
		// cnObj.validateConcludingStatement("abc");

	}

	public void getHnPData() {
		// cnObj.setConsultationFrame();
		// cnObj.openAllHnP();
		// cnObj.getHistoryOfPresentIllness();
		// cnObj.getCheifComplaint();
		// cnObj.getHistoryOfPresentIllness();
		cnObj.getHistories("Past Medical History", "diagnosisDate");
		cnObj.getHistories("Past Surgical History", "procedureDate");
		cnObj.getHistories("Family Medical History", "selectedFamilyMember");
		cnObj.getHistories("Social History", "selectedEducation");

	}

	public void submitTestcases() {

		cnObj.addCN();
		cnObj.setCNFrame();
		cnObj.setInfo();
		hnpObj.setMainFrame();
		int sizeAfter = cnObj.size();
		int j = Integer.parseInt(HashTableRepository.getHash("sizeBefore"));
		int diff = sizeAfter - j;
		if (diff == 1) {
			CustomReporter.MessageLogger("Consultation Note is submitted", status.Pass);
		} else {
			CustomReporter.MessageLogger("Consultation Note is not submitted", status.Fail);
		}
		// int i = cnObj.size();
		// HashTableRepository.setHash("sizeBefore", Integer.toString(i));
		cnObj.editCN();
		cnObj.setCNFrame();
		cnObj.signandSubmit();
		hnpObj.setMainFrame();
		cnObj.verifySignandSubmit();
		cnObj.clickDisplaySelected();
		cnObj.setCNFrame();
		cnObj.amendCN();
		cnObj.signandSubmit();
		hnpObj.setMainFrame();
		cnObj.clickDisplaySelected();
		cnObj.setCNFrame();
		cnObj.verifyAmend();
	}

}
