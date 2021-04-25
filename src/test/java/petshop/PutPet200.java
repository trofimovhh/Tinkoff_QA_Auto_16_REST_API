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

public class PutPet200 {
    @Test
    void TestPostPet200() {
        Pet myPet = new Pet();
        myPet.setId(444444);
        myPet.setName("Original");
        Category category = new Category();
        category.setName("petToBeChanged");
        category.setId(3);
        myPet.setPhotoUrls(Arrays.asList("Original's best photo #1", "Original's best photo #2"));
        myPet.setCategory(category);
        myPet.setStatus("available");
        Pet myNextPet = new Pet();
        myNextPet.setId(myPet.getId());
        myNextPet.setName("Sequel");
        Category nextCategory = new Category();
        nextCategory.setName("petThatHasBeenChanged");
        nextCategory.setId(8);
        myNextPet.setPhotoUrls(Arrays.asList("Sequel's best photo #1", "Sequel's best photo #2"));
        myNextPet.setCategory(nextCategory);
        myNextPet.setStatus("available");

        Pet restAssuredPetPost = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPet.getId())
                .then().statusCode(200).extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPetPost, restAssuredPetGet);

        Pet restAssuredPetPut = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myNextPet)
                .put(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGetAfterChange = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPet.getId())
                .then().statusCode(200).extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPetPut, restAssuredPetGetAfterChange);
        Assertions.assertNotEquals(restAssuredPetPut, restAssuredPetPost);
    }

}

