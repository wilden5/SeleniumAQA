package Selenium.Task60.PageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private final WebDriver driver;
    private static WebDriverSingleton webDriverSingleton;

    private WebDriverSingleton() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static WebDriverSingleton getInstance() {
        if (webDriverSingleton == null) {
            webDriverSingleton = new WebDriverSingleton();
        }
        return webDriverSingleton;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void driverClose() {
        if (webDriverSingleton != null) {
            driver.close();
            webDriverSingleton = null;
        }
    }
}