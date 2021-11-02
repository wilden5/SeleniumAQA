package FinalProject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.util.List;

public class CartPage {

    private final WebDriver driver;

    private static final By CART_ROW = By.xpath("//tr[contains(@class,'cart_item')]");
    private static final By PRICE_OF_PRODUCT = By.xpath("//td[@class='cart_total']/span");
    private static final By DELETE_BUTTON = By.xpath("//a[@class='cart_quantity_delete']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_ROW));
    }

    @Step("Get total price of cart")
    public String getTotalPriceOfCart() {
        List<WebElement> productPrice = driver.findElements(PRICE_OF_PRODUCT);
        DecimalFormat f = new DecimalFormat("##.00");
        double tax = 2.0;
        double totalPrice = tax;
        for (WebElement p : productPrice) {
            String price = p.getText().replace("$", "").trim();
            double convertedPrice = Double.parseDouble(price);
            totalPrice += convertedPrice;
        }
        return f.format(totalPrice);
    }

    @Step("Get number of product from cart")
    public String getNumberOfProduct() {
        List<WebElement> numberOfProduct = driver.findElements(CART_ROW);
        int number = 0;
        for (WebElement n : numberOfProduct) {
            number++;
        }
        return String.valueOf(number);
    }

    @Step("Delete product from cart") //for future implementation when cart state could be saved after logout
    public CartPage deleteProductFromCart() throws InterruptedException {
        List<WebElement> productAmount = driver.findElements(DELETE_BUTTON);
        for (WebElement n : productAmount) {
            n.click();
            Thread.sleep(2000);
        }
        return this;
    }
}