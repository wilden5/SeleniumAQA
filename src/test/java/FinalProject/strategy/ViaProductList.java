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

    public ViaProductList() {
        driver = BaseTest.driver;
    }

    @Override
    public List<WebElement> addProductToCart(int amount) {
        Actions actions = new Actions(driver);
        List<WebElement> listOfProduct = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            WebElement product = driver.findElement(By.xpath("(//div[@class='product-container'])[" + i + "]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", product);
            actions.moveToElement(product).perform();
            driver.findElement(By.xpath("(//a[contains(@class, 'ajax_add_to_cart_button')])[" + i + "]")).click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_MODAL_WINDOW_BUTTON));
            driver.findElement(CLOSE_MODAL_WINDOW_BUTTON).click();
        }
        return listOfProduct;
    }
}
