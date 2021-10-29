package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void closeDriver() {
        driver.quit();
    }

    @Test
    void waitForUser() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
        driver.findElement(By.xpath("//button[@id='save']")).click();
        webDriverWait.until(ExpectedConditions.and(ExpectedConditions.textToBePresentInElement(driver.findElement(By
                        .xpath("//div[@id='loading']")), "First Name : "),
                ExpectedConditions.textToBePresentInElement(driver.findElement(By
                        .xpath("//div[@id='loading']")), "Last Name :")));

        Assertions.assertTrue(driver.findElement(By.xpath("//div[@id='loading']/img")).isDisplayed());
    }
}