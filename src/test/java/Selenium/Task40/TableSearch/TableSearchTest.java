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
    @CsvSource({"60, 330000"})
    public void tableSearch(int targetAge, int targetSalary) {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        dropdown.selectByValue("10");
        List<Employee> employees = sortedList(targetAge, targetSalary);
        employees.stream().forEach(System.out::println);
    }

    private List<Employee> sortedList(int targetAge, int targetSalary) {
        List<Employee> employees = new ArrayList<>();
        List<WebElement> pages = driver.findElements(By.xpath("//div[@id='example_paginate']/span/child::a"));

        for (WebElement page : pages) {
            List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
            WebElement buttonNext = driver.findElement(By.xpath("//a[@id='example_next']"));

            for (WebElement element : rows) {
                String name = element.findElement(By.xpath("td[1]")).getText();
                String position = element.findElement(By.xpath("td[2]")).getText();
                String office = element.findElement(By.xpath("td[3]")).getText();
                String age = element.findElement(By.xpath("td[4]")).getText();
                String salary = element.findElement(By.xpath("td[6]")).getText()
                        .substring(1).replace("/y", "").replace(",", "");

                int convertedAge = Integer.parseInt(age);
                int convertedSalary = Integer.parseInt(salary);

                if (convertedAge > targetAge && convertedSalary <= targetSalary) {
                    employees.add(new Employee(name, position, office));
                }
            }
            buttonNext.click();
        }
        return employees;
    }
}