package resources;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utility {
	public static JavascriptExecutor js;
	public static WebDriver driver;
	public static WebDriverWait wait = new WebDriverWait(driver, 20);

	

	public static void sendkeysJavaScript(String text, WebElement element) {

		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Sending text with  java script ");

				((JavascriptExecutor) driver).executeScript("arguments[0].value='text';", element);
			} else {
				System.out.println("Unable to perform sendkeys");
			}
		} catch (Exception e) {

			System.out.println("Unable to perform sendkeys " + e.getStackTrace());
		}
	}

	public void safeSelectCheckBoxes(int waitTime, WebElement... elements) throws Exception {
		WebElement checkElement = null;
		try {
			if (elements.length > 0) {
				for (WebElement currentElement : elements) {
					checkElement = currentElement;
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(currentElement));

					WebElement checkBox = currentElement;
					if (checkBox.isSelected())
						System.out.println("CheckBox " + currentElement + " is already selected");
					else
						checkBox.click();
				}
			} else {
				System.out.println("Expected atleast one element as argument to safeSelectCheckboxes function");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + checkElement + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + checkElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to select checkbox " + e.getStackTrace());
		}
	}

	public static void waitUntilVisibilityOfElement(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void clickOnButton(WebElement element) {

		try {
			if (element.isDisplayed() || element.isEnabled())
				element.click();
			else {
				System.out.println("Element not found");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void select_Option_In_DropDown_ByVisibleText(WebElement element, String VisibleTextOptionToSelect) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(VisibleTextOptionToSelect);
			System.out.println(select.getFirstSelectedOption());

		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");

		}
	}

	public static void select_Option_In_DropDown_ByIndexVal(String value, WebElement element) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);

		} catch (NoSuchElementException e) {
			System.out.println("Value not find in dropdown");
		}
	}

	public static void select_Option_In_DropDown_ByValue(WebElement element, int indexVal) {
		try {
			Select select = new Select(element);
			select.selectByIndex(indexVal);
		} catch (NoSuchElementException e) {
			System.out.println("Option value not find in dropdown");
		}
	}

	public void Select_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				System.out.println("Checkbox: " + element + "is already selected");
			} else {
				// Select the checkbox
				element.click();
			}
		} catch (Exception e) {
			System.out.println("Unable to select the checkbox: " + element);
		}

	}

	public void DeSelect_The_Checkbox(WebElement element) {
		try {
			if (element.isSelected()) {
				// De-select the checkbox
				element.click();
			} else {
				System.out.println("Checkbox: " + element + "is already deselected");
			}
		} catch (Exception e) {
			System.out.println("Unable to deselect checkbox: " + element);
		}
	}

	// Below method is used to select the checkbox with the specified value from
	// multiple checkboxes.

	public void Select_The_CheckBox_from_List(WebElement element, String valueToSelect) {
		List<WebElement> allOptions = element.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {

			System.out.println("Option value " + option.getText());
			if (valueToSelect.equals(option.getText())) {
				option.click();
				break;
			}
		}
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToWebElement(WebDriver driver, WebElement element) {
		if (element.isEnabled() && element.isDisplayed()) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		}

	}

	public static void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
		}
	}

}
