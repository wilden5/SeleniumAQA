package FinalProject.strategy;

import FinalProject.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ViaProductList implements AddToCartStrategy {

    private WebDriver driver;

    private static final By CLOSE_MODAL_WINDOW_BUTTON = By.xpath("//span[@class='cross']");
    private static final String PRODUCT_CONTAINER = "//div[@class='product-container']";
    private static final String ADD_TO_CART_BUTTON = "//a[contains(@class, 'ajax_add_to_cart_button')]";
    private static final String SCROLL_INTO_VIEW_ARGUMENT = "arguments[0].scrollIntoView();";

    public ViaProductList() {
        driver = BaseTest.driver;
    }

    @Override
    public List<WebElement> addProductToCart(int amount) {
        Actions actions = new Actions(driver);
        List<WebElement> listOfProducts = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            WebElement product = driver.findElement(By.xpath(String.format("(%s)[" + i + "]", PRODUCT_CONTAINER)));
            ((JavascriptExecutor) driver).executeScript(SCROLL_INTO_VIEW_ARGUMENT, product);
            actions.moveToElement(product).perform();
            driver.findElement(By.xpath(String.format("(%s)[" + i + "]", ADD_TO_CART_BUTTON))).click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_MODAL_WINDOW_BUTTON));
            driver.findElement(CLOSE_MODAL_WINDOW_BUTTON).click();
        }
        return listOfProducts;
    }
}
