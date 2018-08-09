package historyAndPhysical.testcases;

import java.util.ArrayList;

import org.openqa.selenium.By;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import common.TableActions;
import consultationNote.pages.CnPages;
import historyAndPhysical.pages.HistoryAndPhysicial;

public class HnPTestCases {
	HistoryAndPhysicial obj = new HistoryAndPhysicial();

	public HnPTestCases(String page) {
		if (page.equals("CN") || page.equals("PN")) {

		} else if (page.equals("HP"))
			obj.gotoHistoryAndPhysicial();
	}

	public void checkIfSigned() {
		obj.checkIfSigned();
	}

	public void impressionTestCases(String method) {
		boolean flag = false;
		obj.getAdmitDate();
		if (method.equals("DS")) {
			obj.actionOnGrid("DDIAG", "Add");
		} else
			obj.actionOnGrid("DIAG", "Add");
		obj.setDiagnosisStatus("A");
		obj.setDiagnosisDate(obj.getPastDate());
		obj.setDiagnosisTIme();

		// obj.verifyErrorDiagnosis(li);
		obj.setRandomDiagnosis();
		// obj.submitDiagnosis();
		TableActions tableObj = new TableActions();
		if (method.equals("DS")) {
			flag = tableObj.validateAddition(obj.getImpressionListDS("DDIAG"), HashTableRepository.getHash("diagnosisDate"));
		} else {
			flag = tableObj.validateAddition(obj.getImpressionList("DIAG"), HashTableRepository.getHash("diagnosisDate"));
		}
		if (flag) {
			// List<String> li2 = obj.listOfDiagnosisSelected();
			obj.clickImpression(HashTableRepository.getHash("diagnosisDate"), method);
			obj.actions("Edit");
			obj.setDiagnosisStatus("C");
			obj.setEditDiagnosisReason();
			obj.submitDiagnosis();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			if (method.equals("DS")) {
				obj.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosis"), "DS");
			} else
				obj.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosis"), "medical");
			obj.clickImpression(HashTableRepository.getHash("diagnosisDate"), method);
			obj.actions("Resolve");
			if (method.equals("DS")) {
				tableObj.validateDeletition(obj.getImpressionListDS("DDIAG"), HashTableRepository.getHash("diagnosisDate"));
			} else
				tableObj.validateDeletition(obj.getImpressionList("DIAG"), HashTableRepository.getHash("diagnosisDate"));
			if (method.equals("DS")) {
				obj.actionOnGrid("DDIAG", "Add");
			} else
				obj.actionOnGrid("DIAG", "Add");

			obj.setDiagnosisStatus("A");
			obj.setDiagnosisDate(obj.getPastDate());
			// obj.verifyErrorDiagnosis(li2);
			obj.setDiagnosisTIme();
			obj.setRandomDiagnosis();
			// obj.submitDiagnosis();
			obj.clickImpression(HashTableRepository.getHash("diagnosisDate"), method);
			obj.actions("Inactivate");
			obj.setInactivateReason();
			obj.submitInactivate();
			if (method.equals("DS")) {
				tableObj.validateDeletition(obj.getDateDiagnosis(), HashTableRepository.getHash("diagnosisDate"));
			} else
				tableObj.validateDeletition(obj.getPMHdateList(), HashTableRepository.getHash("diagnosisDate"));
		}
	}

	public void chiefComplaintTestCases() {

		if (!obj.isGridOpen("CC"))
			obj.clickGrid("CC");
		obj.clickChiefComplaint();
		obj.selectChiefComplaint("1");
		obj.submitChiefComplaint();
		if (obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint1")))
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " added", status.Pass);
		else
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " is not added", status.Fail);
		obj.setAdditionalComplaints(CommonLib.RandomText(1, 10));
		obj.clickChiefComplaint();
		obj.selectChiefComplaint("2");
		obj.submitChiefComplaint();
		if (obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint2")) && obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint1")))
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint2") + " edited.", status.Pass);
		else
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint2") + " is not edited", status.Fail);
		obj.clickChiefComplaint();
		obj.deSelectChiefComplaint(HashTableRepository.getHash("selectedChiefComplaintIndex1"));
		obj.submitChiefComplaint();
		if (obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint2")) && !obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint1")))
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " deleted.", status.Pass);
		else
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " is not deleted", status.Fail);
		// ****************************************//
		if (!obj.isGridOpen("CC"))
			obj.clickGrid("CC");
		obj.clickChiefComplaint();
		obj.selectChiefComplaint("1");
		obj.deSelectChiefComplaint(HashTableRepository.getHash("selectedChiefComplaintIndex1"));
		obj.submitChiefComplaint();
		if (obj.getChiefComplaintValue().contains(HashTableRepository.getHash("selectedChiefComplaint1")))
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " added", status.Pass);
		else
			CustomReporter.MessageLogger("Selected chief complaint : " + HashTableRepository.getHash("selectedChiefComplaint1") + " is not added", status.Fail);
	}

	public void pastSurgicalHistoryTestCases(String method) {
		boolean flag = false;
		obj.getAdmitDate();
		if (method.equals("PROC"))
			obj.actionOnGrid("PROC", "Add");
		else
			obj.actionOnGrid("PSH", "Add");
		obj.setProcedure();
		obj.setProcedureDate(obj.getPastDate());
		obj.setProcedureTime(CommonLib.systemTime("HH:mm"));
		obj.setProcedurePhysician();
		obj.submitProcedure();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		TableActions tableObj = new TableActions();
		if (method.equals("PROC")) {
			flag = tableObj.validateAddition(obj.getProcList(), HashTableRepository.getHash("procedureDate"));

		} else {
			flag = tableObj.validateAddition(obj.getPastSurgicalList(), HashTableRepository.getHash("procedureDate"));
		}
		if (flag) {
			obj.clickSurgicalHistory(HashTableRepository.getHash("procedureDate"));
			obj.actions("Edit");
			obj.setSurgicalHistoryEditReason();
			obj.setProcedureDate(obj.getPastDate());
			obj.submitProcedure();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			tableObj.validateAddition(obj.getPastSurgicalList(), HashTableRepository.getHash("procedureDate"));
			obj.clickSurgicalHistory(HashTableRepository.getHash("procedureDate"));
			obj.actions("Resolve");
			tableObj.validateDeletition(obj.getPastSurgicalList(), HashTableRepository.getHash("procedureDate"));
			if (!method.equals("CN")) {
				obj.actionOnGrid("PSH", "Add");

				// obj.setProcedureMannual();
				obj.setProcedureDate(obj.getPastDate());
				obj.setProcedureTime(CommonLib.systemTime("HH:mm"));
				obj.setProcedure();
				obj.setProcedurePhysician();
				obj.submitProcedure();
				if (method.equals("CN")) {
					CnPages obj = new CnPages();
					obj.setConsultationFrame();
				}
				obj.clickSurgicalHistory(HashTableRepository.getHash("procedureDate"));
				obj.actions("Inactivate");
				obj.setInactivateReason();
				obj.submitInactivate();
				if (method.equals("CN")) {
					CnPages obj = new CnPages();
					obj.setConsultationFrame();
				}
				tableObj.validateDeletition(obj.getPastSurgicalList(), HashTableRepository.getHash("procedureDate"));

			}
		}
		// ***********************//
		obj.getAdmitDate();
		obj.actionOnGrid("PSH", "Add");
		obj.setProcedure();
		obj.setProcedureDate(obj.getPastDate());
		obj.setProcedureTime(CommonLib.systemTime("HH:mm"));
		obj.setProcedurePhysician();
		obj.submitProcedure();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}

		flag = tableObj.validateAddition(obj.getPastSurgicalList(), HashTableRepository.getHash("procedureDate"));
	}

	public void FamilyMedicalHistoryTestCases(String method) {
		obj.actionOnGrid("FMH", "Add");
		obj.selectFamilyMember();
		obj.actions("Add");
		obj.addFamilyDisease();
		TableActions tableObj = new TableActions();
		tableObj.validateAddition(obj.getFamilyDiseaceList(), HashTableRepository.getHash("SelectedFamilyDisease"));
		obj.submit();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		boolean flag = tableObj.validateAddition(obj.getFamilyMemberList(), HashTableRepository.getHash("selectedFamilyMember"));

		if (flag) {
			obj.clickFamilyMember(HashTableRepository.getHash("selectedFamilyMember"));
			obj.actions("Edit");
			obj.setFamilyMemberFrame();
			obj.editFMH();

			obj.submit();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			obj.validateEditFMH(HashTableRepository.getHash("selectedFamilyMember"));
			obj.clickFamilyMember(HashTableRepository.getHash("selectedFamilyMember"));
			obj.actions("Inactivate");
			obj.inactivateFamilyMember();
			obj.submitInactivateFamily();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			tableObj.validateDeletition(obj.getFamilyMemberList(), HashTableRepository.getHash("selectedFamilyMember"));
		}
		// **********************************************//
		obj.actionOnGrid("FMH", "Add");
		obj.selectFamilyMember();
		obj.actions("Add");
		obj.addFamilyDisease();
		tableObj.validateAddition(obj.getFamilyDiseaceList(), HashTableRepository.getHash("SelectedFamilyDisease"));
		obj.submit();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		tableObj.validateAddition(obj.getFamilyMemberList(), HashTableRepository.getHash("selectedFamilyMember"));
	}

	public void immunizationTestCases(String method) {
		obj.actionOnGrid("IMM", "Add");
		obj.setImmunization();

		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		TableActions tableObj = new TableActions();
		boolean flag = tableObj.validateAddition(obj.getAddedImmunization(), HashTableRepository.getHash("selectedImmunization"));
		if (flag) {
			obj.clickImmunization(HashTableRepository.getHash("selectedImmunization"));
			obj.actions("Edit");
			obj.setImmunizationFrame();
			obj.editImmunization();
			obj.setSubmitImmunization();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			obj.clickImmunization(HashTableRepository.getHash("selectedImmunization"));
			obj.actions("Delete");
			obj.deleteImmunization();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			tableObj.validateDeletition(obj.getAddedImmunization(), HashTableRepository.getHash("selectedImmunization"));
		}

	}

	public void pastMedicalHistoryTestCases(String method) {
		obj.getAdmitDate();
		obj.actionOnGrid("PMH", "Add");

		// obj.verifyErrorDiagnosis(li);
		obj.setDiagnosisStatus("C");
		obj.setDiagnosisDate(obj.getPastDate());
		obj.setDiagnosisTIme();
		obj.setRandomDiagnosis();
		// obj.submitDiagnosis();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		TableActions tableObj = new TableActions();
		boolean flag = tableObj.validateAddition(obj.getPMHdateListCN(), HashTableRepository.getHash("diagnosisDate"));
		// int index = tableObj.listIndex(obj.getPMHdateList(),
		// HashTableRepository.getHash("diagnosisDate"));
		if (flag) {
			obj.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosis"), "medical");
			obj.clickDiagnosis(HashTableRepository.getHash("diagnosisDate"));
			obj.actions("Edit");
			obj.setDiagnosisStatus("A");
			obj.setEditDiagnosisReason();
			obj.submitDiagnosis();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			// String one = HashTableRepository.getHash("diagnosisDate");
			// String two =
			// HashTableRepository.getHash("selectedDiagnosisStatus");
			try {
				obj.validateDiagnosis(HashTableRepository.getHash("diagnosisDate"), HashTableRepository.getHash("selectedDiagnosisStatus"), "medical");
				obj.clickDiagnosis(HashTableRepository.getHash("diagnosisDate"));
				obj.actions("Resolve");
				obj.validateResolve(HashTableRepository.getHash("diagnosisDate"));
				obj.clickDiagnosis(HashTableRepository.getHash("diagnosisDate"));
				obj.actions("Inactivate");
				obj.validateInactivate(HashTableRepository.getHash("selectedDiagnosis"));
				tableObj.validateDeletition(obj.getPMHdateList(), HashTableRepository.getHash("diagnosisDate"));
			} catch (Exception e) {

			}

		}
		// *********************************//
		obj.getAdmitDate();
		obj.actionOnGrid("PMH", "Add");

		// obj.verifyErrorDiagnosis(li);
		obj.setDiagnosisStatus("C");
		obj.setDiagnosisDate(obj.getPastDate());
		obj.setDiagnosisTIme();
		obj.setRandomDiagnosis();
		// obj.submitDiagnosis();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		tableObj.validateAddition(obj.getPMHdateListCN(), HashTableRepository.getHash("diagnosisDate"));

	}

	public void historyOfPresentIllness(String page) {
		if (page.equals("DS")) {
			obj.actionOnGrid("DHPI", "Add");
		} else
			obj.actionOnGrid("HPI", "Add");
		obj.setHistoryFrame();
		if (obj.historyPresentIllness() == true) {
			obj.selectHistoryIllness(true);
			obj.submitReview();
			obj.setMainFrame();
			obj.validateHIP(page);
			obj.setMainFrame();
			if (page.equals("CN"))
				obj.setConsultationFrame();
			obj.actionOnGrid("HPI", "Edit");
			obj.setHistoryFrame();
			obj.selectHistoryIllness(false);
			obj.submitReview();
			obj.setMainFrame();
			if (page.equals("CN"))
				obj.setConsultationFrame();
		} else
			obj.emptyHIP(page);
	}

	public void physicialExamTestCases(String method) {
		obj.actionOnGrid("PE", "Add");
		obj.setPhysicialFrame();
		if (obj.isReviewPanePresent("physicial")) {
			obj.selectPhysicial("Y");
			obj.mandate("");
			obj.submitReview();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			if (!obj.isGridOpen("PE"))
				obj.clickGrid("PE");
			obj.validatePhysicial("normal");
			obj.actionOnGrid("PE", "Edit");
			obj.setPhysicialFrame();
			obj.selectPhysicial("N");
			obj.mandate("");
			obj.submitReview();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			if (!obj.isGridOpen("PE"))
				obj.clickGrid("PE");
			obj.validatePhysicial("abnormal");
		}
	}

	public void reviewSystemTestCases(String method) {
		try {
			obj.actionOnGrid("ROS", "Add");
			obj.setReviewSystemFrame();
			if (obj.isReviewPanePresent("review")) {
				obj.selectReview("Yes");
				obj.mandate("review system");
				obj.submitReview();
				obj.setMainFrame();
				if (method.equals("CN")) {
					CnPages obj = new CnPages();
					obj.setConsultationFrame();
				}
				obj.validateReview("Reports");
				obj.actionOnGrid("ROS", "Edit");
				obj.setReviewSystemFrame();
				obj.selectReview("No");
				obj.submitReview();
				obj.setMainFrame();
				if (method.equals("CN")) {
					CnPages obj = new CnPages();
					obj.setConsultationFrame();
				}
				if (!obj.isGridOpen("ROS"))
					obj.clickGrid("ROS");
				obj.validateReview("Denies");
			}
		} catch (Exception e) {
			MenuNavigation.menuNav("HistoryPhysicial");
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
		}
	}

	public void planTestCases(String method) {
		obj.clickGrid("PLN");
		// obj.setTextPlan();
		obj.clickOnOrderEntryLink();
		obj.switchFrameToOrderEntryPopUp();
		// obj.handleOrderEntryError();
		boolean check = obj.getTitleOrderEntryPopUp();
		// obj.handleOrderEntryError();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		if (check) {
			CustomReporter.MessageLogger("The title is 'Order Entry'", status.Pass);
		} else
			CustomReporter.MessageLogger("The title doesnt match", status.Fail);
	}

	public void ccAddTestCase(String method) {
		boolean flag = true;
		obj.actionOnGrid("CC_" + method, "Add");
		obj.changeFrameToPopUpCC();
		obj.clickAddPatient_sPhysician();
		obj.generateRandomLetter();
		obj.choosePhysician();
		while (flag) {
			obj.selectPhysicianType();
			flag = obj.checkIfSamePhysicianWithSameRoleExists();
		}
		boolean check = obj.validatePopUp();
		obj.clickCloseCC();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		boolean check2 = obj.validate(method);
		if (check && check2) {
			CustomReporter.MessageLogger("Physician has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been added", status.Fail);

	}

	public void homeMedicationTestCases(String method) {
		if (method.equals("DS")) {
			obj.actionOnGrid("DMED", "Add");
		} else
			obj.actionOnGrid("MED", "Add");
		obj.setHomeMedicationFrame();
		obj.setDrugName();
		obj.setVolume();
		obj.setFrequency();
		if (method.equals("DS")) {
			obj.setPhysicianName();
		} else
			obj.setSourceInformation();
		obj.setSubmitHomeMedication();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		obj.validateDrugName(method);

		obj.editHomeMedication(method);
		obj.actions("Edit");
		obj.setHomeMedicationFrame();
		obj.changeFrequency();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}

		obj.validateEditHomeMedication(method);
		obj.deleteHomeMedication(method);
		if (!method.equals("DS")) {
			obj.medicationReconcilliation();
			obj.setMedRecFrame();
			obj.addMedRec();
			obj.setFloatingFrame();
			obj.setMainFrame();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
			obj.setMedRecFrame();
			obj.validateMedRec();
			if (method.equals("CN")) {
				CnPages obj = new CnPages();
				obj.setConsultationFrame();
			}
		}

	}

	public void microbiologyResult(String page) {
		if (page.equals("DS")) {
			if (!obj.isGridOpen("DPE"))
				obj.clickGrid("DPE");

		} else {
			if (!obj.isGridOpen("RES"))
				obj.clickGrid("RES");
		}
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
		obj.actions("Add");
		obj.setLabSummaryFrame();
		if (obj.isMicrobiologyDataPresent() == true) {
			obj.checkMicrobiologyData();
			obj.setSubmitMicrobiology();
			obj.setMainFrame();
			obj.validateMicrobiologyDataOnMainScreen();

		} else {
			obj.setCancelMicrobiology();
			obj.setMainFrame();
			CustomReporter.MessageLogger("No microbiology record present", status.Warning);
		}
	}

	public void radioLogyTestCases(String page) {
		if (page.equals("DS")) {
			if (!obj.isGridOpen("DPE"))
				obj.clickGrid("DPE");

		} else {
			if (!obj.isGridOpen("RES"))
				obj.clickGrid("RES");
		}
		obj.clickRESContainor(3);
		obj.actions("Add");
	}

	public void generalLabTestCases(String page) {
		if (page.equals("DS")) {
			if (!obj.isGridOpen("DPE"))
				obj.clickGrid("DPE");

		} else {
			if (!obj.isGridOpen("RES"))
				obj.clickGrid("RES");
		}
		obj.clickOnGeneralLabTestSummary();
		obj.actions("Add");
		obj.setLabSummaryFrame();
		if (obj.isLabDataPresent()) {
			ArrayList<String> labList = obj.getLabSummary();
			obj.selectAllLabSummary();
			obj.submitReview();
			obj.setMainFrame();
			obj.clickOnGeneralLabTestSummary();
			TableActions tableObj = new TableActions();
			boolean flag = false;
			for (String lab : labList) {
				flag = tableObj.validateAddition(obj.getAddedSummaryList(), lab);
			}
			if (flag) {
				obj.clickRESContainor(1);
				obj.actions("Edit");
				obj.setLabSummaryFrame();
				obj.uncheckLabSummary(labList.size() - 1);
				obj.submitReview();
				obj.setMainFrame();
				obj.clickOnGeneralLabTestSummary();
				tableObj.validateDeletition(obj.getAddedSummaryList(), labList.get(labList.size() - 1));
			}

		} else {
			obj.cancel();
			obj.setMainFrame();
			CustomReporter.MessageLogger("No microbiology record present", status.Warning);
		}
	}

	public void socialHistoryTestCases() {

		if (!obj.isGridOpen("SH"))
			obj.clickGrid("SH");
		obj.setSocialHistory("T");
		obj.actions("Add");
		obj.setTobaccco();
		obj.setMainFrame();
		if (!obj.isGridOpen("SH"))
			obj.clickGrid("SH");
		String tobaccoText = obj.getTobaccoText("2");
		if (tobaccoText.contains("Former Tobacco User") && tobaccoText.contains(HashTableRepository.getHash("selectedTobacco")))
			CustomReporter.MessageLogger("tobacco added successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Tobacco is not added", status.Fail);
		obj.setSocialHistory("T");
		obj.actions("Edit");
		obj.setTobaccoFrame();
		obj.setTboaccoEdit();
		obj.clickSubmitTobacco();
		obj.setMainFrame();
		String text = HashTableRepository.getHash("AmountSet");
		tobaccoText = obj.getTobaccoText("2");
		if (tobaccoText.contains("Former Tobacco User") && tobaccoText.contains(HashTableRepository.getHash("AmountSet")))
			CustomReporter.MessageLogger("tobacco edited successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Tobacco is not edited", status.Fail);

		if (!obj.isGridOpen("SH"))
			obj.clickGrid("SH");

		obj.setSocialHistory("G");

		obj.actions("Add");
		obj.setGeneralSocial();
		obj.setMainFrame();
		String generalText = obj.getSocialText();
		if (generalText.contains(HashTableRepository.getHash("selectedEducation")) && generalText.contains(HashTableRepository.getHash("numChiildren")))

			CustomReporter.MessageLogger("social added successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("socail is not added", status.Fail);

		obj.setSocialHistory("G");

		obj.actions("Edit");
		obj.setGeneralFrame();
		obj.setGeneralEducation();
		obj.submitReview();
		obj.setMainFrame();
		// String generalText = obj.getSocialText();
		generalText = obj.getSocialText();
		if (generalText.contains(HashTableRepository.getHash("selectedEducation")))

			CustomReporter.MessageLogger("social edited successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("socail is not edited", status.Fail);

		if (!obj.isGridOpen("SH"))
			obj.clickGrid("SH");

		obj.setSocialHistory("A");

		obj.actions("Add");
		obj.setTobaccoFrame();
		obj.setAlcohol();
		obj.setMainFrame();
		String AlText = obj.getTobaccoText("3");
		if (AlText.contains(HashTableRepository.getHash("selectedAlcohol")) && AlText.contains(HashTableRepository.getHash("AlAmount")))

			CustomReporter.MessageLogger("Alcohol added successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Alcohol is not added", status.Fail);

		obj.setSocialHistory("A");

		obj.actions("Edit");
		obj.setTobaccoFrame();
		obj.setAlAmount();
		obj.submitAl();
		obj.setMainFrame();
		AlText = obj.getTobaccoText("3");
		if (AlText.contains(HashTableRepository.getHash("AlAmount")))

			CustomReporter.MessageLogger("Alcohol edited successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Alcohol is not edited", status.Fail);

		obj.setSocialHistory("S");

		obj.actions("Add");
		obj.setTobaccoFrame();
		obj.setDrug();
		obj.setMainFrame();
		String drug = obj.getTobaccoText("4");
		if (drug.contains(HashTableRepository.getHash("selectedSocialDrug")) && drug.contains(HashTableRepository.getHash("socialDrugAmount")))

			CustomReporter.MessageLogger("Drug added successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Drug is not added", status.Fail);
		obj.setSocialHistory("S");
		obj.actions("Edit");
		obj.setTobaccoFrame();
		obj.setDrugAmount();
		obj.submitDrugSocial();
		obj.setMainFrame();
		drug = obj.getTobaccoText("4");
		if (drug.contains(HashTableRepository.getHash("socialDrugAmount")))

			CustomReporter.MessageLogger("Drug added successfully.", status.Pass);
		else
			CustomReporter.MessageLogger("Drug is not added", status.Fail);
	}

	public void labRadNotes(String page) {
		if (page.equals("DS")) {
			if (!obj.isGridOpen("DPE"))
				obj.clickGrid("DPE");

		} else {
			if (!obj.isGridOpen("RES"))
				obj.clickGrid("RES");
		}
		// if (obj.isGridOpen("labRadNotes")) {
		// obj.clickOnLabRadNotes();
		// }
		obj.setTextFrameForLabRad(page);
		obj.setTextLabRad();

	}

	public void ccEditTestCase(String method) {
		obj.clickEditForTestcase();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		boolean check = obj.validateEdit(method);
		if (check) {
			CustomReporter.MessageLogger("Physician has been edited", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been edited", status.Fail);

	}

	public void ccDeleteTestCase() {
		obj.clickEditForTestcase();
		obj.clickDeleteCC();
		obj.clickOKForDelete();
		boolean check = obj.validatePopUp();
		obj.clickCloseCC();
		boolean check2 = obj.validate("HP");
		if (check == false && check2 == false) {
			CustomReporter.MessageLogger("Physician has been deleted", status.Pass);
		} else
			CustomReporter.MessageLogger("Physician has not been deleted", status.Fail);

	}

	public void allergiesAdd(String method) {
		if (!obj.isGridOpen("ALG"))
			obj.clickGrid("ALG");
		String allergies = obj.getAllergies();
		obj.actionOnGrid("ALG", "Add");
		obj.switchFrameToPopupAllergies();
		obj.selectAllergy(allergies);
		obj.selectSource();
		obj.setMainFrame();
		if (method.equals("CN")) {
			CnPages obj = new CnPages();
			obj.setConsultationFrame();
		}
		obj.checkReminderPopUp();
		boolean check = obj.allergyAddValidateScreen();
		if (check) {
			CustomReporter.MessageLogger("Allergy has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Allergy has not been added", status.Fail);

	}

	public void allergiesEdit(String method) {
		obj.allergyEditClick();
		obj.allergyEdit();
		boolean check = obj.allergyEditValidate(method);
		if (check) {
			CustomReporter.MessageLogger("Allergy has been edited", status.Pass);
		} else
			CustomReporter.MessageLogger("Allergy has not been edited", status.Fail);

	}

	public void allergiesDelete() {
		obj.allergyDeleteClick();
		boolean check = obj.allergyDeleteValidate();
		if (!check) {
			CustomReporter.MessageLogger("Allergy has been deleted", status.Pass);
		} else
			CustomReporter.MessageLogger("Allergy has not been deleted", status.Fail);

	}

	public void ammend() {
		obj.clickOnAllCheckBoxesBeforeSign();
		obj.clickSubmitOnPage();
		obj.validateSignSubmit();
		obj.clickAmmend();
		obj.ammendVerify();

	}
}
