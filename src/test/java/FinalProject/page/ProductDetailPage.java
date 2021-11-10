package FinalProject.page;

import FinalProject.util.WebDriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

    private final WebDriver driver;

    private static final By ADD_WISHLIST_BUTTON = By.xpath("//a[@id='wishlist_button']");
    private static final By CLOSE_CONFIRMATION_POPUP = By.xpath("//div[@class='fancybox-skin']/a");
    private static final By CUSTOMER_ACCOUNT_BUTTON = By.xpath("//div[@class='header_user_info']/a");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@class='exclusive']");

    public ProductDetailPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_BUTTON));
    }

    @Step("Add product to Wishlist")
    public ProductDetailPage addProductToWishlist() {
        driver.findElement(ADD_WISHLIST_BUTTON).click();
        driver.findElement(CLOSE_CONFIRMATION_POPUP).click();
        //Thread.sleep(3000); for Firefox AutoWishlistTest & WishlistTest
        return this;
    }

    @Step("Navigate to User Account page")
    public AccountPage navigateToAccountPage() {
        driver.findElement(CUSTOMER_ACCOUNT_BUTTON).click();
        return new AccountPage();
    }
}