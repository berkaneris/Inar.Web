import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_014_OPU_01 extends Hooks {

	/*
	 * Steps 1- Open the URL 2- Click "WebOrder" button on top bar. 3- Enter valid
	 * username "Inar" and password "Academy". 4- Navigate to the view all order page. 5-
	 * Click "Add More Data" "1" times. 6- Click "Plus Button" end of the order row. 7-
	 * Navigate to the order page. 8- Update the customer city information 9- Click
	 * process button. 10- Verify the update message is displayed.
	 */

	@Test
	public void testOrderUpDateProcess() {
		// Navigate to web order page and login
		WebElement webOrderButton = driver.findElement(By.cssSelector("a[href='/weborder']"));
		webOrderButton.click();

		WebElement usernameInput = driver.findElement(By.id("login-username-input"));
		usernameInput.sendKeys("Inar");

		WebElement passwordInput = driver.findElement(By.id("login-password-input"));
		passwordInput.sendKeys("Academy");

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		// Click add more data
		WebElement addMoreDataButton = driver.findElement(By.cssSelector(".mb-3 button"));
		for (int i = 0; i < 1; i++) {
			addMoreDataButton.click();
		}

		// Find plus button and click
		WebElement plusButton = driver.findElement(By.cssSelector("td > a"));
		plusButton.click();

		// Find city text field and update
		WebElement cityInputField = driver.findElement(By.id("city"));
		cityInputField.clear();
		cityInputField.sendKeys("Chicago");

		// Find the process button
		WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
		processButton.click();

		// Find the update message
		WebElement upDateMessage = driver.findElement(By.cssSelector("div[role='alert']"));

		// Verify the message
		// Different assertions can be done.
		String expectedMessage = "Order has been successfully updated.";
		assertEquals(expectedMessage, upDateMessage.getText(), "The update message is wrong.");
		assertTrue(upDateMessage.isDisplayed(), "The message should be displayed successfully.");
	}

}
