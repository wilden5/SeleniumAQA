package Selenium.Task120;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SauceLabsTest {

    private static final String KEY = "3d48d9c9-68d9-4fe5-a358-f5c7beaed287";
    private static final String URL = "https://wilden24:" + KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    private WebDriver driver;

    @RegisterExtension
    public SauceTestWatcher watcher = new SauceTestWatcher();

    @BeforeEach
    void setUp() throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", "macOS 10.15");
        browserOptions.setCapability("browserVersion", "85");
        Map<String, Object> sauceOptions = new HashMap<>();
        browserOptions.setCapability("sauce:options", sauceOptions);

        driver = new RemoteWebDriver(new URL(URL), browserOptions);
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

    public class SauceTestWatcher implements TestWatcher {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        @Override
        public void testSuccessful(ExtensionContext context) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
            driver.quit();
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
            driver.quit();
        }
    }
}