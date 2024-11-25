package pl.akademiaqa.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.akademiaqa.secrets.TrelloSecrets;

public class BaseRequest {

    // metoda, która ustawi request, zbuduje Request Specification (czyli części wspólne dla requestów)
    // metoda wykorzystuje klasę RequestSpecBuilder (restassured)

    protected static RequestSpecBuilder requestBuilder;                                     // klasa RequestSpecBuilder

    public static RequestSpecification requestSetup() {                                     // metoda zwraca obiekt typu RequestSpecification
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addQueryParam("key", TrelloSecrets.getKey());
        requestBuilder.addQueryParam("token", TrelloSecrets.getToken());
        requestBuilder.addFilter(new RequestLoggingFilter());                               // wyświetla na konsolę wszystkie info o requeście
        requestBuilder.addFilter(new ResponseLoggingFilter());                              // wyświetla na konsolę wszystkie info o responsie

        return requestBuilder.build();                                                      // zbudowanie obiektu
    }
    // metoda ustawia request, buduje Request Specification (czyli części wspólne dla requestów)
    // metoda wykorzystuje klasę RequestSpecBuilder (restassured)
    // metoda zwraca obiekt typu RequestSpecification


}
