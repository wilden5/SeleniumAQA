package Selenium.Task40.TableSearch;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TableSearchTest {

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

    @ParameterizedTest
    @CsvSource({"25, 100000"})
    public void tableSearch(int age, int salary) {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        dropdown.selectByValue("10");
        List<Employee> employees = getData(age, salary);
        employees.stream().forEach(System.out::println);
    }

    private List<Employee> getData(int targetAge, int targetSalary) {
        List<Employee> employees = new ArrayList<>();
        int numOfRows = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
        for (int i = 1; i <= numOfRows; i++) {
            String name = driver.findElement(By.xpath("//tbody//tr//td[1]")).getText();
            String position = driver.findElement(By.xpath("//tbody//tr//td[2]")).getText();
            String office = driver.findElement(By.xpath("//tbody//tr//td[3]")).getText();
            String age = driver.findElement(By.xpath("//tbody//tr//td[4]")).getText();
            String salary = driver.findElement(By.xpath("//tbody//tr//td[6]")).getText()
                    .substring(1).replace("/y", "").replace(",", "");

            int a = Integer.parseInt(age);
            int s = Integer.parseInt(salary);

            if (a > targetAge && s <= targetSalary) {
                employees.add(new Employee(name, position, office, a, s));

            }
            WebElement buttonNext = driver.findElement(By.id("example_next"));
            buttonNext.click();
        }
        return employees;
    }
}