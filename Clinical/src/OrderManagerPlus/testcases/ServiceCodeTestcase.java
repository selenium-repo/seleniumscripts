package OrderManagerPlus.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import OrderManagerPlus.pages.OrderManagerPlusPage;
import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class ServiceCodeTestcase extends OrderManagerPlusPage {

	public void servicecodetable_acknowledgement() {
		MenuNavigation.menuNav("ServiceCode");
		CommonLib.setValueDojo("//input[@id='searchValue']", Config.props.getProperty("drug_AckUpd"));
		CommonLib.getElement(By.id("searchValue")).sendKeys(Keys.ENTER);
		CommonLib.staticWait(6);
		CommonLib.clickButton(By.xpath("//div[@id='searchPane']//div[@id='gbox_searchGrid']//div[3]//div[3]//div//table//tbody//tr[2]//td[2]//a"));
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='N']"));
		boolean flag1 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='N']")).isSelected();
		if (flag1 == true) {
			CustomReporter.MessageLogger("Noting Required is set as no.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Noting Required is set as yes.", status.Fail);
		}

		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[2]/fieldset[@class='container groupRequired']/input[@value='N']"));
		boolean flag2 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[2]/fieldset[@class='container groupRequired']/input[@value='N']")).isSelected();
		if (flag2 == true) {
			CustomReporter.MessageLogger("Receipt Required is set as no.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Receipt Required is set as yes.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[3]/fieldset[@class='container groupRequired']/input[@value='N']"));
		boolean flag3 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[3]/fieldset[@class='container groupRequired']/input[@value='N']")).isSelected();
		if (flag3 == true) {
			CustomReporter.MessageLogger("Verification Required is set as no.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Verification Required is set as yes.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[4]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag4 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[4]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag4 == true) {
			CustomReporter.MessageLogger("Acknowledgement Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Acknowledgement Required is set as no.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[3]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='N']"));
		boolean flag5 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[3]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='N']")).isSelected();
		if (flag5 == true) {
			CustomReporter.MessageLogger("Specimen Collection Required is set as no.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Specimen Collection Required is set as yes", status.Fail);
		}
		CommonLib.setDropDown(By.id("svcanu"), "Forever");
		CommonLib.setDropDown(By.id("svupdu"), "Forever");
		CommonLib.setDropDown(By.id("svdisu"), "Forever");
		CommonLib.setDropDown(By.id("svdou"), "Forever");
		CommonLib.setDropDown(By.id("svdgou"), "Forever");
		CommonLib.clickButton(By.id("button-submit"));
	}

	public void servicecodetable() {
		MenuNavigation.menuNav("ServiceCode");
		CommonLib.setValueDojo("//input[@id='searchValue']", Config.props.getProperty("drug_servicecode"));
		CommonLib.getElement(By.id("searchValue")).sendKeys(Keys.ENTER);
		// CommonLib.setData(By.id("searchValue"),
		// Config.props.getProperty("drug_servicecode"));
		CommonLib.staticWait(6);
		CommonLib.clickButton(By.xpath("//div[@id='searchPane']//div[@id='gbox_searchGrid']//div[3]//div[3]//div//table//tbody//tr[2]//td[2]//a"));
		// String a = Config.props.getProperty("drug_servicecode");
		// CommonLib.clickButton(By.id(a));
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag1 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag1 == true) {
			CustomReporter.MessageLogger("Noting Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Noting Required is set as no.", status.Fail);
		}

		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[2]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag2 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[2]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag2 == true) {
			CustomReporter.MessageLogger("Receipt Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Receipt Required is set as no.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[3]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag3 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[3]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag3 == true) {
			CustomReporter.MessageLogger("Verification Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Verification Required is set as no.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[4]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag4 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[2]/td[@class='entryFormCell']/table/tbody/tr/td[4]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag4 == true) {
			CustomReporter.MessageLogger("Acknowledgement Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Acknowledgement Required is set as no.", status.Fail);
		}
		CommonLib.clickButton(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[3]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='Y']"));
		boolean flag5 = CommonLib.getElement(By.xpath("//form[@id='detailForm1']/table[4]/tbody/tr[3]/td[@class='entryFormCell']/table/tbody/tr/td[1]/fieldset[@class='container groupRequired']/input[@value='Y']")).isSelected();
		if (flag5 == true) {
			CustomReporter.MessageLogger("Specimen Collection Required is set as yes.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Specimen Collection Required is set as no.", status.Fail);
		}
		CommonLib.setDropDown(By.id("svcanu"), "Forever");

		CommonLib.setDropDown(By.id("svupdu"), "Forever");
		CommonLib.setDropDown(By.id("svdisu"), "Forever");
		CommonLib.setDropDown(By.id("svdou"), "Forever");
		CommonLib.setDropDown(By.id("svdgou"), "Forever");
		CommonLib.clickButton(By.id("button-submit"));
	}

}
