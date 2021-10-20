package Selenium.Task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultiSelectListTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    void closeDriver() {
        driver.quit();
    }

    @Test
    void selectValues() {
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        WebElement list = driver.findElement(By.xpath("//select[@id='multi-select']"));
        Select multiSelectList = new Select(list);
        multiSelectList.selectByVisibleText("Florida");
        multiSelectList.selectByVisibleText("Ohio");
        multiSelectList.selectByVisibleText("Texas");

        List<String> selectedOptions = multiSelectList.getAllSelectedOptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedOptions = new ArrayList<>(Arrays.asList("Florida", "Ohio", "Texas"));

        Assertions.assertEquals(expectedOptions, selectedOptions);
    }
}