package test.auto.ficxqa;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.FormEditorObjects;

@Listeners(Resources.Listeners.class)

public class PreviewForms extends Hooks {

	public PreviewForms() throws IOException {
		super();
	}

	@Test
    public void previewfileUpload() throws InterruptedException, IOException{
        ExtentManager.log("Starting previewFileUpload test...");
        FormEditorObjects forms = new FormEditorObjects();
        forms.openSavedForm("AutoFileUpload");
        forms.getPreviewForm().click();
		ExtentManager.pass("Opened form: AutoFileUpload & clicked Preview");
		forms.switchTab();
		ExtentManager.pass("Switched to preview tab");
        forms.previewUploadFiles1();
        forms.previewUploadFiles2();
        forms.previewUploadFiles3();
        Thread.sleep(3000);
    }

	@Test
	public void previewSum() throws InterruptedException, IOException {
		FormEditorObjects forms = new FormEditorObjects();
		ExtentManager.log("STARTING previwSum test...");
		forms.openSavedForm("Automation.v3");
		forms.getPreviewForm().click();
		Thread.sleep(3000);
		forms.switchTab();
		forms.devTools("https://qa19.callvu.net/LAN/APIGateway/CallAPI?Name=Sum");
		forms.previewSum(12, 12);
		forms.previewDone();
	}

	@Test
	public void previewDropdown() throws IOException, InterruptedException {
		FormEditorObjects forms = new FormEditorObjects();
		ExtentManager.log("STARTING previwDropdown test...");
		forms.openSavedForm("idanActionsDD[4autoTest]");
		forms.getPreviewForm().click();
		Thread.sleep(3000);
		forms.switchTab();
		forms.dropdownFill1();
		forms.dropdownFill2();
		forms.dropdownSelectedFieldClear();
		forms.dropdownDataClear1();
		forms.dropdownDataClear2();
	}

	@Test
	public void previewRules() throws IOException, InterruptedException {
		FormEditorObjects forms = new FormEditorObjects();
		ExtentManager.log("STARTING previewRules test...");
		forms.openSavedForm("AutoRulesBasicFields");
		forms.getPreviewForm().click();
		Thread.sleep(3000);
		forms.switchTab();
		forms.prvwRulesStep1();
		forms.prvwRulesStep2();
		forms.prvwRulesStep3();
		Thread.sleep(3000);
	}

}
