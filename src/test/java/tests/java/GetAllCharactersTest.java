package tests.java;

import org.testng.annotations.Test;
import tests.service.RequestBuilder;
import static io.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAllCharactersTest extends RequestBuilder {
    @Test
    public void getAllCharacters() {
        given()
                .spec(spec)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get("/character")
                .then().body(matchesJsonSchemaInClasspath("getAllCharactersJsonResponse.json"));
    }
}