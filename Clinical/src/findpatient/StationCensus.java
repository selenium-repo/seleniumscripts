package findpatient;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.CommonLib;
import common.CustomReporter;
import common.MenuNavigation;

public class StationCensus {

	// int variable = 4;

	public void station_Census() {

		try {
			MenuNavigation.menuNav("FindPatient");

			CommonLib.setFrameToDefault();
			Station_Census();

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

	public void Station_Census() throws InterruptedException {
		CommonLib.LinkClick(By.id("findPatientTabs_tablist_stationCensusTab"));

		WebElement source = CommonLib.getElement(By.xpath("//*[@name='nurseStations']"));
		Select list = new Select(source);
		for (int i = 0; i < list.getOptions().size() - 1; i++) {
			list.selectByIndex(i);
		}
		CommonLib.staticWait(3);
		// Data selection by In-House

		CommonLib.selectradio(By.xpath("//input[@id='stationCensusSortType_L']"));

		if (CommonLib.checkSorryError("stationCensusGrid")) {
			if (CommonLib.getElements(By.xpath("//div[@id='stationCensusGrid']//div[@class='dojoxGridRow']")).size() >= 1) {
				CustomReporter.MessageLogger("Physician found in Station Census In-House.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician not found in Station Census In-House.", CustomReporter.status.Warning);
			}
		}

		// Data Selection by Discharge

		CommonLib.checkbox_set("//*[@type='checkbox' and ( contains(@name,'stationCensusStatus_I'))]", false);

		CommonLib.checkbox_set("//*[@type='checkbox' and ( contains(@name,'stationCensusStatus_D'))]", true);

		if (CommonLib.checkSorryError("stationCensusGrid")) {
			if (CommonLib.getElements(By.xpath("//div[@id='stationCensusGrid']//div[@class='dojoxGridRow']")).size() >= 1) {
				CustomReporter.MessageLogger("Physician found in Station Census Discharge.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician not found in Station Census Discharge.", CustomReporter.status.Warning);
			}
		}
		// Data selection by both In-House and Discharge

		CommonLib.checkbox_set("//*[@type='checkbox' and ( contains(@name,'stationCensusStatus_I'))]", true);

		if (CommonLib.checkSorryError("stationCensusGrid")) {
			if (CommonLib.getElements(By.xpath("//div[@id='stationCensusGrid']//div[@class='dojoxGridRow']")).size() >= 1) {
				CustomReporter.MessageLogger("Physician found in Station Census In-House and discharge.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician not found in Station Census In-House and discharge.", CustomReporter.status.Warning);
			}
		}
	}

}
