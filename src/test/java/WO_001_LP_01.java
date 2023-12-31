import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_001_LP_01 extends Hooks {

	@Test
	public void testLoginFunctionWithValidValues() {

		WebElement webOrderButton = driver
			.findElement(By.cssSelector("[class='text-fifth fw-semibold px-3 fs-3 nav-link']:nth-child(1)"));
		webOrderButton.click();

		WebElement usernameInput = driver.findElement(By.id("login-username-input"));
		usernameInput.sendKeys("Inar");

		WebElement passwordInput = driver.findElement(By.id("login-password-input"));
		passwordInput.sendKeys("Academy");

		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();

		// Example usage
		WebElement welcomeText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome-heading")));

		assertEquals("Welcome, Inar!", welcomeText.getText(),
				"The Welcome message should be displayed in Web Order Page");

	}

}
