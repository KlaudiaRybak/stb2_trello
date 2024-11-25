package pl.akademiaqa.requests.card;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateCardRequest {

    public static Response createCardRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getCardsUrl())
                .then()
                .extract()
                .response();
    }

}
