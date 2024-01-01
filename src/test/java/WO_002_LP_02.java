import com.google.common.base.Function;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_002_LP_02 extends Hooks {

	@Test
	public void testLoginFunctionWithInValidValues() {

		// Navigate to web order page and login
		WebElement webOrderButton = driver
			.findElement(By.cssSelector("[class='text-fifth fw-semibold px-3 fs-3 nav-link']:nth-child(1)"));
		webOrderButton.click();

		WebElement usernameInput = driver.findElement(By.id("login-username-input"));
		usernameInput.sendKeys("Test");

		WebElement passwordInput = driver.findElement(By.id("login-password-input"));
		passwordInput.sendKeys("Deneme");

		WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
		loginButton.click();

		// Find the alert message
		WebElement alertText = driver.findElement(By.id("username-error-alert"));

		// Verify the alert message after the unsuccessful login process
		assertEquals("Invalid username", alertText.getText());
	}

}
