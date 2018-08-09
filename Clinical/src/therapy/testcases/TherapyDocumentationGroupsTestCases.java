package therapy.testcases;

import common.CommonLib;
import common.CustomReporter;
import common.HashTableRepository;
import therapy.pages.TherapyDocumentationGroupsPage;

public class TherapyDocumentationGroupsTestCases extends TherapyDocumentationGroupsPage {

	public void verifyTherapyDocumentationGroupsFunctionalities() {

		verifyTherapyDocumentationGroupsAdd();
		verifyTherapyDocumentationGroupsEdit();
		// verifyTherapyDocumentationGroupsCopy();
		verifyTherapyDocumentationGroupsAudit();
		verifyTherapyDocumentationGroupsDelete();
	}

	public void verifyTherapyDocumentationGroupsAdd() {

		navigateToPatientTherapyDocumentation();
		CommonLib.staticWait(3);
		navigateToTherapyDocumentationGroups();
		therapyDocumentationGroupsAdd();
		setTherapyGroupCode();
		setTherapyGroupDescription();
		clickTherapyDocumentationGroupSubmit();
		CommonLib.staticWait(2);
		setCodeSearchValue();
		clickCodeSearch();
		String therapy_code = getTherapyCode();
		System.out.println(therapy_code);
		if (therapy_code.equals(HashTableRepository.getHash("therapy_doc_code"))) {
			CustomReporter.MessageLogger("Through Therapy Documentation Group's Add toolbar button, user is able to add the therapy documentation code " + therapy_code + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Therapy Documentation Code, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTherapyDocumentationGroupsEdit() {
		selectFirstRow();
		therapyDocumentationGroupsEdit();
		String edit_desc = setTemplateDescriptionEdit();
		clickTherapyDocumentationGroupSubmit();
		clearSearchValue();
		if (!edit_desc.equals(HashTableRepository.getHash("therapy_doc_code"))) {
			CustomReporter.MessageLogger("Through Therapy Documentation Group's Edit toolbar button, user is able to edit the template description " + edit_desc + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Therapy code description in Therapy Documentation Group, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTherapyDocumentationGroupsCopy() {
		selectFirstRow();
		therapyDocumentationGroupsCopy();
		setTherapyGroupCode();
		clickTherapyDocumentationGroupSubmit();
		String copy_desc = getTherapyDescription();

		if (copy_desc.equals(HashTableRepository.getHash("edit_therapy_code"))) {
			CustomReporter.MessageLogger("Through Therapy Documentation Group's  Copy toolbar button, user is able to copy the template description " + copy_desc + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to copy a Therapy code in Therapy Documentation Group, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTherapyDocumentationGroupsAudit() {
		therapyDocumentationGroupsAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Through Therapy Documentation Group's Audit toolbar button, user is able to display the therapy records in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Therapy Documentation Group, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTherapyDocumentationGroupsDelete() {
		int before_del = getTherapyMasterCodeCount();
		selectFirstRow();
		therapyDocumentationGroupsDelete();
		clickDeleteOK();
		CommonLib.staticWait(4);
		int after_del = getTherapyMasterCodeCount();
		if (after_del < before_del) {
			CustomReporter.MessageLogger(
					"Through Therapy Template Master's Delete toolbar button, user is able to delete the template description " + HashTableRepository.getHash("edit_therapy_code") + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete a Therapy code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

}
