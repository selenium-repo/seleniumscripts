package kardexplus.pages;

import org.openqa.selenium.By;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class KardexPlusPage {

	public void menuNavKardex() {
		MenuNavigation.menuNav("KardexPlus");
	}

	public void clickMyFavTab() {
		CommonLib.clickButton(By.id("kardexTabs_tablist_myFavTab"));
	}

	public void clickStationTab() {
		CommonLib.clickButton(By.id("kardexTabs_tablist_nurseStnTab"));
	}

	public void clickSelectedPatientTab() {
		CommonLib.clickButton(By.id("kardexTabs_tablist_myPatTab"));
	}

	public void verifyMyFavTab() {
		try {
			clickMyFavTab();
			CommonLib.checkSorryError("kardexPlusDataGrid");
			CustomReporter.MessageLogger("My Favourite Tab Of Kardex Plus is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("My Favourite Tab Of Kardex Plus is not working properly", status.Fail);
		}
	}

	public void verifyStationTab() {
		try {
			clickStationTab();
			CommonLib.checkSorryError("orderDetail");
			CustomReporter.MessageLogger("Stations Tab Of Kardex Plus is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Stations Tab Of Kardex Plus is not working properly", status.Fail);
		}
	}

	public void verifySelectedPatientTab() {
		try {
			clickSelectedPatientTab();
			CommonLib.checkSorryError("orderDetail");
			CustomReporter.MessageLogger("Selected patient Tab Of Kardex Plus is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Selected patient Tab Of Kardex Plus is not working properly", status.Fail);
		}

	}

}
