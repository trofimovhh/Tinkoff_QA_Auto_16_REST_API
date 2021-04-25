package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Category;
import models.pet.Pet;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PutPet404 {
    @Test
    void TestPutPet404() {
        Pet myPut404Pet = new Pet();
        myPut404Pet.setId(55555);
        myPut404Pet.setName("Sequel");
        Category nextCategory = new Category();
        nextCategory.setName("petThatHasBeenChanged");
        nextCategory.setId(8);
        myPut404Pet.setPhotoUrls(Arrays.asList("Sequel's best photo #1", "Sequel's best photo #2"));
        myPut404Pet.setCategory(nextCategory);
        myPut404Pet.setStatus("available");


        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(EndPoints.petId, myPut404Pet.getId())
                .then()
                .statusCode(404)
                .body(Matchers.emptyOrNullString());

        Pet restAssuredPetPut = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPut404Pet)
                .put(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPut404Pet.getId())
                .then().statusCode(200).extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPetPut, restAssuredPetGet);
    }
}

