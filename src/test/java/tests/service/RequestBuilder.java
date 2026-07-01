package tests.service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    private static final String BASE_URI = "https://rickandmortyapi.com/api";

    private RequestBuilder() {}

    public static RequestSpecification buildSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }
}
