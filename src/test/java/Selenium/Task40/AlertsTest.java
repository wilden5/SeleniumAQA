package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AlertsTest {

    private WebDriver driver;
    private WebElement confirmBoxButton;
    private WebElement confirmResult;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        confirmBoxButton = driver.findElement(By.xpath("//button[contains(@onclick, 'myConfirm')]"));
        confirmResult = driver.findElement(By.xpath("//p[@id='confirm-demo']"));
    }

    @AfterEach
    void closeDriver() {
        driver.quit();
    }

    @Test
    void jsConfirmBoxAccept() {
        confirmBoxButton.click();
        driver.switchTo().alert().accept();

        Assertions.assertTrue(confirmResult.getText().contains("OK"));
    }

    @Test
    void jsConfirmBoxDecline() {
        confirmBoxButton.click();
        driver.switchTo().alert().dismiss();

        Assertions.assertTrue(confirmResult.getText().contains("Cancel"));
    }

    @Test
    void jsAlertBox() {
        driver.findElement(By.xpath("//button[contains(@onclick, 'myAlert')]"))
                .click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertTrue(alert.getText()
                .equalsIgnoreCase("I am an alert box!"));
    }
}