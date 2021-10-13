package Selenium.Task60.PageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexInboxPage {

    private final WebDriver driver;
    private final By messageBox = By.xpath("//div[@data-key='box=left-box']");
    private final By userMenu = By.xpath("(//div[@class='user-pic user-pic_has-plus_ user-account__pic'])[1]");
    private final By logoutButton = By.xpath("//a[contains(@class, 'item_action_exit')] ");

    public YandexInboxPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageBox));
    }

    public YandexSignInPage logout() {
        driver.findElement(userMenu).click();
        driver.findElement(logoutButton).click();
        return new YandexSignInPage(driver);
    }
}
