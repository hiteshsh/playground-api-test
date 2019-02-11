package apis.store;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AllStoreResponse {
    private int total;
    private int limit;
    private int skip;
    private StoreResponse[] data;

}
