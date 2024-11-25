package pl.akademiaqa.tests.board.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import pl.akademiaqa.requests.board.CreateBoardRequest;
import pl.akademiaqa.requests.board.DeleteBoardRequest;
import pl.akademiaqa.requests.card.CreateCardRequest;
import pl.akademiaqa.requests.card.UpdateCardRequest;
import pl.akademiaqa.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)                           // testy w klasie wykonają sie z określoną kolejnością
class MoveCardBetweenListsTest {

    private final String boardName = "Tablica do testu e2e";
    private final String firstListName = "First List";
    private final String secondListName = "Second List";
    private final String cardName = "My first Card";
    private static String firstListId;                                          // jeżeli pole ma być przekazywane między testami musi być static
    private static String secondListId;                                         // jeżeli pole ma być przekazywane między testami musi być static
    private static String boardId;                                              // jeżeli pole ma być przekazywane między testami musi być static
    private static String cardId;

    @Test
    @Order(1)
    void createNewBoardTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        final Response createBoardResponse = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(createBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        boardId = json.getString("id");
    }

    @Test
    @Order(2)
    void createFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idBoard", boardId);
        queryParams.put("name", firstListName);

        final Response createFirstListResponse = CreateListRequest.createListRequest(queryParams);

        Assertions.assertThat(createFirstListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createFirstListResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(firstListName);
        firstListId = json.getString("id");
    }

    @Test
    @Order(3)
    void createSecondListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idBoard", boardId);
        queryParams.put("name", secondListName);

        final Response createSecondListResponse = CreateListRequest.createListRequest(queryParams);

        Assertions.assertThat(createSecondListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createSecondListResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(secondListName);
        secondListId = json.getString("id");
    }

    @Test
    @Order(4)
    void createCardOnFirstListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", firstListId);
        queryParams.put("name", cardName);

        final Response createCardOnFirstResponse = CreateCardRequest.createCardRequest(queryParams);

        Assertions.assertThat(createCardOnFirstResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createCardOnFirstResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(cardName);
        cardId = json.getString("id");
    }

    @Test
    @Order(5)
    void moveCardToSecondListTest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("idList", secondListId);

        final Response moveCardToSecondListResponse = UpdateCardRequest.updateCardRequest(queryParams, cardId);

        Assertions.assertThat(moveCardToSecondListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = moveCardToSecondListResponse.jsonPath();
        Assertions.assertThat(json.getString("idList")).isEqualTo(secondListId);
    }

    @Test
    @Order(6)
    void deleteBoardTest() {
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}


// JUnit wykonuje testy w randomowej kolejności (testy mają być niezależne od siebie)
// można np. utworzyć jeden test i w nim zamiast poszczególnych testów wykonywać metody (usunąć im adnoację @Test)
//    @Test
//    void scenarioE2ETest() {
//        createNewBoardTest();
//        createFirstListTest();
//        createSecondListTest();
//    }



