package Results.testcases;

import Results.pages.Results_page;

public class LabTestSummary2_testcases {
	Results_page obj = new Results_page();

	public void printCHEM() {
		obj.menuNav("LabTestSummary2");
		obj.clickFirstRow("labTestSummary2Grid_CHEMISTRY");
		obj.verifyPrint("LabTestSummary2");

	}

	public void graphsCHEM() {
		// obj.menuNav("LabTestSummary2");
		obj.clickFirstRow("labTestSummary2Grid_CHEMISTRY");
		obj.verifyLineGraphs();
		obj.setMainFrame();
		obj.verifyBarGraphs();
		obj.setMainFrame();
	}

	public void printHEMA() {
		// obj.menuNav("LabTestSummary2");
		obj.clickHematalogyTab();
		obj.clickFirstRow("labTestSummary2Grid_HEMATOLOGY");
		obj.verifyPrint("LabTestSummary2");

	}

	public void graphsHEMA() {
		// obj.menuNav("LabTestSummary2");
		obj.clickHematalogyTab();
		obj.clickFirstRow("labTestSummary2Grid_HEMATOLOGY");
		obj.verifyLineGraphs();
		obj.setMainFrame();
		obj.verifyBarGraphs();
		obj.setMainFrame();
	}

	public void printMICRO() {
		// obj.menuNav("LabTestSummary2");
		obj.clickMicroTab();
		obj.clickFirstRowLTS2();
		obj.verifyPrint("LabTestSummary2");

	}

	public void graphsMICRO() {
		// obj.menuNav("LabTestSummary2");
		obj.clickMicroTab();
		obj.clickFirstRowLTS2();
		obj.verifyLineGraphs();
		obj.setMainFrame();
		obj.verifyBarGraphs();
		obj.setMainFrame();
	}

	public void sequence() {
		// obj.menuNav("LabTestSummary2");
		obj.sortSequenceByDate();
	}

	public void microbiology() {
		// obj.menuNav("LabTestSummary2");
		obj.micro("labTestSummary2MainTabs_tablist_mrTab");
	}
}
