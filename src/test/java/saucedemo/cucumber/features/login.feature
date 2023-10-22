Feature: Login - Sauce Demo

        @Regression @Positive
        Scenario: Login Success
            Given Login Page Sauce Demo
             When Input username
              And Input password
              And Click login button
             Then User redirect to Product Page

        @Regression @Negative
        Scenario: Login Failure
            Given Login Page Sauce Demo
             When Input username
              And Input wrong password
              And Click login button
             Then User get error login message
