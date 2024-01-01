import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_011_VAO_02 extends Hooks {

	@Test
	public void testUnCheckAllFunctionInViewAllOrder() {

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

		// Add order to the table
		WebElement addMoreDataButton = driver.findElement(By.cssSelector(".mb-3 button"));
		for (int i = 0; i < 6; i++) {
			addMoreDataButton.click();
		}

		WebElement checkAllButton = driver.findElement(By.xpath("//button[text()='Check All']"));
		checkAllButton.click();

		List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".form-check-input"));

		// Verify the check button is selected
		for (int i = 0; i < checkBoxes.size(); i++) {
			assertTrue(checkBoxes.get(i).isSelected(), "All check boxes should be selected");
		}

		WebElement unCheckAllButton = driver.findElement(By.xpath("//button[text()='Uncheck All']"));
		unCheckAllButton.click();

		// Verify the check button is unselected
		for (int i = 0; i < checkBoxes.size(); i++) {
			assertTrue(!checkBoxes.get(i).isSelected(), "All check boxes should be unselected");
		}
	}

}
