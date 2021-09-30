package TestNG.shop;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.VirtualItem;

public class VirtualItemTest {

    private VirtualItem disk;

    @BeforeClass(alwaysRun = true, groups = {"shop", "smoke", "items"})
    void setUp() {
        disk = new VirtualItem();
        disk.setName("PUBG");
        disk.setPrice(30);
        disk.setSizeOnDisk(35000);
    }

    @Test(testName = "Check presence of sizeOnDisk field", groups = {"shop", "smoke", "items"})
    void checkPresenceOfSizeField() {
        String sizeOnDisk = String.valueOf(disk.getSizeOnDisk());

        Assert.assertTrue(disk.toString().contains(sizeOnDisk), "Field sizeOnDisk not found");
    }

    @Test(testName = "Check format of print", groups = {"shop", "smoke", "items"})
    void checkFormatOfPrint() {

        Assert.assertEquals(disk.toString(), String.format("Class: %s; Name: %s; Price: %s; Size on disk: %s",
                disk.getClass(), disk.getName(), disk.getPrice(), disk.getSizeOnDisk(), "Wrong format of print"));
    }
}