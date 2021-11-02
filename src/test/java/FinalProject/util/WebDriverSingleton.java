package FinalProject.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private WebDriver driver;
    private static WebDriverSingleton webDriverSingleton;

    private WebDriverSingleton() {

    }

    public static WebDriverSingleton getInstance() {
        if (webDriverSingleton == null) {
            webDriverSingleton = new WebDriverSingleton();
        }
        return webDriverSingleton;
    }

    public WebDriver getDriverWithParameter(WebDriverParameter parameter) throws MalformedURLException {
        if (parameter.getRemote()) {
            DesiredCapabilities dc = null;
            switch (parameter.getName()) {
                case "Sauce":
                    dc = DesiredCapabilities.chrome();
                    dc.setCapability("platform", "macOS 10.15");
                    dc.setCapability("version", "85");
                    Map<String, Object> sauceOptions = new HashMap<>();
                    dc.setCapability("sauce:options", sauceOptions);
                    break;
                case "Grid":
                    dc = new DesiredCapabilities();
                    dc.setBrowserName("chrome");
                    dc.setPlatform(Platform.WINDOWS);
                    break;
            }
            driver = new RemoteWebDriver(new URL(parameter.getUrl()), dc);
        } else {
            switch (parameter.getName()) {
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void setDownDriver() {
        if (webDriverSingleton != null) {
            driver.close();
            webDriverSingleton = null;
        }
    }
}