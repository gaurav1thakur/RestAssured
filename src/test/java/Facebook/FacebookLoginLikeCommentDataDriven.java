package Facebook;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;


public class FacebookLoginLikeCommentDataDriven {
    String post_Id;
    /*
     create post and save post id once
     */

    @Test
    public void createFacebookPost() {
        RestAssured.baseURI = "https://graph.facebook.com";
        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("message", "This heart of mine was made to travel this world")
                        .queryParam("access_token", "")
                        .when()
                        .post("/feed");
        String response = res.asString();
        JsonPath js = new JsonPath(response);
        post_Id = js.get("id");
    }

    @DataProvider
    public Object[] dataFile() throws BiffException, IOException {
        File credentialFile = new File("../RestAssuredSample/src/test/java/resources/creds.xls");

        Workbook wk = Workbook.getWorkbook(credentialFile);

        Sheet ws = wk.getSheet(0);

        int row = ws.getRows();
        int col = ws.getColumns();

        Object[][] obj = new Object[row][col];

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell myCell = ws.getCell(j, i);
                obj[i][j] = myCell.getContents();
            }
        }
        return obj;
    }

    /*
      Login for n number of users and like and comment
     */

    @Test(dataProvider = "dataFile")
    public void facebookLogin(String Email, String Password, String Comments) throws InterruptedException {

        //login
        RestAssured.baseURI = "https://graph.facebook.com/v4.0/oauth/access_token?";
        String redirect_uri = "your redirection url after facebook login completes";
        String client_id = "your app id";
        String client_secret = "your app secret";
        String code = "fetch code before generating token";
        String token ;

        Response res =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("client_id", "298758276968936")
                        .header("redirect_uri", "https://www.facebook.com/connect/login_success.html")
                        .when()
                        .post("/me");
        String response = res.asString();
        JsonPath js = new JsonPath(response);
        String user_Id = js.get("id");

        //post id -> like, comment from xls



    }
}
