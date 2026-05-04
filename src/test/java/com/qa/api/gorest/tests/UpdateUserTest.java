package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest{
	
	
	
	@Test
	public void updateUserTest() {
		//1. post - create a user
		
		User user = User.builder().name("naveen").gender("male").status("active").email(StringUtil.getRandomEmailId()).build();
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, ContentType.JSON, user, AuthType.BEARER_TOKEN, null, null);
		int userId = response.jsonPath().getInt("id");
		System.out.println(userId);
		Assert.assertTrue(response.statusLine().contains("Created"));
		Assert.assertEquals(response.jsonPath().getString("name"), user.getName());
		
		// 2. get user and verify the details
		
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		
		//3. put user and verify the details
		user.setName("Naveen Automation Labs");
		user.setStatus("inactive");
		Response responsePut = restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, ContentType.JSON, user, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responsePut.jsonPath().getInt("id"), userId);
		Assert.assertTrue(responsePut.statusLine().contains("OK"));
		Assert.assertEquals(responsePut.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responsePut.jsonPath().getString("status"), user.getStatus());
		
		
		// 2. get user and verify the details
		
		responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
		Assert.assertTrue(responseGet.statusLine().contains("OK"));
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("status"), user.getStatus());
		
	}

}
