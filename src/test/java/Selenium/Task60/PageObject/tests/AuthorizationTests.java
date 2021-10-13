package Selenium.Task60.PageObject.tests;

import Selenium.Task60.PageObject.pages.YandexEntryPage;
import Selenium.Task60.PageObject.pages.YandexInboxPage;
import Selenium.Task60.PageObject.pages.YandexSignInPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class AuthorizationTests extends BaseTest {

    private WebDriver driver;
    private YandexEntryPage yandexEntryPage;
    private YandexSignInPage yandexSignInPage;
    private YandexInboxPage yandexInboxPage;

    @BeforeEach
    void setUp() {
        driver = BaseTest.getInstance();
        yandexEntryPage = new YandexEntryPage(driver);
        driver.get(TARGET_URL);
    }

    @AfterEach
    void close() {
        BaseTest.driverClose();
    }

    @DisplayName("Login Test Yandex")
    @Test
    void loginTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("wilden.man", "190fF23f3");

        Assertions.assertTrue(driver.getTitle().contains("Inbox"));
    }

    @DisplayName("Logout Test Yandex")
    @Test
    void logoutTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("wilden.man", "190fF23f3");
        yandexInboxPage.logout();

        Assertions.assertEquals(EXPECTED_LOGOUT_TITLE, driver.getTitle());
    }
}