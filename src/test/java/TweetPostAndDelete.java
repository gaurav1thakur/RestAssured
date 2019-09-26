import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class TweetPostAndDelete {

    @Test
    public void postTweetAndDelete()
    {
        String consumer_key = "I9HliRN46eUM21enxlqZxgd5v";
        String consumer_Secret = "woVx8oPhBcfyEreONgwBWU14w2XKjhdiFpDcstwTXeleixyOFc";
        String access_Token = "790217985587748864-0vSYY8y0KpGbQYehqBncavovlZqmeNo";
        String token_Secret = "XPmgIFQpWX9hVbh1C8R3iGuHwpVMrF8jEhxblguRwFdfs";
        //key,secret,token,token secret - may expired and give 403

        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";

        Response res=
                given()
                        .contentType(ContentType.JSON)
                        .auth()
                        .oauth(consumer_key, consumer_Secret, access_Token, token_Secret)
                        .queryParam("status","Hi This is my test tweet to be deleted")
                        .when()
                        .post("/update.json");
        String response = res.asString();
        System.out.println(res.asString());
        JsonPath js  = new JsonPath(response);
        long id = js.get("id");
        System.out.println(id);

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth(consumer_key, consumer_Secret, access_Token, token_Secret)
                .when()
                .post("/destroy/"+id+".json");
    }

}
