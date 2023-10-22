package saucedemo.cucumber.stepDefs;

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

public class checkout {
    String baseUrl = "https://www.saucedemo.com/"; // set base url  
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 

    @Given("Checkout - Login Page Sauce Demo")
    public void checkout_login_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals( true, loginButtonIsDiplayed);
    }

    @When("Checkout - Input username")
    public void checkout_input_username(){
        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Checkout - Input password")
    public void checkout_input_password(){
        // find element by id 'password' to send key 'secret_sauce'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Checkout - Click login button")
    public void checkout_click_login_button(){
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("Checkout - User redirect to Product Page")
    public void checkout_user_redirect_to_product_page(){
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
    }

    @When("Checkout - Add product to cart")
    public void checkout_add_product_to_cart(){
        // find first element from list add to cart buttons to perform action click
        webDriver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory '][text()='Add to cart']")).get(0).click();

        // verify current page is product list page and cart badge is displayed
        Boolean cartBadgeIsDisplayed = webDriver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Assert.assertEquals(true, cartBadgeIsDisplayed);
    }

    @And("Checkout - Click cart button")
    public void checkout_click_cart_button(){
        // find element by id 'shopping_cart_container' to perform action click
        webDriver.findElement(By.id("shopping_cart_container")).click();
    }

    @Then("Checkout - User redirect to Cart Page")
    public void checkout_user_redirect_to_cart_page(){
        // verify current page is cart page by check text from element 
        String cartTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Your Cart')]")).getText();
        Assert.assertEquals("Your Cart", cartTitleText);
    }

    @When("Click checkout button")
    public void click_checkout_button(){
        // find element by id 'checkout' to perform action click
        webDriver.findElement(By.id("checkout")).click();
    }

    @Then("User redirect to Information Page")
    public void user_redirect_to_information_page(){
         // verify current page is checkout information page by check text from element 
         String checkoutTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Checkout: Your Information')]")).getText();
         Assert.assertEquals("Checkout: Your Information", checkoutTitleText);    
    }

    @When("Input first name")
    public void input_first_name(){
        // find element by id 'first-name' to send key 'Ridwan'
        webDriver.findElement(By.id("first-name")).sendKeys("Ridwan");
    }

    @And("Input last name")
    public void input_last_name(){
        // find element by id 'last-name' to send key 'Ismail'
        webDriver.findElement(By.id("last-name")).sendKeys("Ismail");
     }

    @And("Input postal code")
    public void input_postal_code(){
        // find element by id 'postal-code' to send key '40552'
        webDriver.findElement(By.id("postal-code")).sendKeys("40552");
    }

    @And("Click continue button")
    public void click_continue_button(){
        // find element by id 'continue' to perform action click
        webDriver.findElement(By.id("continue")).click();
    }

    @Then("User redirect to Checkout Page")
    public void user_redirect_to_checkout_page(){
       // verify current page is checkout overview page by check text from element 
       String checkoutTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Checkout: Overview')]")).getText();
       Assert.assertEquals("Checkout: Overview", checkoutTitleText);
    }

    @When("Click finish button")
    public void click_finish_button(){
        // find element by id 'finish' to perform action click
        webDriver.findElement(By.id("finish")).click();
    }

    @Then("User redirect to Success Page")
    public void user_redirect_to_success_page(){
        // verify current page is checkout complete page by check text from element 
        String completeTitleText = webDriver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your order!')]")).getText();
        Assert.assertEquals("Thank you for your order!", completeTitleText);

        webDriver.close(); // close web driver
    }

    @Then("User get error checkout message")
    public void user_get_error_checkout_message(){
        // verify login is failure by check error button is displayed
        Boolean alertLoginIsDiplayed = webDriver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertEquals(true, alertLoginIsDiplayed);
        webDriver.close(); // close web driver
    }
}