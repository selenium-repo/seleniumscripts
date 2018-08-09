import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Results.testcases.LabPanelSummary_testcases;
import Results.testcases.LabTestSummary2_testcases;
import Results.testcases.LabTestSummary_testcases;
import Results.testcases.TranscribeResults;
import findpatient.FindPatient;

@Listeners(common.Listener.class)
@Test(groups = "CampaignResults")

public class CampaignResults {

	@Test(priority = 3, dependsOnGroups = { "CampaignLogin" })
	public static void FindPatient() throws Exception {
		FindPatient objFind = new FindPatient();
		objFind.resultNewPatientSearch();
		objFind = null;
	}

	@Test(priority = 4, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary_print() throws Exception {
		LabTestSummary_testcases obj = new LabTestSummary_testcases();
		obj.print();
	}

	@Test(priority = 5, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary_graphs() throws Exception {
		LabTestSummary_testcases obj = new LabTestSummary_testcases();
		obj.graphs();
	}

	@Test(priority = 6, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary_sequence() throws Exception {
		LabTestSummary_testcases obj = new LabTestSummary_testcases();
		obj.sequence();
	}

	@Test(priority = 7, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary_Micro() throws Exception {
		LabTestSummary_testcases obj = new LabTestSummary_testcases();
		obj.microbiology();
	}

	@Test(priority = 8, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_printchem() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.printCHEM();
	}

	@Test(priority = 9, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_graphschem() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.graphsCHEM();
	}

	@Test(priority = 10, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_printhema() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.printHEMA();
	}

	@Test(priority = 11, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_graphshema() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.graphsHEMA();
	}

	@Test(priority = 12, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_printmicro() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.printMICRO();
	}

	@Test(priority = 13, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_graphsmicro() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.graphsMICRO();
	}

	@Test(priority = 14, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_sequence() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.sequence();
	}

	@Test(priority = 15, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestSummary2_microtab() throws Exception {
		LabTestSummary2_testcases obj = new LabTestSummary2_testcases();
		obj.microbiology();
	}

	@Test(priority = 16, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestPanel_Print() throws Exception {
		LabPanelSummary_testcases obj = new LabPanelSummary_testcases();
		obj.print();
	}

	@Test(priority = 17, dependsOnGroups = { "CampaignLogin" })
	public static void LabTestPanel_Graphs() throws Exception {
		LabPanelSummary_testcases obj = new LabPanelSummary_testcases();
		obj.graphs();
	}

	@Test(priority = 18, dependsOnGroups = { "CampaignLogin" })
	public static void TranscribeResults_transcribe() throws Exception {
		TranscribeResults obj = new TranscribeResults();
		obj.transcribe();
	}

}
