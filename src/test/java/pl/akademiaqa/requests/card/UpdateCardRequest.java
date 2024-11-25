package pl.akademiaqa.requests.card;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {

    public static Response updateCardRequest(Map<String, String> queryParams, String cardId) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();
    }
}
