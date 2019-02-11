package apis.store;

import apis.service.ServiceResponse;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class StoreResponse {
    private int id;
    private String name;
    private String type;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private float lat;
    private float lng;
    private String hours;
    private String updatedAt;
    private String createdAt;
    private AllStoreServiceResponse[] services;

    @Getter@Setter
    public static class AllStoreServiceResponse{

        private int id;
        private String name;
        private String updatedAt;
        private String createdAt;
        private StoreServices storeservices;

        @Getter@Setter
        public static class StoreServices{
            private String updatedAt;
            private String createdAt;
            private int storeId;
            private int serviceId;
        }
    }
}
