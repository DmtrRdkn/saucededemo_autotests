package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Arrays;

public class InventoryPage extends Page{

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath ="//button[@id='react-burger-menu-btn']")
    private WebElement menuBtn;

    @FindBy(xpath ="//a[@class='shopping_cart_link']")
    private WebElement basketBtn;

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement logoMain;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement title;

    @FindBy(xpath = "//select[@data-test=\"product_sort_container\"]")
    private WebElement sortContainer;

    @FindBy(xpath = "//option[@value=\"az\"]")
    private WebElement sortAZ;

    @FindBy(xpath = "//option[@value=\"za\"]")
    private WebElement sortZA;

    @FindBy(xpath = "//option[@value=\"lohi\"]")
    private WebElement sortLowToHigh;

    @FindBy(xpath = "//option[@value=\"hilo\"]")
    private WebElement sortHighToLow;

    @FindBy(xpath = "//div[@class='inventory_list']")
    private WebElement inventoryList;

    private WebElement[] items = new WebElement[6];


    public void findItems(){
        String path="//div[@class='inventory_item']";
        WebElement [] items = new WebElement[6];
        for (int i = 0; i <6 ; i++) {
            items[i]=driver.findElement(By.xpath(path+"["+Integer.toString(i+1)+"]"));
        }
        System.arraycopy(items,0,this.items,0,5);
    }

    public String nameItem(int i){
        return items[i].getAttribute("innerText");
    }


}
