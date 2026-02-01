package Apitest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CookiesDemo {
    @Test
public void testCookies()

    {
        given()

        .when()
                .get("https://www.google.com")

        .then()
                .cookie("AEC","AaJma5tcP2JGQAtQUQlJlD2hHDmKp8bAErbPT850S5Tn8V3ejuycAnRElys")
                .log().all();

    }
    @Test
    public void getCokkiesInfo(){
        Response resp=given()

        .when()
                .get("https://www.google.com");

       Map<String,String> cookie_value = resp.getCookies();
       for(String key:cookie_value.keySet()){
           System.out.println("Key is: "+key+" Value is: "+cookie_value.get(key));
       }

    }
}
