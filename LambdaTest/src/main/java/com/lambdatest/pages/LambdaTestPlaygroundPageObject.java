package com.lambdatest.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lambdatest.utils.BaseClass;
import com.lambdatest.utils.DriverFactory;
import com.lambdatest.utils.ReportUtil;

public class LambdaTestPlaygroundPageObject extends BaseClass {
	public String driverInstance;
	public String variableText = "Welcome to LambdaTest";

	public LambdaTestPlaygroundPageObject(String driverInstance) {
		this.driverInstance = driverInstance;
		PageFactory.initElements(DriverFactory.getRemoteWebDriver(), this);
	}

	@FindBy(xpath = "//li[@class='pt-10']/a[text()='Simple Form Demo']")
	public WebElement simpleFormDemo;

	@FindBy(xpath = "//li[@class='pt-10']/a[text()='Input Form Submit']")
	public WebElement inputFormSubmit;

	@FindBy(css = "input#user-message")
	public WebElement enterMessage;

	@FindBy(css = "button#showInput")
	public WebElement getCheckedValue;

	@FindBy(css = "p#message")
	public WebElement outputMessage;

	@FindBy(css = "button.btn.btn-dark")
	public WebElement inputFormSubmitBtn;

	@FindBy(id = "name")
	public WebElement inputFormName;

	@FindBy(id = "inputEmail4")
	public WebElement inputFormEmail;

	@FindBy(id = "inputPassword4")
	public WebElement inputFormPassword;

	@FindBy(id = "company")
	public WebElement inputFormCompany;

	@FindBy(id = "websitename")
	public WebElement inputFormWebSiteName;

	@FindBy(id = "inputCity")
	public WebElement inputFormCity;

	@FindBy(id = "inputAddress1")
	public WebElement inputFormAddress1;

	@FindBy(id = "inputAddress2")
	public WebElement inputFormAddress2;

	@FindBy(id = "inputState")
	public WebElement inputFormState;

	@FindBy(id = "inputZip")
	public WebElement inputFormZip;

	@FindBy(xpath = "//select[@class='w-full border border-gray-90 text-size-14 rounded mt-10 px-10 py-5']")
	public WebElement inputFormSelectCountry;

	@FindBy(css = "p.success-msg.hidden")
	public WebElement inputFormSubmitSuccessMsg;

	@FindBy(xpath = "(//p[@class='inline-block']/i[@class='sp__arrow righter'])[3]")
	public WebElement progressBarSliders;

	@FindBy(xpath = "//li[@class='pt-10']/a[text()='Drag & Drop Sliders']")
	public WebElement dragDropSliders;

	@FindBy(xpath = "(//input[@class='sp__range'])[3]")
	public WebElement sliders;

	@FindBy(xpath = "(//input[@class='sp__range']//following::output)[3]")
	public WebElement slidersValue;

	public void clickOnSimpleFormDemo() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickElement(simpleFormDemo, "Simple Form Demo");
	}

	public void validatingURL() throws IOException, InterruptedException {

		Thread.sleep(5000);
		String CurrentURL = DriverFactory.getRemoteWebDriver().getCurrentUrl();
		if (CurrentURL.contains("simple-form-demo")) {
			logger.info("Current URL Contains simple-form-demo");
			ReportUtil.pass("Current URL Contains simple-form-demo");
		} else {
			logger.info("Current URL doesnot Contains simple-form-demo");
			ReportUtil.fail("Current URL doesnot Contains simple-form-demo");
		}
	}

	public void enteringValue() {
		enterMessage.sendKeys(variableText);
	}

	public void clickOnGetCheckedValue() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickElement(getCheckedValue, "Get Checked Value");
	}

	public void validatingOutputMessage() throws IOException, InterruptedException {
		Thread.sleep(10000);
		verifyText(outputMessage, variableText);
	}

	public void clickOnInputFormSubmit() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickElement(inputFormSubmit, "Input Form Submit");
	}

	public void clickOnInputFormSubmitButton() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickElement(inputFormSubmitBtn, "Input Form Submit Button");
	}

	public void enteringInputForm() throws IOException, InterruptedException {

		inputFormName.sendKeys("Karthik");
		inputFormEmail.sendKeys("Karthik.v@mindtree.com");
		inputFormPassword.sendKeys("12345");
		inputFormCompany.sendKeys("Mindtree");
		selectvalue(inputFormSelectCountry, "India");
		Thread.sleep(2000);
		inputFormWebSiteName.sendKeys("www.LambdaTest.com");
		inputFormCity.sendKeys("AndhraPradesh");
		inputFormAddress1.sendKeys("Chittoor,Santhapet");
		inputFormAddress2.sendKeys("AndhraPradesh-517004");
		inputFormState.sendKeys("AndhraPradesh");
		inputFormZip.sendKeys("517004");

	}

	public void validatingInputFormOutputMessage() throws IOException, InterruptedException {
		Thread.sleep(5000);
		verifyText(inputFormSubmitSuccessMsg, "Thanks for contacting us, we will get back to you shortly.");
	}

	public void clickOnprogressBarSliders() throws IOException, InterruptedException {
		Thread.sleep(5000);
		clickElement(dragDropSliders, "Drag & Drop Slider");
	}

	public void defaultValue15() throws InterruptedException, IOException {

		Actions action = new Actions(DriverFactory.getRemoteWebDriver());
		action.click(sliders).build().perform();
		Thread.sleep(1000);
		verifyText(slidersValue, "50");
		ReportUtil.info("Slider Value is : " + slidersValue.getText());
	}
}
