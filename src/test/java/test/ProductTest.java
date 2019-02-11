package test;

import apis.product.*;
import apis.store.AllStoreResponse;
import apis.store.Store;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest extends BaseTest{

    @Test
    public void canCreateAndFindProduct(){
        ProductRequestBody productRequestBody= new ProductRequestBuilder()
                .withName("prod1").withDescription("test prod").withPrice(100).withModel("XAEWRT")
                .withType("type1").withUpc("1253761891").withManufacturer("apple")
                .withImage("image1").build();

        Product product= new Product();
        Response response=product.createProduct(productRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        ProductResponse createProdResponse= response.as(ProductResponse.class);
        product.verifyProductDetails(createProdResponse,productRequestBody);

        Response response1=product.findProduct(createProdResponse.getId());
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        ProductResponse findResponse= response1.as(ProductResponse.class);
        assertThat(findResponse.getId()).isEqualTo(createProdResponse.getId());
        product.verifyProductDetails(findResponse,productRequestBody);

    }
    @Test
    public void shouldBeAbleToFindAllProductsOfTypeBigBox(){
        Map<String,String> filter= new HashMap<>();
        filter.put("type","HardGood");
        Product product= new Product();
        Response findResponse=product.findProductByFilter(filter);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        AllProductResponse allProductResponse=findResponse.as(AllProductResponse.class);
        assertThat(allProductResponse.getTotal()).isPositive();
        Arrays.stream(allProductResponse.getData()).forEach(productResponse->
                assertThat(productResponse.getType().equals("HardGood")).isEqualTo(true));
    }

    @Test
    public void shouldBeAbleToFindAllProductsUnderCategoryOfHousewares(){
        Map<String,String> filter= new HashMap<>();
        filter.put("category.name","Housewares");
        Product product= new Product();
        Response findResponse=product.findProductByFilter(filter);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllProductResponse allProductResponse=findResponse.as(AllProductResponse.class);
        assertThat(allProductResponse.getTotal()).isPositive();
        Arrays.asList(allProductResponse.getData()).forEach(productResponse ->
                assertThat(Arrays.stream(productResponse.getCategories())
                        .anyMatch(o->o.getName().equals("Housewares"))).isEqualTo(true));
    }

    @Test
    public void shouldBeAbleToFindAllProductsUnderCategoryOfTVBetweenPrice700And800(){
        Map<String,Object> filter= new HashMap<>();
        filter.put("category.name","TVs");
        filter.put("price[$gt]",700);
        filter.put("price[$lt]",800);
        Product product= new Product();
        Response findResponse=product.findProductByFilter(filter);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllProductResponse allProductResponse=findResponse.as(AllProductResponse.class);
        assertThat(allProductResponse.getTotal()).isPositive();
        Arrays.asList(allProductResponse.getData()).forEach(productResponse ->
                assertThat(Arrays.stream(productResponse.getCategories())
                        .anyMatch(o->o.getName().equals("TVs"))).isEqualTo(true));
        Arrays.stream(allProductResponse.getData()).forEach(productResponse->
                assertThat(productResponse.getPrice()).isBetween(700.0,800.0));
    }

    @Test
    public void shouldBeAbleToUpdateProductAndSeeUpdatedDetails(){

    }

    @Test
    public void shouldBeAbleToRemoveProductAndItIsNotAvailable(){

    }
}
