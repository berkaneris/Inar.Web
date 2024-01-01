import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class WO_003_LP_03 extends Hooks {

	@Test
	public void testLogoutFunction() throws InterruptedException {

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

		// Logout process
		WebElement logOutButton = driver.findElement(By.id("logout-button"));
		logOutButton.click();

		// After logout, find the login text in the login page
		WebElement loginText = null;

		try {
			loginText = driver.findElement(By.xpath("//h2[contains(text(),'Login')]"));
		}
		catch (NoSuchElementException ex) {

		}

		// Verify the login text
		assertNotNull(loginText,
				"After logout process, the login page should be displayed and loginText should not be null");
	}

}
