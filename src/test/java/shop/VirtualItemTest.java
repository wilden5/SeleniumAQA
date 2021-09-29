package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VirtualItemTest {

    private VirtualItem disk;

    @Tag("shop-test")
    @BeforeAll
    void setUp() {
        disk = new VirtualItem();
        disk.setName("FIFA22");
        disk.setPrice(68);
        disk.setSizeOnDisk(23000);
    }

    @Tag("shop-test")
    @DisplayName("Check presence of Size on disk field")
    @Test
    void checkPresenceOfSizeField() {
        String expectedString = String.valueOf(disk.getSizeOnDisk());

        assertTrue(disk.toString().contains(expectedString));
    }

    @Tag("shop-test")
    @DisplayName("Check format of print")
    @Test
    void checkFormatOfPrint() {

        assertEquals(disk.toString(), String.format("Class: %s; Name: %s; Price: %s; Size on disk: %s",
                disk.getClass(), disk.getName(), disk.getPrice(), disk.getSizeOnDisk()));
    }
}