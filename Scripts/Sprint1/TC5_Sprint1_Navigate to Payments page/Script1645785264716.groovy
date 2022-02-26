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
import java.text.DecimalFormat as DecimalFormat

DecimalFormat df = new DecimalFormat('0.00')

WebUI.callTestCase(findTestCase('Common/LauchHomePage'), [:], FailureHandling.STOP_ON_FAILURE)

pre_fromCurrentBalance = WebUI.callTestCase(findTestCase('Common/GetCurrrentAccountBalance'), [('accountName') : from], 
    FailureHandling.STOP_ON_FAILURE)

pre_toCurrentBalance = WebUI.callTestCase(findTestCase('Common/GetCurrrentAccountBalance'), [('accountName') : to], FailureHandling.STOP_ON_FAILURE)

System.out.println('Pre: ' + pre_fromCurrentBalance)

System.out.println('Pre: ' + pre_toCurrentBalance)

WebUI.callTestCase(findTestCase('Common/MenuNavigation'), [('menu_var') : menu_var], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Common/PayOrTransfer'), [('from') : from, ('to') : to, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('PayOrTransfer/transfer_btn'))

WebUI.verifyElementPresent(findTestObject('PayOrTransfer/transferSuccessful_txt'), GlobalVariable.mediumWait)

post_fromCurrentBalance = WebUI.callTestCase(findTestCase('Common/GetCurrrentAccountBalance'), [('accountName') : from], 
    FailureHandling.STOP_ON_FAILURE)

post_toCurrentBalance = WebUI.callTestCase(findTestCase('Common/GetCurrrentAccountBalance'), [('accountName') : to], FailureHandling.STOP_ON_FAILURE)

System.out.println('Post: ' + post_fromCurrentBalance)

System.out.println('Post: ' + post_toCurrentBalance)

WebUI.verifyMatch(post_fromCurrentBalance, CustomKeywords.'utilCustomKeyword.utility.computeDoubleToString'(pre_fromCurrentBalance, 
        CustomKeywords.'utilCustomKeyword.utility.doubleFormatter'(amount), 'subtract'), false)

WebUI.verifyMatch(post_toCurrentBalance, CustomKeywords.'utilCustomKeyword.utility.computeDoubleToString'(pre_toCurrentBalance, 
        CustomKeywords.'utilCustomKeyword.utility.doubleFormatter'(amount), 'add'), false)

WebUI.closeBrowser()

