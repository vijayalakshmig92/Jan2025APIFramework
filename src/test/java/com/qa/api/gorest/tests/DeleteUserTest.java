package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	@Test
	public void deleteUserTest() {
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
		Response responseDelete = restClient.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(responseDelete.statusLine().contains("No Content"));
		
		// 2. get user and verify the details
		
		responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, ContentType.JSON, AuthType.BEARER_TOKEN, null, null);
		Assert.assertTrue(responseGet.statusLine().contains("Not Found"));
		Assert.assertEquals(responseGet.getStatusCode(), 404);
		Assert.assertEquals(responseGet.jsonPath().getString("message"), "Resource not found");

	}


}
