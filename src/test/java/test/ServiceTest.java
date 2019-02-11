package test;

import apis.service.Service;
import apis.service.ServiceRequestBody;
import apis.service.ServiceRequestBuilder;
import apis.service.ServiceResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTest extends BaseTest{

    @Test
    public void canCreateNewService(){

        ServiceRequestBody serviceRequestBody= new ServiceRequestBuilder()
                .withName("service1").build();

        Service service= new Service();
        Response response=service.createService(serviceRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        ServiceResponse serviceResponse=response.as(ServiceResponse.class);
        assertThat(serviceResponse.getId()).isPositive();
        assertThat(serviceResponse.getName()).isEqualTo("service1");
    }

    @Test
    public void serviceCreationShouldThrowBadRequestIfFieldServiceNameMissing(){
        ServiceRequestBody serviceRequestBody= new ServiceRequestBuilder().build();
        Service service= new Service();
        Response response=service.createService(serviceRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void canUpdateNameOfAService(){

    }

    @Test
    public void canRemoveAService(){

    }
}
