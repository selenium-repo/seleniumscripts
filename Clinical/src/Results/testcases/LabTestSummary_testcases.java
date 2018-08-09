package Results.testcases;

import Results.pages.Results_page;

public class LabTestSummary_testcases {

	Results_page obj = new Results_page();

	public void print() {
		obj.menuNav("LabTestSummary");
		obj.clickFirstRow("labTestSummaryGrid");
		obj.verifyPrint("LabTestSummary");

	}

	public void graphs() {
		// obj.menuNav("LabTestSummary");
		obj.clickFirstRow("labTestSummaryGrid");
		obj.verifyLineGraphs();
		obj.setMainFrame();
		obj.verifyBarGraphs();
		obj.setMainFrame();
	}

	public void sequence() {
		// obj.menuNav("LabTestSummary");
		obj.sortSequence();
		obj.sortSequenceByDate();
	}

	public void microbiology() {
		// obj.menuNav("LabTestSummary");
		obj.micro("labTestSummaryTabs_tablist_mrTab");
	}
}
