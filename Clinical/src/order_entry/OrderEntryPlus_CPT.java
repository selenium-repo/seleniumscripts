package order_entry;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class OrderEntryPlus_CPT extends OrderEntryPlus_Medication {

	public void cpt() throws Exception {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.selectRequiredDojoListValue("orderPriorityList", Config.props.getProperty("orderPriorityInCPT"));
		CommonLib.clickButton(By.xpath("//span[@id='orderEntryTabs_tablist_orderCptTab']"));
		// processOrders();
		addCPTOrder();
	}

	public void addCPTOrder() {

		// boolean flag = false;
		int count_before_add = getOrderBaskeTCount();
		CommonLib.setData(By.xpath("//input[@id='cptSearchValue']"), "*");
		CommonLib.clickButton(By.xpath("//span[@id='searchCPT_label']"));
		CommonLib.clickButton(By.xpath("//div[@id='cptGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]"));
		// searchfromgrid("cptSearchValue", "searchCPT_label");
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"), 3)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		}
		int count_after_add = getOrderBaskeTCount();
		if (count_after_add > count_before_add) {
			CustomReporter.MessageLogger("Order Entry Plus Add Functionality in Therapeutic Class Tab is working properly as new Order has been added", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality in Therapeutic Class Tab is not working properly as new Order has not been added, please try again!", status.Fail);
		}
	}

	public void searchfromgrid(String searchboxid, String searchbuttonid) {

		boolean flag = false;
		try {
			do {
				Random r = new Random();
				char alpha = (char) (r.nextInt(26) + 'a');
				CommonLib.setData(By.id(searchboxid), String.valueOf(alpha));
				CommonLib.clickButton(By.id(searchbuttonid));
				if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='cptGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]")).size() == 0)
					flag = false;
				else {
					CommonLib.clickButton(By.xpath("//div[@id='cptGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]"));
					flag = true;
				}
			} while (flag != true);

		} catch (Exception e) {

			CommonLib.resetImplicitWait();
		}
	}

	public void processOrders() {

		List<WebElement> orderList = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon errorIcon']"));
		if (orderList.size() > 0) {
			for (WebElement order : orderList) {
				orderList.get(orderList.size() - 1).click();
				CommonLib.clickButton(By.xpath("//span[@id='processBasket']"));
				WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
				CommonLib.setFrameFromCurrent(iFrameOrderEntry);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
				CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
			}
			if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon errorIcon']")).size() > 0) {
				CustomReporter.MessageLogger("Failed, Order Entry Plus Process functionality is not working properly, please try again!", status.Fail);
			} else {
				CustomReporter.MessageLogger("Order Entry Plus Process functionality is working properly", status.Pass);
			}
		} else
			CustomReporter.MessageLogger("There are no orders to be processed", status.Pass);
	}

	public int getOrderBaskeTCount() {
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(1);
		int count = tree_list.size();
		return count;

	}

}
