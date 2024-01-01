import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_010_VAO_01 extends Hooks {

	@Test
	public void testCheckAllFunctionInViewAllOrder() {

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

		// Navigate to view all orders page
		WebElement viewAllOrdersLink = driver.findElement(By.xpath("//div[@id='view-orders-tab']/a"));
		viewAllOrdersLink.click();

		// Add 4 data to the table
		WebElement addMoreDataButton = driver.findElement(By.cssSelector(".mb-3 button"));
		for (int i = 0; i < 4; i++) {
			addMoreDataButton.click();
		}
		// Select the all data
		WebElement checkAllButton = driver.findElement(By.xpath("//button[text()='Check All']"));
		checkAllButton.click();

		List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".form-check-input"));

		// Verify the selection process
		for (int i = 0; i < checkBoxes.size(); i++) {
			assertTrue(checkBoxes.get(i).isSelected(), "All check boxes should be selected");
		}
	}

}
