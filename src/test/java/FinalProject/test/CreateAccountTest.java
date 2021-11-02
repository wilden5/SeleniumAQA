package FinalProject.test;

import FinalProject.page.CreateAccountPage;
import FinalProject.page.AccountPage;
import FinalProject.util.Config;
import FinalProject.util.CustomerInfo;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;

public class CreateAccountTest extends BaseTest {

    private CreateAccountPage createAccountPage;
    private AccountPage accountPage;
    private CustomerInfo randomUser = new CustomerInfo(
            Config.getProperty("db.customer.first.name"),
            Config.getProperty("db.customer.last.name"),
            new Faker().internet().emailAddress(),
            Config.getProperty("db.customer.password"),
            Config.getProperty("db.customer.address"),
            Config.getProperty("db.customer.city"),
            Config.getProperty("db.customer.state"),
            Config.getProperty("db.customer.postal.code"),
            Config.getProperty("db.customer.country"),
            Config.getProperty("db.customer.mobile.phone")
    );

    @TmsLink("ID-01")
    @DisplayName("Create new customer account")
    @Description("Verify that customer could create account")
    @Test
    void createNewAccount() {
        createAccountPage = authenticationPage.startRegistration(randomUser.getEmail());
        accountPage = createAccountPage.finishRegistration(randomUser);

        Assertions.assertEquals(Config.getProperty("db.customer.account.url")
                , driver.getCurrentUrl(), "Registration failed, check entered data");
    }
}