package tests.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import tests.service.RequestBuilder;

public class BaseSetUp {

    protected RequestSpecification spec;

    @BeforeClass
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        spec = RequestBuilder.buildSpec();
    }
}
