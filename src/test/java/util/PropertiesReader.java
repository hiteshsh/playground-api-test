package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hiteshs on 7/11/18.
 */
public class PropertiesReader {
    private static Properties properties;

    public static void loadProperties() {
        if(properties==null) {
            properties = createProperties();
        }
    }

    private static Properties createProperties() {
        InputStream is = null;
        String env = System.getProperty("environment");
        if (env!=null && env.equalsIgnoreCase("local")) {
            is = PropertiesReader.class.getResourceAsStream("/properties" +  File.separator  + "local.properties");
        }

        Properties prop = new Properties();

        try {
            prop.load(is);
        } catch (IOException e) {

        }

        return prop;
    }

    public String getPlayGroundBaseUrl(){
        return properties.getProperty("PLAY_GROUND_BASE_URL");
    }

}
