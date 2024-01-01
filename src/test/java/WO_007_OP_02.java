import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_007_OP_02 extends Hooks {

	@Test
	public void testOrderPlacementWithoutCalculation() {

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

		// Create product information except calculation process
		WebElement dropDownMenu = driver.findElement(By.id("productSelect"));
		Select select = new Select(dropDownMenu);

		select.selectByValue("FamilyAlbum");

		WebElement quantityInput = driver.findElement(By.id("quantityInput"));
		quantityInput.sendKeys("3");

		WebElement discountInput = driver.findElement(By.id("discountInput"));
		discountInput.sendKeys("17");

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
		zipCodeInput.sendKeys("78701");

		// Create payment ınformation
		WebElement visaCheckBox = driver.findElement(By.id("mastercard"));
		visaCheckBox.click();

		WebElement cardNumInput = driver.findElement(By.id("cardNumber"));
		cardNumInput.sendKeys("5162738261027163");

		WebElement expireDateInput = driver.findElement(By.id("expiryDate"));
		expireDateInput.sendKeys("11/28");

		WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
		processButton.click();

		// Finding the alert message
		WebElement alertMessage = driver
			.findElement(By.xpath("//span/em[text()='Fix errors in Product Information ']"));
		String expectedMessage = "Fix errors in Product Information";

		// Verify the alert message
		assertEquals(expectedMessage, alertMessage.getText(), "The alert message is wrong");

	}

}
