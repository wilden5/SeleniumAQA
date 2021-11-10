package FinalProject.page;

import FinalProject.util.Config;
import FinalProject.util.WebDriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPage {

    private final WebDriver driver;

    private final static String AUTH_PAGE_URL = Config.getProperty("db.auth.page.url");
    private final static By CREATE_ACCOUNT_EMAIL_INPUT = By.xpath("//input[@id='email_create']");
    private final static By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@id='SubmitCreate']");
    private final static By EXISTED_ACCOUNT_EMAIL_INPUT = By.xpath("//input[@id='email']");
    private final static By EXISTED_ACCOUNT_PASSWORD_INPUT = By.xpath("//input[@id='passwd']");
    private final static By SIGN_IN_BUTTON = By.xpath("//button[@id='SubmitLogin']");

    public AuthenticationPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        driver.get(AUTH_PAGE_URL);
    }

    @Step("Registration Email address: {emailAddress}")
    public CreateAccountPage startRegistration(String emailAddress) {
        driver.findElement(CREATE_ACCOUNT_EMAIL_INPUT).sendKeys(emailAddress);
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
        //Thread.sleep(5000); for Firefox CreateAccountTest
        return new CreateAccountPage();
    }

    @Step("Credentials of existing account: {emailAddress}, {password}")
    public AccountPage loginToAccount(String emailAddress, String password) {
        driver.findElement(EXISTED_ACCOUNT_EMAIL_INPUT).sendKeys(emailAddress);
        driver.findElement(EXISTED_ACCOUNT_PASSWORD_INPUT).sendKeys(password);
        driver.findElement(SIGN_IN_BUTTON).click();
        return new AccountPage();
    }
}