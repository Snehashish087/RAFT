package com.domain.api.tests;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.domain.api.dto.requests.LoginRequest;
import com.domain.api.dto.responses.LoginResponse;
import com.domain.api.generic.InitialConfiguration;
import com.domain.api.generic.URIBuilder;
import com.domain.api.generic.UtilityManager;
import com.google.gson.Gson;
import com.jayway.restassured.response.Response;


public class LoginTests extends InitialConfiguration
{
	@Test
	public void verify_login_to_the_application_using_valid_username_and_password()
	{
	logger=extent.startTest("Verify login to the application with valid id","As a user when I try to login to the application I should be assigned with a authentication token for my session assigned to my user id");
	Logger log=Logger.getLogger(LoginTests.class);
	log.info("Create an object of login request body");
	LoginRequest login = new LoginRequest();
	login.setEmail("shivam.kannojiya@goodworklabs.com");
	login.setPassword("12345");
	log.info("Passing the body as a post request and retrieving the body result from the post request.");
	Response responseBody=UtilityManager.getResponseObjectFromPostRequest(login, URIBuilder.LOGIN_URI);
	log.info("Get the body in string format");
	String body=responseBody.getBody().asString();
	log.info("Converting the body from JSON body to GSON Body");
	LoginResponse responseResult= new Gson().fromJson(body, LoginResponse.class);
	log.info("Get the ID");
	int id = responseResult.getId();
	log.info("Get the Token");
	responseResult.getToken();
	try
	{
	log.info("Check if the user id matches as per our expected output.");
	Assert.assertEquals(id, 1);
	Reporter.log("The id is matching as per expected",true);
	}
	catch(AssertionError exp)
	{
		Reporter.log("The id is not matching",true);
		Assert.fail();
	}
	
	
	}

}
