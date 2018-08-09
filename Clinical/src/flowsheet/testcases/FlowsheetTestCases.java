package flowsheet.testcases;

import java.io.IOException;

import common.CommonLib;
import common.Config;
import common.CustomReporter;
import common.MenuNavigation;
import flowsheet.pages.FlowsheetPage;

public class FlowsheetTestCases extends FlowsheetPage {
	static String immu_value = null;

	// public void verifyAllFlowsheetFunctionalities() throws
	// InterruptedException, IOException {
	//
	// verifyImmunizationsFunctionalities();
	// verifyIntakeOutputFlowsheetFunctionalities();
	// verifyPupilResponseFlowsheetFunctionalities();
	// verifyLimbMovementFlowsheetFunctionalities();
	// verifyGlasgowComaScaleFlowsheetFunctionalities();
	// verifyHeightWeightFlowsheetFunctionalities();
	// verifyGlucoseFlowsheetFunctionalities();
	// verifyVitalsFlowsheetFunctionalities();
	//
	// }

	public void verifyVitalsFlowsheetFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String vitals_flowsheet = Config.props.getProperty("vitals_flowsheet");
		clickFlowsheetTabName(vitals_flowsheet);
		CommonLib.staticWait(2);

		boolean result1 = verifyVitalsFlowsheetAdd();
		if (result1) {
			verifyVitalsFlowsheetEdit();
			verifyFlowsheetsPrint();
			verifyVitalsFlowsheetBarGraph();
			verifyVitalsFlowsheetLineGraph();
			verifyVitalsFlowsheetDelete();
		}

	}

	public void verifyGlucoseFlowsheetFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String glucose = Config.props.getProperty("glucose");
		System.out.println(glucose);
		clickFlowsheetTabName(glucose);
		CommonLib.staticWait(2);
		boolean result = verifyGlucoseFlowsheetAdd();
		if (result) {
			verifyGlucoseFlowsheetEdit();
			verifyFlowsheetsPrint();
			verifyGlucoseFlowsheetBarGraph();
			verifyGlucoseFlowsheetLineGraph();
			verifyGlucoseFlowsheetDelete();
		}

	}

	public void verifyHeightWeightFlowsheetFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String height_weight = Config.props.getProperty("height_weight");
		clickFlowsheetTabName(height_weight);
		CommonLib.staticWait(2);

		boolean check = verifyHeightWeightFlowsheetsAdd();
		if (check) {
			verifyHeightWeightFlowsheetsEdit();
			verifyFlowsheetsPrint();
			verifyHeightWeightGrowthChart();
			verifyHeightWeightBarGraph();
			verifyHeightWeightLineGraph();
			verifyHeightWeightDelete();
		}

	}

	public void verifyGlasgowComaScaleFlowsheetFunctionalities() throws InterruptedException, IOException {
		CommonLib.changeimplicitwait(3);
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String glasgow_coma = Config.props.getProperty("glasgow_coma");
		clickFlowsheetTabName(glasgow_coma);
		CommonLib.staticWait(2);

		boolean result1 = verifyGlascowComaScaleFlowsheetAdd();
		if (result1) {
			verifyGlasgowComaScaleFlowsheetEdit();
			verifyFlowsheetsPrint();
			verifyGlasgowComaScaleBarGraph();
			verifyGlasgowComaScaleLineGraph();
			verifyGlasgowComaScaleDelete();
		}

	}

	public void verifyLimbMovementFlowsheetFunctionalities() throws InterruptedException, IOException {
		CommonLib.changeimplicitwait(3);
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String limb_movement = Config.props.getProperty("limb_movement");
		clickFlowsheetTabName(limb_movement);
		CommonLib.staticWait(2);

		boolean result2 = verifyLimbMovementFlowsheetAdd();
		if (result2) {
			verifyLimbMovementFlowsheetEdit();
			CommonLib.staticWait(2);
			verifyFlowsheetsPrint();
			verifyLimbMovementFlowsheetDelete();
		}

	}

	public void verifyPupilResponseFlowsheetFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		String pupil_response = Config.props.getProperty("pupil_response");
		clickFlowsheetTabName(pupil_response);
		CommonLib.staticWait(1);
		boolean result3 = verifyPupilResponseFlowsheetAdd();
		if (result3) {
			verifyPupilResponseFlowsheetEdit();
			verifyFlowsheetsPrint();
			verifyPupilResponseBarGraph();
			verifyPupilResponseLineGraph();
			verifyPupilResponseDelete();
		}

	}

	public void verifyIntakeOutputFlowsheetFunctionalities() throws InterruptedException, IOException {
		MenuNavigation.menuNav("PatientAssessmentPlus");
		clickFlowsheetTab();
		CommonLib.staticWait(2);
		boolean result4 = verifyIntakeOutputFlowsheetAdd();
		if (result4) {
			verifyIntakeOutputFlowsheetDetailsEdit();
			verifyIntakeOutputFlowsheetDetailsAdd();
			verifyIntakeOutputFlowsheetDetailsRemove();
			verifyIntakeOutputPrint();
			verifyIntakeOutputBarGraph();
			verifyIntakeOutputLineGraph();

		}

	}

	public void verifyImmunizationsFunctionalities() throws InterruptedException, IOException {

		boolean result = verifyImmunizationsAdd();
		if (result) {
			verifyImmunizationsEdit();
			verifyImmunizationsPrint();
			verifyImmunizationsSend();
			verifyImmunizationsQuery();
			verifyImmunizationRefresh();
			verifyImmunizationsRemove();

		} else {
			CustomReporter.MessageLogger("Since add functionality itself is not working and hence couldn't verify the rest of the functionalities, please try again!", CustomReporter.status.Fail);
		}

	}

	public boolean verifyImmunizationsAdd() throws InterruptedException {
		boolean result = false;
		// navigateToImmunizations();
		SetHomeFrame();
		int before_add = getImmunizationRowCount();
		System.out.println(before_add);
		CommonLib.staticWait(2);
		clickImmunizationAdd();
		setDialogFrame();
		immu_value = selectImmunizationValue();
		System.out.println(immu_value);
		clickImmunizationSubmit();
		SetHomeFrame();
		CommonLib.staticWait(2);
		int after_add = getImmunizationRowCount();
		System.out.println(after_add);
		if (immu_value != null) {
			CustomReporter.MessageLogger("Immunizations record " + immu_value + " has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add a Immunizations record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;

	}

	public void verifyImmunizationsEdit() throws InterruptedException {
		selectFirstRow();
		clickImmunizationEdit();
		setDialogFrame();
		String edit_reason = selectEditReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (edit_reason != null) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit an Immunizations record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyImmunizationsPrint() throws IOException {
		// clickImmunizationPrint();
		// CommonLib.staticWait(5);
		// int count = getWindowHandleCount();
		// CommonLib.setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().close();
		// CommonLib.staticWait(5);
		// CommonLib.setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// SetHomeFrame();
		// CommonLib.staticWait(2);
		// if (count == 2) {
		// CustomReporter.MessageLogger("Immunizations " + immu_value + " record
		// has been Printing in the system successfully, which is as expected",
		// CustomReporter.status.Pass);
		// } else {
		// CustomReporter.MessageLogger("Failed to Print the Immunizations
		// record " + immu_value + " in the system, please try again!",
		// CustomReporter.status.Fail);
		// }
		//
		clickImmunizationPrint();
		CommonLib.staticWait(8);
		int count = getWindowHandleCount();
		// Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical_3_3_0\\ImportFile\\pdf_close.exe");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));

		CommonLib.staticWait(2);
		if (count > 1) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the  Immunizations record " + immu_value + " in the system, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyImmunizationsSend() {
		clickImmunizationSend();
		boolean result = getSendAlertMsg();
		if (result) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " record has been Sent to the Registry successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Send the  Immunizations record " + immu_value + " to the Registry, please try again!", CustomReporter.status.Fail);

		}
	}

	public void verifyImmunizationsQuery() {
		clickImmunizationQuery();
		boolean check = getQueryAlertMsg();
		if (check) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " Query has been Sent to the Registry successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Query the  Immunizations record " + immu_value + " in the Registry, please try again!", CustomReporter.status.Fail);

		}
	}

	public void verifyImmunizationRefresh() {
		selectFirstRow();
		boolean before_refresh = getCellFocusValue();
		clickImmunizationRefresh();
		CommonLib.staticWait(2);
		boolean after_refresh = getCellFocusValue();
		if (before_refresh != after_refresh) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " record has been refreshed using the refresh button successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to refresh the  Immunizations record " + immu_value + " in the system, please try again!", CustomReporter.status.Fail);

		}
	}

	public void verifyImmunizationsRemove() throws InterruptedException {
		selectFirstRow();
		clickImmunizationRemove();
		setDialogFrame();
		String remove_reason = selectRemoveReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_reason != null) {
			CustomReporter.MessageLogger("Immunizations " + immu_value + " record has been removed with " + remove_reason + " from the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to remove the Immunizations record from the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public boolean verifyGlucoseFlowsheetAdd() {
		boolean result = false;
		// clickFlowsheetsRotate();
		verifyRotateButton1();
		int before_add = getRowCount();
		clickFlowsheetsAdd();
		setGlucoseValue(60, 70);
		setGlucoseComment();
		setGlucoseReqSeqNumber(40, 90);
		clickFlowsheetSubmit();
		CommonLib.staticWait(4);
		int after_add = getRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Glucose record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add a Glucose record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;
	}

	public boolean verifyHeightWeightFlowsheetsAdd() {
		CommonLib.changeimplicitwait(3);
		boolean result = false;
		verifyRotateButton1();
		int before_add = getRowCount();
		clickFlowsheetsAdd();
		clearHeightWeightFields();
		setHeightValue(5, 6);
		setWeightValue(50, 65);
		clickFlowsheetSubmit();
		CommonLib.staticWait(2);
		setClinicalRulesDialog();
		CommonLib.staticWait(2);
		int after_add = getRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Height and Weight record has been added by the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add a Height and Weight record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;
	}

	public boolean verifyGlascowComaScaleFlowsheetAdd() {
		boolean result = false;
		verifyRotateButton1();
		int before_add = getRowCount();
		clickFlowsheetsAdd();
		selectEyeOpening();
		selectVerbalResponse();
		selectMotorSkills();
		clickFlowsheetSubmit();
		int after_add = getRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Glasgow Coma Scale record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Glasgow Coma Scale record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;

	}

	public boolean verifyLimbMovementFlowsheetAdd() {
		boolean result = false;
		verifyRotateButton1();
		int before_add = getRowCount();
		System.out.println(before_add);
		clickFlowsheetsAdd();
		selectRightArmMovement();
		selectRightLegMovement();
		selectLeftLegMovement();
		clickFlowsheetSubmit();
		int after_add = getRowCount();
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Limb Movement record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Limb Movement record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;
	}

	public boolean verifyPupilResponseFlowsheetAdd() throws InterruptedException {
		boolean result = false;
		verifyRotateButton1();
		int before_add = getRowCount();
		clickFlowsheetsAdd();
		selectRightPupilResponse();
		selectRightPupilSize();
		selectLeftPupilResponse();
		selectLeftPupilSize();
		CommonLib.staticWait(2);
		selectLeftPupillarySizeRadioButton();
		clickFlowsheetSubmit();
		CommonLib.staticWait(2);
		int after_add = getRowCount();
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Pupil Response record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Pupil Response record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;

	}

	public boolean verifyVitalsFlowsheetAdd() throws InterruptedException {
		CommonLib.changeimplicitwait(3);
		boolean result = false;
		verifyRotateButton1();
		CommonLib.staticWait(1);
		int before_add = getRowCount();
		System.out.println(before_add);
		clickFlowsheetsAdd();
		setBloodPressureValue(90, 120);
		clickFlowsheetSubmit();
		setClinicalRulesDialog();
		CommonLib.staticWait(2);
		int after_add = getRowCount();
		System.out.println(after_add);
		if (after_add > before_add) {
			CustomReporter.MessageLogger("Vitals Flowsheet record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Vitals Flowsheet record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;

	}

	public boolean verifyIntakeOutputFlowsheetAdd() {
		boolean result = false;
		clickIntakeAndOutput();
		setDialogFrame();
		String before_add = getIntakeTotalValue();
		clickIntakeOutputAdd();
		setFloatingFrame();
		selectIntakeRadioButton();
		setOralFluidIntakeData(10, 90);
		// setIVFluidIntakeData(10, 90);
		setBloodProductsIntakeData(10, 90);
		clickImmunizationSubmit();
		// clickFlowsheetSubmit();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		clickIntakeOutputAdd();
		setFloatingFrame();
		selectOutputRadioButton();
		setUrineOutputData(10, 90);
		// setEmesisOutputData(10, 90);
		setJPDrainOutputData(10, 90);
		// clickFlowsheetSubmit();
		clickImmunizationSubmit();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		String after_add = getIntakeTotalValue();
		if (after_add != before_add) {
			CustomReporter.MessageLogger("Intake and Output record has been added to the system successfully, which is as expected", CustomReporter.status.Pass);
			result = true;
		} else {
			CustomReporter.MessageLogger("Failed to add an Intake and Output record to the system, which is not as expected", CustomReporter.status.Fail);

		}
		return result;
	}

	public void verifyHeightWeightFlowsheetsEdit() throws InterruptedException {
		verifyRotateButton2();
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		setHeightInCM(160, 165);
		clickFlowsheetSubmit();
		setClinicalRulesDialog();
		boolean result = checkForError();
		if (result == false && edit_reason != null) {
			CustomReporter.MessageLogger("Height and Weight record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Height and Weight record in the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyGlasgowComaScaleFlowsheetEdit() throws InterruptedException {
		verifyRotateButton2();
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		selectVerbalResponse();
		clickFlowsheetSubmit();
		boolean result = checkForError();
		if (result == false && edit_reason != null) {
			CustomReporter.MessageLogger("Glasgow Coma Scale record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Glasgow Coma Scale record in the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyVitalsFlowsheetEdit() throws InterruptedException {
		verifyRotateButton2();
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		setVitalsTemperature(90, 105);
		clickFlowsheetSubmit();
		setClinicalRulesDialog();
		boolean result = checkForError();
		if (result == false && edit_reason != null) {
			CustomReporter.MessageLogger("Vitals Flowsheet record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit Vitals Flowsheet record in the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyLimbMovementFlowsheetEdit() throws InterruptedException {
		verifyRotateButton2();
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		selectLeftLegMovement();
		clickFlowsheetSubmit();
		boolean result = checkForError();
		if (result == false && edit_reason != null) {
			CustomReporter.MessageLogger("Limb Movement record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Limb Movement in the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPupilResponseFlowsheetEdit() throws InterruptedException {
		verifyRotateButton2();
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		selectLeftPupilSize();
		selectForEditLeftPupillarySizeRadio();
		clickFlowsheetSubmit();
		boolean result = checkForError();
		if (result == false && edit_reason != null) {
			CustomReporter.MessageLogger("Pupil Response record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a PupilResponse record in the system, which is not as expected", CustomReporter.status.Fail);

		}
	}

	public void verifyHeightWeightGrowthChart() {
		boolean result = clickGrowthChart();
		if (result) {
			closeAlertMsg();
			setDialogFrame();
			closeAuditDialog();
			CustomReporter.MessageLogger("Growth Chart is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to display the Growth Chart, as the Growth Chart is disabled, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyHeightWeightBarGraph() {
		clickAddedDescDate();
		checkHeightInFtcheckBox();
		checkWeightInKgCheckbox();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyGlasgowComaScaleBarGraph() {
		clickAddedDescDate();
		checkGlasgowComaScaleCheckbox();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyVitalsFlowsheetBarGraph() {
		clickAddedDescDate();
		checkFlowsheetBloodPressureCheckbox();
		checkFlowsheetTemperatureCheckbox();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyPupilResponseBarGraph() {
		clickAddedDescDate();
		// checkRightPupilSizecheckBox();
		checkLeftPupilSizecheckBox();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyGlucoseFlowsheetEdit() throws InterruptedException {
		verifyRotateButton2();
		CommonLib.staticWait(1);
		clickAddedDescDate();
		clickFlowsheetsEdit();
		String edit_reason = getEditReason();
		clickFlowsheetSubmit();
		if (edit_reason != null) {

			CustomReporter.MessageLogger("Glucose record has been edited with " + edit_reason + " to the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit a Glucose record in the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyFlowsheetsPrint() throws IOException {
		// clickFlowsheetsPrint();
		// CommonLib.staticWait(15);
		// int count = getWindowHandleCount();
		// CommonLib.setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().close();
		// CommonLib.staticWait(5);
		// CommonLib.setWindowHandleToLast();
		// CommonLib.staticWait(2);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// SetHomeFrame();
		// CommonLib.staticWait(2);
		// if (count == 2) {
		// CustomReporter.MessageLogger("Selected Flowsheet Record has been
		// Printing in the system successfully, which is as expected",
		// CustomReporter.status.Pass);
		// } else {
		// CustomReporter.MessageLogger("Failed to Print the Selected Flowsheet
		// in the system, please try again!", CustomReporter.status.Fail);
		// }
		//
		clickFlowsheetsPrint();
		CommonLib.staticWait(9);
		int count = getWindowHandleCount();
		// Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical_3_3_0\\ImportFile\\pdf_close.exe");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		if (count > 1) {
			CustomReporter.MessageLogger("Selected Flowsheet Record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the Selected Flowsheet in the system, please try again!", CustomReporter.status.Fail);
		}

	}

	public void verifyGlucoseFlowsheetBarGraph() {
		clickAddedDescDate();
		checkGlucosecheckBox();
		checkGlucoseReqNum();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyGlucoseFlowsheetLineGraph() {

		checkGlucosecheckBox();
		checkGlucoseReqNum();
		clickFlowsheetsLineGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyHeightWeightLineGraph() {

		checkHeightInFtcheckBox();
		checkWeightInKgCheckbox();
		clickFlowsheetsLineGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyGlasgowComaScaleLineGraph() {

		checkGlasgowComaScaleCheckbox();
		clickFlowsheetsLineGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyVitalsFlowsheetLineGraph() {
		checkFlowsheetBloodPressureCheckbox();
		checkFlowsheetTemperatureCheckbox();
		clickFlowsheetsLineGraph();
		CommonLib.staticWait(3);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPupilResponseLineGraph() {

		// checkRightPupilSizecheckBox();
		checkLeftPupilSizecheckBox();
		clickFlowsheetsLineGraph();
		CommonLib.staticWait(5);
		boolean result = closeGraphs();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyGlucoseFlowsheetDelete() throws InterruptedException {
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Glucose record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Glucose record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyHeightWeightDelete() throws InterruptedException {
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Height and Weight record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Height and Weight record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyGlasgowComaScaleDelete() throws InterruptedException {
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Glasgow Coma Scale record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Glasgow Coma Scale record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyVitalsFlowsheetDelete() throws InterruptedException {
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Vitals Flowsheet record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Vitals Flowsheet record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyLimbMovementFlowsheetDelete() throws InterruptedException {
		clickAddedDescDate();
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Limb Movement record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Limb Movement record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyPupilResponseDelete() throws InterruptedException {
		clickFlowsheetsDelete();
		setDialogFrame();
		String remove_text = selectRemovalReason();
		clickImmunizationSubmit();
		SetHomeFrame();
		if (remove_text != null) {
			CustomReporter.MessageLogger("Pupil Response record has been deleted with the reason as: " + remove_text + " from the system successfully, which is as expected", CustomReporter.status.Pass);

		} else {
			CustomReporter.MessageLogger("Failed to delete the Pupil Response record from the system, which is not as expected", CustomReporter.status.Fail);

		}

	}

	public void verifyIntakeOutputFlowsheetDetailsAdd() {

		// clickIntakeAndOutputDetails();
		// setDialogFrame();
		CommonLib.staticWait(5);
		setFloatingFrame();
		clickIntakeOutputAdd();
		setFloatingFrame();
		selectIntakeRadioButton();
		String data = setOralFluidIntakeData(10, 90);
		System.out.println(data);
		CommonLib.staticWait(3);
		clickImmunizationSubmit();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		String added_val = getIntakeTotalDetailsValue();
		System.out.println(added_val);
		if (added_val != data) {
			CustomReporter.MessageLogger("Intake record has been added to the system through Details Icon successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to add an Intake record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyIntakeOutputFlowsheetDetailsEdit() throws InterruptedException {
		CommonLib.staticWait(5);
		clickIntakeAndOutputDetails();
		setDialogFrame();
		clickDetailsRow();
		clickIntakeOutputEdit();
		setFloatingFrame();
		String edit_reason = selectDetailsEditReason();
		clickImmunizationSubmit();
		CommonLib.staticWait(5);
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		if (edit_reason != null) {
			CustomReporter.MessageLogger("Intake record has been edited with reason: " + edit_reason + " to the system through Details Icon successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to edit an Intake record to the system, which is not as expected", CustomReporter.status.Fail);
		}
	}

	public void verifyIntakeOutputFlowsheetDetailsRemove() throws InterruptedException {
		setFloatingFrame();
		clickDetailsRow();
		clickIntakeOutputRemove();
		setFloatingFrame();
		String remove_reason = selectDetailsRemoveReason();
		clickImmunizationSubmit();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		setFloatingFrame();
		clickIntakeOutputClose();
		CommonLib.staticWait(2);
		CommonLib.GetDriver().switchTo().parentFrame();
		CommonLib.staticWait(2);
		SetHomeFrame();
		CommonLib.staticWait(2);
		setDialogFrame();
		CommonLib.staticWait(5);
		if (remove_reason != null) {
			CustomReporter.MessageLogger("Intake record has been removed with reason: " + remove_reason + " from the system through Details Icon successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to remove an Intake record from the system, which is not as expected", CustomReporter.status.Fail);
		}

	}

	public void verifyIntakeOutputPrint() throws IOException {
		// String main_window = CommonLib.getLatestWindowHandle();
		// HashTableRepository.setHash("MainWindow", main_window);
		// clickIntakeOutputPrint();
		// CommonLib.staticWait(3);
		// boolean result = CommonLib.closeLastWindow();
		//
		// // CommonLib.staticWait(5);
		// // int count = getWindowHandleCount();
		// // // System.out.println(count);
		// // // setWindowHandleToLast();
		// // // CommonLib.staticWait(4);
		// // // CommonLib.GetDriver().close();
		// // // CommonLib.staticWait(4);
		// // // CommonLib.GetDriver().switchTo().defaultContent();
		// // // CommonLib.staticWait(5);
		// //
		// Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical_3_3_0\\ImportFile\\pdf_close.exe");
		// // // setWindowHandleToLast();
		// // // CommonLib.staticWait(2);
		// // // CommonLib.GetDriver().switchTo().parentFrame();
		// // // SetHomeFrame();
		// // CommonLib.GetDriver().switchTo().defaultContent();
		// // setDialogFrame();
		// // CommonLib.staticWait(2);
		// CommonLib.GetDriver().switchTo().parentFrame();
		// SetHomeFrame();

		clickIntakeOutputPrint();
		CommonLib.staticWait(8);
		int count = getWindowHandleCount();
		// Runtime.getRuntime().exec("C:\\CantataHealth\\Clinical_3_3_0\\ImportFile\\pdf_close.exe");
		Runtime.getRuntime().exec(System.getProperty("user.dir") + Config.props.getProperty("autoit_script"));
		CommonLib.staticWait(2);
		if (count > 1) {
			CustomReporter.MessageLogger("Selected Flowsheet Record has been Printing in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to Print the Selected Flowsheet in the system, please try again!", CustomReporter.status.Fail);
		}
		// if (result == true) {
		// CustomReporter.MessageLogger("Intake and Output record has been
		// Printing in the system successfully, which is as expected",
		// CustomReporter.status.Pass);
		// } else {
		// CustomReporter.MessageLogger("Failed to Print the Intake and Output
		// record in the system, please try again!",
		// CustomReporter.status.Fail);
		// }

	}

	public void verifyIntakeOutputBarGraph() {
		// CommonLib.GetDriver().switchTo().defaultContent();
		// setDialogFrame();
		setFloatingFrame();
		checkIntakeTotalcheckBox();
		checkOutputTotalcheckBox();
		clickFlowsheetsBarGraph();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		setFloatingFrame();
		CommonLib.staticWait(5);
		boolean result = clickIntakeOutputBarGraphClose();
		if (result == true) {
			CustomReporter.MessageLogger("Bar Graph for Intake and Output is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Bar Graph for Intake and Output, which is not as expected", CustomReporter.status.Pass);

		}

	}

	public void verifyIntakeOutputLineGraph() {
		// CommonLib.GetDriver().switchTo().defaultContent();
		// setDialogFrame();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		checkIntakeTotalcheckBox();
		checkOutputTotalcheckBox();
		clickFlowsheetsBarGraph();
		CommonLib.staticWait(5);
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		setFloatingFrame();
		boolean result = clickIntakeOutputBarGraphClose();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		setDialogFrame();
		clickIntakeOutputClose();
		CommonLib.GetDriver().switchTo().parentFrame();
		SetHomeFrame();
		if (result == true) {
			CustomReporter.MessageLogger("Line Graph for Intake and Output is displaying in the system successfully, which is as expected", CustomReporter.status.Pass);
		} else {
			CustomReporter.MessageLogger("Failed to display the Line Graph for Intake and Output, which is not as expected", CustomReporter.status.Pass);

		}

	}

}
