package discharge_summary.testcases;

import java.io.IOException;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;
import discharge_summary.pages.DischargeSummaryPage;

public class DischargeSummaryTestCases extends DischargeSummaryPage {
	static String selected_value = "";

	public void verifyAllDischargeSummaryFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("DischargeSummary");
		boolean check = verifyDischargeSummaryAdd();
		if (check) {
			verifyDischargeSummaryEdit();
			verifyDischargeSummaryMarkFinalized();
			verifyDischargeSummaryRequestElectronicCopy();
			verifyDischargeSummaryGenerateElectronicCopy();
			verifyDischargeSummaryAudit();
			verifyDischargeSummaryPrint();
			verifyDischargeSummaryDelete();
		} else {
			CustomReporter.MessageLogger("Since add functionality itself is not working and hence couln't verify the rest of the functionalities, please try again!", CustomReporter.status.Fail);
		}

	}

	public boolean verifyDischargeSummaryAdd() throws InterruptedException {

		CommonLib.changeimplicitwait(4);
		boolean result = false;
		setEntireHistoryRadioButton();
		CommonLib.staticWait(3);
		int before_add = getRowCount();
		clickDischargeSummaryAdd();
		selected_value = selectTypeListBox("templateTypeCode");
		clickDischargeSummarySubmit();
		CommonLib.staticWait(5);
		int after_add = getRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add a Discharge Summary record to the system, which is not as expected", CustomReporter.status.Fail);
		}
		return result;
	}

	public void verifyDischargeSummaryEdit() throws InterruptedException {
		String edit_reason = "";
		selectFirstRow();
		clickDischargeSummaryEdit();
		boolean result = clickOnComment();
		if (result) {
			CommonLib.staticWait(3);
			setComments();
			clickDischargeSummarySubmit();
			setDialogFrame();
			selectEditReason();
			edit_reason = getUpdateReasonValue();
			clickSelectReasonSubmit();
			setHomeFrame();

		} else {
			clickOnStatus();
			selectDischStatusValue();
			clickDischargeSummarySubmit();
			setDialogFrame();
			selectEditReason();
			edit_reason = getUpdateReasonValue();
			clickSelectReasonSubmit();
			setHomeFrame();

		}
		if (edit_reason != null) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to edit a Discharge Summary record to the system, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyDischargeSummaryMarkFinalized() {

		clickDischargeSummaryMarkFinalize();
		// clickFinalizeOK();
		SetHomeFrame();
		CommonLib.staticWait(2);
		String final_text = getFinalizedText();
		System.out.println(final_text);
		if (final_text.contains("finalized")) {
			clickFinalizeOK();
			CommonLib.staticWait(2);
			String verify_final = getRequestElectronicCopyText();
			System.out.println(verify_final);
			if (verify_final.contains("Marked as finalized")) {
				CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been finalized in the system successfully, which is as expected", CustomReporter.status.Pass);
				clickElectronicOK();
			} else {
				CustomReporter.MessageLogger("Failed to finalize the Discharge Summary record " + selected_value + " in the system, please try again!", CustomReporter.status.Fail);
			}
		}
	}

	public void verifyDischargeSummaryRequestElectronicCopy() {
		clickDischargeSummaryRequestElectronicCopy();
		String electronic_text = getRequestElectronicCopyText();
		clickElectronicOK();
		if (electronic_text.contains("Requested Electronic Copy")) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been Requested Electronic Copy in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Request Electronic Copy for Discharge Summary record " + selected_value + " in the system, please try again!", CustomReporter.status.Fail);

		}

	}

	public void verifyDischargeSummaryGenerateElectronicCopy() {
		clickDischargeSummaryGenerateElectronicCopy();
		CommonLib.staticWait(5);
		int count = getWindowHandleCount();
		setWindowHandleToLast();
		CommonLib.staticWait(2);
		CommonLib.GetDriver().close();
		CommonLib.staticWait(5);
		setWindowHandleToLast();
		CommonLib.staticWait(2);
		CommonLib.GetDriver().switchTo().parentFrame();
		CommonLib.staticWait(2);
		SetHomeFrame();
		if (count == 2) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been Generated the Electronic Copy in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Generate the Electronic Copy for Discharge Summary record " + selected_value + " in the system, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyDischargeSummaryPrint() throws IOException {
		clickDischargeSummaryPrint();
		// CommonLib.staticWait(5);
		// int count = getWindowHandleCount();
		// setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().close();
		// CommonLib.staticWait(5);
		// setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// SetHomeFrame();
		// CommonLib.staticWait(2);

		CommonLib.staticWait(8);
		int count = getWindowHandleCount();
		// Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical_3_3_0\\ImportFile\\pdf_close.exe");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		if (count > 1) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the  Discharge Summary record " + selected_value + " in the system, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyDischargeSummaryAudit() {
		clickDischargeSummaryAudit();
		setAuditFrame();
		String last_audit = getLastAuditEventText();
		closeAuditDialog();
		if (last_audit != null) {
			CustomReporter.MessageLogger("Discharge Summary " + selected_value + " has the last audit comment as: " + last_audit + ", which is as expected", status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to display the audit for " + selected_value + " Discharge Summary, which is not as expected", status.Fail);
		}
	}

	public void verifyDischargeSummaryDelete() {
		clickDischargeSummaryDelete();
		CommonLib.staticWait(2);
		String delete_popup = getDeleteText();
		if (delete_popup.contains("delete this Discharge")) {
			closeDeletePopUp();
			setDialogFrame();
			selectEditReason();
			clickSelectReasonSubmit();
			CommonLib.staticWait(2);
			String delete_reason = getUpdateReasonValue();
			setHomeFrame();
			if (delete_reason != null) {
				CustomReporter.MessageLogger("Discharge Summary " + selected_value + " record has been Deleted in the system successfully, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed to Delete the  Discharge Summary record " + selected_value + " in the system, please try again!", CustomReporter.status.Fail);
			}
		}

	}
}
