package FinalProject.page;

import FinalProject.util.WebDriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private final WebDriver driver;

    private static final By PERSONAL_INFORMATION_BUTTON = By.xpath("//a[@title='Information']");
    private static final By CUSTOMER_WISHLIST_BUTTON = By.xpath("//li[@class='lnk_wishlist']/a");
    private static final By DRESSES_PRODUCT_BUTTON = By.xpath("(//a[@title='Dresses'])[2]");

    public AccountPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_INFORMATION_BUTTON));
    }

    @Step("Navigate to Wishlist page")
    public WishlistPage navigateToWishlistPage() {
        driver.findElement(CUSTOMER_WISHLIST_BUTTON).click();
        return new WishlistPage();
    }

    @Step("Navigate to Product page")
    public ProductPage navigateToProductPage() {
        driver.findElement(DRESSES_PRODUCT_BUTTON).click();
        return new ProductPage();
    }
}