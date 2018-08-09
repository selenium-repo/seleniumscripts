package OrderManagerPlus.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.Library;
import order_entry.OrderEntryPlus_OrderPanel;

public class OrderManagerPlusPage extends OrderEntryPlus_OrderPanel {

	By all_tab = By.id("orderWorklistTabs_tablist_allOrdersTab");
	By all_radios = By.xpath("//input[(@name='allDisplayType') and (@role='radio')]");
	By all_table_row_count = By.xpath("//div[@id='allOrdersWorklistGrid']/div[2]/div/div/div/div/div[@class='dojoxGridRow']");
	By nonpharmacy_tab = By.id("orderWorklistTabs_tablist_nonRxOrdersTab");
	By nonpharmacy_table_row_count = By.xpath("//div[@id='OrdersWorklistGrid']/div[2]/div/div/div/div/div[@class='dojoxGridRow']");
	By med_tab = By.id("orderWorklistTabs_tablist_medOrdersTab");
	By iv_tab = By.id("orderWorklistTabs_tablist_ivOrdersTab");
	By select_medfilter = By.id("medSearchTypeNumberList");

	public void clickPrintAll() {
		CommonLib.clickButton(By.id("printAll_AllOrders_label"));
	}

	public void clickPrint() {
		CommonLib.clickButton(By.id("print_AllOrders_label"));
	}

	public void clickFind() {
		CommonLib.clickButton(By.id("findOrders_label"));
	}

	public void clickRefresh() {
		CommonLib.clickButton(By.id("refreshPage_label"));
	}

	public void clickChange() {
		CommonLib.clickButton(By.id("editOrder_label"));
	}

	public void clickReceive() {
		CommonLib.clickButton(By.id("receiveOrder_label"));
	}

	public void clickNote() {
		CommonLib.clickButton(By.id("noteOrder"));
	}

	public void clickVerify() {
		CommonLib.clickButton(By.id("verifyOrder_label"));
	}

	public void clickCollect() {
		CommonLib.clickButton(By.id("collectSpecimen_label"));
	}

	public void clickAck() {
		CommonLib.clickButton(By.id("acknowledgeOrder_label"));
	}

	public void clickAckUpd() {
		CommonLib.clickButton(By.id("acknowledgeUpdateOrder_label"));
	}

	public void clickHold() {
		CommonLib.clickButton(By.id("holdOrder_label"));
	}

	public void clickRelease() {
		CommonLib.clickButton(By.id("releaseOrder_label"));
	}

	public void clickDiscontinue() {
		CommonLib.clickButton(By.id("discontinueNonRxOrder_label"));
	}

	public void clickCancel() {
		CommonLib.clickButton(By.id("cancelOrder_label"));
	}

	public void clickUnCancel() {
		CommonLib.clickButton(By.id("uncancelOrder_AllOrders_label"));
	}

	public void clickReprint() {
		CommonLib.clickButton(By.id("reprintRequisition_label"));
	}

	public void clickResults() {
		CommonLib.clickButton(By.id("showResults_label"));
	}

	public void clickImportResult() {
		CommonLib.clickButton(By.id("importResult_label"));
	}

	public void clickExamDetail() {
		CommonLib.clickButton(By.id("examDetail"));
	}

	public void clickCarePlans() {
		CommonLib.clickButton(By.id("carePlanItem_label"));
	}

	public void clickNonPharmacyTab() {
		CommonLib.clickButton(nonpharmacy_tab);
	}

	public void clickAllTab() {
		CommonLib.clickButton(all_tab);
	}

	public void selectradioLast24() {
		CommonLib.selectradio(By.id("displayType_24Hours"));
	}

	public void selectradioCurrentVisit() {
		CommonLib.selectradio(By.id("displayType_Visit"));
	}

	public void selectradioEntireHistory() {
		CommonLib.selectradio(By.id("displayType_All"));
	}

	public void selectradioEnterpriseHistory() {
		CommonLib.selectradio(By.id("displayType_EntAll"));
	}

	public void selectradioActive() {
		CommonLib.selectradio(By.id("medOrderStatus_Active"));
	}

	public void selectradioInactive() {
		CommonLib.selectradio(By.id("medOrderStatus_Inactive"));
	}

	public void selectradioComplete() {
		CommonLib.selectradio(By.id("medOrderStatus_Complete"));
	}

	public void selectOrderGroups(String data_to_set) {
		CommonLib.selectRequiredDojoListValue("primaryGroupList", data_to_set);
	}

	public void selectFilter(int data_to_set) throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("searchTypeNumberList", data_to_set);
	}

	public Iterator<WebElement> getRadioButtons(String str) {

		List<WebElement> list = CommonLib.getElements(By.xpath("//input[(@name='" + str + "') and (@role='radio')]"));
		int count = list.size();
		System.out.println(count);
		Iterator<WebElement> itr = list.iterator();
		return itr;

	}

	public int getTableRowCount(String var) {
		List<WebElement> list = CommonLib.getElements(By.xpath("//div[@id='" + var + "']/div[2]/div/div/div/div/div[@class='dojoxGridRow']"));
		return list.size();
	}

	public void selectListOrderGroup(int val) throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("primaryGroupList", val);

	}

	public void clickMedTab() {
		CommonLib.clickButton(med_tab);
	}

	public void clickIVsTab() {
		CommonLib.clickButton(iv_tab);

	}

	public void selectMedFilter() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("medSearchTypeNumberList", 2);
	}

	public void selectIVFilter() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("ivSearchTypeNumberList", 2);
	}

	public void selectNonPharmFilter() throws InterruptedException {
		CommonLib.selectDojoDropDownByKeyDownNumber("searchTypeNumberList", 2);
	}

	public String getFirstRowDetailValue() {
		String value = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/a")).getText();
		return value;
	}

	public String getFirstRowDetailDateValue() {
		String value = CommonLib.getElement(By.xpath("//div[@id='allOrdersWorklistGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td")).getText();
		return value;
	}

	public String getFirstRowValueNon() {
		String value = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/a")).getText();
		return value;
	}

	public String getFirstRowDetailDateValueNon() {
		String value = CommonLib.getElement(By.xpath("//div[@id='orderWorklistGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td")).getText();
		return value;

	}

	public String getFirstRowValueMed() {
		String value = CommonLib.getElement(By.xpath("//div[@id='medOrderWorklistGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td/a")).getText();
		return value;
	}

	public String getFirstRowDetailDateValueMed() {
		String value = CommonLib.getElement(By.xpath("//div[@id='medOrderWorklistGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td")).getText();
		return value;

	}

	public String getFirstRowValueIV() {

		String value = CommonLib.getElement(By.xpath("//div[@id='ivOrderWorklistGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td/a")).getText();
		return value;
	}

	public String getFirstRowDetailDateValueIV() {
		String value = CommonLib.getElement(By.xpath("//div[@id='ivOrderWorklistGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td")).getText();
		return value;
	}

	public void clickNewOrdersTab() {
		CommonLib.clickButton(By.xpath("//span[@id='orderWorklistTabs_tablist_newOrdersTab']"));

	}

	public void clickNewOrdersNewNonPharmacyTab() {
		CommonLib.clickButton(By.xpath("//span[@id='newOrderTabs_tablist_newNonRxOrdersTab']"));
	}

	public void clickNewOrdersNewMedsTab() {
		CommonLib.clickButton(By.xpath("//span[@id='newOrderTabs_tablist_newMedOrdersTab']"));
	}

	public void clickNewOrdersNewIVTab() {

		CommonLib.clickButton(By.xpath("//span[@id='newOrderTabs_tablist_newIvOrdersTab']"));
	}

	public String getNewOrdersFirstRowNonPharmValue() {
		String value = CommonLib.getElement(By.xpath("//div[@id='newNonRxOrdersTab']/div/div/div/div[2]/div[2]/div/div/div/div/table/tbody/tr/td/table/tbody/tr/td/a")).getText();
		return value;
	}

	public String getNewOrdersFirstRowMedValue() {
		String value = CommonLib.getElement(By.xpath("//div[@widgetId='newMedOrdersTab']/div/div/div/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td/a[2]")).getText();
		return value;
	}

	public String getNewOrdersFirstRowIVValue() {
		String value = CommonLib.getElement(By.xpath("//div[@widgetId='newIvOrdersTab']/div/div/div/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/table/tbody/tr/td/a[2]")).getText();
		return value;
	}

	public void clickOrderEntryMedTab() {
		CommonLib.clickButton(By.xpath("//span[@id='orderEntryTabs_tablist_orderMedicationTab']"));
	}

	public void selectCheckBox() {

		boolean isPresent = false;
		while (isPresent != true) {
			if (CommonLib
					.getElements(By.xpath(
							"//div[@class='dijitTabContainerTopChildWrapper dijitVisible']//div//div//div[@id='orderWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td/div[@id='orderWorklistGrid_rowSelector_0']"))
					.size() > 0) {
				CommonLib.checkBoxSelect(By.id("orderWorklistGrid_rowSelector_0"), 4);
				isPresent = true;
				break;
			} else if (CommonLib.getElements(By.xpath(
					"//div[@class='dijitTabContainerTopChildWrapper dijitVisible']//div//div//div[@id='allOrdersWorklistGridPane']//div//div[2]//div//div//div//div//div//table/tbody/tr/td/div[@id='allOrdersWorklistGrid_rowSelector_0']"))
					.size() > 0) {
				CommonLib.setBoolFields(true, By.id("allOrdersWorklistGrid_rowSelector_0"));
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

	}

	public void clickSubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label']"));
	}

	public void findOrder(boolean checkboxFlag) {
		clickNonPharmacyTab();
		clickFind();
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'order-worklist-filter')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		Library.checkCheckBox(By.xpath("//input[contains(@class,'dijitCheckBoxInput') and (@value='" + Config.props.getProperty("findOrderCheckbox") + "')]"), checkboxFlag, Config.props.getProperty("findOrderCheckbox"));
		clickSubmit();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void holdOrder() {
		clickHold();
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'hold-order')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		try {
			CommonLib.selectDojoListValue("holdReasonsList", "");
			try {
				CommonLib.selectDojoListValue("heldByPhysicianList", "");
			} catch (Exception e) {
			}
			try {
				CommonLib.selectDojoListValue("coSigningPhysicianList", "");
			} catch (Exception e) {
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickSubmit();
		try {
			CommonLib.setImplicitWait(5);
			clickSubmit();
			clickSubmit();
		} catch (Exception e) {
		}
		CommonLib.resetImplicitWait();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void collectOrder() {
		clickCollect();
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'collect-specimen')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		clickSubmit();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void ackUpdOrder() {
		clickAckUpd();
		CommonLib.staticWait(3);
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'acknowledge-update-order')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		clickSubmit();
		CommonLib.staticWait(2);
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void cancelOrder() {
		clickCancel();
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'cancel-order')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		try {
			CommonLib.selectDojoListValue("cancelReasonsList", "");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			CommonLib.selectDojoListValue("coSigningPhysicianList", "");
		} catch (Exception e) {
		}
		clickSubmit();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void importResult() throws InterruptedException {
		clickImportResult();
		WebElement iFrameOrderManager = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'result-import')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameOrderManager);
		CommonLib.GetDriver().findElement(By.id("upload")).sendKeys(System.getProperty("user.dir") + Config.props.getProperty("importFilePath"));
		CommonLib.selectDojoDropDownByKeyDownNumber("documentTypeList", 3);
		try {
			CommonLib.selectDojoDropDownByKeyDownNumber("dictatingPhysicianList", 3);
		} catch (Exception e) {
		}
		clickSubmit();
		CommonLib.clickButton(By.xpath("//span[@id='button.close_label']"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void change() {
		CommonLib.staticWait(3);
		WebElement iFrameChange = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'order-entry-detail.action?')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameChange);
		try {
			CommonLib.selectDojoListValue("coSigningPhysicianList", "");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonLib.setData(By.id("comment"), "data");
		CommonLib.clickButton(By.id("button.submit_label"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));

	}

	public void release() {
		CommonLib.staticWait(3);
		WebElement iFrameRelease = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'release-order.action?action=process')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameRelease);
		try {
			CommonLib.selectDojoListValue("releasedByPhysicianList", "");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			CommonLib.selectDojoListValue("coSigningPhysicianList", "");
		} catch (Exception e) {
		}
		CommonLib.setImplicitWait(5);
		List<WebElement> a = CommonLib.getElements(By.id("button.submit_label"));
		System.out.println("******(*&(*&(*&(*" + a.size());
		a.get(a.size() - 1).click();
		// CommonLib.clickButton(By.id("button.submit_label"));
		// CommonLib.clickButton(By.id("button.submit_label"));

		CommonLib.resetImplicitWait();
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void discontinue() throws InterruptedException {
		clickDiscontinue();
		WebElement iFrameDiscontinue = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'discontinue-order.action?action')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameDiscontinue);
		CommonLib.selectDojoListValue("discontinuedByPhysicianList", "");
		try {
			CommonLib.selectDojoListValue("coSigningPhysicianList", "");
		} catch (Exception e) {
		}

		CommonLib.clickButton(By.id("button.submit_label"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void reprint() throws InterruptedException {
		WebElement iFrameReprint = CommonLib.getElement(By.xpath("//iframe[contains(@src, 'reprint-requisition.action?action=process')][last()]"));
		CommonLib.setFrameFromCurrent(iFrameReprint);
		CommonLib.selectDojoListValue("destinationList", "");
		CommonLib.clickButton(By.id("button.submit_label"));
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

}
