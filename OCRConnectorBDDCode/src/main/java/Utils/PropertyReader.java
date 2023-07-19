package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Properties;

public class PropertyReader {
    public static String getProperty(final String property) throws IOException, URISyntaxException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
       Properties prop = new Properties();
       prop.load(inputStream);
       return prop.getProperty(property);
    }
}
