package Selenium.Task60.PageFactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexSignInPage {

    private final WebDriver driver;

    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement sigInButton;

    public YandexSignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YandexInboxPage login(String userName, String userPassword) {
        loginField.sendKeys(userName);
        sigInButton.click();
        passwordField.sendKeys(userPassword);
        sigInButton.click();
        return new YandexInboxPage(driver);
    }
}
