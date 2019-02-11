package util;

public class PlayGroundProperties {
    private static final PropertiesReader reader= new PropertiesReader();

    public static final String baseUrl= reader.getPlayGroundBaseUrl();
    public static final String createStoreUrl= String.format("%s/stores",baseUrl);
    public static final String createServiceUrl= String.format("%s/services",baseUrl);
    public static final String createCategoryUrl= String.format("%s/categories",baseUrl);
    public static final String createProductUrl=String.format("%s/products",baseUrl);
}
