package Selenium.Task60.PageObject.test;

import Selenium.Task60.PageObject.TestListener;
import Selenium.Task60.PageObject.WebDriverSingleton;
import Selenium.Task60.PageObject.page.YandexEntryPage;
import Selenium.Task60.PageObject.page.YandexInboxPage;
import Selenium.Task60.PageObject.page.YandexSignInPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(TestListener.class)
public class AuthorizationTest extends BaseTest {

    private YandexEntryPage yandexEntryPage;
    private YandexSignInPage yandexSignInPage;
    private YandexInboxPage yandexInboxPage;

    @BeforeEach
    void setUp() {
        yandexEntryPage = new YandexEntryPage();
    }

    @Feature("Login")
    @Description("Verify that user could login to Yandex")
    @TmsLink("ID-01")
    @DisplayName("Login Test Yandex")
    @Test
    void loginTestYandex() throws IOException {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("ser2223rer", "0177aaa");

        Assertions.assertTrue(WebDriverSingleton.getInstance().getDriver().getTitle().contains("Inbox")
                , "You are on wrong page. Please check the page!");
        createScreenshot("yandex_inbox_page");
    }

    @Feature("Logout")
    @Description("Verify that user could logout from Yandex")
    @TmsLink("ID-02")
    @DisplayName("Logout Test Yandex")
    @Test
    void logoutTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("wilden.man", "190fF23f3");
        yandexInboxPage.logout();

        Assertions.assertTrue(WebDriverSingleton.getInstance().getDriver().getTitle().contains("Authorization")
                , "You are on wrong page. Please check the page!");
    }

    @Flaky
    @Feature("Login")
    @Description("Verify that user could login to Yandex")
    @TmsLink("ID-03")
    @DisplayName("Login Test Yandex v2")
    @Test
    void loginTestYandexFailed() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("ser2223rer", "0177aaa");

        Assertions.assertTrue(WebDriverSingleton.getInstance().getDriver().getTitle().contains("wrongTitleOfPage")
                , "You are on wrong page. Please check the page!");
    }
}