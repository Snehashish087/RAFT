Feature: Creating a contact
@SmokeTesting
Scenario: As a user I should be able to create a contact and view the same
Given User is in the login page
When  He enters the username and password
|snehashish087|1ep10cs087|
And Clicks on Login
Then User should be in the home page of the application
When User hovers the mouse on contacts option
Then User should be able to select the option New Contact
And User should be able to select the Title
And User should be able to enter the contact person's first name
|Saniya|
And User should be able to enter the contact person's last name
|Tagore|
And Once the User provides the mandatory fields he should be able to save the contact details
When The contactName details are saved user should be able to view the contact in contacts table
|Saniya Tagore|
When The user clicks on logout
Then The user should be able view the login page with the login page title