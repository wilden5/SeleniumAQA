package Selenium.Task60.PageObject.page;

import Selenium.Task60.PageObject.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexInboxPage {

    private final WebDriver driver;
    private final By messageBox = By.xpath("//div[@data-key='box=left-box']");
    private final By userMenu = By.xpath("(//span[@class='user-account__name']/following-sibling::div");
    private final By logoutButton = By.xpath("//a[contains(@class, 'item_action_exit')]");

    public YandexInboxPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageBox));
    }

    public YandexSignInPage logout() {
        driver.findElement(userMenu).click();
        driver.findElement(logoutButton).click();
        return new YandexSignInPage();
    }
}
