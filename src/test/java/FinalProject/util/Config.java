package FinalProject.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties PROPERTY = new Properties();

    static {
        InputStream fis = null;

        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            PROPERTY.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return PROPERTY.getProperty(key);
    }
}
