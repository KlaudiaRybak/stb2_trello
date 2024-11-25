package pl.akademiaqa.requests.list;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createListRequest(Map<String, String> queryParams) {

        return given()
                .spec(BaseRequest.requestSetup())
                .queryParams(queryParams)
                .when()
                .post(TrelloUrl.getListsUrl())
                .then()
                .extract()
                .response();
    }
}
