package FinalProject.test;

import FinalProject.page.AccountPage;
import FinalProject.util.Config;
import FinalProject.util.WebDriverSingleton;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;

public class LoginTest extends BaseTest {

    private AccountPage accountPage;

    @TmsLink("ID-02")
    @DisplayName("Login to exist customer account")
    @Description("Verify that customer could login to account")
    @Test
    void loginToExistingAccount() {
        accountPage = authenticationPage.loginToAccount(
                Config.getProperty("db.login"), Config.getProperty("db.password"));

        Assertions.assertEquals(Config.getProperty("db.customer.account.url"),
                WebDriverSingleton.getInstance().getDriver().getCurrentUrl(), "Login failed, check entered data");
    }
}