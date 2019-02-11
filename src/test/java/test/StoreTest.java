package test;

import apis.store.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreTest extends BaseTest{

    @Test
    public void shouldBeAbleToCreateAndFindStore(){

        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withState("state1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        StoreResponse storeResponse=storeCreateResponse.as(StoreResponse.class);
        store.verifyStoreDetails(storeResponse,storeRequestBody);

        Response response= store.findStore(storeResponse.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        StoreResponse findresponse=response.as(StoreResponse.class);
        assertThat(findresponse.getId()).isEqualTo(storeResponse.getId());
        store.verifyStoreDetails(findresponse,storeRequestBody);

    }

    @Test
    public void storeCreateShouldReturnBadRequestIfFieldNameMissing(){
        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withState("state1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void storeCreateShouldReturnBadRequestIfFieldAddressMissing(){
        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress2("test2")
                .withCity("city1").withState("state1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void storeCreateShouldReturnBadRequestIfFieldCityMissing(){
        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withState("state1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void storeCreateShouldReturnBadRequestIfFieldStateMissing(){
        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void storeCreateShouldReturnBadRequestIfFieldZipMissing(){
        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withState("state1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void storeFindShouldReturnNotFoundWithInvalidStoreId(){
        Store store= new Store();
        Response response= store.findStore(120156);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void shouldBeAbleToUpdateStoresAddressAndSeeUpdatedDetails(){

        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withState("state1").withZip("zip1").build();
        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        StoreResponse storeResponse=storeCreateResponse.as(StoreResponse.class);

        StoreRequestBody updateStoreRequestBody= new StoreRequestBuilder()
                .withAddress("testNew").withAddress2("testNew2")
                .withCity("cityNew").withState("stateNew").withZip("zipNew").build();

        Response updateResponse=store.updateStore(updateStoreRequestBody,storeResponse.getId());
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        StoreRequestBody expectedStoreRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("testNew").withAddress2("testNew2")
                .withCity("cityNew").withState("stateNew").withZip("zipNew").build();

        Response findResponse= store.findStore(storeResponse.getId());
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        StoreResponse findresponse=findResponse.as(StoreResponse.class);
        assertThat(findresponse.getId()).isEqualTo(storeResponse.getId());
        store.verifyStoreDetails(findresponse,expectedStoreRequestBody);
    }

    @Test
    public void shouldbeAbleToRemoveStoreAndItShouldNotBeAvailable(){

        StoreRequestBody storeRequestBody= new StoreRequestBuilder()
                .withName("store1").withType("type1").withAddress("test1").withAddress2("test2")
                .withCity("city1").withState("state1").withZip("zip1").build();

        Store store= new Store();
        Response storeCreateResponse=store.createStore(storeRequestBody);
        assertThat(storeCreateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        StoreResponse storeResponse=storeCreateResponse.as(StoreResponse.class);

        Response storeDeleteResponse=store.deleteStore(storeResponse.getId());
        assertThat(storeDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        Response response= store.findStore(storeResponse.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void findAllStoresOfferingBestBuyMobileService(){
        Map<String,String> filter= new HashMap<>();
        filter.put("service.name","Best Buy Mobile");
        Store store= new Store();
        Response findResponse=store.findStoreByFilter(filter);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllStoreResponse allStoreResponse=findResponse.as(AllStoreResponse.class);
        assertThat(allStoreResponse.getTotal()).isPositive();
        Arrays.asList(allStoreResponse.getData()).forEach(storeResponse ->
                assertThat(Arrays.stream(storeResponse.getServices())
                        .anyMatch(o->o.getName().equals("Best Buy Mobile"))).isEqualTo(true));
    }

    @Test
    public void findAllStoresWithLimitAndSkip(){
        Map<String,String> filter= new HashMap<>();
        filter.put("$limit","15");
        filter.put("$skip","10");
        Store store= new Store();
        Response findResponse=store.findStoreByFilter(filter);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        AllStoreResponse allStoreResponse=findResponse.as(AllStoreResponse.class);
        assertThat(allStoreResponse.getTotal()).isPositive();
        assertThat(allStoreResponse.getData().length).isEqualTo(15);
        assertThat(allStoreResponse.getSkip()).isEqualTo(10);
    }
}
