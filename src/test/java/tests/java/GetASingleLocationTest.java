package tests.java;

import org.testng.annotations.Test;
import tests.service.RequestBuilder;
import static io.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetASingleLocationTest extends RequestBuilder {
    @Test
    public void getASingleLocation() {
        given()
                .spec(spec)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .get("/location/3")
                .then().body(matchesJsonSchemaInClasspath("getSingleLocationJsonResponse.json"));
    }
}