package pharmacy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.CustomReporter.status;
import common.HashTableRepository;

public class chargesPage {

	public void setPattientNumber(int number) {
		// CommonLib.setData(By.xpath("//input[@id='patientNumber']"),
		// Config.props.getProperty("RxPatNum"));
		boolean flag = true;
		while (flag) {
			CommonLib.clickButton(By.xpath("//input[@id='patientNumber']//following-sibling::a/img"));
			try {
				WebElement Frame = CommonLib.getElement(
						By.xpath("//iframe[contains(@src,'/OptimumClinicals/LabPharmacyPatientSearch.do?')][last()]"));
				CommonLib.setFrameFromCurrent(Frame);
				flag = false;
			} catch (Exception e) {

			}
		}
		if (number == 1)

			CommonLib.setData(By.xpath("//input[@id='searchFor']"), Config.props.getProperty("RxPatNum"));
		else
			CommonLib.setData(By.xpath("//input[@id='searchFor']"), Config.props.getProperty("RxPatNumMulti"));
		CommonLib.clickButton(By.xpath("//div[contains(text(),'Search')]"));
		CommonLib.clickButton(
				By.xpath("//div[@id='nameOfGrid']/div[2]//table[@class='dojoxGridRowTable']/tbody/tr/td[3]"));
	}

	public void clickDrugName() {
		CommonLib.clickButton(By.xpath("//input[@id='drugName']"));
	}

	public int countRows() {
		List<WebElement> li = CommonLib.getElements(By.xpath(
				"//div[@id='displayDataGrid']/div[2]//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[2]"));
		int size = li.size();
		if (size == 1 && li.get(0).getText().contains("There are no")) {
			size = 0;
		}
		return size;
	}

	public void clickNewPatient() {
		CommonLib.clickButton(By.xpath("//span[contains(text(),'New Patient')]"));
	}

	public void setPatientNumberMultiUser() {
		CommonLib.setData(By.xpath("//input[@id='searchFor']"), Config.props.getProperty("RxPatNumMulti"));
	}

	public void setPatientNumberMultiUserOriginal() {
		CommonLib.setData(By.xpath("//input[@id='searchFor']"), Config.props.getProperty("RxPatNum"));
	}

	public void clickMultiPatientSearch() {
		CommonLib.clickButton(By.xpath("//div[text()='Search']/.."));
	}

	public boolean validateChargeWithoutOrders() {
		List<WebElement> li = CommonLib.getElements(By.xpath(
				"//div[@id='displayDataGrid']/div[2]//div[contains(@class,'dojoxGridRow')]/table/tbody/tr/td[2]"));
		String drugName = "";
		// String quantity = "";
		for (WebElement e : li) {
			drugName = drugName + e.getText() + "_";
		}
		if (drugName.contains(HashTableRepository.getHash("Charge"))) {
			return true;
		} else
			return false;
	}

	public void clickSearchDrug() {
		createOrderPage cop = new createOrderPage();
		cop.clickButtonTwice("//input[@name='drugName']//following-sibling::a/img");
		// CommonLib.clickButton(By.xpath("//input[@name='drugName']//following-sibling::a/img"));
	}

	public void setQuantity(String Quantity) {
		String quantity = Integer.toString(CommonLib.generateRandom(1, 10));
		CommonLib.setData(By.xpath("//input[@id='quantity']"), quantity);
		HashTableRepository.setHash("Quantity", quantity);
	}

	public void clickAdd() {
		// String time =
		// CommonLib.getElement(By.xpath("//input[@id='transactionTime']")).getAttribute("value");
		// HashTableRepository.setHash("time", time);
		int i = 1;
		while (i >= 0) {
			CommonLib.clickButton(By.xpath("//input[@name='Save' and @value='Add']"));
			i--;
			try {
				// CommonLib.acceptAlert();
			} catch (Exception e) {

			}
		}
	}

	public void validateOnScreen(String Charge, String Quantity) {
		List<WebElement> li = CommonLib
				.getElements(By.xpath("//table[@id='chargesTable']/tbody/tr[contains(@class,'clTableRow')]/td[2]"));
		List<WebElement> quant = CommonLib
				.getElements(By.xpath("//table[@id='chargesTable']/tbody/tr[contains(@class,'clTableRow')]/td[7]"));
		String drugName = "";
		String quantity = "";
		for (WebElement we : li) {
			drugName = drugName + we.getText() + "_";
		}
		for (WebElement we : quant) {
			quantity = quantity + we.getText() + "_";
		}
		if (quantity.contains(HashTableRepository.getHash(Quantity))
				&& drugName.contains(HashTableRepository.getHash(Charge))) {
			CustomReporter.MessageLogger("addition successful", status.Pass);

		} else
			CustomReporter.MessageLogger("addition not successful", status.Fail);
		CommonLib.clickButton(By.xpath("//div[text()='Submit']"));

	}

	public void clickSubmit() {
		CommonLib.clickButton(By.xpath("//div[text()='Submit']"));
	}

}
