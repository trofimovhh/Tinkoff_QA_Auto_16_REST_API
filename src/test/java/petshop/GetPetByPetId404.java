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

import java.util.Arrays;


public class GetPetByPetId404 {
    @Test
    void TestGetPetByPetId404() {
        Pet myPet = new Pet();
        myPet.setId(22222);
        PetNotFound petNotFound = new PetNotFound();
        petNotFound.setCode(1);
        petNotFound.setType("error");
        petNotFound.setMessage("Pet not found");


        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(EndPoints.petId, myPet.getId())
                .then().statusCode(404).body(Matchers.emptyOrNullString());


        PetNotFound restAssuredPetGet = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(EndPoints.petId, myPet.getId())
                .then().statusCode(404).extract().as(PetNotFound.class);

        Assertions.assertEquals(petNotFound, restAssuredPetGet);
    }
}
