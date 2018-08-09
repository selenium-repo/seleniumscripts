package discharge_summary.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.CommonLib;
import common.Config;

public class DischargeSummaryPage {

	public void clickDischargeSummaryAdd() {
		CommonLib.clickButton(By.xpath("//span[@id='addItem_label']"));
	}

	public void clickDischargeSummaryEdit() {
		CommonLib.clickButton(By.xpath("//span[@id='editItem_label']"));
	}

	public void clickDischargeSummaryDelete() {
		CommonLib.clickButton(By.xpath("//span[@id='deleteItem_label']"));
	}

	public void clickDischargeSummaryMarkFinalize() {
		CommonLib.clickButton(By.xpath("//span[@id='finalizeItem_label']"));
	}

	public void clickDischargeSummaryRequestElectronicCopy() {
		CommonLib.clickButton(By.xpath("//span[@id='requestElectronicCopy_label']"));
	}

	public void clickDischargeSummaryGenerateElectronicCopy() {
		CommonLib.clickButton(By.xpath("//span[@id='generateElectronicCopy_label']"));

	}

	public void clickDischargeSummaryAudit() {
		CommonLib.clickButton(By.xpath("//span[@id='viewAudit_label']"));
	}

	public void clickDischargeSummaryPrint() {
		CommonLib.clickButton(By.xpath("//span[@id='printItem_label']"));
	}

	public String selectTypeListBox(String id) throws InterruptedException {

		String selected_value = CommonLib.selectRequiredDojoListValue(id, Config.props.getProperty("discharge_summary"));
		return selected_value;

	}

	public void clickDischargeSummarySubmit() {
		CommonLib.clickButton(By.xpath("//span[@id='wizardSubmit_label']"));
	}

	public void setHomeFrame() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public int getRowCount() {
		return CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div//div[contains(@class, 'dojoxGridRow')]")).size();

	}

	public void selectFirstRow() {
		CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div//div[contains(@class, 'dojoxGridRow')]/table/tbody/tr/td")).click();
	}

	public void clickOnStatus() {
		CommonLib.getElement(By.xpath("//span[contains(@class, 'dijitTitlePaneTextNode') and contains(.,'Discharge Status')]")).click();
	}

	public boolean clickOnComment() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//td[@class='sectionHeader']/div/div/div//span[contains(.,'Comments')]"), 2)) {
			CommonLib.getElement(By.xpath("//td[@class='sectionHeader']/div/div/div//span[contains(.,'Comments')]")).click();
			check = true;
		}
		return check;
	}

	public void selectDischStatusValue() {

		try {
			CommonLib.selectDojoListValue("dischgstat", "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setDialogFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='iFrameDialog'][last()]"), 3)) {
			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
		}
	}

	public void setEntireHistoryRadioButton() {
		CommonLib.getElement(By.xpath("//input[@id='displayTypeAll' and @role='radio']")).click();
	}

	public void selectEditReason() {
		WebElement wb = CommonLib.getElement(By.xpath("//input[contains(@class,'dijitArrowButtonInner') and @value='▼ ']"));
		wb.click();
		CommonLib.staticWait(2);
		Actions builder = new Actions(CommonLib.GetDriver());
		builder.sendKeys(Keys.ARROW_DOWN).perform();
		builder.sendKeys(Keys.ARROW_DOWN).perform();
		builder.sendKeys(Keys.ENTER).perform();
		CommonLib.staticWait(3);

	}

	public void clickSelectReasonSubmit() {
		CommonLib.clickButton(By.xpath("//input[@name='button.Submit' and @id='functions']"));

	}

	public String getUpdateReasonValue() {
		// String value =
		// CommonLib.getElement(By.xpath("//div[@id='searchGrid']/div[2]/div/div/div/div//div[contains(@class,
		// 'dojoxGridRow')]/table/tbody/tr/td[7]")).getText();
		// return value;
		return CommonLib.getElement(By.xpath("//form[@id='selectReason']/table/tbody/tr[2]/td[2]/table/tbody/tr/td/div")).getText();
	}

	public String getFinalizedText() {
		return CommonLib.getElement(By.xpath("//div[@id='dijit_Dialog_1']/div[2]/table/tbody/tr/td")).getText();
	}

	public void clickFinalizeOK() {
		CommonLib.clickButton(By.xpath("//div[@id='dijit_Dialog_1']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
	}

	public String getRequestElectronicCopyText() {
		return CommonLib.getElement(By.xpath("//div[@id='genericDialog']/div[2]/table/tbody/tr/td")).getText();

	}

	public void clickElectronicOK() {
		CommonLib.clickButton(By.xpath("//div[@id='genericDialog']/div[2]/table/tbody/tr[4]/td/span/span/span/span[3]"));
	}

	public void setWindowHandleToLast() {
		Set<String> handles = CommonLib.GetDriver().getWindowHandles();
		for (String handle : handles) {
			CommonLib.GetDriver().switchTo().window(handle);
			CommonLib.staticWait(2);
		}
	}

	public int getWindowHandleCount() {
		return CommonLib.GetDriver().getWindowHandles().size();
	}

	public void closePDF() {
		setWindowHandleToLast();

	}

	public void SetHomeFrame() {
		CommonLib.setFrameToDefault();
		CommonLib.setFrame(By.id(Config.props.getProperty("MainFrame")));
	}

	public void setAuditFrame() {
		CommonLib.staticWait(3);
		WebElement iFramePatientAudit = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog' and contains(@src, 'DischargeSummaryAudit')][last()]"));
		CommonLib.setFrameFromCurrent(iFramePatientAudit);

	}

	public String getLastAuditEventText() {
		String lastaudit = CommonLib.getText(By.xpath("//div[@id='auditGrid']/div[2]/div[2]/div/div/div/div[@class='dojoxGridRow']/table[@class='dojoxGridRowTable']/tbody/tr/td[2]"));
		return lastaudit;
	}

	public void closeAuditDialog() {
		CommonLib.clickButton(By.xpath("//div[@id='vitalSignsGraphToolbar']/span/span/span/span[3]"));
		CommonLib.staticWait(2);
		CommonLib.GetDriver().switchTo().parentFrame();
	}

	public String getDeleteText() {

		return CommonLib.getElement(By.xpath("//div[@id='dijit_Dialog_0']/div[2]/table/tbody/tr/td")).getText();

	}

	public void closeDeletePopUp() {

		CommonLib.getElement(By.xpath("//div[@id='dijit_Dialog_0']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]")).click();
	}

	public void setComments() throws InterruptedException {
		// CommonLib.clickIcon("dijitEditorBody");
		// JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		// js.executeScript("document.getElementById('dijitEditorBody').value="
		// + Auto_Text + "");
		// js.executeScript("document.getElementById('dijitEditorBody').click()");

		if (CommonLib.isElementPresent(By.xpath("//iframe[@class='dijitEditorIFrame'][last()]"), 3)) {

			WebElement iFrameDialog = CommonLib.getElement(By.xpath("//iframe[@class='dijitEditorIFrame'][last()]"));
			CommonLib.setFrameFromCurrent(iFrameDialog);
			if (CommonLib.isElementPresent(By.xpath("//div[(@id='dijitEditorBody') and (@contenteditable='true')]"), 3)) {
				CommonLib.getElement(By.xpath("//div[(@id='dijitEditorBody') and (@contenteditable='true')]")).clear();
				CommonLib.getElement(By.xpath("//div[(@id='dijitEditorBody') and (@contenteditable='true')]")).sendKeys(CommonLib.RandomText(1, 10));
				CommonLib.GetDriver().switchTo().parentFrame();
				SetHomeFrame();

			}

		}

	}
}
