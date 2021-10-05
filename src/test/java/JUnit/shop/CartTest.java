package JUnit.shop;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CartTest {

    private Cart cart;
    private RealItem car;
    private VirtualItem disk;
    private VirtualItem disk2;

    @Tag("shop-test")
    @BeforeAll
    void setUp() {
        cart = new Cart(RandomStringUtils.randomAlphabetic(5));
        car = new RealItem();
        car.setName("Opel");
        car.setPrice(12500);
        car.setWeight(1337);

        disk = new VirtualItem();
        disk.setName("FIFA22");
        disk.setPrice(60);
        disk.setSizeOnDisk(23000);

        cart.addVirtualItem(disk);
        cart.addRealItem(car);

        disk2 = new VirtualItem();
        disk2.setName("Max Payne");
        disk2.setPrice(20);
        disk2.setSizeOnDisk(8000);
    }

    @Tag("shop-test")
    @DisplayName("Check cart name and total price")
    @Test
    void checkCartNameAndTotalPrice() {
        String expectedCartName = cart.getCartName();
        double expectedTotalPrice = (car.getPrice() + disk.getPrice()) * 1.2;

        assertAll("Cart name and price after cart creation",
                () -> assertEquals(expectedCartName, cart.getCartName()),
                () -> assertEquals(expectedTotalPrice, cart.getTotalPrice())
        );
    }

    @Tag("shop-test")
    @DisplayName("Check add new item to cart")
    @Test
    void checkAddNewItemToCart() {
        double priceBefore = cart.getTotalPrice();
        cart.addVirtualItem(disk2);

        assertAll("Comparing priceBefore to actual price of cart",
                () -> assertTrue(priceBefore < cart.getTotalPrice()),
                () -> assertNotEquals(priceBefore, cart.getTotalPrice())
        );
    }

    @Disabled
    @Tag("shop-test")
    @DisplayName("Check delete of item from cart")
    @Test
    void checkDeleteItemFromCart() {
        double priceBeforeAddNewItem = cart.getTotalPrice();
        cart.addVirtualItem(disk2);
        cart.deleteVirtualItem(disk2);
        assertEquals(priceBeforeAddNewItem, cart.getTotalPrice());
    }
}