package FinalProject.test;

import FinalProject.page.*;
import FinalProject.util.Config;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoWishlistTest extends BaseTest {

    private AccountPage accountPage;
    private WishlistPage wishlistPage;
    private ProductDetailPage productDetailPage;

    @TmsLink("ID-03")
    @DisplayName("Add product to Auto-Created Wishlist")
    @Description("Verify that selected product could be added to Auto-Created Wishlist")
    @Test
    void addProductToAutoCreatedWishlist() {
        accountPage = authenticationPage.loginToAccount(
                Config.getProperty("db.login"), Config.getProperty("db.password"));
        wishlistPage = accountPage.navigateToWishlistPage()
                .getWishlistQuantity();
        productDetailPage = wishlistPage.navigateToProductPage()
                .selectProduct(0)
                .addProductToWishlist();
        wishlistPage = productDetailPage.navigateToAccountPage()
                .navigateToWishlistPage();

        Assertions.assertAll(
                () -> Assertions.assertTrue(wishlistPage.isAutoWishlistAccessible(), "Auto-Wishlist not created"),
                () -> Assertions.assertEquals(Config.getProperty("db.expected.product"),
                        wishlistPage.getProductName(), "Product not found in Auto-Wishlist")
        );
    }
}