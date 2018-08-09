package OrderManagerPlus.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import OrderManagerPlus.pages.OrderManagerPlusPage;
import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;

public class AllFunctionalityTestcases extends OrderManagerPlusPage {

	public void verifyCarePlan() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickCarePlans();
		WebElement iFrameCarePlan = CommonLib.getElement(By.xpath("//iframe[contains(@src, '/OptimumClinicals/optimum.clinicals.pac.sections/CarePlanPlus')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameCarePlan);
		if (CommonLib.GetDriver().findElements(By.xpath("//div[@class='dojoxGridMasterView']//div[@id='dojox_grid__View_1']//span")).size() > 0) {
			String text = CommonLib.getElement(By.xpath("//div[@class='dojoxGridMasterView']//div[@id='dojox_grid__View_1']//span")).getText();
			if (!text.equals("Sorry, an error occurred"))
				CustomReporter.MessageLogger("Careplan tab fumctionality is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Failed, Careplan fumctionality is not working properly, which is not as expected", status.Fail);
		} else
			CustomReporter.MessageLogger("Careplan tab fumctionality is working properly", status.Pass);
		CommonLib.clickButton(By.id("close_label"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void verifyPrintAll() {
		clickPrintAll();
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("PrintAll Functionality Of Order Manager Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("PrintAll Functionality Of Order Manager Plus is not working properly", status.Fail);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void verifyPrint() {
		clickPrint();
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Order Manager Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Order Manager Plus is not working properly", status.Fail);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void verifyFind() {
		findOrder(true);
		boolean isPresent = false;
		String findText = " ";
		while (isPresent != true) {
			if (CommonLib
					.getElements(By.xpath(
							"//div[@class='dijitTabContainerTopChildWrapper dijitVisible']//div//div//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td/div[@id='orderWorklistGrid_rowSelector_0']"))
					.size() > 0) {
				findText = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[6]")).getText();
				isPresent = true;
				break;
			} else if (CommonLib.getElements(By.xpath(
					"//div[@class='dijitTabContainerTopChildWrapper dijitVisible']//div//div//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td/div[@id='allOrdersWorklistGrid_rowSelector_0']"))
					.size() > 0) {
				findText = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[6]")).getText();
				isPresent = true;
				break;
			} else if ((CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[3]//span")).getText()).equals("No orders.")) {
				isPresent = true;
				break;
			} else if ((CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[3]//span")).getText()).equals("No orders to display.")) {
				isPresent = true;
				break;
			} else if ((CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[3]//span")).getText()).equals("Sorry, an error occurred")) {
				isPresent = true;
				break;
			} else if ((CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[3]//span")).getText()).equals("Sorry, an error occurred")) {
				isPresent = true;
				break;
			}
		}
		if (!findText.equals(" ")) {
			String[] findTextLine = findText.split("\n");
			findText = findTextLine[0];
			if (findText.equals("Radiology"))
				CustomReporter.MessageLogger("Find Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Find Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else {
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
		}
		findOrder(false);
	}

	public void verifyRefresh() {
		clickNonPharmacyTab();
		clickRefresh();
	}

	public void verifyReceive() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickReceive();
		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[0];
			if (Orderstatus.equals("Received"))
				CustomReporter.MessageLogger("Receive Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Receive Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyNote() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickNote();
		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[0];
			if (Orderstatus.equals("Noted"))
				CustomReporter.MessageLogger("Note Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Note Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyVerify() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickVerify();
		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[0];
			if (Orderstatus.equals("Verified"))
				CustomReporter.MessageLogger("Verify Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Verify Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyCollect() {
		clickNonPharmacyTab();
		selectCheckBox();
		collectOrder();
		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[1];
			if (Orderstatus.equals("Unacknowledged"))
				CustomReporter.MessageLogger("Collect Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Collect Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyAcknowledgement() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickAck();
		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[0];
			if (Orderstatus.equals("Acknowledged"))
				CustomReporter.MessageLogger("Acknowledgement Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Acknowledgement Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyImportResult() {
		String OrderStatus = " ";
		int i = 0;
		clickNonPharmacyTab();
		selectCheckBox();
		String Ordertime = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[3]")).getText();
		try {
			importResult();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickAllTab();
		List<WebElement> li = CommonLib.GetDriver().findElements(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[5]"));
		for (WebElement list : li) {
			i++;
			if (Ordertime.equals(list.getText())) {

				OrderStatus = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div[" + i + "]//table/tbody/tr/td[3]")).getText();
				break;
			}
		}
		if (OrderStatus.equals("Result"))
			CustomReporter.MessageLogger("Import Result Functionality Of Order Manager Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Import Result Functionality Of Order Manager Plus is not working properly", status.Fail);

	}

	public void verifyResults() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickResults();
		try {
			WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'results-view-multiple')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderManager);
			CommonLib.clickButton(By.xpath("//span[@id='close_label']"));
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
			CustomReporter.MessageLogger("Results Functionality Of Order Manager Plus is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Results Functionality Of Order Manager Plus is not working properly", status.Fail);
		}
	}

	public void verifyCancel() {
		String OrderStatus = " ";
		int i = 0;
		clickNonPharmacyTab();
		selectCheckBox();
		String Ordertime = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[3]")).getText();
		cancelOrder();
		clickAllTab();
		List<WebElement> li = CommonLib.GetDriver().findElements(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[5]"));
		for (WebElement list : li) {
			i++;
			if (Ordertime.equals(list.getText())) {

				OrderStatus = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div[" + i + "]//table/tbody/tr/td[3]")).getText();
				break;
			}
		}
		if (OrderStatus.equals("Cancelled"))
			CustomReporter.MessageLogger("Cancel Functionality Of Order Manager Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Cancel Functionality Of Order Manager Plus is not working properly", status.Fail);
	}

	public void verifyUncancel() throws InterruptedException {
		String OrderStatus = " ";
		int i = 0;
		clickAllTab();
		selectCheckBox();
		String Ordertime = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[5]")).getText();
		clickUnCancel();
		List<WebElement> li = CommonLib.GetDriver().findElements(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[5]"));
		for (WebElement list : li) {
			i++;
			if (Ordertime.equals(list.getText())) {

				OrderStatus = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div[" + i + "]//table/tbody/tr/td[3]")).getText();
				break;
			}
		}
		if (!OrderStatus.equals("Cancelled"))
			CustomReporter.MessageLogger("UnCancel Functionality Of Order Manager Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("UnCancel Functionality Of Order Manager Plus is not working properly", status.Fail);
	}

	public void verifyDiscontinue() throws InterruptedException {
		String OrderStatus = " ";
		int i = 0;
		clickNonPharmacyTab();
		selectCheckBox();
		String Ordertime = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[3]")).getText();
		discontinue();
		clickAllTab();
		List<WebElement> li = CommonLib.GetDriver().findElements(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[5]"));
		for (WebElement list : li) {
			i++;
			if (Ordertime.equals(list.getText())) {

				CommonLib.staticWait(6);
				OrderStatus = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div[" + i + "]//table/tbody/tr/td[3]")).getText();
				break;
			}
		}
		if (OrderStatus.equals("Discontinued")) {
			CustomReporter.MessageLogger("Order was discontinued successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Discontinue order fumctionality is not working properly, which is not as expected", status.Fail);
		}
	}

	public void verifyHold() {
		clickNonPharmacyTab();
		selectCheckBox();
		holdOrder();
		CommonLib.staticWait(6);
		String OrderStatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (OrderStatus.equals("Held")) {
			CustomReporter.MessageLogger("Order was put on hold successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Held fumctionality is not working properly, which is not as expected", status.Fail);
		}
	}

	public void verifyRelease() {
		clickNonPharmacyTab();
		String b = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table/tbody/tr/td[3]")).getText();
		selectCheckBox();
		clickRelease();
		release();
		if (!b.equals("HELD")) {
			CustomReporter.MessageLogger("Order was released successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Release order fumctionality is not working properly, which is not as expected", status.Fail);
		}
	}

	public void verifyChange() {
		clickNonPharmacyTab();
		selectCheckBox();
		clickChange();
		change();

		CommonLib.staticWait(6);
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div[2]//div//div//div//div//table[@class='dojoxGridRowTable']/tbody/tr/td/table/tbody/tr[4]/td/blockquote")).getText();
		if (!Orderstatus.equals(" ")) {
			if (Orderstatus.equals("data"))
				CustomReporter.MessageLogger("Order was changed successfully", status.Pass);
			else
				CustomReporter.MessageLogger("Failed, Change fumctionality is not working properly, which is not as expected", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

	public void verifyReprint() throws InterruptedException {
		clickNonPharmacyTab();
		selectCheckBox();
		clickReprint();
		reprint();
	}

	public void verifyAckUpd() throws InterruptedException {
		placeOrderTestcase pot = new placeOrderTestcase();
		ServiceCodeTestcase sct = new ServiceCodeTestcase();
		sct.servicecodetable_acknowledgement();
		pot.placeOrderFromOrderEntryPlus(Config.props.getProperty("drug_AckUpd"));
		clickNonPharmacyTab();
		selectCheckBox();
		ackUpdOrder();
		String Orderstatus = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td[3]")).getText();
		if (!Orderstatus.equals(" ")) {
			String[] findTextLine = Orderstatus.split("\n");
			Orderstatus = findTextLine[0];
			if (Orderstatus.equals("Acknowledged"))
				CustomReporter.MessageLogger("Acknowledgement Functionality Of Order Manager Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Acknowledgement Functionality Of Order Manager Plus is not working properly", status.Fail);
		} else
			CustomReporter.MessageLogger("There are no such orders", status.Pass);
	}

}
