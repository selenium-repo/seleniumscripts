package Results.testcases;

import Results.pages.Results_page;

public class LabPanelSummary_testcases {

	Results_page obj = new Results_page();

	public void print() {
		obj.menuNav("LabPanelSummary");
		obj.clickPanelRow();
		obj.verifyPrint("LabPanelSummary");

	}

	public void graphs() {
		// obj.menuNav("LabPanelSummary");
		obj.clickPanelRow();
		obj.verifyLineGraphs();
		obj.setMainFrame();
		obj.verifyBarGraphs();
		obj.setMainFrame();
	}
}
