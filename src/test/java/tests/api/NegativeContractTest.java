package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

@Feature("Negative Tests")
public class NegativeContractTest extends BaseTest {

    @Test
    @Description("Verify 404 with error body for a non-existent character ID")
    public void getCharacter_withInvalidId_returns404() {
        given()
                .spec(spec)
                .when()
                .get("/character/99999")
                .then()
                .statusCode(404)
                .time(lessThan(3000L))
                .body("error", equalTo("Character not found"));
    }

    @Test
    @Description("Verify 404 with error body for a non-existent location ID")
    public void getLocation_withInvalidId_returns404() {
        given()
                .spec(spec)
                .when()
                .get("/location/99999")
                .then()
                .statusCode(404)
                .time(lessThan(3000L))
                .body("error", equalTo("Location not found"));
    }

    @Test
    @Description("Verify 404 with error body for a non-existent episode ID")
    public void getEpisode_withInvalidId_returns404() {
        given()
                .spec(spec)
                .when()
                .get("/episode/99999")
                .then()
                .statusCode(404)
                .time(lessThan(3000L))
                .body("error", equalTo("Episode not found"));
    }

    @Test
    @Description("Verify 404 when a character name filter returns no results")
    public void filterCharacters_withNoMatchingName_returns404WithErrorMessage() {
        given()
                .spec(spec)
                .queryParam("name", "XXXXXNONEXISTENTCHARACTERXXXXX")
                .when()
                .get("/character")
                .then()
                .statusCode(404)
                .time(lessThan(3000L))
                .body("error", equalTo("There is nothing here"));
    }
}
