package pharmacy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class createOrderPage {

	public void setMainFrame() {
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void selectOrder(String order) {
		int flag = 4;
		while (flag >= 0) {
			CommonLib.clickButton(By.xpath("//td[@class='tableDetail']//input[@name='orderMethod' and @value='" + order + "']"));
			flag--;
			// WebElement we = CommonLib.getElement(
			// By.xpath("//td[@class='tableDetail']//input[@name='orderMethod'
			// and @value='"
			// + order + "']"));
			// try {
			// we.getAttribute("checked");
			// flag = false;
			// } catch (Exception e) {
			//
			// }
		}
	}

	public void drugSelectionMed(String a) {

		clickButtonTwice("//*[@id='table']/tbody/tr[4]/td[2]/a/img");
		// setDrugFrame();
		CommonLib.clickButton(By.xpath("//*[@id='dojox_grid__View_1']/div/div/div/div[" + a + "]/table/tbody/tr/td[1]"));
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void drugSelection(String a) {
		setDrugFrame();
		CommonLib.clickButton(By.xpath("//*[@id='dojox_grid__View_1']/div/div/div/div[" + a + "]/table/tbody/tr/td[1]"));
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void clickSolutionIv() {
		clickButtonTwice("//input[@name='drugSol']//following-sibling::a/img");
	}

	public void clickAdditiveIv() {
		clickButtonTwice("//input[@name='drugAdd']//following-sibling::a/img");
	}

	public void clickSolutionMed() {
		clickButtonTwice("//input[@id='tradeName']//following-sibling::a/img");
	}

	public void RandomSelection(String select) {
		// if (select.equals("Solution"))
		// clickButtonTwice("//input[@name='drugSol']//following-sibling::a/img");
		// else
		// clickButtonTwice("//input[@name='drugAdd']//following-sibling::a/img");
		// setDrugFrame();
		// CommonLib.setData(By.xpath("//input[@id='searchFor']"), "*");
		CommonLib.clickButton(By.xpath("//div[text()='Search']"));
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='displayDataGrid']/div[2]//div[@class='dojoxGridContent']/div//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[1]"));
		int random = CommonLib.generateRandom(0, li.size() - 1);
		String value = li.get(random).getText();
		li.get(random).click();

		HashTableRepository.setHash(select, value);
		setMainFrame();
		try {
			override();
		} catch (Exception e) {

		}

		setMainFrame();
	}

	public void setFrequencySolution() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='sigSol']"));
		CommonLib.clickButton(By.xpath("//select[@id='sigSol']/..//following-sibling::td/input"));
	}

	public void setFrequencyAdditive() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='sigAdd']"));
		CommonLib.clickButton(By.xpath("//select[@id='sigAdd']/..//following-sibling::td/input"));
	}

	public void clickUpdate() {
		CommonLib.clickButton(By.xpath("//input[@type='button' and @value='Update']"));
	}

	public void setinfusionRate() {
		CommonLib.setData(By.xpath("//input[@id='infusionRate']"), CommonLib.RandomText(2, 2));
		CommonLib.clickButton(By.xpath("//input[@id='contInfusionNo']"));
		CommonLib.clickButton(By.xpath("//input[@id='contInfusion']"));

		// CommonLib.getElement(By.xpath("//input[@id='infusionRate']")).sendKeys(Keys.TAB);
		// CommonLib.clickButton(By.xpath("//input[@id='stopDate']"));

	}

	public void clickSendOrders() {
		CommonLib.clickButton(By.xpath("//div[text()='Send Orders']"));
	}

	public void antiMicrobialIndication() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='antiMicrIndication']"));
	}

	public void cosigningPhysician() {
		List<WebElement> we = CommonLib.getElements(By.xpath("//select[@id='coSigningPhysicianList']"));
		List<WebElement> li = CommonLib.getElements(By.xpath("//select[@id='coSigningPhysicianList']/option"));
		if (li.size() == 2) {
			if (li.get(1).getAttribute("value").isEmpty()) {
				CommonLib.clickButton(By.xpath("//div[@id='coSignDocListImage']/a/img"));
				coPhysicianFrame();
				CommonLib.setData(By.xpath("//input[@id='searchFor']"), "*");
				CommonLib.clickButton(By.xpath("//table[@id='physicianList']/tbody/tr/td[@class='rowDetail']/a[2]"));
			}
		}
		if (!we.isEmpty()) {
			CommonLib.setRandomDropDown(By.xpath("//select[@id='coSigningPhysicianList']"));
		}
	}

	public void specialInstruction() {
		CommonLib.setData(By.xpath("//div[@id='labTestComments']//following-sibling::table/tbody/tr/td[text()='Special Instructions:']//following-sibling::td/textarea"), CommonLib.RandomText(1, 4));
	}

	public void submitOrderIv() {
		CommonLib.clickButton(By.xpath("//input[@id='submitOrder']"));
		try {
			WebElement Frame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/CreateOrderShowMonographs.do?')][last()]"));
			CommonLib.setFrameFromCurrent(Frame);
			CommonLib.clickButton(By.xpath("//input[@value='Submit']"));
			setMainFrame();
		} catch (Exception e) {

		}
	}

	public void validateOrder(String orderType) {
		String drugName = "";
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[text()='" + orderType + "']/..//following-sibling::tr/td[contains(@id,'DRUGNAME')]/a[1]"));
		int size = li.size();

		for (WebElement e : li) {
			drugName = drugName + e.getText() + "_";
		}
		if (orderType.equals("Medication Orders") || orderType.equals("PiggyBack Orders")) {
			if (size - Integer.parseInt(HashTableRepository.getHash("numberOfRows")) == 3) {
				CustomReporter.MessageLogger("Order has been placed", status.Pass);
			} else
				CustomReporter.MessageLogger("Order has not been placed", status.Fail);
		} else {
			if (size - Integer.parseInt(HashTableRepository.getHash("numberOfRows")) == 1 || drugName.contains(HashTableRepository.getHash("Solution"))) {
				CustomReporter.MessageLogger("Order has been placed", status.Pass);
			} else
				CustomReporter.MessageLogger("Order has not been placed", status.Fail);
		}
		System.out.println(orderType + " " + HashTableRepository.getHash("numberOfRows"));
		System.out.println(orderType + " " + size);
	}

	public void coPhysicianFrame() {
		WebElement Frame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'PharmacyPhysicianSearch.do?')][last()]"));
		CommonLib.setFrameFromCurrent(Frame);
	}

	public void setDrugFrame() {
		WebElement Frame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'DrugMasterSearch.do?')][last()]"));
		CommonLib.setFrameFromCurrent(Frame);

	}

	public void setOverrideFramenew() {
		setMainFrame();
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[@id='floatingFrame_0' and contains(@src,'/OptimumClinicals/CreateOrderShowMonographs')]"));
		CommonLib.setFrameFromCurrent(OverrideFrame);
		/// OptimumClinicals/CreateOrderShowMonographsMed.do?action=showMonographs&prevAction=drugSearchOnTab&drugType=100&fpid=nntFloatingPane_0
	}

	public void setConfirmFrame() {
		setMainFrame();
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[@id='floatingFrame_0' and contains(@src,'/OptimumClinicals/pharmacyConfirmOrder.do?')]"));
		CommonLib.setFrameFromCurrent(OverrideFrame);
		/// OptimumClinicals/CreateOrderShowMonographsMed.do?action=showMonographs&prevAction=drugSearchOnTab&drugType=100&fpid=nntFloatingPane_0
	}

	public void setOverrideFrame() {
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/CreateOrderShowMonographs')][last()]"));
		CommonLib.setFrameFromCurrent(OverrideFrame);

	}

	public void setInteractionConfirmFrame() {
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/pharmacyConfirmOrder.do?')][last()]"));
		CommonLib.setFrameFromCurrent(OverrideFrame);

	}

	public void setReleaseConfirmFrame() {
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/ReleaseNowOrderload.do?')][last()]"));

		CommonLib.setFrameFromCurrent(OverrideFrame);

	}

	public void override() {
		setOverrideFramenew();
		try {

			CommonLib.clickButton(By.xpath("//input[@name='override' and @id='functions']"));
		} catch (Exception e) {
			CommonLib.clickButton(By.xpath("//input[@name='cancel' and @id='functions']"));
		}
		setMainFrame();
	}

	public void confirm() {
		setConfirmFrame();
		try {

			CommonLib.clickButton(By.xpath("//input[@name='Submit' and @id='functions']"));
		} catch (Exception e) {
			// CommonLib.clickButton(By.xpath("//input[@name='cancel' and
			// @id='functions']"));
		}
		setMainFrame();
	}

	public void interactions() {
		setOverrideFrame();
		CommonLib.clickButton(By.xpath("//input[@name='submit' and @id='functions']"));
		setMainFrame();
	}

	public void interactionsConfirm() {
		setInteractionConfirmFrame();
		CommonLib.clickButton(By.xpath("//input[@name='submit' and @id='functions']"));
		setMainFrame();
	}

	public void interactionsReleaseNow() {
		setReleaseConfirmFrame();
		CommonLib.clickButton(By.xpath("//input[contains(@name,'Submit') and @id='functions']"));
		setMainFrame();
	}

	public void interactions2ReleaseNow() {
		setReleaseConfirmFrame();
		CommonLib.clickButton(By.xpath("//input[contains(@name,'submit') and @id='functions']"));
		setMainFrame();
	}

	public void setPhysician() {
		try {
			CommonLib.selectfrmDropdwn("orderingPhysician", 1, "", "");
			// CommonLib.selectDojoListValue("orderingPhysician", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			physician();
		} catch (Exception e) {

		}
		try {
			setCoPhysician();
		} catch (Exception e) {

		}
	}

	public void setLabel() {
		CommonLib.setRandomDropDown(By.xpath("td[text()='Label Name:']//following-sibling::td/select"));
	}

	public void placePBOrder(String frequency) {
		clickCreateOrder();
		selectOrder("PB");
		clickPBdrug();
		setDrugFrame();
		RandomSelection("Solution");
		try {
			highAlert();
		} catch (Exception e) {

		}
		try {
			override();
		} catch (Exception e) {

		}
		setMainFrame();
		clickAddPB();
		clickUpdate();
		frequencyMedPB(frequency);
		try {
			setPRNStatus();
		} catch (Exception E) {

		}
		if (!frequency.equals("onetime")) {
			duration();
		}
		setPhysician();
		clickButtonTwiceforSubmit();
		try {
			interactions();
		} catch (Exception e) {

		}
		try {
			override();
		} catch (Exception e) {

		}

		clickSendOrders();
	}

	public void placeMedOrders(String frequency) {
		clickCreateOrder();
		selectOrder("MED");
		drugSelectionMed("6");
		try {
			highAlert();
		} catch (Exception e) {

		}
		try {
			override();
		} catch (Exception e) {

		}
		frequencyMedPB(frequency);
		if (!frequency.equals("onetime")) {
			duration();
		}
		setPhysician();
		try {
			setLabel();
		} catch (Exception e) {

		}
		clickButtonTwiceforSubmit();
		try {
			interactions();
		} catch (Exception e) {

		}
		try {
			override();
		} catch (Exception e) {

		}
		clickSendOrders();
	}

	public void physician() {

		CommonLib.clickButton(By.xpath("//*[@id='table']/tbody/tr[16]/td[4]/a/img"));
		WebElement Frame = CommonLib.getElement(By.xpath("//iframe[@id ='iFrameDialog' and contains(@src,'PharmacyPhysicianSearch.do?')][last()]"));
		CommonLib.setFrameFromCurrent(Frame);
		CommonLib.setData(By.id("searchFor"), "*");
		WebElement userName = CommonLib.getElement(By.id("searchFor"));
		userName.sendKeys(Keys.ENTER);
		// CommonLib.clickButton(By.xpath("//table[@id='physicianList']//tbody//tr[3]//td//a[3]"));
		CommonLib.clickButton(By.xpath("//table[@id='physicianList']//tbody//tr[3]//td[1]//a[1]"));
		setMainFrame();
	}

	public void duration() {
		// boolean flag = true;
		//
		// while (flag) {
		CommonLib.setData(By.id("duration"), "4");

		CommonLib.selectfrmDropdwn("durationType", 0, "", "");

		WebElement userName = CommonLib.getElement(By.id("durationType"));
		userName.sendKeys(Keys.TAB);
		// CommonLib.selectDojoListValue("durationType", "");
		// CommonLib.setdata(By.xpath("//textarea[@id='specialinstruction']"),
		// CommonLib.RandomText(1, 2));
		// CommonLib.clickButton(By.xpath("//td[contains(text(),'Stop Order
		// Policy:')]"));
		// CommonLib.clickButton(By.id("fromStopDateImg"));
		// WebElement Frame = CommonLib.getElement(By.xpath("//iframe[@id
		// ='calendarFrame' and
		// contains(@src,'CalendarPharmacy.do?')][last()]"));
		// CommonLib.setFrameFromCurrent(Frame);
		// String a =
		// CommonLib.getElement(By.id("startDate")).getAttribute("value");
		String a = CommonLib.addDaysToCurrent(4);
		CommonLib.setData(By.xpath("//input[@id='stopDate']"), a);
		String b = CommonLib.systemTime("");
		CommonLib.setData(By.xpath("//input[@id='stopTime']"), b);

		// CommonLib.setData(By.xpath("//textarea[@id='specialinstruction']"),
		// CommonLib.RandomText(1, 2));
		// CommonLib.setData(By.xpath("//textarea[@id='specialinstruction']"),
		// "");
		// userName.sendKeys(Keys.ENTER);
		// flag =
		// CommonLib.getElement(By.xpath("//input[@id='stopDate']")).getAttribute("value").isEmpty();

		// }
	}

	public void frequencyMedPB(String val) {
		if (val == "withprn") {
			CommonLib.selectfrmDropdwn("sig", 10, "", "");
			WebElement userName = CommonLib.getElement(By.id("sig"));
			userName.sendKeys(Keys.TAB);

			CommonLib.staticWait(6);
			CommonLib.selectfrmDropdwn("prnIndication", 5, "", "");
			try {
				CommonLib.selectfrmDropdwn("antiMicrIndication", 4, "", "");
			} catch (Exception e) {

			}

		} else if (val == "withoutprn") {

			CommonLib.selectfrmDropdwn("sig", 15, "", "");
			try {
				CommonLib.selectfrmDropdwn("antiMicrIndication", 4, "", "");
			} catch (Exception e) {

			}
			// CommonLib.selectRequiredDojoListValue("sig", "BID FOOD-TWO TIMES
			// DAILY WITH FOOD");
		} else if (val == "onetime") {
			CommonLib.selectfrmDropdwn("sig", 5, "", "");
			try {
				CommonLib.selectfrmDropdwn("antiMicrIndication", 4, "", "");
			} catch (Exception e) {

			}
			WebElement userName = CommonLib.getElement(By.id("sig"));

		}
		CommonLib.setData(By.xpath("//textarea[@id='specialinstruction']"), CommonLib.RandomText(1, 2));
		CommonLib.setData(By.xpath("//textarea[@id='specialinstruction']"), "");
	}

	public void setPRNStatus() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='prnIndication']"));
	}

	public void setCoPhysician() {
		CommonLib.clickButton(By.xpath("//*[@id='coSignDocListImage']/a/img"));
		WebElement Frame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'PharmacyPhysicianSearch.do?fieldName=coSigningPhysicianList')][last()]"));
		CommonLib.setFrameFromCurrent(Frame);
		CommonLib.setData(By.id("searchFor"), "*");
		CommonLib.clickButton(By.xpath("//table[@id='physicianList']//tbody//tr[3]//td//a[2]"));
		setMainFrame();
	}

	public void clickButtonTwice(String xpath) {
		// CommonLib.clickButton(By.xpath(xpath));
		int x = 2;
		while (x >= 0) {
			CommonLib.clickButton(By.xpath(xpath));
			try {
				setDrugFrame();
				x = -1;

			} catch (Exception e) {
				x--;
			}
		}
	}

	public void setBaseVolume() {
		CommonLib.setData(By.xpath("//input[@id='baseVolume']"), CommonLib.RandomText(2, 4));
	}

	public void clickSumbit() {
		CommonLib.clickButton(By.id("submitOrder"));
	}

	public void menuNavRxsearch() {
		MenuNavigation.menuNav("RxPtSearch");
	}

	public void calculateRows(String orderType) {
		try {
			clickSendOrders();
		} catch (Exception e) {

		}
		// List<WebElement> li = CommonLib.getElements(
		// By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(@id,'DRUGNAME')]/a"));
		// List<WebElement> li = CommonLib.getElements(By.xpath(
		// "//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr[text()='IV
		// Orders']//following-sibling::tr/td[contains(@id,'DRUGNAME')]/a[1]"));IV
		// Orders
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[text()='" + orderType + "']/..//following-sibling::tr/td[contains(@id,'DRUGNAME')]/a[1]"));
		int size = li.size();
		HashTableRepository.setHash("numberOfRows", Integer.toString(size));
	}

	public void clickCreateOrder() {
		CommonLib.clickButton(By.xpath("//*[text()='Create Order']"));
	}

	public void setHighAlertFrame() {
		WebElement HighAlertFrame = CommonLib.getElement(By.xpath("//iframe[@id='floatingFrame_0' and contains(@src,'/OptimumClinicals/CreateOrderShowMonographsMed.do?')]"));
		CommonLib.setFrameFromCurrent(HighAlertFrame);
	}

	public void highAlert() {
		setHighAlertFrame();
		// WebElement wb = CommonLib.getElement(By.xpath("//*[@id='functions'
		// and @value='close']"));
		// String s = wb.getAttribute("id");
		try {

			CommonLib.clickButton(By.xpath("//input[@name='override' and @id='functions']"));
		} catch (Exception e) {
			CommonLib.clickButton(By.xpath("//input[@name='cancel' and @id='functions']"));
		}
		// CommonLib.clickButton(By.xpath("//input[@name='cancel' and
		// @id='functions']"));
		// CommonLib.clickButton(By.xpath(
		// "//div[@id='pageContainer']//div//form//table[@id='buttonTable']//tbody//tr//td[@id='functions'
		// and @value='close']"));
		setMainFrame();
	}

	// public void clickSendOrders() {
	// CommonLib.clickButton(By.id("sendButton_label"));
	// }

	// public void drugSelectionPB(String a) {
	// CommonLib.clickButton(By.xpath("//*[@id='table17']/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/a/img"));
	// setDrugFrame();
	// CommonLib.clickButton(By.xpath("//*[@id='dojox_grid__View_1']/div/div/div/div["
	// + a + "]/table/tbody/tr/td[1]"));
	// CommonLib.setFrame("mainWorkFrame0");
	// }
	public void clickPBdrug() {
		CommonLib.clickButton(By.xpath("//*[@id='table17']/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/a/img"));
	}

	// public void RandomSelection(String select) {
	// CommonLib.setData(By.xpath("//input[@id='searchFor']"), "*");
	// CommonLib.clickButton(By.xpath("//div[text()='Search']"));
	// List<WebElement> li =
	// CommonLib.getElements(By.xpath("//div[@id='displayDataGrid']/div[2]//div[@class='dojoxGridContent']/div//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[1]"));
	// int random = CommonLib.generateRandom(0, li.size() - 1);
	// String value = li.get(random).getText();
	// li.get(random).click();
	// if (select.equals("Solution"))
	// HashTableRepository.setHash(select, value);
	// else
	// HashTableRepository.setHash("SelectedAdditiveIv", value);
	// setMainFrame();
	// }

	public void clickAddPB() {

		CommonLib.clickButton(By.xpath("//input[@id='doseVolumeAdd']/..//following-sibling::td/input"));

	}

	public void clickButtonTwiceforSubmit() {
		int x = 2;
		while (x >= 0) {
			try {
				clickSumbit();
			} catch (Exception e) {

			}

			x--;
		}
	}

	public void chooseAction(String value) {
		CommonLib.setDropDown(By.xpath("//select[@id='searchBy']"), value);
	}

	public boolean checkIfOrdersPresent(String orderType) {
		boolean status = false;
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[contains(@id,'DRUGNAME')]"));
		List<WebElement> st = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[contains(@id,'STATUS')]"));

		int med = li.size();
		if (med != 0 && st.get(0).getText().contains("ACU")) {
			status = true;
		}
		return status;

	}

	public void clickFirstOrder(String orderType) {
		int i = 2;
		while (i >= 0) {
			if (!CommonLib.getElement(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[2]/input")).isSelected())
				CommonLib.clickButton(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[2]/input"));
			i--;
		}
		String referenceNumber = CommonLib.getElement(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[contains(@id,'REFERENCE')]")).getText();
		HashTableRepository.setHash("ReferenceNumber", referenceNumber);
		CommonLib.clickButton(By.xpath("//div[@id='tableDefault']//following-sibling::div/table/tbody/tr/td//span/div[contains(text(),'Submit')]"));
	}

	public void clickOnCompleteOrder() {
		CommonLib.clickButton(By.xpath("//input[@id='completeStatus']"));
	}

	public void clickReferenceNumberOrder(String orderType) {
		int k = 20;

		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr/td[contains(@id,'REFERENCE')]"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr/td[2]/input[@name='Sel' and @type='checkbox']"));

		// int two = li2.size();
		for (int i = 0; i < li.size(); i++) {
			if (li.get(i).getText().contains(HashTableRepository.getHash("ReferenceNumber"))) {
				while (k >= 0) {
					if (!li2.get(i).isSelected()) {
						CommonLib.staticWait(10);
						li2.get(i).click();
						CommonLib.staticWait(10);
					} else
						break;
					k--;
				}
				break;
			}
		}
		CommonLib.clickButton(By.xpath("//div[@id='tableDefault']//following-sibling::div/table/tbody/tr/td//span/div[contains(text(),'Submit')]"));
	}

	public void validateAction(String action, String orderType) {
		int index = 0;

		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr/td[contains(@id,'REFERENCE')]"));
		for (int i = 0; i < li.size(); i++) {
			if (li.get(i).getText().contains(HashTableRepository.getHash("ReferenceNumber"))) {
				index = i + 1;
				break;
			}
		}
		String st = CommonLib.getElement(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[" + index + "]/td[contains(@id,'STATUS')]")).getText();
		switch (action) {
		case "confirm":
			if (action.equals("confirm") && st.equals("AC")) {
				CustomReporter.MessageLogger("Confirm successful", status.Pass);
			} else
				CustomReporter.MessageLogger("Confirm  not successful", status.Fail);
			break;

		case "hold":
			if (action.equals("hold") && st.equals("HLD")) {
				CustomReporter.MessageLogger("hold successful", status.Pass);
			} else
				CustomReporter.MessageLogger("hold  not successful", status.Fail);
			break;

		case ("release"):
			if (action.equals("release") && st.equals("AC")) {
				CustomReporter.MessageLogger("release successful", status.Pass);
			} else
				CustomReporter.MessageLogger("release  not successful", status.Fail);
			break;
		case ("discontinue"):
			if (action.equals("discontinue") && st.equals("D/C")) {
				CustomReporter.MessageLogger("discontinue successful", status.Pass);
			} else
				CustomReporter.MessageLogger("discontinue  not successful", status.Fail);
			break;

		case ("interventions"):
			String newCount = CommonLib.getElement(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[contains(@id,'INTERVENTION')]")).getText();
			int newCountInt = Integer.parseInt(newCount);
			int oldCount = Integer.parseInt(HashTableRepository.getHash("intervention"));
			if (newCountInt - oldCount == 1) {

				CustomReporter.MessageLogger("intervention successful", status.Pass);
			} else
				CustomReporter.MessageLogger("intervention  not successful", status.Fail);
			break;
		}

	}

	public void countInterventions(String orderType) {
		String intervention = CommonLib.getElement(By.xpath("//div[@id='inpatientTab']//form[@id='frm']/table/tbody//tr/td[contains(text(),'" + orderType + "')]/..//following-sibling::tr[1]/td[contains(@id,'INTERVENTION')]")).getText();
		HashTableRepository.setHash("intervention", intervention);
	}

	public void setInterventionFrame() {
		WebElement OverrideFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'/OptimumClinicals/PharmacyInterventionFromProfile.do?')][last()]"));

		CommonLib.setFrameFromCurrent(OverrideFrame);
	}

	public void clickAddIntervention() {
		CommonLib.clickButton(By.xpath("//input[@id='Add']"));

	}

	public void setClinicalArea() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='selectclinicalArea']"));
		CommonLib.staticWait(10);

	}

	public void setInterventionType() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='selectIntType']"));
		CommonLib.staticWait(10);
	}

	public void setInterventionAction() {
		CommonLib.setRandomDropDown(By.xpath("//select[@id='selectIntAction']"));
		CommonLib.staticWait(10);
	}

	public void setFollowUpToNo() {
		CommonLib.clickButton(By.xpath("//input[@id='followUpRadio' and @value='N']"));
		CommonLib.clickButton(By.xpath("//input[@name='btnSubmit' and @value='Submit']"));
	}

	public void clickClose() {
		CommonLib.clickButton(By.xpath("//div[@id='interventionDetailToolbar']//span[contains(text(),'Close')]"));
		setMainFrame();
	}

}
