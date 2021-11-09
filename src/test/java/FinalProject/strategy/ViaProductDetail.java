package FinalProject.strategy;

import FinalProject.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ViaProductDetail implements AddToCartStrategy {

    private WebDriver driver;

    private static final By CLOSE_MODAL_WINDOW_BUTTON = By.xpath("//span[@class='cross']");
    private static final By NAVIGATE_TO_PRODUCT_PAGE = By.xpath("(//a[@title='Dresses'])[2]");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@class='exclusive']");
    private static final String PRODUCT_CONTAINER = "//div[@class='product-container']";
    private static final String PRODUCT_LABEL = "//h5[@itemprop='name']/a";

    public ViaProductDetail() {
        driver = BaseTest.driver;
    }

    @Override
    public List<WebElement> addProductToCart(int amount) {
        List<WebElement> listOfProducts = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            driver.findElement(By.xpath(String.format("(%s)[" + i + "]%s", PRODUCT_CONTAINER, PRODUCT_LABEL))).click();
            driver.findElement(ADD_TO_CART_BUTTON).click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_MODAL_WINDOW_BUTTON));
            driver.findElement(CLOSE_MODAL_WINDOW_BUTTON).click();
            driver.findElement(NAVIGATE_TO_PRODUCT_PAGE).click();
        }
        return listOfProducts;
    }
}