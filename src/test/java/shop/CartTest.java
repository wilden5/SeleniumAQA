package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartTest {

    private Cart cart;
    private RealItem car;
    private VirtualItem disk;
    private VirtualItem disk2;

    @Tag("shop-test")
    @BeforeAll
    void setUp() {
        cart = new Cart("denis-cart");
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
    @Order(1)
    void check_cart_name_and_total_price() {
        String expectedResult = "denis-cart";
        double expectedResult2 = (car.getPrice() + disk.getPrice()) * 1.2;

        assertAll("Cart name and price after cart creation",
                () -> assertEquals(expectedResult, cart.getCartName()),
                () -> assertEquals(expectedResult2, cart.getTotalPrice())
        );
    }

    @Tag("shop-test")
    @DisplayName("Check add new item to cart")
    @Test
    @Order(2)
    void check_add_new_item_to_cart() {
        double priceBefore = cart.getTotalPrice();
        cart.addVirtualItem(disk2);

        Assertions.assertTrue(priceBefore < cart.getTotalPrice());
        assertNotEquals(priceBefore, cart.getTotalPrice());
    }
}