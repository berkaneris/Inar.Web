import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WO_012_VAO_03 extends Hooks {

	@Test
	public void testDeleteFunctionInViewAllOrder() {

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
		for (int i = 0; i < 8; i++) {
			addMoreDataButton.click();
		}

		// List for table rows
		List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody > tr"));

		// Find the selected orders href link
		List<String> selectedRowsLinks = new ArrayList<>();
		for (int i = 0; i <= 4; i += 2) {
			tableRows.get(i).findElement(By.tagName("input")).click();
			selectedRowsLinks.add(tableRows.get(i).findElement(By.cssSelector("td > a")).getAttribute("href"));
		}

		// Delete the selected orders
		WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
		deleteButton.click();

		// Wait until the delete process is finished.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Delete']")));

		// After delete process find the href links for the remain table
		tableRows = driver.findElements(By.cssSelector("tbody > tr"));
		List<String> rowsLinks = new ArrayList<>();
		for (int i = 0; i < tableRows.size(); i++) {
			rowsLinks.add(tableRows.get(i).findElement(By.cssSelector("td > a")).getAttribute("href"));
		}

		// Verify that the selected orders deleted from table
		System.out.println(selectedRowsLinks);
		for (int i = 0; i < selectedRowsLinks.size(); i++) {
			assertTrue(!tableRows.contains(selectedRowsLinks.get(i)),
					"The table should not contain the selected order href link after deletion.");
		}
	}

}
