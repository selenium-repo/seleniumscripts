package ClinicalFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class PatientProblems {

	static String txtProblem = "";

	public void verifyProblemsFunctions() throws InterruptedException {
		CommonLib.setFrameToDefault();
		MenuNavigation.menuNav("Problems");
		boolean result = addProblems(false);
		if (result) {

			editProblems();
			activateProblems();
			resolveProblems();
			inactivateProblems();
			addCommentProblems();
			attachOrderProblems();
			attachMedProblems();
			auditProblems();
			// ccdaImportProblems();
			summaryProblems();
			problemsProblems();
			filterProblems();

		}
	}

	public static boolean addProblems(boolean value) throws InterruptedException {
		int n = 2;
		boolean result = false;
		CommonLib.staticWait(3);
		CommonLib.clickButton(By.xpath("//span[@id='addProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		if (value) {
			CommonLib.setfocusbyTab("//input[@id='problemList' and contains(@class,'dijitReset dijitInputInner')]", 1);
			txtProblem = CommonLib.setValueDojo("//input[@name='freeTextProblem']", CommonLib.RandomText(1, 12));
		} else {
			txtProblem = CommonLib.selDojoListValue("problemList", 3);
			// System.out.println(txtProblem);

		}
		CommonLib.setCurrentDate("identificationDate");
		CommonLib.selectDojoDropDownByKeyDownNumber("identificationTime", 2);
		CommonLib.staticWait(2);
		CommonLib.selectDojoDropDownByKeyDownNumber("responsiblePhysicianList", 2);
		// CommonLib.selectDojoListValue("responsiblePhysicianList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitProblem']"));
		while (n > 0) {
			if (CommonLib.isElementPresent(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"), 1)) {
				CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
				txtProblem = CommonLib.selDojoListValue("problemList", n + 1);
				CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitProblem']"));
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
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtProblem + "')]]"), 3)) {
			WebElement wb = CommonLib.getElement(By.xpath("//span[text()[contains(.,'" + txtProblem + "')]]"));
			wb.click();
			result = true;
			CustomReporter.MessageLogger("The Patient Problem " + txtProblem + "  has been added to the list successfully", status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to add the The Patient Problem to the list, please try again!", status.Fail);
		}
		return result;

	}

	public static void editProblems() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='editProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(3);
		CommonLib.selectDojoListValue("problemEditReasonList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitProblem']"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='carePlanTriggeredItemsDialog']"), 5)) {
			CommonLib.clickButton(By.xpath("//div[@id='carePlanTriggeredItemsButtons']/table/tbody/tr/td[3]/span/span/span/span[3]/div"));
			// System.out.println("Care Plan Trigger is Present");
		}
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtProblem + "')]]"), 5)) {

			WebElement wb = CommonLib.getElement(By.xpath("//span[text()[contains(.,'" + txtProblem + "')]]"));
			wb.click();
			CustomReporter.MessageLogger("The Patient Problem " + txtProblem + "  has been edited to the list successfully", status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to edit the The Patient Problem in the list, please try again!", status.Fail);
		}

	}

	public static void activateProblems() {

		CommonLib.clickButton(By.xpath("//span[@id='activateProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(2);
		String actString = "An active problem already exists";
		String errorMsg = CommonLib.getText(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr/td"));
		if (errorMsg.contains(actString)) {
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog']/div[2]/table/tbody/tr[2]/td/span/span/span/span[3]"));
			CustomReporter.MessageLogger("The Patient Problem's Activate  button is working as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Problem's Activate  button is not working properly", status.Fail);
		}

	}

	public static void resolveProblems() {
		CommonLib.clickButton(By.xpath("//span[@id='resolveProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(2);
		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class, 'HeaderResolved')]/span[3]/span[text()[contains(.,'" + txtProblem + "')]]"), 6)) {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " got resolved by using the Resolve button, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " failed to got resolved by using the Resolve button, which is not as expected", status.Fail);
		}

	}

	public void inactivateProblems() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='inactivateProblem_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("inactivateEditReasonList", "");
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitInactivation_label']"));

		if (CommonLib.isElementPresent(By.xpath("//div[contains(@class,'HeaderRemoved')]/span[3]/span[text()[contains(.,'" + txtProblem + "')]]"), 5)) {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " got removed by using the Inactivate button, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " failed to get removed by using the Inactivate button, which is not as expected", status.Fail);

		}
	}

	public void addCommentProblems() throws InterruptedException {
		CommonLib.clickButton(By.xpath("//span[@id='addComment_label' and contains(@class,'dijitButtonText')]"));
		String setcomment = CommonLib.setData(By.xpath("//div[@id='problemCommentDialog']/div[2]/form/table/tbody/tr/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitComment_label']"));
		CommonLib.clickButton(By.xpath("//span[@id='problemDetailTabs_tablist_problemComments']"));
		CommonLib.staticWait(2);
		String getcomment = CommonLib.getText(By.xpath("//div[@id='problemCommentsGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr[2]/td"));
		if (setcomment.contains(getcomment)) {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " has been added with the comment " + getcomment + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add Patient Problem comment " + setcomment + " , which is not as expected", status.Fail);
		}

	}

	public void attachOrderProblems() throws InterruptedException {
		String getOrder = "";
		CommonLib.clickButton(By.xpath("//span[@id='problemDetailTabs_tablist_problemOrders']"));
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//span[@id='attachOrder_label' and contains(@class,'dijitButtonText')]"));
		String setOrder = CommonLib.selectDojoListValue("existingOrderList", "");
		CommonLib.setData(By.xpath("//div[@id='attachOrderDialog']/div[2]/form/table/tbody/tr[2]/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitAttach_label']"));
		CommonLib.staticWait(5);
		try {
			getOrder = CommonLib.getText(By.xpath("//div[@id='problemOrdersGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[3]"));
		} catch (Exception e) {
			CommonLib.clickButton(By.xpath("//span[@id='filterRxOrder_label']"));
			CommonLib.clickButton(By.xpath("//input[@id='rxOrderStatus_Complete']"));
			CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitRxFilter_label']"));
			try {
				getOrder = CommonLib.getText(By.xpath("//div[@id='problemOrdersGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[3]"));
			} catch (Exception e2) {
				CustomReporter.MessageLogger("Failed to attach an order to  Patient Problem " + setOrder + " , which is not as expected", status.Fail);
			}
		}

		if (setOrder.contains(getOrder)) {
			CustomReporter.MessageLogger("The Patient Problem's attached order: " + getOrder + " has been successfully verified in Problems Orders Grid, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to attach an order to  Patient Problem " + setOrder + " , which is not as expected", status.Fail);
		}

	}

	public void attachMedProblems() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='attachMedOrder_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.selectDojoListValue("existingMedOrderList", "");
		CommonLib.setData(By.xpath("//div[@id='attachMedDialog']/div[2]/form/table/tbody/tr[2]/td[2]/textarea"), CommonLib.RandomText(1, 12));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmitAttachMed']"));
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'" + txtProblem + "')]]"), 5)) {
			CustomReporter.MessageLogger("The Patient Problem  " + txtProblem + " has been attached medication, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to attach an med order to Patient Problem " + txtProblem + " , which is not as expected", status.Fail);
		}

	}

	public void auditProblems() throws InterruptedException {

		CommonLib.clickButton(By.xpath("//span[@id='viewAudit_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(3);
		WebElement iFramePatientAudit = CommonLib.getElement(By.xpath(".//iframe[@id='iFrameDialog' and contains(@src, 'PatientProblemsAudit')]"));
		CommonLib.setFrameFromCurrent(iFramePatientAudit);
		CommonLib.staticWait(1);
		String lastaudit = CommonLib.getText(By.xpath("//div[@id='auditGrid']/div[2]/div[2]/div/div/div/div/table/tbody/tr/td[2]"));
		CommonLib.clickButton(By.xpath("//div[@id='vitalSignsGraphToolbar']/span/span/span/span[3]"));
		// CommonLib.setFrameToDefault();
		if (lastaudit != null) {
			CustomReporter.MessageLogger("The Patient Problem " + txtProblem + " has the last audit comment as: " + lastaudit + ", which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the audit for " + txtProblem + " problem, which is not as expected", status.Fail);
		}

	}

	public void ccdaImportProblems() {
		CommonLib.clickButton(By.xpath("//span[@id='CCDImport_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(4);
		WebElement iFrameProblemCCDA = CommonLib.getElement(By.xpath("//iframe[contains(@class, 'continuity-of-care-document-import')]"));
		CommonLib.setFrameFromCurrent(iFrameProblemCCDA);
		String ProbCode = CommonLib.getElement(By.xpath("//table[@class='dojoxGridRowTable']/tbody/tr/td[3]")).getText();
		// System.out.println(ProbCode);
		CommonLib.clickButton(By.xpath("//span[@id='button.submit_label' and contains(@class,'dijitButtonText')]"));
		HashTableRepository.setHash(ProbCode, ProbCode);

	}

	public void summaryProblems() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.clickButton(By.xpath("//span[@id='viewSummary_label' and contains(@class,'dijitButtonText')]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"));
			CustomReporter.MessageLogger("For Patient Problem's Summary  the Problems Tree is displaying successfully, which is as expected", status.Pass);
		}
		// else {
		// CustomReporter.MessageLogger("For Patient Problem's Summary the
		// Problems Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'Diagnoses')] and contains(@class, 'dijitTreeLabel')]"), 3)) {
			CommonLib.clickButton(By.xpath("//span[text()[contains(.,'Diagnoses')] and contains(@class, 'dijitTreeLabel')]"));
			CustomReporter.MessageLogger("For Patient Problem's Summary  the Diagnoses Tree is displaying successfully, which is as expected", status.Pass);
		}
		// else {
		// CustomReporter.MessageLogger("For Patient Problem's Summary the
		// Diagnoses Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }
		if (CommonLib.isElementPresent(By.xpath("//span[text()[contains(.,'Procedures')] and contains(@class, 'dijitTreeLabel')]"), 1)) {
			CommonLib.clickButton(By.xpath("//span[text()[contains(.,'Procedures')] and contains(@class, 'dijitTreeLabel')]"));
			CustomReporter.MessageLogger("For Patient Problem's Summary  the Procedures Tree is displaying successfully, which is as expected", status.Pass);
		}
		// else {
		// CustomReporter.MessageLogger("For Patient Problem's Summary the
		// Procedures Tree is not displaying successfully, which is not as
		// expected", status.Fail);
		// }

	}

	public void problemsProblems() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
		CommonLib.clickButton(By.xpath("//span[@id='viewProblem_label' and contains(@class,'dijitButtonText')]"));
		if (CommonLib.isElementPresent(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"), 4)) {
			CommonLib.clickButton(By.xpath("//div[@id='problemsTree']/div[3]/div/div[2]/div/div/span[3]/span[2]"));
			CustomReporter.MessageLogger("For Patient Problem's Problem button, the Problems Tree is displaying successfully, which is as expected", status.Pass);
		} else {
			CustomReporter.MessageLogger("For Patient Problem's Problem button, the Problems Tree is not displaying successfully, which is not as expected", status.Fail);
		}
	}

	public void filterProblems() {
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_E']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int problemCountE = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Problem's displayed for Enterprise Record is :" + problemCountE + ", which is as expected", status.Information);
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_M']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int problemCountM = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Problem's displayed for Medical Record is :" + problemCountM + ", which is as expected", status.Information);
		CommonLib.clickButton(By.xpath("//span[@id='filter_label' and contains(@class,'dijitButtonText')]"));
		CommonLib.staticWait(1);
		CommonLib.clickButton(By.xpath("//div[contains(@class,'dijitRadio')]/input[@id='showScope_A']"));
		CommonLib.clickButton(By.xpath("//span[@id='buttonSubmit_label' and contains(@class,'dijitButtonText')]"));
		int problemCountV = CommonLib.getElements(By.xpath("//span[text()[contains(.,'+')] and contains(@class,'dijitExpandoText')]")).size();
		CustomReporter.MessageLogger("Number of Patient Problem's displayed for Current Visit is :" + problemCountV + ", which is as expected", status.Information);
		if (problemCountV != problemCountE) {
			CustomReporter.MessageLogger("Number of Patient Problem's displayed for Enterprise Record is :" + problemCountE + " and for Current Visit is:" + problemCountV + ", hence the filter button is working properly", status.Pass);
		} else {
			CustomReporter.MessageLogger("Number of Patient Problem's displayed for Enterprise Record and Current Visit is the same :" + problemCountV + ", hence the filter button is not working properly", status.Fail);
		}

	}

}
