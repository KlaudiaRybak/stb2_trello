package pl.akademiaqa.requests.board;

import io.restassured.response.Response;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {

    public static Response deleteBoardRequest(String boardId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId)) // bez stosowania osobno .pathParams("boardId", boardId)
                .then()
                .extract()
                .response();
    }

}
