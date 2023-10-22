package saucedemo.selenium;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Checkout {
    String baseUrl = "https://www.saucedemo.com/"; // set base url 
    WebDriver webDriver = new SafariDriver(); // set driver for test using WebDriver from Selenium, apply safari driver setup to driver 
      
    private void open_checkout_page(){
        WebDriverManager.safaridriver().config(); // setup safari driver automatically using web drive manager

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout 
        webDriver.get(baseUrl); // access base url

        // verify current page is login page by check login button is displayed
        Boolean loginButtonIsDiplayed = webDriver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);

        // find element by id 'user-name' to send key 'standard_user'
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        // find element by id 'password' to send key 'standard_user'
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        // find element by id 'login-button' to perform action click
        webDriver.findElement(By.id("login-button")).click();

        // verify current page is product page by check text from element 
        String titleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products",titleText);

        // find first element from list add to cart buttons to perform action click
        webDriver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory '][text()='Add to cart']")).get(0).click();
        
        // verify current page is product list page and cart badge is displayed
        Boolean cartBadgeIsDisplayed = webDriver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Assert.assertEquals(true,cartBadgeIsDisplayed);

        // find element by id 'shopping_cart_container' to perform action click
        webDriver.findElement(By.id("shopping_cart_container")).click();

        // verify current page is cart page by check text from element 
        String cartTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Your Cart')]")).getText();
        Assert.assertEquals("Your Cart", cartTitleText);

        // find element by id 'checkout' to perform action click
        webDriver.findElement(By.id("checkout")).click();

        // verify current page is checkout information page by check text from element 
        String checkoutTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Checkout: Your Information')]")).getText();
        Assert.assertEquals( "Checkout: Your Information",checkoutTitleText);
    }

    @Test
    public void checkout_product_success_case(){
        open_checkout_page();

        // find element by id 'first-name' to send key 'Ridwan'
        webDriver.findElement(By.id("first-name")).sendKeys("Ridwan");
        // find element by id 'last-name' to send key 'Ismail'
        webDriver.findElement(By.id("last-name")).sendKeys("Ismail");
        // find element by id 'postal-code' to send key '40552'
        webDriver.findElement(By.id("postal-code")).sendKeys("40552");

        // find element by id 'continue' to perform action click
        webDriver.findElement(By.id("continue")).click();

        // verify current page is checkout overview page by check text from element 
        String checkoutTitleText = webDriver.findElement(By.xpath("//span[contains(text(), 'Checkout: Overview')]")).getText();
        Assert.assertEquals("Checkout: Overview", checkoutTitleText);

        // find element by id 'finish' to perform action click
        webDriver.findElement(By.id("finish")).click();

        // verify current page is checkout complete page by check text from element 
        String completeTitleText = webDriver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your order!')]")).getText();
        Assert.assertEquals("Thank you for your order!",completeTitleText);

        webDriver.close(); // close web driver
    }

    @Test
    public void checkout_product_failure_case(){
        open_checkout_page();

        // find element by id 'continue' to perform action click
        webDriver.findElement(By.id("continue")).click();

        // verify checkout is failure by check error button is displayed
        Boolean alertCheckoutIsDiplayed = webDriver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertEquals( true, alertCheckoutIsDiplayed);

        webDriver.close(); // close web driver
    }
}
