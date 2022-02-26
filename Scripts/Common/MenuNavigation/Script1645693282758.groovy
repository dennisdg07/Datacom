import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

System.out.println("Enums declaration");

enum Menu {
	PayOrTransfer("Pay or transfer"),
	Payees("Payees")
	
	final String label;
	
	private Menu(String label) {
		this.label = label;
	}
	public String getValue() {
		return label;
	}
}

WebUI.click(findTestObject('Menu/menu_btn'))

System.out.println("Navigating to " + menu_var);

switch(menu_var) {
	case Menu.PayOrTransfer.getValue():
		WebUI.waitForElementClickable(findTestObject('Menu/payOrTransfer_menu_btn'), GlobalVariable.mediumWait)
		WebUI.click(findTestObject('Menu/payOrTransfer_menu_btn'))
        break;
	case Menu.Payees.getValue():
		WebUI.waitForElementClickable(findTestObject('Menu/payees_menu_btn'), GlobalVariable.mediumWait)
		WebUI.click(findTestObject('Menu/payees_menu_btn'))
		break;
}
