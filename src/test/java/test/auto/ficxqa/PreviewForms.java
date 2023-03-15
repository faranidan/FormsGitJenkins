package test.auto.ficxqa;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Hooks;
import pageObjects.FormEditorObjects;

@Listeners(Resources.Listeners.class)

public class PreviewForms extends Hooks {

	public PreviewForms() throws IOException {
		super();
	}

	@Test
	public void previewSum() throws InterruptedException, IOException {
		FormEditorObjects forms = new FormEditorObjects();
		forms.openSavedForm("Automation.v3");
		forms.getPreviewForm().click();
		Thread.sleep(3000);
		forms.switchTab();
		forms.devTools("https://dev19.callvu.net/LAN/APIGateway/CallAPI?Name=Sum");
		forms.previewSum(12, 12);
		forms.previewDone();
	}

	@Test
	public void previewDropdown() throws IOException, InterruptedException {
		FormEditorObjects forms = new FormEditorObjects();
		forms.openSavedForm("idanActionsDD");
		forms.getPreviewForm().click();
		Thread.sleep(3000);
		forms.switchTab();
		forms.dropdownFill1();
		forms.dropdownFill2();
		forms.dropdownSelectedFieldClear();
		forms.dropdownDataClear1();
		forms.dropdownDataClear2();

	}

}
