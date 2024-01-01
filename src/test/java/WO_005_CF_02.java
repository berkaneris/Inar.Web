import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_005_CF_02 extends Hooks {

	@Test
	public void testCalculateFunctionWithInValidValues() throws InterruptedException {

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

		// Navigate to the order page and set the inputs for calculation except quantity
		WebElement orderButton = driver.findElement(By.xpath("//div[@id='order-tab']/a[@role='tab']"));
		orderButton.click();

		WebElement dropDownMenu = driver.findElement(By.id("productSelect"));
		Select select = new Select(dropDownMenu);

		select.selectByValue("ScreenSaver");

		WebElement discountInput = driver.findElement(By.id("discountInput"));
		discountInput.sendKeys("20");

		WebElement calculateButton = driver.findElement(By.xpath("//button[contains(text(),'Calculate')]"));
		calculateButton.click();

		// Find the error message for quantity
		WebElement errorMessage = driver.findElement(By.xpath("//span[@id='quantityValidateError']/em"));

		// Verify the message
		assertEquals("Field 'Quantity' must be greater than zero.", errorMessage.getText(),
				"An error message should be displayed because quantity should not be blank.");
	}

}
