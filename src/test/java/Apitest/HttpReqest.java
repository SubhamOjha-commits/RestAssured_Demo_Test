package Apitest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//https://reqres.in//api/users/2
public class HttpReqest {
    int id;
    @Test
    public void getUser(){
              given()
                      .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                      .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                      .header("Accept", "application/json")
                      .log().all()
              .when()
                      .get("https://reqres.in/api/users?page=2")
              .then()
                      .log().all()
                      .statusCode(200)
                      .body("page",equalTo(2));
    }
    @Test(priority = 1)
    public void CreateUSer(){

        HashMap<String,String> hm=new HashMap<>();
        hm.put("name","subham");
        hm.put("job","leader");
               id=given()
                        .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                        .log().all()
                        .contentType("application/json")
                        .body(hm)
                .when()
                        .post("https://reqres.in/api/users")
                        .jsonPath().getInt("id");

    }
    @Test(priority = 2,dependsOnMethods = {"CreateUSer"})
    public void UpdateUSer(){
        HashMap<String,String> hm=new HashMap<>();
        hm.put("name","subham");
        hm.put("job","CEO");
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .log().all()
                .contentType("application/json")
                .body(hm)
        .when()
                .put("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(200)
                .log().all();

    }
    @Test(priority = 3)
    public void deleteUser(){
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .log().all()
        .when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }

}
