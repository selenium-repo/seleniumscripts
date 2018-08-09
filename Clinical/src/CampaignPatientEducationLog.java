import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PatientEducationLog.testcases.PatientEducationLogTestcase;
import findpatient.FindPatient;

@Listeners(common.Listener.class)

@Test(groups = "CampaignPatientEducationLog")
public class CampaignPatientEducationLog {

	@Test(priority = 70, dependsOnGroups = { "CampaignLogin" })
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.newPatientSearch();
		objFind = null;
	}

	@Test(priority = 71, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationAdd() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.add();
	}

	@Test(priority = 72, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationEdit() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.edit();
	}

	@Test(priority = 73, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationDisplay() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.display();
	}

	@Test(priority = 74, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationDelete() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.delete();
	}

	@Test(priority = 75, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationPrint() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.print();
	}

	@Test(priority = 76, dependsOnGroups = { "CampaignLogin" })
	public static void PatientEducationPrintAll() throws Exception {
		PatientEducationLogTestcase obj = new PatientEducationLogTestcase();
		obj.printAll();
	}
}
