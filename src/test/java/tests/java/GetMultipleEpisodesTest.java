package tests.java;

import org.testng.annotations.Test;
import tests.service.RequestBuilder;
import static io.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetMultipleEpisodesTest extends RequestBuilder {
    @Test
    public void getMultipleEpisodes() {
        given()
                .spec(spec)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get("/episode/10,28")
                .then().body(matchesJsonSchemaInClasspath("getMultipleEpisodesJsonResponse.json"));
    }
}