package FinalProject.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties property = new Properties();

    static {
        InputStream fis = null;

        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        return property.getProperty(key);
    }
}
