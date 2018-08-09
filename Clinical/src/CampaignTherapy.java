import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import therapy.testcases.CertificatesRecertificatesTestCases;
import therapy.testcases.PatientTherapyDocumentationTestCases;
import therapy.testcases.TherapyDisciplineMasterTestCases;
import therapy.testcases.TherapyDocumentationGroupsTestCases;
import therapy.testcases.TherapyDocumentationOptionsTestCases;
import therapy.testcases.TherapyTemplateMasterTestCases;
import therapy.testcases.TreatmentChargesSummaryTestCases;
import therapy.testcases.TreatmentDiagnosisTestCases;

public class CampaignTherapy {
	@Test(priority = 300, dependsOnGroups = { "CampaignLogin" })
	public static void verifySelectPatient() throws InterruptedException {
		TherapyTemplateMasterTestCases therapyobj = new TherapyTemplateMasterTestCases();
		therapyobj.selectPatient();
	}

	@Test(priority = 301, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTherapyTemplateMaster() throws InterruptedException {
		TherapyTemplateMasterTestCases therapyobj = new TherapyTemplateMasterTestCases();
		therapyobj.verifyTherapyTemplateMasterFunctionalities();
	}

	@Test(priority = 302, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTherapySetUp() throws InterruptedException {
		PatientTherapyDocumentationTestCases therapyobj = new PatientTherapyDocumentationTestCases();
		therapyobj.setUpPatientTherapy();
	}

	@Test(priority = 303, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTherapyNotesPatientTherapyDocumentationFunctionalities() throws InterruptedException, IOException {
		PatientTherapyDocumentationTestCases therapyobj = new PatientTherapyDocumentationTestCases();
		therapyobj.verifyPatientTherapyDocumentationFunctionalities();
	}

	@Test(priority = 304, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTherapySelectionProperties() throws InterruptedException {
		PatientTherapyDocumentationTestCases therapyobj = new PatientTherapyDocumentationTestCases();
		therapyobj.verifyTherapySelectionProperties();
	}

	@Test(priority = 305, dependsOnGroups = { "CampaignLogin" })
	public static void verifyRightClickTreeViewFunctions() throws InterruptedException {
		PatientTherapyDocumentationTestCases therapyobj = new PatientTherapyDocumentationTestCases();
		therapyobj.verifyRightClickTreeViewFunctions();
	}

	@Test(priority = 306, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTreatmentChargesSummaryFunctionalities() throws InterruptedException, IOException {
		TreatmentChargesSummaryTestCases therapyobj = new TreatmentChargesSummaryTestCases();
		therapyobj.verifyTreatmentChargesSummaryFunctionalities();
	}

	@Test(priority = 307, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTreatmentChargesSummarySelectionProperties() throws InterruptedException {
		TreatmentChargesSummaryTestCases therapyobj = new TreatmentChargesSummaryTestCases();
		therapyobj.verifyTreatmentChargesSummarySelectionProperties();
	}

	@Test(priority = 308, dependsOnGroups = { "CampaignLogin" })
	public static void verifyCertificatesFunctionalities() throws InterruptedException, AWTException {
		CertificatesRecertificatesTestCases therapyobj = new CertificatesRecertificatesTestCases();
		therapyobj.verifyCertificatesFunctionalities();
	}

	@Test(priority = 309, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTreatmentDiagnosisFunctionalities() throws InterruptedException, AWTException {
		TreatmentDiagnosisTestCases therapyobj = new TreatmentDiagnosisTestCases();
		therapyobj.verifyTreatmentDiagnosisFunctionalities();
	}

	@Test(priority = 310, dependsOnGroups = { "CampaignLogin" })
	public static void verifyTherapyDocumentationGroupsFunctionalities() throws InterruptedException, AWTException {
		TherapyDocumentationGroupsTestCases therapyobj = new TherapyDocumentationGroupsTestCases();
		therapyobj.verifyTherapyDocumentationGroupsFunctionalities();
	}

	@Test(priority = 311, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyTherapyDocumentationOptionsFunctionalities() throws InterruptedException, AWTException {
		TherapyDocumentationOptionsTestCases therapyobj = new TherapyDocumentationOptionsTestCases();
		therapyobj.therapyDocumentationOptionsFunctionalities();
	}

	@Test(priority = 312, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyTherapyDisciplineMasterFunctionalities() throws InterruptedException, AWTException {
		TherapyDisciplineMasterTestCases therapyobj = new TherapyDisciplineMasterTestCases();
		therapyobj.verifyTherapyDisciplineMasterFunctionalities();
	}

}
