package order_entry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class OrderEntryPlus_RxOrderSet extends OrderEntryPlus_OrderPanel {

	public void verifyAddRxOrderSet() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		AddRxOrderSet();
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void AddRxOrderSet() throws InterruptedException {
		CommonLib.changeimplicitwait(4);
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(2);
		int tree_count = tree_list.size();
		try {
			checkOrderPhysicianValue("orderingPhysicianList");
			checkCoSignListBox();
			// CommonLib.selectDojoListValue("coSigningPhysicianList", "");
			// CommonLib.selectRequiredDojoListValue("orderPriorityList",
			// Config.props.getProperty("OrderPriority"));
			CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderStandingOrdersTab"));

		} catch (Exception el) {
			// CommonLib.selectRequiredDojoListValue("orderPriorityList",
			// Config.props.getProperty("OrderPriority"));
			CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderStandingOrdersTab"));
		}
		CommonLib.staticWait(2);
		// searchagridvalue("//div[@id='widget_standingOrdersSearchValue']/div/input[@id='standingOrdersSearchValue'
		// and (@type='text')]", "searchStandingOrders_label");
		searchagridvalue("standingOrdersSearchValue", "searchStandingOrders_label");
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.xpath("//div[@id='standingOrdersGrid']/div[2]/div[@class='dojoxGridView']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[@role='presentation']"));
		WebElement iFrameRXOrderSet = CommonLib.getElement(By.xpath("//iframe[contains(@src, '/OptimumClinicals/standing-orders.action?action')]"));
		CommonLib.setFrameFromCurrent(iFrameRXOrderSet);
		CommonLib.setCheckbox("//div[@name='standingOrdersGrid_rowSelector' and (contains(@id,'standingOrdersGrid_rowSelector_'))]");
		CommonLib.clickButton(By.id("button.submit_label"));
		CommonLib.staticWait(2);
		CommonLib.GetDriver().switchTo().parentFrame();
		
		try {
			CommonLib.changeimplicitwait(1);
			for (int i = 1; i < 4; i++) {
				errorHandling();
				checkClinicalWarning();
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		// WebElement iFrameOrderEntry =
		// CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-detail')][last()]"));
		// CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		// CommonLib.staticWait(1);
		// if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog'
		// or @id='reminderDialog']/div[2]/table/tbody/tr/td"), 5)) {
		// CommonLib.clickButton(By.xpath("//div[@id='reminderDialog'or
		// @id='errorDialog']/div[2]/table/tbody/tr[4]/td/span/span/span/span[3]"));
		// }
		// CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 3);
		// boolean pnrElement =
		// CommonLib.getElement(By.xpath("//input[@id='prnIndList']")).isEnabled();
		// if (pnrElement == true) {
		// CommonLib.selectDojoListValue("prnIndList", "");
		// }
		// CommonLib.clickButton(By.id("button.submit_label"));
		// CommonLib.staticWait(5);
		// ClinicalWarning();
		// errorHandling();
		// checkClinicalWarning();
		// }
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		WebElement nwb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> newtree_list = nwb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(2);
		int newtree_count = newtree_list.size();
		if (newtree_count > tree_count) {
			CustomReporter.MessageLogger("Rx order set added successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add a Rx order set", status.Fail);
		}
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void searchagridvalue(String searchboxid, String searchbuttonid) {

		CommonLib.setData(By.id(searchboxid), Config.props.getProperty("rxorderset"));
		CommonLib.clickButton(By.id(searchbuttonid));
		int rowcount = CommonLib.getElements(By.xpath("//div[@id='orderSetGrid']/div[2]/div/div/div/div/div/table/tbody/tr")).size();
		// int rowcount =
		// CommonLib.getElements(By.xpath("//div[@id='dojox_grid__View_9']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]")).size();
		if (rowcount >= 1) {

			CommonLib.clickButton(By.xpath("//div[@id='orderSetGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td/a[contains(@class, 'tableLink')]"));
		}
	}

	public void ClinicalWarning() throws InterruptedException {

		if (CommonLib.isElementPresent(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')]"), 1)) {
			WebElement iFrameClinicalWarning = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings.action?action')]"));
			CommonLib.setFrameFromCurrent(iFrameClinicalWarning);
			CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
			CommonLib.clickButton(By.xpath(".//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
			CommonLib.staticWait(2);
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);

		} else {
			CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
			CommonLib.clickButton(By.xpath(".//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
			CommonLib.staticWait(3);
		}

	}

}
