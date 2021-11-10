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
    private WebDriverParameter parameter;
    private static WebDriverSingleton webDriverSingleton;

    private WebDriverSingleton() {
        parameter = new WebDriverParameter("Chrome", false);
        initDriver();
    }

    public static WebDriverSingleton getInstance() {
        if (webDriverSingleton == null) {
            webDriverSingleton = new WebDriverSingleton();
        }
        return webDriverSingleton;
    }

    private void initDriver() {
        if (parameter.getRemote()) {
            setRemoteDriver();
        } else {
            setLocalDriver();
        }
        setDriverConfig();
    }

    private void setRemoteDriver() {
        DesiredCapabilities dc = null;
        switch (parameter.getName()) {
            case "Sauce":
                dc = DesiredCapabilities.chrome();
                dc.setCapability("platform", "macOS 11.00");
                dc.setCapability("version", "94");
                Map<String, Object> sauceOptions = new HashMap<>();
                dc.setCapability("sauce:options", sauceOptions);
                break;
            case "Grid":
                dc = new DesiredCapabilities();
                dc.setBrowserName("chrome");
                dc.setPlatform(Platform.WINDOWS);
                break;
        }
        try {
            driver = new RemoteWebDriver(new URL(parameter.getUrl()), dc);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Hub's URL incorrect!");
        }
    }

    private void setLocalDriver() {
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

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriverConfig() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setDownDriver() {
        if (webDriverSingleton != null) {
            driver.close();
            webDriverSingleton = null;
        }
    }
}