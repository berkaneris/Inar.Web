import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_006_OP_01 extends Hooks {

	// Create a list for test data Information
	List<String> orderInformation = new ArrayList<>(Arrays.asList("Inar Academy", "MyMoney", "8",
			DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now()), "1100 Congress Ave", "Austin", "TX",
			"78701", "Visa", "4938281746192845", "11/28"));

	@Test
	public void testOrderPlacementWithValidValues() {

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

		select.selectByValue(orderInformation.get(1));

		WebElement quantityInput = driver.findElement(By.id("quantityInput"));
		quantityInput.sendKeys(orderInformation.get(2));

		WebElement discountInput = driver.findElement(By.id("discountInput"));
		discountInput.sendKeys("15");

		WebElement calculateButton = driver.findElement(By.xpath("//button[contains(text(),'Calculate')]"));
		calculateButton.click();

		// Enter customer Information
		WebElement nameInput = driver.findElement(By.id("name"));
		nameInput.sendKeys(orderInformation.get(0));

		WebElement streetInput = driver.findElement(By.id("street"));
		streetInput.sendKeys(orderInformation.get(4));

		WebElement cityInput = driver.findElement(By.id("city"));
		cityInput.sendKeys(orderInformation.get(5));

		WebElement stateInput = driver.findElement(By.id("state"));
		stateInput.sendKeys(orderInformation.get(6));

		WebElement zipCodeInput = driver.findElement(By.id("zip"));
		zipCodeInput.sendKeys(orderInformation.get(7));

		// Create payment ınformation
		WebElement visaCheckBox = driver.findElement(By.id("visa"));
		visaCheckBox.click();

		WebElement cardNumInput = driver.findElement(By.id("cardNumber"));
		cardNumInput.sendKeys(orderInformation.get(9));

		WebElement expireDateInput = driver.findElement(By.id("expiryDate"));
		expireDateInput.sendKeys(orderInformation.get(10));

		WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
		processButton.click();

		// Find confirmation message
		WebElement confirmationMessage = driver.findElement(By.cssSelector("div[role='alert']"));
		String actualMessage = confirmationMessage.getText();
		String expectedMessage = "New order has been successfully added.";
		assertEquals(expectedMessage, actualMessage, "Confirmation Message is wrong.");

		// Navigation to the view all orders page
		WebElement viewAllOrderLink = driver.findElement(By.cssSelector("#view-orders-tab > a"));
		viewAllOrderLink.click();

		// List the table data
		List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody > tr"));
		List<WebElement> columnValuesInLastRow = tableRows.get(tableRows.size() - 1).findElements(By.xpath("td"));

		// Verify the order information
		for (int i = 0; i < orderInformation.size(); i++) {
			String expectedInfo = orderInformation.get(i);
			String actualInfo = columnValuesInLastRow.get(i + 1).getText();
			assertEquals(expectedInfo, actualInfo, "Wrong Order Information");
		}

	}

}
