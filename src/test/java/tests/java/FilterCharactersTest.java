package tests.java;

import org.testng.annotations.Test;
import tests.service.RequestBuilder;
import static io.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FilterCharactersTest extends RequestBuilder {
    @Test
    public void filterCharacters() {
        given()
                .spec(spec)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get("/character/?name=rick&status=alive")
                .then().body(matchesJsonSchemaInClasspath("filterCharactersJsonResponse.json"));
    }
}