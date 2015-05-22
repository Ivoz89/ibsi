package mistrzowie.shop.logic.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Arek
 */
public class PropertiesUtil {

    private Properties props;

    public PropertiesUtil(String propFilename) {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream
                    = getClass().getClassLoader().getResourceAsStream(propFilename);
            if (resourceAsStream == null) {
                System.out.println("Nie odnaleziono pliku properties");
                return;
            }
            props = new Properties();
            props.load(resourceAsStream);
            resourceAsStream.close();
        } catch (IOException ex) {
            System.out.println("Bład ladowania pliku properties");
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
