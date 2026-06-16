package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

@Feature("Episodes API")
public class GetMultipleEpisodesTest extends BaseTest {

    @Test
    @Description("Validate schema and that both requested episode IDs are returned for GET /episode/10,28")
    public void getMultipleEpisodes_returnsAllRequestedEpisodesWithValidSchema() {
        given()
                .spec(spec)
                .when()
                .get("/episode/10,28")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("getMultipleEpisodesJsonResponse.json"))
                .body("size()", equalTo(2))
                .body("[0].id", equalTo(10))
                .body("[1].id", equalTo(28))
                .body("[0].name", not(emptyString()))
                .body("[1].name", not(emptyString()))
                .body("[0].air_date", not(emptyString()))
                .body("[1].air_date", not(emptyString()));
    }
}
