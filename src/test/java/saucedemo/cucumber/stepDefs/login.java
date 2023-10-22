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

public class login {
    String baseUrl = "https://www.saucedemo.com/"; // set base url  
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 

    @Given("Login Page Sauce Demo")
    public void login_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals( true, loginButtonIsDiplayed);
    }

    @When("Input username")
    public void input_username(){
        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input password")
    public void input_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Input wrong password")
    public void input_wrong_password(){
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_123");
    }

    @And("Click login button")
    public void click_login_button(){
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("User redirect to Product Page")
    public void user_redirect_to_product_page(){
        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
        webDriver.close(); // close web driver
    }

    @Then("User get error login message")
    public void user_get_error_login_message(){
        // verify login is failure by check error button is displayed
        Boolean alertLoginIsDiplayed = webDriver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertEquals( true, alertLoginIsDiplayed);
        webDriver.close(); // close web driver
    }
}