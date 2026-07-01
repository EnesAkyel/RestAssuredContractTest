package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Characters API")
@Story("Get All Characters")
public class GetAllCharactersTest extends BaseTest {

    @Test
    @Description("Validate schema, pagination metadata, and first result fields for GET /character")
    public void getAllCharacters_returnsValidSchemaAndFields() {
        given()
                .spec(spec)
                .when()
                .get("/character")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("getAllCharactersJsonResponse.json"))
                .body("info.count", greaterThan(0))
                .body("info.pages", greaterThan(0))
                .body("info.next", notNullValue())
                .body("results", not(empty()))
                .body("results[0].id", notNullValue())
                .body("results[0].name", not(emptyString()))
                .body("results[0].status", not(emptyString()))
                .body("results[0].species", not(emptyString()));
    }
}
