package saucedemo.selenium;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
    String baseUrl = "https://www.saucedemo.com/"; // set base url    
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 
    
    private void open_login_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);
    }

    @Test
    public void login_success_case(){
        open_login_page();

        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find element by id 'password' to send key 'secret_sauce'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();

        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products",titleText);

        webDriver.close(); // close web driver
    }

    @Test
    public void login_failure_case(){
        open_login_page();

        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find element by id 'password' to send key 'secret_123'
        webDriver.findElement(By.id("password")).sendKeys("secret_123");
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();

        // verify login is failure by check error button is displayed
        Boolean alertLoginIsDiplayed = webDriver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertEquals( true,alertLoginIsDiplayed);

        webDriver.close(); // close web driver
    }
}
