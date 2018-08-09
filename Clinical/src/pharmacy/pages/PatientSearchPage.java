package pharmacy.pages;

import org.openqa.selenium.By;

import common.CommonLib;
import common.Config;
import common.HashTableRepository;

public class PatientSearchPage {

	public void selectSearchByPatNumber() {
		CommonLib.getElement(By.id("searchByPatientNumber")).click();
	}

	public void selectSearchByPatName() {
		CommonLib.getElement(By.id("searchByPatientName")).click();
	}

	public void selectSearchByMedRecNumber() {
		CommonLib.getElement(By.id("searchByMAR")).click();
	}

	public void selectSearchByDOB() {
		CommonLib.getElement(By.id("searchByDOB")).click();
	}

	public void selectSearchBySubType() {
		CommonLib.getElement(By.id("searchBySubType")).click();
	}

	public void checkPatientType() {
		CommonLib.setCheckbox(
				"//input[@type='checkbox' and ( contains(@name,'inpatient') or contains(@name,'outpatient') or contains(@name,'emergency') or contains(@name,'skilled') or contains(@name,'inhouse') or contains(@name,'discharged') or contains(@name,'preAdmit'))]");
	}

	public void searchForPatientNum() {
		CommonLib.getElement(By.id("searchFor")).sendKeys(Config.props.getProperty("PatNum"));
	}

	public void clickSearchButton() {
		CommonLib.clickButton(By.id("btnSubmit_label"));
	}

	public void selectSearchByMedRecNo() {
		CommonLib.getElement(By.id("searchByMAR")).click();
	}

	public void setPatientDetails() {
		String patient_name = CommonLib.getElement(By.xpath("//table/tbody/tr/td[@id='ptName']")).getText();
		patient_name = patient_name.trim().toString();
		String patient_ids = CommonLib.getElement(By.xpath("//td[@id='ptIds']")).getText();
		String[] ids = patient_ids.split("/");
		String med_record_no = ids[0].trim().toString();
		String pat_num = ids[1].trim().toString();
		String dob_txt = CommonLib.getElement(By.xpath("//div[@align='center']/table[2]/tbody/tr[2]/td[2]")).getText();
		String[] dob = dob_txt.split(" ");
		String pat_dob = dob[0].trim().toString();
		HashTableRepository.setHash("patient_name", patient_name);
		HashTableRepository.setHash("med_record_no", med_record_no);
		HashTableRepository.setHash("pat_num", pat_num);
		HashTableRepository.setHash("pat_dob", pat_dob);

	}

	public void searchForPatientName() {
		CommonLib.getElement(By.id("searchFor")).sendKeys(HashTableRepository.getHash("patient_name"));
	}

	public void checkPatientRow() {
		if (CommonLib.isElementPresent(
				By.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"),
				5)) {
			CommonLib
					.getElement(By.xpath(
							"//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"))
					.click();
		}
	}

	public String getPatientName() {
		String pat_name = "";
		if (CommonLib.isElementPresent(
				By.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[3]"),
				4)) {
			pat_name = CommonLib.getElement(By
					.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[3]"))
					.getText();
			pat_name = pat_name.trim().toString();
		}
		return pat_name;
	}

	public void searchForMedRecNo() {
		CommonLib.getElement(By.id("searchFor")).sendKeys(HashTableRepository.getHash("med_record_no"));
	}

	public String getPatientMedRecNo() {
		String pat_med_rec_no = "";
		if (CommonLib.isElementPresent(
				By.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[5]"),
				4)) {
			pat_med_rec_no = CommonLib.getElement(By
					.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[5]"))
					.getText();
			pat_med_rec_no = pat_med_rec_no.trim().toString();
		}
		return pat_med_rec_no;

	}

	public String getDateOfBirth() {
		String pat_dob = "";
		if (CommonLib.isElementPresent(
				By.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[7]"),
				4)) {
			pat_dob = CommonLib.getElement(By
					.xpath("//div[@id='patientSearchPane']//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[7]"))
					.getText();
			pat_dob = pat_dob.trim().toString();
		}
		return pat_dob;
	}

	public void searchForDOB() {
		CommonLib.getElement(By.id("searchFor")).sendKeys(HashTableRepository.getHash("pat_dob"));
	}

	public void selectStationCensusTab() {
		CommonLib.getElement(By.id("findPatientTabs_tablist_stationCensusTab")).click();
	}

	public void selectStation() {
		// CommonLib.selectfrmDropdwn("searchFor", "", "", "");
		CommonLib.setRandomDropDown(By.id("searchFor"));
	}

	public int checkDataGridOfStation() {
		int count = 0;
		if (CommonLib.isElementPresent(
				By.xpath("//div[@id='nameOfGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"), 5)) {
			count = CommonLib
					.getElements(By.xpath(
							"//div[@id='nameOfGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]"))
					.size();

		}
		return count;

	}

	public boolean checkForErros() {
		boolean check = true;
		String error_txt = CommonLib.getElement(By.xpath(
				"//div[@id='nameOfGrid']/div[2]/div/div/div/div/div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td"))
				.getText();
		if (error_txt.contains("orry")) {
			check = false;
		}
		return check;

	}

}
