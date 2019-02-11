package apis.category;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PlayGroundProperties;

import static io.restassured.RestAssured.given;

public class Category {

    private static final Logger logger = LogManager.getLogger(Category.class);

    public Response createCategory(CategoryRequestBody categoryRequestBody){
        logger.info("\nCategory Create URL --- POST {}", PlayGroundProperties.createCategoryUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(categoryRequestBody)
                .when()
                .post(PlayGroundProperties.createCategoryUrl);
        logger.info("\nCategory Create Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response findCategory(String categoryId) {
        String findCategoryUrl= PlayGroundProperties.createCategoryUrl+"/"+categoryId;
        logger.info("\nCategory Find URL --- GET {}",findCategoryUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .get(findCategoryUrl);
        logger.info("\nCategory Find Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response updateCategory(CategoryRequestBody categoryRequestBody,String categoryId){
        String updateCategoryUrl= PlayGroundProperties.createCategoryUrl+"/"+categoryId;
        logger.info("\nCategory Update URL --- PATCH {}", updateCategoryUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(categoryRequestBody)
                .when()
                .patch(updateCategoryUrl);
        logger.info("\nCategory Update Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response removeCategory(String categoryId){
        String deleteCategoryUrl= PlayGroundProperties.createCategoryUrl+"/"+categoryId;
        logger.info("\nCategory Remove URL --- DELETE {}", deleteCategoryUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteCategoryUrl);
        logger.info("\nCategory Remove Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }
}
