package PatientEducationLog.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import PatientEducationLog.pages.PatientEducationLogPage;
import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;

public class PatientEducationLogTestcase {

	PatientEducationLogPage obj = new PatientEducationLogPage();

	public void add() {
		obj.menuNav();
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		int listSize = addList.size();
		HashTableRepository.setHash("AddGrid", String.valueOf(listSize));
		obj.clickAdd();
		obj.clickAllCategories();
		CommonLib.staticWait(10);
		try {
			List<WebElement> listCheck = CommonLib.getElements(By.xpath("//span[@id='lookup']//input[@type='checkbox']"));
			String sd = CommonLib.getText(By.xpath("//span[@id='lookup']"));
			String[] checkArray = sd.split("    ");

			for (int i = 0; i < checkArray.length; i++) {
				if (checkArray[i].contains(Config.props.getProperty("patientEduLog"))) {
					listCheck.get(i - 1).click();
					break;
				}
			}
			CommonLib.staticWait(10);
			List<WebElement> check2 = CommonLib.getElements(By.xpath("//span[@id='documents']//input[@type='checkbox']"));
			String sd2 = CommonLib.getText(By.xpath("//span[@id='documents']"));
			String[] checkArray2 = sd2.split("    ");

			for (int i = 0; i < checkArray2.length; i++) {
				if (checkArray2[i].contains(Config.props.getProperty("patientEduLog1"))) {
					check2.get(i - 1).click();
					break;
				}
			}
		} catch (Exception e) {
		}
		obj.clickSubmit();
		obj.verifyAdd();
	}

	public void edit() {
		obj.clickorder();
		obj.clickEdit();
		obj.setInfo();
		obj.setComment();
		obj.submitButton();
		obj.verifyEdit();

	}

	public void delete() {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='searchPane']//div[@id='searchGrid']//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		int listSize = addList.size();
		HashTableRepository.setHash("DeleteGrid", String.valueOf(listSize));
		obj.clickorder();
		obj.clickDelete();
		obj.clickDeleteOk();
		obj.verifyDelete();

	}

	public void display() {
		PatientEducationLogPage obj = new PatientEducationLogPage();
		try {
			obj.clickorder();
			obj.clickDisplay();
			obj.setDisplayFrame();
			obj.clickCancelDisplay();
			obj.SetMainFrame();
			CustomReporter.MessageLogger("Display Functionality Of Patient Education Log is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Display Functionality Of Patient Education Log is not working properly", status.Fail);
		}

	}

	public void print() {
		obj.clickPrint();
		obj.verifyPrint();
		obj.SetMainFrame();
	}

	public void printAll() {
		obj.menuNav();
		obj.clickPrintAll();
		obj.selectPrintAll();
		obj.verifyPrintAll();
	}

}
