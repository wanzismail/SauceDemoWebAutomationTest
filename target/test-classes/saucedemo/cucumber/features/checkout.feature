Feature: Checkout - Sauce Demo

        @Regression @Positive
        Scenario: Checkout Success
            Given Checkout - Login Page Sauce Demo
             When Checkout - Input username
              And Checkout - Input password
              And Checkout - Click login button
             Then Checkout - User redirect to Product Page
             When Checkout - Add product to cart
              And Checkout - Click cart button
             Then Checkout - User redirect to Cart Page
             When Click checkout button
             Then User redirect to Information Page
             When Input first name
              And Input last name
              And Input postal code
              And Click continue button
             Then User redirect to Checkout Page
             When Click finish button
             Then User redirect to Success Page

        @Regression @Negative
        Scenario: Checkout Failure
            Given Checkout - Login Page Sauce Demo
             When Checkout - Input username
              And Checkout - Input password
              And Checkout - Click login button
             Then Checkout - User redirect to Product Page
             When Checkout - Add product to cart
              And Checkout - Click cart button
             Then Checkout - User redirect to Cart Page
             When Click checkout button
             Then User redirect to Information Page
             When Click continue button
             Then User get error checkout message

    