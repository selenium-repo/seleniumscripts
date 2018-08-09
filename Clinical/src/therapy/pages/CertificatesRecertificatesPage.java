package therapy.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;

public class CertificatesRecertificatesPage extends PatientTherapyDocumentationPage {

	public void selectCertificateTab() {
		CommonLib.clickButton(By.id("patientTherapyDocumentationTabs_tablist_outPatientCertTab"));
	}

	public void clickCertificatesAddArrow() {
		CommonLib.clickButton(By.xpath("//div[@class='dijitReset dijitArrowButtonInner']"));
	}

	public void clickCertificateAdd() {
		CommonLib.clickButton(By.xpath("//div[@id='addItemCombo_dropdown']/table/tbody/tr/td[2]"));
	}

	public void clickReCertificateAdd() {
		CommonLib.clickButton(By.xpath("//div[@id='addItemCombo_dropdown']/table/tbody/tr[2]/td[2]"));
	}

	public void switchToLastFloatingFrame() {
		if (CommonLib.isElementPresent(By.xpath(".//iframe[contains(@id,'floatingFrame')][last()]"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath(".//iframe[contains(@id,'floatingFrame')][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void switchToReCertiFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='floatingFrame_3']"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='floatingFrame_3']"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void switchToShortTermGoalFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='shortTermGoal_iframe']"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='shortTermGoal_iframe']"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void setFrequencyValue() {
		CommonLib.setData(By.xpath("//form[@id='therapyReCertificationForm']//div[contains(@class,'dijitInputField dijitInputContainer')]/input[@id='freqDuration']"), getRandomIntString());
	}

	public void switchToLongTermGoalFrame() {
		CommonLib.staticWait(3);
		CommonLib.GetDriver().switchTo().parentFrame();

		// CommonLib.GetDriver().switchTo().defaultContent();
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='longTermGoal_iframe']"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='longTermGoal_iframe']"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void switchToTreatmentPlanFrame() {
		CommonLib.staticWait(3);
		CommonLib.GetDriver().switchTo().parentFrame();

		// CommonLib.GetDriver().switchTo().defaultContent();
		if (CommonLib.isElementPresent(By.xpath("//iframe[@id='treatmentPlan_iframe']"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@id='treatmentPlan_iframe']"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void setParentFrame() {
		CommonLib.staticWait(3);
		CommonLib.GetDriver().switchTo().parentFrame();
	}

	public void setInitialAssessment() {
		if (CommonLib.isElementPresent(By.xpath("//form[@id='therapyReCertificationForm']//textarea[@id='initialAssessment']"), 4)) {
			CommonLib.setData(By.xpath("//form[@id='therapyReCertificationForm']//textarea[@id='initialAssessment']"), CommonLib.RandomText(1, 10));
		}
	}

	public void setReasonContinueTreatment() {
		if (CommonLib.isElementPresent(By.xpath("//form[@id='therapyReCertificationForm']//textarea[@id='reasonContTreatment']"), 4)) {
			CommonLib.setData(By.xpath("//form[@id='therapyReCertificationForm']//textarea[@id='reasonContTreatment']"), CommonLib.RandomText(1, 10));
		}
	}

	public void setShortTermGoal() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='dijitEditorBody']"), 4)) {
			// CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"),
			// CommonLib.RandomText(1, 8));
			String data = CommonLib.RandomText(1, 10);
			CommonLib.GetDriver().findElement(By.xpath("//div[@id='dijitEditorBody']")).sendKeys(data);
		}
	}

	public void setLongTermGoal() {
		if (CommonLib.isElementPresent(By.xpath("(//div[@id='dijitEditorBody'])"), 4)) {
			// CommonLib.setData(By.xpath("(//div[@id='dijitEditorBody'])"),
			// CommonLib.RandomText(1, 8));
			String data = CommonLib.RandomText(1, 10);
			CommonLib.GetDriver().findElement(By.xpath("//div[@id='dijitEditorBody']")).sendKeys(data);
		}
	}

	public void switchToLastEditorFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[@class='dijitEditorIFrame'][last()]"), 3)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[@class='dijitEditorIFrame'][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public void setTreatmentPlan() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='dijitEditorBody']"), 4)) {
			// CommonLib.setData(By.xpath("//div[@id='dijitEditorBody'][3]"),
			// CommonLib.RandomText(1, 8));
			String data = CommonLib.RandomText(1, 10);
			CommonLib.GetDriver().findElement(By.xpath("//div[@id='dijitEditorBody']")).sendKeys(data);
			CommonLib.staticWait(1);

		}
	}

	public void clickCertificateSubmit() throws AWTException {
		CommonLib.clickButton(By.xpath("//form[@id='therapyReCertificationForm']//span[@id='wizardSubmitRecert_label']"));
		// WebElement wb =
		// CommonLib.GetDriver().findElement(By.xpath("//div[@id='dijitEditorBody']"));
		// Robot rbt = new Robot();
		// rbt.keyPress(KeyEvent.VK_TAB);
		// CommonLib.staticWait(1);
		// rbt.keyPress(KeyEvent.VK_TAB);
		// CommonLib.staticWait(1);
		// rbt.keyPress(KeyEvent.VK_TAB);
		// CommonLib.staticWait(2);
		// rbt.keyPress(KeyEvent.VK_ENTER);
		// CommonLib.staticWait(2);
		// rbt.keyPress(KeyEvent.VK_ENTER);
		// CommonLib.staticWait(4);
	}

	public int getCertificatesCount() {
		int certi_count = 0;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 5)) {
			certi_count = CommonLib.getElements(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
		}
		return certi_count;
	}

	public void selectPhysician() throws InterruptedException {
		// CommonLib.selDojoListValue("phyList", 2);
		CommonLib.selectDojoListByXpath("//input[@id='phyList']", "phyList");
	}

	public boolean selectCreatedCertificateRow() {
		int certi_count = 0;
		boolean flag = true;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 3)) {
			certi_count = CommonLib.getElements(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]")).size();
			System.out.println(certi_count);
			for (int i = 1; i <= certi_count; i++) {
				String text = CommonLib.getElement(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + i + "]/table/tbody/tr/td[6]")).getText();
				if (text.equals("ReCert")) {
					CommonLib.getElement(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + (i - 1) + "]")).click();
					flag = false;
				}
			}
		}
		return flag;
	}

	public void clickCertificatesEdit() {
		CommonLib.clickButton(By.xpath("//span[@id='editItem_label']"));
	}

	public void clickLastRow(int i) {
		CommonLib.getElement(By.xpath("//div[@id='certRecertGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')][" + i + "]")).click();
	}

	// public void switchToEditFloatingFrame() {
	// if
	// (CommonLib.isElementPresent(By.xpath("//iframe[@id='floatingFrame_1']"),
	// 6)) {
	// WebElement iFrame =
	// CommonLib.getElement(By.xpath("//iframe[@id='floatingFrame_1']"));
	// CommonLib.setFrameFromCurrent(iFrame);
	// }
	// }
	public void switchToEditFloatingFrame() {
		if (CommonLib.isElementPresent(By.xpath("//iframe[contains(@id, 'floatingFrame')][last()]"), 6)) {
			WebElement iFrame = CommonLib.getElement(By.xpath("//iframe[contains(@id, 'floatingFrame')][last()]"));
			CommonLib.setFrameFromCurrent(iFrame);
		}
	}

	public boolean selectPrimaryDiagnosis() throws InterruptedException {
		boolean flag = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@id='widget_diagnosisList']/div[3]/input[@id='diagnosisList']"), 3)) {
			CommonLib.selectDojoListByXpath("//div[@id='widget_diagnosisList']/div[3]/input[@id='diagnosisList']", "diagnosisList");
			flag = true;
		}
		return flag;
	}

	public void checkTherapyDocumentationIncompletCheckBox() {
		CommonLib.clickButton(By.id("incompleteCertChkBox"));
	}

	public void clickCertificateReasonDelete() {
		if (CommonLib.isElementPresent(By.xpath("//div[@id='dijit_Dialog_2']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"), 6)) {
			CommonLib.clickButton(By.xpath("//div[@id='dijit_Dialog_2']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
		}

	}

	public boolean closeCertificateFlowsheets() {
		boolean check = false;
		if (CommonLib.isElementPresent(By.xpath("//div[@class='dojoxFloatingPaneTitle']/span[@class='dojoxFloatingCloseIcon']"), 8)) {
			CommonLib.clickButton(By.xpath("//div[@class='dojoxFloatingPaneTitle']/span[@class='dojoxFloatingCloseIcon']"));
			check = true;
		}
		return check;
	}

}
