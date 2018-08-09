package PatientEducationLog.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class PatientEducationLogPage {

	public void menuNav() {
		MenuNavigation.menuNav("PatientEducationLog");
	}

	public void clickAdd() {
		CommonLib.clickButton(By.id("addItem_label"));
	}

	public void clickEdit() {
		CommonLib.clickButton(By.id("edit2Item_label"));
	}

	public void clickDisplay() {
		CommonLib.clickButton(By.id("displayItem_label"));
	}

	public void clickDelete() {
		CommonLib.clickButton(By.id("deleteItem_label"));
	}

	public void clickPrint() {
		CommonLib.clickButton(By.id("printItem_label"));
	}

	public void clickPrintAll() {
		CommonLib.clickButton(By.id("printAllItem_label"));
	}

	public void clickAllCategories() {
		CommonLib.clickButton(By.id("ecType_Categories"));
	}

	public void clickSubmit() {
		CommonLib.clickButton(By.id("buttonSubmitAddExitCareEducation_label"));
	}

	public void SetMainFrame() {
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
	}

	public void SetPrintFrame() {
		WebElement MedPrintFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'medication-reconciliation.action?action=print')][last()]"));
		CommonLib.setFrameFromCurrent(MedPrintFrame);
	}

	public void PrintSubmit() {
		CommonLib.clickButton(By.xpath("//table[@id='buttonTable']//input[@id='functions']"));
	}

	public void verifyAdd() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		int listSize = addList.size();
		int listSizeInit = Integer.parseInt(HashTableRepository.getHash("AddGrid"));
		if (listSize > listSizeInit)
			CustomReporter.MessageLogger("Add Functionality Of Patient Education Log is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Add Functionality Of Patient Education Log is not working properly", status.Fail);
		SetMainFrame();
	}

	public void verifyPrintAll() {
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Patient Education Log is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Patient Education Log is not working properly", status.Fail);
	}

	public void clickorder() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		WebElement element = addList.get(addList.size() - 1);
		element.click();
	}

	public void setInfo() {
		try {
			CommonLib.selectDojoListValue("piInfoProvidedTo", "");
		} catch (Exception e) {
		}
	}

	public void setComprehension() {
		try {
			CommonLib.selectDojoListValue("patientComprehension", "");
		} catch (Exception e) {
		}
	}

	public void setBarriersToLearning() {
		try {
			CommonLib.selectDojoListValue("patientBarriersToLearning", "");
		} catch (Exception e) {
		}
	}

	public void setComment() {
		CommonLib.setData(By.id("piCommentsText"), "data");
	}

	public void submitButton() {
		CommonLib.clickButton(By.id("buttonSubmitAddPatientEducation_label"));
	}

	public void verifyEdit() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]//table//tbody//tr[1]//td[8]"));
		// int listSize = addList.size();
		WebElement element = addList.get(addList.size() - 1);
		String a = element.getText();
		if (a.equals("data"))
			CustomReporter.MessageLogger("Edit Functionality Of Patient Education Log is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Edit Functionality Of Patient Education Log is not working properly", status.Fail);
	}

	public void clickDeleteOk() {
		CommonLib.clickButton(By.xpath("//span[text()='OK']"));
	}

	public void verifyDelete() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		int listSize = addList.size();
		int listSizeold = Integer.parseInt(HashTableRepository.getHash("DeleteGrid"));
		if (listSizeold > listSize)
			CustomReporter.MessageLogger("Delete Functionality Of Patient Education Log is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Delete Functionality Of Patient Education Log is not working properly", status.Fail);
	}

	public void clickCancelDisplay() {
		CommonLib.clickButton(By.id("buttonCancelDisplayPatientEducation_label"));
	}

	public void verifyPrint() {

		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Patient Education Log is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Patient Education Log is not working properly", status.Fail);
	}

	public void setWindowHandleToLast() {
		Set<String> handles = CommonLib.GetDriver().getWindowHandles();
		for (String handle : handles) {
			CommonLib.GetDriver().switchTo().window(handle);
		}
	}

	public void setDisplayFrame() {
		WebElement setDisplayFrame = CommonLib.getElement(By.xpath("//iframe[contains(@src,'patient-education-details.action?')][last()]"));
		CommonLib.setFrameFromCurrent(setDisplayFrame);
	}

	public void selectPrintAll() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//span[@id='printRecordCheckBox']//div[@class='dijit dijitReset dijitInline dijitCheckBox']//input[@name='printRecord']"));
		// int listSize = addList.size();
		WebElement element = addList.get(addList.size() - 1);
		element.click();
		try {
			WebElement element2 = addList.get(addList.size() - 2);
			element2.click();
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("buttonSubmitPrintAllRecords_label"));
		CommonLib.staticWait(5);
	}
}
