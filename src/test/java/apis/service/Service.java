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
    
}
