Feature: Cart - Sauce Demo

        @Regression @Positive
        Scenario: Add Product to Cart Success
            Given Cart - Login Page Sauce Demo
             When Cart - Input username
              And Cart - Input password
              And Cart - Click login button
             Then Cart - User redirect to Product Page
             When Cart - Add product to cart
             Then Badge on cart button displayed

        @Regression @Negative
        Scenario: Add Product to Cart Failure
            Given Cart - Login Page Sauce Demo
             When Cart - Input username
              And Cart - Input password
              And Cart - Click login button
             Then Cart - User redirect to Product Page
             When Click on product item desc
             Then Remove button on product not displayed

    