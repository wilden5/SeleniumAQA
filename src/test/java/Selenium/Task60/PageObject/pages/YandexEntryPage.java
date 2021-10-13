package Selenium.Task60.PageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexEntryPage {

    private final WebDriver driver;
    private final By loginButton = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[2]");

    public YandexEntryPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexSignInPage navigateToSignInPage() {
        driver.findElement(loginButton).click();
        return new YandexSignInPage(driver);
    }
}
