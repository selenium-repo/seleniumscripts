package pharmacy.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import pharmacy.testcases.PatientSearchTestCases;

public class StandingOrdersPage extends PatientSearchTestCases {

	public void clickCreateOrder() {
		CommonLib.clickButton(By.xpath("//div[contains(text(),'Create Order')]"));

	}

	public void selectStandingOrderRadioButton() {
		CommonLib.getElement(By.xpath("//td//input[@name='orderMethod' and @value='SO']")).click();
	}

	public void selectStandingOrderList() throws AWTException {
		// CommonLib.selectRequiredDojoListValue("dijit_form_FilteringSelect_0",
		// Config.props.getProperty("standing_order"));
		String standing_order = Config.props.getProperty("standing_order");

		CommonLib.clickButton(By.id("dijit_form_FilteringSelect_0"));
		for (int c = 0; c < standing_order.length(); c++) {
			char ch = standing_order.charAt(c);
			String s = new StringBuilder().append(ch).toString();
			CommonLib.getElement(By.id("dijit_form_FilteringSelect_0")).sendKeys(s);
		}
		CommonLib.getElement(By.id("dijit_form_FilteringSelect_0")).sendKeys(Keys.ARROW_DOWN);
		CommonLib.getElement(By.id("dijit_form_FilteringSelect_0")).sendKeys(Keys.ARROW_DOWN);
		CommonLib.getElement(By.id("dijit_form_FilteringSelect_0")).sendKeys(Keys.ENTER);

	}

	public void selectDirectAccessNo() {
		CommonLib.clickButton(By.xpath("//td//input[@name='directAccess' and @value='N']"));
	}

	public void clickStandingOrderSubmit() {
		CommonLib.clickButton(By.xpath("//input[@id='navigation' and @value='Submit']"));
	}

	public void checkStandingOrder(String var) {
		if (!CommonLib.getElement(By.xpath("//td//input[@name='selOrder' and @value='" + var + "']")).isSelected()) {
			CommonLib.clickButton(By.xpath("//td//input[@name='selOrder' and @value='" + var + "']"));

		}
	}

	public void clickSubmit() {
		CommonLib.clickButton(By.id("selButtonId"));
	}

	public void checkInteractions() {
		if (CommonLib.isElementPresent(
				By.xpath("//iframe[contains(@id, 'floatingFrame') or contains(@id, 'iFrameDialog')][last()]"), 5)) {
			WebElement iFrame = CommonLib.getElement(
					By.xpath("//iframe[contains(@id, 'floatingFrame') or contains(@id, 'iFrameDialog')][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
			setOverrideReason();
		}
	}

	public void setOverrideReason() {
		if (CommonLib.isElementPresent(By.xpath("//input[@name='overrideReason']"), 3)) {
			CommonLib.setData(By.xpath("//input[@name='overrideReason']"), CommonLib.RandomText(1, 10));
			CommonLib.clickButton(By.xpath("//input[@value='Submit' or @id='functions']"));
			setHomeFrame();

		}

	}

	public void setHomeFrame() {
		CommonLib.GetDriver().switchTo().parentFrame();
		CommonLib.setFrame(By.id(Config.props.getProperty("MainFrame")));

	}

	public void selectUserOrderType() {
		CommonLib.setRandomDropDown(By.id("userOrderType"));
	}

	public void enterSpecialInstructions() {
		CommonLib.setData(By.xpath("//td//textarea[@name='specialinstruction']"), CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.xpath("//td//textarea[@name='specialinstruction']")).sendKeys(Keys.RETURN);
		CommonLib.setData(By.xpath("//td//textarea[@name='specialinstruction']"), CommonLib.RandomText(1, 15));
	}

	public void clickOrderSubmit() {
		CommonLib.clickButton(By.xpath("//td//input[@id='submitOrder']"));
	}

	public void clickSendOrders() {
		CommonLib.clickButton(By.id("sendButton_label"));
	}

	public int getStandingOrdersRowCount(String order_type) {
		int med_count = 0;
		if (CommonLib.isElementPresent(
				By.xpath("//tr/td[contains(@class,'row')]/a[contains(@href,'" + order_type + "')]"), 8)) {
			med_count = CommonLib
					.getElements(By.xpath("//tr/td[contains(@class,'row')]/a[contains(@href,'" + order_type + "')]"))
					.size();
		}
		return med_count;
	}

}
