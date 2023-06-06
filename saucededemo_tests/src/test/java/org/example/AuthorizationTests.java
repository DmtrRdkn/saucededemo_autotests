package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utill.ConfPropirties;
import utill.ExcelDataProvider;

import java.time.Duration;

public class AuthorizationTests {
    public static WebDriver driver;
    public static LoginPage loginPage;


    @BeforeGroups(groups = {"correctLoginTest","lockedLoginTest","emptyLoginTest","emptyPasswordTest","incorrectDataTest"})
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfPropirties.getProperty("chromedriver"));
        driver=new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }


    @Test (groups = {"correctLoginTest"},dataProvider ="correctLogins",dataProviderClass = ExcelDataProvider.class)
    public void Test1(String login, String password){
        loginPage.open(ConfPropirties.getProperty("page"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @Test(groups = {"lockedLoginTest"},dataProvider ="lockedLogins",dataProviderClass = ExcelDataProvider.class)
    public void Test2(String login, String password){
        loginPage.open(ConfPropirties.getProperty("page"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.errorText(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(groups = {"emptyLoginTest"},dataProvider ="emptyLogin",dataProviderClass = ExcelDataProvider.class)
    public void Test3(String login, String password){
        loginPage.open(ConfPropirties.getProperty("page"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.errorText(),"Epic sadface: Username is required");
    }

    @Test(groups = {"emptyPasswordTest"},dataProvider ="emptyPassword",dataProviderClass = ExcelDataProvider.class)
    public void Test4(String login, String password){
        loginPage.open(ConfPropirties.getProperty("page"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.errorText(),"Epic sadface: Password is required");
    }

    @Test(groups = {"incorrectDataTest"},dataProvider ="incorrectData",dataProviderClass = ExcelDataProvider.class)
    public void Test5(String login, String password){
        loginPage.open(ConfPropirties.getProperty("page"));
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertEquals(loginPage.errorText(),"Epic sadface: Username and password do not match any user in this service");
    }


    @Test(groups = {"VisualChangesTest"},priority = 1,description = "Нижний бортик поля login красный")
    public void Test6(){
        Assert.assertEquals(loginPage.loginBorderColor(),"rgba(226, 35, 26, 1)");
    }

    @Test(groups = {"VisualChangesTest"},priority = 1,description = "Нижний бортик поля password красный")
    public void Test7(){
        Assert.assertEquals(loginPage.passwordBorderColor(),"rgba(226, 35, 26, 1)");
    }

    @Test(groups = {"VisualChangesTest"},priority = 1)
    public void Test8(){
        loginPage.closeError();
        Assert.assertFalse(loginPage.errorIsPresent());
    }

    @Test(groups = {"VisualChangesTest"},priority = 1, dependsOnMethods = {"Test8"})
    public void Test9(){
        Assert.assertEquals(loginPage.loginBorderColor(),"rgba(237, 237, 237, 1)");
    }

    @Test(groups = {"VisualChangesTest"},priority = 1, dependsOnMethods = {"Test8"})
    public void Test10(){
        Assert.assertEquals(loginPage.passwordBorderColor(),"rgba(237, 237, 237, 1)");
    }

    @Test(groups = {"VisualChangesTest"},priority = 1, dependsOnMethods = {"Test8"})
    public void Test11(){
        Assert.assertEquals(loginPage.errorContinerText(),"");
    }



    @AfterGroups(groups = {"correctLoginTest","VisualChangesTest"})
    public void closeUp(){
        driver.close();
    }


}
