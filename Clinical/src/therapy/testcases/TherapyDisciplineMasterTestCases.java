package therapy.testcases;

import common.CommonLib;
import common.CustomReporter;
import common.HashTableRepository;
import therapy.pages.TherapyDisciplineMasterPage;

public class TherapyDisciplineMasterTestCases extends TherapyDisciplineMasterPage {

	public void verifyTherapyDisciplineMasterFunctionalities() throws InterruptedException {

		boolean check = verifyTherapyDisciplineMasterAdd();
		if (check) {
			verifyTherapyDisciplineMasterEdit();
			// verifyTherapyDisciplineMasterCopy();
			verifyTherapyDisciplineMasterDetails();
			verifyTherapyDisciplineMasterDelete();
		}
		verifyTherapyDisciplineMasterAudit();
	}

	public boolean verifyTherapyDisciplineMasterAdd() throws InterruptedException {
		boolean result = false;
		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(3);
		navigateToTherapyDisciplineMaster();
		int before_add = getTherapyMasterCodeCount();
		therapyDisciplineMasterAdd();
		setTherapyDisciplineMasterCode();
		setTherapyDisciplineMasterDescription();
		setCMSTherapyDisciplineCorrelation();
		clickTherapyDisciplineMasterSubmit();
		CommonLib.staticWait(2);
		int after_add = getTherapyMasterCodeCount();
		String therapy_code = getTherapyCode();
		System.out.println(therapy_code);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Through Therapy Discipline Master's Add toolbar button, user is able to add the therapy discipline master code to the system successfully, which is as expected", CustomReporter.status.Pass);
			setCodeSearchValue();
			clickCodeSearch();
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add a Therapy Discipline Master Code itself, hence couldn't verify Edit, Copy, Delete and Details functionalities, please try again! which is not as expected", CustomReporter.status.Fail);
		}
		return result;
	}

	public void verifyTherapyDisciplineMasterEdit() {
		selectFirstRow();
		therapyDisciplineMasterEdit();
		String edit_desc = setTemplateDescriptionEdit();
		clickTherapyDisciplineMasterSubmit();
		clearSearchValue();
		if (!edit_desc.equals(HashTableRepository.getHash("therapy_dis_code"))) {
			CustomReporter.MessageLogger("Through Therapy Discipline Master's Edit toolbar button, user is able to edit the template description " + edit_desc + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Therapy Discipline Master's description, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTherapyDisciplineMasterCopy() {
		selectFirstRow();
		therapyDisciplineMasterCopy();
		setTherapyDisciplineMasterCopyCode();
		clickTherapyDisciplineMasterSubmit();
		String copy_desc = getTherapyDescription();

		if (copy_desc.equals(HashTableRepository.getHash("edit_therapy_code"))) {
			CustomReporter.MessageLogger("Through Therapy Discipline Master's  Copy toolbar button, user is able to copy the template description " + copy_desc + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to copy a Therapy code in Therapy Discipline Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTherapyDisciplineMasterDetails() throws InterruptedException {
		selectFirstRow();
		therapyDisciplineMasterDetails();
		therapyDisciplineMasterAdd();
		setTherapyStaffingLevelCode();
		setTherapyDisciplineMasterStaffingDescription();
		clickTherapyDisciplineMasterSubmit();
		boolean check = clickTherapyDisciplineMasterPrevious();

		if (check) {
			CustomReporter.MessageLogger("Through Therapy Discipline Master's Details toolbar button, user is able to add the therapy records details in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add the therapy Discipline Master's, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTherapyDisciplineMasterDelete() {
		int before_del = getTherapyMasterCodeCount();
		selectFirstRow();
		therapyDisciplineMasterDelete();
		clickDeleteOK();
		CommonLib.staticWait(4);
		int after_del = getTherapyMasterCodeCount();
		if (after_del < before_del) {
			CustomReporter.MessageLogger(
					"Through Therapy Discipline Master's Delete toolbar button, user is able to delete the template description " + HashTableRepository.getHash("edit_therapy_code") + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete a Therapy code in Therapy Discipline Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTherapyDisciplineMasterAudit() {

		therapyDisciplineMasterAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Through Therapy Discipline Master's Audit toolbar button, user is able to display the therapy records in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Discipline Master's toolbar button, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

}
