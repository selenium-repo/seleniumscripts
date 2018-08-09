
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ClinicalFunction.PatientDiagnoses;
import ClinicalFunction.PatientProblems;
import ClinicalFunction.PatientProcedures;
import discharge_summary.testcases.DischargeSummaryTestCases;

@Listeners(common.Listener.class)

@Test(groups = "CampaignPatientClinicalDoc")
public class CampaignPatientClinicalDoc {

	@Test(priority = 15, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void PatientProblems() throws Exception {
		PatientProblems patprob = new PatientProblems();
		patprob.verifyProblemsFunctions();

	}

	@Test(priority = 16, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void PatientProcedures() throws Exception {
		PatientProcedures patproc = new PatientProcedures();
		patproc.verifyProceduresFunctions();

	}

	@Test(priority = 17, dependsOnGroups = { "CampaignLogin" }, enabled = true)
	public static void PatientDiagnoses() throws Exception {
		PatientDiagnoses patdiag = new PatientDiagnoses();
		patdiag.verifyDiagnosesFunctions();

	}

	@Test(priority = 18, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyingDischargeSummary() throws Exception {
		DischargeSummaryTestCases obj = new DischargeSummaryTestCases();
		obj.verifyAllDischargeSummaryFunctionalities();
		obj = null;
	}

}
