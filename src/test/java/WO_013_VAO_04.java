import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WO_013_VAO_04 extends Hooks {

	/*
	 * Steps 1- Open the URL 2- Click "WebOrder" button on top bar. 3- Enter valid
	 * username "Inar" and password "Academy". 4- Navigate to the view all order page. 5-
	 * Click "Add More Data" "1" times. 6- Click "Plus Button" end of the order row. 7-
	 * Navigate to the order page. 8- Verify the total price for the inputs according to
	 * the processed order.
	 */

	@Test
	public void testOrderProcessFromViewAllOrdersToOrder() {

		// Navigate to web order page and login
		WebElement webOrderButton = driver.findElement(By.cssSelector("a[href='/weborder']"));
		webOrderButton.click();

		WebElement usernameInput = driver.findElement(By.id("login-username-input"));
		usernameInput.sendKeys("Inar");

		WebElement passwordInput = driver.findElement(By.id("login-password-input"));
		passwordInput.sendKeys("Academy");

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		// Click add more data
		WebElement addMoreDataButton = driver.findElement(By.cssSelector(".mb-3 button"));
		for (int i = 0; i < 1; i++) {
			addMoreDataButton.click();
		}

		// Find product information for the order
		WebElement orderRow = driver.findElement(By.cssSelector("tbody > tr"));
		String orderProductName = orderRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
		String orderQuantity = orderRow.findElement(By.cssSelector("td:nth-child(4)")).getText();

		// Navigate to the view all products and find the price and discount for the order
		// product name
		WebElement viewAllProductsLink = driver.findElement(By.cssSelector("#view-products-tab > a"));
		viewAllProductsLink.click();

		// List the rows
		List<WebElement> productsRows = driver.findElements(By.cssSelector("tbody > tr"));

		// Find information for the order product name
		String productPrice = "";
		String productDiscount = "";
		for (int i = 0; i < productsRows.size(); i++) {
			WebElement productName = productsRows.get(i).findElement(By.cssSelector("td:nth-child(1)"));
			if (productName.getText().equals(orderProductName)) {
				productPrice = productsRows.get(i).findElement(By.cssSelector("td:nth-child(2) span")).getText();
				productDiscount = productsRows.get(i).findElement(By.cssSelector("td:nth-child(3) span")).getText();
			}
		}

		// Calculate the total price for the created order
		int price = Integer.parseInt(productPrice);
		int quantity = Integer.parseInt(orderQuantity);
		int discount = Integer.parseInt(productDiscount);
		String expectedTotalPrice = ((quantity * price) * (100 - discount) / 100) + "";

		// Navigate to view all orders
		WebElement viewAllOrderLink = driver.findElement(By.cssSelector("#view-orders-tab > a"));
		viewAllOrderLink.click();

		// Find the plus button
		WebElement plusButton = driver.findElement(By.cssSelector("tbody > tr > td > a"));
		plusButton.click();

		// Find the product information on the order page
		WebElement productDropDown = driver.findElement(By.id("productSelect"));
		Select select = new Select(productDropDown);
		String productNameInfo = select.getFirstSelectedOption().getText();
		String quantityInfo = driver.findElement(By.id("quantityInput")).getAttribute("value");
		String unitPriceInfo = driver.findElement(By.id("unitPriceInput")).getAttribute("value");
		String discountInfo = driver.findElement(By.id("discountInput")).getAttribute("value");
		String totalInfo = driver.findElement(By.id("totalInput")).getAttribute("value");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0 , 250)");
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		// Verify the accuracy of the information
		assertEquals(orderProductName, productNameInfo,
				"The created order name should be equal to the name in the order page.");
		assertEquals(orderQuantity, quantityInfo,
				"The created order quantity should be equal to the quantity in the order page.");
		assertEquals(productPrice, unitPriceInfo,
				"The order product price  should be equal to the unit price in the order page.");
		assertEquals(productDiscount, discountInfo,
				"The order product discount should be equal to the discount in the order page.");
		assertEquals(expectedTotalPrice, totalInfo,
				"The order total price should be equal to the total in the order page.");
	}

}
