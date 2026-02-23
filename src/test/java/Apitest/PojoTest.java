package Apitest;

import Apitest.Pojo_Post.PoJo_postRequest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PojoTest {

    @Test
    public void testWithJSONObject(){
        JSONObject data = new JSONObject();
        data.put("name", "Subham");
        data.put("job", "Lead");

        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("Content-Type", "application/json")
                .body(data.toString())
                .log().all()
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .log().all()
                .body("name", equalTo("Subham"))
                .body("job", equalTo("Lead"));
    }

    @Test
    public void testPojo(){
        PoJo_postRequest data=new PoJo_postRequest();
        data.setName("Subham");
        data.setJob("SDET");
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("Content-Type", "application/json")
                .body(data)
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .body("name", equalTo("Subham"))
                .body("job", equalTo("SDET"));
    }

    @Test
    public void testJson() throws FileNotFoundException {
        File f=new File("src/Body/Body.Json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject data=new JSONObject(jt);
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("Content-Type", "application/json")
                .body(data.toString())
                .log().all()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .body("name", equalTo("Subham"))
                .body("job", equalTo("SDET"));
    }

}
