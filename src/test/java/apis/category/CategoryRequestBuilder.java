package apis.category;

public class CategoryRequestBuilder {

    private CategoryRequestBody categoryRequestBody;

    public CategoryRequestBuilder() {
        this.categoryRequestBody= new CategoryRequestBody();
    }

    public CategoryRequestBuilder withId(String id){
        categoryRequestBody.setId(id);
        return this;
    }

    public CategoryRequestBuilder withName(String name){
        categoryRequestBody.setName(name);
        return this;
    }
    public CategoryRequestBody build(){
        return categoryRequestBody;
    }
}
