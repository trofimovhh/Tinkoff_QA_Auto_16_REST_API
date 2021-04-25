package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PostPet200 {

    @Test
    void TestPostPet200() {
        Pet myPet = new Pet();
        myPet.setId(333333);
        myPet.setName("Tractor");
        Category category = new Category();
        category.setName("MyPets");
        category.setId(1);
        myPet.setPhotoUrls(Arrays.asList("Tractor's best photo #1", "Tractor's best photo #2"));
        myPet.setCategory(category);
        myPet.setStatus("available");

        Pet restAssuredPetPost = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet).post(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPet.getId())
                .then().statusCode(200).extract().as(Pet.class);


        Assertions.assertEquals(restAssuredPetPost, restAssuredPetGet);
    }
}
