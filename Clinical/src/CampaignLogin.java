
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import common.Config;
import common.Login;

@Listeners(common.Listener.class)
public class CampaignLogin {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("Resources/testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

	@Test(priority = 1, groups = "CampaignLogin")
	public static void LoginTest() throws Exception {
		Login objLogin = new Login();
		objLogin.initlogin(Config.props.getProperty("username"), Config.props.getProperty("password"), false);
		objLogin = null;

	}

	// @Test(priority = 2, groups = "CampaignLogin")
	// public static void FindPatient() throws Exception {
	// FindPatient objFind = new FindPatient();
	// objFind.newPatientSearch();
	// // objFind.PatientSearch();
	// // unlockOrders();
	// objFind = null;
	// }

}
