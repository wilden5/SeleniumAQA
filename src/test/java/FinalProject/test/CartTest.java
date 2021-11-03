package FinalProject.test;

import FinalProject.page.*;
import FinalProject.util.Config;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {

    private AccountPage accountPage;
    private CartPage cartPage;

    @TmsLink("ID-05")
    @DisplayName("Add product to Cart")
    @Description("Verify that customer could add product to cart")
    @Test
    void addProductToCart() {
        accountPage = authenticationPage
                .loginToAccount(Config.getProperty("db.login"), Config.getProperty("db.password"));
        cartPage = accountPage
                .navigateToProductPage()
                .addViaProductList(3);

        Assertions.assertAll(
                () -> Assertions.assertEquals(Config.getProperty("db.expected.cart.price"),
                        cartPage.getTotalPriceOfCart(), "Cart total price not same as expected"),
                () -> Assertions.assertEquals(Config.getProperty("db.expected.product.amount"),
                        cartPage.getNumberOfProduct(), "Amount of product is not same as expected")
        );
    }
}
