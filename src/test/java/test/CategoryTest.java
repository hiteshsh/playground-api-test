package test;

import apis.category.Category;
import apis.category.CategoryRequestBody;
import apis.category.CategoryRequestBuilder;
import apis.category.CategoryResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static util.PlayGroundUtil.getRandomId;

public class CategoryTest extends BaseTest{

    @Test
    public void shouldBeAbleToCreateAndFindTheSameCategory(){
        String categoryId= getRandomId();
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withId(categoryId).withName("test Category").build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        CategoryResponse categoryResponse=response.as(CategoryResponse.class);
        assertThat(categoryResponse.getId()).isEqualTo(categoryId);
        assertThat(categoryResponse.getName()).isEqualTo("test Category");

        Response response1=category.findCategory(categoryId);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CategoryResponse categoryResponse1=response.as(CategoryResponse.class);
        assertThat(categoryResponse1.getId()).isEqualTo(categoryId);
        assertThat(categoryResponse1.getName()).isEqualTo("test Category");

    }
    @Test
    public void shouldNotBeAbleToCreateCategoryAgainWithExistingId(){
        String categoryId= getRandomId();
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withId(categoryId).withName("test Category").build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        Response response1=category.createCategory(categoryRequestBody);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void categoryCreateShouldThrowBadRequestIfFieldCategoryIdMissing() {
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withName("category2").build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void categoryCreateShouldThrowBadRequestIfFieldCategoryNameMissing() {
        String categoryId= getRandomId();
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withId(categoryId).build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void shouldBeAbleToUpdateCategoryNameForExistingCategory(){
        String categoryId= getRandomId();
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withId(categoryId).withName("test Category").build();

        CategoryRequestBody updateCategoryRequestBody= new CategoryRequestBuilder()
                .withName("test Category New").build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        Response UpdateResponse=category.updateCategory(updateCategoryRequestBody,categoryId);
        assertThat(UpdateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        Response findResponse=category.findCategory(categoryId);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        CategoryResponse findResponse1=findResponse.as(CategoryResponse.class);
        assertThat(findResponse1.getName()).isEqualTo("test Category New");
    }

    @Test
    public void shouldBeAbleToRemoveACategoryAndItShouldNotBeAvailable(){
        String categoryId= getRandomId();
        CategoryRequestBody categoryRequestBody= new CategoryRequestBuilder()
                .withId(categoryId).withName("test Category").build();

        Category category= new Category();
        Response response=category.createCategory(categoryRequestBody);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);

        Response removeResponse=category.removeCategory(categoryId);
        assertThat(removeResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        Response findResponse=category.findCategory(categoryId);
        assertThat(findResponse.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }

}
