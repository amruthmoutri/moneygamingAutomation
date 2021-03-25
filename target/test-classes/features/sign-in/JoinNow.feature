Feature: Sign up new user
  As a new moneygaming user
  I want to be able to register using my details
  So that I can play online casino games


  Background:
  Given user is on home page
   And join Now button is present on homepage
   And user clicks on join now button to display sign up page
  Then I expect sign up page to have form labels
   And I expect terms and conditions on sign in page to not be selected

  Scenario: New gaming user registration
  When user enter details on sign up page
      | Label         | Value  |
      | Title: *      | Mr     |
      | First Name: * | random |
      | SurName: *    | random |
   And user "accept" "Terms and Conditions" on sign in page and verify it's selected
  Then I click on Join now button
   And I verify "This field is required" appears under "DOB"





  # Note I enter user details on the sign up page can handel the  below fields as well
#      | Date of Birth: *       | 23-00-1997                        |
#      | Email Address: *       | test@test.com                     |
#      | Telephone: *           | 123123123123                      |
#      | Mobile: *              | 123123123123                      |
#      | Address Line 1: *      | 13231231                          |
#      | City: *                | London                            |
#      | County: *              | London                            |
#      | Postcode: *            | TK23 5DE                          |
#      | Country *              | GB                                |
#      | Choose Username *      | am343                             |
#      | Choose Password *      | am3432!                           |
#      | Re-type Password *     | am3432!                           |
#      | Security question 1: * | What was your childhood nickname? |
#      | Answer: *              | hello                             |
#      | Security question 2: * | What is your favorite color?      |
#      | Answer two: *          | hello                             |
#      | Currency: *            | GBP                               |



