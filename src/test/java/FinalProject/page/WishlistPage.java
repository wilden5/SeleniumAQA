package FinalProject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WishlistPage {

    private final WebDriver driver;

    private static final By WISHLIST_TABLE = By.xpath("//table");
    private static final By WISHLIST_ROW = By.xpath("//tbody/tr");
    private static final By REMOVE_WISHLIST_ROW = By.xpath("//td[@class='wishlist_delete']/a");
    private static final By WISHLIST_NAME = By.xpath("//td[@style='width:200px;']/a");
    private static final By DRESSES_PRODUCT_BUTTON = By.xpath("(//a[@title='Dresses'])[2]");
    private static final By SAVE_WISHLIST_BUTTON = By.xpath("//button[@id='submitWishlist']");
    private static final By PRODUCT_LABEL_IN_WISHLIST = By.xpath("//p[@class='product-name']");
    private static final By WISHLIST_NAME_INPUT = By.xpath("//input[@id='name']");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SAVE_WISHLIST_BUTTON));
    }

    @Step("Check quantity of Wishlist")
    public WishlistPage getWishlistQuantity() {
        try {
            driver.findElement(WISHLIST_TABLE).isDisplayed();
            deleteExistingWishlist();
            return this;
        } catch (NoSuchElementException e) {
            return this;
        }
    }

    @Step("Navigate to shop Product page")
    public ProductPage navigateToProductPage() {
        driver.findElement(DRESSES_PRODUCT_BUTTON).click();
        return new ProductPage(driver);
    }

    @Step("Check availability of the auto-created Wishlist")
    public boolean isAutoWishlistAccessible() {
        List<WebElement> nameOfRows = driver.findElements(WISHLIST_NAME);
        for (WebElement nameOfRow : nameOfRows) {
            if (nameOfRow.getText().equals("My wishlist")) {
                return true;
            }
        }
        return false;
    }

    @Step("Delete existing Wishlist")
    public WishlistPage deleteExistingWishlist() {
        List<WebElement> rows = driver.findElements(WISHLIST_ROW);
        for (WebElement row : rows) {
            driver.findElement(REMOVE_WISHLIST_ROW).click();
            driver.switchTo().alert().accept();
        }
        return this;
    }

    @Step("Get product name from Auto-Created Wishlist")
    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(WISHLIST_NAME).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL_IN_WISHLIST));
        return driver.findElement(PRODUCT_LABEL_IN_WISHLIST).getText();
    }

    @Step("Create Wishlist with custom name: {wishlistName}")
    public WishlistPage createCustomWishlist(String wishlistName) {
        driver.findElement(WISHLIST_NAME_INPUT).sendKeys(wishlistName);
        driver.findElement(SAVE_WISHLIST_BUTTON).click();
        return this;
    }
}