package therapy.testcases;

import common.CommonLib;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;
import therapy.pages.TherapyTemplateMasterPage;

public class TherapyTemplateMasterTestCases extends TherapyTemplateMasterPage {

	public void selectPatient() {
		MenuNavigation.menuNav("RxPatientSearch");
		CommonLib.staticWait(3);
		verifyPatientSearchByNumber();
	}

	public void verifyTherapyTemplateMasterFunctionalities() throws InterruptedException {
		navigateToTherapyTemplateMaster();
		verifyTemplateMasterAdd();
		verifyTemplateMasterEdit();
		// verifyTemplateMasterCopy();
		verifyTemplateMasterDelete();
		verifyTemplateMasterInactivate();
		verifyTemplateMasterActivate();
		verifyTemplateMasterAudit();
		verifyTemplateMasterFilter();
		verifyTemplateMasterDetails();
	}

	public void verifyTemplateMasterAdd() throws InterruptedException {
		therapyTemplateMasterAdd();
		setTemplateCode();
		setTemplateDescription();
		selectTherapyDiscipline();
		clickTherapyMasterSubmit();
		CommonLib.staticWait(8);
		setSearchValue();
		clickCodeSearch();
		String therapy = getTherapyCode();
		System.out.println(therapy);
		if (therapy.equals(HashTableRepository.getHash("template_code"))) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Add toolbar button, user is able to add the template code " + therapy + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Therapy Code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTemplateMasterEdit() {
		selectFirstRow();
		therapyTemplateMasterEdit();
		String edit_desc = setTemplateDescriptionEdit();
		clickTherapyMasterSubmit();
		clearSearchValue();
		if (!edit_desc.equals(HashTableRepository.getHash("template_code"))) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Edit toolbar button, user is able to edit the template description " + edit_desc + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Therapy description in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTemplateMasterCopy() {
		selectFirstRow();
		therapyTemplateMasterCopy();
		setTemplateCode();
		clickTherapyMasterSubmit();
		String copy_desc = getTherapyDescription();
		if (copy_desc.equals(HashTableRepository.getHash("edit_template_code"))) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Copy toolbar button, user is able to copy the template description " + copy_desc + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to copy a Therapy code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTemplateMasterDelete() {
		int before_del = getTherapyMasterCodeCount();
		selectFirstRow();
		therapyTemplateMasterDelete();
		clickDeleteOK();
		CommonLib.staticWait(4);
		int after_del = getTherapyMasterCodeCount();
		if (after_del < before_del) {
			CustomReporter.MessageLogger(
					"Through Therapy Template Master's Delete toolbar button, user is able to delete the template description " + HashTableRepository.getHash("edit_template_code") + " to the system successfully, which is as expected",
					CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete a Therapy code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyTemplateMasterInactivate() throws InterruptedException {
		verifyTemplateMasterAdd();
		selectFirstRow();
		String status_before = getTherapyStatus();
		System.out.println(status_before);
		therapyTemplateMasterInactivate();
		confirmMessageClick();
		CommonLib.staticWait(4);
		String status_after = getTherapyStatus();
		System.out.println(status_after);
		if ((status_before != status_after) && status_after.equals("Inactive")) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Inactivate toolbar button, user is able to Inactivate the therapy template in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Inactivate a Therapy code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTemplateMasterActivate() throws InterruptedException {
		selectFirstRow();
		String status_before = getTherapyStatus();
		therapyTemplateMasterActivate();
		confirmMessageClick();
		CommonLib.staticWait(4);
		String status_after = getTherapyStatus();
		if ((status_before != status_after) && status_after.equals("Active")) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Activate toolbar button, user is able to Activate the therapy template in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Activate a Therapy code in Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTemplateMasterAudit() {
		therapyTemplateMasterAudit();
		switchToLastIframe();
		boolean check = closeAudit();
		setHomeFrame();
		if (check) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Audit toolbar button, user is able to display the therapy records in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Audit Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyTemplateMasterFilter() {
		clearSearchValue();
		therapyTemplateMasterFilter();
		boolean check = clickFilterSubmit();
		if (check) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Filter toolbar button, user is able to filter the therapy records in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to filter Therapy Template Master, please try again! which is not as expected", CustomReporter.status.Information);
		}
	}

	public void verifyTemplateMasterDetails() throws InterruptedException {
		selectFirstRow();
		therapyTemplateMasterDetails();
		therapyTemplateMasterAdd();
		sectionType();
		selectPredefinedTitle();
		clickDetailsSubmit();
		therapyTemplateMasterAdd();

		sectionType();
		selectPredefinedMissedSessionTitle();
		clickDetailsSubmit();
		therapyTemplateMasterAdd();

		setUserDefinedTitle();
		clickDetailsSubmit();
		clickUDSectionType();
		clickSectionDataPoint();
		therapyTemplateMasterAdd();
		selectLineType();
		clickDataPointSearchIcon();
		CommonLib.staticWait(3);
		selectDataPointRow();
		CommonLib.staticWait(3);
		clickDetailsSubmit();
		clickDetailsSubmit();
		clickPreviousButton();
		clickPreviousButton();
		selectFirstRow();
		therapyTemplateMasterEdit();
		selectPublish();
		clickTherapyMasterSubmit();
		if (clickPublishConfirm()) {
			CustomReporter.MessageLogger("Through Therapy Template Master's Details toolbar button, user is able to add the therapy records details in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add the therapy details, please try again! which is not as expected", CustomReporter.status.Fail);
		}

	}

}
