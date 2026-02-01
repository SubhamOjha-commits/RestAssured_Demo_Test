package Apitest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HeadersDemo {
    @Test
    public void testHeaders(){
        given()

        .when()
                .get("https://www.google.com")

        .then()
                .headers("Content-Type","text/html; charset=ISO-8859-1")
                .headers("Content-Encoding","gzip")
                .log().all();
    }

    @Test
    public void getHeaders(){
        Response resp=given()

        .when()
                .get("https://www.google.com");

        Headers header=resp.getHeaders();
        for(Header hd:header) {
            System.out.println("Key is: " + hd.getName() + " Value is: " + hd.getValue());
        }

    }
}
