package TestNG.parser;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonParserTest {

    private SoftAssert softAssert;
    private Gson gson;
    private JsonParser jsonParser;
    private Cart cart;
    private RealItem car;
    private Path pathToFile;
    private String fileExtension;

    @BeforeClass(alwaysRun = true, groups = {"parser", "smoke"})
    void setUp() {
        softAssert = new SoftAssert();
        gson = new Gson();
        jsonParser = new JsonParser();
        cart = new Cart(RandomStringUtils.randomAlphabetic(5));
        pathToFile = Paths.get("src", "main", "resources", cart.getCartName());
        fileExtension = ".json";

        car = new RealItem();
        car.setName("Audi");
        car.setPrice(15000);
        car.setWeight(2800);

        cart.addRealItem(car);
    }

    @AfterMethod(alwaysRun = true, groups = {"parser", "smoke"})
    void cleanData() throws IOException {
        Files.deleteIfExists(Paths.get(pathToFile + fileExtension));
    }

    @Test(testName = "Check write to file", groups = {"parser", "smoke"})
    void checkWriteToFile() throws IOException {
        jsonParser.writeToFile(cart);
        Reader reader = new FileReader(pathToFile + fileExtension);
        Cart actualCart = gson.fromJson(reader, Cart.class);
        reader.close();

        softAssert.assertFalse(Files.exists(Paths.get(pathToFile + fileExtension, "File not found!")));
        softAssert.assertEquals(actualCart.getCartName(), cart.getCartName(), "Cart name not same");
        softAssert.assertEquals(actualCart.getTotalPrice(), cart.getTotalPrice(), "Cart totalPrice not same");
        softAssert.assertAll();
    }

    @Test(testName = "Check read exist file", groups = {"parser", "smoke"})
    void readExistFile() throws IOException {
        FileWriter writer = new FileWriter(pathToFile + fileExtension);
        writer.write(gson.toJson(cart));
        writer.close();
        Cart expectedCart = jsonParser.readFromFile(new File(pathToFile + fileExtension));

        softAssert.assertEquals(cart.getTotalPrice(), expectedCart.getTotalPrice(), "Cart name not same");
        softAssert.assertEquals(cart.getCartName(), expectedCart.getCartName(), "Cart totalPrice not same");
    }

    @Test(testName = "Check exception", groups = {"parser", "smoke"}, dataProvider = "file extensions")
    void checkException(String fileExtension) {

        Assert.assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(new File(pathToFile + fileExtension)));
    }

    @DataProvider(name = "file extensions")
    public Object[][] createExtensionsData() {
        return new Object[][]{
                {".doc"},
                {".csv"},
                {".xlsx"},
                {".xls"},
                {".txt"}};
    }
}