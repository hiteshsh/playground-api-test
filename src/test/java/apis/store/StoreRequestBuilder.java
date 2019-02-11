package apis.store;

public class StoreRequestBuilder {

    private StoreRequestBody storeRequestBody;

    public StoreRequestBuilder() {
        this.storeRequestBody = new StoreRequestBody();
    }

    public StoreRequestBody build(){
        return storeRequestBody;
    }
    public StoreRequestBuilder withName(String name){
        storeRequestBody.setName(name);
        return this;
    }
    public StoreRequestBuilder withType(String type){
        storeRequestBody.setType(type);
        return this;
    }
    public StoreRequestBuilder withAddress(String address){
        storeRequestBody.setAddress(address);
        return this;
    }
    public StoreRequestBuilder withAddress2(String address2){
        storeRequestBody.setAddress2(address2);
        return this;
    }
    public StoreRequestBuilder withCity(String city){
        storeRequestBody.setCity(city);
        return this;
    }
    public StoreRequestBuilder withState(String state){
        storeRequestBody.setState(state);
        return this;
    }
    public StoreRequestBuilder withZip(String zip){
        storeRequestBody.setZip(zip);
        return this;
    }

    public StoreRequestBuilder withlat(float lat){
        storeRequestBody.setLat(lat);
        return this;
    }

    public StoreRequestBuilder withlng(float lng){
        storeRequestBody.setLng(lng);
        return this;
    }
//    public StoreRequestBuilder withHours(String hours){
//        storeRequestBody.setHours(hours);
//        return this;
//    }
}
