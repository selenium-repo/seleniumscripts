package order_entry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class OrderEntryPlus_Service extends OrderEntryPlus_OrderPanel {

	public String service_code;
	public String service_desc;

	public void verifyOrderEntryPlusServiceTab() throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		addService(false, "", "");

	}

	public void addService(boolean orderManagerPlus, String edit_order_priority, String drug_servicecode) {
		int count_before = getOrderBaskeTCount();
		String str = " ";
		if (orderManagerPlus != true) {
			char ch = CommonLib.getRandomCharacter();
			str = new StringBuilder().append(ch).toString();
		} else {
			CommonLib.selectRequiredDojoListValue("orderPriorityList", edit_order_priority);
			CommonLib.staticWait(3);
		}
		CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderServiceTab"));
		if (orderManagerPlus == true)
			CommonLib.setData(By.id("serviceSearchValue"), drug_servicecode);
		if (orderManagerPlus == false)
			CommonLib.setData(By.id("serviceSearchValue"), str);
		CommonLib.clickButton(By.id("searchService_label"));
		CommonLib.staticWait(3);
		if (CommonLib.getElements(By.xpath("//div[@id='serviceGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size() > 0) {
			WebElement wb = CommonLib.getElement(By.xpath("//div[@id='serviceGrid']"));
			WebElement web_cell1 = wb.findElement(By.xpath(".//tr[1]/td[1]"));
			WebElement web_cell2 = wb.findElement(By.xpath(".//tr[1]/td[2]"));
			service_code = web_cell1.getText();
			service_desc = web_cell2.getText();
			HashTableRepository.setHash("service_code", web_cell1.getText());
			HashTableRepository.setHash("service_desc", web_cell2.getText());
			// System.out.println(service_code);
			web_cell1.click();
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"), 5)) {
				WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
				CommonLib.setFrameFromCurrent(iFrameOrderEntry);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
				CommonLib.staticWait(3);
			}
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			int count_after = getOrderBaskeTCount();
			if (count_after > count_before) {
				CustomReporter.MessageLogger("Order having the service code :'" + service_code + "' has been placed in the Order Basket by Service Tab successfully", status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed to place the Order in the Order Basket by the Service Tab, please try again!", status.Fail);
			}
		} else {
			CustomReporter.MessageLogger("There is no data rows displaying for the current search criteria, please try again!", status.Fail);
		}

	}

	public int getOrderBaskeTCount() {
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(1);
		int count = tree_list.size();
		return count;

	}

}
