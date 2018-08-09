import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.MenuNavigation;
import flowsheet.testcases.FlowsheetTestCases;

@Listeners(common.Listener.class)
@Test(groups = "CampaignFlowsheets")
public class CampaignFlowsheets {

	@Test(priority = 204, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyIntakeOutputFlowsheet() throws Exception {
		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyIntakeOutputFlowsheetFunctionalities();
	}

	@Test(priority = 205, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyPupilResponseFlowsheet() throws Exception {

		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyPupilResponseFlowsheetFunctionalities();
	}

	@Test(priority = 206, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyLimbMovementFlowsheet() throws Exception {
		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyLimbMovementFlowsheetFunctionalities();
	}

	@Test(priority = 207, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyGlasgowComaScaleFlowsheet() throws Exception {

		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyGlasgowComaScaleFlowsheetFunctionalities();
	}

	@Test(priority = 208, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyHeightWeightFlowsheet() throws Exception {

		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyHeightWeightFlowsheetFunctionalities();
	}

	@Test(priority = 209, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyGlucoseFlowsheet() throws Exception {

		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyGlucoseFlowsheetFunctionalities();
	}

	@Test(priority = 210, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyVitalsFlowsheet() throws Exception {

		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyVitalsFlowsheetFunctionalities();
		fstc = null;
	}

	@Test(priority = 211, dependsOnGroups = { "CampaignLogin" })
	public static void VerifyImmunizationFlowsheet() throws Exception {
		MenuNavigation.menuNav("Immunizations");
		FlowsheetTestCases fstc = new FlowsheetTestCases();
		fstc.verifyImmunizationsFunctionalities();
	}

}
