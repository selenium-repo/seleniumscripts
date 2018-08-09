package findpatient;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.HashTableRepository;
import common.MenuNavigation;

public class FindPatient {

	int variable = 4;

	public void PatientSearch() {

		try {
			MenuNavigation.menuNav("FindPatient");
			CommonLib.LinkClick(By.id("findPatientTabs_tablist_patientSearchTab"));
			CommonLib.setCheckbox("//*[@type='checkbox' and ( contains(@name,'patientSearchType') or contains(@name,'patientSearchStatus'))]");
			String idx = "2;11;14;8";
			// String idx = "2;12;15;9";
			setSearch("PNUM", Config.props.getProperty("PatNum"), Integer.parseInt(idx.split(";")[3]), idx);
			setSearch("PNAME", HashTableRepository.getHash("PNAME"), Integer.parseInt(idx.split(";")[0]), "");
			setSearch("MRN", HashTableRepository.getHash("MRN"), Integer.parseInt(idx.split(";")[1]), "");
			setSearch("DOB", HashTableRepository.getHash("DOB"), Integer.parseInt(idx.split(";")[2]), "");

			setSearch("PNUM", Config.props.getProperty("PatNum"), Integer.parseInt(idx.split(";")[3]), "");
			CommonLib.LinkClick(By.xpath("//a[contains(text(), '" + HashTableRepository.getHash("PNAME") + "')]"));
			CommonLib.setFrameToDefault();

		} catch (Exception e) {
			CustomReporter.MessageLogger(e.getMessage() + "\n", CustomReporter.status.Error);
		}
	}

	public void setSearch(String searchType, String searchValue, int ColCount, String idx) throws InterruptedException {
		CommonLib.selectradio(By.xpath("//input[@name='searchType' and @value='" + searchType + "']"));
		CommonLib.setValueDojo("//input[@name='searchValue']", searchValue);
		CommonLib.clickButton(By.id("searchButton_label"));
		CommonLib.staticWait(2);
		tablePatientSearch(searchValue, ColCount, idx);
		if (idx.length() > 0) {
			CommonLib.LinkClick(By.id("findPatientTabs_tablist_myFavoritesTab"));
			CommonLib.staticWait(2);
			tableMyfavSearch(searchValue, ColCount);
			CommonLib.LinkClick(By.id("findPatientTabs_tablist_patientSearchTab"));
		}
	}

	public void tableMyfavSearch(String searchText, int ColNum) throws InterruptedException {
		String indexLoc;
		if (ColNum == 2) {
			indexLoc = "[1]";
		} else {
			indexLoc = "[2]";
		}
		List<WebElement> tr_collection = CommonLib.getElements(By.xpath("//div[@id='myFavoritesGrid']/div[2]/div" + indexLoc + "/div/div/div/div/table/tbody/tr/td[@idx='" + ColNum + "']"));

		Iterator<WebElement> rowsC = tr_collection.iterator();
		int i = 1;
		boolean found = false;
		WLoop: while (rowsC.hasNext()) {
			String celText = rowsC.next().getText().trim().toString();
			if (celText.contains(searchText)) {
				CommonLib.clickIcon("fav_" + i);
				found = true;
				break WLoop;
			}
			i = i + 1;
		}
		if (found == false) {
			CustomReporter.MessageLogger("Patient Not found in favorites.", CustomReporter.status.Fail);
		} else {
			CustomReporter.MessageLogger("Patient found in favorites.", CustomReporter.status.Pass);
		}

	}

	public void tablePatientSearch(String searchText, int ColNum, String idx) throws InterruptedException {
		String indexLoc;
		if (ColNum == 2) {
			indexLoc = "[1]";
		} else {
			indexLoc = "[2]";
		}
		List<WebElement> tr_collection = CommonLib.getElements(By.xpath("//div[@id='patientSearchGrid']/div[2]/div" + indexLoc + "/div/div/div/div/table/tbody/tr/td[@idx='" + ColNum + "']"));

		Iterator<WebElement> rowsC = tr_collection.iterator();
		int i = 1;
		boolean found = false;
		WLoop: while (rowsC.hasNext()) {
			String celText = rowsC.next().getText().trim().toString();
			if (celText.contains(searchText)) {
				if (idx.length() > 0) {
					setPatientInfo("PNAME;MRN;DOB;PNUM", i, idx);
					try {
						CommonLib.clickIcon("fav_" + i);
					} catch (Exception e) {
						CustomReporter.MessageLogger("Patient " + searchText + " already added to Favorite.", CustomReporter.status.Warning);
					}
				}
				found = true;
			} else {
				if (!celText.equals("")) {
					// System.out.println(celText);
					found = false;
					break WLoop;
				}
			}
			if (!celText.equals("")) {
				i = i + 1;
			}
		}
		i--;
		if (found == false) {
			CustomReporter.MessageLogger("Patient Filter Failed for " + CommonLib.logFormator(searchText) + ".", CustomReporter.status.Fail);
		} else {
			CustomReporter.MessageLogger("Patient Filter found " + CommonLib.logFormator(i + "") + " records for " + CommonLib.logFormator(searchText) + ".", CustomReporter.status.Pass);
		}
	}

	public void setPatientInfo(String hKey, int RowCnt, String ColtoStore) {
		String ColNum;
		String indexLoc;
		for (int intC = 0; intC < ColtoStore.split(";").length; intC++) {
			ColNum = ColtoStore.split(";")[intC].toString();
			if (ColNum.equals("2")) {
				indexLoc = "[1]";
			} else {
				indexLoc = "[2]";
			}

			List<WebElement> tr_collection = CommonLib.getElements(By.xpath("//div[@id='patientSearchGrid']/div[2]/div" + indexLoc + "/div/div/div/div[" + RowCnt + "]/table/tbody/tr/td[@idx='" + ColNum + "']"));

			Iterator<WebElement> rowsC = tr_collection.iterator();
			HashTableRepository.setHash(hKey.split(";")[intC].toString(), rowsC.next().getText().trim().toString());

		}
	}

	public void newPatientSearch() {

		MenuNavigation.menuNav("FindPatient");
		CommonLib.LinkClick(By.id("findPatientTabs_tablist_patientSearchTab"));
		CommonLib.setCheckbox("//*[@type='checkbox' and ( contains(@name,'patientSearchType') or contains(@name,'patientSearchStatus'))]");
		CommonLib.selectradio(By.xpath("//input[@name='searchType' and @value='PNUM']"));
		try {
			CommonLib.setValueDojo("//input[@name='searchValue']", Config.props.getProperty("PatNum"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		CommonLib.clickButton(By.id("searchButton_label"));
		CommonLib.staticWait(3);
		if (CommonLib.isElementPresent(By.xpath("//div[@id='patientSearchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/a/following-sibling::a[starts-with(@href, 'select-patient')]"), 5))

			CommonLib.LinkClick(By.xpath("//div[@id='patientSearchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/a/following-sibling::a[starts-with(@href, 'select-patient')]"));

	}

	public void resultNewPatientSearch() {

		MenuNavigation.menuNav("FindPatient");
		CommonLib.LinkClick(By.id("findPatientTabs_tablist_patientSearchTab"));
		CommonLib.setCheckbox("//*[@type='checkbox' and ( contains(@name,'patientSearchType') or contains(@name,'patientSearchStatus'))]");
		CommonLib.selectradio(By.xpath("//input[@name='searchType' and @value='PNUM']"));
		try {
			CommonLib.setValueDojo("//input[@name='searchValue']", Config.props.getProperty("ResultPatNum"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		CommonLib.clickButton(By.id("searchButton_label"));
		CommonLib.staticWait(3);
		if (CommonLib.isElementPresent(By.xpath("//div[@id='patientSearchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/a/following-sibling::a[starts-with(@href, 'select-patient')]"), 5))

			CommonLib.LinkClick(By.xpath("//div[@id='patientSearchGrid']/div[2]/div/div/div/div/div/table/tbody/tr/td[3]/a/following-sibling::a[starts-with(@href, 'select-patient')]"));

	}
}
