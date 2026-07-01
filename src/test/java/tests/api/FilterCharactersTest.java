package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

@Feature("Characters API")
@Story("Filter Characters")
public class FilterCharactersTest extends BaseTest {

    @DataProvider(name = "characterFilters")
    public Object[][] characterFilters() {
        return new Object[][]{
                {"rick",  "alive"},
                {"morty", "alive"},
                {"beth",  "alive"},
        };
    }

    @Test(dataProvider = "characterFilters")
    @Description("Validate schema and that filter results match the given name and status query params")
    public void filterCharacters_byNameAndStatus_matchesSchemaAndResults(String name, String status) {
        given()
                .spec(spec)
                .queryParam("name", name)
                .queryParam("status", status)
                .when()
                .get("/character")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(matchesJsonSchemaInClasspath("filterCharactersJsonResponse.json"))
                .body("results", not(empty()))
                .body("results.name", everyItem(containsStringIgnoringCase(name)))
                .body("results.status", everyItem(equalToIgnoringCase(status)));
    }
}
