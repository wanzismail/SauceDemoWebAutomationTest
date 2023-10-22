package saucedemo.selenium;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Cart {
    String baseUrl = "https://www.saucedemo.com/"; // set base url 
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 
      
    private void open_product_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals( true, loginButtonIsDiplayed);

        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();

        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals( "Products", titleText);
    }

    @Test
    public void add_product_to_cart_success_case(){
        open_product_page();

        // find first element from list add to cart buttons to perform action click
        webDriver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory '][text()='Add to cart']")).get(0).click();

        // verify current page is product list page and cart badge is exists
        Boolean cartBadgeIsDisplayed = webDriver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Assert.assertEquals(true, cartBadgeIsDisplayed);

        webDriver.close(); // close web driver
    }

    @Test
    public void add_product_to_cart_failure_case(){
        open_product_page();

        // find first element from list element with class name 'inventory_item_desc' to perform action click
        webDriver.findElements(By.className("inventory_item_desc")).get(0).click();

        // verify current page is product list page and not have button with text 'Remove'
        Boolean haveRemoveButton = false;
        try{
            haveRemoveButton = !webDriver.findElements(By.xpath("//button[contains(text(), 'Remove')")).isEmpty();
        } catch(NoSuchElementException e){
            System.out.println("Element not exists");
        }
        Assert.assertEquals(false, haveRemoveButton);

        webDriver.close(); // close web driver
    }
}
