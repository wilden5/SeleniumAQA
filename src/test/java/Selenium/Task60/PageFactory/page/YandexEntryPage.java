package Selenium.Task60.PageFactory.page;

import Selenium.Task60.PageObject.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexEntryPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(@href, 'auth')]")
    private WebElement loginButton;

    public YandexEntryPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        driver.get("https://mail.yandex.com/");
        PageFactory.initElements(driver, this);
    }

    public YandexSignInPage navigateToSignInPage() {
        loginButton.click();
        return new YandexSignInPage();
    }
}