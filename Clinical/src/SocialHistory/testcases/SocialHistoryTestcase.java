package SocialHistory.testcases;

import SocialHistory.pages.SocialHistoryPage;

public class SocialHistoryTestcase {

	SocialHistoryPage obj = new SocialHistoryPage();

	public void socialDrugAdd() {

		// obj.menuNav();
		obj.clickStatus("socialdrugSection");
		obj.rowCount("socialdrugSection");
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(1, "currentUse");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(1, "currentSomeUse");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(1, "formerUse");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(1, "heavyUse");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(2, "neverUsed");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(2, "currentUnknownUse");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(2, "unknownEver");
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setDrug(1, "lightUse");
		obj.setMainFrame();
		obj.verifyAdd("socialdrugSection");
	}
	// edit

	public void socialDrugEdit() {
		obj.clickFirstRow("socialdrugSection");
		obj.clickAction("socialdrugSection", 2, "application_edit");
		obj.setTobaccoFrame();
		obj.editSocialDrug();
		obj.setMainFrame();
		obj.clickAction("socialdrugSection", 4, "table");
		obj.setAuditFrame();
		obj.verifyEdit("Social Drug");
		obj.setMainFrame();
	}

	// delete
	public void socialDrugDelete() {
		obj.rowCount("socialdrugSection");
		obj.clickFirstRow("socialdrugSection");
		obj.clickAction("socialdrugSection", 3, "delete");

		obj.delete();
		obj.setMainFrame();

		obj.verifydelete("socialdrugSection");

	}

	public void alcoholAdd() {
		// obj.menuNav();
		obj.clickStatus("alcoholSection");
		obj.rowCount("alcoholSection");
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(1, "currentUse");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(1, "currentSomeUse");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(1, "formerUse");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(1, "heavyUse");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(2, "neverUsed");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(2, "currentUnknownUse");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(2, "unknownEver");
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setAlcohol(1, "lightUse");
		obj.setMainFrame();
		obj.verifyAdd("alcoholSection");
	}

	// edit
	public void alcoholEdit() {
		obj.clickFirstRow("alcoholSection");
		obj.clickAction("alcoholSection", 2, "application_edit");
		obj.setTobaccoFrame();
		obj.editAlcohol();
		obj.setMainFrame();
		obj.clickAction("alcoholSection", 4, "table");
		obj.setAuditFrame();
		obj.verifyEdit("Alcohol");
		obj.setMainFrame();
	}

	// delete
	public void alcoholDelete() {
		obj.rowCount("alcoholSection");
		obj.clickFirstRow("alcoholSection");
		obj.clickAction("alcoholSection", 3, "delete");

		obj.delete();
		obj.setMainFrame();

		obj.verifydelete("alcoholSection");

	}

	public void tobaccoAdd() {
		// obj.menuNav();
		obj.clickStatus("tobaccoSection");
		obj.rowCount("tobaccoSection");
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(1, "currentUse");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(1, "currentSomeUse");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(1, "formerUse");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(1, "heavyUse");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(2, "neverUsed");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(2, "currentUnknownUse");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(2, "unknownEver");
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 1, "add");
		obj.setTobaccoFrame();
		obj.setTobaccco(1, "lightUse");
		obj.setMainFrame();
		obj.verifyAdd("tobaccoSection");
	}

	// edit
	public void tobaccoEdit() {
		obj.clickFirstRow("tobaccoSection");
		obj.clickAction("tobaccoSection", 2, "application_edit");
		obj.setTobaccoFrame();
		obj.setTobaccoEdit();
		obj.setMainFrame();
		obj.clickAction("tobaccoSection", 4, "table");
		obj.setAuditFrame();
		obj.verifyEdit("Tobacco");
		obj.setMainFrame();
	}

	// delete
	public void tobaccoDelete() {
		obj.rowCount("tobaccoSection");
		obj.clickFirstRow("tobaccoSection");
		obj.clickAction("tobaccoSection", 3, "delete");

		obj.delete();
		obj.setMainFrame();

		obj.verifydelete("tobaccoSection");
	}

	public void general() {
		obj.menuNav();
		// obj.clickStatus("GeneralSection");
		obj.setGerneral();
		obj.audit();
		obj.setMainFrame();
		obj.verifyPrint();
		obj.verifyPrintAll();

	}

}
