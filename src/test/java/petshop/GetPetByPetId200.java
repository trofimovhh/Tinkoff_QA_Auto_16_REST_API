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

public class GetPetByPetId200 {
    @Test
    void TestGetPetByPetId200() {
        Pet myPet = new Pet();
        myPet.setId(11111);
        myPet.setName("Fluffy");
        Category category = new Category();
        category.setName("MyPets");
        category.setId(1);
        myPet.setPhotoUrls(Arrays.asList("Fluffy's best photo #1", "Fluffy's best photo #2"));
        myPet.setCategory(category);
        myPet.setStatus("available");

        Pet restAssuredPetPost = RestAssured.given()
                .contentType(ContentType.JSON)
//                .filter(new RequestLoggingFilter())
//                .filter(new ResponseLoggingFilter())
                .body(myPet).post(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
//                .filter(new RequestLoggingFilter())
//                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPet.getId())
                .then().statusCode(200).extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPetPost, restAssuredPetGet);
    }
}

