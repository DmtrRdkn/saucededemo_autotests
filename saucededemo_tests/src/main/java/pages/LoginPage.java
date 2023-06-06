package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name=\"user-name\"]")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name=\"login-button\"]")
    private WebElement logBtn;

    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement error;

    @FindBy(xpath = "//div[@class='error-message-container']")
    private WebElement error_container;

    @FindBy(xpath = "//h3[@data-test='error']/button[@class='error-button']")
    private WebElement errorCloseBtn;

    public void clickLoginBtn() {

        logBtn.click();
    }

    public void closeError(){

        errorCloseBtn.click();
    }

    public void inputLogin(String login) {

        loginInput.sendKeys(login);
    }

    public void inputPassword(String password) {

        passwordInput.sendKeys(password);
    }

    public String errorText(){

        return error.getAttribute("textContent");
    }

    public String errorContinerText(){
        if (error_container.getAttribute("contentText")==null) return "";
        return error_container.getAttribute("contentText");
    }

    public String loginBorderColor(){
        return loginInput.getCssValue("border-bottom-color");
    }

    public String passwordBorderColor(){
        return passwordInput.getCssValue("border-bottom-color");
    }

    public  boolean errorIsPresent(){
        return this.isElementPresent("//h3[@data-test=\"error\"]");
    }
}
