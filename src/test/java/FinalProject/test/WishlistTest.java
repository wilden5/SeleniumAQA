package FinalProject.test;

import FinalProject.page.AccountPage;
import FinalProject.page.WishlistPage;
import FinalProject.page.ProductDetailPage;
import FinalProject.util.Config;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WishlistTest extends BaseTest {

    private AccountPage accountPage;
    private WishlistPage wishlistPage;
    private ProductDetailPage productDetailPage;

    @TmsLink("ID-04")
    @DisplayName("Add product to custom Wishlist")
    @Description("Verify that selected product could be added to custom Wishlist")
    @Test
    void addProductToCustomWishlist() {
        accountPage = authenticationPage
                .loginToAccount(Config.getProperty("db.login"), Config.getProperty("db.password"));
        wishlistPage = accountPage
                .navigateToWishlistPage()
                .getWishlistQuantity()
                .createCustomWishlist("test123");
        productDetailPage = wishlistPage
                .navigateToProductPage()
                .selectProduct(2)
                .addProductToWishlist();
        wishlistPage = productDetailPage
                .navigateToAccountPage()
                .navigateToWishlistPage();

        Assertions.assertEquals(Config.getProperty("db.expected.product2"),
                wishlistPage.getProductName(), "Product not found in Wishlist");
    }
}