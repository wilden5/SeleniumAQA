package Selenium.Task60.PageFactory.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static WebDriver driver;
    protected static String TARGET_URL = "https://mail.yandex.com/";
    protected static String EXPECTED_LOGOUT_TITLE = "Authorization";

    public static WebDriver getInstance() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void driverClose() {
        driver.quit();
        driver = null;
    }
}
