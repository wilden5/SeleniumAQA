package FinalProject.page;

import FinalProject.strategy.ViaProductDetail;
import FinalProject.strategy.ViaProductList;
import FinalProject.strategy.CartContext;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {

    private final WebDriver driver;
    private CartContext cartContext = new CartContext();

    private static final By PRODUCT_LABEL = By.xpath("//h5[@itemprop='name']/a");
    private static final By COMPARE_PRODUCT_BUTTON = By.xpath("//form[@class='compare-form']");
    private static final By CART_BUTTON = By.xpath("//div[@class='shopping_cart']/a");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPARE_PRODUCT_BUTTON));
    }

    @Step("Select product from Product page with id: {productId}")
    public ProductDetailPage selectProduct(int productId) {
        List<WebElement> productList = driver.findElements(PRODUCT_LABEL);
        productList.get(productId).click();
        return new ProductDetailPage(driver);
    }

    @Step("Add product to cart via Product list, amount: {count}")
    public CartPage addViaProductList(int count) {
        cartContext.setStrategy(new ViaProductList());
        cartContext.executeStrategy(count);
        driver.findElement(CART_BUTTON).click();
        return new CartPage(driver);
    }

    @Step("Add product to cart via Product detail, amount: {count}")
    public CartPage addViaProductDetail(int count) {
        cartContext.setStrategy(new ViaProductDetail());
        cartContext.executeStrategy(count);
        driver.findElement(CART_BUTTON).click();
        return new CartPage(driver);
    }
}