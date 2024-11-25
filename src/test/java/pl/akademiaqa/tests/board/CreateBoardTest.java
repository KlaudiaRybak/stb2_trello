package pl.akademiaqa.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.board.CreateBoardRequest;
import pl.akademiaqa.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTest {

    private String boardId;

    @DisplayName("Create a board with valid data")                  // JUnit Jupiter Params - opis testu
    @ParameterizedTest(name = "Board name: {0}")                    // JUnit Jupiter Params - test z parametrami - przekazywanie parametrów do testu
    @MethodSource("sampleBoardNameData")                            // JUnit Jupiter Params - podanie źródła danych przekazywanych do testu (nazwa metody)
    void createBoardTest(String boardName) {                        // przekazanie danych do testu

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        // CREATE A BOARD
        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);            // <- assertJ || JUnit -> Assertions.assertEquals(HttpStatus.SC_OK,
        // response.getStatusCode());
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);                // <- assertJ || JUnit -> Assertions.assertEquals(boardName, json.getString("name"));
        boardId = json.getString("id");                                                    // przekazanie utworzonej wartości do zmiennej boardId

        //DELETE A BOARD
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);

        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);       // <- assertJ || JUnit -> Assertions.assertEquals(HttpStatus.SC_OK, deleteResponse.getStatusCode());
    }

    private static Stream<Arguments> sampleBoardNameData() {                // JUnit - metoda z danymi testowymi, zwraca stream argumentów przekazywanych do testów

        return Stream.of(
                Arguments.of("nazwaTablicy"),
                Arguments.of("NAZWATABLICY"),
                Arguments.of("NAZWA_TABLICY"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&")
        );
    }


}
