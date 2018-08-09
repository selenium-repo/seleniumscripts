package findpatient;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.MenuNavigation;

public class Physiciangroup {
	public void Physician_group() {

		try {
			MenuNavigation.menuNav("FindPatient");

			CommonLib.setFrameToDefault();
			physician_Group();

		} catch (Exception e) {

			CustomReporter.MessageLogger(e.getMessage() + "\n", CustomReporter.status.Error);
		}

	}

	public void tablePhysicianCensusSearch(String searchText, int ColNum) throws InterruptedException {
		String indexLoc;
		if (ColNum <= 2) {
			indexLoc = "[1]";
		} else {
			indexLoc = "[2]";
		}
		List<WebElement> tr_collection = CommonLib.getElements(By.xpath("//div[@id='physiciansCensusGrid']/div[2]/div" + indexLoc + "/div/div/div/div/table/tbody/tr/td[@idx='" + ColNum + "']"));

		Iterator<WebElement> rowsC = tr_collection.iterator();
		System.out.println(rowsC);
		int i = 1;
		boolean found = false;
		WLoop: while (rowsC.hasNext()) {
			String celText = rowsC.next().getText().trim().toString();
			// System.out.println(celText);

			if (celText.contains(searchText)) {
				// CommonLib.clickIcon("fav_" + i);
				found = true;
				break WLoop;
			}
			i = i + 1;
		}
		if (found == false) {
			CustomReporter.MessageLogger("Physician Not found in Physician Census.", CustomReporter.status.Fail);
		} else {
			CustomReporter.MessageLogger("Physician found in Physician Census", CustomReporter.status.Pass);
		}

	}

	public void physician_Group() throws InterruptedException {

		MenuNavigation.menuNav("FindPatient");
		// System.out.println("in group");
		CommonLib.LinkClick(By.id("findPatientTabs_tablist_physicianGroupCensusTab"));
		//// Using Patient type-Inpatient
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_I']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_O']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_E']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_I']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_D']", true);

		CommonLib.selectDojoDropDownByKeyDownNumber("physicianGroupSelect", 2);
		CommonLib.staticWait(3);

		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));

		String Phys_Name = "";
		try {
			Phys_Name = CommonLib.getText(By.xpath("//div[@id='physicianGroupCensusGrid']/div[2]//tbody/tr/td[3]"));
		} catch (Exception e) {
		}

		if (CommonLib.checkSorryError("physicianGroupCensusGrid")) {

			if (Config.props.getProperty("PhysicianName").equals(Phys_Name)) {
				CustomReporter.MessageLogger("Physician found in Physician Group with All option.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician  not found in Physician Group with All option.", CustomReporter.status.Warning);
			}
		}
		/// Search using Patient Type Outpatient
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_I']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_O']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_E']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_I']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_D']", true);

		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_I'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_E'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_O'))]", true);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusStatus_I'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusStatus_D'))]", true);

		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));
		// tablePhysicianCensusSearch("ADAMS", 10);
		Phys_Name = "";
		try {
			Phys_Name = CommonLib.getText(By.xpath("//div[@id='physicianGroupCensusGrid']/div[2]//tbody/tr/td[3]"));
		} catch (Exception e) {
		}
		if (CommonLib.checkSorryError("physicianGroupCensusGrid")) {
			if (Config.props.getProperty("PhysicianName").equals(Phys_Name)) {
				CustomReporter.MessageLogger("Physician found in Physician Group for Outpatient.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician not found in Physician Group for Outpatient.", CustomReporter.status.Warning);
			}
		}

		/// Searching using Patient Type Emergency

		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_I']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_O']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_E']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_I']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_D']", true);

		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_O'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_I'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_E'))]", true);

		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));

		Phys_Name = "";
		try {
			Phys_Name = CommonLib.getText(By.xpath("//div[@id='physicianGroupCensusGrid']/div[2]//tbody/tr/td[3]"));
		} catch (Exception e) {
		}
		if (CommonLib.checkSorryError("physicianGroupCensusGrid")) {
			if (Config.props.getProperty("PhysicianName").equals(Phys_Name)) {
				CustomReporter.MessageLogger("Physician  found in Physician Group for Emergency Only.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician  not found in Physician Group for Emergency Only.", CustomReporter.status.Warning);
			}
		}
		///// Searching using Patient Status-Discharge

		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_I']", true);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_O']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusType_E']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_I']", false);
		CommonLib.checkbox_set("//*[@id='physicianGroupCensusStatus_D']", true);

		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_O'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_E'))]", false);
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusType_I'))]", true);
		//
		// CommonLib.checkbox_set("//*[@type='checkbox' and (
		// contains(@name,'physicianGroupCensusStatus_D'))]", true);

		CommonLib.clickButton(By.id("dijit_form_Button_0_label"));
		// tablePhysicianCensusSearch("ADAMS", 10);

		Phys_Name = "";
		try {
			Phys_Name = CommonLib.getText(By.xpath("//div[@id='physicianGroupCensusGrid']/div[2]//tbody/tr/td[3]"));
		} catch (Exception e) {
		}

		if (CommonLib.checkSorryError("physicianGroupCensusGrid")) {
			if (Config.props.getProperty("PhysicianName").equals(Phys_Name)) {
				CustomReporter.MessageLogger("Physician  found in Physician Group.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician  not found in Physician Group.", CustomReporter.status.Warning);
			}
		}

	}
}
