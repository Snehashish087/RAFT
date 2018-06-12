$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("/Users/snehashish/shopkick_automation/CIService/RAFT/cucumber-features-repository/contactcreate.feature");
formatter.feature({
  "line": 1,
  "name": "Creating a contact",
  "description": "",
  "id": "creating-a-contact",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "As a user I should be able to create a contact and view the same",
  "description": "",
  "id": "creating-a-contact;as-a-user-i-should-be-able-to-create-a-contact-and-view-the-same",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 2,
      "name": "@SmokeTesting"
    }
  ]
});
formatter.step({
  "line": 4,
  "name": "User is in the login page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "He enters the username and password",
  "rows": [
    {
      "cells": [
        "snehashish087",
        "1ep10cs087"
      ],
      "line": 6
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Clicks on Login",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "User should be in the home page of the application",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "User hovers the mouse on contacts option",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "User should be able to select the option New Contact",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "User should be able to select the Title",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "User should be able to enter the contact person\u0027s first name",
  "rows": [
    {
      "cells": [
        "Saniya"
      ],
      "line": 13
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "User should be able to enter the contact person\u0027s last name",
  "rows": [
    {
      "cells": [
        "Tagore"
      ],
      "line": 15
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Once the User provides the mandatory fields he should be able to save the contact details",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "The contactName details are saved user should be able to view the contact in contacts table",
  "rows": [
    {
      "cells": [
        "Saniya Tagore"
      ],
      "line": 18
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "The user clicks on logout",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "The user should be able view the login page with the login page title",
  "keyword": "Then "
});
formatter.match({
  "location": "CreateContactTests.user_is_in_the_login_page()"
});
