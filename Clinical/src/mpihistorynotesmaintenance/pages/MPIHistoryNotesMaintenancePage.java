package mpihistorynotesmaintenance.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;

import common.CommonLib;
import common.Config;
import common.MenuNavigation;

public class MPIHistoryNotesMaintenancePage {

	public void navigateToMPIPatientSummary() {
		MenuNavigation.menuNav("MPIPatientSummary");
	}

	public void setMainFrame() {
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void selectSearchBy() {
		CommonLib.staticWait(3);
		CommonLib.setDropDown(By.xpath("//form[@id='frmPatSearchCriteria']//select[@id='cboSearchBy']"), Config.props.getProperty("search_by"));
	}

	public void textMedRecNumber() {
		CommonLib.setData(By.xpath("//input[@id='txtMedRec']"), Config.props.getProperty("MRN"));
	}

	public void clickBtnSearch() {
		CommonLib.clickButton(By.id("cmdSearch"));
	}

	public void selectLinkHistoryNotesMaintenance() throws AWTException {
		if (CommonLib.isElementPresent(By.id("lstMenuItm"), 8)) {
			CommonLib.setDropDownValue(By.id("lstMenuItm"), Config.props.getProperty("hnm"));
			CommonLib.staticWait(5);
			clickOnSelectLink();
		}
	}

	public void clickOnSelectLink() throws AWTException {
		Robot act = new Robot();
		act.keyPress(KeyEvent.VK_ENTER);
		CommonLib.staticWait(5);
	}

	public void clickMPIHistoryNotesMaintenanceAdd() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdAdd']"));
	}

	public void clickMPIHistoryNotesMaintenanceEdit() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdEdit']"));
	}

	public void clickMPIHistoryNotesMaintenanceDelete() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdDelete']"));
	}

	public void clickMPIHistoryNotesMaintenanceFilter() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdShowFilter']"));
	}

	public void clickMPIHistoryNotesMaintenanceSearch() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdShowSearch']"));
	}

	public void clickMPIHistoryNotesMaintenanceSearchS() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdSearch']"));
	}

	public void clickMPIHistoryNotesMaintenanceFilterS() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdFilter']"));
	}

	public void clickCategoryCodeIcon() {
		CommonLib.clickButton(By.xpath("//input[@id='imgCtgry']"));

	}

	public void selectCategoryCode() {
		CommonLib.clickButton(By.xpath(""));
	}

	public void clickCateoryCommentIcon() {
		CommonLib.clickButton(By.xpath("//input[@id='imgCommentCode']"));
	}

	public void setFutureDate() {
		CommonLib.setData(By.id("txtHistoryExpDate"), CommonLib.addDaysToCurrent(5));
	}

	public void clickMPIHistoryNotesMaintenanceSave() {
		CommonLib.clickButton(By.xpath("//input[@id='cmdAddUpdate']"));
	}

	public String getTableData() {
		String str = CommonLib.getElement(By.xpath("//table[@id='tblDetails']/tbody/tr[2]/td[3]")).getText();
		return str;
	}

	public int getHPIHistoryNotesMaaintenanceTableRowCount() {
		int data = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]"), 5)) {
			data = CommonLib.getElements(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]")).size();
		}
		return data;
	}

	public void setMainWorkFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void selectAddedHistoryNote() {
		if (CommonLib.isElementPresent(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]"), 5)) {
			CommonLib.getElement(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]")).click();
		}
	}

	public void setEditedDate() {
		CommonLib.getElement(By.id("txtHistoryExpDate")).clear();
		CommonLib.setData(By.id("txtHistoryExpDate"), CommonLib.addDaysToCurrent(7));
	}

	public String getHistoryExpirationData() {
		String text = "";
		if (CommonLib.isElementPresent(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]/td[6]"), 5)) {
			text = CommonLib.getElement(By.xpath("//div[@class='TundraFrame']/table[@id='tblDetails']/tbody//tr[contains(@class,'TundraRow')]/td[6]")).getText();
		}
		return text;
	}

	public void selectCategoryCodeForFilter() {
		CommonLib.setDropDown(By.id("cboCatCode"), Config.props.getProperty("filter_cat_code"));
	}

	public void selectDiffCategoryCodeForFilter() {
		CommonLib.setRandomDropDown(By.id("cboCatCode"));
	}

	public void setSearchDate() {
		CommonLib.setData(By.id("txtStartDate"), CommonLib.addDaysToCurrent(0));
	}

	public void setNewSearchDate() {
		CommonLib.getElement(By.xpath("txtStartDate")).clear();
		CommonLib.setData(By.id("txtStartDate"), CommonLib.addDaysToCurrent(-30));
	}

}
