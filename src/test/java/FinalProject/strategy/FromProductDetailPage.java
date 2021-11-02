package FinalProject.strategy;

import FinalProject.test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FromProductDetailPage implements AddToCartStrategy {

    private WebDriver driver;

    private static final By CLOSE_MODAL_WINDOW_BUTTON = By.xpath("//span[@class='cross']");
    private static final By NAVIGATE_TO_PRODUCT_PAGE = By.xpath("(//a[@title='Dresses'])[2]");

    public FromProductDetailPage() {
        driver = BaseTest.driver;
    }

    @Override
    public List<WebElement> addProductToCart(int amount) {
        List<WebElement> listOfProduct = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            driver.findElement(By.xpath("(//div[@class='product-container'])[" + i + "]//h5[@itemprop='name']/a")).click();
            driver.findElement(By.xpath("//button[@class='exclusive']")).click();
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(CLOSE_MODAL_WINDOW_BUTTON));
            driver.findElement(CLOSE_MODAL_WINDOW_BUTTON).click();
            driver.findElement(NAVIGATE_TO_PRODUCT_PAGE).click();
        }
        return listOfProduct;
    }
}
