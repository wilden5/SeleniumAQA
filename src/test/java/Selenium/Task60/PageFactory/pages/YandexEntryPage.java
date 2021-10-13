package Selenium.Task60.PageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexEntryPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='HeadBanner-ButtonsWrapper']/a[2]")
    private WebElement loginButton;

    public YandexEntryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YandexSignInPage navigateToSignInPage() {
        loginButton.click();
        return new YandexSignInPage(driver);
    }
}
