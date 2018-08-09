package mpihistorynotesmaintenance.testcases;

import java.awt.AWTException;

import common.CommonLib;
import common.CustomReporter;
import mpihistorynotesmaintenance.pages.MPIHistoryNotesMaintenancePage;

public class MPIHistoryNotesMaintenanceTestCases extends MPIHistoryNotesMaintenancePage {

	public void verifyMPIHistoryNotesMaintenanceFunctionalities() throws AWTException {

		goToMPIHistoryNotesMaintenanceScreen();
		verifyMPIHistoryNotesMaintenanceAdd();
		verifyMPIHistoryNotesMaintenanceEdit();
		verifyMPIHistoryNotesMaintenanceSearch();
		verifyMPIHistoryNotesMaintenanceFilter();
		verifyMPIHistoryNotesMaintenanceDelete();

	}

	public void goToMPIHistoryNotesMaintenanceScreen() throws AWTException {

		navigateToMPIPatientSummary();
		setMainFrame();
		selectSearchBy();
		textMedRecNumber();
		clickBtnSearch();
		CommonLib.staticWait(5);
		selectLinkHistoryNotesMaintenance();

	}

	public void verifyMPIHistoryNotesMaintenanceAdd() throws AWTException {
		CommonLib.staticWait(5);
		setMainWorkFrame();
		int before_add = getHPIHistoryNotesMaaintenanceTableRowCount();
		System.out.println(before_add);
		clickMPIHistoryNotesMaintenanceAdd();
		clickCategoryCodeIcon();
		CommonLib.staticWait(3);
		clickOnSelectLink();
		CommonLib.staticWait(3);
		clickCateoryCommentIcon();
		CommonLib.staticWait(5);
		clickOnSelectLink();
		CommonLib.staticWait(3);
		setFutureDate();
		clickMPIHistoryNotesMaintenanceSave();
		setMainWorkFrame();
		CommonLib.staticWait(5);
		int after_add = getHPIHistoryNotesMaaintenanceTableRowCount();
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("An MPI History Notes Maintenance record has been added to the system, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add an MPI History Notes Maintenance record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyMPIHistoryNotesMaintenanceEdit() {
		String before_edit = getHistoryExpirationData();
		CommonLib.staticWait(3);
		selectAddedHistoryNote();
		clickMPIHistoryNotesMaintenanceEdit();
		setEditedDate();
		clickMPIHistoryNotesMaintenanceSave();
		CommonLib.staticWait(5);
		String after_edit = getHistoryExpirationData();
		if (before_edit != after_edit) {
			CustomReporter.MessageLogger("An MPI History Notes Maintenance record has been edited in the system, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit an MPI History Notes Maintenance record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyMPIHistoryNotesMaintenanceDelete() throws AWTException {
		CommonLib.staticWait(4);
		int before_add = getHPIHistoryNotesMaaintenanceTableRowCount();
		selectAddedHistoryNote();
		clickMPIHistoryNotesMaintenanceDelete();
		CommonLib.staticWait(4);
		clickOnSelectLink();
		CommonLib.staticWait(3);
		int after_add = getHPIHistoryNotesMaaintenanceTableRowCount();
		if (after_add < before_add) {
			CustomReporter.MessageLogger("An MPI History Notes Maintenance record has been deleted from the system, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete an MPI History Notes Maintenance record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyMPIHistoryNotesMaintenanceFilter() {
		clickMPIHistoryNotesMaintenanceFilter();
		CommonLib.staticWait(4);
		selectDiffCategoryCodeForFilter();
		clickMPIHistoryNotesMaintenanceFilterS();
		int filter_second = getHPIHistoryNotesMaaintenanceTableRowCount();
		CommonLib.staticWait(4);
		selectCategoryCodeForFilter();
		clickMPIHistoryNotesMaintenanceFilterS();
		int filter_first = getHPIHistoryNotesMaaintenanceTableRowCount();
		if (filter_first != filter_second) {
			CustomReporter.MessageLogger("An MPI History Notes Maintenance records has been filtered from the system, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to filter an MPI History Notes Maintenance records in the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyMPIHistoryNotesMaintenanceSearch() {
		CommonLib.staticWait(3);
		clickMPIHistoryNotesMaintenanceSearch();
		CommonLib.staticWait(3);
		setSearchDate();
		clickMPIHistoryNotesMaintenanceSearchS();
		CommonLib.staticWait(3);
		int search_first = getHPIHistoryNotesMaaintenanceTableRowCount();
		// setNewSearchDate();
		// clickMPIHistoryNotesMaintenanceSearchS();
		// CommonLib.staticWait(3);
		// int search_second = getHPIHistoryNotesMaaintenanceTableRowCount();
		if (search_first != 0) {
			CustomReporter.MessageLogger("An MPI History Notes Maintenance records has been searched from the system, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to search an MPI History Notes Maintenance records in the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

}
