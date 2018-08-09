package DischargeSummaryPhyscDoc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;
import consultationNote.pages.CnPages;

public class DischargeSummaryPhyscDocPage {
	CnPages cn = new CnPages();

	public void menuNav() {
		MenuNavigation.menuNav("DischargeSummaryPhysDoc");
	}

	public void setMainFrame() {
		CommonLib.setFrame("mainWorkFrame0");
	}

	public void setDischargeCondition() {

		// CommonLib.getElement(By.xpath("//span[text()='Discharge
		// Condition']")).click();
		try {
			String dsCondition = CommonLib.selectDojoListValue("dischargeConditionList", "");
			HashTableRepository.setHash("dsCondition", dsCondition);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setActivities() {
		CommonLib.getElement(By.id("chiefComplaintBtn_label")).click();
	}

	public int randomSelector(List<WebElement> li) {
		boolean a = true;
		int size = li.size();
		int random = CommonLib.generateRandom(0, size - 1);
		while (a) {
			if (!li.get(random).isSelected()) {
				li.get(random).click();
				a = false;
			} else {
				random = CommonLib.generateRandom(0, size - 1);
			}

		}
		return random;
	}

	public void getActivityList() {
		List<WebElement> li = CommonLib
				.getElements(By.xpath("//div[@id='activityContainer']//div[@id='activity_div']/div"));
		List<WebElement> ti = CommonLib
				.getElements(By.xpath("//div[@id='activityContainer']//div[@id='activity_div']/label"));
		int random = randomSelector(li);

		String activity = ti.get(random).getText();
		HashTableRepository.setHash("activity", activity);

		CommonLib.clickButton(By.id("buttonSubmitChiefComp_label"));
	}

	public void setDiets() {
		CommonLib.getElement(By.id("dischargeDietBtn_label")).click();

		List<WebElement> di = CommonLib.getElements(By.xpath("//div[@id='dietContainer']//div[@id='diet_div']//div"));
		List<WebElement> si = CommonLib.getElements(By.xpath("//div[@id='dietContainer']//div[@id='diet_div']//label"));
		int randomDiet = randomSelector(di);
		String dietlistName = si.get(randomDiet).getText();
		HashTableRepository.setHash("dietlistName", dietlistName);

		CommonLib.clickButton(By.id("buttonSubmit_label"));
	}

	public void setPatientEducationDetail() {

		WebElement PatientEducationDetail = CommonLib.getElement(By
				.xpath("//div[@id='DIV_INS']/form[@id='instructions']//div[@id='instructionsList3']/div[2]/div/input"));
		PatientEducationDetail.click();
		String selectedPatientEducationDetail = PatientEducationDetail.getText();
		HashTableRepository.setHash("selectedPatientEducationDetail", selectedPatientEducationDetail);
	}

	public void setAdditionalInstruction() {
		String comment = CommonLib.setData(By.id("insEditor"), CommonLib.RandomText(1, 5));
		HashTableRepository.setHash("additionalInstruction", comment);
	}

	public void clickSubmit() {
		if (CommonLib.getElement(By.xpath("//*[@id='hpSurgenList']")).isEnabled()) {
			try {
				CommonLib.selectDojoListValue("hpSurgenList", "");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CommonLib.clickButton(By.id("submit_label"));
	}

	public void checkforErrorConsulationNote() {
		cn.setConsultationFrame();
		List<WebElement> li = CommonLib.getElements(By.xpath("//span[text()='OK']"));
		if (li.size() != 0) {
			CommonLib.clickButton(By.xpath("//span[text()='OK']"));
		}
		if (CommonLib.getElement(By.xpath("//span[@class='iframeDialogTitle']")).getText()
				.equals("Consultation Note")) {
			CustomReporter.MessageLogger("The title matches", status.Pass);
		} else
			CustomReporter.MessageLogger("The title doesnt match", status.Fail);
		CommonLib.clickButton(By.xpath("//span[@title='Close']"));
		setMainFrame();
	}

	public void setComment(String field) {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[@id='" + field + "Editor_iframe']"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
		String text = CommonLib.RandomText(1, 4);
		WebElement editor = CommonLib.getElement(By.xpath("//div[@id='dijitEditorBody']"));
		editor.sendKeys(text);
		// CommonLib.setData(By.xpath("//div[@id='dijitEditorBody']"), text);
		HashTableRepository.setHash(field, text);
		setMainFrame();
	}

	public void setFollowUp() {
		List<WebElement> li = CommonLib
				.getElements(By.xpath("//div[@id='followUpId']/div[@id='flwEditor']//input[@name='followupstmtRad']"));
		List<WebElement> li2 = CommonLib.getElements(By.xpath(
				"//div[@id='followUpId']/div[@id='flwEditor']//div[@class='statement']/div[@class='textStrDiv']"));
		int random = CommonLib.generateRandom(0, li.size() - 1);
		if (!li.get(random).isSelected())
			li.get(random).click();
		String selected = li2.get(random).getText();
		HashTableRepository.setHash("SelectedFollowUp", selected);
		HashTableRepository.setHash("FollowUpIndex", Integer.toString(random));

	}

	public void followupVerification() {
		List<WebElement> li = CommonLib.getElements(
				By.xpath("//div[@id='followUpId']/div[@id='flwEditor']//input[@name='followupstmtRad']/.."));
		if (li.get(Integer.parseInt(HashTableRepository.getHash("FollowUpIndex"))).getAttribute("class")
				.contains("dijitChecked")) {
			
			CustomReporter.MessageLogger("Followup added", status.Pass);
		} else
			CustomReporter.MessageLogger("Followup not added", status.Fail);
	}

	public void verifyInstructions() {
		String dischargeActivity = CommonLib.getElement(By.xpath("//input[@id='dischargeActivityList']"))
				.getAttribute("value");
		String diet = CommonLib.getElement(By.xpath("//input[@id='dischargeDietList']")).getAttribute("value");
		String inst = CommonLib.getElement(By.id("insEditor")).getText();
		
		if (dischargeActivity.contains(HashTableRepository.getHash("activity"))
				&& inst.contains(HashTableRepository.getHash("additionalInstruction"))
				&& diet.contains(HashTableRepository.getHash("dietlistName"))) {
			CustomReporter.MessageLogger("Activity added", status.Pass);
		} else
			CustomReporter.MessageLogger("Activity NOT added", status.Fail);
	}

	public void submitAndVerify() {
		clickSubmit();
		 followupVerification();
		verifyInstructions();

	}

}
