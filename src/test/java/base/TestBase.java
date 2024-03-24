package base;

import configuration.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestBase {
    public final static String baseUrl = Config.getBaseUrl();
    public final static String boards = "/boards";
    public final static String lists = "/lists";
    public final static String cards = "/cards";
    public final static String actions = "/actions";
    public static RequestSpecification commonReqSpec;

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

    // PONIŻSZY KOD USUWA WSZYSTKI BOARDY W ORGANIZACJI
//    @AfterAll
//    public static void testsCleanup() {
//        Response response =
//                given()
//                        .spec(commonReqSpec)
//                        .when()
//                        .pathParam("id", Config.getOrganisationId())
//                        .get(baseUrl + "/organizations/{id}/boards")
//                        .then()
//                        .statusCode(200)
//                        .extract().response();
//
//        JsonPath jsonPath = response.jsonPath();
//        List<String> allBoardIds = jsonPath.getList("id");
//
//        for (String id : allBoardIds) {
//            given()
//                    .spec(commonReqSpec)
//                    .pathParam("id", id)
//                    .when()
//                    .delete(baseUrl + boards + "/{id}")
//                    .then()
//                    .statusCode(200);
//        }
//    }
}
