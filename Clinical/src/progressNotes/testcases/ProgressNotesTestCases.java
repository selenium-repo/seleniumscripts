package progressNotes.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.TableActions;
import historyAndPhysical.pages.HistoryAndPhysicial;
import historyAndPhysical.testcases.HnPTestCases;
import progressNotes.pages.ProgressNotesPages;

public class ProgressNotesTestCases {

	ProgressNotesPages obj;
	HistoryAndPhysicial objHis;

	static boolean navigationFlag = false;

	public ProgressNotesTestCases() {

		if (navigationFlag == false) {
			obj = new ProgressNotesPages();
			obj.gotoProgressNotes();
			obj.clickAddNewNode();
			navigationFlag = true;
		}
	}

	public void assesmentLinkTest() {
		obj = new ProgressNotesPages();
		obj.testAssesment();
	}

	public void validateSubmit() {
		obj = new ProgressNotesPages();
		obj.submit();
		String submitText = obj.getSubmitText();
		submitText = submitText.replaceFirst("Progress Note -              ", "");
		String[] submitTextSplit = submitText.split(";");
		TableActions tableObj = new TableActions();
		tableObj.validateAddition(obj.getPNList(), submitTextSplit[0]);
		obj.checkSignCheckBoxes();
		obj.clickSignAndSubmit();
		String pnText = obj.getProgressNoteText(submitTextSplit[0]);
		if (pnText.contains("Signed"))
			CustomReporter.MessageLogger("Progress note added on :" + submitTextSplit[0] + " has been signed ", status.Pass);
		else
			CustomReporter.MessageLogger("Progress note aded on :" + submitTextSplit[0] + " is not signed ", status.Fail);

		String signTimeStamp = obj.getSignSubmitStamp();
		// System.out.println(signTimeStamp + "##############");

		objHis = new HistoryAndPhysicial();
		objHis.actions("Amend");
		obj.clickOk();
		obj.amendTest();
		String ammendTimeStamp = obj.getSignSubmitStamp();
		// System.out.println(ammendTimeStamp + "############");
		obj.clickSignAndSubmit();
		obj.validateAmmend();

	}

	public void ccTestCases() {
		HnPTestCases obj = new HnPTestCases("PN");

		obj.ccAddTestCase("PN");
		obj.ccEditTestCase("PN");
		obj.ccDeleteTestCase();
	}

	public void microBiologyTestCases() {

		objHis = new HistoryAndPhysicial();
		if (!objHis.isGridOpen("OBJ"))
			objHis.clickGrid("OBJ");
		CommonLib.getElement(By.xpath("//span[text()='Microbiology Results']")).click();
		// int i = 0;
		// while (i < 3) {
		// try {
		// List<WebElement> li =
		// CommonLib.getElements(By.id("RESContainer2_titleBarNode"));
		//
		// // CommonLib.clickButton(By.id("RESContainer2"));
		// break;
		// } catch (ElementNotInteractableException e) {
		//
		// }
		// i++;
		// }
		objHis.actions("Add");
		objHis.setLabSummaryFrame();
		if (objHis.isMicrobiologyDataPresent() == true) {
			objHis.checkMicrobiologyData();
			objHis.setSubmitMicrobiology();
			objHis.setMainFrame();
			if (!objHis.isGridOpen("OBJ"))
				objHis.clickGrid("OBJ");
			if (!objHis.isGridOpen("OBJContainer2"))
				objHis.clickGrid("OBJContainer2");

			objHis.validateMicrobiologyDataOnMainScreen();

		} else {
			objHis.setCancelMicrobiology();
			objHis.setMainFrame();
			CustomReporter.MessageLogger("No microbiology record present", status.Warning);
		}
	}

	public void generalLabTestCases() {

		objHis = new HistoryAndPhysicial();
		if (!objHis.isGridOpen("OBJ"))
			objHis.clickGrid("OBJ");
		objHis.clickOnGeneralLabTestSummary();
		objHis.actions("Add");
		objHis.setLabSummaryFrame();
		if (objHis.isLabDataPresent()) {
			ArrayList<String> labList = objHis.getLabSummary();
			objHis.selectAllLabSummary();
			objHis.submitReview();
			objHis.setMainFrame();
			objHis.clickOnGeneralLabTestSummary();
			TableActions tableObj = new TableActions();
			boolean flag = true;
			for (String lab : labList) {
				flag = tableObj.validateAddition(objHis.getAddedSummaryPNList(), lab);
			}
			if (flag) {
				objHis.clickPNContainer(1);
				objHis.actions("Edit");
				objHis.setLabSummaryFrame();
				objHis.uncheckLabSummary(labList.size() - 1);
				objHis.submitReview();
				objHis.setMainFrame();
				objHis.clickOnGeneralLabTestSummary();
				tableObj.validateDeletition(objHis.getAddedSummaryPNList(), labList.get(labList.size() - 1));
			}

		} else {
			objHis.cancel();
			objHis.setMainFrame();
			CustomReporter.MessageLogger("No microbiology record present", status.Warning);
		}
	}

	public void objectiveTestCases() {

		objHis = new HistoryAndPhysicial();
		if (!objHis.isGridOpen("OBJ"))
			objHis.clickGrid("OBJ");

		objHis.actionOnGrid("PE", "Add");
		objHis.setPhysicialFrame();
		if (objHis.isReviewPanePresent("physicial")) {
			objHis.selectPhysicial("Y");
			objHis.mandate("");
			objHis.submitReview();
			objHis.setMainFrame();
			if (!objHis.isGridOpen("OBJ"))
				objHis.clickGrid("OBJ");
			if (!objHis.isGridOpen("PE"))
				objHis.clickGrid("PE");
			objHis.validatePhysicial("normal");
			objHis.actionOnGrid("PE", "Edit");
			objHis.setPhysicialFrame();
			objHis.selectPhysicial("N");
			objHis.mandate("");
			objHis.submitReview();
			objHis.setMainFrame();
			if (!objHis.isGridOpen("OBJ"))
				objHis.clickGrid("OBJ");
			if (!objHis.isGridOpen("PE"))
				objHis.clickGrid("PE");
			objHis.validatePhysicial("abnormal");
		}
	}

	public void ehrTestCases() {
		obj = new ProgressNotesPages();
		objHis = new HistoryAndPhysicial();

		obj.clickEhrArrow();
		List<WebElement> ehrList = obj.getEHRList();
		for (int i = 1; i < ehrList.size(); i++) {
			try {
				obj.clickEhr(i);
				String title = obj.getFlowSheetTitle();

				if (title.equals("ORDER ENTRY")) {
					try {
						objHis.switchFrameToOrderEntryPopUp();

						List<WebElement> ok = CommonLib.getElements(By.xpath("//*[text()='OK']"));
						ok.get(ok.size() - 1).click();

					} catch (Exception e) {

					}
				}
				objHis.setMainFrame();
				obj.closeFlowSheet();
				obj.clickEhrArrow();
				CustomReporter.MessageLogger("EHR :" + title, status.Pass);
			} catch (Exception e) {
				CustomReporter.MessageLogger("EHR Failed:", status.Fail);
			}

		}

	}

	public void flowSheetTestCases() {
		obj = new ProgressNotesPages();
		// obj.gotoProgressNotes();
		obj.clickFlowSheetArrow();
		List<WebElement> li = obj.getFlowSheets();

		for (int i = 1; i <= li.size(); i++) {
			try {
				obj.selectFlowSheet(i);

				CustomReporter.MessageLogger("Selected flow sheet: " + obj.getFlowSheetTitle(), status.Information);
				obj.closeFlowSheet();
				obj.clickFlowSheetArrow();
			} catch (Exception e) {

				obj.closeFlowSheet();
				obj.clickFlowSheetArrow();
			}
		}

		try {
			List<WebElement> ele = CommonLib.getElements(By.xpath("//div[contains(@class,'jsPanel-btn-close')]"));
			for (WebElement e : ele) {
				try {
					e.click();
				} catch (Exception ex) {
				}
			}
		} catch (Exception e) {

		}

	}

	public void subJectiveTestCases() {
		obj = new ProgressNotesPages();
		objHis = new HistoryAndPhysicial();
		objHis.actionOnGrid("SUB", "Add");
		objHis.setHistoryFrame();
		if (objHis.historyPresentIllness() == true) {
			objHis.selectHistoryIllness(true);

			objHis.submitReview();
			objHis.setMainFrame();
			if (!objHis.isGridOpen("SUB"))
				objHis.clickGrid("SUB");
			objHis.validateHIP("PN");
			objHis.setMainFrame();
			objHis.actionOnGrid("SUB", "Edit");
			objHis.setHistoryFrame();
			CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("hpiVerbiage_iframe")));
			HashTableRepository.setHash("mannualHIP", CommonLib.RandomText(1, 15));
			CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("mannualHIP"));
			// CommonLib.setData(By.id("dijitEditorBody"), "testing");
			// objHis.setMainFrame();
			// CommonLib.clickButton(By.id("tdMenuTree"));
			// objHis.selectHistoryIllness(true);
			objHis.setMainFrame();
			objHis.setHistoryFrame();
			objHis.submitReview();

			objHis.setMainFrame();
			if (!objHis.isGridOpen("SUB"))
				objHis.clickGrid("SUB");
			CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("subEditor_iframe")));
			String hipText = CommonLib.getText(By.id("dijitEditorBody"));
			if (hipText.contains(HashTableRepository.getHash("mannualHIP")))
				CustomReporter.MessageLogger("History of present illness has been added ", status.Pass);
			else {
				CustomReporter.MessageLogger("History of present illness has not been added ", status.Fail);
			}
			objHis.setMainFrame();

		} else
			objHis.emptyHIP("");
	}

	public void reviewOfSystems() {
		objHis = new HistoryAndPhysicial();
		if (!objHis.isGridOpen("SUB"))
			objHis.clickGrid("SUB");

		objHis.actionOnGrid("pnRos", "Add");
		objHis.setReviewSystemFrame();
		if (objHis.isReviewPanePresent("review")) {
			objHis.selectReview("Yes");
			objHis.mandate("review system");
			objHis.submitReview();
			objHis.setMainFrame();
			if (!objHis.isGridOpen("SUB"))
				objHis.clickGrid("SUB");
			if (!objHis.isGridOpen("pnRos"))
				objHis.clickGrid("pnRos");
			String reviewText = CommonLib.getText(By.id("rosDivContentPn"));
			// System.out.println(reviewText);
			if (reviewText.contains("Reports"))
				CustomReporter.MessageLogger("Review added successfully.: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Pass);
			else
				CustomReporter.MessageLogger("Review is not added .: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Fail);

			// objHis.validateReview("Reports");
			objHis.actionOnGrid("pnRos", "Edit");
			objHis.setReviewSystemFrame();
			objHis.selectReview("No");
			objHis.submitReview();
			objHis.setMainFrame();

			if (!objHis.isGridOpen("SUB"))
				objHis.clickGrid("SUB");

			if (!objHis.isGridOpen("pnRos"))
				objHis.clickGrid("pnRos");
			reviewText = CommonLib.getText(By.id("rosDivContentPn"));
			if (reviewText.contains("Denies"))
				CustomReporter.MessageLogger("Review edited successfully.: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Pass);
			else
				CustomReporter.MessageLogger("Review is not edited .: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Fail);

			// objHis.validateReview("Denies");
		}

	}

	public void assesmentTestCases() {

		obj = new ProgressNotesPages();

		objHis = new HistoryAndPhysicial();
		objHis.getAdmitDate();
		objHis.actionOnGrid("ASSMT", "Add");
		objHis.setDiagnosisStatus("C");
		objHis.setDiagnosisDate(objHis.getPastDate());
		objHis.setDiagnosisTIme();
		objHis.setRandomDiagnosis();
		objHis.setMainFrame();
		boolean validationFlag = objHis.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosis"), "PN");
		if (validationFlag == true) {
			objHis.clickDiagnosis(HashTableRepository.getHash("diagnosisDate"));
			objHis.actions("Edit");
			objHis.setDiagnosisStatus("A");
			objHis.setEditDiagnosisReason();
			objHis.submitDiagnosis();
			objHis.setMainFrame();
		}

	}

	public void planTestCases() {
		HnPTestCases objHnP = new HnPTestCases("PN");
		obj = new ProgressNotesPages();

		objHnP.planTestCases("");
	}

}
