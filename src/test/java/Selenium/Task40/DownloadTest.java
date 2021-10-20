package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DownloadTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    void closeDriver() {
        driver.quit();
    }

    @Test
    void progressBar() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        driver.findElement(By.xpath("//button[@id='cricle-btn']")).click();

        while (!driver.findElement(By.xpath("//div[@class='percenttext']")).getText().equals("100")) {
            int progress = Integer.parseInt(driver.findElement(By
                    .xpath("//div[@class='percenttext']")).getText().replace("%", ""));

            if (progress >= 50) {
                driver.navigate().refresh();
                return;
            }
        }
        Assertions.assertEquals("0",driver.findElement(By.xpath("//div[@class='percenttext']")).getText());
    }
}
