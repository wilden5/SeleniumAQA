package Selenium.Task60.PageObject;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Optional;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class TestListener implements TestWatcher {

    @Attachment(value = "{name}", type = "image/png")
    private byte[] captureScreenshot(String name) {
        return ((TakesScreenshot) WebDriverSingleton.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void setAllureEnvironment(String browserName, String browserVersion) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browserName)
                        .put("Browser.Version", browserVersion)
                        .build());
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        captureScreenshot(extensionContext.getDisplayName());
        setAllureEnvironment("Chrome","95.0.4638.54");
        WebDriverSingleton.getInstance().driverClose();
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        WebDriverSingleton.getInstance().driverClose();
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        WebDriverSingleton.getInstance().driverClose();
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        WebDriverSingleton.getInstance().driverClose();
    }
}