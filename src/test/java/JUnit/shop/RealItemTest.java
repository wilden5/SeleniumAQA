package JUnit.shop;

import org.junit.jupiter.api.*;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RealItemTest {

    private RealItem car;

    @Tag("shop-test")
    @BeforeAll
    void setUp() {
        car = new RealItem();
        car.setName("Opel");
        car.setPrice(12500);
        car.setWeight(1337);
    }

    @Tag("shop-test")
    @DisplayName("Check presence of Weight field")
    @Test
    void checkPresenceOfWeightField() {
        String expectedString = String.valueOf(car.getWeight());

        assertTrue(car.toString().contains(expectedString));
    }

    @Tag("shop-test")
    @DisplayName("Check format of print")
    @Test
    void checkFormatOfPrint() {

        assertEquals(car.toString(), String.format("Class: %s; Name: %s; Price: %s; Weight: %s",
                car.getClass(), car.getName(), car.getPrice(), car.getWeight()));
    }
}