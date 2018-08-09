package order_entry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ClinicalFunction.allergies;
import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class OrderEntryPlus_OrderPanel extends allergies {

	public void verifyOrderEntryPlusFunctions() throws Exception {
		CommonLib.changeimplicitwait(6);
		MenuNavigation.menuNav("Order Entry Plus");
		if (CommonLib.isElementPresent(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr/td"), 3)) {
			String warn_msg = CommonLib.getElement(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr/td")).getText();
			if (warn_msg.contains("Allergies must be entered")) {
				CommonLib.clickButton(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				MenuNavigation.menuNav("Allergies");
				addAllergy(true);
				MenuNavigation.menuNav("Order Entry Plus");
			}
		}
		CommonLib.staticWait(2);
		CommonLib.setFrameToDefault();
		String screen_id = CommonLib.getText(By.xpath("//div[@id='divTitleBar']/table/tbody/tr/td[3]/span[2]"));
		if (screen_id.contains("Order Entry")) {
			CustomReporter.MessageLogger("Navigated to the Screen ID:" + screen_id + " to check the Order Entry Plus functionalities", status.Pass);
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			String order_test = Config.props.getProperty("other_test");
			String order_type = Config.props.getProperty("order_type");
			// String order_set = Config.props.getProperty("order_set");
			// String non_pharm_order = Config.props.getProperty("non_pharm");
			String lab_order = Config.props.getProperty("lab_order");
			completeOrderBasket();

			addOrderBasket(order_type, order_test);
			CommonLib.staticWait(3);
			editOrderBasket(order_test);
			if (order_type.contains("Med")) {
				clinicalWarning(order_test);
			}
			processOrderBasket(order_test);
			CommonLib.staticWait(5);
			deleteOrderBasket(order_test);
			addRepeatOrders(lab_order);
			completeOrderBasket();

			//
			// boolean result = addOrderSetFromOrderPanel(order_set);
			// if (result) {
			// sortOrderBasket();
			// deleteOrderSetFromBasket(order_set);
			// CommonLib.staticWait(5);
			// MenuNavigation.menuNav("Order Entry Plus");
			// boolean check = addOrderBasket("", non_pharm_order);
			// if (check) {
			// addOrderBasket("", non_pharm_order);
			// checkDuplicateOrders(non_pharm_order);
			// }
			// addOrderBasket(order_type, order_test);
			// CommonLib.staticWait(3);
			// editOrderBasket(order_test);
			// if (order_type.contains("Med")) {
			// clinicalWarning(order_test);
			// }
			// processOrderBasket(order_test);
			// CommonLib.staticWait(5);
			// deleteOrderBasket(order_test);
			// addRepeatOrders(lab_order);
			// completeOrderBasket();
			//
			// } else {
			// CustomReporter.MessageLogger("Failed to add the Order Set to the
			// Order Entry Plus screen, to check the Order Entry Plus
			// functionalities", status.Fail);
			// }

		} else {
			CustomReporter.MessageLogger("Failed to navigate to the Order Entry screen ID, to check the Order Entry Plus functionalities", status.Fail);
		}

	}

	public void addRepeatOrders(String lab_order) throws InterruptedException {

		// CommonLib.selectDojoListValue("orderingPhysicianList", "");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.staticWait(2);
		CommonLib.selectRequiredDojoListValue("orderPriorityList", Config.props.getProperty("order_priority"));
		CommonLib.staticWait(3);
		String order_panel = CommonLib.selectRequiredDojoListValue("orderPanelsList", Config.props.getProperty("order_panel"));
		CommonLib.staticWait(3);
		List<WebElement> list_web_elemts = getNoOfWebElements(lab_order);
		int basket_count_before = list_web_elemts.size();
		System.out.println(basket_count_before);
		CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + lab_order + "') and contains(@href,'addItemToBasketWithOrderType')]"));
		CommonLib.staticWait(5);
		// getIframeCount();
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"), 7)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			CommonLib.clickButton(By.xpath("//span[@id='addRepeat_label' and contains(@class, 'dijitButtonText')]"));
			CommonLib.staticWait(2);
			WebElement iFrameRepeatOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-repeat.action')]"));
			CommonLib.setFrameFromCurrent(iFrameRepeatOrderEntry);
			CommonLib.selectDojoDropDownByKeyDownNumber("sigList", 5);
			CommonLib.setData(By.xpath("//input[@id='duration' and contains(@class,'dijitInputInner')]"), "10");
			CommonLib.selectRequiredDojoListValue("durationTypeList", "Days");
			CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitRepeat_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.staticWait(3);
		}
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		List<WebElement> list_web_elemts1 = getNoOfWebElements(lab_order);
		int basket_count_after = list_web_elemts1.size();
		System.out.println(basket_count_after);
		if (basket_count_after > basket_count_before) {
			CustomReporter.MessageLogger("Repeat Order :'" + lab_order + "' has been placed in the Order Basket by Order Panel " + order_panel + " successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to place the Reapeat Order :'" + lab_order + "' in the Order Basket, please try again!", status.Fail);
		}
		return;

	}

	public boolean addOrderBasket(String order_type, String order) throws InterruptedException {
		CommonLib.changeimplicitwait(6);
		boolean result = false;
		CommonLib.staticWait(2);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		if (order_type != "") {
			// CommonLib.clickButton(By.xpath("//form[@id='orderEntry']/table/tbody/tr[8]/td/table[@id='orderTypeList']/tbody/tr/td/div/span"));
			// WebElement wb =
			// CommonLib.getElement(By.xpath("//form[@id='orderEntry']/table/tbody/tr[8]/td/table[@id='orderTypeList']/tbody/tr/td/div/span"));
			// wb.sendKeys("");
			// wb.sendKeys(order_type);
			// wb.sendKeys(Keys.RETURN);
			CommonLib.clickButton(By.xpath("//table[@id='orderTypeList']/tbody/tr/td[2]/input"));
			CommonLib.clickButton(By.xpath("//table[@id='orderTypeList_menu']/tbody/tr/td[text()='" + order_type + "']"));

		}
		// CommonLib.selectRequiredDojoListValue("orderPriorityList",Config.props.getProperty("order_priority"));
		CommonLib.staticWait(3);
		String order_panel = CommonLib.selectRequiredDojoListValue("orderPanelsList", Config.props.getProperty("order_panel"));
		CommonLib.staticWait(3);
		List<WebElement> list_web_elemts = getNoOfWebElements(order);
		int basket_count_before = list_web_elemts.size();
		System.out.println(basket_count_before);
		CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + order + "') and contains(@href,'addItemToBasketWithOrderType')]"));
		CommonLib.staticWait(5);
		getIframeCount();
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"), 5)) {
			WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameOrderEntry);
			String currentFrameName = (String) ((JavascriptExecutor) CommonLib.GetDriver()).executeScript("return window.frameElement.name");
			System.out.println(currentFrameName);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
			if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 2)) {
				CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
				if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 2)) {
					CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
					CommonLib.selDojoListValue("prnIndList", 3);
					CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
				}
				if (CommonLib.isElementPresent(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')][last()]"), 2)) {
					WebElement iFrameClinicalWarning = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')][last()]"));
					CommonLib.setFrameFromCurrent(iFrameClinicalWarning);
					CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
					CommonLib.clickButton(By.xpath("//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
					CommonLib.staticWait(3);
					CommonLib.GetDriver().switchTo().parentFrame();

				}
			}
		}
		CommonLib.staticWait(4);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		String currentFrameName1 = (String) ((JavascriptExecutor) CommonLib.GetDriver()).executeScript("return window.frameElement.name");
		System.out.println(currentFrameName1);
		List<WebElement> order_bskt_obj = getNoOfWebElements(order);
		int basket_count_after = order_bskt_obj.size();
		System.out.println(basket_count_after);
		if (basket_count_after > basket_count_before) {
			CustomReporter.MessageLogger("Order :'" + order + "' has been placed in the Order Basket by Order Panel " + order_panel + " successfully", status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to place the Order :'" + order + "' in the Order Basket, please try again!", status.Fail);
		}
		return result;

	}

	public void editOrderBasket(String order) throws InterruptedException {
		CommonLib.changeimplicitwait(6);
		List<WebElement> order_bskt = getNoOfWebElements(order);
		int basket_count_before = order_bskt.size();
		System.out.println("count:" + basket_count_before);
		CommonLib.staticWait(4);
		// CommonLib.GetDriver().findElement(By.xpath("//span[contains(text(),'"
		// + order + "') and @class='dijitTreeLabel'][last()]")).click();
		order_bskt.get(basket_count_before - 1).click();
		String before_edit = order_bskt.get(basket_count_before - 1).getText();
		CommonLib.clickButton(By.xpath("//span[@id='editBasketItem']"));
		CommonLib.staticWait(1);
		getIframeCount();
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-detail')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);

		if (CommonLib.isElementPresent(By.xpath("//input[@id='orderTypeList']"), 1)) {
			CommonLib.selectRequiredDojoListValue("orderTypeList", Config.props.getProperty("edit_order_priority"));
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
		} else {
			if (CommonLib.isElementPresent(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr/td"), 1)) {
				CommonLib.clickButton(By.xpath("//div[@id='reminderDialog']/div[2]/table/tbody/tr[4]/td/span/span/span/span[3]"));
			}
			CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 2);
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 1)) {
				CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				CommonLib.selDojoListValue("prnIndList", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			}
			WebElement iFrameClinicalWarning = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')]"));
			CommonLib.setFrameFromCurrent(iFrameClinicalWarning);
			CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
			CommonLib.clickButton(By.xpath("//span[@id='buttonContinue_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.GetDriver().switchTo().parentFrame();
		}
		CommonLib.staticWait(1);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		List<WebElement> order_bskt_obj = getNoOfWebElements(order);
		int basket_count_after = order_bskt_obj.size();
		String after_edit = order_bskt_obj.get(basket_count_after - 1).getText();
		if (before_edit != after_edit) {
			CustomReporter.MessageLogger("Before editing the Order is displaying as :" + before_edit + " and after editing the Order is displaying as: " + after_edit + " in the Order Basket successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit the Order :'" + order + "' in the Order Basket, please try again!", status.Fail);
		}

	}

	public void getIframeCount() {
		CommonLib.staticWait(1);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		int i_count = CommonLib.getElements(By.xpath(".//iframe[@id='iFrameDialog' and contains(@src,'order-entry')]")).size();
		System.out.println("The iframe count is:" + i_count);

	}

	public List<WebElement> getNoOfWebElements(String order) {
		// div[@id='orderBasketTree']
		List<WebElement> getWebElements = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']//span[contains(.,'" + order + "') and @class='dijitTreeLabel']"));
		return getWebElements;

	}

	public void deleteOrderBasket(String order) {
		CommonLib.changeimplicitwait(8);
		List<WebElement> order_bskt1 = getNoOfWebElements(order);
		int basket_count_bef = order_bskt1.size();
		System.out.println(basket_count_bef);
		order_bskt1.get(basket_count_bef - 1).click();
		String to_delete = order_bskt1.get(basket_count_bef - 1).getText();
		System.out.println(to_delete);
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//span[@id='deleteBasketItem']"));
		CommonLib.staticWait(2);
		List<WebElement> order_bskt_aft = getNoOfWebElements(order);
		int basket_count_aft = order_bskt_aft.size();
		if (basket_count_aft < basket_count_bef) {
			CustomReporter.MessageLogger(
					"Before deleting the Order: " + to_delete + " the number of Orders displying was:" + basket_count_bef + " and after deleting the Order number of Orders displaying as: " + basket_count_aft + " in the Order Basket",
					status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete the Order :'" + order + "' in the Order Basket, please try again!", status.Fail);
		}
	}

	public void deleteOrderSetFromBasket(String order) {
		CommonLib.changeimplicitwait(15);
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(2);
		int tree_count = tree_list.size();
		String last_order = tree_list.get(tree_count - 1).getText();
		tree_list.get(tree_count - 1).click();
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//span[@id='deleteBasketChain']"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='deleteOrderSetDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='deleteOrderSetDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		}
		CommonLib.staticWait(15);
		WebElement web = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list1 = web.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(2);
		int tree_count_after = tree_list1.size();
		if (tree_count_after < tree_count) {
			CustomReporter.MessageLogger("Before deleting the Order Set : " + order + " the number of Orders displying was:" + tree_count + " and after deleting the Order " + last_order + " number of Orders displaying as: '"
					+ tree_count_after + "' in the Order Basket", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to delete the Order Set :'" + order + "' in the Order Basket, please try again!", status.Fail);
		}
	}

	public void clinicalWarning(String order) {

		List<WebElement> order_bskt = getNoOfWebElements(order);
		int basket_count_before = order_bskt.size();
		order_bskt.get(basket_count_before - 1).click();
		CommonLib.clickButton(By.xpath("//span[@id='clinicalWarnings']"));
		WebElement iFrameClinicalWarning = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')]"));
		CommonLib.setFrameFromCurrent(iFrameClinicalWarning);
		String warning_title = CommonLib.getElement(By.xpath("//span[contains(text(),'Clinical Warnings') and  (@class='iframeDialogTitle')]")).getText();
		CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
		CommonLib.clickButton(By.xpath(".//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
		String verify_warning = Config.props.getProperty("clinical_warning");
		if (warning_title.contains(verify_warning)) {
			CustomReporter.MessageLogger("Clinical Warning button is working properly as it is displaying the CLinical Warning window successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Clinical Warning button is not working properly as it is not displaying the CLinical Warning window, please try again!", status.Fail);
		}
		CommonLib.GetDriver().switchTo().parentFrame();

	}

	public void processOrderBasket(String order) throws InterruptedException {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + order + "') and contains(@href,'addItemToBasketWithOrderType')]"));

		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"), 4)) {
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
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		if (CommonLib.getElements(By.xpath("//span/following-sibling::span[contains(@class,'errorIcon')]")).size() == 0) {
			CustomReporter.MessageLogger("Process Icon of Order Basket is working properly, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Process Icon of Order Basket is not working properly, which is not as expected", status.Fail);
		}

	}

	public void sortOrderBasket() {
		CommonLib.staticWait(3);
		WebElement web_ele = CommonLib.getElement(By.xpath("//div[@widgetId='basketView']/div/input[contains(@class, 'dijitArrowButtonInner') and @value='▼ ']"));
		// web_ele.clear();
		web_ele.click();
		CommonLib.staticWait(2);
		Actions builder = new Actions(CommonLib.GetDriver());
		builder.sendKeys(Keys.ARROW_DOWN).perform();
		builder.sendKeys(Keys.ARROW_DOWN).perform();
		builder.sendKeys(Keys.ENTER).perform();
		CommonLib.staticWait(3);
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int tree_count = tree_list.size();
		System.out.println(tree_count);

		for (WebElement w : tree_list) {
			System.out.println(w.getText());
			CustomReporter.MessageLogger("Sorting the Order Basket by Order Set displays as: " + w.getText() + " successfully", status.Pass);
		}
		CommonLib.staticWait(6);
		web_ele.click();
		CommonLib.staticWait(2);
		builder.sendKeys(Keys.ARROW_DOWN).perform();
		builder.sendKeys(Keys.ENTER).perform();
		WebElement wb1 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list1 = wb1.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int tree_count1 = tree_list1.size();
		System.out.println(tree_count1);

		for (WebElement w1 : tree_list1) {
			System.out.println(w1.getText());
			CustomReporter.MessageLogger("Sorting the Order Basket by Order Group displays as: " + w1.getText() + " successfully", status.Pass);
		}

	}

	public void completeOrderBasket() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.changeimplicitwait(8);
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int tree_count = tree_list.size();
		System.out.println(tree_count);
		WebElement wb1 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='activeOrdersTree']"));
		List<WebElement> tree_list1 = wb1.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int tree_count1 = tree_list1.size();
		System.out.println(tree_count1);
		for (WebElement w1 : tree_list1) {
			System.out.println(w1.getText());
			CustomReporter.MessageLogger("Before completing  the Order Basket, Active Orders displaying as: " + w1.getText() + " successfully", status.Pass);
		}
		CommonLib.staticWait(4);
		CommonLib.clickButton(By.xpath("//span[@widgetId='completeOrders' and contains(@class,'dijitButton')]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='verbalOrdersReadbackDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='verbalOrdersReadbackDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		}
		CommonLib.staticWait(20);
		WebElement wb2 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list2 = wb2.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(4);
		int tree_count2 = tree_list2.size();
		System.out.println(tree_count2);
		if (tree_count2 != tree_count) {
			WebElement wb3 = CommonLib.GetDriver().findElement(By.xpath("//div[@id='activeOrdersTree']"));
			List<WebElement> tree_list3 = wb3.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
			CommonLib.staticWait(4);
			int tree_count3 = tree_list3.size();
			System.out.println(tree_count3);
			for (WebElement w3 : tree_list3) {
				System.out.println(w3.getText());
				CustomReporter.MessageLogger("After completing  the Order Basket, Active Orders displaying as: " + w3.getText() + " successfully", status.Pass);
			}

		}
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public boolean addOrderSetFromOrderPanel(String order_test) throws InterruptedException {
		CommonLib.changeimplicitwait(9);
		boolean result = false;
		// CommonLib.selectDojoListValue("orderingPhysicianList", "");
		checkOrderPhysicianValue("orderingPhysicianList");
		// CommonLib.selectDojoDropDownByKeyDownNumber("orderingPhysicianList",
		// 2);
		checkCoSignListBox();
		CommonLib.selectRequiredDojoListValue("orderPanelsList", Config.props.getProperty("order_panel"));
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		int to_process_count = wb.findElements(By.xpath(".//span[contains(@class,'errorIcon')]")).size();
		System.out.println("To be processed order count is " + to_process_count);
		CommonLib.staticWait(1);
		int basket_count_before = tree_list.size();
		System.out.println(basket_count_before);
		if (basket_count_before > 1) {
			for (WebElement web_ele : tree_list) {
				System.out.println(web_ele.getText());
				CustomReporter.MessageLogger("Before adding the Order Panel there are : " + basket_count_before + " Orders in the Order Basket, they are: " + web_ele.getText() + "", status.Information);
				CommonLib.staticWait(1);
			}
		}
		CommonLib.staticWait(3);
		CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + order_test + "') and contains(@href,'addItemToBasketWithOrderType')]"));
		CommonLib.staticWait(3);
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry-detail')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
		try {
			CommonLib.changeimplicitwait(2);
			for (int i = 1; (i < (9 + to_process_count)); i++) {
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
				CustomReporter.MessageLogger("After adding the Order Panel :" + order_test + " there are : " + basket_count_after + " Orders in the Order Basket, and one of the Order in that is: " + web_ele.getText() + "", status.Pass);
				result = true;
			}
		} else {
			CustomReporter.MessageLogger("Failed to add the Orders through the Order Panel :" + order_test + ", please try again!", status.Fail);
		}
		return result;

	}

	public void errorHandling() throws InterruptedException {

		if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog' or @id='reminderDialog']/div[2]/table/tbody/tr/td"), 2)) {
			String error_msg = CommonLib.getText(By.xpath("//div[@id='errorDialog'or @id='reminderDialog']/div[2]/table/tbody/tr/td"));
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog' or @id='reminderDialog']/div[2]/table/tbody/tr[2 or 4]/td/span/span/span/span[3]"));
			if (error_msg.contains("A HCPCS Level II modifier is required for this service")) {
				CommonLib.selectfrmDropdwn("hcpcs", 3, "", "");
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("A valid duration") || error_msg.contains("Frequency Code must be") || error_msg.contains("time")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 2);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("PRN")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("prnIndList", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("body site")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("bodySiteList", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("auto")) {
				CommonLib.setData(By.xpath("//input[@id='ap_AIRLINE']"), Config.props.getProperty("order_panel"));
				// CommonLib.selectDojoDropDownByKeyDownNumber("diagnosisList",
				// 2);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("Exam")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("reasonForExam", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("service date")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("serviceTime", 2);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("repeat")) {
				repeatOrderProcess();
			} else if (error_msg.contains("Antimicrobial")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("antimicrobialCodeList", 4);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("Priority")) {
				CommonLib.selectDojoDropDownByKeyDownNumber("priorityList", 3);
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("A valid infusion rate must be entered")) {
				CommonLib.GetDriver().findElement(By.xpath("//input[@id='infusionRate']")).sendKeys("10");
				// CommonLib.setData(By.xpath("//input[@id='infusionRate']"),
				// Config.props.getProperty("infusion_data"));
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			} else if (error_msg.contains("Solutions must have a Frequency Code")) {
				CommonLib.getElement(By.xpath("//div[@id='solutionsGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[2]")).click();
				CommonLib.clickButton(By.xpath("//span[@id='editSolution_label']"));
				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 2);
				CommonLib.clickButton(By.xpath("//input[@name='button.SubmitDrug']"));
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			}

		} else {
			if (CommonLib.isElementPresent(By.xpath("//span[@id='button.submit_label']"), 1)) {
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			}

		}
	}

	public void checkClinicalWarning() throws InterruptedException {

		if (CommonLib.isElementPresent(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')][last()]"), 0)) {
			WebElement iFrameClinicalWarning = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog'and contains(@src,'clinical-warnings')][last()]"));
			CommonLib.setFrameFromCurrent(iFrameClinicalWarning);
			CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
			CommonLib.clickButton(By.xpath(".//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
			CommonLib.staticWait(3);
			CommonLib.GetDriver().switchTo().parentFrame();
			CommonLib.setFrame(Config.props.getProperty("MainFrame"));
			if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"), 2)) {
				WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"));
				CommonLib.setFrameFromCurrent(iFrameOrderEntry);
				if (CommonLib.isElementPresent(By.xpath("//span[@id='button.submit_label']"), 1)) {
					CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
				}
			}

		} else if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 0)) {
			errorHandling();
		} else {
			if (CommonLib.isElementPresent(By.xpath("//span[@id='button.submit_label']"), 0)) {
				CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			}

		}

	}

	public void repeatOrderProcess() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='addRepeat_label' and contains(@class, 'dijitButtonText')]"));
		CommonLib.staticWait(2);
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-repeat.action')]"), 5)) {
			WebElement iFrameRepeatOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-repeat.action')]"));
			CommonLib.setFrameFromCurrent(iFrameRepeatOrderEntry);
			CommonLib.selectDojoDropDownByKeyDownNumber("sigList", 5);
			CommonLib.setData(By.xpath("//input[@id='duration' and contains(@class,'dijitInputInner')]"), "10");
			CommonLib.selectRequiredDojoListValue("durationTypeList", "Days");
			CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitRepeat_label' and contains(@class,'dijitButtonText')]"));
			CommonLib.GetDriver().switchTo().parentFrame();
		}
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
	}

	public void checkDuplicateOrders(String non_pharm) {

		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'treeDuplicate')]/span[3]/span/following-sibling::span[contains(text(),'" + non_pharm + "') ]"), 5)) {
			CustomReporter.MessageLogger("Duplicate Orders " + non_pharm + " has been placed to the Order Basket successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to place the Duplicate Orders to the Order Basket, please try again!", status.Fail);
		}

	}

	public void checkOrderPhysicianValue(String id) throws InterruptedException {

		if (CommonLib.getElement(By.id(id)).isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
			String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
			if (dojoValue.isEmpty()) {
				try {
					CommonLib.selectDojoDropDownByKeyDownNumber(id, 2);
				} catch (Exception e) {
				}

			}
		}
	}

	public void checkCoSignListBox() throws InterruptedException {
		if (CommonLib.getElement(By.id("coSigningPhysicianList")).isEnabled()) {
			CommonLib.selectDojoDropDownByKeyDownNumber("coSigningPhysicianList", 2);
			System.out.println("Cosign has beeen enabled and hence been set the value");
		}
	}

	public boolean addIVOrder(String order_type_iv, String order_test) throws InterruptedException {
		boolean flag = false;
		if (order_type_iv != "") {
			// CommonLib.clickButton(By.xpath("//form[@id='orderEntry']/table/tbody/tr[8]/td/table[@id='orderTypeList']/tbody/tr/td/div/span"));
			// WebElement wb =
			// CommonLib.getElement(By.xpath("//form[@id='orderEntry']/table/tbody/tr[8]/td/table[@id='orderTypeList']/tbody/tr/td/div/span"));
			// wb.sendKeys("");
			// wb.sendKeys(order_type_iv);
			// wb.sendKeys(Keys.RETURN);
			CommonLib.clickButton(By.xpath("//table[@id='orderTypeList']/tbody/tr/td[2]/input"));
			CommonLib.clickButton(By.xpath("//table[@id='orderTypeList_menu']/tbody/tr/td[text()='" + order_type_iv + "']"));
		}
		CommonLib.staticWait(3);
		CommonLib.selectRequiredDojoListValue("orderPanelsList", Config.props.getProperty("order_panel"));
		CommonLib.staticWait(3);
		int bef_add_count = getOrderBaskeTCount();
		CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + order_test + "') and contains(@href,'addItemToBasketWithOrderType')]"));
		CommonLib.staticWait(3);
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[contains(@src,'order-entry')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.changeimplicitwait(2);
		for (int i = 1; (i < 3); i++) {
			errorHandling();
			checkClinicalWarning();
		}
		int aft_add_count = getOrderBaskeTCount();
		if (aft_add_count > bef_add_count) {
			flag = true;
		}
		return flag;
	}

	public int getOrderBaskeTCount() {
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		List<WebElement> tree_list = wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		CommonLib.staticWait(1);
		int count = tree_list.size();
		return count;

	}

	public void checkAllergiesWarning() {
		CommonLib.staticWait(2);
		if (CommonLib.getElements(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr[2]/td/span//span[@id='dijit_form_Button_8_label']")).size() > 0) {
			CommonLib.staticWait(2);
			CommonLib.clickButton(By.xpath("//div[@id='warningDialog']/div[2]/table/tbody/tr[2]/td/span//span[@id='dijit_form_Button_8_label']"));
		}
	}

	public void checkReadBackOrders() {
		CommonLib.staticWait(2);
		try {
			CommonLib.clickButton(By.xpath("//div[@id='verbalOrdersReadbackDialog']/div[2]/table/tbody/tr[2]/td/span[1]/span/span/span[3]"));
		} catch (Exception e) {
		}
	}

}
