package order_entry;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class OrderEntryPlus_TherapeuticClass extends OrderEntryPlus_Medication {

	public void therapeuticClass() throws Exception {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		CommonLib.selectDojoListValue("orderPriorityList", "");
		CommonLib.clickButton(By.xpath("//span[@id='orderEntryTabs_tablist_orderTherapeuticClassTab']"));
		// processOrders();
		addOrderTherapeuticClassTab();
	}

	public void addOrderTherapeuticClass() {

		boolean flag = false;
		List<WebElement> orderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		CommonLib.setData(By.xpath("//input[@id='therapeuticClassSearchValue']"), "*");
		CommonLib.clickButton(By.xpath("//span[@id='searchTherapeuticClass']"));
		try {
			do {
				searchfromgridOfTherapeuticClass("therapeuticClassSearchValue", "searchTherapeuticClass");
				// String text =
				// CommonLib.getElement(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td")).getText();
				if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td")).size() == 0)
					flag = false;
				else {
					CommonLib.clickButton(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
					flag = true;
					WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
					CommonLib.setFrameFromCurrent(iFrameOrderEntry);
					if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='reminderDialog']")).size() > 0) {
						CommonLib.clickButton(By.xpath("//span[@id='dijit_form_Button_1_label'] and contains(text(), 'Ok')]"));
						CommonLib.clickButton(By.xpath("//span[@id='button.cancel_label']/div"));
						CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
						flag = false;
					}
				}
			} while (flag != true);

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
			CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
			List<WebElement> clinicalWarningsList = CommonLib.getElements(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'clinical-warnings')]"));
			if (clinicalWarningsList.size() > 0)
				clinicalWarnings();
			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

		} catch (Exception e) {

			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality in Therapeutic Class Tab is not working properly as new Order has not been added, please try again!", status.Fail);
		}

		int orderListAfterAdd = clickOrder();

		if (orderListAfterAdd > orderListBeforeAdd.size()) {
			CustomReporter.MessageLogger("Order Entry Plus Add Functionality in Therapeutic Class Tab is working properly as new Order has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality in Therapeutic Class Tab is not working properly as new Order has not been added, please try again!", status.Fail);
	}

	public void searchfromgridOfTherapeuticClass(String searchboxid, String searchbuttonid) {

		try {
			Random r = new Random();
			char alpha = (char) (r.nextInt(26) + 'a');
			CommonLib.setData(By.id(searchboxid), String.valueOf(alpha));
			CommonLib.clickButton(By.id(searchbuttonid));
			HashTableRepository.setHash("drugSelectedTherapeuticClass", CommonLib.getElement(By.xpath("//div[@id='therapeuticClassGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]")).getText());
			CommonLib.clickButton(By.xpath("//div[@id='therapeuticClassGrid']//div[2]//div[@class='dojoxGridContent']/div/div[1]/table/tbody/tr/td[1]"));

		} catch (Exception e) {

			CommonLib.resetImplicitWait();
		}
	}

	public void addOrderTherapeuticClassTab() {

		boolean flag = false;
		CommonLib.staticWait(5);
		List<WebElement> orderListBeforeAdd = CommonLib.getElements(By.xpath("//div[@id='orderBasketTree']/div[@class='dijitTreeContainer']/div/div[2]/div/div[2]/div//span[@class='dijitInline dijitIcon dijitTreeIcon acceptIcon']"));
		CommonLib.setData(By.xpath("//input[@id='therapeuticClassSearchValue']"), "*");
		CommonLib.clickButton(By.xpath("//span[@id='searchTherapeuticClass']"));
		try {
			do {
				searchfromgridOfTherapeuticClass("therapeuticClassSearchValue", "searchTherapeuticClass");
				// String text =
				// CommonLib.getElement(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td")).getText();
				if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td")).size() == 0)
					flag = false;
				else {
					CommonLib.clickButton(By.xpath("//div[@id='dojox_grid__View_19']/div[@class='dojoxGridScrollbox']/div[@class='dojoxGridContent']/div[1]/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"));
					flag = true;
					WebElement iFrameOrderEntry = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-entry-detail')][last()]"));
					CommonLib.setFrameFromCurrent(iFrameOrderEntry);
					CommonLib.staticWait(4);
					if (CommonLib.isElementPresent(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"), 3)) {
						CommonLib.clickButton(By.xpath(".//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
					}
					for (int i = 1; i < 5; i++) {
						try {
							CommonLib.changeimplicitwait(1);
							errorHandling();
							checkClinicalWarning();

						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
					if (CommonLib.GetDriver().findElements(By.xpath("//div[@id='reminderDialog']")).size() > 0) {
						CommonLib.clickButton(By.xpath("//span[@id='dijit_form_Button_1_label'] and contains(text(), 'Ok')]"));
						CommonLib.clickButton(By.xpath("//span[@id='button.cancel_label']/div"));
						CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
						flag = false;
					}
				}
			} while (flag != true);

		} catch (Exception e) {

			CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality in Therapeutic Class Tab is not working properly as new Order has not been added, please try again!", status.Fail);
		}
		CommonLib.staticWait(4);
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		int orderListAfterAdd = clickOrder();

		if (orderListAfterAdd > orderListBeforeAdd.size()) {
			CustomReporter.MessageLogger("Order Entry Plus Add Functionality in Therapeutic Class Tab is working properly as new Order has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("Failed, Order Entry Plus Add Functionality in Therapeutic Class Tab is not working properly as new Order has not been added, please try again!", status.Fail);
	}

}
