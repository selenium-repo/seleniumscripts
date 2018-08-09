package historyAndPhysical.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import consultationNote.pages.CnPages;

public class HistoryAndPhysicial {

	static boolean isNavigated = false;

	public void gotoHistoryAndPhysicial() {
		if (isNavigated == false) {
			MenuNavigation.menuNav("HistoryPhysicial");
			setMainFrame();
			isNavigated = true;
		}
	}

	public void clickOnAllCheckBoxesBeforeSign() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//label[text()='Physician Reviewed']/..//div/input"));
		for (WebElement e : li) {
			if (!e.isSelected()) {
				e.click();
			}
		}
	}

	public void setMainFrame() {
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void actions(String action) {

		CommonLib.clickButton(By.xpath("//*[@id='" + action.replace("-", "").toLowerCase() + "_label'  and (contains(text(),'" + action + "'))]"));

		// span[@id='lblPassword' and (contains(text(),'Password:'))]
	}

	public void clickADD(String action) {
		try {
			CommonLib.changeimplicitwait(30);
			WebElement button = CommonLib.getElement(By.xpath("//*[@id='" + action.replace("-", "").toLowerCase() + "_label'  and (contains(text(),'" + action + "'))]"));
			button.click();
		} catch (Exception e) {

		}
	}

	public void clickGrid(String text) {
		CommonLib.clickButton(By.xpath("//*[@id='" + text + "_titleBarNode']"));
	}

	public boolean isGridOpen(String text) {
		boolean isOpen = false;
		String classText = CommonLib.getElement(By.xpath("//*[@id='" + text + "_titleBarNode']")).getAttribute("class");
		if (classText.contains("dijitOpen"))
			isOpen = true;

		return isOpen;
	}

	public String getTobaccoText(String index) {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid" + index + "']//tbody//td"));
	}

	public void setDrugAmount() {
		try {
			CommonLib.selectDojoListValue("reasonListS", "");
		} catch (Exception e) {

		}
		HashTableRepository.setHash("socialDrugAmount", Integer.toString(CommonLib.generateRandom(1, 10)));
		CommonLib.setData(By.id("amountSF1"), HashTableRepository.getHash("socialDrugAmount"));
	}

	public void setTobaccoFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//*[contains(@src,'substance-properties.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setGeneralSocial() {
		setGeneralFrame();
		HashTableRepository.setHash("numChiildren", Integer.toString(CommonLib.generateRandom(1, 5)));
		CommonLib.setData(By.id("numOfChildren"), HashTableRepository.getHash("numChiildren"));
		try {
			String education = CommonLib.selectDojoListValue("educationList", "");
			HashTableRepository.setHash("selectedEducation", education);
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("submit_label"));
	}

	public void setGeneralFrame() {
		List<WebElement> iframe = CommonLib.getElements(By.xpath("//iframe[contains(@src,'social-history.action?')]"));
		CommonLib.setFrameFromCurrent(iframe.get(iframe.size() - 1));
	}

	public void setGeneralEducation() {
		try {
			CommonLib.getElement(By.xpath("//input[@id='citizenshipList']"));
			String education = CommonLib.selectDojoListValue("citizenshipList", "");
			HashTableRepository.setHash("selectedEducation", education);
		} catch (Exception e) {
		}
	}

	public void setAlcohol() {
		CommonLib.clickButton(By.id("formerUseA"));
		try {
			String selectedAl = CommonLib.selectDojoListValue("alcoholChoiceF1", "");
			HashTableRepository.setHash("selectedAlcohol", selectedAl);
		} catch (Exception e) {
		}
		setAlAmount();
		submitAl();
	}

	public void submitAl() {
		CommonLib.clickButton(By.id("submitA_label"));
	}

	public void setAlAmount() {
		try {
			CommonLib.selectDojoListValue("reasonListA", "");
		} catch (Exception e) {

		}

		HashTableRepository.setHash("AlAmount", Integer.toString(CommonLib.generateRandom(1, 10)));
		CommonLib.setData(By.id("amountAF1"), HashTableRepository.getHash("AlAmount"));
	}

	public void submitDrugSocial() {
		CommonLib.clickButton(By.id("submitS_label"));
	}

	public void setDrug() {
		CommonLib.clickButton(By.id("formerUseS"));
		try {
			String selectedSocialDrug = CommonLib.selectDojoListValue("socialRecChoiceF1", "");
			HashTableRepository.setHash("selectedSocialDrug", selectedSocialDrug);
		} catch (Exception e) {
		}
		setDrugAmount();
		submitDrugSocial();
	}

	public String getSocialText() {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid1']//tbody//td"));
	}

	// }

	public void setAdditionalComplaints(String data) {
		CommonLib.setData(By.id("ccEditor"), data);
		HashTableRepository.setHash("additionalComplaints", data);
	}

	public void actionOnGrid(String text, String action) {

		clickGrid(text);
		actions(action);
	}

	public String getChiefComplaintValue() {
		return CommonLib.getElement(By.id("dataPointCC")).getAttribute("value");
	}

	public void submitChiefComplaint() {
		CommonLib.clickButton(By.id("buttonSubmitChiefComp_label"));
	}

	public void deSelectChiefComplaint(String index) {
		CommonLib.clickButton(By.id("chiefComplaint" + index));
	}

	public void selectChiefComplaint(String key) {
		List<WebElement> chiedComplaints = getChiefComplaintList();
		String checkBoxText = "";
		int index = 0;
		boolean complaintNotSelected = true;
		while (complaintNotSelected) {
			index = CommonLib.generateRandom(1, chiedComplaints.size() - 2);
			if (!CommonLib.getElement(By.xpath("//*[@id='chiefComplaint" + index + "']/..")).getAttribute("class").contains("dijitChecked")) {
				checkBoxText = CommonLib.getText(By.xpath("//*[@id='chiefComplaint" + index + "']/../..//label"));

				CommonLib.clickButton(By.id("chiefComplaint" + index));
				complaintNotSelected = false;
				break;
			}

		}

		HashTableRepository.setHash("selectedChiefComplaintIndex" + key, Integer.toString(index));
		HashTableRepository.setHash("selectedChiefComplaint" + key, checkBoxText);
	}

	public List<WebElement> getChiefComplaintList() {
		return CommonLib.getElements(By.xpath("//*[contains(@id,'chiefComplaint') and(@role='checkbox')]"));
	}

	public void clickChiefComplaint() {
		CommonLib.clickButton(By.id("chiefComplaintBtn_label"));
	}

	public void getAdmitDate() {
		String admitDateTime = CommonLib.getText(By.xpath("//*[text()='Admit Date:']//following-sibling::td[1]"));
		HashTableRepository.setHash("AdmitDate", admitDateTime.split(" ")[0]);
	}

	public void setDiagnosisStatus(String status) {
		if (status.equals("")) {
			List<WebElement> statusList = CommonLib.getElements(By.xpath("//*[contains(@id,'problemStatus_')]"));
			int random = CommonLib.generateRandom(0, statusList.size() - 3);

			status = statusList.get(random).getAttribute("value");
			statusList.get(random).click();

		} else
			CommonLib.clickButton(By.id("problemStatus_" + status + "_Diag"));
		HashTableRepository.setHash("selectedDiagnosisStatus", status);
	}

	public void setDiagnosisDate(String data) {
		CommonLib.setData(By.id("diagnosisDate"), data);
		HashTableRepository.setHash("diagnosisDate", data);
	}

	public void clickImpression(String date, String ds) {

		if (ds.equals("DS")) {
			CommonLib.clickButton(By.xpath("//*[@id='DDIAGGrid1']//div[2]// tbody/tr/td[text()='" + date + "'] "));
		} else
			CommonLib.clickButton(By.xpath("//*[@id='DIAGGrid1']//div[2]// tbody/tr/td[text()='" + date + "'] "));
	}

	public List<WebElement> getImpressionList(String gridName) {
		return CommonLib.getElements(By.xpath("//*[@id='" + gridName + "Grid1']//div[2]//tbody/tr/td[5]"));
	}

	public List<WebElement> getImpressionListDS(String gridName) {
		return CommonLib.getElements(By.xpath("//*[@id='" + gridName + "Grid1']//div[2]//tbody/tr/td[1]"));
	}

	public void submitDiagnosis() {
		CommonLib.clickButton(By.id("buttonSubmitDiagnosis_label"));
	}

	public void setProcedureMannual() {

		CommonLib.setData(By.id("freeTextProcedure"), CommonLib.RandomText(1, 10));

	}

	public void setProcedureEdit() {
		try {
			CommonLib.selDojoListValue("procedureList", CommonLib.generateRandom(1, 10));
		} catch (Exception e) {

		}
	}

	public void setProcedure() {
		try {

			String selectedProcedure = CommonLib.selectDojoListValue("procedureList", "");
			if (selectedProcedure.equals(""))
				selectedProcedure = CommonLib.selectDojoListValue("procedureList", "");
			HashTableRepository.setHash("selectedProcedure", selectedProcedure);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setProcedurePhysician() {
		try {
			String selectedProcedurePhysician = CommonLib.selectDojoDropDownByKeyDownNumber("performingPhysicianList", 2);
			// String selectedProcedurePhysician =
			// CommonLib.selectDojoListValue("performingPhysicianList", "");
			HashTableRepository.setHash("selectedProcedurePhysician", selectedProcedurePhysician);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setProcedureTime(String Time) {
		CommonLib.setData(By.id("procedureStartTime"), Time);
	}

	public void setProcedureDate(String data) {
		CommonLib.setData(By.id("procedureStartDate"), data);
		HashTableRepository.setHash("procedureDate", data);
	}

	public void setRandomDiagnosis() {
		boolean flag = true;
		// try {
		// List<WebElement> te = CommonLib.getElements(By.id("diagnosisList"));
		// System.out.println(te.size());
		// String selectedDiagnosis =
		// CommonLib.selectDojoListValue("diagnosisList",
		// "");
		// for (String diagnosis : list) {
		// if (selectedDiagnosis.equals(diagnosis)) {
		//
		// }
		// }
		// HashTableRepository.setHash("selectedDiagnosis", selectedDiagnosis);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			while (CommonLib.getElement(By.xpath("//input[@id='encounterDiagnosisList']")).getAttribute("value").isEmpty()) {
				CommonLib.selectDojoListValue("encounterDiagnosisList", "");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (CommonLib.getElement(By.xpath("//input[@id='severityList']")).getAttribute("value").isEmpty()) {
				CommonLib.selectDojoListValue("severityList", "");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (CommonLib.getElement(By.xpath("//input[@id='diagnosingPhysicianList']")).getAttribute("value").isEmpty()) {
				CommonLib.selectDojoDropDownByKeyDownNumber("diagnosingPhysicianList", 2);
				// CommonLib.selectDojoListValue("diagnosingPhysicianList", "");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (flag) {
			String selectedDiagnosis = "";
			try {
				Thread.sleep(2000);

				selectedDiagnosis = CommonLib.selectDojoListValue("diagnosisList", "");
				if (selectedDiagnosis.equals(""))
					selectedDiagnosis = CommonLib.selectDojoListValue("diagnosisList", "");
				HashTableRepository.setHash("selectedDiagnosis", selectedDiagnosis);
				submitDiagnosis();
				flag = checkErrorBoxDiagnosis();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void verifyErrorDiagnosis() {
		try {
			CommonLib.getElement(By.id("errorDialog"));
			CommonLib.clickButton(By.xpath("//*[text()='OK']"));
			String selectedDiagnosis = CommonLib.selectDojoListValue("diagnosisList", "");
			HashTableRepository.setHash("selectedDiagnosis", selectedDiagnosis);
			submitDiagnosis();
		} catch (Exception e) {

		}
	}

	public boolean checkErrorBoxDiagnosis() {
		boolean flag = false;
		try {
			CommonLib.getElement(By.id("errorDialog"));
			CommonLib.clickButton(By.xpath("//*[text()='OK']"));
			flag = true;
		} catch (Exception e) {

		}
		return flag;

	}

	public List<WebElement> getPMHdateList() {
		return CommonLib.getElements(By.xpath("//*[@id='PMHGrid1']//div[@id='dojox_grid__View_1']//div[contains(@class,'dojoxGridRow')]//tr/td[1]"));
	}

	public List<WebElement> getDateDiagnosis() {
		return CommonLib.getElements(By.xpath("//div[@id='DDIAGGrid1']/div[2]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[1]"));
	}

	public List<WebElement> getPMHdateListCN() {
		return CommonLib.getElements(By.xpath("//div[@id='PMHGrid1']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[1]"));
	}

	public List<WebElement> getPastSurgicalList() {
		return CommonLib.getElements(By.xpath("//*[@id='PSHGrid1']//div[2]//tbody/tr/td[1]"));
	}

	public List<WebElement> getProcList() {
		return CommonLib.getElements(By.xpath("//div[@id='PROCGrid1']/div[2]//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[1]"));
	}

	public void submitProcedure() {
		CommonLib.clickButton(By.id("buttonSubmitProcedure_label"));
		CommonLib.changeimplicitwait(3);
		try {
			WebElement button = CommonLib.getElement(By.xpath("//span[contains(text(),'Override Warning')]"));
			button.click();
			// CommonLib.clickButton(By.id("dijit_form_Button_56_label"));
		} catch (Exception e) {
		}
		CommonLib.resetImplicitWait();
	}

	public void setDiagnosisTIme() {

		CommonLib.setData(By.id("diagnosisTime"), CommonLib.systemTime("HH:mm"));
		// try {
		// CommonLib.selectDojoListValue("diagnosisTime", );
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void validateEditFMH(String family) {
		String status = CommonLib.getText(By.xpath("//*[@id='FMHGrid1']//div[2]//tbody/tr/td[3]/span[text()='" + family + "']/..//following-sibling::td[1]"));
		if (status.equals("Yes"))
			CustomReporter.MessageLogger("Adoption edited to yes", common.CustomReporter.status.Pass);
		else
			CustomReporter.MessageLogger("Adoption is not edited to yes", common.CustomReporter.status.Fail);
	}

	public void editImmunization() {
		try {
			CommonLib.selectDojoListValue("editReasonList", "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editFMH() {

		try {
			CommonLib.selectDojoListValue("editReasonList", "");
			CommonLib.clickButton(By.id("ADOPTED_Y"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inactivateFamilyMember() {
		try {
			CommonLib.selectDojoListValue("removalFMHReasonList", "");
		} catch (Exception e) {

		}
	}

	public void setEditDiagnosisReason() {
		try {
			CommonLib.selectDojoListValue("diagnosisEditReasonList", "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickDiagnosis(String date) {

		// if (!isGridOpen("PHM"))
		// clickGrid("PHM");

		CommonLib.clickButton(By.xpath("//div[@id='PMHGrid1Border']//td[text()='" + date + "']"));
	}

	public void validateImpression(String date, String description) {

	}

	public boolean validateDiagnosis(String date, String description, String category) {
		boolean valaidationFlag = false;
		String diagnosis = "";
		if (category.equals("impression"))
			diagnosis = CommonLib.getText(By.xpath("//*[@id='DIAGGrid1']//div[2]/tbody/tr/td[text()='" + date + "']/..//td[3]/span"));
		else if (category.equals("PN"))
			diagnosis = CommonLib.getText(By.xpath("//div[@id='ASSMTGrid1']//td[text()='" + date + "']/..//td[3]/span"));

		else {
			try {

				diagnosis = CommonLib.getText(By.xpath("//div[@id='PMHGrid1Border']//td[text()='" + date + "']//following-sibling::td[3]/span"));
			} catch (Exception e) {

			}
			if (diagnosis.contains(description)) {
				CustomReporter.MessageLogger("Diagnosis has been added with description :" + diagnosis, status.Pass);
				valaidationFlag = true;
			}

			else {
				CustomReporter.MessageLogger("Diagnosis with description : " + diagnosis + " is not added", status.Fail);
				valaidationFlag = false;
			}

		}
		return valaidationFlag;
	}

	public void validateInactivate(String diagnosis) {
		String actualDiagnosis = CommonLib.getText(By.id("problemForInactivationDIV"));
		if (actualDiagnosis.equals(diagnosis))
			CustomReporter.MessageLogger("Diagnosis on Inactivate pop up is :" + actualDiagnosis, status.Pass);
		else
			CustomReporter.MessageLogger("Diagnosis on Inactivate pop up is :" + diagnosis + " but it should be :" + actualDiagnosis, status.Fail);
		setInactivateReason();
		submitInactivate();
		// CommonLib.clickButton(By.id("buttonSubmitInactivation_label"));
	}

	public void submitInactivate() {
		CommonLib.clickButton(By.id("buttonSubmitInactivation_label"));
	}

	public void setInactivateReason() {
		try {
			CommonLib.selectDojoListValue("inactivateEditReasonList", "");
		} catch (Exception e) {

		}
	}

	public void clickSubmitGeneric() {
		List<WebElement> submitList = CommonLib.getElements(By.xpath("//*[contains(@id,'Submit')]"));
		submitList.get(submitList.size() - 1).click();
	}

	public void validateResolve(String date) {
		String diagnosis = CommonLib.getText(By.xpath("//div[@id='PMHGrid1Border']//td[text()='" + date + "']//following-sibling::td[1]"));
		if (diagnosis.contains("Yes"))
			CustomReporter.MessageLogger("Diagnosis  with description :" + diagnosis + " has been reolved", status.Pass);
		else
			CustomReporter.MessageLogger("Diagnosis with description : " + diagnosis + " is not resolved", status.Fail);

	}

	public String getPastDate() {

		int difference = CommonLib.numberOfDaysTo(HashTableRepository.getHash("AdmitDate"), CommonLib.getcurrentdatetime("MM/dd/YYYY", 0, "", 0));
		difference = difference + CommonLib.generateRandom(1, 50);
		return CommonLib.getcurrentdatetime("MM/dd/YYYY", -difference, "", 0);

	}

	public void addFamilyDisease() {
		try {
			HashTableRepository.setHash("SelectedFamilyDisease", CommonLib.selectDojoListValue("diseaseList", ""));
		} catch (Exception e) {

		}
		CommonLib.setData(By.id("ageatDiagnosis"), Integer.toString(CommonLib.generateRandom(1, 50)));
		CommonLib.clickButton(By.id("buttonSubmitReaction_label"));
	}

	public void submit() {
		CommonLib.clickButton(By.xpath("//*[text()='Submit']"));
	}

	public void submitInactivateFamily() {
		CommonLib.clickButton(By.id("buttonSubmitRemoval_label"));
	}

	public void clickSurgicalHistory(String date) {
		CommonLib.clickButton(By.xpath("//*[@id='PSHGrid1']//div[2]//tbody/tr/td[text()='" + date + "']"));
	}

	public void setSurgicalHistoryEditReason() {
		try {
			CommonLib.selectDojoListValue("procedureEditReasonList", "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickFamilyMember(String familyMember) {
		CommonLib.clickButton(By.xpath("//*[@id='FMHGrid1']//div[2]//tbody/tr/td[3]/span[text()='" + familyMember + "']"));
	}

	public List<WebElement> getFamilyMemberList() {
		return CommonLib.getElements(By.xpath("//*[@id='FMHGrid1']//div[2]//tbody/tr/td[3]"));
	}

	public List<WebElement> getFamilyDiseaceList() {
		return CommonLib.getElements(By.xpath("//*[@id='dojox_grid__View_1']//tbody/tr/td[4]"));
	}

	public void setFamilyMemberFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'family-history-entry.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void selectFamilyMember() {
		setFamilyMemberFrame();
		try {
			String familyMember = CommonLib.selectDojoListValue("familyMemberList", "");
			HashTableRepository.setHash("selectedFamilyMember", familyMember);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int random = CommonLib.generateRandom(1, 10);
		if (random % 2 == 0)

			CommonLib.clickButton(By.id("GENDER_F"));
		else
			CommonLib.clickButton(By.id("GENDER_M"));

	}

	public void submitReview() {
		List<WebElement> sub = CommonLib.getElements(By.xpath("//div[text()='Submit']"));
		sub.get(sub.size() - 1).click();
		// CommonLib.clickButton(By.xpath("//div[text()='Submit']"));
	}

	public void validateReview(String statusReview) {
		String reviewText = CommonLib.getText(By.id("rosDivContent"));
		if (reviewText.contains(HashTableRepository.getHash("reviewpaneHeading")) && reviewText.contains(statusReview + " " + HashTableRepository.getHash("slectedReview")))
			CustomReporter.MessageLogger("Review added successfully.: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + statusReview + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Pass);
		else
			CustomReporter.MessageLogger("Review is not added .: <b>" + HashTableRepository.getHash("reviewpaneHeading") + " " + statusReview + HashTableRepository.getHash("slectedReview") + "</b>", common.CustomReporter.status.Fail);
	}

	public void validatePhysicial(String statusPhy) {

		String physicialText = null;

		try {
			physicialText = CommonLib.getText(By.xpath("//div[@id='PE']//div[@id='peDivContent']"));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			physicialText = CommonLib.getText(By.id("vitalDisplay"));
		}

		if (physicialText.toLowerCase().contains(HashTableRepository.getHash("slectedPhysicial").toLowerCase() + " = " + statusPhy))
			CustomReporter.MessageLogger("", status.Pass);
	}

	public void cancel() {
		CommonLib.clickButton(By.id("wizardCancel_label"));
	}

	public void mandate(String function) {
		CommonLib.changeimplicitwait(2);
		try {
			List<WebElement> l = CommonLib.getElements(By.xpath("//div[contains(@id,'Yellow') and (@class='radioMandatory')]//input[contains(@id,'Y')]"));
			for (WebElement mandatory : l) {
				mandatory.click();
				if (function.equals("review system")) {
					CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("rosVerbiage_iframe")));
				} else {
					CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("peVerbiage_iframe")));
				}
				CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(CommonLib.RandomText(1, 4));
				// CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"),
				// CommonLib.RandomText(1, 4));
				setMainFrame();
				if (function.equals("review system")) {
					setReviewSystemFrame();
				} else {
					setPhysicialFrame();
				}

			}
			CommonLib.resetImplicitWait();
		} catch (Exception e) {

		}

	}

	// public void mandate() {
	// CommonLib.changeimplicitwait(2);
	// try {
	// List<WebElement> l =
	// CommonLib.getElements(By.xpath("//div[contains(@id,'Yellow') and
	// (@class='radioMandatory')]//input[contains(@id,'Y')]"));
	// for (WebElement mandatory : l) {
	// mandatory.click();
	// CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("rosVerbiage_iframe")));
	// CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(CommonLib.RandomText(1,
	// 4));
	// // CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"),
	// // CommonLib.RandomText(1, 4));
	// setMainFrame();
	// setReviewSystemFrame();
	// }
	// CommonLib.resetImplicitWait();
	// } catch (Exception e) {
	//
	// }
	//
	// }

	public void selectPhysicial(String status) {
		CommonLib.clickButton(By.xpath("//div[@id='bosHolder']/div[@id='" + HashTableRepository.getHash("physicialPainId") + "']/div[2]//div[@class='divTable']/div[1]//div[2]//div[contains(@id,'reviewRad" + status + "')]//input"));
		String selectedReview = CommonLib.getText(By.xpath("//div[@id='bosHolder']/div[@id='" + HashTableRepository.getHash("physicialPainId") + "']/div[2]//div[@class='divTable']/div[1]//div[2]//div[@class='divDescription']"));
		HashTableRepository.setHash("slectedPhysicial", selectedReview);

	}

	public void selectReview(String status) {
		CommonLib.clickButton(By.xpath("//div[@id='bosHolder']/div[@id='" + HashTableRepository.getHash("reviewPainId") + "']/div[2]//div[@class='divTable']/div[1]//div[2]//div[contains(@id,'h" + status + "Rad')]//input"));
		String selectedReview = CommonLib.getText(By.xpath("//div[@id='bosHolder']/div[@id='" + HashTableRepository.getHash("reviewPainId") + "']/div[2]//div[@class='divTable']/div[1]//div[2]//div[@class='divDescription']"));
		HashTableRepository.setHash("slectedReview", selectedReview);
	}

	public boolean isReviewPanePresent(String key) {
		boolean isPresent = false;
		if (CommonLib.getElements(By.xpath("//div[@id='bosHolder']/div[1]")).size() > 0) {
			String painId = CommonLib.getElement(By.xpath("//div[@id='bosHolder']/div[1]")).getAttribute("id");
			HashTableRepository.setHash(key + "PainId", painId);
			String paneHeading = CommonLib.getText(By.xpath("//div[@id='bosHolder']/div[@id='" + painId + "']/div[1]//span[@class='dijitTitlePaneTextNode']"));
			HashTableRepository.setHash(key + "paneHeading", paneHeading);

			isPresent = true;
		}
		return isPresent;

	}

	public void selectHistoryIllness(boolean selectionFlag) {
		// List<WebElement> getCheckBoxes = CommonLib
		// .getElements(By.xpath("//div[@id='" +
		// HashTableRepository.getHash("firstHistoryPresentID")
		// + "']//div[2]//td/div[contains(@class,'dijitCheckBox')]"));
		if (selectionFlag == true) {
			if (!CommonLib.getElement(By.xpath("//div[@id='" + HashTableRepository.getHash("firstHistoryPresentID") + "']//div[2]//td/div[contains(@class,'dijitCheckBox')]")).getAttribute("class")
					.contains("dijitCheckBoxChecked dijitChecked"))
				CommonLib.getElement(By.xpath("//div[@id='" + HashTableRepository.getHash("firstHistoryPresentID") + "']//div[2]//td/div[contains(@class,'dijitCheckBox')]")).click();
		} else {
			if (CommonLib.getElement(By.xpath("//div[@id='" + HashTableRepository.getHash("firstHistoryPresentID") + "']//div[2]//td/div[contains(@class,'dijitCheckBox')]")).getAttribute("class")
					.contains("dijitCheckBoxChecked dijitChecked"))
				CommonLib.getElement(By.xpath("//div[@id='" + HashTableRepository.getHash("firstHistoryPresentID") + "']//div[2]//td/div[contains(@class,'dijitCheckBox')]")).click();
			// getCheckBoxes.get(0).click();
		}

		List<WebElement> getSelectedTExt = CommonLib.getElements(By.xpath("	//div[@id='" + HashTableRepository.getHash("firstHistoryPresentID") + "']//div[2]//table[@class='dojoxGridRowTable']//td[@idx='2']/div"));
		HashTableRepository.setHash("selectedHistoryIllness", getSelectedTExt.get(0).getText());
	}

	public void validateHIP(String page) {

		if (page.equals("CN"))
			setConsultationFrame();

		else if (page.equals("PN"))
			CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("subEditor_iframe")));
		else
			CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("hpiEditor_iframe")));
		String hipText = CommonLib.getText(By.id("dijitEditorBody"));
		if (hipText.contains(HashTableRepository.getHash("selectedHistoryIllness")))
			CustomReporter.MessageLogger("History of present illness has been added ", status.Pass);
		else {
			CustomReporter.MessageLogger("History of present illness has not been added ", status.Fail);
		}
	}

	public void emptyHIP(String method) {
		// List<WebElement> f =
		// CommonLib.getElements(By.id("hpiVerbiage_iframe"));
		CnPages cObj = new CnPages();
		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("hpiVerbiage_iframe")));
		HashTableRepository.setHash("mannualHIP", CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("mannualHIP"));
		// CommonLib.setData(By.id("dijitEditorBody"), "testing");
		setMainFrame();
		if (method.equals("CN")) {

			cObj.setConsultationFrame();
		}
		setHistoryFrame();
		submitReview();
		// CommonLib.clickButton(By.id("wizardCancel_label"));
		CustomReporter.MessageLogger("HIP library is empty.", status.Warning);
		setMainFrame();
		if (method.equals("CN")) {

			cObj.setConsultationFrame();
		}
	}

	public boolean historyPresentIllness() {
		CommonLib.changeimplicitwait(4);
		List<WebElement> historyIllnessList = CommonLib.getElements(By.xpath("//*[@id='statementHolder']//div[contains(@class,'dijitTitlePane')]"));
		if (historyIllnessList.size() != 0) {
			HashTableRepository.setHash("firstHistoryPresentID", historyIllnessList.get(0).getAttribute("id"));
			CommonLib.resetImplicitWait();
			return true;
		}

		else {
			CommonLib.resetImplicitWait();

			return false;
		}

	}
	// public boolean isHistoryPresent()
	// {
	// boolean isPresent =false;
	// if(CommonLib.getElements(By.xpath("//div[@id='statementHolder']//div[class='dijitTitlePane']")).size()>0)
	// {
	// List<WebElement> gridList =
	// CommonLib.getElements(By.xpath("//div[@id='statementHolder']//div[class='dijitTitlePane']"));
	// String id = gridList.get(0).getAttribute("id");
	//
	// }

	// }

	public void setHistoryFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'history-present-illness.action?')]"));
		if (frame.size() > 0)
			CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setPhysicialFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'physical-exam.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setReviewSystemFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'review-of-systems.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	// public void changeFrameToPopUpCC() {
	// WebElement frame =
	// CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and
	// contains(@src,'/OptimumClinicals/PatientsPhysicians')][last()]"));
	// CommonLib.setFrameFromCurrent(frame);
	// CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span[2]//span//span//span[@id='addItem_label']"));
	// String data = CommonLib.RandomText(1, 1);
	// CommonLib.setData(By.xpath("//input[@id='patientPhysicianList']"), data);
	// }
	//
	// public void selectedPhysicians() {
	// List<WebElement> li =
	// CommonLib.getElements(By.xpath("//div[@id='CC_HPGrid1']//table[@class='dojoxGridRowTable']//tbody//tr//td[4]"));
	// // String text = CommonLib
	// //
	// .getElement(By.xpath("//div[@id='CC_HPGrid1']//table[@class='dojoxGridRowTable']//tbody//tr//td[4]"))
	// // .getText();
	// String text = "";
	// for (WebElement we : li) {
	// text = text + "_" + we.getText();
	// }
	// String[] physicians = text.split("_");
	// // }
	// }
	//
	// public void setTextPlan() {
	// WebElement frame =
	// CommonLib.getElement(By.xpath("//iframe[@id='planEditor_iframe']"));
	// CommonLib.setFrameFromCurrent(frame);
	// String data = CommonLib.RandomText(1, 3);
	// CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"), data);
	// setMainFrame();
	//
	// }
	//
	// public void clickOnOrderEntryLink() {
	// CommonLib.clickButton(By.xpath("//div[@id='PLN_titleBarNode']//div//div[@id='oeId'
	// and contains(@class,'titlePane')]//u"));
	// }
	//
	// public boolean getTitleOrderEntryPopUp() {
	// // String title = CommonLib.getElement(By.xpath(
	// //
	// "//iframe[contains(@src,'/OptimumClinicals/order-entry-plus.action?')]//html//body//div[class='dijitBorderContainerNoGutter
	// // dijitContainer
	// //
	// dijitLayoutContainer']//div//div[@class='iframeDialogTitleBar']//span[@class='iframeDialogTitle']"))
	// // .getText();
	// String title = CommonLib.getText(By.className("iframeDialogTitle"));
	// CommonLib.clickButton(By.xpath("//span[@title='Close']"));
	// if (title.equals("Order Entry")) {
	// return true;
	// } else
	// return false;
	// // String title = CommonLib.getElement(By.xpath(
	// // "//iframe[@id='iFrameDialog' and
	// //
	// contains(@src,'/OptimumClinicals/order-entry-plus.action?')][last()]//html//body//div[2]//div//div//span[@class='iframeDialogTitle']"))
	// // .getText();
	// // String title = CommonLib
	// //
	// .getElement(By.xpath("//div[@id='PLN_titleBarNode']//div//div//u[text()='+Order
	// // Entry']")).getText();
	// }

	public void setSocialHistory(String history) {
		CommonLib.clickButton(By.id("SHContent" + history));
	}

	public String getTobaccoText() {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid2']//tbody//td"));
	}

	public void setTobaccco() {

		WebElement frame = CommonLib.getElement(By.xpath("//*[contains(@src,'substance-properties.action?')]"));
		CommonLib.setFrameFromCurrent(frame);
		CommonLib.clickButton(By.id("formerUse"));
		try {
			String tobacco = CommonLib.selectDojoListValue("tobaccoChoiceF1", "");
			HashTableRepository.setHash("selectedTobacco", tobacco);
		} catch (Exception e) {
		}
		String text = CommonLib.RandomText(2, 1);
		CommonLib.setData(By.id("amountF1"), text);
		HashTableRepository.setHash("AmountSet", text);
		CommonLib.setData(By.id("packYearsF1"), CommonLib.RandomText(2, 2));
		CommonLib.clickButton(By.id("submitT_label"));

	}

	public void setTobaccoAmount(String amount) {
		CommonLib.setData(By.id("amountF1"), amount);
	}

	public void setTboaccoEdit() {
		try {
			CommonLib.selectDojoListValue("reasonList", "");
			String text = CommonLib.RandomText(2, 1);
			CommonLib.setData(By.id("amountF1"), text);
			HashTableRepository.setHash("AmountSet", text);

		} catch (Exception e) {

		}
	}

	public void clickSubmitTobacco() {
		CommonLib.clickButton(By.xpath("//div[text()='Submit']"));
	}

	public ArrayList<WebElement> getCheckBoxesForHPI() {

		ArrayList<WebElement> cb = (ArrayList<WebElement>) CommonLib
				.getElements(By.xpath("//div[@id='statementHolder']//div[@class='dijitTitlePane']//div[contains(@id,'_Grid')]//div[contains(@class,'dojoxGridRowbar')]//div[contains(@class,'dijitCheckBox')]"));

		// ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) CommonLib
		// .getElements(By.xpath("//div[@class='dojoxGridCheckSelector
		// dijitReset
		// dijitInline dijitCheckBox']"));
		return cb;
	}

	public void getTextOfChecked() {
		List<WebElement> li = CommonLib.getElements(
				By.xpath("//div[@class='dojoxGridCheckSelector dijitReset dijitInline dijitCheckBox dijitCheckBoxChecked dijitChecked']/../../../../../../../../..//following-sibling::div//div//div//div//div//table//tbody//tr//td[3]//div"));
		List<WebElement> titleList = CommonLib
				.getElements(By.xpath("//div[@class='dojoxGridCheckSelector dijitReset dijitInline dijitCheckBox dijitCheckBoxChecked dijitChecked']/../../../../../../../../../../../../../../..//div[1]//span[3]"));
		String title = null;
		String checked = null;
		for (WebElement e : titleList) {
			title = title + ":" + e.getText();
		}
		for (WebElement e : li) {
			checked = checked + "," + e.getText();
		}
		HashTableRepository.setHash("LibraryOfStatementsChosen", checked);
		HashTableRepository.setHash("TitlesOfStatementsChosen", title);

		// String checked = CommonLib.getElement(By.xpath(
		// "//div[@class='dojoxGridCheckSelector dijitReset dijitInline
		// dijitCheckBox
		// dijitCheckBoxChecked
		// dijitChecked']/../../../../../../../../..//following-sibling::div//div//div//div//div//table//tbody//tr//td[3]//div"))
		// .getText();
		// System.out.println(checked);
	}

	// history-present-illness.action?action=init&noteType=HP&section=HPI&noteId=665&maxHeight=768
	public void switchFrameLibraryOfStatement() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'history-present-illness.action?')]"));
		CommonLib.setFrameFromCurrent(frame);
	}

	public void selectRandomCheckBox() {
		List<WebElement> los = CommonLib.getElements(By.xpath("//div[@id='statementHolder']//div[@class='dijitTitlePane']"));
		// int size = los.size();
		int random = CommonLib.generateRandom(1, los.size());
		String id = los.get(random).getAttribute("id");
		List<WebElement> checkboxes = CommonLib.getElements(By.xpath("//div[@id='" + id + "']//div[contains(@id,'_Grid')]//div[contains(@class,'dojoxGridRowbar')]//div[contains(@class,'dijitCheckBox')]"));

		// div[@id='" + id + "']//div[@class='dojoxGridCheckSelector dijitReset
		// dijitInline dijitCheckBox']
		// int checkBoxSize = checkboxes.size();
		// List<WebElement> checkboxes =
		// CommonLib.getElements(By.xpath("//div[@id='statementHolder']//div[@id='"
		// + id
		// + "']//div[2]//div//div//div//div[2]//div//div//div//div//div"));
		int randomCheckBoxes = CommonLib.generateRandom(1, checkboxes.size());
		CommonLib.setBoolFields(true, By.xpath("//div[@id='statementHolder']//div[@id='" + id + "']//div[2]//div//div//div//div[2]//div//div//div//div//div[" + randomCheckBoxes + "]//table//tbody//tr//td//div"));
		// String id =
		// CommonLib.getElement(By.xpath("//div[@id='statementHolder']//div[" +
		// random +
		// "]"))
		// .getAttribute("id");
		// List<WebElement> checkboxes =
		// CommonLib.getElements(By.xpath("//div[@id='statementHolder']//div[" +
		// random
		// + "]//div[2]//div//div//div//div[2]//div//div//div//div//div"));
		// int randomCheckBoxes = CommonLib.generateRandom(1,
		// checkboxes.size());

	}

	public void switchFrameToOrderEntryPopUp() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/order-entry-plus.action?')][last()]"));
		CommonLib.setFrameFromCurrent(frame);
		try {
			List<WebElement> ok = CommonLib.getElements(By.xpath("//*[text()='OK']"));
			ok.get(ok.size() - 1).click();
		} catch (Exception e) {

		}
	}

	public void switchFrameToMainFrame() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[@id='mainWorkFrame0']"));
		CommonLib.setFrameFromCurrent(frame);
	}

	public boolean getTitleOrderEntryPopUp() {
		// String title = CommonLib.getElement(By.xpath(
		// "//iframe[contains(@src,'/OptimumClinicals/order-entry-plus.action?')]//html//body//div[class='dijitBorderContainerNoGutter
		// dijitContainer
		// dijitLayoutContainer']//div//div[@class='iframeDialogTitleBar']//span[@class='iframeDialogTitle']"))
		// .getText();
		String title = CommonLib.getText(By.className("iframeDialogTitle"));
		// handleOrderEntryError();
		CommonLib.clickButton(By.xpath("//span[@title='Close']"));
		if (title.equals("Order Entry")) {
			return true;
		} else
			return false;
		// String title = CommonLib.getElement(By.xpath(
		// "//iframe[@id='iFrameDialog' and
		// contains(@src,'/OptimumClinicals/order-entry-plus.action?')][last()]//html//body//div[2]//div//div//span[@class='iframeDialogTitle']"))
		// .getText();
		// String title = CommonLib
		// .getElement(By.xpath("//div[@id='PLN_titleBarNode']//div//div//u[text()='+Order
		// Entry']")).getText();
	}

	public void handleOrderEntryError() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//span[text()='OK']"));
		if (li.size() != 0)
			CommonLib.clickButton(By.xpath("//span[text()='OK']"));

	}

	public void clickOnOrderEntryLink() {
		CommonLib.clickButton(By.xpath("//div[@id='PLN_titleBarNode']//div//div[@id='oeId' and contains(@class,'titlePane')]//u"));
	}

	public void setTextPlan() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[@id='planEditor_iframe']"));
		CommonLib.setFrameFromCurrent(frame);
		String data = CommonLib.RandomText(1, 3);
		CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"), data);
		setMainFrame();

	}

	public void generateRandomLetter() {
		String[] a = { "A", "B", "W", "N", "T", "R", "S", "L" };
		int random = CommonLib.generateRandom(0, 2);
		CommonLib.setData(By.xpath("//input[@id='patientPhysicianList']"), a[random]);
		CommonLib.getElement(By.xpath("//input[@id='patientPhysicianList']")).sendKeys(Keys.ENTER);

	}

	public void choosePhysician() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='physicianListGrid']//div[2]//div//div//div//div//div"));
		int random = CommonLib.generateRandom(1, li.size());
		CommonLib.doubleClick("//div[@id='physicianListGrid']//div[2]//div//div//div//div//div[" + random + "]//table//tbody//tr//td[2]");

		// CommonLib.doubleClick(
		// "//div[@id='physicianListGrid']//div[2]//div//div//div//div//div//table//tbody//tr//td[2]");
		String physicianName = CommonLib.getElement(By.xpath("//div[@id='physicianListGrid']//div[2]//div//div//div//div//div[" + random + "]//table//tbody//tr//td[1]")).getText();
		HashTableRepository.setHash("SelectedPhysicianName", physicianName);
	}

	public boolean validatePopUp() {
		boolean status = false;
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[1]"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[2]"));
		for (int i = 0; i < li.size(); i++) {
			String modified = HashTableRepository.getHash("SelectedPhysicianName").replaceAll(" ", "");
			String modified2 = li.get(i).getText().replaceAll(" ", "");
			if (modified2.equals(modified)) {
				if (li2.get(i).getText().equals(HashTableRepository.getHash("SelectedPhysicianType"))) {
					status = true;
				}
			}
		}

		return status;
	}

	public boolean validate(String page) {

		boolean status = false;
		if (page.equals("HP") || page.equals("DS") || page.equals("PN")) {
			setMainFrame();
		} else {
			setMainFrame();
			setConsultationFrame();
		}
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='CC_" + page + "Grid1']/div[2]//table/tbody/tr/td[3]"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='CC_" + page + "Grid1']/div[2]//table/tbody/tr/td[4]"));
		List<WebElement> li3 = CommonLib.getElements(By.xpath("//div[@id='CC_" + page + "Grid1']/div[2]//table/tbody/tr/td[1]/div"));
		for (int i = 0; i < li.size(); i++) {
			String modified = HashTableRepository.getHash("SelectedPhysicianName").replaceAll(" ", "");
			String modified2 = li.get(i).getText().replaceAll(" ", "");
			if (modified2.equals(modified)) {
				if (li2.get(i).getText().equals(HashTableRepository.getHash("SelectedPhysicianType"))) {
					status = true;
					CommonLib.setBoolFields(false, li3.get(i));

				}
			}
		}
		return status;
	}

	public void setConsultationFrame() {

		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'consultation-note.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));

	}

	public void clickEditForTestcase() {
		actions("Edit");
		changeFrameToPopUpCC();
	}

	public void clickDeleteCC() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[1]"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[2]"));
		for (int i = 0; i < li.size(); i++) {
			String modified = HashTableRepository.getHash("SelectedPhysicianName").replaceAll(" ", "");
			String modified2 = li.get(i).getText().replaceAll(" ", "");
			if (modified2.equals(modified)) {
				if (li2.get(i).getText().equals(HashTableRepository.getHash("SelectedPhysicianType"))) {
					li.get(i).click();
					CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span[4]//span//span//span[@id='deleteItem_label']"));
					break;
				}

			}
		}
	}

	public void clickOKForDelete() {
		List<WebElement> okBu = CommonLib.getElements(By.xpath("//span[text()='OK']"));
		okBu.get(okBu.size() - 1).click();
		// CommonLib.clickButton(By.xpath("//span[text()='OK']"));
	}

	public boolean validateEdit(String page) {
		boolean status = false;
		boolean flag = true;
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[1]"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[2]"));
		for (int i = 0; i < li.size(); i++) {
			String modified = HashTableRepository.getHash("SelectedPhysicianName").replaceAll(" ", "");
			String modified2 = li.get(i).getText().replaceAll(" ", "");
			if (modified2.equals(modified)) {
				if (li2.get(i).getText().equals(HashTableRepository.getHash("SelectedPhysicianType"))) {
					li.get(i).click();
					clickEditPatient_sPhysician();
					while (flag) {
						selectPhysicianType();
						flag = checkIfSamePhysicianWithSameRoleExists();
					}
					// selectPhysicianType();
					boolean check = validatePopUp();
					clickCloseCC();
					boolean check2 = validate(page);
					if (check && check2) {
						status = true;
					}
					break;
				}
			}
		}
		return status;
	}

	public void selectPhysicianType() {
		try {
			String optionSelected = CommonLib.selectDojoListValue("physicianTypeList", "");
			if (optionSelected.contains("ADMITTING") || optionSelected.contains("ATTENDING") || optionSelected.contains("CO-ATTENDING") || optionSelected.contains("ADMITTING PHYSICIAN")) {
				selectPhysicianType();
			} else {
				HashTableRepository.setHash("SelectedPhysicianType", optionSelected);
				CommonLib.clickButton(By.xpath("//span[@id='wizardSubmits_label']//div"));
			}
		} catch (InterruptedException e) {

			// e.printStackTrace();
		}
	}

	public boolean checkIfSamePhysicianWithSameRoleExists() {
		boolean check = false;
		try {
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]//span[text()='OK']"));
			check = true;
		} catch (Exception e) {

		}
		return check;
	}

	public String[] selectedPhysicians() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='CC_HPGrid1']//table[@class='dojoxGridRowTable']//tbody//tr//td[4]"));
		String text = "";
		for (WebElement we : li) {
			text = text + "_" + we.getText();
		}
		String[] physicians = text.split("_");
		return physicians;
		// }
	}

	public void changeFrameToPopUpCC() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/PatientsPhysicians')][last()]"));
		CommonLib.setFrameFromCurrent(frame);
	}

	public void clickAddPatient_sPhysician() {
		CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span[2]//span//span//span[@id='addItem_label']"));

	}

	public void clickEditPatient_sPhysician() {
		CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span[3]//span//span//span[@id='editItem_label']"));
	}

	public void clickSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmits_label']//div"));
	}

	public void setImmunizationFrame() {

		List<WebElement> frames = CommonLib.getElements(By.xpath("//iframe[contains(@src,'/OptimumClinicals/ImmunizationsEntry.do?')]"));
		if (frames.size() != 0)
			CommonLib.setFrameFromCurrent(frames.get(frames.size() - 1));
	}

	public void setImmunization() {
		// CommonLib.GetDriver().switchTo().frame(arg0)
		// WebElement iFrameOrderManager =
		// CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and
		// contains(@src,'hold-order')][last()]"));
		// CommonLib.setFrameFromCurrent(iFrameOrderManager);
		setImmunizationFrame();
		try {
			List<WebElement> l = CommonLib.getElements(By.id("IHCODEList"));
			CommonLib.selectDojoDropDownByKeyDownNumber("IHCODEList", 4);
			String selectedImmunization = CommonLib.selectDojoListValue("IHCODEList", "");
			HashTableRepository.setHash("selectedImmunization", selectedImmunization);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSubmitImmunization();

	}

	public void setSubmitImmunization() {
		CommonLib.clickButton(By.id("button.submit_label"));

	}

	public void deleteImmunization() {
		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/ImmunizationsRemove.do?')]")));
		try {
			CommonLib.selectDojoListValue("removalReasonList", "");
		} catch (Exception e) {

		}
		CommonLib.clickButton(By.id("button.submit_label"));
		setMainFrame();
	}

	public void clickImmunization(String immu) {
		CommonLib.clickButton(By.xpath("//div[@id='IMMGrid1']//div[2]//tbody//td/span[contains(text(),'" + immu + "')]"));
	}

	public List<WebElement> getAddedImmunization() {
		return CommonLib.getElements(By.xpath("//div[@id='IMMGrid1']//div[2]//tbody//td/span"));
	}

	// public void setLabSummaryFrame() {
	// List<WebElement> frame =
	// CommonLib.getElements(By.xpath("//iframe[contains(@src,'/OptimumClinicals/lab-test-summary.action?')]"));
	// CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	//
	// }
	//
	// public ArrayList<String> getLabSummary() {
	// List<WebElement> labSummary =
	// CommonLib.getElements(By.xpath("//div[@id='labTestSummaryGrid']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[@idx='1']"));
	// ArrayList<String> listLabSummary = new ArrayList<String>();
	// for (WebElement lab : labSummary) {
	// listLabSummary.add(lab.getText());
	// }
	// return listLabSummary;
	// }

	// public boolean isLabDataPresent() {
	// List<WebElement> labSummary =
	// CommonLib.getElements(By.xpath("//div[@id='labTestSummaryGrid']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[2]"));
	// if (labSummary == null)
	// return false;
	// else
	// return true;
	// }
	//
	// public List<WebElement> getAddedSummaryList() {
	// return
	// CommonLib.getElements(By.xpath("//div[@id='RESGrid1']/div[2]//tbody//td"));
	// }
	//
	// public void selectAllLabSummary() {
	// CommonLib.clickButton(By.id("labTestSummaryGrid_rowSelector_-1"));
	// }
	//
	// public void uncheckLabSummary(int index) {
	// CommonLib.clickButton(By.id("labTestSummaryGrid_rowSelector_" + index));
	// }
	//
	// public void clickRESContainor(int index) {
	// if (index != 4)
	// CommonLib.clickButton(By.id("RESContainer" + index + "_titleBarNode"));
	// else
	// CommonLib.clickButton(By.id("labRadNotes"));
	//
	// }
	//
	// public void setResultSummaryFrame() {
	// List<WebElement> frames =
	// CommonLib.getElements(By.xpath("//iframe[contains(@src,'ResultSummary.do?')]"));
	// CommonLib.setFrameFromCurrent(frames.get(frames.size() - 1));
	// }
	//
	// public void getMicrobiology() {
	// List<WebElement> listMicro =
	// CommonLib.getElements(By.xpath("//div[@id='mrResults']//span[contains(@style,'color:
	// rgb(0, 0, 255)')]"));
	// ArrayList<String> text = new ArrayList<>();
	// for (WebElement micro : listMicro) {
	// text.add(micro.getText());
	// }
	// System.out.println(text);
	// }
	//
	// public boolean isMicrobiologyDataPresent() {
	// if (CommonLib.getElements(By.id("CheckAllMicro")).size() > 0) {
	// return true;
	// } else
	// return false;
	// }
	//
	// public void setLabNotes() {
	// String classAt =
	// CommonLib.getElement(By.id("labRadNotes_titleBarNode")).getAttribute("class");
	// if (!classAt.contains("dijitOpen"))
	// CommonLib.clickButton(By.id("labRadNotes_titleBarNode"));
	// HashTableRepository.setHash("labNotesStudies", CommonLib.RandomText(1,
	// 20));
	// CommonLib.setData(By.id("dijitEditorBody"),
	// HashTableRepository.getHash("labNotesStudies"));
	// }
	//
	// // public void clickCloseCC() {
	// //
	// CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span//span//span//span[@id='close_label']"));
	// // }
	//
	// public void radioLogyTestCases() {
	// CommonLib.clickButton(By.id("RADLabId"));
	// List<WebElement> frame =
	// CommonLib.getElements(By.xpath("//iframe[contains(@src='/OptimumClinicals/ResultSummary.do?')]"));
	// CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	// String title = CommonLib.getText(By.className("iframeDialogTitle"));
	// if (title.equals("Result Summary"))
	// CustomReporter.MessageLogger("Result Summary title is as expected.",
	// status.Pass);
	// else
	// CustomReporter.MessageLogger("Result Summary pop up is not as expected.",
	// status.Fail);
	// CommonLib.clickButton(By.className("iframeDialogCloseIcon"));
	// setMainFrame();
	//
	// }
	//
	// public void labTestSummary() {
	// CommonLib.clickButton(By.id("genLabId"));
	// List<WebElement> frame =
	// CommonLib.getElements(By.xpath("//iframe[contains(@src='/OptimumClinicals/lab-test-summary.action?')]"));
	// CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	// String title = CommonLib.getText(By.className("iframeDialogTitle"));
	// if (title.equals("Lab Test Summary"))
	// CustomReporter.MessageLogger("Lab test summary title is as expected.",
	// status.Pass);
	// else
	// CustomReporter.MessageLogger("Lab test summary pop up is not as
	// expected.", status.Fail);
	// CommonLib.clickButton(By.className("iframeDialogCloseIcon"));
	// setMainFrame();
	//
	// }

	public void clickCloseCC() {
		CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span//span//span//span[@id='close_label']"));
	}

	public String getAllergies() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span"));
		String text = "";
		for (WebElement we : li) {
			text = text + "_" + we.getText();
		}
		return text;
	}

	public void switchFrameToPopupAllergies() {
		WebElement frame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/AllergiesEntry')][last()]"));
		CommonLib.setFrameFromCurrent(frame);

	}

	public void selectAllergy(String allergy) {
		try {
			String optionSelected = CommonLib.selectDojoListValue("compositeCodeList", "");
			if (allergy.contains(optionSelected)) {
				selectAllergy(allergy);
			} else {
				HashTableRepository.setHash("SelectedAllergy", optionSelected);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void selectSource() {
		try {
			String optionSelected = CommonLib.selectDojoListValue("allergySource", "");
			HashTableRepository.setHash("SelectedAllergySource", optionSelected);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']/div[text()='Submit']"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void allergySubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']/div[text()='Submit']"));
	}

	public void checkReminderPopUp() {
		try {
			CommonLib.getElement(By.xpath("//div[@id='reminderDialog']/div[2]//span[text()='OK']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public List<String> getAllergyForValidation() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span"));
		List<String> allergyList = new ArrayList<String>();
		for (WebElement we : li) {
			allergyList.add(we.getText());
		}
		return allergyList;
	}

	public boolean allergyAddValidateScreen() {
		boolean status = false;
		// List<WebElement> li = CommonLib.getElements(
		// By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span"));
		// List<String> allergyList = new ArrayList<String>();
		// for (WebElement we : li) {
		// allergyList.add(we.getText());
		// }
		List<String> allergyList = getAllergyForValidation();
		for (String allergies : allergyList) {
			if (allergies.equals(HashTableRepository.getHash("SelectedAllergy"))) {
				status = true;
				break;
			}
		}
		return status;

	}

	public void allergyEditClick() {
		CommonLib.getElement(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span[text()='" + HashTableRepository.getHash("SelectedAllergy") + "']")).click();
		actions("Edit");
		switchFrameToPopupAllergies();
	}

	public void allergyDeleteClick() {
		CommonLib.getElement(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span[text()='" + HashTableRepository.getHash("SelectedAllergy") + "']")).click();
		actions("Inactivate");
		try {
			CommonLib.selectDojoListValue("AlgRemovalReasonList", "");
		} catch (InterruptedException e) {
		}
		CommonLib.clickButton(By.xpath("//form[@id='removeAlgForm']//span[@id='buttonSubmitReaction_label']"));
	}

	public void allergyEdit() {
		try {
			CommonLib.selectDojoListValue("editReasonList", "");
			String currentDate = CommonLib.addDaysToCurrent(0);
			CommonLib.setData(By.xpath("//input[@id='initialReactionDate']"), currentDate);
			HashTableRepository.setHash("EditedDate", currentDate);
			allergySubmit();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public boolean allergyDeleteValidate() {
		boolean status = false;
		List<String> allergyList = getAllergyForValidation();
		for (String allergies : allergyList) {
			if (allergies.equals(HashTableRepository.getHash("SelectedAllergy"))) {
				status = true;
				break;
			}
		}
		return status;

	}

	public boolean allergyEditValidate(String method) {
		boolean check = false;
		setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[3]//span"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='ALGGrid1']/div[2]//table[@class='dojoxGridRowTable']//tbody//tr//td[7]"));
		for (int i = 0; i < li.size(); i++) {
			String modified = HashTableRepository.getHash("SelectedAllergy");
			String modified2 = li.get(i).getText();
			if (modified2.equals(modified)) {
				if (li2.get(i).getText().equals(HashTableRepository.getHash("EditedDate"))) {
					check = true;
					break;
				}
			}
		}
		return check;

	}

	public void deleteAllPatient_sPhysicians() {
		boolean isDeleted = true;
		String type = "";

		while (isDeleted) {
			List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[2]"));
			int index = 0;
			for (WebElement listData : li2) {
				type = listData.getText();
				if (!(type.equals("ADMITTING")) && !(type.equals("ATTENDING")) && !(type.equals("CO-ATTENDING"))) {
					listData.click();
					CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span[4]//span//span//span[@id='deleteItem_label']"));
					clickOKForDelete();
					break;
				}
			}

			List<WebElement> li3 = CommonLib.getElements(By.xpath("//div[@id='searchGrid']//table/tbody/tr/td[2]"));
			for (int i = 0; i < li3.size(); i++) {
				type = li2.get(i).getText();
				if (!(type.equals("ADMITTING")) || !(type.equals("ATTENDING")) || !(type.equals("CO-ATTENDING"))) {
					index++;
				}
			}

			if (index == 0) {
				isDeleted = false;
			}

		}
	}

	public void validateSignSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='signAndSubmitHP_label']/div[text()='Sign and Submit']"));
		String lable = "Gupta, Tarun";
		String signedLable = CommonLib.getElement(By.xpath("//div[@id='hpSectionBar']/div/span")).getText();
		boolean check = signedLable.contains(lable);
		if (check) {
			CustomReporter.MessageLogger("Sign and submit working as expected", status.Pass);
		} else
			CustomReporter.MessageLogger("Sign and submit not working as expected", status.Fail);
	}

	public void clickSubmitOnPage() {
		CommonLib.clickButton(By.xpath("//span[@id='submitHP_label']/div[text()='Submit']"));
	}

	public void clickAmmend() {
		actions("Amend");
		CommonLib.clickButton(By.xpath("//div[@id='amendDlg']/div[2]//span[text()='OK']"));
	}

	public void ammendVerify() {
		CommonLib.clickButton(By.xpath("//span[@id='signAndSubmitHP_label']/div[text()='Sign and Submit']"));
		String lable = "Amended Signed:  NTT 1, DATA (657452)";
		String signedLable = CommonLib.getElement(By.xpath("//div[@id='hpSectionBar']/div/span")).getText();
		boolean check = signedLable.contains(lable);
		if (check) {
			CustomReporter.MessageLogger("Ammend working as expected", status.Pass);
		} else
			CustomReporter.MessageLogger("Ammend not working as expected", status.Fail);

	}

	public void setHomeMedicationFrame() {
		WebElement HomeMedicationFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/medication-reconciliation-entry.action?')][last()]"));
		CommonLib.setFrameFromCurrent(HomeMedicationFrame);

		// WebElement frame =
		// CommonLib.getElement(By.xpath("//iframe[contains(@src='/OptimumClinicals/medication-reconciliation-entry.action?']"));
		// CommonLib.setFrameFromCurrent(frame);
	}

	public void setDrugName() {
		try {
			while (CommonLib.getElement(By.xpath("//input[@id='medicationIDList']")).getAttribute("value").isEmpty()) {
				String selectedDrugName = CommonLib.selectDojoListValue("medicationIDList", "");
				HashTableRepository.setHash("selectedDrugName", selectedDrugName);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setPhysicianName() {
		try {
			while (CommonLib.getElement(By.xpath("//input[@id='orderingPhysicianList']")).getAttribute("value").isEmpty()) {
				String selectedDrugName = CommonLib.selectDojoListValue("orderingPhysicianList", "");
				HashTableRepository.setHash("selectedPhysicianName", selectedDrugName);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setVolume() {
		int i = 0;
		while (i < 2) {
			try {

				String selectedVolume = CommonLib.selectDojoListValue("medRecDoseVolumeUnit", "");
				HashTableRepository.setHash("selectedVolume", selectedVolume);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

	public void setFrequency() {
		try {
			while (CommonLib.getElement(By.xpath("//input[@id='sigList']")).getAttribute("value").isEmpty()) {
				String selectedFrequency = CommonLib.selectDojoListValue("sigList", "");
				if (selectedFrequency.contains("PRN")) {

					CommonLib.selectDojoListValue("prnIndList", "");

				}
				HashTableRepository.setHash("selectedFrequency", selectedFrequency);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDrugText(String drug) {
		return CommonLib.getText(By.xpath("//div[@id='MEDGrid1']//span[contains(text(),'" + drug + "')]"));
	}

	// public List<WebElement> getHomeMedicationText() {
	// List<WebElement> drugList =
	// CommonLib.getElements(By.xpath("//div[@id='MEDGrid1']/div[2]//tbody//span"));
	// return drugList;
	// }

	public void setSourceInformation() {
		try {
			String selectedSourceInformation = CommonLib.selectDojoListValue("informationSourceList", "");
			HashTableRepository.setHash("selectedSourceInformation", selectedSourceInformation);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setSubmitHomeMedication() {
		CommonLib.clickButton(By.id("button.Submit_label"));
	}

	public void validateDrugName(String method) {
		WebElement we;
		if (method.equals("DS")) {
			we = CommonLib.getElement(By.xpath("//div[@id='DMEDGrid1']//div[2]//table/tbody/tr/td/span"));
		} else
			we = CommonLib.getElement(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));

		String homeMedication = we.getText();
		String drugName = HashTableRepository.getHash("selectedDrugName").substring(0, HashTableRepository.getHash("selectedDrugName").length() - 10);
		String frequency = HashTableRepository.getHash("selectedFrequency").substring(0, 10);
		if (homeMedication.contains(drugName) && homeMedication.contains(HashTableRepository.getHash("selectedVolume")) && homeMedication.contains(frequency)) {
			CustomReporter.MessageLogger("Home medication drug is added.", status.Pass);
		} else
			CustomReporter.MessageLogger("Home medication drug is not added.", status.Fail);

	}

	public void editHomeMedication(String method) {
		if (method.equals("DS")) {
			CommonLib.clickButton(By.xpath("//div[@id='DMEDGrid1']//div[2]//table/tbody/tr/td/span"));
		} else {
			clickGrid("IMM");
			// WebElement xy =
			// CommonLib.getElement(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));
			CommonLib.clickButton(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));
		}

	}

	public void changeFrequency() {
		try {
			String changedFrequency = CommonLib.selectDojoDropDownByKeyDownNumber("sigList", 3);
			HashTableRepository.setHash("changedFrequency", changedFrequency);

			if (changedFrequency.equals(""))
				changedFrequency = CommonLib.selectDojoDropDownByKeyDownNumber("sigList", 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> sub = CommonLib.getElements(By.id("button.Submit_label"));
		sub.get(sub.size() - 1).click();
		// CommonLib.clickButton(By.id("button.Submit_label"));
	}

	public void validateEditHomeMedication(String method) {
		WebElement we;
		if (method.equals("DS")) {
			we = CommonLib.getElement(By.xpath("//div[@id='DMEDGrid1']//div[2]//table/tbody/tr/td/span"));
		} else
			we = CommonLib.getElement(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));
		String homeMedication = we.getText();
		String editedFrequency = HashTableRepository.getHash("changedFrequency").substring(0, 5);
		if (homeMedication.contains(editedFrequency)) {
			CustomReporter.MessageLogger("Home medication drug is edited.", status.Pass);
		} else
			CustomReporter.MessageLogger("Home medication drug is not edited.", status.Fail);

	}

	public void deleteHomeMedication(String method) {
		if (method.equals("DS")) {
			CommonLib.clickButton(By.xpath("//div[@id='DMEDGrid1']//div[2]//table/tbody/tr/td/span"));
		} else
			CommonLib.clickButton(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));
		CommonLib.clickButton(By.id("delete_label"));
		List<WebElement> drugList;
		if (method.equals("DS")) {
			drugList = CommonLib.getElements(By.xpath("//div[@id='DMEDGrid1']//div[2]//table/tbody/tr/td/span"));
		} else
			drugList = CommonLib.getElements(By.xpath("//div[@id='MEDGrid1']//div[2]//div//div//div//div[1]//span"));
		if (drugList.size() != 0) {
			String homeMedication = drugList.get(0).getText();
			String drugName = HashTableRepository.getHash("selectedDrugName").substring(0, HashTableRepository.getHash("selectedDrugName").length() - 10);
			if (homeMedication.contains(drugName)) {
				CustomReporter.MessageLogger("Home medication drug is not deleted.", status.Fail);
			} else
				CustomReporter.MessageLogger("Drug is deleted", status.Pass);

		} else
			CustomReporter.MessageLogger("Drug is deleted", status.Pass);
	}

	public void medicationReconcilliation() {
		CommonLib.clickButton(By.id("mrId"));
	}

	public void setMedRecFrame() {
		WebElement MedRecFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'medication-reconciliation.action?')][last()]"));
		CommonLib.setFrameFromCurrent(MedRecFrame);
		try {
			CommonLib.clickButton(By.xpath("//span[@id='drFirstSetupDialog_title']//following-sibling::span"));
		} catch (Exception e) {

		}
	}

	public void addMedRec() {
		CommonLib.clickButton(By.id("add_label"));
	}

	public void setFloatingFrame() {
		WebElement FloatingFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'medication-reconciliation-entry.action?')][last()]"));
		CommonLib.setFrameFromCurrent(FloatingFrame);

		try {
			String MedRecDrug = CommonLib.selectDojoListValue("medicationIDList", "");
			HashTableRepository.setHash("MedRecDrug", MedRecDrug);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
			String MedRecVolume = CommonLib.selectDojoListValue("medRecDoseVolumeUnit", "");
			HashTableRepository.setHash("MedRecVolume", MedRecVolume);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String MedRecFreq = CommonLib.selectDojoListValue("sigList", "");
			if (MedRecFreq.contains("PRN")) {

				CommonLib.selectDojoListValue("prnIndList", "");

			}
			HashTableRepository.setHash("MedRecFreq", MedRecFreq);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String MedRecSOI = CommonLib.selectDojoListValue("informationSourceList", "");
			HashTableRepository.setHash("MedRecSOI", MedRecSOI);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CommonLib.clickButton(By.id("button.Submit_label"));

	}

	public void validateMedRec() {
		WebElement we = CommonLib.getElement(By.xpath("//div[@id='homeMedsGrid']//div[2]//div[1]//div//div//div//div//table//tbody//tr//td//a"));
		String medRec = we.getText();
		String MedRecDrug = HashTableRepository.getHash("MedRecDrug").substring(0, HashTableRepository.getHash("MedRecDrug").length() - 10);
		if (medRec.contains(MedRecDrug)) {
			CustomReporter.MessageLogger("Home medication drug is added.", status.Pass);
		} else
			CustomReporter.MessageLogger("Home medication drug is not added.", status.Fail);
		CommonLib.clickButton(By.className("iframeDialogCloseIcon"));
		setMainFrame();

	}

	public void setLabSummaryFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'/OptimumClinicals/lab-test-summary.action?')]"));

		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));

	}

	public void setTextFrameForLabRad(String page) {
		List<WebElement> frame;
		if (page.equals("DS")) {
			frame = CommonLib.getElements(By.xpath("//iframe[@id='dpeEditor_iframe']"));
		} else {
			frame = CommonLib.getElements(By.xpath("//iframe[@id='resEditor_iframe']"));
		}

		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));

	}

	public void setTextLabRad() {
		String random = CommonLib.RandomText(1, 3);
		CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"), random);
		HashTableRepository.setHash("LabRadText", random);
		setMainFrame();
	}

	public ArrayList<String> getLabSummary() {
		List<WebElement> labSummary = CommonLib.getElements(By.xpath("//div[@id='labTestSummaryGrid']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[@idx='1']"));
		ArrayList<String> listLabSummary = new ArrayList<String>();
		for (WebElement lab : labSummary) {
			listLabSummary.add(lab.getText());
		}
		return listLabSummary;
	}

	public boolean isLabDataPresent() {
		List<WebElement> labSummary = CommonLib.getElements(By.xpath("//div[@id='labTestSummaryGrid']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[2]"));
		if (labSummary.isEmpty())
			return false;
		else
			return true;
	}

	public List<WebElement> getAddedSummaryList() {
		return CommonLib.getElements(By.xpath("//div[@id='RESGrid1']/div[2]//tbody//td"));
	}

	public List<WebElement> getAddedSummaryPNList() {
		return CommonLib.getElements(By.xpath("//div[@id='OBJGrid1']/div[2]//tbody//td"));
	}

	public void selectAllLabSummary() {
		CommonLib.clickButton(By.id("labTestSummaryGrid_rowSelector_-1"));
	}

	public void uncheckLabSummary(int index) {
		CommonLib.clickButton(By.id("labTestSummaryGrid_rowSelector_" + index));
	}

	public void clickPNContainer(int index) {
		if (index != 4)
			CommonLib.clickButton(By.id("OBJContainer" + index + "_titleBarNode"));
		else
			CommonLib.clickButton(By.id("labRadNotes"));

	}

	public void clickRESContainor(int index) {
		if (index != 4)
			CommonLib.clickButton(By.id("RESContainer" + index + "_titleBarNode"));
		else
			CommonLib.clickButton(By.id("labRadNotes"));

	}

	public void setResultSummaryFrame() {
		List<WebElement> frames = CommonLib.getElements(By.xpath("//iframe[contains(@src,'ResultSummary.do?')]"));
		CommonLib.setFrameFromCurrent(frames.get(frames.size() - 1));
	}

	public void getMicrobiology() {
		List<WebElement> listMicro = CommonLib.getElements(By.xpath("//div[@id='mrResults']//span[contains(@style,'color: rgb(0, 0, 255)')]"));
		ArrayList<String> text = new ArrayList<>();
		for (WebElement micro : listMicro) {
			text.add(micro.getText());
		}
		System.out.println(text);
	}

	public void clickOnGeneralLabTestSummary() {
		CommonLib.getElement(By.xpath("//span[text()='General Lab Test Summary']")).click();
	}

	public void clickOnLabRadNotes() {
		CommonLib.getElement(By.xpath("//span[text()='Lab/Rad Notes']")).click();
	}

	public void setTextLabRadNotes() {

	}

	public boolean isMicrobiologyDataPresent() {
		if (CommonLib.getElements(By.id("0_CheckMr")).size() > 0) {
			return true;
		} else
			return false;
	}

	public void checkMicrobiologyData() {
		CommonLib.setBoolFields(true, By.xpath("//input[@id='CheckAllMicro']"));
		List<WebElement> li = CommonLib.getElements(By.xpath("//span[contains(@style,'color: rgb(0, 0, 255)')]"));
		String text = "";
		for (WebElement e : li) {
			text = text + e.getText();
		}
		HashTableRepository.setHash("MicrobiologyText", text);
	}

	public void validateMicrobiologyDataOnMainScreen() {
		CommonLib.getElement(By.xpath("//span[text()='Microbiology Results']")).click();
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='RESGrid2Container']//span[contains(@style,'color: rgb(0, 0, 255)')]"));
		String text = "";
		for (WebElement e : li) {
			text = text + e.getText();
		}
		if (text.equals(HashTableRepository.getHash("MicrobiologyText"))) {
			CustomReporter.MessageLogger("Microbiology Results have been sucessfully added ", status.Pass);
		} else
			CustomReporter.MessageLogger("Microbiology Results have not been added ", status.Fail);
	}

	public void setLabNotes() {
		String classAt = CommonLib.getElement(By.id("labRadNotes_titleBarNode")).getAttribute("class");
		if (!classAt.contains("dijitOpen"))
			CommonLib.clickButton(By.id("labRadNotes_titleBarNode"));
		HashTableRepository.setHash("labNotesStudies", CommonLib.RandomText(1, 20));
		CommonLib.setData(By.id("dijitEditorBody"), HashTableRepository.getHash("labNotesStudies"));
	}

	// public void clickCloseCC() {
	// CommonLib.clickButton(By.xpath("//div[@id='findPatientToolbar']//span//span//span//span[@id='close_label']"));
	// }

	public void radioLogyTestCases() {
		CommonLib.clickButton(By.id("RADLabId"));
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src='/OptimumClinicals/ResultSummary.do?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
		String title = CommonLib.getText(By.className("iframeDialogTitle"));
		if (title.equals("Result Summary"))
			CustomReporter.MessageLogger("Result Summary title is as expected.", status.Pass);
		else
			CustomReporter.MessageLogger("Result Summary pop up is not as expected.", status.Fail);
		CommonLib.clickButton(By.className("iframeDialogCloseIcon"));
		setMainFrame();

	}

	public void labTestSummary() {
		CommonLib.clickButton(By.id("genLabId"));
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src='/OptimumClinicals/lab-test-summary.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
		String title = CommonLib.getText(By.className("iframeDialogTitle"));
		if (title.equals("Lab Test Summary"))
			CustomReporter.MessageLogger("Lab test summary title is as expected.", status.Pass);
		else
			CustomReporter.MessageLogger("Lab test summary pop up is not as expected.", status.Fail);
		CommonLib.clickButton(By.className("iframeDialogCloseIcon"));
		setMainFrame();

	}

	public void setSubmitMicrobiology() {
		CommonLib.clickButton(By.xpath("//span[@id='submit_label']"));
	}

	public void setCancelMicrobiology() {
		CommonLib.clickButton(By.xpath("//div[text()='Cancel']"));
	}

	public void closeAll() {
		CommonLib.clickButton(By.xpath("//span[text()='Close All']"));
	}

	public void checkIfSigned() {
		String text = CommonLib.getElement(By.xpath("//div[@id='hpSectionBar']/div/span")).getText();
		text = text.trim();
		String[] ar = text.split(":");
		int size = ar.length;
		if (size != 1) {
			CommonLib.clickButton(By.xpath("//span[@id='entireHistory_label']"));
			setEntireHistoryFrame();
			CommonLib.changeimplicitwait(30);
			CommonLib.clickButton(By.xpath("//input[@id='displayTypeAll']"));
			// List<WebElement> l = CommonLib
			// .getElements(By.xpath("//div[@id='entireHistoryGrid']/div[2]//table/tbody/tr"));
			// if (l.size() > 0) {
			// try {
			// WebDriverWait w = new WebDriverWait(CommonLib.GetDriver(), 10);
			// w.until(ExpectedConditions
			// .elementToBeClickable(By.xpath("//div[@id='entireHistoryGrid']/div[2]//table/tbody/tr")));
			// } catch (Exception e) {
			//
			// }
			// }
			//
			// CommonLib.resetImplicitWait();
			CommonLib.clickButton(By.xpath("//div[@id='entireHistoryGrid']/div[2]//table/tbody/tr"));
			if (CommonLib.getElement(By.xpath("//span[@id='delete_label']")).isEnabled())
				CommonLib.clickButton(By.xpath("//span[@id='delete_label']"));
			// else {
			// CommonLib.clickButton(By.xpath("//input[@id='displayTypeVisit']"));
			// if
			// (CommonLib.getElement(By.xpath("//span[@id='delete_label']")).isEnabled())
			// CommonLib.clickButton(By.xpath("//span[@id='delete_label']"));
			// else {
			//
			// }
			// }
			try {
				CommonLib.clickButton(By.xpath("//span[text()='OK']"));
			} catch (Exception e) {
				CommonLib.clickButton(By.xpath("//input[@id='displayTypeVisit']"));
				if (CommonLib.getElement(By.xpath("//span[@id='delete_label']")).isEnabled()) {
					CommonLib.clickButton(By.xpath("//span[@id='delete_label']"));
					CommonLib.clickButton(By.xpath("//span[text()='OK']"));
				} else {

				}
			}

		}
		setMainFrame();
	}

	public void setEntireHistoryFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//*[contains(@src,'history-and-physical.action?action=entireHistory')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void getPastMedicalHistoryText() {
		String finalText = "";
		List<WebElement> test = CommonLib.getElements(By.xpath("//div[@id='PMHGrid1']/div[2]"));
		if (test.size() != 0) {
			List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='PMHGrid1']/div[2]//table/tbody/tr/td[4]/span"));
			for (WebElement e : li) {
				String text = e.getText();
				String arr[] = text.split(" ");
				finalText += arr[1];
			}
			HashTableRepository.setHash("PastMedicalHistoryText", finalText);
		}
	}

}
