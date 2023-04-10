package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v108.network.Network;
import org.openqa.selenium.devtools.v108.network.model.RequestId;
import org.openqa.selenium.devtools.v108.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BasePage;
import base.ExtentManager;

public class FormEditorObjects extends BasePage {
	public WebDriver driver;

	public FormEditorObjects() throws IOException, InterruptedException {
		super();
		PageFactory.initElements(getDriver(), this);
	}

	JavascriptExecutor jse = (JavascriptExecutor) getDriver();
	Actions act = new Actions(getDriver());

	public void specificAttUpload(String size, String fileTypes1, String fileTypes2, String filesNumberLimit, String errMsg) throws InterruptedException, IOException{
		specificAtt.click();
		waitForElement(limitSize, Duration.ofSeconds(3));
		limitSize.click();
		act.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, size).perform();
		fileTypes.click();
		act.sendKeys(fileTypes1, Keys.ENTER).perform();
		act.sendKeys(fileTypes2, Keys.ENTER).perform();
		acceptMultiFiles.click();
		limitNumber.click();
		act.sendKeys(Keys.BACK_SPACE,filesNumberLimit).perform();
		limitErrorMsg.click();
		act.sendKeys(errMsg).perform();
	}

	public void addField(WebElement field, String inputLabelID) throws InterruptedException, IOException {
		if (field.isDisplayed() == false) {
			getBasicFieldsDropdown().click();
			waitForElement(field, Duration.ofSeconds(6));
		}
		jse.executeScript(jsDragnDrop(), field, getDropArea1());
		getGenericAttrbts().click();
		getIntID().click();
		act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(inputLabelID).perform();
		getGenAttLabel().click();
		act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(inputLabelID).perform();
	}

	public void renameFormTitleBlock(String formName, String titleName, String blockName)
			throws InterruptedException, IOException {
		getRenameForm().sendKeys(Keys.BACK_SPACE, formName);
		getFormTitle().sendKeys(titleName);
		getBlock1Title().sendKeys(Keys.BACK_SPACE, blockName);
		Thread.sleep(900);
	}

	public void openSavedForm(String name) throws InterruptedException, IOException {
		ExtentManager.log("Starting openSavedForm test...");
		waitForElement(getFolderActions(), Duration.ofSeconds(6));
		getFolderActions().click();
		waitForElement(getOpenForm(), Duration.ofSeconds(6));
		getOpenForm().click();
		ExtentManager.pass("Got to forms folder");
		waitForElement(getFirstForm(), Duration.ofSeconds(6));
		for (int i = 0; i < formList.size(); i++) {
			String formName = formList.get(i).getText();
			if (formName.contains(name)) {
				act.scrollToElement(formList.get(i)).click(formList.get(i)).perform();
			}
		}
		ExtentManager.pass("Listed all forms and selected the desired form to open");
		waitForElement(getOpenBtn(), Duration.ofSeconds(3));
		getOpenBtn().click();
		waitForInvisibility(getOpenBtn(), Duration.ofSeconds(9));
		ExtentManager.pass("Opened desired form");
		Thread.sleep(600);
	}

	public void saveForm() throws InterruptedException, IOException {
		ExtentManager.log("Starting saveForm method");
		waitForElement(getSaveBtn(), Duration.ofSeconds(6));
		getSaveBtn().click();
		ExtentManager.pass("Clicked main save button");
		Thread.sleep(600);
		try{
			saveBtnTest.click();
			Thread.sleep(600);
			ExtentManager.pass("Clicked save button notification");
		} catch(org.openqa.selenium.NoSuchElementException e){
			ExtentManager.pass("no save notification");
		}
	}

	public void createAction() throws InterruptedException, IOException {
		getSettings().click();
		act.scrollToElement(getCreateAction()).click(getCreateAction()).perform();
		Thread.sleep(1200);
	}

	public void createSumApi(String apiName, String actionCode, String input1, String input2, String sum)
			throws InterruptedException, IOException {
		getSelectAPI().click();
		Thread.sleep(300);
		act.scrollToElement(getSumAPI()).click(getSumAPI()).perform();
		Thread.sleep(300);
		act.click(getActionName()).sendKeys(apiName).perform();
		Thread.sleep(300);
		act.click(getActionCode()).sendKeys(actionCode).perform();
		act.click(selectField1).sendKeys(input1).sendKeys(Keys.ENTER).perform();
		act.click(selectField2).sendKeys(input2).sendKeys(Keys.ENTER).perform();
		Thread.sleep(300);
		act.scrollToElement(getActionSumFld()).click(getActionSumFld()).sendKeys(sum, Keys.ENTER).perform();
		Thread.sleep(300);
		act.scrollToElement(getActionSaveBtn()).click(getActionSaveBtn()).perform();
		Thread.sleep(900);
	}

	public void newSumActionISA() throws InterruptedException, IOException {
		act.scrollToElement(getCreateNewActionBtn()).click(getCreateNewActionBtn()).perform();
		Thread.sleep(300);
		getSelectAPI().click();
		Thread.sleep(300);
		act.scrollToElement(getListAPI()).click(getListAPI()).perform();
		Thread.sleep(300);
		getSelectAPI().click();
		Thread.sleep(300);
		act.scrollToElement(getSumAPI()).click(getSumAPI()).perform();
		Thread.sleep(300);
	}

	public void switchTab() throws InterruptedException, IOException {
		ExtentManager.log("Starting switchTab test...");
		String MainWindow = getDriver().getWindowHandle(); 
		Set<String> handles = getDriver().getWindowHandles(); 
		Iterator<String> iterate = handles.iterator(); 
		while (iterate.hasNext()) { 
			String child = iterate.next(); 
			if (!MainWindow.equalsIgnoreCase(child)) { 
				getDriver().switchTo().window(child);
				ExtentManager.pass("Switched succesfully to second tab. Now previewing form.");
			}
		}
	}

	public void previewSum(Integer var1, Integer var2) throws InterruptedException, IOException {
		ExtentManager.log("Starting previewSum test...");
		getPrvwOneInpt().sendKeys(var1.toString());
		getPrvwTwoInpt().sendKeys(var2.toString());
		getPrvwSum1Inpt().click();
		ExtentManager.pass("Sent selected values: " + var1.toString() + "+" + var2.toString());
		Thread.sleep(900);
		if (getPrvwSum1Inpt().getAccessibleName().equals("sum1 " + (var1 + var2))) {
			ExtentManager.pass("Success! sum1 = " + (var1 + var2));
			System.out.println("Success! sum1 = " + (var1 + var2));
		} else {
			ExtentManager.fail("Failed! " + getPrvwSum1Inpt().getAccessibleName());
			System.out.println("Failed! " + getPrvwSum1Inpt().getAccessibleName());
		}
		prvwSum2Input.click();
		Thread.sleep(900);
		if (prvwSum2Input.getAccessibleName().equals("n2sums " + ((var1 + var2) * 2))) {
			ExtentManager.pass("Success! sum2 = " + ((var1 + var2) * 2));
			System.out.println("Success! sum2 = " + ((var1 + var2) * 2));
		} else {
			ExtentManager
					.fail("Failed! sum2 != " + ((var1 + var2) * 2) + ". It is: " + prvwSum2Input.getAccessibleName());
			System.out.println(
					"Failed! sum2 != " + ((var1 + var2) * 2) + ". It is: " + prvwSum2Input.getAccessibleName());
		}
		Thread.sleep(600);
	}

	public void previewDone() throws InterruptedException, IOException {
		ExtentManager.log("Starting previewDone test...");
		getPrvwDone().click();
		waitForElement(formEndImg, Duration.ofSeconds(9));
		Assert.assertEquals(getDriver().getTitle(), "Thank You");
		ExtentManager.pass("Got to 'Thank You' page, form ended successfully");
	}

	public void devTools(String url) throws InterruptedException, IOException {
		ExtentManager.log("Starting devTools test...");
		DevTools devTools = ((HasDevTools) getDriver()).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		RequestId[] id = new RequestId[1];
		ExtentManager.pass("Setup configurations for devTools done");
		devTools.addListener(Network.responseReceived(), response -> {
			Response res = response.getResponse();
			id[0] = response.getRequestId();
			if (res.getUrl().equals(url)) {
				String responseBody = devTools.send(Network.getResponseBody(id[0])).getBody();
				System.out.println("Response url: " + res.getUrl());
				System.out.println("Response status: " + res.getStatus());
				System.out.println("Response body: " + responseBody);
			}
		});
		ExtentManager.pass("Response of url " + url + " recorded to console logs");
	}

	public void dropdownFill1() throws InterruptedException, IOException {
		ExtentManager.log("Starting dropdownFill1 test...");
		fatherDropdown.click();
		Thread.sleep(600);
		act.moveToElement(fddItem1).click(fddItem1).perform();
		act.moveToElement(dropdown1).click(dropdown1).perform();
		waitForElement(dd1Item2, Duration.ofSeconds(9));
		dd1Item2.click();
		Thread.sleep(600);
		autocomplete1.click();
		waitForElement(ac1Item3, Duration.ofSeconds(3));
		ac1Item3.click();
		multiSelect1.click();
		ms1Item4.click();
		ms1Item5.click();
		ExtentManager.pass("Selected fields for fatherDropdown, dropdown1 & autocomplete1 successfully");
	}

	public void dropdownFill2() throws InterruptedException, IOException {
		ExtentManager.log("Starting dropdownFill2 test...");
		formHeader.click();
		act.moveToElement(dropdown2).click(dropdown2).perform();
		Thread.sleep(3600);
		// waitForElement(dd2Item1,Duration.ofSeconds(12));
		dd2Item1.click();
		autocomplete2.click();
		Thread.sleep(600);
		// waitForElement(ac2Item1,Duration.ofSeconds(6));
		ac2Item1.click();
		multiSelect2.click();
		ms2Item1.click();
		ExtentManager.pass("Selected fields for dropdown2, autocomplete2 & multiSelect2 successfully");
	}

	public void dropdownSelectedFieldClear() throws InterruptedException {
		ExtentManager.log("Starting dropdownSelectedFieldClear test...");
		fddClear.click();
		ExtentManager.pass("Cleared fatherDropdown");
		Thread.sleep(4500);
		if (autocomplete1.getAttribute("placeholder") != null) {
			ExtentManager.log("Autocoplete1 text: " + autocomplete1.getAttribute("placeholder"));
			if (autocomplete1.getAttribute("placeholder").contains("Start typing")) {
				ExtentManager.pass("Autocomplete1 field cleared");
			} else {
				ExtentManager.fail("Autocomplete1 field DID NOT clear");
			}
		} else {
			ExtentManager.fail("Autocomplete1 field DID NOT clear");
		}
		if (dropdown1.getAttribute("placeholder") != null) {
			ExtentManager.log("Dropdown1 text: " + dropdown1.getAttribute("placeholder"));
			if (dropdown1.getAttribute("placeholder").contains("Please Select")) {
				ExtentManager.pass("Dropdown1 field cleared");
			} else {
				ExtentManager.fail("Dropdown1 field DID NOT clear");
			}
		} else {
			ExtentManager.fail("Dropdown1 field DID NOT clear");
		}

		if (!ms1Selected.isDisplayed()) {
			ExtentManager.pass("Multi-Select1 field cleared");
		} else {
			ExtentManager.fail("Multi-Select1 field DID NOT clear");
		}

		if (autocomplete2.getAttribute("placeholder") != null) {
			ExtentManager.log("Autocoplete2 text: " + autocomplete2.getAttribute("placeholder"));
			if (autocomplete2.getAttribute("placeholder").contains("Start typing")) {
				ExtentManager.pass("autocomplete2 field cleared");
			} else {
				ExtentManager.fail("autocomplete2 field DID NOT clear");
			}
		} else {
			ExtentManager.fail("autocomplete2 field DID NOT clear");
		}
		if (dropdown2.getAttribute("placeholder") != null) {
			ExtentManager.log("Dropdown2 text: " + dropdown2.getAttribute("placeholder"));
			if (dropdown2.getAttribute("placeholder").contains("Please Select")) {
				ExtentManager.pass("dropdown2 field cleared");
			} else {
				ExtentManager.fail("dropdown2 field DID NOT clear");
			}
		} else {
			ExtentManager.fail("dropdown2 field DID NOT clear");
		}
		if (!ms2Selected.isDisplayed()) {
			ExtentManager.pass("Multi-Select2 field cleared");
		} else {
			ExtentManager.fail("Multi-Select2 field DID NOT clear");
		}
	}

	public void dropdownDataClear1() throws InterruptedException {
		ExtentManager.log("Starting dropdownDataClear1 test...");
		autocomplete1.click();
		Thread.sleep(600);
		if (ac1Item3.isDisplayed()) {
			ExtentManager.fail("Autocomplete1 API data DID NOT clear");
		} else {
			ExtentManager.pass("Autocomplete1 API data cleared");
		}
		formHeader.click();
		act.moveToElement(dropdown1).click(dropdown1).perform();
		Thread.sleep(600);
		if (dd1Item2.isDisplayed()) {
			ExtentManager.fail("Dropdown1 API data DID NOT clear");
		} else {
			ExtentManager.pass("Dropdown1 API data cleared");
		}
		formHeader.click();
		multiSelect1.click();
		Thread.sleep(600);
		if (ms1Item4.isDisplayed()) {
			ExtentManager.fail("Multi-select1 API data DID NOT clear");
		} else {
			ExtentManager.pass("Multi-select1 API data cleared");
		}
	}

	public void dropdownDataClear2() throws InterruptedException {
		ExtentManager.log("Starting dropdownDataClear2 test...");
		formHeader.click();
		autocomplete2.click();
		Thread.sleep(600);
		if (ac2Item1.isDisplayed()) {
			ExtentManager.fail("Autocomplete2 API data DID NOT clear");
		} else {
			ExtentManager.pass("Autocomplete2 API data cleared");
		}
		formHeader.click();
		act.moveToElement(dropdown2).click(dropdown2).perform();
		Thread.sleep(600);
		if (dd2Item1.isDisplayed()) {
			ExtentManager.fail("Dropdown2 API data DID NOT clear");
		} else {
			ExtentManager.pass("Dropdown2 API data cleared");
		}
		formHeader.click();
		multiSelect2.click();
		Thread.sleep(600);
		if (ms2Item1.isDisplayed()) {
			ExtentManager.fail("Multi-select2 API data DID NOT clear");
		} else {
			ExtentManager.pass("Multi-select2 API data cleared");
		}
	}

	public void previewUploadFiles1() throws InterruptedException, IOException{
		ExtentManager.log("Starting Upload1 method");
		waitForElement(prvwFileUp1, Duration.ofSeconds(6));
		prvwFileUp1.click();
		Thread.sleep(600);
		Runtime.getRuntime().exec("C:\\Users\\idan.faran\\Desktop\\resources\\Files\\upload test files\\autoIT\\upload1.exe");
		Thread.sleep(600);
		ExtentManager.pass("Uploaded file1 successfully");
		try{
			if(errorAlert.getText().contains("File can not be empty")){
				ExtentManager.pass("Upload1 test Passed. Empty file msg: "+errorAlert.getText());
			} else {
				ExtentManager.fail("Upload1 test Failed: "+errorAlert.getText());
			}
		} catch (Exception e){
			ExtentManager.fail("Upload1: No error message. Empty file uploaded successfully");
		}
	}

	public void previewUploadFiles2() throws IOException, InterruptedException{
		ExtentManager.log("Starting Upload2 method");
		prvwFileUp2.click();
		Thread.sleep(600);
		Runtime.getRuntime().exec("C:\\Users\\idan.faran\\Desktop\\resources\\Files\\upload test files\\autoIT\\upload2file1.exe");
		ExtentManager.pass("Uploaded file1 successfully");
		Thread.sleep(600);
		try{
			if(uploadedFileName.getText().contains("Form")){
				ExtentManager.pass("Upload2 file1 Passed. Uploaded file: "+uploadedFileName.getText());
			} else {
				ExtentManager.fail("Upload2 file1 Failed: "+uploadedFileName.getText());
			}
		} catch (Exception e){
			ExtentManager.fail("Upload2 file1 failed. No uploaded file text");
		}
		prvwFileUp2.click();
		Thread.sleep(600);
		Runtime.getRuntime().exec("C:\\Users\\idan.faran\\Desktop\\resources\\Files\\upload test files\\autoIT\\upload2file2.exe");
		Thread.sleep(600);
		ExtentManager.pass("Uploaded file2 successfully");
		try{
			if(errorAlert.getText().contains("You tried to upload file(s) with forbidden extension(s)")){
				ExtentManager.pass("Upload2 file2 Passed. Error msg: "+errorAlert.getText());
			} else {
				ExtentManager.fail("Upload2 file2 Failed: "+errorAlert.getText());
			}
		} catch (Exception e){
			ExtentManager.fail("Upload2 file2 Failed. No error message. File uploaded successfully");
		}
		
	}

	public void previewUploadFiles3() throws IOException, InterruptedException{
		ExtentManager.log("Starting Upload3 method");
		prvwFileUp3.click();
		Thread.sleep(600);
		Runtime.getRuntime().exec("C:\\Users\\idan.faran\\Desktop\\resources\\Files\\upload test files\\autoIT\\upload3file1.exe");
		ExtentManager.pass("Uploaded file1 successfully");
		Thread.sleep(600);
		try{
			if(uploadedFileName2.getText().contains("Regression")){
				ExtentManager.pass("Upload3 file1 Passed. Uploaded file: "+uploadedFileName2.getText());
			} else {
				ExtentManager.fail("Upload3 file1 Failed: "+uploadedFileName2.getText());
			}
		} catch (Exception e){
			ExtentManager.fail("Upload3 file1 Failed. No uploaded file text");
		}
		prvwFileUp3.click();
		Thread.sleep(600);
		Runtime.getRuntime().exec("C:\\Users\\idan.faran\\Desktop\\resources\\Files\\upload test files\\autoIT\\upload3file2.exe");
		ExtentManager.pass("Uploaded file2 successfully");
		Thread.sleep(600);
		try{
			if(errorAlert2.getText().contains("The file you are trying to upload is larger")){
				ExtentManager.pass("Upload3 file2 Passed. Error msg: "+errorAlert2.getText());
			} else {
				ExtentManager.pass("Upload3 file2 Failed: "+errorAlert2.getText());
			}
		} catch (Exception e){
			ExtentManager.pass("Upload3 file2 failed. No error message. File uploaded successfully");
		}
		
	}

	public String input1 = "one";
	public String input2 = "two";
	public String input3 = "sum1";
	public String input4 = "n2sums";

	@FindBy(xpath = "(//li[@class='file-names-list'])[2]") public WebElement uploadedFileName2;
	@FindBy(xpath = "//div[normalize-space()='The file you are trying to upload is larger than the 0.5 MB limit']")
	public WebElement errorAlert2;
	@FindBy(css = ".file-names-list") public WebElement uploadedFileName;
	@FindBy(css = ".text-error-alert") public WebElement errorAlert;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_1')]") public WebElement prvwFileUp1;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_2')]") public WebElement prvwFileUp2;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_3')]") public WebElement prvwFileUp3;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_4')]") public WebElement prvwFileUp4;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_5')]") public WebElement prvwFileUp5;
	@FindBy(xpath = "//div[contains(text(),'fileUpload_6')]") public WebElement prvwFileUp6;

	@FindBy(xpath = "//div[contains(text(),'Specific Attributes')]") public WebElement specificAtt;
	@FindBy(xpath = "//input[@aria-label='Limit Size (in MB)']") public WebElement limitSize;
	@FindBy(xpath = "//label[normalize-space()='Accept Multiple Files']") public WebElement acceptMultiFiles;
	@FindBy(xpath = "//input[@aria-label='Allowed File Types']") public WebElement fileTypes;
	@FindBy(xpath = "//input[@aria-label='Limit Number']") public WebElement limitNumber;
	@FindBy(xpath = "//input[@aria-label='Error Message (Exceeding Number Of Files)']") public WebElement limitErrorMsg;

	@FindBy(xpath = "//div[contains(text(),'1')]") public WebElement tempUploadFile;
	@FindBy(xpath = "//div[normalize-space()='Checkbox']") public WebElement checkboxField;
	@FindBy(xpath = "//div[normalize-space()='Radio']") public WebElement radioField;
	@FindBy(xpath = "//div[normalize-space()='File Upload']") public WebElement fileUploadField;
	@FindBy(xpath = "//div[normalize-space()='Save']") public WebElement saveBtnTest;
	@FindBy(css = "img[alt='CallVU']")
	public WebElement formEndImg;
	@FindBy(css = ".ms1 .v-select__selection--comma")
	public WebElement ms1Selected;
	@FindBy(css = ".ms2 .v-select__selection--comma")
	public WebElement ms2Selected;
	@FindBy(css = "#app div:nth-of-type(6) [role='listitem']:nth-of-type(1) .v-list__tile__title")
	public WebElement dd2Item1;
	@FindBy(css = "#app div:nth-of-type(4) [role='listitem']:nth-of-type(1) .v-list__tile__title")
	public WebElement ac2Item1;
	@FindBy(css = "#app div:nth-of-type(2) [role='listitem']:nth-of-type(1) .v-list__tile--link")
	public WebElement ms2Item1;
	@FindBy(css = ".dd2 .v-select__selections")
	public WebElement dropdown2;
	@FindBy(css = ".dd1 .v-select__selections")
	public WebElement dropdown1;
	@FindBy(css = ".ac1.v-tooltip.v-tooltip--bottom input[role='combobox']")
	public WebElement autocomplete1;
	@FindBy(css = ".ms1 .v-select__selections")
	public WebElement multiSelect1;
	@FindBy(css = ".ac2.v-tooltip.v-tooltip--bottom input[role='combobox']")
	public WebElement autocomplete2;
	@FindBy(css = ".ddf .v-select__selections")
	public WebElement fatherDropdown;
	@FindBy(css = ".ms2 .v-select__selections")
	public WebElement multiSelect2;
	@FindBy(css = "div:nth-of-type(14) > .theme--light.v-card.v-select-list > div[role='list'] > div:nth-of-type(1)")
	public WebElement fddItem1;
	@FindBy(css = "#app div:nth-of-type(12) [role='listitem']:nth-of-type(2) .v-list__tile__title")
	public WebElement dd1Item2;
	@FindBy(css = "#app div:nth-of-type(10) [role='listitem']:nth-of-type(3) .v-list__tile__title")
	public WebElement ac1Item3;

	@FindBy(css = "#app div:nth-of-type(8) [role='listitem']:nth-of-type(4) .v-list__tile--link")
	public WebElement ms1Item4;
	@FindBy(css = "#app div:nth-of-type(8) [role='listitem']:nth-of-type(5) .v-list__tile--link")
	public WebElement ms1Item5;
	@FindBy(css = ".ddf .material-icons")
	public WebElement fddClear;
	@FindBy(css = ".block_lezusi58 [tabindex]")
	public WebElement formHeader;

	@FindBy(css = ".n2sums.v-tooltip.v-tooltip--bottom input[type='text']")
	public WebElement prvwSum2Input;
	@FindBy(css = ".form-help-action-button.v-btn--depressed.theme--dark .v-btn__content")
	public WebElement rulesBtn;
	@FindBy(css = ".theme--light.v-card.v-sheet > div[role='list'] > div")
	public List<WebElement> formList;
	@FindBy(css = "div[role='list'] > div:nth-of-type(3) .layout > div:nth-of-type(2) > div[role='combobox']  .v-input__slot.white")
	public WebElement selectField2;
	@FindBy(css = ".theme--light:nth-of-type(4) [role='listitem']:nth-of-type(2) [class='flex pr-4 xs4']:nth-of-type(2) .v-input__slot")
	public WebElement selectField1;

	By basicFieldsDropdown = By.cssSelector(
			".v-expansion-panel__container:nth-of-type(2) .v-expansion-panel__header .justify-center .flex:nth-of-type(2)");
	By dropArea1 = By.cssSelector(".main-area .align-center");
	By dropNewBlock = By.cssSelector(".add-field-wrapper.flex");
	By formTitle = By
			.cssSelector(".form-title.theme--light.v-card.v-sheet.v-sheet--tile .form-name-cont > .editable.s-ltr");
	By renameForm = By.cssSelector(
			".elevation-0.elevation-6.theme--dark.toolbar-bottom-border.v-toolbar.v-toolbar--clipped.v-toolbar--fixed .form-name-cont > .editable.s-ltr");
	By saveForm = By.cssSelector(".lime.theme--dark.v-btn.v-btn--round > .v-btn__content");
	By previewForm = By.cssSelector(
			".lime--text.theme--dark.v-btn.v-btn--depressed.v-btn--outline.v-btn--round > .v-btn__content");
	By shortText = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(1) .element");
	By longText = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(2) .element");
	By paragraph = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(3) .element");
	By numberFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(4) .element");
	By phoneNmbr = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(5) .element");
	By emailFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(6) .element");
	By psswrdFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(7) .element");
	By signFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(8) .element");
	By idFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(9) .element");
	By dateFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(10) .element");
	By timeFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(11) .element");
	By dropdownFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(13) .element");
	By autoComplete = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(14) .element");
	By multiSelect = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(15) .element");
	By checkboxFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(16) .element");
	By radioFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(17) .element");
	By radioKVFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(18) .element");
	By fileUpldFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(19) .element");
	By displayPDFFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(20) .element");
	By dynamicPDFFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(21) .element");
	By subtitleFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(22) .element");
	By commentFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(23) .element");
	By crrncyFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(12) .element");
	By mobilePgBrkFld = By.cssSelector("[tabindex='0']:nth-of-type(2) div:nth-of-type(24) .element");

	By block1Title = By.cssSelector(".primary--text .editable");
	By genericAttrbts = By.cssSelector(".v-form .theme--dark.v-expansion-panel > li:nth-of-type(1) .flex");
	By readOnlyAtt = By.cssSelector("div:nth-of-type(11) > .v-input__control > .v-input__slot > .theme--light.v-label");
	By requiredAtt = By.cssSelector("div:nth-of-type(12) > .v-input__control > .v-input__slot > .theme--light.v-label");
	By hiddenAtt = By.cssSelector("div:nth-of-type(13) > .v-input__control > .v-input__slot > .theme--light.v-label");
	By addStep = By.cssSelector(".blue--text .material-icons");
	By genAttLabel = By.cssSelector(
			".element-list.v-card__text > div:nth-of-type(2) > .v-input__control > .v-input__slot > .v-text-field__slot > input[type='text']");
	By intID = By.cssSelector("[aria-label='Integration ID']");
	// nth-of-type(xx) is a dynamic field
	By saveConfirm = By.cssSelector("#app [tabindex='-1']:nth-of-type(17) .white--text .v-btn__content");
	By saveBtn = By.cssSelector(".lime .v-btn__content");
	By folderActions = By.cssSelector("img[alt='folder.png']");
	By openForm = By
			.cssSelector("div:nth-of-type(2) > .theme--dark.v-list__tile.v-list__tile--link > .v-list__tile__title");
	By firstForm = By.cssSelector("div:nth-of-type(1) > div[role='listitem'] .v-list__tile__sub-title");
	By n4Form = By.cssSelector(".v-list--two-line .theme--light:nth-of-type(4)");
	By openBtn = By.cssSelector(".blue.ml-0.mt-0.theme--light.v-btn.v-btn--round.white--text > .v-btn__content");
	By settings = By.cssSelector(".v-btn--flat.white--text .v-btn__content");
	By createAction = By.cssSelector(".theme--light.v-card.v-sheet > button:nth-of-type(3) > .v-btn__content");
	By selectAPI = By.cssSelector("div[role='combobox']  .v-input__slot.white");
	By sumAPI = By.cssSelector("div[role='list'] > div:nth-of-type(11) .v-list__tile__title");
	By listAPI = By.cssSelector("div[role='list'] > div:nth-of-type(13) .v-list__tile__title");
	By actionName = By.cssSelector("[class='d-inline-block mr-5'] .v-input__slot");
	By actionCode = By.cssSelector(".CodeMirror-scroll");
	By actionInputFld1 = By.cssSelector(
			"div:nth-of-type(2) .layout > div:nth-of-type(2) > div[role='combobox']  .v-input__slot.white");
	By actionInputFld2 = By.cssSelector(
			"div:nth-of-type(3) .layout > div:nth-of-type(2) > div[role='combobox']  .v-input__slot.white");
	By actionSumField = By.cssSelector(".xs4:nth-of-type(3) .v-input__slot");
	By actnDrpdwnItm2 = By
			.cssSelector("#app div:nth-of-type(21) [role='listitem']:nth-of-type(2) .v-list__tile__title");
	By actionSaveBtn = By.cssSelector(".width20.white--text .v-btn__content");
	By actionCancel = By.cssSelector(".width20.v-btn--depressed .v-btn__content");
	By createNewActionBtn = By.cssSelector("[class='v-card__title title pt-2 pb-2'] .v-btn__content");

	By prvwSingleInput = By.cssSelector("input[required]");
	By prvwNext = By.cssSelector(".v-btn--block .v-btn__content");
	By prvwDone = By.cssSelector(".done-btn .material-icons");
	By prvwBlock1Hdr = By.cssSelector("h2");
	By prvwReadOnly = By.cssSelector("[readonly='readonly']");

	By prvwOneInpt = By.cssSelector("[aria-label='one ']");
	By prvwTwoInpt = By.cssSelector("[aria-label='two ']");
	By prvwSum1Inpt = By.cssSelector(".sum1.v-tooltip.v-tooltip--bottom input[type='text']");
	By prvwN2SumInpt = By.cssSelector(".n2sums.v-tooltip.v-tooltip--bottom input[type='text']");

	public WebElement getPrvwOneInpt() throws InterruptedException, IOException {
		return getDriver().findElement(prvwOneInpt);
	}

	public WebElement getPrvwTwoInpt() throws InterruptedException, IOException {
		return getDriver().findElement(prvwTwoInpt);
	}

	public WebElement getPrvwSum1Inpt() throws InterruptedException, IOException {
		return getDriver().findElement(prvwSum1Inpt);
	}

	public WebElement getPrvwN2SumInpt() throws InterruptedException, IOException {
		return getDriver().findElement(prvwN2SumInpt);
	}

	public WebElement getCreateNewActionBtn() throws InterruptedException, IOException {
		return getDriver().findElement(createNewActionBtn);
	}

	public WebElement getActionCancel() throws InterruptedException, IOException {
		return getDriver().findElement(actionCancel);
	}

	public WebElement getActionSaveBtn() throws InterruptedException, IOException {
		return getDriver().findElement(actionSaveBtn);
	}

	public WebElement getActionDrpdwnItm2() throws InterruptedException, IOException {
		return getDriver().findElement(actnDrpdwnItm2);
	}

	public WebElement getActionSumFld() throws InterruptedException, IOException {
		return getDriver().findElement(actionSumField);
	}

	public WebElement getActionInputFld1() throws InterruptedException, IOException {
		return getDriver().findElement(actionInputFld1);
	}

	public WebElement getActionInputFld2() throws InterruptedException, IOException {
		return getDriver().findElement(actionInputFld2);
	}

	public WebElement getActionCode() throws InterruptedException, IOException {
		return getDriver().findElement(actionCode);
	}

	public WebElement getActionName() throws InterruptedException, IOException {
		return getDriver().findElement(actionName);
	}

	public WebElement getListAPI() throws InterruptedException, IOException {
		return getDriver().findElement(listAPI);
	}

	public WebElement getSumAPI() throws InterruptedException, IOException {
		return getDriver().findElement(sumAPI);
	}

	public WebElement getSelectAPI() throws InterruptedException, IOException {
		return getDriver().findElement(selectAPI);
	}

	public WebElement getCreateAction() throws InterruptedException, IOException {
		return getDriver().findElement(createAction);
	}

	public WebElement getSettings() throws InterruptedException, IOException {
		return getDriver().findElement(settings);
	}

	public WebElement getPrvwReadOnly() throws InterruptedException, IOException {
		return getDriver().findElement(prvwReadOnly);
	}

	public WebElement getPrvwBlock1Hdr() throws InterruptedException, IOException {
		return getDriver().findElement(prvwBlock1Hdr);
	}

	public WebElement getPrvwDone() throws InterruptedException, IOException {
		return getDriver().findElement(prvwDone);
	}

	public WebElement getPrvwNext() throws InterruptedException, IOException {
		return getDriver().findElement(prvwNext);
	}

	public WebElement getPrvwSingleInput() throws InterruptedException, IOException {
		return getDriver().findElement(prvwSingleInput);
	}

	public WebElement getOpenBtn() throws InterruptedException, IOException {
		return getDriver().findElement(openBtn);
	}

	public WebElement getFirstForm() throws InterruptedException, IOException {
		return getDriver().findElement(firstForm);
	}

	public WebElement getN4Form() throws InterruptedException, IOException {
		return getDriver().findElement(n4Form);
	}

	public WebElement getOpenForm() throws InterruptedException, IOException {
		return getDriver().findElement(openForm);
	}

	public WebElement getFolderActions() throws InterruptedException, IOException {
		return getDriver().findElement(folderActions);
	}

	public WebElement getSaveBtn() throws InterruptedException, IOException {
		return getDriver().findElement(saveBtn);
	}

	public WebElement getSaveConfirm() throws InterruptedException, IOException {
		return getDriver().findElement(saveConfirm);
	}

	public WebElement getGenAttLabel() throws InterruptedException, IOException {
		return getDriver().findElement(genAttLabel);
	}

	public WebElement getIntID() throws InterruptedException, IOException {
		return getDriver().findElement(intID);
	}

	public WebElement getAddStep() throws InterruptedException, IOException {
		return getDriver().findElement(addStep);
	}

	public WebElement getHiddenAtt() throws InterruptedException, IOException {
		return getDriver().findElement(hiddenAtt);
	}

	public WebElement getrequiredAtt() throws InterruptedException, IOException {
		return getDriver().findElement(requiredAtt);
	}

	public WebElement getReadOnlyAtt() throws InterruptedException, IOException {
		return getDriver().findElement(readOnlyAtt);
	}

	public WebElement getGenericAttrbts() throws InterruptedException, IOException {
		return getDriver().findElement(genericAttrbts);
	}

	public WebElement getBlock1Title() throws InterruptedException, IOException {
		return getDriver().findElement(block1Title);
	}

	public WebElement getDropArea1() throws InterruptedException, IOException {
		return getDriver().findElement(dropArea1);
	}

	public WebElement getDropNewBlock() throws InterruptedException, IOException {
		return getDriver().findElement(dropNewBlock);
	}

	public WebElement getBasicFieldsDropdown() throws InterruptedException, IOException {
		return getDriver().findElement(basicFieldsDropdown);
	}

	public WebElement getFormTitle() throws InterruptedException, IOException {
		return getDriver().findElement(formTitle);
	}

	public WebElement getRenameForm() throws InterruptedException, IOException {
		return getDriver().findElement(renameForm);
	}

	public WebElement getSaveForm() throws InterruptedException, IOException {
		return getDriver().findElement(saveForm);
	}

	public WebElement getPreviewForm() throws InterruptedException, IOException {
		return getDriver().findElement(previewForm);
	}

	public WebElement getShortText() throws InterruptedException, IOException {
		return getDriver().findElement(shortText);
	}

	public WebElement getLongText() throws InterruptedException, IOException {
		return getDriver().findElement(longText);
	}

	public WebElement getMobilePageBrkFld() throws InterruptedException, IOException {
		return getDriver().findElement(mobilePgBrkFld);
	}

	public WebElement getCommentFld() throws InterruptedException, IOException {
		return getDriver().findElement(commentFld);
	}

	public WebElement getSubtitleFld() throws InterruptedException, IOException {
		return getDriver().findElement(subtitleFld);
	}

	public WebElement getDynamicPDFFld() throws InterruptedException, IOException {
		return getDriver().findElement(dynamicPDFFld);
	}

	public WebElement getDisplayPDFFld() throws InterruptedException, IOException {
		return getDriver().findElement(displayPDFFld);
	}

	public WebElement getFileUpldFld() throws InterruptedException, IOException {
		return getDriver().findElement(fileUpldFld);
	}

	public WebElement getRadioKVFld() throws InterruptedException, IOException {
		return getDriver().findElement(radioKVFld);
	}

	public WebElement getRadioFld() throws InterruptedException, IOException {
		return getDriver().findElement(radioFld);
	}

	public WebElement getCeckboxFld() throws InterruptedException, IOException {
		return getDriver().findElement(checkboxFld);
	}

	public WebElement getMultiSelectFld() throws InterruptedException, IOException {
		return getDriver().findElement(multiSelect);
	}

	public WebElement getAutoComplete() throws InterruptedException, IOException {
		return getDriver().findElement(autoComplete);
	}

	public WebElement getDropdownFld() throws InterruptedException, IOException {
		return getDriver().findElement(dropdownFld);
	}

	public WebElement getCurrencyFld() throws InterruptedException, IOException {
		return getDriver().findElement(crrncyFld);
	}

	public WebElement getTimeFld() throws InterruptedException, IOException {
		return getDriver().findElement(timeFld);
	}

	public WebElement getDateFld() throws InterruptedException, IOException {
		return getDriver().findElement(dateFld);
	}

	public WebElement getIdFld() throws InterruptedException, IOException {
		return getDriver().findElement(idFld);
	}

	public WebElement getSignFld() throws InterruptedException, IOException {
		return getDriver().findElement(signFld);
	}

	public WebElement getPasswordFld() throws InterruptedException, IOException {
		return getDriver().findElement(psswrdFld);
	}

	public WebElement getEmailFld() throws InterruptedException, IOException {
		return getDriver().findElement(emailFld);
	}

	public WebElement getPhoneNmbr() throws InterruptedException, IOException {
		return getDriver().findElement(phoneNmbr);
	}

	public WebElement getNumberFld() throws InterruptedException, IOException {
		return getDriver().findElement(numberFld);
	}

	public WebElement getParagraph() throws InterruptedException, IOException {
		return getDriver().findElement(paragraph);
	}

	public String jsDragnDrop() {
		return "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
				+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
				+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
				+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
				+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
				+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
				+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
				+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
				+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
				+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
				+ "var dropEvent = createEvent('drop');\n"
				+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
				+ "var dragEndEvent = createEvent('dragend');\n"
				+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
				+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
				+ "simulateHTML5DragAndDrop(source,destination);";
	}

}
