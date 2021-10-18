package Selenium.Task60.PageObject.page;

import Selenium.Task60.PageObject.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexEntryPage {

    private final WebDriver driver;
    private final By loginButton = By.xpath("//a[contains(@href, 'auth')]");

    public YandexEntryPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        driver.get("https://mail.yandex.com/");
    }

    public YandexSignInPage navigateToSignInPage() {
        driver.findElement(loginButton).click();
        return new YandexSignInPage();
    }
}
