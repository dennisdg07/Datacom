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

WebUI.callTestCase(findTestCase('Common/LauchHomePage'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Common/MenuNavigation'), [('menu_var') : menu_var], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Payees/add_btn'))

WebUI.callTestCase(findTestCase('Common/AddPayee'), [('payeeName') : payeeName, ('accountBank') : accountBank, ('accountBranch') : accountBranch
        , ('accountAccount') : accountAccount, ('accountSuffix') : accountSuffix], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Payees/addPayee_btn'))

WebUI.verifyElementPresent(findTestObject('Payees/payeeAdded_txt'), GlobalVariable.mediumWait)

//Verify Ascending
//Get the count that will be use for loop
int payeesCount = CustomKeywords.'utilCustomKeyword.utility.countElementsPresent'(findTestObject('Payees/payeesCountElementPresent'))

ArrayList<String> payeeNames = new ArrayList<String>()

for (int i = 1; i <= payeesCount; i++) {
    payeeNames.add(WebUI.getText(findTestObject('Payees/payeesNameByIndex', [('index') : i])))
}

System.out.println(payeeNames)

System.out.println(CustomKeywords.'utilCustomKeyword.utility.checkSorting'(payeeNames))

assert CustomKeywords.'utilCustomKeyword.utility.checkSorting'(payeeNames) == true


//Verify Descending

WebUI.click(findTestObject('Payees/nameAscendingDescending_txt'))

payeeNames.clear();

for (int i = 1; i <= payeesCount; i++) {
	payeeNames.add(WebUI.getText(findTestObject('Payees/payeesNameByIndex', [('index') : i])))
}

System.out.println(payeeNames)

System.out.println(CustomKeywords.'utilCustomKeyword.utility.checkSorting'(payeeNames))

assert CustomKeywords.'utilCustomKeyword.utility.checkSorting'(payeeNames) == false

WebUI.closeBrowser()

