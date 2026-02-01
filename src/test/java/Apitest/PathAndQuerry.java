package Apitest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PathAndQuerry {

    @Test
    public void testPathParameter(){
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .pathParam("mypath", "users")
                .log().all()
        .when()
                .get("https://reqres.in/api/{mypath}")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testQueryParameter(){
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .queryParam("page", 2)
                .queryParam("id",5)
                .log().all()
        .when()
                .get("https://reqres.in/api/users")
        .then()
                .statusCode(200)
                .log().all();
             //   .body("page", equalTo(2));
    }
}
