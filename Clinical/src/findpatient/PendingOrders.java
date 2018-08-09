package findpatient;

import org.openqa.selenium.By;

import common.CommonLib;
import common.CustomReporter;
import common.MenuNavigation;

public class PendingOrders {

	public void Pending_Orders() {

		try {
			MenuNavigation.menuNav("FindPatient");
			CommonLib.setFrameToDefault();
			pending_orders();
		} catch (Exception e) {
			CustomReporter.MessageLogger(e.getMessage() + "\n", CustomReporter.status.Error);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void pending_orders() throws InterruptedException {

		CommonLib.LinkClick(By.id("findPatientTabs_tablist_pendingOrdersTab"));
		if (CommonLib.checkSorryError("physicianGroupCensusGrid")) {
			if (CommonLib.getElements(By.xpath("//div[@id='physicianGroupCensusGrid']//div[@class='dojoxGridRow']")).size() >= 1) {
				CustomReporter.MessageLogger("Patient found in Pending Orders.", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Physician not found in Pending Orders.", CustomReporter.status.Warning);
			}
		}
	}

}
