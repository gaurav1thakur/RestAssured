package Facebook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class FacebookLoginLikeCommentDataDriven {
    @DataProvider
    public Object[] dataFile() throws BiffException, IOException {
        File credentialFile = new File("../RestAssuredSample/src/test/java/resources/creds.xls");

        Workbook wk = Workbook.getWorkbook(credentialFile);

        Sheet ws = wk.getSheet(0);

        int row = ws.getRows();
        int col = ws.getColumns();

        Object[][] obj = new Object[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell myCell = ws.getCell(j, i);
                obj[i][j] = myCell.getContents();
            }
        }
        return obj;
    }

    @Test(dataProvider = "dataFile")
    public void facebookLogin(String Email, String Password, String Comments) throws InterruptedException {

        //login

        //create post and save post id once

        //post id -> like, comment from xls

    }

}
