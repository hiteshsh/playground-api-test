package apis.service;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ServiceResponse {
    private int id;
    private String name;
    private String updatedAt;
    private String createdAt;
}
