package CarePlanPlus.testcases;

import org.openqa.selenium.By;

import CarePlanPlus.pages.CarePlanPlusPage;
import common.CommonLib;
import common.HashTableRepository;

public class CarePlanPlusTestcase {

	CarePlanPlusPage obj = new CarePlanPlusPage();

	public void addCarePlan() {
		obj.menunav();
		obj.SetMainFrame();
		HashTableRepository.setHash("CPCountInit", String.valueOf(obj.countCPTree()));
		obj.clickAdd();
		obj.selectCarePlan();
		obj.selectCarePlanStatus(2);
		obj.cpPriority();
		obj.clickSubmit();
		obj.verifyAddCarePlan();
		obj.addCarePlanNew();

	}

	public void addProblem() {
		HashTableRepository.setHash("pbCountInit", String.valueOf(obj.countPbTree()));
		obj.rightClick(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"), "Add Problem");
		obj.selectProblem();
		obj.problemPriority();
		obj.clickSubmit();
		obj.verifyAddProblem();
	}

	public void addOutcome() {
		HashTableRepository.setHash("otCountInit", String.valueOf(obj.countOutTree()));
		obj.rightClick(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div/span[3]/span[2]/span"), "Add Outcome");
		obj.selectOutcome();
		obj.outcomePriority();
		obj.clickSubmit();
		obj.verifyAddOutcome();
	}

	public void addIntervention() {
		HashTableRepository.setHash("IVTCountInit", String.valueOf(obj.countInvtTree()));
		obj.rightClick(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div/span[3]/span[2]/span"), "Add Intervention");
		obj.selectIntervention();
		obj.orderPriority();
		obj.kardexGroup();
		obj.interventionType();
		obj.interventionPriority();
		obj.clickSubmit();
		obj.verifyAddIntervention();
	}

	public void addOrder() {
		HashTableRepository.setHash("OrderCountInit", String.valueOf(obj.countOrderTree()));
		obj.rightClick(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"), "Add Order");
		obj.selectOrder();
		obj.clickOrderSubmit();
		obj.verifyAddIntervention();
	}

	public void editCarePlan() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		obj.clickEdit();
		obj.editPlan();
		obj.clickSubmit();
		obj.verifyEditCarePlan();
	}

	public void editProblem() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickEdit();
		obj.editProblem();
		obj.clickSubmit();
		obj.verifyEditProblem();
	}

	public void editOutcome() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickEdit();
		obj.editOutcome();
		obj.clickSubmit();
		obj.verifyEditOutcome();
	}

	public void editIntervention() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickEdit();
		obj.editIntervention();
		obj.clickSubmit();
		obj.verifyEditIntervention();
	}

	public void deleteIntervention() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickDelete();
		obj.deleteDialog();
		obj.verifyDeleteIntervention();
	}

	public void deleteOutcome() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickDelete();
		obj.deleteDialog();
		obj.verifyDeleteOutcome();
	}

	public void deleteProblem() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div/div[2]/div/div/span[3]/span[2]/span"));
		obj.clickDelete();
		obj.deleteDialog();
		obj.verifyDeleteProblem();
	}

	public void deleteCarePlan() {
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		obj.clickDelete();
		obj.deleteDialog();
		obj.verifyDeleteCarePlan();
	}

	public void print() {
		obj.clickAudit();
		obj.setAuditFrame();
		HashTableRepository.setHash("auditInit", String.valueOf(obj.countAudit()));
		CommonLib.clickButton(By.xpath("//div[@id='careplanPlusAuditLogToolbar']//span[@id='wizardCancel_label']"));
		obj.SetMainFrame();
		obj.clickPrint();
		obj.verifyPrint();
		obj.SetMainFrame();
	}

	public void activeOnly() {
		HashTableRepository.setHash("CPCountInit", String.valueOf(obj.countCPTree()));
		obj.clickElement(By.xpath("//div[@id='CarePlanTree']/div[3]/div/div[2]/div[2]/div/span[3]/span[2]/span"));
		obj.clickEdit();
		obj.selectCarePlanStatus(5);
		obj.clickSubmit();
		obj.clickActiveOnly();
		obj.verifyActiveOnly();
	}

	public void entirePlan() {
		HashTableRepository.setHash("CPCountInit", String.valueOf(obj.countCPTree()));
		obj.clickEntirePlan();
		obj.verifyEntirePlan();
	}

	public void properties() {
		HashTableRepository.setHash("CPCountInit", String.valueOf(obj.countCPTree()));
		obj.propertiesData();
		obj.verifyProperties();
		obj.propertiesData();

	}

	public void control() {
		obj.clickControl();
		obj.setControlFrame();
		obj.verifyControl();
		obj.SetMainFrame();
	}

	public void addViewSuggested() {
		obj.clickAddViewSuggested();
		obj.setAddViewSuggestedFrame();
		HashTableRepository.setHash("suggestedInit", String.valueOf(obj.countSuggested()));
		obj.cancelSuggestedCP();
		obj.SetMainFrame();
		obj.clickElement(By.xpath("//b[text()='(R)']/.."));
		obj.clickEdit();
		obj.selectCarePlanStatus(3);
		obj.clickSubmit();
		obj.clickAddViewSuggested();
		CommonLib.staticWait(5);
		obj.setAddViewSuggestedFrame();
		CommonLib.staticWait(5);
		HashTableRepository.setHash("suggestedFinal", String.valueOf(obj.countSuggested()));
		obj.cancelSuggestedCP();
		obj.SetMainFrame();
		obj.verifyAddViewSuggested();
	}

	public void audit() {
		obj.clickAudit();
		obj.setAuditFrame();
		obj.verifyAudit();
	}

	public void physicianReview() {
		obj.clickPhysicianReview();
		obj.verifyPhysicianReview();
	}

}
