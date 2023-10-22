package saucedemo.cucumber.stepDef;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class cart {
    String baseUrl = "https://www.saucedemo.com/"; // set base url  
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 

    @Given("Cart - Login Page Sauce Demo")
    public void checkout_login_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);
    }

    @When("Cart - Input username")
    public void checkout_input_username(){
        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Cart - Input password")
    public void checkout_input_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Cart - Input wrong password")
    public void checkout_input_wrong_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_123");
    }

    @And("Cart - Click login button")
    public void checkout_click_login_button(){
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("Cart - User redirect to Product Page")
    public void checkout_user_redirect_to_product_page(){
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
    }

    @When("Cart - Add product to cart")
    public void checkout_add_product_to_cart(){
        // find element by id 'add-to-cart-sauce-labs-backpack' to perform action click
        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }
    
    @When("Click on product item desc")
    public void click_on_product_item_desc(){
        // find first element from list element with class name 'inventory_item_desc' to perform action click
        webDriver.findElements(By.className("inventory_item_desc")).get(0).click();
    }


    @Then("Badge on cart button displayed")
    public void badge_on_cart_button_displayed(){
         // verify current page is product list page and cart badge is not displayed
         Boolean cartBadgeIsDisplayed = webDriver.findElement(By.className("shopping_cart_badge")).isDisplayed();
         Assert.assertEquals(true, cartBadgeIsDisplayed);
 
         webDriver.close(); // close web driver
    }

    @Then("Remove button on product not displayed")
    public void remove_button_on_product_not_displayed(){
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