package TestNG.shop;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    private SoftAssert softAssert;
    private Cart cart;
    private RealItem car;
    private VirtualItem disk;
    private VirtualItem disk2;

    @BeforeClass(alwaysRun = true, groups = {"shop", "smoke", "cart"})
    void setUp() {
        softAssert = new SoftAssert();
        cart = new Cart(RandomStringUtils.randomAlphabetic(5));
        car = new RealItem();
        car.setName("Audi");
        car.setPrice(15000);
        car.setWeight(2800);

        disk = new VirtualItem();
        disk.setName("PUBG");
        disk.setPrice(30);
        disk.setSizeOnDisk(35000);

        cart.addRealItem(car);
        cart.addVirtualItem(disk);

        disk2 = new VirtualItem();
        disk2.setName("Scum");
        disk2.setPrice(25);
        disk2.setSizeOnDisk(50000);
    }

    @Test(testName = "Check cart name and total price", groups = {"shop", "smoke", "cart"})
    void checkCartNameAndTotalPrice() {
        String expectedCartName = cart.getCartName();
        double expectedTotalPrice = (car.getPrice() + disk.getPrice()) * 1.2;

        softAssert.assertEquals(cart.getCartName(), expectedCartName, "Cart name not same");
        softAssert.assertEquals(cart.getTotalPrice(), expectedTotalPrice, "Cart totalPrice not same");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkCartNameAndTotalPrice"}, testName = "Check add new item to cart", groups = {"shop", "smoke", "cart"})
    void checkAddNewItemToCart() {
        double priceBefore = cart.getTotalPrice();
        cart.addVirtualItem(disk2);

        Assert.assertTrue(priceBefore < cart.getTotalPrice(), "priceBefore same or higher than actual price");
    }

    @Test(enabled = false, testName = "Check delete item from cart", groups = {"shop", "smoke", "cart"})
    void checkDeleteItemFromCart() {
        double priceBeforeAddNewItem = cart.getTotalPrice();
        cart.addVirtualItem(disk2);
        cart.deleteVirtualItem(disk2);

        assertEquals(cart.getTotalPrice(), priceBeforeAddNewItem, "Price not same");
    }
}