import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WO_008_OP_03 extends Hooks {

	@Test
	public void testOrderPlacementWithInvalidZipCode() {

		// Navigate to web order page and login
		WebElement webOrderButton = driver
			.findElement(By.cssSelector("[class='text-fifth fw-semibold px-3 fs-3 nav-link']:nth-child(1)"));
		webOrderButton.click();

		WebElement usernameInput = driver.findElement(By.id("login-username-input"));
		usernameInput.sendKeys("Inar");

		WebElement passwordInput = driver.findElement(By.id("login-password-input"));
		passwordInput.sendKeys("Academy");

		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();

		// Navigate to the order page
		WebElement orderLink = driver.findElement(By.cssSelector("#order-tab > a"));
		orderLink.click();

		// Create product information
		WebElement dropDownMenu = driver.findElement(By.id("productSelect"));
		Select select = new Select(dropDownMenu);

		select.selectByValue("MyMoney");

		WebElement quantityInput = driver.findElement(By.id("quantityInput"));
		quantityInput.sendKeys("8");

		WebElement discountInput = driver.findElement(By.id("discountInput"));
		discountInput.sendKeys("20");

		WebElement calculateButton = driver.findElement(By.xpath("//button[contains(text(),'Calculate')]"));
		calculateButton.click();

		// Enter customer Information
		WebElement nameInput = driver.findElement(By.id("name"));
		nameInput.sendKeys("Inar Academy");

		WebElement streetInput = driver.findElement(By.id("street"));
		streetInput.sendKeys("1100 Congress Ave");

		WebElement cityInput = driver.findElement(By.id("city"));
		cityInput.sendKeys("Austin");

		WebElement stateInput = driver.findElement(By.id("state"));
		stateInput.sendKeys("TX");

		WebElement zipCodeInput = driver.findElement(By.id("zip"));
		zipCodeInput.sendKeys("92@#83");

		// Create payment ınformation
		WebElement visaCheckBox = driver.findElement(By.id("amex"));
		visaCheckBox.click();

		WebElement cardNumInput = driver.findElement(By.id("cardNumber"));
		cardNumInput.sendKeys("342738261027163");

		WebElement expireDateInput = driver.findElement(By.id("expiryDate"));
		expireDateInput.sendKeys("01/28");

		WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
		processButton.click();

		// Finding the alert message
		WebElement alertText = null;

		try {
			alertText = driver.findElement(By.linkText("Zip Code is invalid"));
		}
		catch (NoSuchElementException ex) {

		}

		// Verify the alert message
		assertNotNull(alertText, "There should be an alert text written 'Zip Code is invalid'");
	}

}
