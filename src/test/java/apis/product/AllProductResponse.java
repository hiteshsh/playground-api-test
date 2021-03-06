package apis.product;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AllProductResponse {
    private int total;
    private int limit;
    private int skip;
    private ProductResponse[] data;

}
