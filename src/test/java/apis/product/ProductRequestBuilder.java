package apis.product;

import java.util.Optional;

public class ProductRequestBuilder {

    private ProductRequestBody productRequestBody;

    public ProductRequestBuilder() {
        this.productRequestBody = new ProductRequestBody();
    }

    public ProductRequestBuilder withName(String name){
        productRequestBody.setName(name);
        return this;
    }

    public ProductRequestBuilder withType(String type){
        productRequestBody.setType(type);
        return this;
    }
    public ProductRequestBuilder withUpc(String upc){
        productRequestBody.setUpc(upc);
        return this;
    }
    public ProductRequestBuilder withShipping(float shipping){
        productRequestBody.setShipping(shipping);
        return this;
    }
    public ProductRequestBuilder withDescription(String description){
        productRequestBody.setDescription(description);
        return this;
    }
    public ProductRequestBuilder withPrice(float price){
        productRequestBody.setPrice(price);
        return this;
    }
    public ProductRequestBuilder withModel(String model){
        productRequestBody.setModel(model);
        return this;
    }
    public ProductRequestBuilder withManufacturer(String manufacturer){
        productRequestBody.setManufacturer(manufacturer);
        return this;
    }
    public ProductRequestBuilder withUrl(String url){
        productRequestBody.setUrl(url);
        return this;
    }
    public ProductRequestBuilder withImage(String image){
        productRequestBody.setImage(image);
        return this;
    }
    public ProductRequestBody build(){
        return productRequestBody;
    }
}
