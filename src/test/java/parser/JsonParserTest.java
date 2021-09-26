package parser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JsonParserTest {

    private JsonParser jsonParser;
    private Cart cart;
    private RealItem car;
    private Path pathToFile;
    private String fileExtension;

    @Tag("parser-test")
    @BeforeAll
    void setUp() {
        jsonParser = new JsonParser();
        cart = new Cart("denis-cart");
        pathToFile = Paths.get("src", "main", "resources", cart.getCartName());
        fileExtension = ".json";

        car = new RealItem();
        car.setName("Opel");
        car.setPrice(12500);
        car.setWeight(1337);

        cart.addRealItem(car);
    }

    @Tag("parser-test")
    @AfterAll
    void clean_data() throws IOException {
        Files.deleteIfExists(Paths.get(pathToFile + fileExtension));
    }

    @Tag("parser-test")
    @DisplayName("Check write to file")
    @Test
    @Order(1)
    void check_write_to_file() {
        jsonParser.writeToFile(cart);

        assertTrue(Files.exists(Paths.get(pathToFile + fileExtension)));
    }

    @Disabled
    @Tag("parser-test")
    @DisplayName("Check read exist file")
    @Test
    @Order(2)
    void read_exist_file() {
        //double expectedPrice = 15000; - could be used as additional verification
        Cart expectedResult = jsonParser.readFromFile(new File(pathToFile + fileExtension));

        assertEquals(expectedResult.getTotalPrice(), cart.getTotalPrice());
    }

    @Tag("parser-test")
    @DisplayName("Check NoSuchFileException")
    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"cart1", "cart2", "cart3", "cart4", "cart5"})
    void check_NoSuchFileException(String fileName) {
        Path path = Paths.get("src", "main", "resources", fileName);

        assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(new File(path + fileExtension)));
    }

}