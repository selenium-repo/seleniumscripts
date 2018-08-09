package ClinicalFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.MenuNavigation;

public class PatientDiagnoses {
	static String txtDiagnoses = "";

	public void verifyDiagnosesFunctions() throws InterruptedException {
		CommonLib.setFrameToDefault();
		MenuNavigation.menuNav("Diagnoses");
		boolean result = addDiagnoses(false);
		if (result) {

			editDiagnoses();
			activateDiagnoses();
			resolveDiagnoses();
			inactivateDiagnoses();
			addCommentDiagnoses();
			attachOrderDiagnoses();
			attachMedDiagnoses();
			auditDiagnoses();
			summaryDiagnoses();
			diagnosesDiagnoses();
			filterDiagnoses();

		}
	}

	public static boolean addDiagnoses(boolean value) throws InterruptedException {
		int n = 2;
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		boolean result = false;
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.xpath("//span[@id='addDiagnose_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		if (value) {
			CommonLib.setfocusbyTab("//input[@id='diagnosisList' and contains(@class,'dijitReset dijitInputInner')]", 1);
			txtDiagnoses = CommonLib.setValueDojo("//input[@name='freeTextDiagnosis']", CommonLib.RandomText(1, 12));
		} else {
			txtDiagnoses = CommonLib.selDojoListValue("diagnosisList", 3);
			// System.out.println(txtDiagnoses);
		}
		CommonLib.setCurrentDate("diagnosisDate");
		String str = CommonLib.selectDojoDropDownByKeyDownNumber("diagnosisTime", 2);
		System.out.println(str);
		CommonLib.staticWait(2);
		CommonLib.selectDojoDropDownByKeyDownNumber("diagnosingPhysicianList", 2);
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitDiagnosis_label']"));
		while (n > 0) {
			if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 1)) {
				CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				txtDiagnoses = CommonLib.selDojoListValue("diagnosisList", n + 1);
				CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitDiagnosis_label']"));
				n = n + 1;
			} else {
				break;
			}
		}
		if (value) {
			// String errorMsg =
			// CommonLib.getText(By.xpath("//div[@id='fTPOverrideDialog']/div[2]/table/tbody/tr/td/label/b"));
			CommonLib.clickButton(By.xpath("//div[@id='fTPOverrideDialog']/div[2]/table[2]/tbody/tr/td/span/span/span/span[3]"));
			// System.out.println(errorMsg);
		}
		if (CommonLib.isElementPresent(By.xpath("//div[@id='carePlanTriggeredItemsDialog']"), 2)) {
			CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]/div"));
			// System.out.println("Care Plan Trigger is Present");
		}
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"), 6)) {
			WebElement wb = CommonLib.getElement(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"));
			wb.click();
			result = true;
			CustomReporter.MessageLogger("The Patient Diagnoses " + txtDiagnoses + "  has been added to the list successfully", status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to add the The Patient Diagnoses to the list, please try again!", status.Fail);
		}
		return result;

	}

	public static void editDiagnoses() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='editProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(3);
		CommonLib.selectDojoListValue("diagnosisEditReasonList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitDiagnosis_label']"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='carePlanTriggeredItemsDialog']"), 3)) {
			CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]/div"));
			// System.out.println("Care Plan Trigger is Present");
		}
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"), 3)) {

			WebElement wb = CommonLib.getElement(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"));
			wb.click();
			CustomReporter.MessageLogger("The Patient Diagnoses " + txtDiagnoses + "  has been edited to the list successfully", status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to edit the The Patient Diagnoses in the list, please try again!", status.Fail);
		}

	}

	public static void activateDiagnoses() {

		CommonLib.clickButton(By.xpath("//span[@id='activateProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(2);
		String actString = "An active problem already exists";
		String errorMsg = CommonLib.getText(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"));
		if (errorMsg.contains(actString)) {
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
			CustomReporter.MessageLogger("The Patient Diagnoses's Activate  button is working as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Diagnoses's Activate  button is not working properly", status.Fail);
		}

	}

	public static void resolveDiagnoses() {
		CommonLib.clickButton(By.xpath("//span[@id='resolveProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(2);
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class, 'HeaderResolved')]/span[3]/span[text()[contains(.,'" + txtDiagnoses + "')]]"), 6)) {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " got resolved by using the Resolve button, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " failed to got resolved by using the Resolve button, which is not as expected", status.Fail);
		}

	}

	public void inactivateDiagnoses() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='inactivateProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("inactivateEditReasonList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitInactivation_label']"));

		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'HeaderRemoved')]/span[3]/span[text()[contains(.,'" + txtDiagnoses + "')]]"), 6)) {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " got removed by using the Inactivate button, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " failed to get removed by using the Inactivate button, which is not as expected", status.Fail);

		}
	}

	public void addCommentDiagnoses() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='addComment_label' and contains(@class,'dijitButtonText')]"));
		String setcomment = CommonLib.setData(By.xpath("//div[@id='problemCommentDialog']/div[2]/form/table/tbody/tr/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitComment_label']"));
		CommonLib.clickButton(By.xpath("//span[@id='problemDetailTabs_tablist_problemComments']"));
		CommonLib.staticWait(2);
		String getcomment = CommonLib.getText(By.xpath("//div[@id='problemCommentsGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr[2]/td"));
		CommonLib.clickButton(By.xpath("//span[@id='problemDetailTabs_tablist_problemOrders']"));
		if (setcomment.contains(getcomment)) {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " has been added with the comment " + getcomment + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add Patient Diagnoses comment  " + setcomment + " , which is not as expected", status.Fail);
		}

	}

	public void attachOrderDiagnoses() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='problemDetailTabs_tablist_problemOrders']"));
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//span[@id='attachOrder_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("existingOrderList", "");
		CommonLib.setData(By.xpath("//div[@id='attachOrderDialog']/div[2]/form/table/tbody/tr[2]/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitAttach_label']"));
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"), 5)) {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " has been attached with the order, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to attach an order to  Patient Diagnoses " + txtDiagnoses + " , which is not as expected", status.Fail);
		}

	}

	public void attachMedDiagnoses() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='attachMedOrder_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("existingMedOrderList", "");
		CommonLib.setData(By.xpath("//div[@id='attachMedDialog']/div[2]/form/table/tbody/tr[2]/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitAttachMed']"));
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtDiagnoses + "')]]"), 5)) {
			CustomReporter.MessageLogger("The Patient Diagnoses  " + txtDiagnoses + " has been attached medication, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to attach an med order to Patient Diagnoses " + txtDiagnoses + " , which is not as expected", status.Fail);
		}

	}

	public void auditDiagnoses() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='viewAudit_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(3);
		WebElement iFramePatientAudit = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog' and contains(@src, 'PatientDiagnosesAudit')]"));
		CommonLib.setFrameFromCurrent(iFramePatientAudit);
		CommonLib.staticWait(1);
		String lastaudit = CommonLib.getText(By.xpath("//div[@id='auditGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[2]"));
		CommonLib.clickButton(By.xpath("//div[@id='vitalSignsGraphToolbar']/span/span/span/span[3]"));
		// CommonLib.setFrameToDefault();
		if (lastaudit != null) {
			CustomReporter.MessageLogger("The Patient Diagnoses " + txtDiagnoses + " has the last audit comment as: " + lastaudit + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the audit for " + txtDiagnoses + " Diagnoses, which is not as expected", status.Fail);
		}

	}

	public void summaryDiagnoses() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.clickButton(By.xpath("//span[@id='viewSummary_label' and contains(@class,'dijitButtonText')]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"));
			CustomReporter.MessageLogger("For Patient Diagnoses's Summary  the Problems Tree is displaying successfully, which is as expected", status.Pass);
		}
		// } else {
		// CustomReporter.MessageLogger("For Patient Diagnoses's Summary the
		// Problems Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'Diagnoses')] and contains(@class, 'dijitTreeLabel')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[text()[contains(.,'Diagnoses')] and contains(@class, 'dijitTreeLabel')]"));
			CustomReporter.MessageLogger("For Patient Diagnoses's Summary  the Diagnoses Tree is displaying successfully, which is as expected", status.Pass);
		}
		// } else {
		// CustomReporter.MessageLogger("For Patient Diagnoses's Summary the
		// Diagnoses Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'Procedures')] and contains(@class, 'dijitTreeLabel')]"), 1)) {
			CommonLib.clickButton(By.xpath("//span[text()[contains(.,'Procedures')] and contains(@class, 'dijitTreeLabel')]"));
			CustomReporter.MessageLogger("For Patient Diagnoses's Summary  the Procedures Tree is displaying successfully, which is as expected", status.Pass);
		}
		// } else {
		// CustomReporter.MessageLogger("For Patient Diagnoses's Summary the
		// Procedures Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }

	}

	public void diagnosesDiagnoses() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.clickButton(By.xpath("//span[@id='viewProblem_label' and contains(@class,'dijitButtonText')]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"));
			CustomReporter.MessageLogger("For Patient Diagnoses button, the Diagnoses Tree is displaying successfully, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("For Patient Diagnoses button, the Diagnoses Tree is not displaying successfully, which is not as expected", status.Fail);
		}
	}

	public void filterDiagnoses() {
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_E']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int diagnosesCountE = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Diagnoses's displayed for Enterprise Record is :" + diagnosesCountE + ", which is as expected", status.Information);
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_M']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int diagnosesCountM = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Diagnoses's displayed for Medical Record is :" + diagnosesCountM + ", which is as expected", status.Information);
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_A']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int diagnosesCountV = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Diagnoses's displayed for Current Visit is :" + diagnosesCountV + ", which is as expected", status.Information);
		if (diagnosesCountV != diagnosesCountE) {
			CustomReporter.MessageLogger("Number of Patient Diagnoses's displayed for Enterprise Record is :" + diagnosesCountE + " and for Current Visit is:" + diagnosesCountV + ", hence the filter button is working properly",
					status.Pass);
		} else {
			CustomReporter.MessageLogger("Number of Patient Diagnoses's displayed for Enterprise Record and Current Visit is the same :" + diagnosesCountV + ", hence the filter button is not working properly", status.Fail);
		}

	}

}
