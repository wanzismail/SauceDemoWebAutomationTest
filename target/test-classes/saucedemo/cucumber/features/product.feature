Feature: Product - Sauce Demo

        @Regression @Positive
        Scenario: View Product Detail Success
            Given Product - Login Page Sauce Demo
             When Product - Input username
              And Product - Input password
              And Product - Click login button
             Then Product - User redirect to Product Page
             When Click on product name
             Then User redirect to Product Detail Page

        @Regression @Negative
        Scenario: View Product Detail Failure
            Given Product - Login Page Sauce Demo
             When Product - Input username
              And Product - Input password
              And Product - Click login button
             Then Product - User redirect to Product Page
             When Click on product desc
             Then User stay on Product Page

    