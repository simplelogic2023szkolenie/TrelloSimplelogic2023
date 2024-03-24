package base;

import configuration.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public final static String baseUrl = Config.getBaseUrl();
    public final static String boards = "/boards";
    public final static String lists = "/lists";
    public final static String cards = "/cards";
    public final static String actions = "/actions";
    public RequestSpecification commonReqSpec;

    @BeforeEach
    public void setup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.addQueryParam("key", Config.getApiKey());
        reqSpecBuilder.addQueryParam("token", Config.getToken());
        reqSpecBuilder.setBaseUri(Config.getBaseUrl());
        reqSpecBuilder.setContentType(ContentType.JSON);
        commonReqSpec = reqSpecBuilder.build();
    }
}
