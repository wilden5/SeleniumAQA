package Selenium.Task30;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ByVariables {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://mail.yandex.com/");
        driver.findElement(By.xpath("//div[@class='HeadBanner-Title']")).isDisplayed();
        driver.findElement(By.linkText("Log in")).isDisplayed();
        driver.findElement(By.cssSelector("div[class='HeadBanner-Image HeadBanner-Image_Right']")).isDisplayed();
        driver.findElement(By.tagName("div")).isDisplayed();
        driver.findElement(By.partialLinkText("an")).isDisplayed();

        driver.get("https://passport.yandex.com/");
        driver.findElement(By.name("login")).isDisplayed();
        driver.findElement(By.className("passp-flex-wrapper")).isDisplayed();
        driver.findElement(By.id("passp-field-login")).isDisplayed();
        driver.findElement(By.className("passp-page-overlay")).isDisplayed();
        driver.close();
    }
}
