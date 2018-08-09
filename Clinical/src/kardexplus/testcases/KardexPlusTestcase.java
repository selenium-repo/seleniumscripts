package kardexplus.testcases;

import kardexplus.pages.KardexPlusPage;

public class KardexPlusTestcase {

	public void kardexPlus() {
		KardexPlusPage obj = new KardexPlusPage();
		obj.menuNavKardex();
		obj.verifyStationTab();
		obj.verifyMyFavTab();
		obj.verifySelectedPatientTab();
		obj = null;
	}
}
