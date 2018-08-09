package order_entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class OrderEntryPlus_Abbrevonym extends OrderEntryPlus_Service {

	public void verifyOrderEntryPlusAbbrevonymTab() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		addAbbrevonym();

	}

	public void addAbbrevonym() {

		int count_before = getOrderBaskeTCount();
		CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderAbbrevonymnTab"));
		String service_code = HashTableRepository.getHash("service_code");
		CommonLib.setData(By.id("itemCode"), service_code);
		CommonLib.clickButton(By.xpath("//span[@title='Add']/span[contains(@class,'dijitIcon addIcon')]"));
		CommonLib.staticWait(3);
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"), 3)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.staticWait(3);
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		}
		int count_after = getOrderBaskeTCount();
		if (count_after > count_before) {
			CustomReporter.MessageLogger("Order having the service code :'" + service_code + "' has been placed in the Order Basket by Abbrevonym Tab successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to place the Order in the Order Basket by the Abbrevonym Tab, please try again!", status.Fail);
		}

	}

}
