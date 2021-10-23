package Selenium.Task80;

import Selenium.Task60.PageObject.test.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadTest extends BaseTest {

    private static final String PATH_TO_FILE_DIRECTORY = "C:\\Users\\words\\Desktop\\UnitTesting\\" +
            "UnitTesting-master\\src\\test\\java\\Selenium\\Files";
    private static final String START_URL = "http://demo.automationtesting.in/FileDownload.html#google_vignette";

    private WebDriver driver;
    private File folder;
    private File file;
    private File[] listOfFiles;
    private boolean isFound;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        createNewFolder("src/test/java/Selenium/Files/");
    }

    @AfterEach
    void setDown() {
        driver.close();
        for (File file : folder.listFiles()) {
            file.delete();
        }
    }

    @DisplayName("Download file with Chrome")
    @Test
    void fileDownloadChrome() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", PATH_TO_FILE_DIRECTORY);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get(START_URL);
        driver.findElement(By.xpath("//a[@type='button']")).click();
        Thread.sleep(15000);
        checkFileInFolder(PATH_TO_FILE_DIRECTORY);

        Assertions.assertTrue(isFound, "Downloaded file is not found");
    }

    @DisplayName("Download file with Firefox")
    @Test
    void fileDownloadFirefox() throws InterruptedException {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("pdfjs.disabled", true);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.download.dir", PATH_TO_FILE_DIRECTORY);
        options.addPreference("browser.download.folderList", 2);

        driver = new FirefoxDriver(options);
        driver.get(START_URL);
        driver.findElement(By.xpath("//a[@type='button']")).click();
        Thread.sleep(15000);
        checkFileInFolder(PATH_TO_FILE_DIRECTORY);

        Assertions.assertTrue(isFound, "Downloaded file is not found");
    }

    public void checkFileInFolder(String path) {
        folder = new File(path);
        listOfFiles = folder.listFiles();
        isFound = false;
        file = null;

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.contains("samplefile")) {
                    file = new File(fileName);
                    isFound = true;
                }
            }
        }
    }
}