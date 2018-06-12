package com.domain.api.generic;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;

import com.github.javafaker.Faker;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class UtilityManager 
{

	
	//Post image files
	public static Response getResponsefromPostMediaRequest(String header_parameter_1,String header_parameter_2,
															String key,String filePath,String fileType,String sub_uri_type)
	{
		Response upload= given()
						.header(header_parameter_1,header_parameter_2)
						.contentType("multipart/form-data")
						.multiPart(key, new File(filePath),fileType)
						.when()
				        .post(URIBuilder.BASE_URI+sub_uri_type);
		return upload;
	}
	
	//Post Request Utils
	public static Response getResponseFromPostRequest(String header_parameter_1,String header_parameter_2,
			  										  String sub_uri_type,String uri_append)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append);
		return retriveBody;
	}
	
	public static Response getResponseFromPostRequest(String header_parameter_1,String header_parameter_2,
			  Object object,String sub_uri_type,String uri_append)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.body(object)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append);
		return retriveBody;
	}
	

	public static Response getResponseFromPostRequest(String header_parameter_1,String header_parameter_2,Object object,
													  String sub_uri_type,String uri_append_1,String uri_append_2)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.body(object)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append_1+"/"+uri_append_2);
		return retriveBody;
	}

	public static Response getResponseFromPostRequest(String header_parameter_1,String header_parameter_2,String object,
													  String sub_uri_type,String uri_append_1,String uri_append_2)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.body(object)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append_1+"/"+uri_append_2);
		return retriveBody;
	}
	public static Response getResponseFromPostRequest(String header_parameter_1,String header_parameter_2,String parameter_key,String parameter_value,Object object,
			  String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.param(parameter_key, parameter_value)
							.when()
							.contentType(ContentType.JSON)
							.body(object)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	
	
	
	public static int getStatusFromPostObject(String header_parameter_1,String header_parameter_2,
				  Object post_object,String sub_uri_type)
	{
		int status=given()
				  .header(header_parameter_1,header_parameter_2)
				  .contentType(ContentType.JSON)
				  .when()
				  .body(post_object)
				  .then()
				  .post(URIBuilder.BASE_URI+sub_uri_type)
				  .statusCode();
		return status;
	}

	public static Response getResponseObjectFromPostRequest(Object post_object,String sub_uri_type)
	{
		Response retriveBody=given()
						.contentType(ContentType.JSON)
						.when()
						.body(post_object)
						.then()
						.post(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	public static Response getResponseObjectFromPostRequest(String header_parameter_1,String header_parameter_2,
						Object post_object,String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.contentType(ContentType.JSON)
							.when()
							.body(post_object)
							.then()
							.post(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}

	


	//Get Request Utils
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,Object object,
			 String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.body(object)
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	
	
	
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
													 String sub_uri_type,String uri_append)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append);
		return retriveBody;
	}
/*	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String sub_uri_type,String uri_append_1,String uri_append_2)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append_1+"/"+uri_append_2);
		return retriveBody;
	}*/
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String parameter_key_1,String parameter_value_1,String parameter_key_2,String parameter_value_2,
			 String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.param(parameter_key_1, parameter_value_1)
							.param(parameter_key_2, parameter_value_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	

	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String parameter_key_1,String parameter_value_1,Object object,String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.param(parameter_key_1, parameter_value_1)
							.when()
							.body(object)
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	
	
	
	
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String parameter_key_1,String parameter_value_1,String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.param(parameter_key_1, parameter_value_1)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
													Object object, String sub_uri_type,String uri_append)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.body(object)
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append);
		return retriveBody;
	}
	
	
	public static Response getResponseFromGetRequest(String header_parameter_1,String header_parameter_2,
			 String parameter_key_1,String parameter_value_1,String parameter_key_2,String parameter_value_2
			 ,String parameter_key_3,String parameter_value_3,String sub_uri_type)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.param(parameter_key_1, parameter_value_1)
							.param(parameter_key_2, parameter_value_2)
							.param(parameter_key_3, parameter_value_3)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type);
		return retriveBody;
	}
	//For put request
	public static Response getResponseObjectFromPutRequest(String header_parameter_1,String header_parameter_2,
			Object post_object,String sub_uri_type,String uri_append)
	{
	Response retriveBody=given()
					.header(header_parameter_1,header_parameter_2)
					.contentType(ContentType.JSON)
					.when()
					.body(post_object)
					.then()
					.put(URIBuilder.BASE_URI+sub_uri_type+"/"+uri_append);
	return retriveBody;
	}
	
	
	
	//For Getting the path
	public static Response getPathContents(String header_parameter_1,String header_parameter_2,
			 							  String sub_uri_type,String path)
	{
		Response retriveBody=given()
							.header(header_parameter_1,header_parameter_2)
							.when()
							.contentType(ContentType.JSON)
							.then()
							.get(URIBuilder.BASE_URI+sub_uri_type).path(path);
		return retriveBody;
	}
	
	//Below codes generates random name
	public static String generateRandomName()
		{
			Faker faker = new Faker();
			String name = faker.name().fullName();
	        return name;
		}
		
	public static String generateRandomDescription()
		{
			Faker faker = new Faker();
			String name=faker.commerce().productName();
			return name;
		}

	
}
