package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

@Feature("Locations API")
@Story("Get Multiple Locations")
public class GetMultipleLocationsTest extends BaseTest {

    @Test
    @Description("Validate schema and that both requested location IDs are returned for GET /location/3,21")
    public void getMultipleLocations_returnsAllRequestedLocationsWithValidSchema() {
        given()
                .spec(spec)
                .when()
                .get("/location/3,21")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("getMultipleLocationsJsonResponse.json"))
                .body("size()", equalTo(2))
                .body("[0].id", equalTo(3))
                .body("[1].id", equalTo(21))
                .body("[0].name", not(emptyString()))
                .body("[1].name", not(emptyString()))
                .body("[0].dimension", not(emptyString()))
                .body("[1].dimension", not(emptyString()));
    }
}
