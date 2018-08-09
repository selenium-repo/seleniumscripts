package pharmacy.patientsearch.testcases;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;
import pharmacy.patientsearch.pages.PatientSearchPage;

public class PatientSearchTestCases extends PatientSearchPage {

	public void verifyPatientSearchOptions() {
		MenuNavigation.menuNav("RxPatientSearch");
		CommonLib.staticWait(3);
		verifyPatientSearchByNumber();
		verifyPatientSearchByName();
		verifyPatientSearchByMedRecNo();
		verifyPatientSearchByDOB();
		verifyNursingStationSearch();

	}

	public void verifyPatientSearchByNumber() {
		selectSearchByPatNumber();
		checkPatientType();
		searchForPatientNum();
		clickSearchButton();
		CommonLib.staticWait(4);
		setPatientDetails();
		if (Config.props.getProperty("PatNum").equals(HashTableRepository.getHash("pat_num"))) {
			CustomReporter.MessageLogger("Rx Patient Search by Patient Number " + HashTableRepository.getHash("pat_num") + " is working successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to do the Rx Patient Search by Patient Number, please try again, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientSearchByName() {
		MenuNavigation.menuNav("RxPatientSearch");
		String patient_name = HashTableRepository.getHash("patient_name");
		String[] full_name1 = patient_name.split(",");
		String first_name1 = full_name1[0].trim().toString();
		String last_name1 = full_name1[1].trim().toString();
		selectSearchByPatName();
		checkPatientType();
		searchForPatientName();
		clickSearchButton();
		CommonLib.staticWait(4);
		String pat_name = getPatientName();
		String[] full_name2 = pat_name.split(",");
		String first_name2 = full_name2[0].trim().toString();
		String last_name2 = full_name2[1].trim().toString();
		if (first_name1.equals(first_name2) && last_name1.equals(last_name2)) {
			CustomReporter.MessageLogger("Rx Patient Search by Patient Name " + HashTableRepository.getHash("patient_name") + " is working successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to do the Rx Patient Search by Patient Name, please try again, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPatientSearchByMedRecNo() {
		String med_rec_no = HashTableRepository.getHash("med_record_no");
		selectSearchByMedRecNo();
		checkPatientType();
		searchForMedRecNo();
		clickSearchButton();
		CommonLib.staticWait(4);
		setPatientDetails();
		String pat_med_rec_no = HashTableRepository.getHash("med_record_no");
		if (pat_med_rec_no.equals(med_rec_no)) {
			CustomReporter.MessageLogger("Rx Patient Search by Patient Medical Record Number " + HashTableRepository.getHash("med_record_no") + " is working successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to do the Rx Patient Search by Patient Medical Record Number, please try again, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyPatientSearchByDOB() {
		MenuNavigation.menuNav("RxPatientSearch");
		selectSearchByDOB();
		checkPatientType();
		searchForDOB();
		clickSearchButton();
		CommonLib.staticWait(4);
		String dob_txt = getDateOfBirth();
		if (HashTableRepository.getHash("pat_dob").equals(dob_txt)) {
			CustomReporter.MessageLogger("Rx Patient Search by Patient Date Of Birth " + HashTableRepository.getHash("pat_dob") + " is working successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to do the Rx Patient Search by Patient DOB, please try again, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyNursingStationSearch() {
		MenuNavigation.menuNav("RxPatientSearch");
		selectStationCensusTab();
		selectStation();
		clickSearchButton();
		int count = checkDataGridOfStation();
		if (count >= 1) {
			if (checkForErros()) {
				CustomReporter.MessageLogger("Rx Search By Nursing Station is working successfully, which is as expected", CustomReporter.status.Pass);
			} else {
				CustomReporter.MessageLogger("Failed to do the Rx Search By Nursing Station, please try again, which is not as expected", CustomReporter.status.Fail);
			}

		} else {
			CustomReporter.MessageLogger("Rx Search By Nursing Station is working properly, but there is no data for the selected nursing station", CustomReporter.status.Warning);
		}
	}

}
