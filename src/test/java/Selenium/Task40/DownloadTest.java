package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 20);
    }

    @AfterEach
    void closeDriver() {
        driver.quit();
    }

    @Test
    void progressBar() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        driver.findElement(By.xpath("//button[@id='cricle-btn']")).click();

        webDriverWait.until(new ExpectedCondition<Boolean>() {
                                public Boolean apply(WebDriver driver) {
                                    int progress = Integer.parseInt(driver.findElement(By
                                            .xpath("//div[@class='percenttext']")).getText()
                                            .replace("%", ""));
                                    if (progress >= 50) {
                                        driver.navigate().refresh();
                                        return true;
                                    }
                                    return false;
                                }
                            }
        );
        Assertions.assertEquals("0%", driver.findElement(By.xpath("//div[@class='percenttext']")).getText());
    }
}