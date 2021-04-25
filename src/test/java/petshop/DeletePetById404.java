package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Pet;
import models.pet.PetNotFound;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePetById404 {
    @Test
    void TestDeletePetById404() {
        Pet myDeletePetById404Pet = new Pet();
        myDeletePetById404Pet.setId(777);
        PetNotFound petNotFound = new PetNotFound();
        petNotFound.setCode(1);
        petNotFound.setType("error");
        petNotFound.setMessage("Pet not found");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(EndPoints.petId, myDeletePetById404Pet.getId())
                .then()
                .statusCode(404)
                .body(Matchers.emptyOrNullString());

        PetNotFound restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myDeletePetById404Pet.getId())
                .then().statusCode(404).extract().as(PetNotFound.class);

        Assertions.assertEquals(petNotFound, restAssuredPetGet);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(EndPoints.petId, myDeletePetById404Pet.getId())
                .then()
                .body(Matchers.emptyOrNullString());

    }
}

