package apis.product;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PlayGroundProperties;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Product {

    private static final Logger logger = LogManager.getLogger(Product.class);

    public Response createProduct(ProductRequestBody productRequestBody){
        logger.info("\nProduct Creation URL --- POST {}", PlayGroundProperties.createProductUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(productRequestBody)
                .when()
                .post(PlayGroundProperties.createProductUrl);
        logger.info("\nProduct Creation Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response updateProduct(ProductRequestBody productRequestBody,int productId){
        String updateProductUrl= PlayGroundProperties.createProductUrl +"/"+productId;
        logger.info("\nProduct Update URL --- POST {}", updateProductUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(productRequestBody)
                .when()
                .patch(updateProductUrl);
        logger.info("\nProduct Update Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response findProduct(int productId) {
        String findProductUrl= PlayGroundProperties.createProductUrl+"/"+productId;
        logger.info("\nProduct Find URL --- GET {}",findProductUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .get(findProductUrl);
        logger.info("\nProduct Find Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response findProductByFilter(Map param){
        logger.info("\nProduct Find URL --- GET {}",PlayGroundProperties.createProductUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .queryParams(param)
                .when()
                .get(PlayGroundProperties.createProductUrl);
        logger.info("\nProduct Find Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response removeProduct(int productId) {
        String removeProductUrl= PlayGroundProperties.createProductUrl+"/"+productId;
        logger.info("\nProduct Remove URL --- DELETE {}",removeProductUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .delete(removeProductUrl);
        logger.info("\nProduct Remove Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public void verifyProductDetails(ProductResponse productResponse,ProductRequestBody productRequestBody){
        assertThat(productResponse.getId()).isPositive();
        assertThat(productResponse.getName()).isEqualTo(productRequestBody.getName());
        assertThat(productResponse.getType()).isEqualTo(productRequestBody.getType());
        assertThat(productResponse.getDescription()).isEqualTo(productRequestBody.getDescription());
        assertThat(productResponse.getModel()).isEqualTo(productRequestBody.getModel());
        assertThat(productResponse.getManufacturer()).isEqualTo(productRequestBody.getManufacturer());
        assertThat(productResponse.getPrice()).isEqualTo(productRequestBody.getPrice());
        assertThat(productResponse.getUpc()).isEqualTo(productRequestBody.getUpc());
        assertThat(productResponse.getImage()).isEqualTo(productRequestBody.getImage());
        assertThat(productResponse.getUrl()).isEqualTo(productRequestBody.getUrl());
    }
}
