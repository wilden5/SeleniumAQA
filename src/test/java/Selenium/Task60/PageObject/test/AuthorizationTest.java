package Selenium.Task60.PageObject.test;

import Selenium.Task60.PageObject.WebDriverSingleton;
import Selenium.Task60.PageObject.page.YandexEntryPage;
import Selenium.Task60.PageObject.page.YandexInboxPage;
import Selenium.Task60.PageObject.page.YandexSignInPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class AuthorizationTest {

    private WebDriver driver;
    private YandexEntryPage yandexEntryPage;
    private YandexSignInPage yandexSignInPage;
    private YandexInboxPage yandexInboxPage;

    @BeforeEach
    void setUp() {
        driver = WebDriverSingleton.getInstance().getDriver();
        yandexEntryPage = new YandexEntryPage();
    }

    @AfterEach
    void close() {
        WebDriverSingleton.getInstance().driverClose();
    }

    @DisplayName("Login Test Yandex")
    @Test
    void loginTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("safdfsdffsdfsdf", "1799aaa");

        Assertions.assertTrue(driver.getTitle().contains("Inbox")
                , "You are on wrong page. Please check the page!");
    }

    @DisplayName("Logout Test Yandex")
    @Test
    void logoutTestYandex() {
        yandexSignInPage = yandexEntryPage.navigateToSignInPage();
        yandexInboxPage = yandexSignInPage.login("wilden.man", "190fF23f3");
        yandexInboxPage.logout();

        Assertions.assertTrue(driver.getTitle().contains("Authorization")
                , "You are on wrong page. Please check the page!");
    }
}