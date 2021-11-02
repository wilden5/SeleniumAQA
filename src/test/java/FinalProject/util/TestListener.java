package FinalProject.util;

import FinalProject.test.BaseTest;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class TestListener implements TestWatcher {

    @Attachment(value = "{name}", type = "image/png")
    private byte[] captureScreenshot(String name) {
        return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Details", type = "text/plain")
    private String getEnvironmentDetails() {
        Capabilities capabilities = ((RemoteWebDriver) BaseTest.driver).getCapabilities();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return "Execute date: " + format.format((date)) +
                "\nBrowser name: " + capabilities.getBrowserName() +
                "\nBrowser version:" + capabilities.getVersion() +
                "\nPlatform: " + capabilities.getPlatform();
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        getEnvironmentDetails();
        captureScreenshot(extensionContext.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {

    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {

    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {

    }
}