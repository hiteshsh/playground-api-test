package apis.product;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class ProductRequestBody {

    private String name;
    private String type;
    private String upc;
    private float shipping;
    private float price;
    private String description;
    private String model;
    private String manufacturer;
    private String url;
    private String image;
}
