package Selenium.Task30;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private static final String USERNAME = "t.acca";
    private static final String PASSWORD = "fdg#54gf4$";
    private static final String TARGET_URL = "https://mail.yandex.com/";
    private static final String EXPECTED_URL = "https://mail.yandex.com/?uid=1499620431#tabs/relevant";
    private static final String EXPECTED_TITLE = "Inbox";

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    void closeDriver() {
        driver.close();
    }

    @DisplayName("Login Test Yandex")
    @Test
    void loginTestYandex() throws InterruptedException {
        driver.get(TARGET_URL);
        driver.findElement(By.xpath("//a[contains(@href, 'auth')]"))
                .click();
        driver.findElement(By.xpath("//input[@id='passp-field-login']"))
                .sendKeys(USERNAME);
        driver.findElement(By.xpath("//button[@id='passp:sign-in']"))
                .click();
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']"))
                .sendKeys(PASSWORD);
        driver.findElement(By.xpath("//button[@id='passp:sign-in']"))
                .click();
        Thread.sleep(3000); // Thread.sleep causes the current thread to suspend execution for a specified period

        Assertions.assertAll(
                () -> Assertions.assertEquals(EXPECTED_URL, driver.getCurrentUrl()),
                () -> Assertions.assertTrue(driver.getTitle().contains(EXPECTED_TITLE)),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//span[@class='mail-NestedList-Item-Name']"))
                        .isDisplayed())
        );
    }

    @DisplayName("Login Test Yandex V2")
    @Test
    void loginTestYandexV2() {
        Actions action = new Actions(driver);
        driver.get(TARGET_URL);
        driver.findElement(By.xpath("//a[contains(@href, 'auth')]"))
                .click();
        action.moveToElement(driver.findElement(By.xpath("//input[@id='passp-field-login']")))
                .click().sendKeys(USERNAME).sendKeys(Keys.ENTER).build().perform();
        action.moveToElement(driver.findElement(By.xpath("//input[@id='passp-field-passwd']")))
                .sendKeys(PASSWORD).sendKeys(Keys.ENTER).build().perform();
        driver.findElement(By.xpath("//div[@data-key='box=left-box']"))
                .isDisplayed();

        Assertions.assertAll(
                () -> Assertions.assertEquals(EXPECTED_URL, driver.getCurrentUrl()),
                () -> Assertions.assertTrue(driver.getTitle().contains(EXPECTED_TITLE)),
                () -> Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='b-messages__placeholder']"))
                        .isDisplayed())
        );
    }
}