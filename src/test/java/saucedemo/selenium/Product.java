package saucedemo.selenium;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Product {
    String baseUrl = "https://www.saucedemo.com/"; // set base url 
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 
      
    private void open_product_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);

        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find element by id 'password' to send key 'secret_sauce'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();

        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
    }

    @Test
    public void view_product_detail_success_case(){
        open_product_page();

        // find first element from list elements with class name 'inventory_item_name' to perform action click
        webDriver.findElements(By.className("inventory_item_name")).get(0).click();
        
        // verify current page is detail product page by check back button is displayed
        Boolean backButtonIsDisplayed = webDriver.findElement(By.id("back-to-products")).isDisplayed();
        Assert.assertEquals(true, backButtonIsDisplayed);

        webDriver.close(); // close web driver
    }

    @Test
    public void view_product_detail_failure_case(){
         open_product_page();

        // find first element from list elements with class name 'inventory_item_desc' to perform action click
        webDriver.findElements(By.className("inventory_item_desc")).get(0).click();
        
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);

        webDriver.close(); // close web driver
    }
}
