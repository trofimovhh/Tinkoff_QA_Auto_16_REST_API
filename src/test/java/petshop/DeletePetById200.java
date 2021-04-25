package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Category;
import models.pet.Pet;
import models.pet.PetNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DeletePetById200 {
    @Test
    void TestDeletePetById200() {
        Pet myDeletePetById200Pet = new Pet();
        myDeletePetById200Pet.setId(666);
        myDeletePetById200Pet.setName("Lucky");
        Category nextCategory = new Category();
        nextCategory.setName("Pet_to_be_deleted");
        nextCategory.setId(6);
        myDeletePetById200Pet.setPhotoUrls(Arrays.asList("Lucky is pooping under the three",
                "Lucky is peeing into the swimming pool"));
        myDeletePetById200Pet.setCategory(nextCategory);
        myDeletePetById200Pet.setStatus("available");
        PetNotFound petNotFound = new PetNotFound();
        petNotFound.setCode(1);
        petNotFound.setType("error");
        petNotFound.setMessage("Pet not found");

        Pet restAssuredPetPost = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myDeletePetById200Pet).post(EndPoints.baseUri)
                .then().statusCode(200).extract().as(Pet.class);

        Pet restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myDeletePetById200Pet.getId())
                .then().statusCode(200).extract().as(Pet.class);

        Assertions.assertEquals(restAssuredPetPost, restAssuredPetGet);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(EndPoints.petId, myDeletePetById200Pet.getId())
                .then().statusCode(200);

        PetNotFound restAssuredPetGetAfterDelete = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myDeletePetById200Pet.getId())
                .then().statusCode(404).extract().as(PetNotFound.class);

        Assertions.assertEquals(petNotFound, restAssuredPetGetAfterDelete);
    }
}

