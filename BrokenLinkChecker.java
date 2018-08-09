import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import common.CommonLib;
import common.CustomReporter;
import common.CustomReporter.status;

@Listeners(common.Listener.class)
public class LinkChecker {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("Resources/testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

	@Test(priority = 1)
	public static void ValidateLink() throws Exception {
		int rCode = 200;
		String sURL = "";
		HttpURLConnection HTTPCon = null;

		List<WebElement> links = CommonLib.getElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			WebElement link = it.next();
			sURL = link.getAttribute("href");
			if (sURL == null || sURL.isEmpty()) {
				CustomReporter.MessageLogger(link + " - URL is either not configured for anchor tag or it is empty", status.Warning);
				continue;
			}
			try {
				HTTPCon = (HttpURLConnection) (new URL(sURL).openConnection());
				HTTPCon.setRequestMethod("HEAD");
				HTTPCon.connect();
				rCode = HTTPCon.getResponseCode();
				if (rCode >= 400) {
					CustomReporter.MessageLogger(sURL + " is a broken link", status.Fail);
				} else {
					CustomReporter.MessageLogger(sURL + " is a valid link", status.Pass);
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
