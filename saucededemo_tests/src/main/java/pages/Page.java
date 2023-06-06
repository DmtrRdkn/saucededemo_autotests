package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

    public WebDriver driver;

    public Page (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public void open(String url){

        driver.get(url);
    }

    public boolean isElementPresent(String path){
        try {
            driver.findElement(By.xpath(path));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
