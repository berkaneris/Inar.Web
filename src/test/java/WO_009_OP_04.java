import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_009_OP_04 extends Hooks {

	@Test
	public void testOrderPlacementWithOutCardType() {

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

		// Navigate to order page
		WebElement orderLink = driver.findElement(By.cssSelector("#order-tab > a"));
		orderLink.click();

		// Create product information
		WebElement dropDownMenu = driver.findElement(By.id("productSelect"));
		Select select = new Select(dropDownMenu);

		select.selectByValue("SportsEquipment");

		WebElement quantityInput = driver.findElement(By.id("quantityInput"));
		quantityInput.sendKeys("1");

		WebElement discountInput = driver.findElement(By.id("discountInput"));
		discountInput.sendKeys("10");

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
		zipCodeInput.sendKeys("78701");

		// Create payment ınformation
		WebElement cardNumInput = driver.findElement(By.id("cardNumber"));
		cardNumInput.sendKeys("4938220192845");

		WebElement expireDateInput = driver.findElement(By.id("expiryDate"));
		expireDateInput.sendKeys("09/26");

		WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
		processButton.click();

		// Finding alert message
		WebElement cardAlertText = driver.findElement(By.xpath("//span/em[text()='Card type cannot be empty']"));

		// Verify the alert message
		assertEquals("Card type cannot be empty", cardAlertText.getText());
	}

}
