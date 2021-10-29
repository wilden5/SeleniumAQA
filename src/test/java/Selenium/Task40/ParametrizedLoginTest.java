package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ParametrizedLoginTest {

    private static final String TARGET_URL = "https://mail.yandex.com/";
    private static final String EXPECTED_TITLE = "Inbox";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

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

    @ParameterizedTest
    @CsvSource({"t.acca,fdg#54gf4$", "h.acca,sakafsk@%$"})
    void loginTestYandex(String userName, String userPassword) {
        webDriverWait = new WebDriverWait(driver, 5);
        driver.get(TARGET_URL);
        driver.findElement(By.xpath("//a[contains(@href, 'auth')]"))
                .click();
        driver.findElement(By.xpath("//input[@id='passp-field-login']"))
                .sendKeys(userName);
        driver.findElement(By.xpath("//button[@id='passp:sign-in']"))
                .click();
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']"))
                .sendKeys(userPassword);
        driver.findElement(By.xpath("//button[@id='passp:sign-in']"))
                .click();
        webDriverWait.pollingEvery(Duration.ofSeconds(1));
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='user-account__name']")));

        Assertions.assertTrue(driver.getTitle().contains(EXPECTED_TITLE));
    }
}