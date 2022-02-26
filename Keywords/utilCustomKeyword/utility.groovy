package utilCustomKeyword
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.eclipse.persistence.platform.database.DBasePlatform
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import java.text.DecimalFormat as DecimalFormat


class utility {
	/**
	 * Refresh browser
	 */

	WebDriver driver = DriverFactory.getWebDriver()
	private static final DecimalFormat df = new DecimalFormat("0.00")

	enum Operation {
		add,
		subtract
	}

	@Keyword
	int countElementsPresent(TestObject object_to_count) {
		//Count the element without Frame, ensure that object has an xpath
		/**
		 * @param frame_object = you will find the String byText inside this frame_object
		 * @param byText = the string that you want to find
		 *
		 */
		//Need to switch to frame in order to interact on its elements inside
		//WebUI.switchToFrame(driver, 10)
		//Count the number of element e.g. - count Under Assessment labels/elements present

		Integer count_element = driver.findElements(By.xpath(object_to_count.findPropertyValue('xpath').toString())).size();

		//This will bring back the focus of window to Main Window/Top Window
		WebUI.switchToDefaultContent()
		//Return the number of element found
		System.out.println("Number of Element found using this xpath "+ object_to_count.findPropertyValue('xpath').toString() + ": " + count_element);
		return count_element;

	}

	@Keyword
	boolean checkSorting(ArrayList< String > arraylist){
		boolean isSorted=true;
		for(int i=1;i < arraylist.size();i++){
			if(arraylist.get(i-1).compareTo(arraylist.get(i)) > 0){
				isSorted= false;
				break;
			}
		}
		return isSorted;
	}

	@Keyword
	String doubleFormatter(String stringToFormat) {

		System.out.println("Raw format: " + stringToFormat)

		String stf = stringToFormat.replaceAll(",", "")

		System.out.println("Formatted: " + stf)

		double pd = Double.parseDouble(stf);

		System.out.println("Done parsing: " + pd)

		System.out.println("Return format: " + df.format(pd))

		return df.format(pd)
	}

	@Keyword
	String computeDoubleToString(String base, String addends, String operation) {

		System.out.println("Base raw format: " + base)

		System.out.println("Addends raw format: " + addends)

		double result;
		double dBase = Double.parseDouble(base);
		double dAddends = Double.parseDouble(addends);

		System.out.println("Base parsed: " + base)

		System.out.println("Addends parsed: " + addends)

		switch(operation) {
			case Operation.add.name():
				result = dBase + dAddends
				System.out.println("Result: " + result)
				break;
			case Operation.subtract.name():
				result = dBase - dAddends
				System.out.println("Result: " + result)
				break;
		}

		System.out.println("Return format: " + df.format(result))

		return df.format(result)
	}
}