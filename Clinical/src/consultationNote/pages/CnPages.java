package consultationNote.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;

public class CnPages {

	public void setConsultationFrame() {

		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'consultation-note.action?')]"));
		if (frame.size() > 0)
			CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public List<WebElement> getImpressionList() {
		return CommonLib.getElements(By.xpath("//*[@id='IMPGrid1']//div[2]//tbody/tr/td[4]"));
	}

	public void orderEntryLink() {
		CommonLib.clickButton(By.id("rmdId"));

	}

	public void setRecommendationFrame() {
		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("recEditor_iframe")));
	}

	public void setRecommendation(String data) {
		CommonLib.setData(By.id("dijitEditorBody"), data);
		HashTableRepository.setHash("recommendationCN", data);
	}

	public void validateConcludingStatement(String statement) {

		CommonLib.setFrameFromCurrent(CommonLib.getElement(By.id("consultStmtEditor_iframe")));
		try {
			WebElement e = CommonLib.getElement(By.id("dijitEditorBody"));
			System.out.println(e);
		} catch (Exception e) {

		}
		String actualStatement = CommonLib.getText(By.id("dijitEditorBody"));
		if (actualStatement.contains(statement))
			CustomReporter.MessageLogger("Concluding statement is as expected", status.Pass);
		else
			CustomReporter.MessageLogger("Concluding statement is not as expected", status.Fail);
	}

	public void openAllHnP() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@id='hpTree']//span[@data-dojo-attach-point='expandoNode']"));
		for (WebElement e : li) {
			e.click();
		}
	}

	public void getCheifComplaint() {
		CommonLib.clickButton(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='Chief Complaint']/../../span[1]"));
		List<WebElement> first = CommonLib.getElements(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='Chief Complaint']/../..//following-sibling::div//div[@class='hpTreeNode']"));
		if (!first.isEmpty()) {
			String cheifComplaint = CommonLib.getElement(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='Chief Complaint']/../..//following-sibling::div//div[@class='hpTreeNode']")).getText();
			HashTableRepository.setHash("cheifComplaint", cheifComplaint);
			// List<WebElement> li = CommonLib.getElements(By.xpath(
			// "//div[@id='hpTree']//span[text()='Chief
			// Complaint']/../..//div//div[@class='hpTreeNode']//div"));
			// String additionalComplaint = "";
			// for (WebElement e : li) {
			// additionalComplaint = e.getText();
			// }
			// HashTableRepository.setHash("additionalComplaint",
			// additionalComplaint);
			String chiefComplaintHnP = HashTableRepository.getHash("selectedChiefComplaint1");
			if (cheifComplaint.contains(chiefComplaintHnP)) {
				CustomReporter.MessageLogger("chief complaint verified as per H and P ", status.Pass);
			} else
				CustomReporter.MessageLogger("chief complaint not verified as per H and P ", status.Fail);

		}
	}

	public void getHistoryOfPresentIllness() {
		CommonLib.clickButton(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='History Of Present Illness']/../../span[1]"));
		List<WebElement> historyOfPresentIllnessElement = CommonLib.getElements(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='History Of Present Illness']/../..//following-sibling::div//div[@class='hpTreeNode']"));

		if (!historyOfPresentIllnessElement.isEmpty()) {
			String historyOfPresentIllness = CommonLib.getElement(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='History Of Present Illness']/../..//following-sibling::div//div[@class='hpTreeNode']")).getText();
			HashTableRepository.setHash("historyOfPresentIllness", historyOfPresentIllness);
		} else {
			HashTableRepository.setHash("historyOfPresentIllness", "");
		}
		if (HashTableRepository.getHash("historyOfPresentIllness").contains(HashTableRepository.getHash("selectedHistoryIllness"))) {

			CustomReporter.MessageLogger("HIP verified as per H and P ", status.Pass);
		} else
			CustomReporter.MessageLogger("HIP not verified as per H and P ", status.Fail);

	}

	public void getHistories(String name, String hashTable) {
		CommonLib.clickButton(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='" + name + "']/../../span[1]"));
		List<WebElement> baseNode = CommonLib.getElements(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='" + name + "']/../..//following-sibling::div//div[@class='hpTreeNode']"));
		String value = "";
		if (!baseNode.isEmpty()) {
			List<WebElement> pastMedicalHistoryList = CommonLib.getElements(By.xpath("//div[@id='hpTree']/div[3]/div/div[2]//span[text()='" + name + "']/../..//following-sibling::div//div[@class='hpTreeNode']//div"));

			for (WebElement e : pastMedicalHistoryList) {
				value = value + "_" + e.getText();
			}
			HashTableRepository.setHash(name, value);
		}
		if (HashTableRepository.getHash(hashTable).contains(value)) {

			CustomReporter.MessageLogger(name + " verified as per H and P ", status.Pass);
		} else
			CustomReporter.MessageLogger(name + " not verified as per H and P ", status.Fail);

		// return value;
	}

	// public void pastMedicalHistoryText() {
	// String pmh = getHistories("Past Medical History");
	// if (pmh.contains(HashTableRepository.getHash("diagnosisDate"))) {
	// CustomReporter.MessageLogger("PMH verified as per H and P ",
	// status.Pass);
	// } else
	// CustomReporter.MessageLogger("PMH verified as per H and P ",
	// status.Fail);
	// }
	//
	// public void pastSurgicalHistoryText() {
	// String pmh = getHistories("Past Surgical History");
	// if (pmh.contains(HashTableRepository.getHash("procedureDate"))) {
	// CustomReporter.MessageLogger("PMH verified as per H and P ",
	// status.Pass);
	// } else
	// CustomReporter.MessageLogger("PMH verified as per H and P ",
	// status.Fail);
	// }
	//
	// public void familyMedicalHistoryText() {
	// String pmh = getHistories("Family Medical History");
	// if (pmh.contains(HashTableRepository.getHash("selectedFamilyMember"))) {
	// CustomReporter.MessageLogger("FMH verified as per H and P ",
	// status.Pass);
	// } else
	// CustomReporter.MessageLogger("FMH verified as per H and P ",
	// status.Fail);
	// }

	// public void SocialHistoryText() {
	// String pmh = getHistories("Social History");
	// if (pmh.contains(HashTableRepository.getHash("selectedTobacco"))
	// && pmh.contains(HashTableRepository.getHash("selectedEducation"))
	// && pmh.contains(HashTableRepository.getHash("selectedAlcohol"))) {
	// CustomReporter.MessageLogger("SH verified as per H and P ", status.Pass);
	// } else
	// CustomReporter.MessageLogger("SH verified as per H and P ", status.Fail);
	// }

	public void getGeneralLabTestSummary() {
		List<WebElement> li = CommonLib.getElements(By.xpath("//div[@class='hpStudiesTreeNode']/div[1]//tbody//tr/td"));
		String generalLab = "";
		for (WebElement e : li) {
			generalLab = e.getText();
		}
		HashTableRepository.setHash("generalLab", generalLab);
	}

	public void setConcludingFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[contains(@src,'history-present-illness.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setLibraryOfStatement() {
		CommonLib.setBoolFields(true, By.xpath("//div[@id='General_Grid']//div[2]//table/tbody/tr/td/div[contains(@div,dijitCheckBox)]"));
		String libraryOfStatement = CommonLib.getElement(By.xpath("//div[@id='General_Grid']//div[2]//table/tbody/tr//td[3]/div")).getText();
		HashTableRepository.setHash("libraryOfStatement", libraryOfStatement);
		CommonLib.clickButton(By.xpath("//span[@id='submit_label']/div"));

	}

	public void setMainFrame() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void validateConcludingStatement() {
		// CommonLib.clickButton(By.xpath("//span[@id='close_label']"));
		setMainFrame();
		setConsultationFrame();
		List<WebElement> frame = CommonLib.getElements(By.xpath("//iframe[@id='consultStmtEditor_iframe']"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
		String txt = CommonLib.getElement(By.xpath("//div[@id='dijitEditorBody']")).getText();
		if (txt.contains(HashTableRepository.getHash("libraryOfStatement"))) {
			CustomReporter.MessageLogger("The concluding statement has been added", status.Pass);
		} else
			CustomReporter.MessageLogger("The concluding statement has not been added", status.Fail);

	}

	public void setListOfCategory() {
		CommonLib.setBoolFields(true, By.xpath("//div[@id='General_Grid']//div[2]//table/tbody/tr/td/div"));
		CommonLib.clickButton(By.xpath("//span[@id='submit_label']/div"));
	}

	public int size() {
		// List<WebElement> si =
		// CommonLib.getElements(By.xpath("//div[@id='consultNoteGrid']/div[2]//div//div//div//div/table"));
		List<WebElement> si = CommonLib.getElements(By.xpath("//div[@id='dojox_grid__View_1']//div//div//div//div"));
		return si.size();
	}

	public void addCN() {
		CommonLib.clickButton(By.id("add_label"));
	}

	public void setInfo() {

		CommonLib.setData(By.id("consultReason"), CommonLib.RandomText(1, 4));
		// List<WebElement> li =
		// CommonLib.getElements(By.xpath("//*[@id='hpSurgenList']"));
		// int x = li.size();
		if (CommonLib.getElement(By.xpath("//*[@id='hpSurgenList']")).isEnabled()) {
			try {
				// CommonLib.dojoListCount("hpSurgenList");
				// CommonLib.selectDojoDropDownByKeyDownNumber("hpSurgenList",
				// CommonLib.dojoListCount("hpSurgenList") - 2);
				CommonLib.selectDojoListValue("hpSurgenList", "");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CommonLib.clickButton(By.id("submitCN_label"));

		// int sizeAfter = size();
		// int j = Integer.parseInt(HashTableRepository.getHash("sizeBefore"));
		// int diff = sizeAfter - j;
		// if (diff == 1) {
		// CustomReporter.MessageLogger("Consultation Note is submitted",
		// status.Pass);
		// } else {
		// CustomReporter.MessageLogger("Consultation Note is not submitted",
		// status.Fail);
		// }
	}

	public void editCN() {
		CommonLib.getElement(By.xpath("//div[@id='consultNoteGrid']/div[2]//div//div//div//div//div[1]//table//tbody//tr")).click();
		CommonLib.clickButton(By.xpath("//*[@id=\"edit_label\"]"));
	}

	public void setCNFrame() {
		CommonLib.setFrame(By.xpath("//iframe[@id='" + Config.props.getProperty("MainFrame") + "']"));
		WebElement CNFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'consultation-note.')]"));
		CommonLib.setFrameFromCurrent(CNFrame);
	}

	public void signandSubmit() {
		CommonLib.clickButton(By.id("signAndSubmitCN_label"));
		try {
			CommonLib.clickButton(By.xpath("//span[text()='OK']"));
			CommonLib.getElement(By.xpath("//*[@id=\"CC_CN_titleBarNode\"]/div")).click();
			CommonLib.checkBoxSelect(By.id("CC_CNGrid1_rowSelector_-1"), 1);
			CommonLib.getElement(By.id("CC_CNGrid1_rowSelector_-1")).click();
			CommonLib.clickButton(By.id("signAndSubmitCN_label"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void verifySignandSubmit() {
		String text = CommonLib.getElement(By.xpath("//div[@id='consultNoteGrid']//div[2]//div//div//div//div//div[1]//table//tbody//tr//td[3]")).getText();
		if (text.isEmpty()) {
			CustomReporter.MessageLogger("Consultation Note is not signed and submitted", status.Fail);
		} else {
			CustomReporter.MessageLogger("Consultation Note is signed and submitted", status.Pass);
		}
	}

	public void clickDisplaySelected() {
		CommonLib.getElement(By.xpath("//div[@id='consultNoteGrid']/div[2]//div//div//div//div//div[1]//table//tbody//tr")).click();
		CommonLib.clickButton(By.id("display_label"));
	}

	public void amendCN() {
		CommonLib.clickButton(By.xpath("//*[@id=\"amend\"]"));
		CommonLib.getElement(By.xpath("//span[text()='OK']")).click();

	}

	public void verifyAmend() {
		WebElement kk = CommonLib.getElement(By.xpath("//div[@id='sectionBarline1']//span//b[2]"));
		String amendSigned = kk.getText();
		if (amendSigned.isEmpty()) {
			CustomReporter.MessageLogger("Consultation Note is not Amended", status.Fail);
		} else {
			CustomReporter.MessageLogger("Consultation Note is Amended", status.Pass);
		}
	}
}
