package e2e;

import base.TestBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloE2eTest extends TestBase {

    private static String boardId;
    private static String doneId;
    private static String randomBoardName;

    @Test
    @Order(1)
    public void createNewBoard() {
        randomBoardName = new Faker().name().username();

        Response response =
                given().
                            spec(commonReqSpec).
                            queryParam("name", randomBoardName).
                            queryParam("defaultLists", false).
                        when().
                            post(boards).
                        then().
                            statusCode(200).extract().response();

        boardId = response.jsonPath().getString("id");
    }


    @Test
    @Order(2)
    public void shouldCreateDoneList() {
        Response response =
                given().
                            spec(commonReqSpec).
                            queryParam("name", "DONE").
                            queryParam("idBoard", boardId).
                        when().
                            post(lists).
                        then().
                            statusCode(200).extract().response();

        doneId = response.jsonPath().getString("id");
    }


    @Test
    @Order(9)
    public void shouldDeleteBoard() {
        given().
                    spec(commonReqSpec).
                   pathParams("id", boardId).
                when().
                    delete(boards + "/{id}").
                then().
                    statusCode(200);
    }


}
