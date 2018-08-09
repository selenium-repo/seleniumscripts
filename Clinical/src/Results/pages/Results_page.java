package Results.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class Results_page {

	public void setMainFrame() {
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void menuNav(String page) {

		if (page == "LabPanelSummary") {
			MenuNavigation.menuNav("LabPanelSummary");
		} else if (page == "LabTestSummary") {
			MenuNavigation.menuNav("LabTestSummary");
		} else if (page == "LabTestSummary2") {
			MenuNavigation.menuNav("LabTestSummary2");
		} else if (page == "TranscribeResults") {
			MenuNavigation.menuNav("TranscribeResults");
		} else if (page == "ResultSummary") {
			MenuNavigation.menuNav("ResultSummary");
		}
	}

	public void clickPrint() {
		CommonLib.clickButton(By.id("print_label"));
	}

	public void clickLineGraph() {
		CommonLib.clickButton(By.id("lineGraph_label"));
	}

	public void clickBarGraph() {
		CommonLib.clickButton(By.id("barGraph_label"));
	}

	public void clickFirstRow(String page) {
		CommonLib.clickButton(By.xpath("//div[@id='" + page + "']//div[2]//div//div//div//div[1]//table//tbody//tr//td"));
	}

	public void verifyPrint(String page) {
		clickPrint();
		CommonLib.staticWait(4);
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of " + page + " is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of " + page + " is not working properly", status.Fail);
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void setGraphFrame() {

		WebElement GraphFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'quantitative-result-graph.action?')][last()]"));

		CommonLib.setFrameFromCurrent(GraphFrame);

	}

	public void verifyLineGraphs() {
		clickLineGraph();
		setGraphFrame();
		CommonLib.CheckImage(By.xpath("//img[contains(@src,'servlet/com.objectplanet.chart.ChartServlet')]"), "Line Graph Functionality");
		CommonLib.clickButton(By.id("close_label"));
	}

	public void verifyBarGraphs() {
		clickBarGraph();
		setGraphFrame();
		CommonLib.CheckImage(By.xpath("//img[contains(@src,'servlet/com.objectplanet.chart.ChartServlet')]"), "Bar Graph Functionality");
		CommonLib.clickButton(By.id("close_label"));
	}

	public void clickAlphaSequence() {
		CommonLib.clickButton(By.id("sortSequenceAlpha"));
	}

	public void sortSequence() {
		clickAlphaSequence();
		CommonLib.clickButton(By.xpath("//span[contains(text(),'Search')] "));
		String Row = CommonLib.getElement(By.xpath("//div[@id='labTestSummaryGrid']//div[2]//div//div//div//div//div[1]//table//tbody//tr//td")).getText();
		if (!Row.equals("HEMATOLOGY")) {
			CustomReporter.MessageLogger("Alphabetic sequence sort Of Lab Test Summary is working properly", status.Pass);
		} else {
			CustomReporter.MessageLogger("Alphabetic sequence sort Of Lab Test Summary is not working properly", status.Fail);
		}

	}

	public void clickMicroResultTab() {
		CommonLib.clickButton(By.id("labTestSummaryTabs_tablist_mrTab"));
	}

	public void clickGeneralLabTestSummaryTab() {
		CommonLib.clickButton(By.id("labTestSummaryTabs_tablist_otherTab"));
	}

	public List<WebElement> getDates() {
		List<WebElement> listDate = CommonLib.getElements(By.xpath("//div[@id='labTestSummaryGrid']//div//div[2]//div//div//table//tbody//tr//th//div[contains(text(),':')]"));
		return listDate;
	}

	public void sortSequenceByDate() {

		String olddate = CommonLib.getcurrentdatetime("MM/dd/YYYY", -400, "", 0);
		CommonLib.setData(By.id("fromDate"), olddate);
		CommonLib.clickButton(By.xpath("//span[contains(text(),'Search')]"));
		List<WebElement> listDate = getDates();
		if (listDate.size() > 0) {
			for (WebElement item : listDate) {
				String date = item.getText();
				String[] parts = date.split(" ");
				String part1 = parts[0];
				String[] newpart = part1.split("/");
				String n1 = newpart[0];
				String n2 = newpart[1];
				String n3 = newpart[2];
				String finalpart = n1 + "/" + n2 + "/" + "20" + n3;
				int diff = CommonLib.numberOfDaysTo(olddate, finalpart);
				if (!(diff >= 0 && diff <= 400)) {
					CustomReporter.MessageLogger("Date sequence is not working fine.", status.Fail);
					break;
				} else
					CustomReporter.MessageLogger("Date sequence is working fine.", status.Pass);
			}
		}
	}

	public void micro(String id) {

		CommonLib.clickButton(By.id("" + id + ""));
		CommonLib.clickButton(By.id("displayType72"));
		CommonLib.clickButton(By.xpath("//span[contains(text(),'Search')] "));
		CommonLib.clickButton(By.id("displayTypeVisit"));
		CommonLib.clickButton(By.xpath("//span[contains(text(),'Search')] "));
		String m = CommonLib.getElement(By.xpath("//div[@id='mrResults']//span[contains(text(),'Service')]")).getText();
		if (!m.equals("")) {
			CustomReporter.MessageLogger("MicroBiology tab Of Lab Test Summary is working properly", status.Pass);
		} else {
			CustomReporter.MessageLogger("MicroBiology tab Of Lab Test Summary is not working properly", status.Fail);
		}
		// clickGeneralLabTestSummaryTab();
	}

	public void clickFirstRowLTS2() {
		CommonLib.clickButton(By.xpath("//div[@id='labTestSummary2Grid_MICRO']//div[2]//div//div//div//div[1]//table//tbody//tr//td"));
	}

	public void clickHematalogyTab() {
		CommonLib.clickButton(By.id("labTestSummary2Tabs_tablist_tab_HEMATOLOGY"));
	}

	public void clickMicroTab() {
		CommonLib.clickButton(By.id("labTestSummary2Tabs_tablist_tab_MICRO"));
	}

	public void clickPanelRow() {
		CommonLib.clickButton(By.xpath("//div[contains(@id,'labPanelSummaryGrid')]//div[2]//div//div//div//div[1]//table//tbody//tr//td"));
	}

	public void clickTarnscribeRow() {
		CommonLib.clickButton(By.xpath("//div[@id='searchGrid']//div[2]//div//div//div//div//div[1]//table//tbody//tr//td[2]"));

	}

	public void setTranscribeFrame() {
		WebElement GraphFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/order-detail.action?')][last()]"));

		CommonLib.setFrameFromCurrent(GraphFrame);
	}

	public void clickClose() {
		CommonLib.clickButton(By.id("close_label"));
	}

	public void clickTranscribe() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void setDate() {
		CommonLib.setCurrentDate("dictationDate");
	}

	public void setTime() {
		String a = CommonLib.systemTime("");
		CommonLib.setData(By.id("dictationTime"), a);

	}

	public void selectDicPhysician() {
		try {
			CommonLib.selectDojoListValue("dictatingPhysicianList", "searchValue");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickSumbit() {
		CommonLib.clickButton(By.id("buttonSubmitDocument_label"));
	}

}
