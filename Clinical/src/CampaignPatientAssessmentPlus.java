import org.testng.annotations.Test;

import patient_assessment_plus.testcases.PatientAssessmentPlusTestCases;

public class CampaignPatientAssessmentPlus {

	@Test(priority = 211, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyPatientAssessmentPlus() throws Exception {

		PatientAssessmentPlusTestCases pap = new PatientAssessmentPlusTestCases();
		pap.verifyPtAssessmentPlusFucntionalities();
	}

}
