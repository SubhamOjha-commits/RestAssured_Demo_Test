package Apitest;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class DemoTest {
    int id;
    @Test
    public void getreq(){

       Response resp= given()
                .header("x-api-key","reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("content-type","application/json")

        .when()
                .get("https://reqres.in/api/users?page=2");
        JSONObject jo=new JSONObject(resp.asString());
        boolean status=false;
       for(int i=0;i<jo.getJSONArray("data").length();i++){
           String email=jo.getJSONArray("data").getJSONObject(i).get("email").toString();
           if(email.equals("lindsay.ferguson@reqres.in")){
                  status=true;
                  break;
           }
       }
        Assert.assertEquals(status,true);
    }
    @Test
    public void setreq() throws FileNotFoundException {
        File f=new File("src/Body/Body.Json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject data=new JSONObject(jt);
        given()
                .header("x-api-key", "reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("Content-Type", "application/json")
                .body(data.toString())

                .when()
                .post("https://reqres.in/api/users")


                .then()
                .statusCode(201)
                .log().all()
                .body("name",equalTo("Subham"))
                .body("job",equalTo("SDET"));
    }
    @Test (priority = 1)
    public void Create() throws FileNotFoundException {
        File f=new File("src/Body/Body.Json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONArray usersArray=new JSONArray(jt);

        // Create 3 users from the array
        for(int i=0; i<usersArray.length(); i++){
            JSONObject user = usersArray.getJSONObject(i);

            given()
                    .header("x-api-key","reqres_faf32e73cef24716a52fd26b49f60f8b")
                    .header("Content-Type","application/json")
                    .body(user.toString())
                    .log().all()

            .when()
                    .post("https://reqres.in/api/users")

            .then()
                    .statusCode(201)
                    .log().all()
                    .extract().jsonPath().getInt("id");

        }
    }

    @Test(priority = 2,dependsOnMethods = {"Create"})
    public void update() throws FileNotFoundException {
        File f=new File("src/Body/Update.Json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject data=new JSONObject(jt);
        given()
                .header("x-api-key","reqres_faf32e73cef24716a52fd26b49f60f8b")
                .header("Content-Type","application/json")
                .body(data.toString())
                .log().all()

                .when()
                .put("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(200)
                .log().all()
                .body("name",equalTo("Subham"))
                .body("job",equalTo("Senior Sdet"));



    }

    @Test(priority = 3,dependsOnMethods = {"Create"})
    public void delete(){
        given()
                .header("x-api-key","reqres_2be58509bd2d4a10aafbee83d993cb84")
                .log().all()

                .when()
                .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();
    }
}

