package Selenium.Task60.PageObject.test;

import Selenium.Task60.PageObject.WebDriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TestBase {

    public void createNewFolder(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void createScreenshot(String screenshotName) throws IOException {
        createNewFolder("src/test/java/Selenium/Screenshots/");
        File src = ((TakesScreenshot) WebDriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("src/test/java/Selenium/Screenshots/" + screenshotName
                + RandomStringUtils.randomNumeric(5) + ".png"));
    }
}