package order_entry;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class OrderEntryPlus_Medication extends OrderEntryPlus_OrderPanel {

	OrderEntryPlus_VerifyIcons obj = new OrderEntryPlus_VerifyIcons();

	public void medication(boolean homeMedPharmacy) throws Exception {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//table[@id='orderTypeList']/tbody/tr/td[2]/input"));
		CommonLib.clickButton(By.xpath("//table[@id='orderTypeList_menu']/tbody/tr/td[text()='Medication']"));
		CommonLib.selectRequiredDojoListValue("orderPriorityList", "Medication");
		CommonLib.clickButton(By.xpath("//span[@id='orderEntryTabs_tablist_orderMedicationTab']"));
		boolean result = addMedicationOrder();
		if (result) {
			clickOrder();
			if (homeMedPharmacy != true)
				deleteOrder();
			completeOrder();
		} else {
			CustomReporter.MessageLogger("Failed to add the Pharmacy Medication Order to the Order Entry Plus screen, to check the Order Entry Plus functionalities", status.Fail);
		}

	}

	public void searchfromgrid(String searchboxid, String searchbuttonid) {
		while (true) {

			try {
				Random r = new Random();
				char alpha = (char) (r.nextInt(26) + 'a');
				CommonLib.setData(By.id(searchboxid), String.valueOf(alpha));
				CommonLib.clickButton(By.id(searchbuttonid));
				HashTableRepository.setHash("drugSelected", CommonLib.getElement(By.xpath("//div[@id='medicationGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]")).getText());
				int rowcount;
				rowcount = CommonLib.getElements(By.xpath("//div[@id='dojox_grid__View_7']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]")).size();
				if (rowcount >= 1) {
					CommonLib.clickButton(By.xpath("//div[@id='dojox_grid__View_7']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[1]"));
					if (CommonLib.GetDriver().findElements(By.xpath("//div[@class='dijitToasterContainer dijitToasterError']/div[contains(text(),'height and weight must be')]")).size() > 0) {
					} else
						break;
				}

			} catch (Exception e) {

				CommonLib.resetImplicitWait();
			}
		}
	}

	public void processOrders() throws InterruptedException {

		List<WebElement> orderList = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon errorIcon']"));
		if (orderList.size() > 0) {
			for (WebElement order : orderList) {
				orderList.get(orderList.size() - 1).click();
				CommonLib.clickButton(By.xpath("//span[@id='processBasket']"));
				editOrders();
			}
			if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon errorIcon']")).size() > 0) {
				CustomReporter.MessageLogger("Failed, Order Entry Plus Process functionality is not working properly, please try again!", status.Fail);
			} else {
				CustomReporter.MessageLogger("Order Entry Plus Process functionality is working properly", status.Pass);
			}
		} else
			CustomReporter.MessageLogger("There are no orders to be processed", status.Pass);
	}

	public boolean editOrders() throws InterruptedException {

		// clickOrder();
		// CommonLib.clickButton(By.xpath("//span[@id='editBasketItem']"));
		boolean result = false;
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);

		if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='reminderDialog']")).size() > 0) {
			CommonLib.clickButton(By.xpath("//span[@id='dijit_form_Button_1_label'] and contains(text(), 'Ok')]"));
			CommonLib.clickButton(By.xpath("//span[@id='button.cancel_label']/div"));
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

		} else {
			if (CommonLib.GetDriver().findElements(By.xpath("//input[@id='sigCodeList']")).size() > 0) {
				result = true;
				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 3);
				HashTableRepository.setHash("selectedValueAfterEdit", getDropDownValue("sigCodeList"));

				String durationCheck = CommonLib.getElement(By.xpath("//input[@id='duration']")).getText();
				boolean pnrElement = CommonLib.getElement(By.xpath("//input[@id='prnIndList']")).isEnabled();
				if (pnrElement == true) {
					CommonLib.selectDojoListValue("prnIndList", "");
				}
				if (durationCheck == "") {
					CommonLib.setData(By.xpath("//input[@id='duration']"), "1");
					CommonLib.clickButton(By.xpath("//input[@id='durationType_D']"));
				}
			}
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			CommonLib.staticWait(25);
			List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
			if (clinicalWarningsList.size() > 0)
				clinicalWarnings();
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		}
		return result;
	}

	public void clinicalWarnings() throws InterruptedException {

		List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
		if (clinicalWarningsList.size() > 0) {
			WebElement clinicalWarningsElement = clinicalWarningsList.get(clinicalWarningsList.size() - 1);
			CommonLib.setFrameFromCurrent(clinicalWarningsElement);
			if (CommonLib.GetDriver().findElements(By.xpath("//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]")).size() > 0) {
				CustomReporter.MessageLogger("Clinical Warning button is working properly as it is displaying the CLinical Warning window successfully", status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed, Clinical Warning button is not working properly as it is not displaying the CLinical Warning window, please try again!", status.Fail);
			}

			CommonLib.selectRequiredDojoListValue("overrideReason", Config.props.getProperty("override_reason"));
			CommonLib.clickButton(By.xpath("//span[contains(text(),'Override and Continue') and contains(@class,'dijitButtonText')]"));
		}
	}

	public String getDropDownValue(String id) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		String dojoValue = (String) js.executeScript("return dijit.byId('" + id + "').get('displayedValue')");
		CustomReporter.MessageLogger("For " + id + " dropdown, selected value is : " + dojoValue, CustomReporter.status.Information);
		return dojoValue;

	}

	public void validateEdit() throws InterruptedException {

		String selectedValueAfterEdit = HashTableRepository.getHash("selectedValueAfterEdit");
		clickOrder();
		CommonLib.clickButton(By.xpath("//span[@id='editBasketItem']"));
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		String editValueCheck = getDropDownValue("sigCodeList");
		if (selectedValueAfterEdit.equals(editValueCheck))
			CustomReporter.MessageLogger("Order Entry Plus Edit functionality is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Failed, Order Entry Plus Edit functionality is not working properly, please try again!", status.Fail);
		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
		CommonLib.staticWait(25);
		List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
		if (clinicalWarningsList.size() > 0)
			clinicalWarnings();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public boolean addOrder() throws InterruptedException {

		boolean flag = false;
		boolean result = false;
		CommonLib.setData(By.xpath("//input[@id='medicationSearchValue']"), "*");
		CommonLib.clickButton(By.xpath("//span[@id='searchMedication']"));
		CommonLib.clickButton(By.xpath("//span[@class='dijitReset dijitInline dijitIcon resultSet_last']"));
		List<WebElement> orderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		try {
			do {
				flag = false;
				searchfromgrid("medicationSearchValue", "searchMedication");
				WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
				CommonLib.setFrameFromCurrent(iFrameOrderEntry);

				if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='reminderDialog']")).size() > 0) {
					CommonLib.clickButton(By.xpath("//span[@id='dijit_form_Button_1_label'] and contains(text(), 'Ok')]"));
					CommonLib.clickButton(By.xpath("//span[@id='button.cancel_label']/div"));
					CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
					flag = true;
				}
			} while (flag != false);

			if (CommonLib.GetDriver().findElements(By.xpath("//input[@id='sigCodeList']")).size() > 0) {
				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 3);
				HashTableRepository.setHash("drugSelectedForDuplicateOrders", CommonLib.getElement(By.xpath("//input[@id='tradeName']")).getAttribute("value"));

				String durationCheck = CommonLib.getElement(By.xpath("//input[@id='duration']")).getText();

				boolean pnrElement = CommonLib.getElement(By.xpath("//input[@id='prnIndList']")).isEnabled();
				if (pnrElement == true) {
					CommonLib.selectDojoListValue("prnIndList", "");
				}
				if (durationCheck.equals("")) {
					CommonLib.setData(By.xpath("//input[@id='duration']"), "1");
					CommonLib.clickButton(By.xpath("//input[@id='durationType_D']"));
				}
			}
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			CommonLib.staticWait(25);
			List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
			if (clinicalWarningsList.size() > 0)
				clinicalWarnings();
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

		} catch (Exception e) {

			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		}

		int orderListAfterAdd = clickOrder();

		if (orderListAfterAdd > orderListBeforeAdd.size()) {
			result = true;
			CustomReporter.MessageLogger("Order Entry Plus Add Functionality is working properly as new Order has been added", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality is working properly as new Order has not been added, please try again!", status.Fail);
		}
		return result;
	}

	public int clickOrder() {

		List<WebElement> orderList = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		WebElement element = orderList.get(orderList.size() - 1);
		element.click();
		return orderList.size();
	}

	public void validateClinicalWarnings() throws InterruptedException {

		clickOrder();
		CommonLib.clickButton(By.xpath("//span[@id='clinicalWarnings']"));
		clinicalWarnings();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void deleteOrder() throws InterruptedException {

		int orderSizeBeforeDelete = clickOrder();
		CommonLib.clickButton(By.xpath("//span[@id='deleteBasketItem']"));
		List<WebElement> orderSizeAfterDelete = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		if (orderSizeAfterDelete.size() < orderSizeBeforeDelete) {
			CustomReporter.MessageLogger("Order Entry Plus Delete functionality is working properly", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Order Entry Plus Delete functionality is not working properly, please try again!", status.Fail);
		}
	}

	public void completeOrder() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='completeOrders']"));
		checkReadBackOrders();
		CommonLib.staticWait(25);
		List<WebElement> orderListAfterCompleteOrders = CommonLib
				.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		if (orderListAfterCompleteOrders.size() == 0) {
			CustomReporter.MessageLogger("Order Entry Plus Complete functionality is working properly", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Order Entry Plus Complete functionality is not working properly, please try again!", status.Fail);
		}
	}

	public String createDuplicateOrders(String non_pharm) throws InterruptedException {

		boolean flag = false;
		String duplicateOrder = HashTableRepository.getHash("drugSelectedForDuplicateOrders");
		CommonLib.setData(By.xpath("//input[@id='medicationSearchValue']"), duplicateOrder);
		CommonLib.clickButton(By.xpath("//span[@id='searchMedication']"));
		CommonLib.clickButton(By.xpath("//div[@id='medicationGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]"));
		try {
			do {
				flag = false;
				WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
				CommonLib.setFrameFromCurrent(iFrameOrderEntry);

				if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='reminderDialog']")).size() > 0) {
					CommonLib.clickButton(By.xpath("//span[@id='dijit_form_Button_1_label'] and contains(text(), 'Ok')]"));
					CommonLib.clickButton(By.xpath("//span[@id='button.cancel_label']/div"));
					CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
					flag = true;
				}
			} while (flag != false);

			if (CommonLib.GetDriver().findElements(By.xpath("//input[@id='sigCodeList']")).size() > 0) {

				CommonLib.selectDojoDropDownByKeyDownNumber("sigCodeList", 3);

				String durationCheck = CommonLib.getElement(By.xpath("//input[@id='duration']")).getText();

				boolean pnrElement = CommonLib.getElement(By.xpath("//input[@id='prnIndList']")).isEnabled();
				if (pnrElement == true) {
					CommonLib.selectDojoListValue("prnIndList", "");
				}
				if (durationCheck == "") {
					CommonLib.setData(By.xpath("//input[@id='duration']"), "1");
					CommonLib.clickButton(By.xpath("//input[@id='durationType_D']"));
				}
			}
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			CommonLib.staticWait(25);
			List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
			if (clinicalWarningsList.size() > 0)
				clinicalWarnings();
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

		} catch (Exception e) {

			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		}
		return duplicateOrder;
	}

	public void validateDuplicateOrders(String duplicateOrder) throws InterruptedException {

		if (CommonLib.isElementPresent(By.xpath(".//div[contains(@class,'treeDuplicate')]/span[3]/span/following-sibling::span[contains(text(),'" + duplicateOrder + "') ]"), 3)) {
			CustomReporter.MessageLogger("Duplicate Order " + duplicateOrder + " has been placed to the Order Basket successfully", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to place the Duplicate Orders to the Order Basket, please try again!", status.Fail);
		}

	}

	public boolean addMedicationOrder() throws InterruptedException {

		boolean result = false;
		CommonLib.setData(By.xpath("//input[@id='medicationSearchValue']"), "*");
		CommonLib.clickButton(By.xpath("//span[@id='searchMedication']"));
		CommonLib.clickButton(By.xpath("//span[@class='dijitReset dijitInline dijitIcon resultSet_last']"));
		List<WebElement> orderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));

		searchfromgrid("medicationSearchValue", "searchMedication");
		CommonLib.staticWait(2);
		WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderEntry);
		CommonLib.staticWait(4);
		if (CommonLib.isElementPresent(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"), 3)) {
			CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
		}
		for (int i = 1; i < 4; i++) {
			try {
				CommonLib.changeimplicitwait(1);
				errorHandling();
				checkClinicalWarning();

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

		int orderListAfterAdd = clickOrder();

		if (orderListAfterAdd > orderListBeforeAdd.size()) {
			result = true;
			CustomReporter.MessageLogger("Order Entry Plus Add Functionality is working properly as new Order has been added", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality is working properly as new Order has not been added, please try again!", status.Fail);
		}
		return result;
	}

	public void cleanUpOrders() {
		MenuNavigation.menuNav("Order Entry Plus");
		obj.checkAllergiesWarning();
		WebElement wb = CommonLib.GetDriver().findElement(By.xpath("//div[@id='orderBasketTree']"));
		// List<WebElement> tree_list =
		// wb.findElements(By.xpath(".//span[@class='dijitTreeLabel']"));
		List<WebElement> tree_list1 = wb.findElements(By.xpath(".//span[contains(@class,'errorIcon') or contains(@class, 'acceptIcon') or contains(@class, 'exclamationIcon') or contains(@class, 'beakerIcon')] "));
		int totalOrdersinit = tree_list1.size();
		if (totalOrdersinit > 0) {
			for (WebElement web_ele : tree_list1) {
				CommonLib.staticWait(1);
				CommonLib.clickButton(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']//div/div[2]/div/div[2]/div//span[contains(@class, 'dijitTreeLabel')]"));
				CommonLib.clickButton(By.xpath("//span[@id='deleteBasketItem']"));
			}
			try {
				int totalOrdersfinal = wb.findElements(By.xpath(".//span[contains(@class,'errorIcon') or contains(@class, 'acceptIcon') or contains(@class, 'exclamationIcon') or contains(@class, 'beakerIcon')] ")).size();
				if (totalOrdersfinal > 0)
					CustomReporter.MessageLogger("Failed, Orders have not been deleted in Order Entry Plus, please try again!", status.Fail);
			} catch (Exception e) {
				CustomReporter.MessageLogger("Orders have been deleted in Order Entry Plus", status.Pass);
			}
		} else {
			CustomReporter.MessageLogger("There are no orders in Order Entry Plus", status.Pass);
		}
	}
}
