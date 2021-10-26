package Selenium.Task110;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumGridTest {

    WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        dc.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
    }

    @AfterEach
    void setDown() {
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

    @Test
    void selectValues() {
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        WebElement list = driver.findElement(By.xpath("//select[@id='multi-select']"));
        Select multiSelectList = new Select(list);
        List<String> expectedOptions = new ArrayList<>(Arrays.asList("Florida", "Ohio", "Texas"));
        for (String state : expectedOptions) {
            multiSelectList.selectByVisibleText(state);
        }
        List<String> selectedOptions = multiSelectList.getAllSelectedOptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());

        Assertions.assertEquals(expectedOptions, selectedOptions);
    }

    @Test
    void progressBar() {
        webDriverWait = new WebDriverWait(driver, 20);
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