package test.auto.ficxqa;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Hooks;
import pageObjects.FormEditorObjects;

@Listeners(Resources.Listeners.class)

public class SumActionsTest extends Hooks {

	public SumActionsTest() throws IOException {
		super();
	}

	@Test
	public void sumInputs() throws InterruptedException, IOException {
		FormEditorObjects forms = new FormEditorObjects();
		
		forms.renameFormTitleBlock("Automation.v3: Sum action API", "Page1", "Block1");
		
		String input1 = "one";
		String input2 = "two";
		String input3 = "sum1";
		String input4 = "n2sums";
		forms.addShortText(input4);
		forms.addShortText(input3);
		forms.addShortText(input2);
		forms.addShortText(input1);	
		forms.savingForm();

		forms.createAction();
		forms.createSumApi("SUM-1", "one != '' && two != ''", input1, input2, input3);
		forms.newSumActionISA();
		forms.createSumApi("SUM-2", "sum1 != ''", input3, input3, input4);
		
		forms.getActionCancel().click();		
		forms.savingForm();
		Thread.sleep(3000);	
		
	}
	
	@Test
	public void previewSum() throws InterruptedException, IOException {
		FormEditorObjects forms = new FormEditorObjects();
		forms.openSavedForm("Automation.v2");	
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
