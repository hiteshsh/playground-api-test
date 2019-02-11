package apis.service;

public class ServiceRequestBuilder {

    private ServiceRequestBody serviceRequestBody;

    public ServiceRequestBuilder() {
        this.serviceRequestBody = new ServiceRequestBody();
    }

    public ServiceRequestBody build(){
        return serviceRequestBody;
    }
    public ServiceRequestBuilder withName(String name){
        serviceRequestBody.setName(name);
        return this;
    }
}
