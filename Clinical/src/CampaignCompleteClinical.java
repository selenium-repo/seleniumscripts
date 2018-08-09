
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.CommonLib;

@Listeners(common.Listener.class)

public class CampaignCompleteClinical {

	@Test(priority = 500)
	public static void LogOut() throws Exception {
		// Login objLogin = new Login();
		// objLogin.logout();
		CommonLib.GetDriver().navigate().refresh();
		// objLogin = null;
	}

}
