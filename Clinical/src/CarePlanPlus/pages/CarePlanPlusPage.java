package CarePlanPlus.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;
import common.MenuNavigation;

public class CarePlanPlusPage {

	public void menunav() {
		MenuNavigation.menuNav("CarePlanPlus");
	}

	public void SetMainFrame() {
		CommonLib.setFrame(By.xpath("//iframe[@id='mainWorkFrame0']"));
	}

	public void setPropertiesFrame() {
		WebElement PropertiesFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'care-plan-plus-properties.action')][last()]"));
		CommonLib.setFrameFromCurrent(PropertiesFrame);
	}

	public void setControlFrame() {
		WebElement ControlFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/CarePlan.do?action=control')][last()]"));
		CommonLib.setFrameFromCurrent(ControlFrame);
	}

	public void setAddViewSuggestedFrame() {
		WebElement SuggestedFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'suggested-care-plan-plus.action')][last()]"));
		CommonLib.setFrameFromCurrent(SuggestedFrame);
	}

	public void setAuditFrame() {
		WebElement AuditFrame = CommonLib.getElement(By.xpath("//iframe[@id='iFrameDialog' and contains(@src,'/OptimumClinicals/care-plan-plus-audit-log.action')][last()]"));
		CommonLib.setFrameFromCurrent(AuditFrame);
	}

	public void clickAdd() {
		CommonLib.clickButton(By.id("add_label"));
	}

	public void clickEdit() {
		CommonLib.clickButton(By.id("edit_label"));
	}

	public void clickDelete() {
		CommonLib.clickButton(By.id("delete_label"));
	}

	public void clickPrint() {
		CommonLib.clickButton(By.id("print_label"));
	}

	public void clickActiveOnly() {
		CommonLib.clickButton(By.id("activeOnly_label"));
	}

	public void clickEntirePlan() {
		CommonLib.clickButton(By.id("entirePlan_label"));
	}

	public void clickProperties() {
		CommonLib.clickButton(By.id("properties_label"));
	}

	public void clickControl() {
		CommonLib.clickButton(By.id("Control_label"));
	}

	public void clickAddViewSuggested() {
		CommonLib.clickButton(By.id("addView_label"));
	}

	public void clickAudit() {
		CommonLib.clickButton(By.id("auditLog_label"));
	}

	public void cancelSuggestedCP() {
		List<WebElement> cancel = CommonLib.getElements(By.xpath("//div[text()='Cancel']"));
		cancel.get(cancel.size() - 1).click();
	}

	public void clickPhysicianReview() {
		CommonLib.clickButton(By.id("physicianReview_label"));
	}

	public void selectCarePlanStatus(int status) {
		try {
			CommonLib.selectDojoDropDownByKeyDownNumber("CPSTATUSList", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectCarePlan() {
		try {
			CommonLib.selectDojoListValue("carePlanCode1List", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectProblem() {
		try {
			CommonLib.selectDojoListValue("problemCodeList", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectOutcome() {
		try {
			CommonLib.selectDojoListValue("outcomeCodeList", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectIntervention() {
		try {
			CommonLib.selectDojoListValue("interventionCodeList", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectOrder() {
		try {
			CommonLib.selectDojoListValue("orderOptionList", " ");
		} catch (Exception e) {
			CustomReporter.MessageLogger("There are no orders to select.", status.Pass);
		}
	}

	public void orderPriority() {
		try {
			CommonLib.selectDojoListValue("orderTypeList", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void kardexGroup() {
		try {
			CommonLib.selectDojoListValue("kardexGroupCodeList", " ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cpPriority() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		WebElement wb = CommonLib.getElement(By.id("CPPRITY_2"));
		CommonLib.clickButton(By.id("CPPRITY_2"));
		while (result != true) {
			if (wb.getAttribute("aria-checked").equals("false")) {
				js.executeScript("arguments[0].scrollIntoView();", wb);
				CommonLib.clickButton(By.id("CPPRITY_2"));
			} else {
				result = true;
				break;
			}
		}

	}

	public void problemPriority() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		WebElement wb = CommonLib.getElement(By.id("CPPPRITY_2"));
		CommonLib.clickButton(By.id("CPPPRITY_2"));
		while (result != true) {
			if (wb.getAttribute("aria-checked").equals("false")) {
				js.executeScript("arguments[0].scrollIntoView();", wb);
				CommonLib.clickButton(By.id("CPPPRITY_2"));
			} else {
				result = true;
				break;
			}
		}
	}

	public void outcomePriority() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		WebElement wb = CommonLib.getElement(By.id("PPOPRITY_2"));
		CommonLib.clickButton(By.id("PPOPRITY_2"));
		while (result != true) {
			if (wb.getAttribute("aria-checked").equals("false")) {
				js.executeScript("arguments[0].scrollIntoView();", wb);
				CommonLib.clickButton(By.id("PPOPRITY_2"));
			} else {
				result = true;
				break;
			}
		}
	}

	public void interventionPriority() {
		boolean result = false;
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		WebElement wb = CommonLib.getElement(By.id("PPIPRITY_2"));
		CommonLib.clickButton(By.id("PPIPRITY_2"));
		while (result != true) {
			if (wb.getAttribute("aria-checked").equals("false")) {
				js.executeScript("arguments[0].scrollIntoView();", wb);
				CommonLib.clickButton(By.id("PPIPRITY_2"));
			} else {
				result = true;
				break;
			}
		}
	}

	public void interventionType() {
		try {
			CommonLib.clickButton(By.id("PPIIVTYPE_M"));
		} catch (Exception e) {
		}
	}

	public String getCarePlanValue() {
		return CommonLib.getElement(By.xpath("//input[@id='carePlanCode1List']/input")).getAttribute("value");
	}

	public void clickSubmit() {
		CommonLib.clickButton(By.id("buttonSubmitReaction"));
	}

	public void clickOrderSubmit() {
		CommonLib.clickButton(By.id("buttonSubmit_label"));
	}

	public void expandTree() {
		try {
			CommonLib.clickButton(By.xpath(
					"//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeRow dijitTreeRowSelected']/span[@class='dijitInline dijitTreeExpando dijitTreeExpandoClosed']"));
		} catch (Exception e) {
		}
	}

	public int countCPTree() {
		int count = CommonLib
				.getElements(By.xpath("//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[contains(@class, 'dijitNotLoaded') or contains(@class, 'dijitLoaded')]"))
				.size();
		System.out.println("####################### Care PLan" + count);
		return count;
	}

	public int countPbTree() {
		expandTree();
		CommonLib.staticWait(10);
		int count = CommonLib.getElements(By.xpath(
				"//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[contains(@class, 'dijitNotLoaded') or contains(@class, 'dijitLoaded')]"))
				.size();
		System.out.println("####################### Problem" + count);
		return count;
	}

	public int countOutTree() {
		expandTree();
		List<WebElement> wb = CommonLib.getElements(By.xpath("//b[contains(text(),'Problem')]/.."));
		for (WebElement wbList : wb) {
			wb.get(0).click();
			break;
		}
		CommonLib.staticWait(10);
		int count = CommonLib.getElements(By.xpath(
				"//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[contains(@class, 'dijitNotLoaded') or contains(@class, 'dijitLoaded')]"))
				.size();
		System.out.println("####################### Outcome" + count);
		return count;
	}

	public int countInvtTree() {
		expandTree();
		List<WebElement> wb = CommonLib.getElements(By.xpath("//b[contains(text(),'Outcome')]/.."));
		for (WebElement wbList : wb) {
			wb.get(0).click();
			break;
		}
		CommonLib.staticWait(10);
		int count = CommonLib.getElements(By.xpath(
				"//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[contains(@class, 'dijitNotLoaded') or contains(@class, 'dijitLoaded')]"))
				.size();
		System.out.println("####################### Intervention" + count);
		return count;
	}

	public int countOrderTree() {
		expandTree();
		List<WebElement> wb = CommonLib.getElements(By.xpath("//b[contains(text(),'Intervention')]/.."));
		for (WebElement wbList : wb) {
			wb.get(0).click();
			break;
		}
		CommonLib.staticWait(10);
		int count = CommonLib.getElements(By.xpath(
				"//div[@id='CarePlans']//div[@id='CarePlanTree']//div[@class='dijitTreeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[@class='dijitTreeNodeContainer']//div[contains(@class, 'dijitNotLoaded') or contains(@class, 'dijitLoaded')]"))
				.size();
		System.out.println("####################### Order" + count);
		return count;
	}

	public int countSuggested() {
		int count = CommonLib.getElements(By.xpath("//div[@id='suggestedCarePlanGrid']/div[2]//div[contains(@class, 'dojoxGridRow')]/table/tbody/tr/td/img")).size();
		System.out.println("####################### Suggested" + count);
		return count;
	}

	public int countAudit() {
		int count = CommonLib.getElements(By.xpath("//div[@id='searchGrid']/div[2]//div[contains(@class, 'dojoxGridRow')]")).size();
		System.out.println("####################### Suggested" + count);
		return count;
	}

	public void selectProblemValueList() {
		WebElement wb = CommonLib.getElement(By.xpath("//form[@id='problemDetail']/table/tbody/tr[4]/td[2]/textarea[@id='CPPDESC']"));
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		js.executeScript("arguments[0].scrollIntoView();", wb);
		try {
			int dp = CommonLib.getElements(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_*MOTOR1']/option[1]")).size();
			if (dp > 0)
				CommonLib.clickButton(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_*MOTOR1']/option[1]"));
			CommonLib.setData(By.xpath("//form[@id='problemDetail']/table/tbody/tr[4]/td[2]/textarea[@id='CPPDESC']"), "data");
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("buttonSubmitVTList_label"));

		clickSubmit();

	}

	public void selectOutcomeValueList() {
		WebElement wb = CommonLib.getElement(By.xpath("//form[@id='outcomeDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPODESC']"));
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		js.executeScript("arguments[0].scrollIntoView();", wb);
		try {
			int dp = CommonLib.getElements(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_FOODPREF']/option[1]")).size();
			if (dp > 0)
				CommonLib.clickButton(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_FOODPREF']/option[1]"));
		} catch (Exception e) {
		}
		try {
			int dp = CommonLib.getElements(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_AAAVDIR']/option[1]")).size();
			if (dp > 0)
				CommonLib.clickButton(By.xpath("//div[@id='vtDiv']/table/tbody/tr[3]/td/select[@id='valueTableList_AAAVDIR']/option[1]"));
			int text = CommonLib.getElements(By.xpath("//form[@id='outcomeDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPODESC']")).size();
			if (text > 0) {
				CommonLib.setData(By.xpath("//form[@id='outcomeDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPODESC']"), "data");
			}
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("buttonSubmitVTList_label"));
		clickSubmit();
	}

	public void selectInterventionValueList() {
		WebElement wb = CommonLib.getElement(By.xpath("//form[@id='interventionDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPIDESC']"));
		JavascriptExecutor js = (JavascriptExecutor) CommonLib.GetDriver();
		js.executeScript("arguments[0].scrollIntoView();", wb);
		try {
			int dp = CommonLib.getElements(By.xpath("//div[@id='vtDiv1']/table/tbody/tr[3]/td/select[@id='valueTableList_*MOTOR']/option[1]")).size();
			if (dp > 0)
				CommonLib.clickButton(By.xpath("//div[@id='vtDiv1']/table/tbody/tr[3]/td/select[@id='valueTableList_*MOTOR']/option[1]"));
		} catch (Exception e) {
		}
		try {
			int dp = CommonLib.getElements(By.xpath("//div[@id='vtDiv1']/table/tbody/tr[5]/td/select[@id='valueTableList_*CDAFCS1']/option[1]")).size();
			if (dp > 0)
				CommonLib.clickButton(By.xpath("//div[@id='vtDiv1']/table/tbody/tr[5]/td/select[@id='valueTableList_*CDAFCS1']/option[1]"));
			int text = CommonLib.getElements(By.xpath("//form[@id='interventionDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPIDESC']")).size();
			if (text > 0) {
				CommonLib.setData(By.xpath("//form[@id='interventionDetail']/table/tbody/tr[4]/td[2]/textarea[@id='PPIDESC']"), "data");
			}
		} catch (Exception e) {
		}
		CommonLib.clickButton(By.id("buttonSubmitVTList_label"));
		clickSubmit();
	}

	public boolean errorDialogCP() {
		boolean error = false;
		List<WebElement> errorList = CommonLib.getElements(By.xpath("//span[text()='OK']"));
		try {
			errorList.get(errorList.size() - 1).click();
			error = true;
		} catch (Exception e) {
		}
		return error;
	}

	public boolean errorDialog() {
		boolean error = false;
		try {
			CommonLib.clickButton(By.xpath("//div[@id='genericDialog']//div[2]//table/tbody/tr[4]/td//span[@class='dijitReset dijitInline dijitButtonText']"));
			error = true;
		} catch (Exception e) {
		}
		return error;
	}

	public boolean errorDialogInvt() {
		boolean error = false;
		try {
			CommonLib.clickButton(By.xpath("//div[@id='errorDialog']//div[2]//table/tbody/tr[2]/td//span[@class='dijitReset dijitInline dijitButtonText']"));
			error = true;
		} catch (Exception e) {
		}
		return error;
	}

	public void deleteDialog() {
		CommonLib.clickButton(By.xpath("//div[@id='delCarePlanDialogId']//span[contains(text(), 'OK')]"));
	}

	public void editPlan() {
		CommonLib.setData(By.xpath("//form[@id='planDetail']/table/tbody/tr[4]/td[2]//input[@id='CPDAY']"), "2");
	}

	public void editProblem() {
		try {
			CommonLib.setData(By.xpath("//form[@id='problemDetail']/table/tbody/tr[5]/td[2]//input[@id='CPPDAY']"), "2");
		} catch (Exception e) {
			CommonLib.setData(By.xpath("//form[@id='problemDetail']/table/tbody/tr[8]/td[2]//input[@id='CPPDAY']"), "2");
		}

	}

	public void editOutcome() {
		CommonLib.setData(By.xpath("//form[@id='outcomeDetail']/table/tbody/tr[6]/td[2]//input[@id='PPODAY']"), "2");
	}

	public void editIntervention() {
		CommonLib.setData(By.xpath("//form[@id='interventionDetail']/table/tbody/tr[8]/td[2]//input[@id='PPIDAY']"), "2");
		interventionPriority();
		clickSubmit();
		boolean error = errorDialogInvt();
		if (error) {
			String date = CommonLib.addDaysToCurrent(-2);
			String[] date1 = date.split("");
			if (date1[0].equals("0"))
				date = date.substring(1);
			CommonLib.setData(By.xpath("//form[@id='interventionDetail']//input[@id='PPISTATDT']"), date);
		}
	}

	public boolean verifyAddCarePlan() {
		boolean error = false;
		int CPCountInit = Integer.valueOf(HashTableRepository.getHash("CPCountInit"));
		int CPCountNew = countCPTree();
		HashTableRepository.setHash("CPCountFinal", String.valueOf(CPCountNew));
		if (CPCountNew > CPCountInit)
			CustomReporter.MessageLogger("Add Functionality Of Care Plan Plus is working properly", status.Pass);
		else {
			if (errorDialogCP()) {
				if (errorDialogCP()) {
				}
				CustomReporter.MessageLogger("This care Plan is already present.", status.Pass);
				error = true;
			} else
				CustomReporter.MessageLogger("Add Functionality Of Care Plan Plus is not working properly", status.Fail);
		}
		return error;
	}

	public void verifyAddProblem() {
		int pbCountInit = Integer.valueOf(HashTableRepository.getHash("pbCountInit"));
		if (errorDialog())
			selectProblemValueList();
		int pbCountNew = countPbTree();
		HashTableRepository.setHash("PbCountFinal", String.valueOf(pbCountNew));
		if (pbCountNew > pbCountInit)
			CustomReporter.MessageLogger("Problem Add Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Problem Add Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyAddOutcome() {
		int otCountInit = Integer.valueOf(HashTableRepository.getHash("otCountInit"));
		if (errorDialog())
			selectOutcomeValueList();
		int otCountNew = countOutTree();
		HashTableRepository.setHash("OutCountFinal", String.valueOf(otCountNew));
		if (otCountNew > otCountInit)
			CustomReporter.MessageLogger("Outcome Add Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Outcome Add Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyAddIntervention() {
		int ivtCountInit = Integer.valueOf(HashTableRepository.getHash("IVTCountInit"));
		if (errorDialogInvt())
			selectInterventionValueList();
		int ivtCountNew = countInvtTree();
		HashTableRepository.setHash("IVTCountFinal", String.valueOf(ivtCountNew));
		if (ivtCountNew > ivtCountInit)
			CustomReporter.MessageLogger("Intervention Add Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Intervention Add Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyAddOrder() {
		int OrderCountInit = Integer.valueOf(HashTableRepository.getHash("OrderCountInit"));
		int OrderCountFinal = countOrderTree();
		if (OrderCountFinal > OrderCountInit)
			CustomReporter.MessageLogger("Order Add Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Order Add Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyEditCarePlan() {
		CommonLib.staticWait(5);
		String value = CommonLib.getElement(By.xpath("//form[@id='planDetail']/table/tbody/tr[4]/td[2]//input[@id='CPDAY']")).getAttribute("value");
		System.out.println("carePlan value &&&&&&&&&&&&&&&&&&&&&" + value);
		if (value.equals("2"))
			CustomReporter.MessageLogger("Care Plan Edit Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Care Plan Edit Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyEditProblem() {
		String value = "";
		CommonLib.staticWait(5);
		try {
			value = CommonLib.getElement(By.xpath("//form[@id='problemDetail']/table/tbody/tr[5]/td[2]//input[@id='CPPDAY']")).getAttribute("value");
		} catch (Exception e) {
			value = CommonLib.getElement(By.xpath("//form[@id='problemDetail']/table/tbody/tr[8]/td[2]//input[@id='CPPDAY']")).getAttribute("value");
		}
		System.out.println(" Problem value  &&&&&&&&&&&&&&&&&&&&&" + value);
		if (value.equals("2"))
			CustomReporter.MessageLogger("Problem Edit Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Problem Edit Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyEditOutcome() {
		CommonLib.staticWait(5);
		String value = CommonLib.getElement(By.xpath("//form[@id='outcomeDetail']/table/tbody/tr[6]/td[2]//input[@id='PPODAY']")).getAttribute("value");
		System.out.println(" Outcome value  &&&&&&&&&&&&&&&&&&&&&" + value);
		if (value.equals("2"))
			CustomReporter.MessageLogger("Outcome Edit Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Outcome Edit Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyEditIntervention() {
		CommonLib.staticWait(5);
		String value = CommonLib.getElement(By.xpath("//form[@id='interventionDetail']/table/tbody/tr[8]/td[2]//input[@id='PPIDAY']")).getAttribute("value");
		System.out.println(" Outcome value  &&&&&&&&&&&&&&&&&&&&&" + value);
		if (value.equals("2"))
			CustomReporter.MessageLogger("Intervention Edit Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("intervention Edit Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyDeleteIntervention() {
		int ivtCountFinal = Integer.valueOf(HashTableRepository.getHash("IVTCountFinal"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		CommonLib.staticWait(5);
		int ivtCountNew = countInvtTree();
		if (ivtCountNew < ivtCountFinal)
			CustomReporter.MessageLogger("Intervention Delete Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Intervention Delete Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyDeleteOutcome() {
		int OutCountFinal = Integer.valueOf(HashTableRepository.getHash("OutCountFinal"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		CommonLib.staticWait(5);
		int OutCountNew = countOutTree();
		if (OutCountNew < OutCountFinal)
			CustomReporter.MessageLogger("Outcome Delete Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Outcome Delete Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyDeleteProblem() {
		int PbCountFinal = Integer.valueOf(HashTableRepository.getHash("PbCountFinal"));
		clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		CommonLib.staticWait(5);
		int PbCountNew = countPbTree();
		if (PbCountNew < PbCountFinal)
			CustomReporter.MessageLogger("Problem Delete Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Problem Delete Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyDeleteCarePlan() {
		int CPCountFinal = Integer.valueOf(HashTableRepository.getHash("CPCountFinal"));
		CommonLib.staticWait(5);
		int CPCountNew = countCPTree();
		if (CPCountNew < CPCountFinal)
			CustomReporter.MessageLogger("Care Plan Delete Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Care Plan Delete Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void rightClick(By locator, String rightclick_text) {
		WebElement wb = CommonLib.getElement(locator);
		wb.click();
		CommonLib.staticWait(10);
		Actions action = new Actions(CommonLib.GetDriver()).contextClick(wb);
		action.build().perform();
		CommonLib.staticWait(2);
		CommonLib.clickButton(By.xpath("//table[@id='careplanMenu']//td[contains(.,'" + rightclick_text + "')]/ancestor::tr[@class='dijitReset dijitMenuItem']"));
	}

	public void clickElement(By locator) {
		WebElement wb = CommonLib.getElement(locator);
		wb.click();
	}

	public void addCarePlanNew() {
		int CPCount = countCPTree();
		boolean error = false;
		if (CPCount < 3) {
			do {
				clickAdd();
				selectCarePlan();
				selectCarePlanStatus(2);
				cpPriority();
				clickSubmit();
				error = verifyAddCarePlan();
				CPCount = countCPTree();
			} while (error == true);
			if (CPCount < 3) {
				do {
					clickAdd();
					selectCarePlan();
					selectCarePlanStatus(2);
					cpPriority();
					clickSubmit();
					error = verifyAddCarePlan();
				} while (error == true);
			}
		} else
			CustomReporter.MessageLogger("Three Care Plans already added", status.Pass);
	}

	public void propertiesData() {
		clickProperties();
		setPropertiesFrame();
		CommonLib.clickButton(By.xpath("//input[@name='displayPlanActive']"));
		CommonLib.clickButton(By.xpath("//input[@name='displayProblemActive']"));
		CommonLib.clickButton(By.xpath("//input[@name='displayOutcomeActive']"));
		CommonLib.clickButton(By.xpath("//input[@name='displayInterventionActive']"));
		CommonLib.clickButton(By.xpath("//span[contains(@id, 'dijit_form_Button')]/div[text()='Submit']"));
		SetMainFrame();
	}

	public void verifyPrint() {
		boolean var = false;
		var = CommonLib.closeLastWindow();
		if (var == true)
			CustomReporter.MessageLogger("Print Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Print Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyActiveOnly() {
		CommonLib.staticWait(5);
		int CPCountInit = Integer.valueOf(HashTableRepository.getHash("CPCountInit"));
		int CPCountNew = countCPTree();
		if (CPCountNew < CPCountInit)
			CustomReporter.MessageLogger("Active Only Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Active Only Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyEntirePlan() {
		CommonLib.staticWait(5);
		int CPCountInit = Integer.valueOf(HashTableRepository.getHash("CPCountInit"));
		int CPCountNew = countCPTree();
		if (CPCountNew > CPCountInit)
			CustomReporter.MessageLogger("Entire Plan Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Entire Plan Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyProperties() {
		CommonLib.staticWait(5);
		int CPCountInit = Integer.valueOf(HashTableRepository.getHash("CPCountInit"));
		int CPCountNew = countCPTree();
		if (CPCountNew < CPCountInit)
			CustomReporter.MessageLogger("Properties Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Properties Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyControl() {
		try {
			CommonLib.setData(By.xpath("//input[@id='CPCDAY']"), "2");
			CommonLib.clickButton(By.xpath("//span[@class='iframeDialogCloseIcon']"));
			CustomReporter.MessageLogger("Control Functionality Of Care Plan Plus is working properly", status.Pass);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Control Plan Functionality Of Care Plan Plus is not working properly", status.Fail);
		}
	}

	public void verifyAddViewSuggested() {
		int suggestedInit = Integer.valueOf(HashTableRepository.getHash("suggestedInit"));
		int suggestedFinal = Integer.valueOf(HashTableRepository.getHash("suggestedFinal"));
		if (suggestedFinal > suggestedInit)
			CustomReporter.MessageLogger("Add/View Suggested Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Add/View Suggested Functionality Of Care Plan Plus is not working properly", status.Fail);
	}

	public void verifyAudit() {
		int auditInit = Integer.valueOf(HashTableRepository.getHash("auditInit"));
		CommonLib.staticWait(5);
		int auditFinal = countAudit();
		if (auditFinal > auditInit)
			CustomReporter.MessageLogger("Audit Functionality Of Care Plan Plus is working properly", status.Pass);
		else
			CustomReporter.MessageLogger("Audit Functionality Of Care Plan Plus is not working properly", status.Fail);
		CommonLib.clickButton(By.xpath("//div[@id='careplanPlusAuditLogToolbar']//span[@id='wizardCancel_label']"));
		SetMainFrame();
	}

	public void verifyPhysicianReview() {
		try {
			String text = CommonLib.getElement(By.xpath("//div[@id='carePlanPhysicianReview']//table/tbody/tr[2]/td/span")).getText();
			if (text.contains("reviewed"))
				CustomReporter.MessageLogger("Physician Review Functionality Of Care Plan Plus is working properly", status.Pass);
			else
				CustomReporter.MessageLogger("Physician Review Functionality Of Care Plan Plus is not working properly", status.Fail);
		} catch (Exception e) {
			CustomReporter.MessageLogger("Physician Review Functionality Of Care Plan Plus is not working properly", status.Fail);
		}

	}
}
