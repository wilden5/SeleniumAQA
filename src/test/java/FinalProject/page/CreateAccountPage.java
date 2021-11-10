package FinalProject.page;

import FinalProject.util.CustomerInfo;
import FinalProject.util.WebDriverSingleton;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {

    private final WebDriver driver;

    private static final By CUSTOMER_FIRST_NAME_INPUT = By.xpath("//input[@id='customer_firstname']");
    private static final By CUSTOMER_LAST_NAME_INPUT = By.xpath("//input[@id='customer_lastname']");
    private static final By CUSTOMER_PASSWORD_INPUT = By.xpath("//input[@id='passwd']");
    private static final By ADDRESS_INPUT = By.xpath("//input[@id='address1']");
    private static final By CITY_INPUT = By.xpath("//input[@id='city']");
    private static final By STATE_SELECT = By.xpath("//select[@id='id_state']");
    private static final By POSTAL_CODE_INPUT = By.xpath("//input[@id='postcode']");
    private static final By COUNTRY_SELECT = By.xpath("//select[@id='id_country']");
    private static final By MOBILE_PHONE_INPUT = By.xpath("//input[@id='phone_mobile']");
    private static final By SUBMIT_ACCOUNT_BUTTON = By.xpath("//button[@id='submitAccount']");

    public CreateAccountPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_ACCOUNT_BUTTON));
    }

    @Step("Populate required fields in second part of registration")
    public AccountPage finishRegistration(CustomerInfo customer) {
        driver.findElement(CUSTOMER_FIRST_NAME_INPUT).sendKeys(customer.getFirstName());
        driver.findElement(CUSTOMER_LAST_NAME_INPUT).sendKeys(customer.getLastName());
        driver.findElement(CUSTOMER_PASSWORD_INPUT).sendKeys(customer.getPassword());
        driver.findElement(ADDRESS_INPUT).sendKeys(customer.getAddress());
        driver.findElement(CITY_INPUT).sendKeys(customer.getCity());
        driver.findElement(POSTAL_CODE_INPUT).sendKeys(customer.getPostalCode());
        driver.findElement(MOBILE_PHONE_INPUT).sendKeys(customer.getMobilePhone());

        driver.findElement(STATE_SELECT).click();
        new Select(driver.findElement(STATE_SELECT)).selectByVisibleText(customer.getState());
        new Select(driver.findElement(COUNTRY_SELECT)).selectByVisibleText(customer.getCountry());
        driver.findElement(SUBMIT_ACCOUNT_BUTTON).click();
        return new AccountPage();
    }
}