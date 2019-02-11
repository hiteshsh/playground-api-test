package apis.product;

import apis.category.CategoryRequestBody;
import apis.category.CategoryResponse;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductResponse {

    private int id;
    private String name;
    private String type;
    private String upc;
    private float shipping;
    private double price;
    private String description;
    private String model;
    private String manufacturer;
    private String url;
    private String image;
    private String updatedAt;
    private String createdAt;
    private CategoryResponse[] categories;
}
