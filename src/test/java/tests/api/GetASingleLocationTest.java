package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

@Feature("Locations API")
public class GetASingleLocationTest extends BaseTest {

    @DataProvider(name = "locationData")
    public Object[][] locationData() {
        return new Object[][]{
                {1,  "Earth (C-137)"},
                {3,  "Citadel of Ricks"},
                {20, "Earth (Replacement Dimension)"},
        };
    }

    @Test(dataProvider = "locationData")
    @Description("Validate schema, ID, name, and residents list for GET /location/{id}")
    public void getSingleLocation_returnsValidSchemaAndMatchesExpectedData(int locationId, String expectedName) {
        given()
                .spec(spec)
                .when()
                .get("/location/{id}", locationId)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("getSingleLocationJsonResponse.json"))
                .body("id", equalTo(locationId))
                .body("name", equalTo(expectedName))
                .body("residents", not(empty()));
    }
}
