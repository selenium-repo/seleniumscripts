package SocialHistory.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

//import consultationNote.pages.CnPages;
public class SocialHistoryPage {

	public void setMainFrame() {
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void actions(String action) {
		CommonLib.clickButton(By.xpath("//*[@id='" + action.replace("-", "").toLowerCase() + "_label'  and (contains(text(),'" + action + "'))]"));

		// span[@id='lblPassword' and (contains(text(),'Password:'))]
	}

	public void clickGrid(String text) {
		CommonLib.clickButton(By.xpath("//*[@id='" + text + "_titleBarNode']"));
	}

	public boolean isGridOpen(String text) {
		boolean isOpen = false;
		String classText = CommonLib.getElement(By.xpath("//*[@id='" + text + "_titleBarNode']")).getAttribute("class");
		if (classText.contains("dijitOpen"))
			isOpen = true;

		return isOpen;
	}

	public String getTobaccoText(String index) {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid" + index + "']//tbody//td"));
	}

	public void setTobaccoFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//*[contains(@src,'substance-properties.action?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setAuditFrame() {
		List<WebElement> frame = CommonLib.getElements(By.xpath("//*[contains(@src,'/OptimumClinicals/SocialHistoryAudit.do?')]"));
		CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void setGeneralFrame() {
		List<WebElement> iframe = CommonLib.getElements(By.xpath("//iframe[contains(@src,'social-history.action?')]"));
		CommonLib.setFrameFromCurrent(iframe.get(iframe.size() - 1));
	}

	public String getSocialText() {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid1']//tbody//td"));
	}

	public void setAlcohol(int a, String AlcoholUse) {
		if (a == 1) {
			CommonLib.clickButton(By.xpath("//*[@id='" + AlcoholUse + "A']"));
			try {
				String selectedAlcohol = CommonLib.selectDojoListValue("alcoholChoice1", "");
				HashTableRepository.setHash("selectedAlcohol", selectedAlcohol);
			} catch (Exception e) {
			}
			try {
				String selectedAlcohol = CommonLib.selectDojoListValue("alcoholChoiceF1", "");
				HashTableRepository.setHash("selectedAlcohol", selectedAlcohol);
			} catch (Exception e) {
			}
			setAlAmount();
		} else {
			CommonLib.clickButton(By.xpath("//*[@id='" + AlcoholUse + "A']"));
			CommonLib.setData(By.id("commentA"), "some random text");
		}
		submitAl();
	}

	public void submitDrugSocial() {
		CommonLib.clickButton(By.id("submitS_label"));
	}

	public void setDrug(int a, String DrugUse) {
		if (a == 1) {
			CommonLib.clickButton(By.xpath("//*[@id='" + DrugUse + "S']"));
			// CommonLib.clickButton(By.xpath("//[@id='" + DrugUse + "S']"));
			try {
				String selectedSocialDrug = CommonLib.selectDojoListValue("socialRecChoice1", "");
				HashTableRepository.setHash("selectedSocialDrug", selectedSocialDrug);
			} catch (Exception e) {
			}
			try {
				String selectedSocialDrug = CommonLib.selectDojoListValue("socialRecChoiceF1", "");
				HashTableRepository.setHash("selectedSocialDrug", selectedSocialDrug);
			} catch (Exception e) {
			}
			setDrugAmount();

		} else {
			CommonLib.clickButton(By.xpath("//*[@id='" + DrugUse + "S']"));
			CommonLib.setData(By.id("commentS"), "some random text");
		}
		submitDrugSocial();
	}

	public void setDrugAmount() {
		try {
			CommonLib.selectDojoListValue("reasonListS", "");
		} catch (Exception e) {

		}
		HashTableRepository.setHash("socialDrugAmount", Integer.toString(CommonLib.generateRandom(1, 10)));
		try {
			CommonLib.setData(By.id("amountS1"), HashTableRepository.getHash("socialDrugAmount"));
		} catch (Exception e) {
			CommonLib.setData(By.id("amountSF1"), HashTableRepository.getHash("socialDrugAmount"));
		}

	}

	public void submitAl() {
		CommonLib.clickButton(By.id("submitA_label"));
	}

	public void setAlAmount() {
		try {
			CommonLib.selectDojoListValue("reasonListA", "");
		} catch (Exception e) {

		}
		HashTableRepository.setHash("socialDrugAmount", Integer.toString(CommonLib.generateRandom(1, 10)));
		try {
			CommonLib.setData(By.id("amountA1"), HashTableRepository.getHash("socialDrugAmount"));
		} catch (Exception e) {
			CommonLib.setData(By.id("amountAF1"), HashTableRepository.getHash("socialDrugAmount"));
		}

		// try {
		// CommonLib.selectDojoListValue("reasonListA", "");
		// } catch (Exception e) {
		//
		// }
		//
		// HashTableRepository.setHash("AlAmount",
		// Integer.toString(CommonLib.generateRandom(1, 10)));
		// CommonLib.setData(By.id("amountAF1"),
		// HashTableRepository.getHash("AlAmount"));
	}

	public void setGeneralEducation() {
		try {
			CommonLib.getElement(By.xpath("//input[@id='citizenshipList']"));
			String education = CommonLib.selectDojoListValue("citizenshipList", "");
			HashTableRepository.setHash("selectedEducation", education);
		} catch (Exception e) {
		}
	}

	public void setGeneralSocial() {
		setGeneralFrame();
		HashTableRepository.setHash("numChiildren", Integer.toString(CommonLib.generateRandom(1, 5)));
		CommonLib.setData(By.id("numOfChildren"), HashTableRepository.getHash("numChiildren"));
		try {
			String education = CommonLib.selectDojoListValue("educationList", "");
			HashTableRepository.setHash("selectedEducation", education);
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("submit_label"));
	}

	public void submitReview() {
		CommonLib.clickButton(By.xpath("//div[text()='Submit']"));
	}

	public void setSocialHistory(String history) {
		CommonLib.clickButton(By.id("SHContent" + history));
	}

	public String getTobaccoText() {
		return CommonLib.getText(By.xpath("//*[@id='SHGrid2']//tbody//td"));
	}

	public void setTobaccco(int a, String TobaccoUse) {
		if (a == 1) {
			CommonLib.clickButton(By.xpath("//*[@id='" + TobaccoUse + "']"));
			;
			try {
				String selectedTobacco = CommonLib.selectDojoListValue("tobaccoChoice1", "");
				HashTableRepository.setHash("selectedTobacco", selectedTobacco);
			} catch (Exception e) {
			}
			try {
				String selectedTobacco = CommonLib.selectDojoListValue("tobaccoChoiceF1", "");
				HashTableRepository.setHash("selectedTobacco", selectedTobacco);
			} catch (Exception e) {
			}
			setTobaccoAmount();

		} else {
			CommonLib.clickButton(By.xpath("//*[@id='" + TobaccoUse + "']"));
			CommonLib.setData(By.id("comment"), "some random text");
		}
		CommonLib.clickButton(By.id("submitT_label"));

		// WebElement frame =
		// CommonLib.getElement(By.xpath("//*[contains(@src,'substance-properties.action?')]"));
		// CommonLib.setFrameFromCurrent(frame);
		// CommonLib.clickButton(By.id("formerUse"));
		// try {
		// String tobacco = CommonLib.selectDojoListValue("tobaccoChoiceF1",
		// "");
		// HashTableRepository.setHash("selectedTobacco", tobacco);
		// } catch (Exception e) {
		// }
		// String text = CommonLib.RandomText(2, 1);
		// CommonLib.setData(By.id("amountF1"), text);
		// HashTableRepository.setHash("AmountSet", text);
		// CommonLib.setData(By.id("packYearsF1"), CommonLib.RandomText(2, 2));
		// CommonLib.clickButton(By.id("submitT_label"));

	}

	public void setTobaccoAmount() {
		try {
			CommonLib.selectDojoListValue("reasonList", "");
		} catch (Exception e) {

		}
		HashTableRepository.setHash("socialDrugAmount", Integer.toString(CommonLib.generateRandom(1, 10)));
		try {
			CommonLib.setData(By.id("amount1"), HashTableRepository.getHash("socialDrugAmount"));
		} catch (Exception e) {
			CommonLib.setData(By.id("amountF1"), HashTableRepository.getHash("socialDrugAmount"));
		}

	}

	public void setTobaccoEdit() {
		try {
			CommonLib.selectDojoListValue("reasonList", "");
			String text = CommonLib.RandomText(2, 1);
			CommonLib.setData(By.id("amountF1"), text);
			HashTableRepository.setHash("AmountSet", text);

		} catch (Exception e) {

		}
		clickSubmitTobacco();
	}

	public void clickSubmitTobacco() {
		CommonLib.clickButton(By.xpath("//div[text()='Submit']"));
	}

	public ArrayList<WebElement> getCheckBoxesForHPI() {

		ArrayList<WebElement> cb = (ArrayList<WebElement>) CommonLib
				.getElements(By.xpath("//div[@id='statementHolder']//div[@class='dijitTitlePane']//div[contains(@id,'_Grid')]//div[contains(@class,'dojoxGridRowbar')]//div[contains(@class,'dijitCheckBox')]"));

		// ArrayList<WebElement> checkBoxes = (ArrayList<WebElement>) CommonLib
		// .getElements(By.xpath("//div[@class='dojoxGridCheckSelector
		// dijitReset
		// dijitInline dijitCheckBox']"));
		return cb;
	}

	public void menuNav() {
		MenuNavigation.menuNav("SocialHistory");
	}

	public void clickPrint() {
		CommonLib.clickButton(By.id("printItem_label"));
	}

	public void clickStatus(String status) {
		CommonLib.clickButton(By.xpath("//*[@id='" + status + "']//div//div//span[3]"));
	}

	public void clickAction(String status, int position, String action) {
		try {
			CommonLib.clickButton(By.xpath("//div[@id='" + status + "']//div[2]//div//div//table//tbody//tr[1]//td//table//tr//td[" + position + "]//a//img[@src='/OptimumClinicals/Images/png/" + action + ".png']"));

		} catch (Exception e) {
			CommonLib.clickButton(By.xpath("//*[@id=\'" + status + "_pane']/table/tbody/tr[1]/td/table/tbody/tr//td[" + position + "]//a//img[@src='/OptimumClinicals/Images/png/" + action + ".png']"));

		}
		// CommonLib.clickButton(By.xpath("//*[@id=\"alcoholSection_pane\"]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/a/img"));
	}

	public void clickFirstRow(String status) {
		CommonLib.clickButton(By.xpath("//div[@id='" + status + "']//div[2]//div//div//div//div[2]//div//div//div//div//div[1]//table//tbody//tr//td[1]"));
	}

	public void editSocialDrug() {
		try {
			CommonLib.selectDojoListValue("reasonListS", "");
			String text = CommonLib.RandomText(2, 1);
			CommonLib.setData(By.id("amountS1"), text);
			HashTableRepository.setHash("AmountSet", text);

		} catch (Exception e) {

		}
		submitDrugSocial();
	}

	public void editAlcohol() {
		try {
			CommonLib.selectDojoListValue("reasonListA", "");
			String text = CommonLib.RandomText(2, 1);
			CommonLib.setData(By.id("amountA1"), text);
			HashTableRepository.setHash("AmountSet", text);

		} catch (Exception e) {

		}
		submitAl();
	}

	public void rowCount(String status) {
		List<WebElement> addList = CommonLib.getElements(By.xpath("//div[@id='" + status + "']//div[2]//div//div//div//div[2]//div[contains(@class,'dojoxGridRow')]"));
		int listSize = addList.size();
		HashTableRepository.setHash("listSize", Integer.toString(listSize));
	}

	public void clickAuditClose() {
		CommonLib.clickButton(By.id("close_label"));
	}

	public void verifyAdd(String Status) {
		int listSizeOld = Integer.parseInt(HashTableRepository.getHash("listSize"));
		List<WebElement> addListNew = CommonLib.getElements(By.xpath("//div[@id='" + Status + "']//div[2]//div//div//div//div[2]//div[contains(@class,'dojoxGridRow')]"));
		int listSizeNew = addListNew.size();
		if (listSizeNew > listSizeOld) {
			CustomReporter.MessageLogger("Add functionality of " + Status + " is working properly.", status.Pass);

		} else {
			CustomReporter.MessageLogger("Add functionality of " + Status + " is not working properly.", status.Fail);
		}
	}

	public void verifyEdit(String Type) {
		String a = CommonLib.getElement(By.xpath("//div[@id='pageContainer']//div//div[@id='auditGrid']//div[2]//div[2]//table//tbody//tr//td[6]")).getText();

		if (a == null) {
			CustomReporter.MessageLogger("" + Type + " was not edited successfully.", status.Fail);

		} else {
			CustomReporter.MessageLogger("" + Type + " was edited successfully.", status.Pass);
		}
		clickAuditClose();
	}

	public void delete() {
		// setMainFrame();
		CommonLib.clickButton(By.xpath("//span[contains(text(),'OK')]"));
		setDeleteFrame();
		WebElement we = CommonLib.getElement(By.xpath("//*[@id='selectReason']/table/tbody/tr[2]/td[1]"));
		we.sendKeys(Keys.TAB);

		we = CommonLib.getElement(By.xpath("//*[@id='reasonList']/tbody/tr/td[1]"));
		we.sendKeys(Keys.ENTER);
		we.sendKeys(Keys.DOWN);
		CommonLib.clickButton(By.xpath("//*[text()='Data Entry Error']"));
		CommonLib.clickButton(By.xpath("//*[@name='button.Submit']"));

	}

	public void setDeleteFrame() {

		WebElement CophysicianFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/select-reason.action?')][last()]"));
		CommonLib.setFrameFromCurrent(CophysicianFrame);

		// List<WebElement> frame =
		// CommonLib.getElements(By.xpath("//*[contains(@src,'/OptimumClinicals/select-reason.action?')]"));
		// CommonLib.setFrameFromCurrent(frame.get(frame.size() - 1));
	}

	public void verifydelete(String Status) {
		int listSizeOld = Integer.parseInt(HashTableRepository.getHash("listSize"));
		List<WebElement> addListNew = CommonLib.getElements(By.xpath("//div[@id='" + Status + "']//div[2]//div//div//div//div[2]//div[contains(@class,'dojoxGridRow')]"));
		int listSizeNew = addListNew.size();
		if (listSizeNew < listSizeOld) {
			CustomReporter.MessageLogger("Delete functionality of " + Status + " is working properly.", status.Pass);

		} else {
			CustomReporter.MessageLogger("Delete functionality of " + Status + " is not working properly.", status.Fail);
		}
	}

	public void setGerneral() {
		String newEdu = null;
		String newOccu = null;
		String oldEdu = CommonLib.getElement(By.id("educationList")).getText();
		String oldOccu = CommonLib.getElement(By.id("occupHistoryList")).getText();
		try {
			CommonLib.clickButton(By.id("livesAloneY"));
			CustomReporter.MessageLogger("Lives alone set as YES.", status.Pass);
		} catch (Exception e) {
			CommonLib.clickButton(By.id("livesAloneN"));
			CustomReporter.MessageLogger("Lives alone set as NO.", status.Pass);
		}
		try {
			CommonLib.selectDojoListValue("educationList", "");

			newEdu = CommonLib.getElement(By.id("educationList")).getText();

		} catch (Exception e) {
		}
		try {
			CommonLib.selectDojoListValue("occupHistoryList", "");

			newOccu = CommonLib.getElement(By.id("occupHistoryList")).getText();

		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("submit_label"));
		MenuNavigation.menuNav("KardexPlus");
		MenuNavigation.menuNav("SocialHistory");
		if (newEdu.equals(oldEdu)) {
			CustomReporter.MessageLogger("Add functionality of General Section is working properly.", status.Pass);
		} else if (newOccu.equals(oldOccu)) {
			CustomReporter.MessageLogger("Add functionality of General Section is working properly.", status.Pass);
		} else {
			CustomReporter.MessageLogger("Add functionality of General Section is not working properly.", status.Fail);
		}

	}

	public void clickPrintAll() {
		CommonLib.clickButton(By.id("printAllItem_label"));
	}

	public void clickAudit() {
		CommonLib.clickButton(By.id("viewAudit_label"));
	}

	public void auditRowCount() {

		List<WebElement> a = CommonLib.getElements(By.xpath("//div[@id='auditGrid']//div[2]//div[2]//div//div//div//div[contains(@class,'dojoxGridRow')]"));
		int size = a.size();
		if (size == 0) {
			CustomReporter.MessageLogger("Audit functionality of General Section is not working properly.", status.Fail);

		} else {
			CustomReporter.MessageLogger("Audit functionality of General Section is working properly.", status.Pass);
		}
	}

	public void audit() {
		clickAudit();
		setAuditFrame();
		auditRowCount();
		CommonLib.clickButton(By.id("close_label"));

	}

	public void verifyPrint() {
		clickPrint();
		CommonLib.staticWait(4);
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Social History is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Social History is not working properly", status.Fail);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

	public void verifyPrintAll() {
		clickPrintAll();
		CommonLib.staticWait(4);
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("PrintAll Functionality Of Social History is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("PrintAll Functionality Of Social History is not working properly", status.Fail);
		CommonLib.setFrame(Config.props.getProperty("MainFrame"));
	}

}
