package test;

import org.junit.BeforeClass;
import util.PropertiesReader;

public class BaseTest {

    @BeforeClass
    public static void setUp(){
        PropertiesReader.loadProperties();
    }
}
