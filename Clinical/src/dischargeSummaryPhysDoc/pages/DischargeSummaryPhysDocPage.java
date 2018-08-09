package dischargeSummaryPhysDoc.pages;

import org.openqa.selenium.By;

import common.CommonLib;

public class DischargeSummaryPhysDocPage {
	public void actions(String action) {

		CommonLib.clickButton(By.xpath("//*[@id='" + action.replace("-", "").toLowerCase()
				+ "_label'  and (contains(text(),'" + action + "'))]"));

		// span[@id='lblPassword' and (contains(text(),'Password:'))]
	}
}
