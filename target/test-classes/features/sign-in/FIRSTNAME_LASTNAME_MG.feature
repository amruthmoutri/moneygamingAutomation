#Feature: Sign up new user
#  In order to tests the password Criteria
#  As a new user
#  I want to specify conditions
#
#  Background:
#    Given user is on home page
#    And join Now button is present on homepage
#    And user clicks on join now button to display sign up page
#    When user enter details on sign up page
#      | Label                  | Value                             |
#      | Title: *               | Mrs                               |
#      | First Name: *          | random                            |
#      | SurName: *             | random                            |
#      | Date of Birth: *       | 23-00-1997                        |
#      | Email Address: *       | test@test.com                     |
#      | Telephone: *           | 123123123123                      |
#      | Mobile: *              | 123123123123                      |
#
#  Scenario Outline: Sign up fail - possible combinations
#    And I enter "<Password>" in the text field area
#    And I enter "<Re-type password>" in the text field area
#    And user "accept" "Terms and Conditions" on sign in page and verify it's selected
#    Then I click on Join now button
#    Examples:
#      | Password | Re-type password |
#      | random   | random           |
#      | random1  | random1          |
#      | random@  | random@          |
#      | ran      | ran              |
#      | ran!@    | ran!@            |
#      | 123123   | 232423           |
#      | !!!!!!   | !!!!!!           |










