package FinalProject.test;

import FinalProject.page.AuthenticationPage;
import FinalProject.util.Config;
import FinalProject.util.TestListener;
import FinalProject.util.WebDriverParameter;
import FinalProject.util.WebDriverSingleton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

@ExtendWith(TestListener.class)
public class BaseTest {

    public static WebDriver driver;
    AuthenticationPage authenticationPage = new AuthenticationPage(driver);

    @BeforeAll
    static void setUp() throws MalformedURLException {
        WebDriverSingleton singleton = WebDriverSingleton.getInstance();
        WebDriverParameter parameter = new WebDriverParameter("Chrome", false);
        driver = singleton.getDriverWithParameter(parameter);
    }

    @AfterAll()
    static void setDown() {
        WebDriverSingleton.getInstance().setDownDriver();
    }
}