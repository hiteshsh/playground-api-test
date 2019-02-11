package apis.store;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PlayGroundProperties;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Store {

    private static final Logger logger = LogManager.getLogger(Store.class);

    public Response createStore(StoreRequestBody storeRequestBody){
        logger.info("\nStore Creation URL --- POST {}",PlayGroundProperties.createStoreUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(storeRequestBody)
                .when()
                .post(PlayGroundProperties.createStoreUrl);
        logger.info("\nStore Creation Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response findStore(int storeId) {
        String findStoreUrl= PlayGroundProperties.createStoreUrl+"/"+storeId;
        logger.info("\nStore Find URL --- GET {}",findStoreUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .get(findStoreUrl);
        logger.info("\nStore Find Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response updateStore(StoreRequestBody storeRequestBody,int storeId){
        String updateStoreUrl= PlayGroundProperties.createStoreUrl+"/"+storeId;
        logger.info("\nStore Update URL --- PATCH {}",updateStoreUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(storeRequestBody)
                .when()
                .patch(updateStoreUrl);
        logger.info("\nStore Update Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response findStoreByFilter(Map param){
        logger.info("\nStore Find URL --- GET {}",PlayGroundProperties.createStoreUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .queryParams(param)
                .when()
                .get(PlayGroundProperties.createStoreUrl);
        logger.info("\nStore Find Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response deleteStore(int storeId){
        String deleteStoreUrl= PlayGroundProperties.createStoreUrl+"/"+storeId;
        logger.info("\nStore Delete URL --- DELETE {}",deleteStoreUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteStoreUrl);
        logger.info("\nStore Delete Response --- ({}) {}",response.statusCode(),response.asString());
        return response;

    }

    public void verifyStoreDetails(StoreResponse storeResponse, StoreRequestBody storeRequestBody){
        assertThat(storeResponse.getId()).isPositive();
        assertThat(storeResponse.getName()).isEqualTo(storeRequestBody.getName());
        assertThat(storeResponse.getAddress()).isEqualTo(storeRequestBody.getAddress());
        assertThat(storeResponse.getAddress2()).isEqualTo(storeRequestBody.getAddress2());
        assertThat(storeResponse.getCity()).isEqualTo(storeRequestBody.getCity());
        assertThat(storeResponse.getState()).isEqualTo(storeRequestBody.getState());
        assertThat(storeResponse.getZip()).isEqualTo(storeRequestBody.getZip());
    }
}
