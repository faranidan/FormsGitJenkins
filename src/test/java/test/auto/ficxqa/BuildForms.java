package test.auto.ficxqa;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.FormEditorObjects;

@Listeners(Resources.Listeners.class)

public class BuildForms extends Hooks {

	public BuildForms() throws IOException {
		super();
	}

	@Test
	public void buildSumApi() throws InterruptedException, IOException {
		FormEditorObjects forms = new FormEditorObjects();
		ExtentManager.log("STARTING buildSumApi test...");
		forms.renameFormTitleBlock("Auto SumAPI", "Page1", "Block1");
		forms.addShortText(forms.input4);
		forms.addShortText(forms.input3);
		forms.addShortText(forms.input2);
		forms.addShortText(forms.input1);
		forms.savingForm();

		forms.createAction();
		forms.createSumApi("SUM-1", "one != '' && two != ''", forms.input1, forms.input2, forms.input3);
		forms.newSumActionISA();
		forms.createSumApi("SUM-2", "sum1 != ''", forms.input3, forms.input3, forms.input4);

		forms.getActionCancel().click();
		forms.savingForm();
	}

}
