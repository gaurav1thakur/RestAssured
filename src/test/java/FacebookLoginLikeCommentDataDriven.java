import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class FacebookLoginLikeCommentDataDriven {
        @DataProvider
        public Object[] dataFile() throws BiffException, IOException
        {
            File credentialFile = new File("../Automation.Selenium_Framwork/credetials.xls");

            Workbook wk = Workbook.getWorkbook(credentialFile);

            Sheet ws=wk.getSheet(0);

            int row= ws.getRows();
            int col= ws.getColumns();

            Object[][] obj = new Object[row][col];

            for (int i=0 ; i<row; i++)
            {
                for (int j=0; j<col; j++)
                {
                    Cell myCell = ws.getCell(j, i);
                    obj[i][j]= myCell.getContents();
                }
            }
            return obj;
        }
        @Test(dataProvider="dataFile")
        public void facebookLogin(String email, String pass) throws InterruptedException

        {
            System.setProperty("webdriver.chrome.driver", "/Downloads/chrom/chromedriver");
            ChromeDriver driver = new ChromeDriver();

            driver.get("https://www.facebook.com/");
            driver.findElement(By.id("royal_email")).sendKeys(email);
            driver.findElement(By.id("royal_pass")).sendKeys(pass);
            driver.findElement(By.id("royal_login_button")).click();

        }

}
