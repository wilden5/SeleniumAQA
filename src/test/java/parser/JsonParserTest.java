package parser;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JsonParserTest {

    private Gson gson;
    private JsonParser jsonParser;
    private Cart cart;
    private RealItem car;
    private Path pathToFile;
    private String fileExtension;

    @Tag("parser-test")
    @BeforeAll
    void setUp() {
        gson = new Gson();
        jsonParser = new JsonParser();
        cart = new Cart(RandomStringUtils.randomAlphabetic(5));
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
    void cleanData() throws IOException {
        Files.deleteIfExists(Paths.get(pathToFile + fileExtension));
    }

    @Tag("parser-test")
    @DisplayName("Check write to file")
    @Test
    void checkWriteToFile() throws IOException {
        jsonParser.writeToFile(cart);
        Reader reader = new FileReader(pathToFile + fileExtension);
        Cart actualCart = gson.fromJson(reader, Cart.class);
        reader.close();

        assertAll("Write to file, verification of content",
                () -> assertFalse(Files.exists(Paths.get(pathToFile + fileExtension, "File not found!"))),
                () -> assertEquals(cart.getCartName(), actualCart.getCartName()),
                () -> assertEquals(cart.getTotalPrice(), actualCart.getTotalPrice())
        );
    }

    @Tag("parser-test")
    @DisplayName("Check read exist file")
    @Test
    void readExistFile() throws IOException {
        FileWriter writer = new FileWriter(pathToFile + fileExtension);
        writer.write(gson.toJson(cart));
        writer.close();
        Cart expectedCart = jsonParser.readFromFile(new File(pathToFile + fileExtension));

        assertAll(
                () -> assertEquals(expectedCart.getTotalPrice(), cart.getTotalPrice()),
                () -> assertEquals(expectedCart.getCartName(), cart.getCartName())
        );
    }

    @Tag("parser-test")
    @DisplayName("Check NoSuchFileException")
    @ParameterizedTest
    @ValueSource(strings = {".doc", ".csv", ".xlsx", ".xls", ".txt"})
    void checkException(String fileExtension) {

        assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(new File(pathToFile + fileExtension)));
    }
}