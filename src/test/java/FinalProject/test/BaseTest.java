package FinalProject.test;

import FinalProject.page.AuthenticationPage;
import FinalProject.util.TestListener;
import FinalProject.util.WebDriverSingleton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
public class BaseTest {

    AuthenticationPage authenticationPage = new AuthenticationPage();

    @AfterAll()
    static void setDown() {
        WebDriverSingleton.getInstance().setDownDriver();
    }
}