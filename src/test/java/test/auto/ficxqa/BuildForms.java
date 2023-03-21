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
		forms.addField(forms.getShortText(), forms.input4);
		forms.addField(forms.getShortText(), forms.input3);
		forms.addField(forms.getShortText(), forms.input2);
		forms.addField(forms.getShortText(), forms.input1);
		forms.saveForm();

		forms.createAction();
		forms.createSumApi("SUM-1", "one != '' && two != ''", forms.input1, forms.input2, forms.input3);
		forms.newSumActionISA();
		forms.createSumApi("SUM-2", "sum1 != ''", forms.input3, forms.input3, forms.input4);

		forms.getActionCancel().click();
		forms.saveForm();
	}

	@Test
    public void buildBasicFields() throws IOException, InterruptedException{
        ExtentManager.log("Starting test1 for WIP...");
        FormEditorObjects forms = new FormEditorObjects();
        forms.renameFormTitleBlock("AutoAllBasicFields", "All basic fields", "Block1");
        forms.addField(forms.getLongText(), "lt1");
        forms.addField(forms.getParagraph(), "prg1");
        forms.addField(forms.getNumberFld(), "nmb1");
        forms.addField(forms.getPhoneNmbr(), "phn1");
        forms.addField(forms.getEmailFld(), "email1");
        forms.addField(forms.getPasswordFld(), "pass1");
        forms.getAddStep().click();
        forms.saveForm();

        forms.addField(forms.getIdFld(), "id1");
        forms.addField(forms.getDateFld(), "date1");
        forms.addField(forms.getTimeFld(), "time1");
        forms.addField(forms.getCurrencyFld(), "crr1");
        forms.addField(forms.checkboxField, "chkbox`");
        forms.addField(forms.radioField, "radio1");
        forms.saveForm();
    }

}
