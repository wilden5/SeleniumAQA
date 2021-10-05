package TestNG.shop;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.RealItem;

public class RealItemTest {

    private RealItem car;

    @Parameters({"weight"})
    @BeforeClass(alwaysRun = true, groups = {"shop", "smoke", "items"})
    void setUp(@Optional("2000") double weight) {
        car = new RealItem();
        car.setName("Audi");
        car.setPrice(15000);
        car.setWeight(weight);
    }

    @Test(testName = "Check presence of weight field", groups = {"shop", "smoke", "items"})
    void checkPresenceOfWeightField() {
        String weight = String.valueOf(car.getWeight());

        Assert.assertTrue(car.toString().contains(weight), "Field weight not found");
    }

    @Test(testName = "Check format of print", groups = {"shop", "smoke", "items"})
    void checkFormatOfPrint() {

        Assert.assertEquals(car.toString(), String.format("Class: %s; Name: %s; Price: %s; Weight: %s",
                car.getClass(), car.getName(), car.getPrice(), car.getWeight(), "Wrong format of print"));
    }
}