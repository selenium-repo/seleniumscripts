package order_entry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class OrderEntryPlus_OrderSet extends OrderEntryPlus_OrderPanel {

	public void verifyOrderSetFunctions() throws InterruptedException {

		MenuNavigation.menuNav("Order Entry Plus");
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		String order_set = Config.props.getProperty("order_set_other");
		String edit_order = Config.props.getProperty("edit_order");
		// String pharm_order = Config.props.getProperty("pharm_order");

		boolean result = addOrderSetFromTab(order_set);
		if (result) {
			// sortOrderBasket();
			deleteOrderSetFromBasket(edit_order);
			// addOrderSetFromTab(order_set);
			// addOrderSetFromTab(order_set);
			// checkDuplicateOrders(pharm_order);
			// clinicalWarning(pharm_order);
			// editOrderBasket(edit_order);
			// processOrderBasket(pharm_order);
			// completeOrderBasket();
		} else {
			CustomReporter.MessageLogger("Failed to add the Order Set to the Order Entry Plus screen, to check the Order Entry Plus functionalities", status.Fail);
		}

	}

	public boolean addOrderSetFromTab(String order_set) throws InterruptedException {
		boolean result = false;
		CommonLib.staticWait(3);
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(1);
		int basket_count_before = tree_list.size();
		System.out.println(basket_count_before);
		if (basket_count_before > 1) {
			for (WebElement web_ele : tree_list) {
				System.out.println(web_ele.getText());
				CustomReporter.MessageLogger("Before adding the Order Set :" + order_set + " there are : " + basket_count_before + " Orders in the Order Basket, they are: " + web_ele.getText() + "", status.Information);
				CommonLib.staticWait(1);
			}
		}
		try {
			checkOrderPhysicianValue("orderingPhysicianList");
			checkCoSignListBox();
			// CommonLib.selectRequiredDojoListValue("orderPriorityList",
			// Config.props.getProperty("OrderPriority"));
			CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderOrderSetTab"));
			CommonLib.staticWait(2);
			searchagridvalue("orderSetSearchValue", "searchOrderSet_label");

		} catch (Exception el) {
			// CommonLib.selectRequiredDojoListValue("orderPriorityList",
			// Config.props.getProperty("OrderPriority"));
			CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderOrderSetTab"));
			searchagridvalue("orderSetSearchValue", "searchOrderSet_label");
		}
		CommonLib.staticWait(4);
		WebElement iFrameOrderSet = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'order-entry-order-set-detail.action')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderSet);
		CommonLib.clickButton(By.xpath("//div[@id='orderSetDetailContainer']/div[3]/table/tbody/tr/td/span/span/span/span[3][last()]"));
		CommonLib.staticWait(4);
		CommonLib.GetDriver().switchTo().parentFrame();
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@src,'order-entry')][last()]"), 5)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
		}
		try {
			CommonLib.changeimplicitwait(1);
			for (int i = 1; i < 7; i++) {
				errorHandling();
				checkClinicalWarning();
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		CommonLib.staticWait(2);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		WebElement wb2 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list2 = wb2.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int basket_count_after = tree_list2.size();
		System.out.println(basket_count_after);

		if (basket_count_after > basket_count_before) {
			for (WebElement web_ele : tree_list2) {
				System.out.println(web_ele.getText());
				CustomReporter.MessageLogger("After adding the Order Set :" + order_set + " there are : " + basket_count_after + " Orders in the Order Basket, and one of the Order in that is: " + web_ele.getText() + "", status.Pass);
				result = true;
			}
		} else {
			CustomReporter.MessageLogger("Failed to add the Orders through Order Set Tab, please try again!", status.Fail);
		}
		return result;

	}

	public void searchagridvalue(String searchboxid, String searchbuttonid) {

		CommonLib.setData(By.id(searchboxid), Config.props.getProperty("order_set_other"));
		CommonLib.clickButton(By.id(searchbuttonid));
		int rowcount = CommonLib.getElements(By.xpath("//div[@id='orderSetGrid']/div[2]/div/div/div/div/div/table/tbody/tr")).size();
		// int rowcount =
		// CommonLib.getElements(By.xpath("//div[@id='dojox_grid__View_9']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]")).size();
		if (rowcount >= 1) {

			CommonLib.clickButton(By.xpath("//div[@id='orderSetGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td/a[contains(@class, 'tableLink')]"));
		}

	}

	public void processOrderBasket(String order) {

		if (CommonLib.isElementPresent(By.xpath("//span/following-sibling::span[contains(@class,'errorIcon')]"), 5)) {
			CommonLib.clickButton(By.xpath("//span[@id='processBasket']"));
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-detail')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
			for (int i = 1; i < 3; i++) {
				try {
					errorHandling();
					checkClinicalWarning();

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		} else {
			CommonLib.changeimplicitwait(5);
			CommonLib.clickButton(By.id("orderEntryTabs_tablist_orderOrderSetTab"));
			searchagridvalue("orderSetSearchValue", "searchOrderSet_label");
			WebElement iFrameOrderSet = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'order-entry-order-set-detail.action')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderSet);
			CommonLib.clickButton(By.xpath("//div[@id='orderSetDetailContainer']/div[3]/table/tbody/tr/td/span/span/span/span[3][last()]"));
			CommonLib.staticWait(4);
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.GetDriver().switchTo().defaultContent();
			MenuNavigation.menuNav("Order Entry Plus");
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			List<WebElement> order_bskt = getNoOfWebElements(order);
			int basket_count_before = order_bskt.size();
			order_bskt.get(basket_count_before - 1).click();
			CommonLib.clickButton(By.xpath("//span[@id='processBasket']"));
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-detail')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
			for (int i = 1; i < 3; i++) {
				try {
					errorHandling();
					checkClinicalWarning();

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}

		if (CommonLib.getElements(By.xpath("//span/following-sibling::span[contains(@class,'errorIcon')]")).size() == 0) {
			CustomReporter.MessageLogger("Process Icon of Order Basket is working properly, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Process Icon of Order Basket is not working properly, which is not as expected", status.Fail);
		}

	}

}
