package Results.testcases;

import Results.pages.Results_page;

public class TranscribeResults {

	Results_page obj = new Results_page();

	public void transcribe() {
		obj.menuNav("TranscribeResults");
		obj.clickTarnscribeRow();
		obj.clickTranscribe();
		obj.setTranscribeFrame();
		obj.clickClose();
		obj.setMainFrame();
		obj.clickTranscribe();
		obj.setDate();
		obj.setTime();
		obj.selectDicPhysician();
		obj.clickSumbit();
	}
}
