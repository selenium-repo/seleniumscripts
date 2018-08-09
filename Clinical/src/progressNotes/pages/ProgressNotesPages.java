package progressNotes.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import historyAndPhysical.pages.HistoryAndPhysicial;

public class ProgressNotesPages {

	public void clickEhrArrow() {
		CommonLib.clickButton(By.id("EhrPN_arrow"));
	}

	public void clickEhr(int i) {
		WebElement selectedEhR = CommonLib.getElement(By.xpath("//div[@id='EhrPN_dropdown']//tbody/tr[" + i + "]/td[2]"));
		HashTableRepository.setHash("selectedEHR", selectedEhR.getText());
		selectedEhR.click();
	}

	public List<WebElement> getEHRList() {
		return CommonLib.getElements(By.xpath("//div[@id='EhrPN_dropdown']//tbody/tr"));
	}

	public void clickAddNewNode() {
		CommonLib.clickButton(By.id("addNote_label"));
	}

	public void gotoProgressNotes() {

		MenuNavigation.menuNav("ProgressNotes");
	}

	public void closeFlowSheet() {
		try {
			CommonLib.clickButton(By.xpath("//div[contains(@class,'jsPanel-btn-close')]"));
		} catch (Exception e) {

		}
	}

	public String getFlowSheetTitle() {
		return CommonLib.getText(By.className("jsPanel-title"));
	}

	public void validateAmmend() {
		String assesment = CommonLib.getText(By.id("assessmentEditorSigned"));
		if (assesment.contains("Amended By"))
			CustomReporter.MessageLogger("Ammended and Signed successfully", status.Pass);
		else
			CustomReporter.MessageLogger("Ammend and sign in not successfull", status.Fail);

		String plan = CommonLib.getText(By.id("planEditorSigned"));
		if (plan.contains("Amended By"))
			CustomReporter.MessageLogger("Ammended and Signed successfully", status.Pass);
		else
			CustomReporter.MessageLogger("Ammend and sign in not successfull", status.Fail);

		String subjective = CommonLib.getText(By.id("subEditorSigned"));
		if (subjective.contains("Amended By"))
			CustomReporter.MessageLogger("Ammended and Signed successfully", status.Pass);
		else
			CustomReporter.MessageLogger("Ammend and sign in not successfull", status.Fail);

		String objective = CommonLib.getText(By.id("losEditorSigned"));
		if (objective.contains("Amended By"))
			CustomReporter.MessageLogger("Ammended and Signed successfully", status.Pass);
		else
			CustomReporter.MessageLogger("Ammend and sign in not successfull", status.Fail);

	}

	public void testAssesment() {
		CommonLib.clickButton(By.id("assessments_label"));
		String title = CommonLib.getText(By.className("jsPanel-title"));
		if (title.equalsIgnoreCase("Patient Assessment"))
			CustomReporter.MessageLogger("Assesment pop is as expected.", status.Pass);
		else
			CustomReporter.MessageLogger("Assesment pop up is not as expected", status.Fail);

		closeFlowSheet();

	}

	public void amendTest() {
		HistoryAndPhysicial objHis = new HistoryAndPhysicial();
		if (!objHis.isGridOpen("ASSMT"))
			objHis.clickGrid("ASSMT");

		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("assessmentEditor_iframe")));
		HashTableRepository.setHash("ammendAssesment", CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("ammendAssesment"));
		CommonLib.setFrame("mainWorkFrame0");

		if (!objHis.isGridOpen("PLN"))
			objHis.clickGrid("PLN");

		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("planEditor_iframe")));
		HashTableRepository.setHash("ammendPlan", CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("ammendPlan"));
		CommonLib.setFrame("mainWorkFrame0");
		if (!objHis.isGridOpen("SUB"))
			objHis.clickGrid("SUB");
		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("subEditor_iframe")));
		HashTableRepository.setHash("ammendSubjective", CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("ammendSubjective"));
		CommonLib.setFrame("mainWorkFrame0");
		if (!objHis.isGridOpen("OBJ"))
			objHis.clickGrid("OBJ");
		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("losEditor_iframe")));
		HashTableRepository.setHash("ammendObjective", CommonLib.RandomText(1, 15));
		CommonLib.getElement(By.id("dijitEditorBody")).sendKeys(HashTableRepository.getHash("ammendObjective"));
		CommonLib.setFrame("mainWorkFrame0");

	}

	public void clickOk() {
		try {
			CommonLib.clickButton(By.xpath("//span[text()='OK']"));
		} catch (Exception e) {
		}
	}

	public String getSignSubmitStamp() {
		return CommonLib.getText(By.id("sectionBarText1"));
	}

	public String getProgressNoteText(String dateTime) {

		String text = "";
		try {
			text = CommonLib.getText(By.xpath("//span[contains(text(),'" + dateTime + "')]"));
		} catch (StaleElementReferenceException e) {
			text = CommonLib.getText(By.xpath("//span[contains(text(),'" + dateTime + "')]"));
		}
		return text;
	}

	public void checkSignCheckBoxes() {
		CommonLib.checkbox_set("//input[@id='chkOBJ']", true);
		CommonLib.checkbox_set("//input[@id='chkASSMT']", true);

	}

	public void selectFlowSheet(int i) {
		CommonLib.clickButton(By.xpath("//div[@id='dijit_form_ComboButton_0_dropdown']/table//tr[" + i + "]/td[2]"));
	}

	public List<WebElement> getFlowSheets() {
		return CommonLib.getElements(By.xpath("//div[@id='dijit_form_ComboButton_0_dropdown']/table//tr/td[2]"));
	}

	public void clickSignAndSubmit() {
		CommonLib.clickButton(By.id("signAndSubmitPN_label"));
	}

	public void clickFlowSheetArrow() {
		CommonLib.clickButton(By.xpath("//td[@id='dijit_form_ComboButton_0_arrow']"));
	}

	public List<WebElement> getAssesmentList() {
		return CommonLib.getElements(By.xpath("//div[@id='ASSMTGrid1']/div[2]//div[contains(@class,'dojoxGridRow')]//tr/td[5]"));
	}

	public List<WebElement> getPNList() {
		return CommonLib.getElements(By.xpath("//div[@id='progressNotesTree']//div[@class='dijitTreeContainer']//span[contains(@id,'dijit__TreeNode_')]"));
	}

	public String getSubmitText() {
		return CommonLib.getText(By.id("headingRHS"));
	}

	public void submit() {
		CommonLib.clickButton(By.id("submitPN_label"));
	}
}
