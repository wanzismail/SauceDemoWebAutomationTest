package saucedemo.cucumber.stepDef;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class product {
    String baseUrl = "https://www.saucedemo.com/"; // set base url  
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 

    @Given("Product - Login Page Sauce Demo")
    public void checkout_login_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);
    }

    @When("Product - Input username")
    public void checkout_input_username(){
        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Product - Input password")
    public void checkout_input_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Product - Input wrong password")
    public void checkout_input_wrong_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_123");
    }

    @And("Product - Click login button")
    public void checkout_click_login_button(){
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("Product - User redirect to Product Page")
    public void checkout_user_redirect_to_product_page(){
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
    }
   
    @When("Click on product name")
    public void click_on_product_name(){
        // find first element from list elements with class name 'inventory_item_name' to perform action click
        webDriver.findElements(By.className("inventory_item_name")).get(0).click();
    }

     @When("Click on product desc")
    public void click_on_product_desc(){
        // find first element from list elements with class name 'inventory_item_desc' to perform action click
        webDriver.findElements(By.className("inventory_item_desc")).get(0).click();
    }

    @Then("User redirect to Product Detail Page")
    public void badge_on_cart_button_displayed(){
        // verify current page is detail product page by check back button is displayed
        Boolean backButtonIsDisplayed = webDriver.findElement(By.id("back-to-products")).isDisplayed();
        Assert.assertEquals(true, backButtonIsDisplayed);
 
        webDriver.close(); // close web driver
    }

    @Then("User stay on Product Page")
    public void badge_on_cart_button_not_displayed(){
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals(titleText, "Products");
 
        webDriver.close(); // close web driver
    }
}