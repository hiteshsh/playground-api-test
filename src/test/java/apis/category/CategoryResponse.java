package apis.category;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CategoryResponse {

    private String id;
    private String name;
    private String updatedAt;
    private String createdAt;
    private CategoryResponse[] subCategories;
    private CategoryResponse[] categoryPath;
}
