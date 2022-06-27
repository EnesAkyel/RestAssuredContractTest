package tests.service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String SERVICE_URL = "https://rickandmortyapi.com/api";

    public final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(SERVICE_URL)
            .setBasePath("/")
            .build();
}