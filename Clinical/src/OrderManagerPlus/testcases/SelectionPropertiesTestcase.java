package OrderManagerPlus.testcases;

import java.util.Iterator;

import org.openqa.selenium.WebElement;

import OrderManagerPlus.pages.OrderManagerPlusPage;
import common.CommonLib;
import common.CustomReporter;
import common.MenuNavigation;

public class SelectionPropertiesTestcase extends OrderManagerPlusPage {

	public void verifyOrderManagerSelectionProperties() throws InterruptedException {

		CommonLib.changeimplicitwait(3);
		MenuNavigation.menuNav("Order Manager Plus");
		try {
			checkOrderPhysicianValue("orderingPhysicianList");
		} catch (Exception e) {
		}
		try {
			checkCoSignListBox();
		} catch (Exception e) {
		}
		clickAllTab();
		Iterator<WebElement> itr = getRadioButtons("allDisplayType");
		int i = 1;
		while (itr.hasNext()) {
			itr.next().click();
			CommonLib.staticWait(2);
			int count = getTableRowCount("allOrdersWorklistGrid");
			if (count > 0) {
				CustomReporter.MessageLogger("For Order Manager Plus ALL tab, when " + i++ + " display type radio button is selected; the number of rows displaying as :" + count + "", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("For Order Manager Plus ALL tab, when " + i++ + " display type radio button is selected; there are no orders displaying", CustomReporter.status.Information);
			}
		}

		clickNonPharmacyTab();
		Iterator<WebElement> itor = getRadioButtons("displayType");
		int j = 1;
		while (itor.hasNext()) {
			itor.next().click();
			if (j == 1) {
				selectListOrderGroup(2);
				selectNonPharmFilter();
			}
			int count = getTableRowCount("orderWorklistGrid");
			if (count > 0) {
				CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected; the number of rows displaying as :" + count + "", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected, there are no orders displaying", CustomReporter.status.Information);
			}

		}
		selectListOrderGroup(3);
		int cnt = getTableRowCount("orderWorklistGrid");
		if (cnt > 0) {
			CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected; the number of rows displaying as :" + cnt + "", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected, there are no orders displaying", CustomReporter.status.Information);
		}
		selectListOrderGroup(6);
		int counter = getTableRowCount("orderWorklistGrid");
		if (counter > 0) {
			CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected; the number of rows displaying as :" + counter + "", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("For Order Manager Plus Non Pharmacy tab, when " + j++ + " display type radio button  is selected; there are no orders displaying", CustomReporter.status.Information);
		}

		clickMedTab();
		Iterator<WebElement> iter = getRadioButtons("medOrderStatus");
		int k = 1;
		while (iter.hasNext()) {
			iter.next().click();
			CommonLib.staticWait(3);
			boolean flg = true;
			Iterator<WebElement> itra = getRadioButtons("medDisplayType");
			while (itra.hasNext()) {
				itra.next().click();
				if (k == 1 && flg == true) {
					selectMedFilter();
					flg = false;
				}
				CommonLib.staticWait(3);
				int counts = getTableRowCount("medOrderWorklistGrid");
				if (counts > 0) {
					CustomReporter.MessageLogger("For Order Manager Plus Med tab, when " + k++ + " display type radio button  is selected; the number of rows displaying as :" + counts + "", CustomReporter.status.Pass);
				} else {
					CustomReporter.MessageLogger("For Order Manager Plus Med tab, when " + k++ + " display type radio button  is selected; there are no orders displaying", CustomReporter.status.Information);
				}

			}

		}

		clickIVsTab();
		Iterator<WebElement> it = getRadioButtons("ivOrderStatus");
		int l = 1;
		while (it.hasNext()) {
			it.next().click();
			CommonLib.staticWait(3);
			boolean flag = true;
			Iterator<WebElement> ita = getRadioButtons("ivDisplayType");
			while (ita.hasNext()) {
				ita.next().click();
				if (l == 1 && flag == true) {
					selectIVFilter();
					flag = false;
				}
				CommonLib.staticWait(3);
				int ct = getTableRowCount("ivOrderWorklistGrid");
				if (ct > 0) {
					CustomReporter.MessageLogger("For Order Manager Plus IV tab, when " + l++ + " display type radio button  is selected; the number of rows displaying as :" + ct + "", CustomReporter.status.Pass);
				} else {
					CustomReporter.MessageLogger("For Order Manager Plus IV tab, when " + l++ + " display type radio button  is selected; there are no orders displaying", CustomReporter.status.Information);
				}

			}

		}

	}

}
