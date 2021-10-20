package Selenium.Task60.PageObject.test;

import Selenium.Task60.PageObject.WebDriverSingleton;
import Selenium.Task60.PageObject.page.YandexEntryPage;
import Selenium.Task60.PageObject.page.YandexInboxPage;
import Selenium.Task60.PageObject.page.YandexSignInPage;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class AuthorizationTest extends BaseTest {

    private YandexEntryPage yandexEntryPage;
    private YandexSignInPage yandexSignInPage;
    private YandexInboxPage yandexInboxPage;

    @BeforeEach
    void setUp() {
        yandexEntryPage = new YandexEntryPage();
    }

    @AfterEach
    void close() {
        WebDriverSingleton.getInstance().driverClose();
    }

    @DisplayName("Login Test Yandex")
    @Test
    void loginTestYandex() throws IOException {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("ser2223rer", "0177aaa");

        Assertions.assertTrue(WebDriverSingleton.getInstance().getDriver().getTitle().contains("Inbox")
                , "You are on wrong page. Please check the page!");
        createScreenshot("yandex_inbox_page");
    }

    @DisplayName("Logout Test Yandex")
    @Test
    void logoutTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("wilden.man", "190fF23f3");
        yandexInboxPage.logout();

        Assertions.assertTrue(WebDriverSingleton.getInstance().getDriver().getTitle().contains("Authorization")
                , "You are on wrong page. Please check the page!");
    }
}