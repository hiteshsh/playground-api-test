package apis.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PlayGroundProperties;

import static io.restassured.RestAssured.given;

public class Service {
    private static final Logger logger = LogManager.getLogger(Service.class);

    public Response createService(ServiceRequestBody serviceRequestBody){
        logger.info("\nService Creation URL --- POST {}", PlayGroundProperties.createServiceUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(serviceRequestBody)
                .when()
                .post(PlayGroundProperties.createServiceUrl);
        logger.info("\nService Creation Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }


    public Response updateService(ServiceRequestBody serviceRequestBody,int serviceId){
        String updateServiceUrl= PlayGroundProperties.createServiceUrl+"/"+serviceId;
        logger.info("\nService Update URL --- PATCH {}",updateServiceUrl);
        Response response=given()
                .contentType(ContentType.JSON)
                .body(serviceRequestBody)
                .when()
                .patch(updateServiceUrl);
        logger.info("\nService Update Response --- ({}) {}",response.statusCode(),response.asString());
        return response;
    }

    public Response removeService(int serviceId) {
        String deleteServiceUrl = PlayGroundProperties.createServiceUrl + "/" + serviceId;
        logger.info("\nService Delete URL --- DELETE {}", deleteServiceUrl);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteServiceUrl);
        logger.info("\nService Delete Response --- ({}) {}", response.statusCode(), response.asString());
        return response;
    }

    public Response findService(int serviceId) {
        String findServiceUrl = PlayGroundProperties.createServiceUrl + "/" + serviceId;
        logger.info("\nService Find URL --- GET {}", findServiceUrl);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(findServiceUrl);
        logger.info("\nService Find Response --- ({}) {}", response.statusCode(), response.asString());
        return response;
    }


    
}
